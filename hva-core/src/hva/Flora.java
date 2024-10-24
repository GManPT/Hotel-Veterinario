package hva;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import hva.habitat.Tree;
import hva.Hotel;

public class Flora implements Serializable{
    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    private Hotel _hotel;

    // Current Season: 0 - Primavera, 1 - Verao, 2 - Outono, 3 - Inverno
    private int _currentSeason;

    // Tree Map with the initial Season of each tree
    private Map<String,Integer> _treeSeason;
    

    // Array with the effort of each season accotiding to the type of tree
    private int[] _sazonal = {1,2,5,0,1,1,1,2};

    // constructor
    public Flora(Hotel hotel) {
        _hotel = hotel;
        _currentSeason = 0; // Primavera
        _treeSeason = new TreeMap<String, Integer>();
    }

    // Advances season and increases age of trees born in that season
    public void changeSeason() {
        
        if (_currentSeason == 3) {
           _currentSeason = 0;
        }

        else {
            _currentSeason += 1;
        }

        // Loops through all trees and increases all that were planted in the current season
        for(String idTree : _treeSeason.keySet()) {
            if (_treeSeason.get(idTree) == _currentSeason) {
                _hotel.getTree(idTree).increaseAge();
            }
        }
    }

    public void putFloraSeason(String idTree) {
        _treeSeason.put(idTree, _currentSeason);
    }

    public int getSazonalEffort(String type) {
        int i;
        if (type == "CADUCA") {
            i = 0;
        }
        else {
            i = 4;
        }

        return _sazonal[_currentSeason + i];
    }
}