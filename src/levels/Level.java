package levels;

import arkanoid.Block;
import core.Counter;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import levels.blockDetails.BackGroundImage;
import levels.blockDetails.BlocksDefinitionReader;
import levels.blockDetails.BlocksFromSymbolsFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Level.
 */
public class Level implements LevelInformation {

    private int numOfBalls;
    private List<Counter> numOfBlocksInLines;
    private TreeMap<String, String> treeMap;
    private List<String> blockList;
    private BlocksFromSymbolsFactory fromSymbolsFactory;


    /**
     * Constructor.
     *
     * @param treeMapin     is the map of setting.
     * @param linesOfBlocks the lines of blocks
     */
    public Level(TreeMap<String, String> treeMapin, List<String> linesOfBlocks) {
        this.treeMap = treeMapin;
        this.blockList = linesOfBlocks;
        numOfBlocksInLines = new ArrayList<>();
        InputStream inputStream = null;
        Reader reader = null;
        if (treeMap.get("block_definitions") == null) {
            System.out.println("there is no block definitions");
            System.exit(1);
        }
        inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(treeMap.get("block_definitions"));
        if (inputStream != null) {
            reader = new InputStreamReader(inputStream);
        } else {
            System.out.println("inputStream is null!!!" + treeMap.get("block_definitions"));
        }
        this.fromSymbolsFactory = BlocksDefinitionReader.fromReader((reader));

    }

    @Override
    public int numberOfBalls() {
        String s = this.treeMap.get("ball_velocities");
        if (s == null) {
            System.out.println("there is no ball velocities");
            System.exit(1);
        }
        String[] arr = s.split(" ");
        this.numOfBalls = arr.length;
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        String s = this.treeMap.get("ball_velocities");
        if (s == null) {
            System.out.println("there is no ball velocities");
            System.exit(1);
        }
        String[] arr = s.split(" ");
        List<Velocity> bVel = new ArrayList<>(numberOfBalls());
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i].indexOf(",");
            double dx = Double.valueOf(arr[i].substring(0, index)) % 12;
            double dy = Double.valueOf(arr[i].substring(index + 1)) % 12;
            bVel.add(Velocity.fromAngleAndSpeed(dx, dy));
        }
        return bVel;
    }

    @Override
    public int paddleSpeed() {
        if (treeMap.get("paddle_speed") == null) {
            System.out.println("there is no paddle speed");
            System.exit(1);
        }
        return Integer.valueOf(treeMap.get("paddle_speed"));
    }

    @Override
    public int paddleWidth() {
        if (treeMap.get("paddle_width") == null) {
            System.out.println("there is no paddle width");
            System.exit(1);
        }
        return Integer.valueOf(treeMap.get("paddle_width"));

    }

    @Override
    public String levelName() {
        if (treeMap.get("level_name") == null) {
            System.out.println("there is no level name");
            System.exit(1);
        }
        return this.treeMap.get("level_name");
    }

    @Override
    public Sprite getBackground() {
        String s = treeMap.get("background");
        if (s == null) {
            System.out.println("there is no background");
            System.exit(1);
        }

        if (s.startsWith("image(")) {
            ImageParser ip = new ImageParser();
            return new BackGroundImage(ip.getImg(s));

        } else {
            BackGroundOfLevels backGroundOfLevels = new BackGroundOfLevels();
            ColorsParser cp = new ColorsParser();
            backGroundOfLevels.drawBlock(new Point(0, 0), 800, 600, cp.color(s));
            return backGroundOfLevels;
        }
    }

    @Override
    public List<Block> blocks() {
        List<Block> b;
        if (treeMap.get("blocks_start_x") == null || treeMap.get("blocks_start_y") == null) {
            System.out.println("there is no block start points");
            System.exit(1);
        }
        if (treeMap.get("row_height") == null) {
            System.out.println("there is no row height");
            System.exit(1);
        }
        int blockX = Integer.valueOf(treeMap.get("blocks_start_x"));
        int blockY = Integer.valueOf(treeMap.get("blocks_start_y"));
        int rowHeight = Integer.valueOf(treeMap.get("row_height"));
        b = this.fromSymbolsFactory.getBlockTable(this.blockList, blockX, blockY, rowHeight);
        return b;
    }


    @Override
    public int numberOfBlocksToRemove() {
        if (treeMap.get("num_blocks") == null) {
            System.out.println("there is no num blocks");
            System.exit(1);
        }
        return Integer.valueOf(treeMap.get("num_blocks"));
    }

    /**
     * Gets num of blocks in lines.
     *
     * @return list of counter with how many blocks each line.
     */
    public List<Counter> getNumOfBlocksInLines() {
        return numOfBlocksInLines;
    }
}

