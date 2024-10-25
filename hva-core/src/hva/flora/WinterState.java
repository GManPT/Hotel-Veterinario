package hva.flora;

public class WinterState extends SeasonState {

    /**
     * Constructor for the WinterState class
     */
    public WinterState(Flora flora) {
        super(flora, 3, "SEMFOLHAS","LARGARFOLHAS",0,2);
    }

    /**
     * Advance the season to the next season
     */
    public void advanceSeason() {
        // Change the season to Spring
        super.getFlora().changeSeason(new SpringState(super.getFlora()));
    }

    /**
     * Check if the season is equal to the given season
     * @param s the season to compare to
     * @return true if the season is equal to the given season, false otherwise
     */
    public boolean isEqualTo(SeasonState s) {
        return s instanceof WinterState;
    }
}
