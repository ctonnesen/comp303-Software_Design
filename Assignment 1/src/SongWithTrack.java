import java.io.File;

public class SongWithTrack extends Song{
			private Integer trackNum;
			public SongWithTrack(Integer trackNum,File pFile,ExpectedTags aExpectedTagsMap,OptionalTags aOptionalTagsMap,CustomTags aCustomTagsMap,AudioFormat aFormat) {
				super(pFile,aExpectedTagsMap, aOptionalTagsMap, aCustomTagsMap);
				this.trackNum = trackNum;
			}
			public SongWithTrack(Song pSong, int trackNum) {
				super (pSong);
				this.trackNum = trackNum;
			}
			
			public Integer getTrackNum() {
				return this.trackNum;
			}	
}

