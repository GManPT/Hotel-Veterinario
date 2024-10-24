package hva.flora;

import hva.habitat.DeciduousTree;
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
    private Season _currentSeason;

    // Tree Map with the initial Season of each tree
    private Map<String,Season> _treeSeason;
    

    // Array with the effort of each season accotiding to the type of tree
    private int[] _sazonal = {1,2,5,0,1,1,1,2};

    // constructor
    public Flora(Hotel hotel) {
        _hotel = hotel;
        _currentSeason = new Spring(this);
        _treeSeason = new TreeMap<String, Season>();
    }

    // Advances season and increases age of trees born in that season
    public void changeSeason(Season season) {

        _currentSeason = season;

        // Loops through all trees and increases all that were planted in the current season, also change biocycle
        for(String id : _treeSeason.keySet()) {

            Tree t = _hotel.getTree(id);
            if (t instanceof DeciduousTree) {
                t.setBioCycle(_currentSeason.getBioCycleCaduca());
            }
            else {
                t.setBioCycle(_currentSeason.getBioCyclePerene());
            }

            Season s = _treeSeason.get(id);
            if (s.isEqualTo(_currentSeason)) {
                t.increaseAge();
            }
        }
    }

    public void putFloraSeason(String idTree) {
        _treeSeason.put(idTree, _currentSeason);
    }

    public int getSazonalEffort(int o) {

        if (o == 0) {
            return _currentSeason.getSazonalEffortCaduca();
        }

        return _currentSeason.getSazonalEffortPerene();
    }

    public Season getCurrentSeason() {
        return _currentSeason;
    }

}