package levels;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param file the file
     * @return the list
     */
    public List<LevelInformation> fromReader(File file) {
        if (file == null) {
            System.out.println("Error6!");
            return null;
        }
        List<LevelInformation> levelInformationList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        Reader reader = null;
        String read;
        String status = "";
        boolean isThereAnyLevel = false;
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("definitions/" + file.getName());
            if (inputStream == null) {
                System.out.println("definitions/" + file.getName() + "++" + ClassLoader.getSystemClassLoader());
                return levelInformationList;
            }
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            TreeMap<String, String> levelTree = new TreeMap<>();
            List<String> linesOfBlocks = new ArrayList<>();
            while ((read = bufferedReader.readLine()) != null) {
                //empty line of comments.
                if (read.startsWith("#") || read.length() == 0) {
                    continue;
                }
                if (read.equals("START_LEVEL")) {
                    isThereAnyLevel = false;
                    status = "START_LEVEL";
                    continue;
                }
                if (read.equals("START_BLOCKS")) {
                    status = "START_BLOCKS";
                    continue;
                }
                if (read.equals("END_BLOCKS")) {
                    status = "";
                    continue;
                }
                if (read.equals("END_LEVEL")) {
                    isThereAnyLevel = true;
                    levelInformationList.add(new Level(new TreeMap<>(levelTree), new ArrayList<>(linesOfBlocks)));
                    read = null;
                    status = "";
                    linesOfBlocks.clear();
                    levelTree.clear();
                    continue;
                }
                if (status.equals("START_LEVEL")) {
                    int index = read.indexOf(":");

                    if (index != -1) {
                        String key = read.substring(0, index);
                        String value = read.substring(index + 1);
                        //mapping propertise
                        levelTree.put(key, value);
                    }
                }
                if (status.equals("START_BLOCKS")) {
                    //add 1 line of blocks as string.
                    linesOfBlocks.add(read);
                }
            } //end of while true
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
        }
        if (!isThereAnyLevel) {
            System.out.println("there is no levels");
            System.exit(1);
        }
        return levelInformationList;
    }
}
