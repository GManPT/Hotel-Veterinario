package hva.flora;


public class AutumnState extends SeasonState {

    /**
     * Constructor for the AutumnState
     */
    public AutumnState(Flora flora) {
        super(flora, 2, "LARGARFOLHAS","COMFOLHAS",5,1);
    }

    /**
     * Advance the season to the next season
     */
    public void advanceSeason() {
        // Change the season to Winter
        super.getFlora().changeSeason(new WinterState(super.getFlora()));
    }

    /**
     * Check if the season is equal to the given season
     * @param s the season to check
     * @return true if the season is equal to the given season
     */
    public boolean isEqualTo(SeasonState s) {
        return s instanceof AutumnState;
    }
}
