package hva.habitat;

import java.io.Serializable;


public abstract class Tree implements Serializable {
    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    /** id of the tree */
    private String _idTree;

    /** name of the tree */
    public String _nameTree;

    /** age of the tree */
    private int _age;

    /** cleaning difficulty of the tree */
    private int _cleaningDifficulty;
    
    /**
     * constructor of the class Tree
     */
    public Tree(String idTree, String nameTree, int age, int cleaningDifficulty) {
        _idTree = idTree;
        _nameTree = nameTree;
        _age = age;
        _cleaningDifficulty = cleaningDifficulty;
    }

    public abstract void setBioCycle(String newBioCycle);

    /**
     * get the id of the tree
     * 
     * @return the id of the tree
     */
    public String getIdTree() {
        return _idTree;
    }

    /**
     * get the name of the tree
     * 
     * @return the name of the tree
     */
    public String getName() {
        return _nameTree;
    }

    /**
     * get the age of the tree
     * 
     * @return the age of the tree
     */
    public int getTreeAge() {
        return _age;
    }

    /**
     * get the cleaning difficulty of the tree
     * 
     * @return the cleaning difficulty of the tree
     */
    public int getCleaningDifficulty() {
        return _cleaningDifficulty;
    }

    /**
     * increase the age of the tree
     */
    public void increaseAge() {
        _age++;
    }

    /**
     * tree to string
     * 
     * @return tree in string format
     */
    @Override
    public String toString() {
        return "ÁRVORE|" + _idTree + "|" + _nameTree + "|" + _age + "|" + _cleaningDifficulty + "|";
    }
}