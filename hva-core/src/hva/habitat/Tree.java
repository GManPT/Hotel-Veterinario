package hva.habitat;


public abstract class Tree {
    private String _idTree;
    public String _nameTree;
    private int _age;
    private int _cleaningDifficulty;
    
    public Tree(String idTree, String nameTree, int age, int cleaningDifficulty) {
        _idTree = idTree;
        _nameTree = nameTree;
        _age = age;
        _cleaningDifficulty = cleaningDifficulty;
    }
    
    public String getIdTree() {
        return _idTree;
    }

    public String getName() {
        return _nameTree;
    }

    public int getTreeAge() {
        return _age;
    }

    public int getCleaningDifficulty() {
        return _cleaningDifficulty;
    }

    public void increaseAge() {
        _age++;
    }

    @Override
    public String toString() {
        return "ARVORE|" + getIdTree() + "|" + getName() + "|" + getTreeAge() + getCleaningDifficulty() + "|";
    }
}