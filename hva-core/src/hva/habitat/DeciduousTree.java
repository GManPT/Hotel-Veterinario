package hva.habitat;

public class DeciduousTree extends Tree implements EffortSheetTypeProvider {

    /** effort of the tree */
    private int _effort;

    /** type of tree */
    private final String _type = "CADUCA";

    /** 
     * To be implemented in final delivery, left "GERARFOLHAS" as default 
     * assuming that the tree is always in the same bio cycle
     */
    private String _bioCycle = "GERARFOLHAS";
    
    /**
     * Constructor for DeciduousTree
     */
    public DeciduousTree(String idTree, String nameTree, int age, int cleaningDifficulty) {
        super(idTree, nameTree, age, cleaningDifficulty);
    }
    
    /**
     * get effort
     * 
     * @return effort
     */
    public int getEffort() {
        return _effort;
    }

    /**
     * get type
     * 
     * @return type of tree
     */
    public String getType() {
        return _type;
    }

    /**
     * tree to string
     * 
     * @return tree to string
     */
    @Override
    public String toString() {
        return super.toString() + getType() + "|" + _bioCycle;
    }
}