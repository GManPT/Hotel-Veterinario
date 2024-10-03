package hva;


public abstract class Tree {
    private String _idTree;
    private int _age;
    private int _cleaningDifficulty;
    
    public Tree(String idTree, int age, int cleaningDifficulty) {
        _idTree = idTree;
        _age = age;
        _cleaningDifficulty = cleaningDifficulty;
    }
    
    public String getIdTree() {
        return _idTree;
    }

    public int getTreeAge() {
        return _age;
    }

    public int getCleaningDifficulty() {
        return _cleaningDifficulty;
    }
}