package hva.calculatesatisfaction;

import hva.Hotel;
import java.io.Serializable;
import hva.habitat.Tree;
import hva.habitat.DeciduousTree;
import hva.habitat.EvergreenTree;

public class TreeEffort implements CalculateStrategy, Serializable {
    private static final long serialVersionUID = 202407081733L;

    /** Hotel */
    private Hotel _hotel;

    /**
     * Constructor
     */
    public TreeEffort(Hotel hotel) {
        _hotel = hotel;
    }

    /**
    * 
    * get the effort of a tree
    * @param idTree
    * @return effort
    */
    @Override
    public double calculate(String idTree) {

        Tree t = _hotel.getTree(idTree);
        int o = 0;

        if (t instanceof DeciduousTree) {
            o = 0;
        }
        else if (t instanceof EvergreenTree) {
            o = 1;
        }

        double effort = t.getCleaningDifficulty() * _hotel.getFlora().getSazonalEffort(o) * Math.log(t.getTreeAge() + 1);

        return effort;
    }

}