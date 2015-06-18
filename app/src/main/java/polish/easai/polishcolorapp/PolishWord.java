package polish.easai.polishcolorapp;

/**
 * Created by IB USER on 6/16/2015.
 */
public class PolishWord {
    public String getPl() {
        return pl;
    }

    public String getEn() {
        return en;
    }

    public int getAudioID() {
        return audioID;
    }

    public int getColor() {
        return color;
    }

    String pl;
    String en;
    int audioID;
    int color;

    public PolishWord(String pl, String en, int audioID, int color) {
        this.pl = pl;
        this.en = en;
        this.audioID = audioID;
        this.color=color;
    }
}
