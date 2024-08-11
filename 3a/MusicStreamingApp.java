// MusicStreamingApp.java

// Common interface for all music sources
interface MusicSource {
    void play();
}

// Adapter Pattern: Adapting different music sources to a common interface

// Local file music source
class LocalFileMusic implements MusicSource {
    private String fileName;

    public LocalFileMusic(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void play() {
        System.out.println("Playing music from local file: " + fileName);
    }
}

// Online streaming music source
class OnlineStreamingMusic implements MusicSource {
    private String streamUrl;

    public OnlineStreamingMusic(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    @Override
    public void play() {
        System.out.println("Streaming music online from: " + streamUrl);
    }
}

// Radio station music source
class RadioStationMusic implements MusicSource {
    private String stationName;

    public RadioStationMusic(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public void play() {
        System.out.println("Playing music from radio station: " + stationName);
    }
}

// Decorator Pattern: Adding additional features to the music playback

abstract class MusicDecorator implements MusicSource {
    protected MusicSource decoratedMusicSource;

    public MusicDecorator(MusicSource musicSource) {
        this.decoratedMusicSource = musicSource;
    }

    @Override
    public void play() {
        decoratedMusicSource.play();
    }
}

// Equalizer feature decorator
class EqualizerDecorator extends MusicDecorator {

    public EqualizerDecorator(MusicSource musicSource) {
        super(musicSource);
    }

    @Override
    public void play() {
        super.play();
        addEqualizer();
    }

    private void addEqualizer() {
        System.out.println("Applying equalizer settings.");
    }
}

// Volume control feature decorator
class VolumeControlDecorator extends MusicDecorator {

    public VolumeControlDecorator(MusicSource musicSource) {
        super(musicSource);
    }

    @Override
    public void play() {
        super.play();
        adjustVolume();
    }

    private void adjustVolume() {
        System.out.println("Adjusting the volume.");
    }
}

// Main Music Streaming Application
public class MusicStreamingApp {
    public static void main(String[] args) {
        // Playing local file music without any additional features
        MusicSource localFile = new LocalFileMusic("song.mp3");
        localFile.play();

        System.out.println();

        // Playing online streaming music with equalizer
        MusicSource onlineStream = new OnlineStreamingMusic("http://musicstream.com/stream");
        MusicSource onlineStreamWithEqualizer = new EqualizerDecorator(onlineStream);
        onlineStreamWithEqualizer.play();

        System.out.println();

        // Playing radio station music with equalizer and volume control
        MusicSource radioStation = new RadioStationMusic("Rock Radio");
        MusicSource radioWithFeatures = new VolumeControlDecorator(new EqualizerDecorator(radioStation));
        radioWithFeatures.play();
    }
}