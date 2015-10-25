package graphics;

public class ColorPictures
{
    private int red;
    private int green;
    private int blue;

    /**
     * Constructs a new Color object.
     * @param red the red value of the color (between 0 and 255)
     * @param green the green value of the color (between 0 and 255)
     * @param blue the blue value of the color (between 0 and 255)
     */
    public ColorPictures(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Gets the red value of this color.
     * @return the red value (between 0 and 255)
     */
    public int getRed()
    {
       return red;
    }

    /**
     * Gets the green value of this color.
     * @return the green value (between 0 and 255)
     */
    public int getGreen()
    {
       return green;
    }

    /**
     * Gets the blue value of this color.
     * @return the blue value (between 0 and 255)
     */
    public int getBlue()
    {
       return blue;
    }

    public String toString()
    {
        return "Color[red=" + red + ",green=" + green + ",blue=" + blue + "]";
    }

    // Color constants

    public static final ColorPictures RED = new ColorPictures(255, 0, 0);
    public static final ColorPictures GREEN = new ColorPictures(0, 255, 0);
    public static final ColorPictures BLUE = new ColorPictures(0, 0, 255);
    public static final ColorPictures WHITE = new ColorPictures(255, 255, 255);
    public static final ColorPictures LIGHT_GRAY = new ColorPictures(192, 192, 192);
    public static final ColorPictures GRAY = new ColorPictures(128, 128, 128);
    public static final ColorPictures DARK_GRAY = new ColorPictures(64, 64, 64);
    public static final ColorPictures BLACK = new ColorPictures(0, 0, 0);
    public static final ColorPictures CYAN = new ColorPictures(0, 255, 255);
    public static final ColorPictures MAGENTA = new ColorPictures(255, 0, 255);
    public static final ColorPictures YELLOW = new ColorPictures(255, 255, 0);
    public static final ColorPictures PINK = new ColorPictures(255, 175, 175);
    public static final ColorPictures ORANGE = new ColorPictures(255, 200, 0);
}
