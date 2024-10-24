package hva.flora;

public class SpringState extends SeasonState {

    public SpringState(Flora flora) {
        super(flora, 0, "GERARFOLHAS","GERARFOLHAS",1,1);
    }

    public void advanceSeason() {
        // Change the season to Summer
        super.getFlora().changeSeason(new Summer(super.getFlora()));
    }

    public boolean isEqualTo(Season s) {
        return s instanceof Spring;
    }
}