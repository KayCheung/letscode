import java.io.FileInputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class AudioTest {
	public static void main(String[] args) throws Exception {

		AudioStream as = new AudioStream(new FileInputStream("c:/a/b/c.wav"));
//		AudioPlayer.player.start(as);
//		AudioPlayer.player.stop(as);

		AudioData ad = as.getData();
		ContinuousAudioDataStream cads = new ContinuousAudioDataStream(ad);
		AudioPlayer.player.start(cads);

		//AudioPlayer.player.stop(cads);

	}
}
