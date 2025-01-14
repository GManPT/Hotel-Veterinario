package hva;

import java.io.*;
import hva.exceptions.*;

/**
 * Class that represents the hotel application.
 */
public class HotelManager {

    /** This is the current hotel */
    private Hotel _hotel = new Hotel();

    /** the path where the hotel is stored */
    private String _filePath;

    /** 
     * hotel changed
     * 
     * @return true if the hotel has been modified since the last save
     */
    public void changed() throws StateChangedException {
        if(_hotel.isModified()) {
            throw new StateChangedException();
        }
    }

    /** 
     * creates a new hotel
     * file path is set to null
     */
    public void resetHotel() {
        _hotel = new Hotel();
        _filePath = null;
    }
    
    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        
        // verifica se existe algum ficheiro associado de momento
        if (_filePath == null || _filePath == "") {
            throw new MissingFileAssociationException();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filePath)))) {
            oos.writeObject(_hotel);
            oos.close();
        } 

        _hotel.setModified(false);

    }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {

        _filePath = filename;

        save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
        
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            _hotel = (Hotel) ois.readObject();
            ois.close();
            _filePath = filename;
        }

    }

    /**
     * Read text input file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
        try {
            _hotel.importFile(filename);
        }
        catch (UnrecognizedEntryException e) {
            throw new ImportFileException(filename, e);
        }
    }


    /**
     * get hotel
     * 
     * @return the hotel
     */
    public Hotel getHotel() {
        return _hotel;
    }

    /**
     * change season
     * 
     * @return the new season as integer
     */
    public int changeSeason() {
        return _hotel.changeSeason();
    }

    /**
     * global satisfaction
     * 
     * @return the global satisfaction
     */
    public double globalSatisfaction() {
        try {
            return _hotel.globalSatisfaction();
        } catch (UnknownAnimalException | UnknownSpeciesException e) {
            return 0;
        }

    }

}
