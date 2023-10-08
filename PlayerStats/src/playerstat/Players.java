package group4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import numberlist.IndexRangeException;
import numberlist.objectlist.Date;
import numberlist.objectlist.NumericObjectArrayList;
import numberlist.primitivelist.IntegerArrayList;

/**
 *
 * @author [Paula Elsaeed]
 * @author [Deanna Siaterlis]
 * @author [Hilana Ibrahim]
 * @author [Aman Siid]
 * @version 3/19/2020
 *
 */
public class Players {

    //fields
    ArrayList<String> name;
    private final IntegerArrayList pace;
    private final IntegerArrayList dribbling;
    private final IntegerArrayList physical;
    private final IntegerArrayList total;
    private final NumericObjectArrayList birthDate;
    private static boolean save = true;
    
    /**
     * constructor
     */
    public Players() {
        this.name = new ArrayList<>();
        this.pace = new IntegerArrayList();
        this.dribbling = new IntegerArrayList();
        this.physical = new IntegerArrayList();
        this.total = new IntegerArrayList();
        this.birthDate = new NumericObjectArrayList();
    }

    /**
     * Add a player to the list of players
     *
     * @param newName: name of the player
     * @param newBirthDate
     * @param newPace: the pace of the player (higher is better)
     * @param newDribbling: the dribbling skills of the player (higher is
     * better)
     * @param newPhysical: the physical condition of the player (higher is
     * better)
     *
     * @return the index of the newly inserted player
     */
    public int addPlayer(String newName, String newBirthDate, int newPace, int newDribbling, int newPhysical) {

        // Parse the date
        int month = Integer.parseInt(newBirthDate.split("/")[0]);
        int day = Integer.parseInt(newBirthDate.split("/")[1]);
        int year = Integer.parseInt(newBirthDate.split("/")[2]);
        Date newDateObj = new Date(month, day, year);

        name.add(newName);
        birthDate.insert(name.size() - 1, newDateObj);
        pace.insert(newPace);
        dribbling.insert(newDribbling);
        physical.insert(newPhysical);
        total.insert(newPace + newDribbling + newPhysical);

        return name.size() - 1;
    }
    /**
     * Delete at a given index
     * 
     * @param index 
     */
    public void deleteAt(int index) {
        try {
            name.remove(index);
            birthDate.deleteAt(index);
            pace.deleteAt(index);
            dribbling.deleteAt(index);
            physical.deleteAt(index);

        } catch (IndexRangeException ex) {
            Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the total number of players in the Data Class
     *
     * @return the number of players
     */
    public int numberOfPlayers() {
        return name == null ? 0 : name.size();
    }

    /**
     * Given an index, get the name of the player
     *
     * @param index: the index in the list
     * @return the name of the player
     * @throws IndexRangeException if index is out of range
     */
    public String getName(int index) throws IndexRangeException {
        return name.get(index);
    }

    /**
     * Given an index, get the age of the player
     *
     * @param index: the index in the list
     * @return the age of the player
     * @throws IndexRangeException if index is out of range
     */
    public String getBirthDate(int index) throws IndexRangeException {
        return birthDate.getValueAt(index).toString();
    }

    /**
     * Given an index, get the Pace of the player
     *
     * @param index: the index in the list
     * @return the pace of the player
     * @throws IndexRangeException if index is out of range
     */
    public long getPace(int index) throws IndexRangeException {
        return pace.getValueAt(index);
    }

    /**
     * Given an index, get the dribbling of the player
     *
     * @param index: the index in the list
     * @return the dribbling of the player
     * @throws IndexRangeException if index is out of range
     */
    public long getDribbling(int index) throws IndexRangeException {
        return dribbling.getValueAt(index);
    }

    /**
     * Given an index, get the physical condition of the player
     *
     * @param index: the index in the list
     * @return the physical condition of the player
     * @throws IndexRangeException if index is out of range
     */
    public long getPhysical(int index) throws IndexRangeException {
        return physical.getValueAt(index);
    }

    /**
     * Given an index, get the total performance of the player. The total
     * performance is the sum of pace, dribbling and physical condition
     *
     * @param index: the index in the list
     * @return the total stat of the player
     * @throws IndexRangeException if index is out of range
     */
    public long getTotal(int index) throws IndexRangeException {
        return total.getValueAt(index);
    }

    public int findPlayer(String name) {
        return this.name.indexOf(name);
    }

    /**
     * Given the option of an ENUM list SortType, return the appropriate object
     *
     * @param type: a value from the SortType [Age, Physical, Pace, Dribbling]
     * @return the appropriate object
     */
    public ReturningValues getSortingObject(SortType type) {
        switch (type) {
            case Pace:
                return new ReturningValues(pace, null);
            case Physical:
                return new ReturningValues(physical, null);
            case Dribbling:
                return new ReturningValues(dribbling, null);
            default:
                return null;
        }
    }

    public static class ReturningValues {

        public final IntegerArrayList integerList;
        public final NumericObjectArrayList numericList;
        /**
         * 
         * @param integerList
         * @param numericList 
         */
        public ReturningValues(IntegerArrayList integerList, NumericObjectArrayList numericList) {
            this.integerList = integerList;
            this.numericList = numericList;
        }
        /**
         * gets the value at a given index
         * 
         * @param index
         * @return 
         */
        public long getValueAt(int index) {
            if (this.integerList == null) {
                Date date = (Date) numericList.getValueAt(index);
                return date.toInteger();
            }
            return integerList.getValueAt(index);
        }
    }

    /**
     * Given a SortType, sort the list based on that type. Extract the object
     * from the function getSortingObject in order to build the Comparator
     *
     * @param type: a value from the SortType [Age, Physical, Pace, Dribbling]
     * @return the sorted list, a list of integers
     */
    public ArrayList<Integer> getSortedByTerm(SortType type) {
        ReturningValues sortingObject = getSortingObject(type);
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < name.size(); i++) {
            sorted.add(i);
        }
        sorted.sort((Integer a, Integer b) -> {
            try {
                return (int) (sortingObject.getValueAt(b) - sortingObject.getValueAt(a));
            } catch (IndexRangeException ex) {
                Logger.getLogger(Players.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        });

        return sorted;
    }
    /**
     * toString for displaying the players
     * 
     * @param index
     * @return
     * @throws IndexRangeException 
     */
    public String toString(int index) throws IndexRangeException {
        return "Name: " + "("+ name.get(index)+")"
                + " Birth: " + birthDate.getValueAt(index)
                + " Pace: " + pace.getValueAt(index) + " Dribbling: " + dribbling.getValueAt(index)
                + " Physical: " + physical.getValueAt(index);

    }

    /**
     * Saves the Players to a file
     *
     * @param listPlayer to be saved
     */
    public static void savePlayers(Players listPlayer) {
        File playerSave = new File("Players.txt");
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(playerSave))) {
            output.writeObject(listPlayer);
        } catch (IOException e) {
            if (save) {
                save = false;
                Alert popup = new Alert(Alert.AlertType.NONE);
                popup.setAlertType(Alert.AlertType.ERROR);
                popup.setTitle("Error");
                popup.setContentText("Cannot read/write to file."
                        + "\nFile save or load is disabled.");
                popup.show();
            }

        }
    }

    /**
     * Loads a Players from a file
     *
     * @return Players from a file
     */
    public static Players loadPlayers() {
        File file;
        Players Playerlist = new Players();
        try {
            file = new File("Players.txt");
            if (!file.exists()) {
                System.out.println("File does not exist... Creating");
                file.createNewFile();
                savePlayers(Playerlist);
            } else {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
                    Playerlist = (Players) input.readObject();
                } catch (ClassNotFoundException e) {
                    Alert popup = new Alert(Alert.AlertType.NONE);
                    popup.setAlertType(Alert.AlertType.ERROR);
                    popup.setTitle("Error");
                    popup.setContentText("Data Error");
                    popup.show();
                }
            }
        } catch (IOException e) {
            Alert popup = new Alert(Alert.AlertType.NONE);
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Error");
            popup.setContentText("Cannot read/write to file."
                    + "\nFile save or load is disabled.");
            popup.show();
        }
        return Playerlist;
    }
}
