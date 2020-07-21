package levels.blockDetails;

import arkanoid.Block;
import biuoop.DrawSurface;
import core.BlockCreator;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

/**
 * The type Block color or image.
 */
public class BlockColorOrImage extends Block implements BlockCreator {
    private java.util.List<Color> colorList;
    private java.util.List<Image> imageList;
    private int h;
    private int w;
    private int hitP;
    private Color stroke;

    /**
     * Instantiates a new Block color or image.
     *
     * @param h      the h
     * @param w      the w
     * @param hitP   the hit p
     * @param c      the c
     * @param i      the
     * @param stroke the stroke
     */
    public BlockColorOrImage(int h, int w, int hitP, List<Color> c, List<Image> i, Color stroke) {
        super(0, 0, w, h);
        this.h = h;
        this.w = w;
        this.stroke = stroke;
        this.hitP = hitP;
        this.colorList = c;
        this.imageList = i;
        this.setScopeColor(stroke);
        this.setNumHits(hitP);

    }

    /**
     * Gets color list.
     *
     * @return the color list
     */
    public java.util.List<Color> getColorList() {
        return colorList;
    }

    /**
     * Draw the blocks.
     *
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        int i1 = (int) this.getBlock().getUpperLeft().getX();
        int i2 = (int) this.getBlock().getUpperLeft().getY();
        int i3 = (int) this.getBlock().getWidthOfRec();
        int i4 = (int) this.getBlock().getHeightOfRec();
        int hitPoint = getHitPoints();
        if (hitPoint >= 1) {
            if (this.imageList.size() > 1 && this.imageList.get(hitPoint - 1) != null) {
                surface.drawImage(i1, i2, this.imageList.get(hitPoint - 1));
            } else if (this.colorList.size() > 1 && this.colorList.get(hitPoint - 1) != null) {
                surface.setColor(this.colorList.get(hitPoint - 1));
                surface.fillRectangle(i1, i2, i3, i4);
            } else if (colorList.size() == 1 && colorList.get(0) != null) {
                surface.setColor(this.colorList.get(0));
                surface.fillRectangle(i1, i2, i3, i4);
            } else if (imageList.size() == 1 && imageList.get(0) != null) {
                surface.drawImage(i1, i2, this.imageList.get(0));
            } else {
                surface.setColor(Color.MAGENTA);
                surface.fillRectangle(i1, i2, i3, i4);
            }
        }
        super.drawOnScope(surface);
    }

    /**
     * Gets image list.
     *
     * @return the image list
     */
    public List<Image> getImageList() {
        return imageList;
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
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    @Override
    public Block create(int xpos, int ypos) {
        BlockColorOrImage b = new BlockColorOrImage(getH(), getW(), this.hitP,
                getColorList(), getImageList(), getStroke());
        b.setLocation(xpos, ypos);
        return b;
    }
}
