package hva;

import java.io.*;
import hva.exceptions.*;
//FIXME import other Java classes
//FIXME import other project classes

/**
 * Class that represents the hotel application.
 */
public class HotelManager {

    /** This is the current hotel. */
    private Hotel _hotel = new Hotel();

    // FIXME maybe add more fields if needed

    // Onde vai ser guardado o path do ficheiro associado ao hotel atual
    private String _filePath;  

    
    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        
        // verifica se existe algum ficheiro associado de momento
        if (_filePath == null) {
            throw new MissingFileAssociationException();
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filePath)));
            oos.writeObject(_hotel);
            oos.close();
          }
        catch (FileNotFoundException e) {e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

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

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filePath)));
            oos.writeObject(_hotel);
            oos.close();
          }
        catch (FileNotFoundException e) {e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
            _hotel = (Hotel) ois.readObject();
            ois.close();
            _filePath = filename;
          }
          catch (FileNotFoundException e) {
            throw new UnavailableFileException(filename);
        } catch (IOException e) {
            throw new UnavailableFileException(filename);
        } catch (ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Read text input file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
        _hotel.importFile(filename);
    }


    public Hotel getHotel() {
        return _hotel;
    }

}
