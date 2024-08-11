// MusicStreamingApp.java

// Common interface for all music sources (Adapter Pattern)
interface MusicSource {
    void play();
}

// Concrete music sources
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

// Bridge Pattern: Decoupling the music playback functionality from the music source
abstract class MusicPlayer {
    protected MusicSource musicSource;

    public MusicPlayer(MusicSource musicSource) {
        this.musicSource = musicSource;
    }

    public abstract void play();
}

class BasicMusicPlayer extends MusicPlayer {

    public BasicMusicPlayer(MusicSource musicSource) {
        super(musicSource);
    }

    @Override
    public void play() {
        System.out.println("Using Basic Music Player:");
        musicSource.play();
    }
}

// Decorator Pattern: Adding additional features to the music playback
abstract class MusicPlayerDecorator extends MusicPlayer {
    protected MusicPlayer decoratedPlayer;

    public MusicPlayerDecorator(MusicPlayer player) {
        super(player.musicSource);
        this.decoratedPlayer = player;
    }

    @Override
    public void play() {
        decoratedPlayer.play();
    }
}

class EqualizerDecorator extends MusicPlayerDecorator {

    public EqualizerDecorator(MusicPlayer player) {
        super(player);
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

class VolumeControlDecorator extends MusicPlayerDecorator {

    public VolumeControlDecorator(MusicPlayer player) {
        super(player);
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
        // Adapter Pattern: Playing music from various sources
        MusicSource localFile = new LocalFileMusic("song.mp3");
        MusicSource onlineStream = new OnlineStreamingMusic("http://musicstream.com/stream");
        MusicSource radioStation = new RadioStationMusic("Rock Radio");

        // Bridge Pattern: Decoupling music playback from the music source
        MusicPlayer basicPlayerLocal = new BasicMusicPlayer(localFile);
        basicPlayerLocal.play();

        System.out.println();

        MusicPlayer basicPlayerStream = new BasicMusicPlayer(onlineStream);
        basicPlayerStream.play();

        System.out.println();

        MusicPlayer basicPlayerRadio = new BasicMusicPlayer(radioStation);
        basicPlayerRadio.play();

        System.out.println();

        // Decorator Pattern: Adding additional features like equalizer and volume control
        MusicPlayer equalizerPlayer = new EqualizerDecorator(basicPlayerLocal);
        equalizerPlayer.play();

        System.out.println();

        MusicPlayer volumeAndEqualizerPlayer = new VolumeControlDecorator(new EqualizerDecorator(basicPlayerStream));
        volumeAndEqualizerPlayer.play();
    }
}