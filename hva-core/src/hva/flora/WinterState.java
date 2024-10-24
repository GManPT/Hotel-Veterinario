package hva.flora;

public class WinterState extends SeasonState {

    public WinterState(Flora flora) {
        super(flora, 3, "SEMFOLHAS","LARGARFOLHAS",0,2);
    }

    public void advanceSeason() {
        // Change the season to Spring
        super.getFlora().changeSeason(new Spring(super.getFlora()));
    }

    public boolean isEqualTo(Season s) {
        return s instanceof Winter;
    }
}
