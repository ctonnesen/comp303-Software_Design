package activity4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.Test;

public class BufferDecoratorTest
{
	private final File filepath1 = new File("FilePathTest");
	private final Song song1 = new Song(filepath1);
	private final BufferDecorator buffer = new BufferDecorator(song1,2,3);
	

	@Test
	public void bufferTest() {
		assertEquals(buffer,buffer.clone());
	}
	

}
