package hva.flora;

import java.io.Serializable;


public abstract class SeasonState implements Serializable {

    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    /** int numSeason - 0 (Spring), 1 (Summer), 2 (Autumn), 3 (Winter) */
    private int _numSeason;

    /** String bioCycleCaduca - Caduca's bio cycle */
    private String _bioCycleCaduca;

    /** String bioCyclePerene - Perene's bio cycle */
    private String _bioCyclePerene;

    /** int sazonalEffortCaduca - Caduca's seasonal effort */
    private int _sazonalEffortCaduca;

    /** int sazonalEffortPerene - Perene's seasonal effort */
    private int _sazonalEffortPerene;

    /** Flora _flora - Flora object */
    private Flora _flora;

    /**
     * Constructor
     */
    public SeasonState (Flora flora, int numSeason, String bioCycleCaduca, String bioCyclePerene, int sazonalEffortCaduca, int sazonalEffortPerene) {

        _numSeason = numSeason;
        _bioCycleCaduca = bioCycleCaduca;
        _bioCyclePerene = bioCyclePerene;
        _flora = flora;
        _sazonalEffortCaduca = sazonalEffortCaduca;
        _sazonalEffortPerene = sazonalEffortPerene;
    }

    /**
     * get flora
     * 
     * @return Flora
     */
    public Flora getFlora() {
        return _flora;
    }

    /**
     * get numSeason
     * 
     * @return int
     */
    public int getNumSeason() {
        return _numSeason;
    }

    /**
     * get bioCycleCaduca
     * 
     * @return String
     */
    public String getBioCycleCaduca() {
        return _bioCycleCaduca;
    }

    /**
     * get bioCyclePerene
     * 
     * @return String
     */
    public String getBioCyclePerene() {
        return _bioCyclePerene;
    }

    /**
     * get sazonalEffortCaduca
     * 
     * @return int
     */
    public int getSazonalEffortCaduca() {
        return _sazonalEffortCaduca;
    }

    /**
     * get sazonalEffortPerene
     * 
     * @return int
     */
    public int getSazonalEffortPerene() {
        return _sazonalEffortPerene;
    }

    /**
     * Abstract method to advance season
     */
    public abstract void advanceSeason();

    /**
     * Abstract method to check if season is equal to
     * 
     * @param s
     * @return boolean
     */
    public abstract boolean isEqualTo(SeasonState s);
}