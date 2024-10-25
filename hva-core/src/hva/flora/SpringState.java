package hva.flora;

public class SpringState extends SeasonState {

    /**
     * Constructor for the SpringState class
     */
    public SpringState(Flora flora) {
        super(flora, 0, "GERARFOLHAS","GERARFOLHAS",1,1);
    }

    /**
     * Advance the season to the next season
     */
    public void advanceSeason() {
        // Change the season to Summer
        super.getFlora().changeSeason(new Summer(super.getFlora()));
    }

    /**
     * Check if the season is equal to the given season
     * @param s the season to compare to
     * @return true if the season is equal to the given season, false otherwise
     */
    public boolean isEqualTo(Season s) {
        return s instanceof Spring;
    }
}