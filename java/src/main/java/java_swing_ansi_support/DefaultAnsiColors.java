package java_swing_ansi_support;

import java.awt.Color;

/**
 * Default ANSI colors.
 */
public final class DefaultAnsiColors implements IAnsiColors {
    public final static Color black = new Color(0x00, 0x00, 0x00);
    public final static Color red = new Color(0xaa, 0x00, 0x00);
    public final static Color green = new Color(0x00, 0xaa, 0x00);
    public final static Color yellow = new Color(0xaa, 0xaa, 0x00);
    public final static Color blue = new Color(0x00, 0x00, 0xaa);
    public final static Color magenta = new Color(0xaa, 0x00, 0xaa);
    public final static Color cyan = new Color(0x00, 0xaa, 0xaa);
    public final static Color white = new Color(0xaa, 0xaa, 0xaa);

    public final static Color brightBlack = new Color(0x55, 0x55, 0x55);
    public final static Color brightRed = new Color(0xff, 0x55, 0x55);
    public final static Color brightGreen = new Color(0x55, 0xff, 0x55);
    public final static Color brightYellow = new Color(0xff, 0xff, 0x55);
    public final static Color brightBlue = new Color(0x55, 0x55, 0xff);
    public final static Color brightMagenta = new Color(0xff, 0x55, 0xff);
    public final static Color brightCyan = new Color(0x55, 0xff, 0xff);
    public final static Color brightWhite = new Color(0xff, 0xff, 0xff);

    public final static Color defaultColor = brightWhite;
}
