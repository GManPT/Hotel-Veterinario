package hva.habitat;

public class DeciduousTree extends Tree {




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
     * tree to string
     * 
     * @return tree to string
     */
    @Override
    public String toString() {
        return super.toString() + "CADUCA" + "|" + _bioCycle;
    }
}