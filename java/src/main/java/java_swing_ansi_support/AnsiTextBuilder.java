package java_swing_ansi_support;

/**
 * The AnsiTextBuilder is builder for building ANSI texts using a
 * <a href="https://java-design-patterns.com/patterns/fluentinterface/">Fluent Interface</a>.
 * <p>
 * Example of how to use the AnsiTextBuilder:
 * <pre>
 *     var ansiTextBuilder = new AnsiTextBuilder()
 *         .bold().red().text("bold").newline();
 *     editorPane.setText(ansiTextBuilder.build());
 * </pre>
 */
public class AnsiTextBuilder {
    private final StringBuilder builder = new StringBuilder();

    /**
     * Builds a text string containing ANSI Escape Codes based on the methods being called on the builder.
     * This method is the last method to call, when building the ANSI text is done.
     *
     * @return a text string containing ANSI Escape Codes.
     */
    public String build() {
        return builder.toString();
    }

    /**
     * Appends plain text to the ANSI string.
     *
     * @param plain a plain text string.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder text(String plain) {
        builder.append(plain);
        return this;
    }

    /**
     * Appends a new-line character to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder newline() {
        builder.append("\n");
        return this;
    }

    /**
     * Appends a tab character to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder tab() {
        return tab(1);
    }

    /**
     * Appends tab characters to the ANSI string.
     *
     * @param count is the number of tab characters to append. Default is 1.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder tab(Integer count) {
        if (count == null)
            count = 1;
        repeat("\t", count);
        return this;
    }

    /**
     * Appends a space character to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder space() {
        return space(1);
    }

    /**
     * Appends space characters to the ANSI string.
     *
     * @param count is the number of space characters to append. Default is 1.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder space(Integer count) {
        if (count == null)
            count = 1;
        repeat(" ", count);
        return this;
    }

    private void repeat(Object fragment, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count must be >= 0");
        builder.append(String.valueOf(fragment).repeat(count));
    }

    /**
     * Appends a {@link AnsiEscCode} to the ANSI string.
     *
     * @param escCode is the {@link AnsiEscCode} to append.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder esc(AnsiEscCode escCode) {
        builder.append(escCode);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#RESET} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder reset() {
        builder.append(AnsiEscCode.RESET);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BOLD} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder bold() {
        return bold(true);
    }

    /**
     * Appends a {@link AnsiEscCode#BOLD} or {@link AnsiEscCode#NOT_BOLD} to the ANSI string.
     *
     * @param enabled {@code true} to append {@link AnsiEscCode#BOLD};
     *                {@code false} to append {@link AnsiEscCode#NOT_BOLD}.
     *                Default is {@code true}.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder bold(boolean enabled) {
        builder.append(enabled ? AnsiEscCode.BOLD : AnsiEscCode.NOT_BOLD);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#FAINT} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder faint() {
        return faint(true);
    }

    /**
     * Appends a {@link AnsiEscCode#FAINT} or {@link AnsiEscCode#NORMAL} to the ANSI string.
     *
     * @param enabled {@code true} to append {@link AnsiEscCode#FAINT};
     *                {@code false} to append {@link AnsiEscCode#NORMAL}.
     *                Default is {@code true}.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder faint(boolean enabled) {
        builder.append(enabled ? AnsiEscCode.FAINT : AnsiEscCode.NORMAL);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#ITALIC} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder italic() {
        return italic(true);
    }

    /**
     * Appends a {@link AnsiEscCode#ITALIC} or {@link AnsiEscCode#NOT_ITALIC} to the ANSI string.
     *
     * @param enabled {@code true} to append {@link AnsiEscCode#ITALIC};
     *                {@code false} to append {@link AnsiEscCode#ITALIC}.
     *                Default is {@code true}.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder italic(boolean enabled) {
        builder.append(enabled ? AnsiEscCode.ITALIC : AnsiEscCode.NOT_ITALIC);
        return this;
    }

    /**
     * Appends an {@link AnsiEscCode#UNDERLINE} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder underline() {
        return underline(true);
    }

    /**
     * Appends a {@link AnsiEscCode#UNDERLINE} or {@link AnsiEscCode#NOT_UNDERLINED} to the ANSI string.
     *
     * @param enabled {@code true} to append {@link AnsiEscCode#UNDERLINE};
     *                {@code false} to append {@link AnsiEscCode#NOT_UNDERLINED}.
     *                Default is {@code true}.
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder underline(boolean enabled) {
        builder.append(enabled ? AnsiEscCode.UNDERLINE : AnsiEscCode.NOT_UNDERLINED);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#NORMAL} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder normal() {
        builder.append(AnsiEscCode.NORMAL);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BLACK} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder black() {
        builder.append(AnsiEscCode.BLACK);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#RED} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder red() {
        builder.append(AnsiEscCode.RED);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#GREEN} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder green() {
        builder.append(AnsiEscCode.GREEN);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#YELLOW} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder yellow() {
        builder.append(AnsiEscCode.YELLOW);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BLUE} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder blue() {
        builder.append(AnsiEscCode.BLUE);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#MAGENTA} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder magenta() {
        builder.append(AnsiEscCode.MAGENTA);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#CYAN} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder cyan() {
        builder.append(AnsiEscCode.CYAN);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#WHITE} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder white() {
        builder.append(AnsiEscCode.WHITE);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#DEFAULT} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder defaultColor() {
        builder.append(AnsiEscCode.DEFAULT);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_BLACK} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightBlack() {
        builder.append(AnsiEscCode.BRIGHT_BLACK);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_RED} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightRed() {
        builder.append(AnsiEscCode.BRIGHT_RED);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_GREEN} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightGreen() {
        builder.append(AnsiEscCode.BRIGHT_GREEN);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_YELLOW} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightYellow() {
        builder.append(AnsiEscCode.BRIGHT_YELLOW);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_BLUE} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightBlue() {
        builder.append(AnsiEscCode.BRIGHT_BLUE);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_MAGENTA} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightMagenta() {
        builder.append(AnsiEscCode.BRIGHT_MAGENTA);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_CYAN} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightCyan() {
        builder.append(AnsiEscCode.BRIGHT_CYAN);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_WHITE} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightWhite() {
        builder.append(AnsiEscCode.BRIGHT_WHITE);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BLACK_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder blackBg() {
        builder.append(AnsiEscCode.BLACK_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#RED_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder redBg() {
        builder.append(AnsiEscCode.RED_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#GREEN_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder greenBg() {
        builder.append(AnsiEscCode.GREEN_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#YELLOW_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder yellowBg() {
        builder.append(AnsiEscCode.YELLOW_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BLUE_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder blueBg() {
        builder.append(AnsiEscCode.BLUE_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#MAGENTA_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder magentaBg() {
        builder.append(AnsiEscCode.MAGENTA_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#CYAN_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder cyanBg() {
        builder.append(AnsiEscCode.CYAN_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#WHITE_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder whiteBg() {
        builder.append(AnsiEscCode.WHITE_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#DEFAULT_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder defaultBg() {
        builder.append(AnsiEscCode.DEFAULT_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_BLACK_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightBlackBg() {
        builder.append(AnsiEscCode.BRIGHT_BLACK_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_RED_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightRedBg() {
        builder.append(AnsiEscCode.BRIGHT_RED_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_GREEN_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightGreenBg() {
        builder.append(AnsiEscCode.BRIGHT_GREEN_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_YELLOW_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightYellowBg() {
        builder.append(AnsiEscCode.BRIGHT_YELLOW_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_BLUE_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightBlueBg() {
        builder.append(AnsiEscCode.BRIGHT_BLUE_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_MAGENTA_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightMagentaBg() {
        builder.append(AnsiEscCode.BRIGHT_MAGENTA_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_CYAN_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightCyanBg() {
        builder.append(AnsiEscCode.BRIGHT_CYAN_BACKGROUND);
        return this;
    }

    /**
     * Appends a {@link AnsiEscCode#BRIGHT_WHITE_BACKGROUND} to the ANSI string.
     *
     * @return an instance of this builder used for chaining methods.
     */
    public AnsiTextBuilder brightWhiteBg() {
        builder.append(AnsiEscCode.BRIGHT_WHITE_BACKGROUND);
        return this;
    }
}
