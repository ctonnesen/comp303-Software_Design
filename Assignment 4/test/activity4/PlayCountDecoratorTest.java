package activity4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;


class PlayCountDecoratorTest {

    static class PlayableExample implements Playable {
        private static final Playable NULL = new PlayableExample("NUll Object") {
            @Override
            public boolean isNull() {
                return true;
            }
        };

        private String aDescription;
        private int aTime = 0;

        public PlayableExample(String pDescription) {
            assert pDescription != null;
            aDescription = pDescription;
        }

        @Override
        public void play() {
            System.out.println("Playing");
        }

        @Override
        public int duration() {
            return aTime;
        }

        @Override
        public String description() {
            return aDescription;
        }

        @Override
        public boolean isNull() {
            return false;
        }

        @Override
        public Playable clone() {
            PlayableExample clone = new PlayableExample(aDescription);
            clone.aTime = aTime;
            return clone;
        }

    }

    PlayableExample testPlayExReal = new PlayableExample("TestDescription");
    PlayCountDecorator testPCD = new PlayCountDecorator(testPlayExReal);

    @Test
    void playTest() {
        try {
            testPCD.play();
            testPCD.play();
            testPCD.play();
            Field playCountField = PlayCountDecorator.class.getDeclaredField("playCount");
            playCountField.setAccessible(true);
            assertEquals(3, playCountField.get(testPCD));
        } catch (ReflectiveOperationException e) {
            fail();
            System.out.print("playTest failed");
        }
    }

    @Test
    void durationTest() {
        try {
            Field timeField = PlayableExample.class.getDeclaredField("aTime");
            timeField.setAccessible(true);
            timeField.setInt(testPlayExReal, 32);
            assertEquals(32, testPCD.duration());
        } catch (ReflectiveOperationException e) {
            fail();
            System.out.print("durationTest failed");
        }
    }

    @Test
    void descriptionTest() {
        try {
            Field descriptionField = PlayableExample.class.getDeclaredField("aDescription");
            descriptionField.setAccessible(true);
            String output = "Played for " + 0 + "times. TestDescription";
            assertEquals(output, testPCD.description());
        } catch (ReflectiveOperationException e) {
            fail();
            System.out.print("descriptionTest failed");
        }
    }

    @Test
    void isNullTest() {
        try {
            assertFalse(testPCD.isNull());
            PlayCountDecorator nullPCD = new PlayCountDecorator(PlayableExample.NULL);
            assertTrue(nullPCD.isNull());
        } catch (Exception e) {
            fail();
            System.out.println("isNullTest failed");
        }
    }

    @Test
    void cloneTest() {
        try {
            Field descriptionField = PlayableExample.class.getDeclaredField("aDescription");
            descriptionField.setAccessible(true);
            String newDescription = "This is the new test description for cloneTest";
            descriptionField.set(testPlayExReal, newDescription);
            Field timeField = PlayableExample.class.getDeclaredField("aTime");
            timeField.setAccessible(true);
            timeField.setInt(testPlayExReal, 32);
            Field playCountField = PlayCountDecorator.class.getDeclaredField("playCount");
            playCountField.setAccessible(true);
            testPCD.play();
            testPCD.play();
            PlayCountDecorator clone = testPCD.clone();
            assertEquals(testPCD.description(), clone.description());
            assertEquals(testPCD.duration(), clone.duration());
            assertEquals(playCountField.get(testPCD), playCountField.get(clone));
        } catch (ReflectiveOperationException e) {
            fail();
            System.out.println("cloneTest failed");
        }
    }
}