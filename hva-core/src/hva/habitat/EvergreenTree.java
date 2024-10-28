package hva.habitat;

public class EvergreenTree extends Tree {

    /** 
     * To be implemented in final delivery, left "GERARFOLHAS" as default 
     * assuming that the tree is always in the same bio cycle
     */
    private String _bioCycle;
    
    /**
     * Constructor of the class EvergreenTree
     */
    public EvergreenTree(String idTree, String nameTree, int age, int cleaningDifficulty) {
        super(idTree, nameTree, age, cleaningDifficulty);
    }

    /**
     * Set bioCycle
     * 
     * @param String new BioCycle
     */
    public void setBioCycle(String newBioCycle) {
        _bioCycle = newBioCycle;
    }

    /**
     * tree to string
     * 
     * @return the tree to string
     */
    @Override
    public String toString() {
        return super.toString() + "PERENE" + "|" + _bioCycle;
    }
}