package hva.flora;


public class Autumn extends Season{

    public Autumn(Flora flora) {
        super(flora, 2, "LARGARFOLHAS","COMFOLHAS",5,1);
    }

    public void advanceSeason() {
        // Change the season to Winter
        super.getFlora().changeSeason(new Winter(super.getFlora()));
    }

    public boolean isEqualTo(Season s) {
        return s instanceof Autumn;
    }
}
