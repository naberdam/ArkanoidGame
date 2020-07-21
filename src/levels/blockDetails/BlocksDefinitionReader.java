package levels.blockDetails;

import core.BlockCreator;
import levels.ColorsParser;
import levels.ImageParser;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksDefinitionReader bdr = new BlocksDefinitionReader();
        TreeMap<String, String> defaultArgs = new TreeMap<>();
        List<TreeMap<String, String>> sdef = new ArrayList<>();
        List<TreeMap<String, String>> types = new ArrayList<>();
        String read = "";
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(reader);
            while ((read = bufferedReader.readLine()) != null) {
                //check doc of empty line.
                if (read.startsWith("#") || read.length() == 0) {
                    continue;
                }
                //read the default line with all the defines.
                if (read.startsWith("default ")) {
                    defaultArgs = bdr.getArgsFromLine("default ", read);
                    continue;
                }
                if (read.startsWith("bdef ")) {
                    types.add(bdr.getArgsFromLine("bdef ", read));
                    continue;
                }
                if (read.startsWith("sdef ")) {
                    sdef.add(bdr.getArgsFromLine("sdef ", read));
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error9: " + e.getLocalizedMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
        }

        TreeMap<String, Integer> spacer = bdr.generateSpacer(sdef);
        TreeMap<String, BlockCreator> blocker = null;
        try {
            blocker = bdr.generateBlocker(types, defaultArgs);
        } catch (Exception e) {
            System.out.println("error2" + e.getLocalizedMessage());
        }
        return new BlocksFromSymbolsFactory(spacer, blocker);


    }

    /**
     * Convert Sting dict to int dict.
     *
     * @param stringTreeMap the cur dict.
     * @return int treemap.
     */
    private TreeMap<String, Integer> generateSpacer(List<TreeMap<String, String>> stringTreeMap) {
        TreeMap<String, Integer> updatedDict = new TreeMap<>();
        for (TreeMap<String, String> s : stringTreeMap) {
            String key = s.get("symbol");
            String value = s.get("width");
            if (value == null) {
                System.out.println("there is no width spacer");
                System.exit(1);
            }
            updatedDict.put(key, Integer.valueOf(value));
        }
        return updatedDict;
    }

    /**
     * convert treemao ti blockCreator.
     *
     * @param stringTreeMap the s,s map
     * @param defaultArgs   the def args.
     * @return new dict.
     * @throws Exception when there is not anough args.
     */
    private TreeMap<String, BlockCreator> generateBlocker(List<TreeMap<String, String>> stringTreeMap,
                                                          TreeMap<String, String> defaultArgs)
            throws Exception {
        TreeMap<String, BlockCreator> blockCreator = new TreeMap<>();
        String symbol, fill, stroke;
        Integer height, width, hitPoints;
        List<Color> fillColor = new ArrayList<>();
        List<String> fillImg = new ArrayList<>();
        Color scope;

        for (TreeMap<String, String> treeMap : stringTreeMap) {
            height = null;
            width = null;
            hitPoints = null;
            symbol = treeMap.get("symbol");
            String s;
            s = treeMap.get("height");
            if (s != null) {
                height = Integer.valueOf(s);
            }
            s = treeMap.get("width");
            if (s != null) {
                width = Integer.valueOf(s);
            }
            s = treeMap.get("hit_points");
            if (s != null) {
                hitPoints = Integer.valueOf(s);
            }
            fill = treeMap.get("fill");
            if (fill == null) {
                fill = treeMap.get("fill-1");
            }
            stroke = treeMap.get("stroke");
            String temp;
            if (height == null) {
                temp = defaultArgs.get("height");
                if (temp != null) {
                    height = Integer.valueOf(temp);
                }
            }
            if (width == null) {
                temp = defaultArgs.get("width");
                if (temp != null) {
                    width = Integer.valueOf(temp);
                }
            }
            if (hitPoints == null) {
                temp = defaultArgs.get("hit_points");
                if (temp != null) {
                    hitPoints = Integer.valueOf(temp);
                }
            }
            if (fill == null) {
                fill = defaultArgs.get("fill");
            }
            if (stroke == null) {
                stroke = defaultArgs.get("stroke");
            }
            if (height == null || width == null || hitPoints == null || fill == null) {
                System.out.println("Error3!");
                System.out.println("height:" + height + " width:" + width + " hit points:"
                        + hitPoints + " fill:" + fill + " stroke:" + stroke);
                System.exit(1);
            } else {
                ColorsParser cp = new ColorsParser();
                scope = cp.color(stroke);
                if (fill.startsWith("image(") && treeMap.get("fill") != null) {
                    fillImg.add(treeMap.get("fill"));
                    fillColor.add(null);
                } else if (fill.startsWith("color(") && treeMap.get("fill") != null) {
                    fillColor.add(cp.color(treeMap.get("fill")));
                    fillImg.add(null);
                }
                for (int i = 1; i <= hitPoints; i++) {
                    String fillK = treeMap.get("fill-" + i);
                    if (fillK == null) {
                        fillK = defaultArgs.get("fill-" + i);
                    }
                    if (fillK == null) {
                        continue;
                    }
                    if (fillK.startsWith("color")) {
                        fillColor.add(cp.color(fillK));
                        fillImg.add(null);
                    } else if (fillK.startsWith("image")) {
                        fillImg.add(fillK);
                        fillColor.add(null);
                    } else {
                        if (fillColor.get(0) != null) {
                            fillColor.add(fillColor.get(0));
                            fillImg.add(null);
                        } else if (fillImg.get(0) != null) {
                            fillImg.add(fillImg.get(0));
                            fillColor.add(null);
                        } else {
                            throw new Exception("Error7!");
                        }
                    }
                }
                ImageParser im = new ImageParser();
                BlockColorOrImage block = new BlockColorOrImage(height, width, hitPoints,
                        new ArrayList<>(fillColor), im.getImages(fillImg), scope);
                blockCreator.put(symbol, block);
                fillColor.clear();
                fillImg.clear();
            }
        }
        return blockCreator;
    }

    /**
     * get a line and seperate him to dict.
     *
     * @param type     the type of this line.
     * @param readLine the read kine.
     * @return treemap of this k:v
     */
    private TreeMap<String, String> getArgsFromLine(String type, String readLine) {
        String subString = readLine.substring(type.length());
        String[] defaultArgs = subString.split(" ");
        TreeMap<String, String> defaultArgsDict = new TreeMap<>();
        for (int i = 0; i < defaultArgs.length; i++) {
            int index = defaultArgs[i].indexOf(":");
            if (index == -1) {
                continue;
            }
            String key = defaultArgs[i].substring(0, index);
            String value = defaultArgs[i].substring(key.length() + 1);
            defaultArgsDict.put(key, value);
        }
        return defaultArgsDict;
    }
}

