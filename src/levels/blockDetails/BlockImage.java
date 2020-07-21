package levels.blockDetails;

import arkanoid.Block;
import biuoop.DrawSurface;
import core.BlockCreator;

import java.awt.Image;
import java.awt.Color;
import java.util.List;

/**
 * The type Block image.
 */
public class BlockImage extends Block implements BlockCreator {
    private java.util.List<Image> hitImages;
    private int h;
    private int w;
    private int hitP;
    private Color stroke;

    /**
     * Instantiates a new Block color.
     *
     * @param h         the h
     * @param w         the w
     * @param hitP      the hit p
     * @param fillColor the fill color
     * @param stroke    the stroke
     */
    public BlockImage(int h, int w, int hitP, List<Image> fillColor, Color stroke) {
        super(0, 0, h, w);
        this.setNumHits(hitP);
        this.hitP = hitP;
        this.hitImages = fillColor;
        this.setScopeColor(stroke);
        this.h = h;
        this.w = w;
        this.stroke = stroke;


    }


    /**
     * Draw on.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int i1 = (int) this.getBlock().getUpperLeft().getX();
        int i2 = (int) this.getBlock().getUpperLeft().getY();
        if (hitImages.size() == 1) {
            surface.drawImage(i1, i2, this.hitImages.get(0));
        } else if (this.hitImages.size() >= getHitPoints()) {
            surface.drawImage(i1, i2, this.hitImages.get(getHitPoints() - 1));
        }
        super.drawOn(surface);


    }


    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return w;
    }

    /**
     * Gets hit images.
     *
     * @return the hit images
     */
    public List<Image> getHitImages() {
        return hitImages;
    }

    /**
     * Gets hit p.
     *
     * @return the hit p
     */
    public int getHitP() {
        return hitP;
    }

    /**
     * Gets stroke.
     *
     * @return the stroke
     */
    public Color getStroke() {
        return stroke;
    }

    /**
     * Create block.
     *
     * @param xLocation the xLocation
     * @param yLocation the yLocation
     * @return the block
     */
    @Override
    public Block create(int xLocation, int yLocation) {
        BlockImage b = new BlockImage(getH(), getW(), getHitP(), getHitImages(), getStroke());
        b.setLocation(xLocation, yLocation);
        return b;
    }
}
