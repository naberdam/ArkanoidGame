package levels;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Image parser.
 */
public class ImageParser {

    /**
     * Gets img.
     *
     * @param s the s
     * @return the img
     */
    public Image getImg(String s) {
        s = this.cleanString(s);
        if (s == null || s.length() == 0) {
            return null;
        }
        Image image = null;
        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            if (inputStream == null) {
                System.out.println("inputstream is null:" + "+*+" + ClassLoader.getSystemClassLoader());
                return image;
            }
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println(s);
            System.out.println("Error8!!:" + e.getMessage() + "  " + s + " " + e.getLocalizedMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
        return image;
    }

    /**
     * Gets images.
     *
     * @param l the l
     * @return the images
     */
    public java.util.List<Image> getImages(java.util.List<String> l) {
        List<Image> imageList = new ArrayList<>();
        for (String s : l) {
            imageList.add(getImg(s));
        }
        return imageList;
    }

    /**
     * Clean the string to the import.
     *
     * @param s the stringm
     * @return clean string.
     */
    private String cleanString(String s) {
        if (s == null) {
            return null;
        }
        if (s.startsWith("image(")) {
            s = s.substring("image(".length());
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
}

