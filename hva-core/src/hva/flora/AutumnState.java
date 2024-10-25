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
        super.getFlora().changeSeason(new Winter(super.getFlora()));
    }

    /**
     * Check if the season is equal to the given season
     * @param s the season to check
     * @return true if the season is equal to the given season
     */
    public boolean isEqualTo(Season s) {
        return s instanceof Autumn;
    }
}
