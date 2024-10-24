package hva.flora;


public class Summer extends Season{

    public Summer(Flora flora) {
        super(flora, 1, "COMFOLHAS","COMFOLHAS",2,1);
    }

    public void advanceSeason() {
        // Change the season to Autumn
        super.getFlora().changeSeason(new Autumn(super.getFlora()));
    }

    public boolean isEqualTo(Season s) {
        return s instanceof Summer;
    }
}
