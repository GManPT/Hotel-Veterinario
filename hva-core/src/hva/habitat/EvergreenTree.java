package hva.habitat;

public class EvergreenTree extends Tree {


    /** type of the tree */
    private final String _type = "PERENE";

    /** 
     * To be implemented in final delivery, left "GERARFOLHAS" as default 
     * assuming that the tree is always in the same bio cycle
     */
    private String _bioCycle = "GERARFOLHAS";
    
    /**
     * Constructor of the class EvergreenTree
     */
    public EvergreenTree(String idTree, String nameTree, int age, int cleaningDifficulty) {
        super(idTree, nameTree, age, cleaningDifficulty);
    }

    /**
     * get type
     * 
     * @return the type of the tree
     */
    public String getType() {
        return _type;
    }

    /**
     * tree to string
     * 
     * @return the tree to string
     */
    @Override
    public String toString() {
        return super.toString() + getType() + "|" + _bioCycle;
    }
}