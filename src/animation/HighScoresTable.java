package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {

    private List<ScoreInfo> highScore;
    private int size;
    private File file;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.highScore = new ArrayList<>(size);
    }

    /**
     * Instantiates a new High scores table.
     *
     * @param size      the size
     * @param highScore the high score
     */
    public HighScoresTable(int size, List<ScoreInfo> highScore) {
        this.highScore = highScore;
        this.size = size;
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        int index = this.getRank(score.getScore());
        if (index <= size) {
            if (this.highScore.size() == size) {
                this.highScore.remove(size - 1);
            }
            this.highScore.add(index - 1, score);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.highScore;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i;
        for (i = 0; i < getHighScores().size(); i++) {
            if (getHighScores().get(i).getScore() <= score) {
                //i+1 because the array start from 0
                return (i + 1);
            }
        }
        //dont enter this score or the list is empty.
        return i + 1;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        getHighScores().clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable highScoresTable = loadFromFile(filename);
        this.highScore = highScoresTable.getHighScores();
        this.size = highScoresTable.size();
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);

            // unsafe down casting, we better be sure that the stream really contains a Person!
            highScoresTable = (HighScoresTable) objectInputStream.readObject();

        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            highScoresTable = new HighScoresTable(5);
            try {
                highScoresTable.save(filename);
            } catch (IOException e2) {
                System.err.println("Unable to create new file");
            }
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename.getName());
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
        return highScoresTable;

    }

    /**
     * Sets file.
     *
     * @param file1 the file 1
     */
    public void setFile(File file1) {
        this.file = file1;
    }

    /**
     * Size of list int.
     *
     * @return the int
     */
    public int sizeOfList() {
        return getHighScores().size();
    }
}
