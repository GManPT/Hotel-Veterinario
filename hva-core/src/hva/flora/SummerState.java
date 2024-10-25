package hva.flora;


public class SummerState extends SeasonState {

    /**
     * Constructor for the SummerState class
     */
    public SummerState(Flora flora) {
        super(flora, 1, "COMFOLHAS","COMFOLHAS",2,1);
    }

    /**
     * Advance the season to the next season
     */
    public void advanceSeason() {
        // Change the season to Autumn
        super.getFlora().changeSeason(new Autumn(super.getFlora()));
    }

    /**
     * Check if the season is equal to the given season
     * @param s the season to compare to
     * @return true if the season is equal to the given season, false otherwise
     */
    public boolean isEqualTo(Season s) {
        return s instanceof Summer;
    }
}

