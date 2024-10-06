package hva.habitat;

public class DeciduousTree extends Tree implements EffortSheetTypeProvider {
    private int _effort;
    public String _type = "CADUCA";
    
    public DeciduousTree(String idTree, String nameTree, int age, int cleaningDifficulty) {
        super(idTree, nameTree, age, cleaningDifficulty);
    }
    
    public int getEffort() {
        return _effort;
    }

    public String getType() {
        return _type;
    }

    @Override
    public String toString() {
        return super.toString() + getType();
    }
}