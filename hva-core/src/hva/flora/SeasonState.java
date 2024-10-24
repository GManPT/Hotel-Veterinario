package hva.flora;

import java.io.Serializable;


public abstract class SeasonState implements Serializable {

    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    private int _numSeason;
    private String _bioCycleCaduca;
    private String _bioCyclePerene;
    private int _sazonalEffortCaduca;
    private int _sazonalEffortPerene;
    private Flora _flora;

    public SeasonState (Flora flora, int numSeason, String bioCycleCaduca, String bioCyclePerene, int sazonalEffortCaduca, int sazonalEffortPerene) {

        _numSeason = numSeason;
        _bioCycleCaduca = bioCycleCaduca;
        _bioCyclePerene = bioCyclePerene;
        _flora = flora;
        _sazonalEffortCaduca = sazonalEffortCaduca;
        _sazonalEffortPerene = sazonalEffortPerene;
    }

    public Flora getFlora() {
        return _flora;
    }

    public int getNumSeason() {
        return _numSeason;
    }

    public String getBioCycleCaduca() {
        return _bioCycleCaduca;
    }

    public String getBioCyclePerene() {
        return _bioCyclePerene;
    }

    public int getSazonalEffortCaduca() {
        return _sazonalEffortCaduca;
    }

    public int getSazonalEffortPerene() {
        return _sazonalEffortPerene;
    }

    public abstract void advanceSeason();

    public abstract boolean isEqualTo(Season s);
}