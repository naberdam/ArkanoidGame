package animation;


import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Read sub menu.
 */
public class ReadSubMenu {
    private TreeMap<String, SubMenuPath> sub;
    private List<String> keys;

    /**
     * Instantiates a new Read sub menu.
     *
     * @param file the file
     */
    public ReadSubMenu(File file) {
        TreeMap<String, SubMenuPath> subMenu = new TreeMap<>();
        this.keys = new ArrayList<>();
        LineNumberReader lineNumberReader = null;
        InputStream inputStream = null;
        Reader reader = null;
        boolean nullOrNot = false;
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(file.getName());
            if (inputStream != null) {
                reader = new InputStreamReader(inputStream);
            } else {
                System.out.println("inputStream is null!!!" + file.getName());
            }
            if (reader != null) {
                lineNumberReader = new LineNumberReader(reader);
                String s;
                String m;
                while (true) {
                    s = lineNumberReader.readLine();
                    m = lineNumberReader.readLine();
                    if (!nullOrNot && (s == null || m == null)) {
                        this.sub = null;
                        break;
                    }
                    if (s == null || m == null) {
                        break;
                    }
                    nullOrNot = true;
                    keys.add(s.substring(0, 1));
                    subMenu.put(s.substring(0, 1), new SubMenuPath(s.substring(2), m));
                }
            } else {
                System.out.println("reader is null!");
            }
            if (nullOrNot) {
                this.sub = subMenu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Gets keys.
     *
     * @return the keys
     */
    public List<String> getKeys() {
        return keys;
    }

    /**
     * Gets sub.
     *
     * @return the sub
     */
    public TreeMap<String, SubMenuPath> getSub() {
        return sub;
    }
}
