package java_swing_ansi_support;

import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.util.regex.Pattern;

/**
 * The AnsiEditorKit is a specialized {@link StyledEditorKit} that is able to created {@link StyledDocument}s based on
 * text containing ANSI escape codes for styling the text.
 * The documents are created with the Monospaced font to simulate an old-fashioned text console for displaying ANSI
 * graphics.
 */
public class AnsiEditorKit extends StyledEditorKit {

    private final int fontSize;
    private final IAnsiColors ansiColors;

    private final Pattern ansiEscCodePattern = Pattern.compile("\u001b\\[(\\d+;?)+m");

    /**
     * Creates a AnsiEditorKit using a monospaced font size of 14, and the {@link DefaultAnsiColors} as ANSI colors.
     */
    public AnsiEditorKit() {
        this(14, new DefaultAnsiColors());
    }

    /**
     * Creates a AnsiEditorKit using a monospaced font size set as input parameter, and the {@link DefaultAnsiColors} as ANSI colors.
     *
     * @param fontSize is the monospaced font size to use across an entire document. Default is 14.
     */
    public AnsiEditorKit(int fontSize) {
        this(fontSize, new DefaultAnsiColors());
    }

    /**
     * Creates a AnsiEditorKit using a monospaced font size of 14, and the ANSI colors set as input parameter.
     *
     * @param ansiColors is the {@link IAnsiColors} to use for the ANSI Colors. Default is the {@link DefaultAnsiColors}.
     */
    public AnsiEditorKit(IAnsiColors ansiColors) {
        this(14, ansiColors);
    }

    /**
     * Creates a AnsiEditorKit using a monospaced font size set as input parameter, and the ANSI colors set as input parameter.
     *
     * @param fontSize   is the monospaced font size to use across an entire document. Default is 14.
     * @param ansiColors is the {@link IAnsiColors} to use for the ANSI Colors. Default is the {@link DefaultAnsiColors}.
     */
    public AnsiEditorKit(int fontSize, IAnsiColors ansiColors) {
        this.fontSize = fontSize;
        this.ansiColors = ansiColors;
    }

    @Override
    public String getContentType() {
        return "text/x-ansi";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(InputStream inputStream, Document doc, int pos) throws IOException, BadLocationException {
        read(new BufferedReader(new InputStreamReader(inputStream)), doc, pos);
    }

    /**
     * {@inheritDoc}
     */
    public void read(Reader reader, Document doc, int pos) throws IOException, BadLocationException {
        if (!(doc instanceof StyledDocument))
            throw new IllegalArgumentException("The document must be a StyledDocument for this kit");

        String text = readText(reader);
        insertAnsi((StyledDocument)doc, text, pos);
    }

    /**
     * {@inheritDoc}
     */
    public void write(OutputStream outputStream, Document doc, int pos, int len) throws IOException, BadLocationException {
        write(new BufferedWriter(new OutputStreamWriter(outputStream)), doc, pos, len);
    }

    /**
     * {@inheritDoc}
     */
    public void write(Writer writer, Document doc, int pos, int len) throws BadLocationException, IOException {
        writer.write(doc.getText(pos, len));
    }

    /**
     * Inserts an ANSI text into the tail of the document.
     * ANSI escape codes are converted into {@link AttributeSet}s to style the inserted text.
     *
     * @param doc      is a {@link StyledDocument} the ANSI text is inserted into.
     * @param ansiText is the ANSI text to insert into the document.
     */
    public void insertAnsi(StyledDocument doc, String ansiText) throws BadLocationException {
        insertAnsi(doc, ansiText, doc.getLength());
    }

    /**
     * Inserts an ANSI text into a specific text position of the document.
     * ANSI escape codes are converted into {@link AttributeSet}s to style the inserted text.
     *
     * @param doc      is a {@link StyledDocument} the ANSI text is inserted into.
     * @param ansiText is the ANSI text to insert into the document.
     * @param offset   is the offset into the document where the text will be inserted.
     */
    public void insertAnsi(StyledDocument doc, String ansiText, int offset) throws BadLocationException {
        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be negative. Was: " + offset);

        MutableAttributeSet attributes = new SimpleAttributeSet(doc.getCharacterElement(offset).getAttributes());
        StyleConstants.setFontFamily(attributes, "Monospaced");
        StyleConstants.setFontSize(attributes, fontSize);

        // Set the foreground color to the default ANSI color if no foreground color has been set previously
        if (StyleConstants.getForeground(attributes) == Color.black) { // if no foreground color is set, black is returned?!
            attributes = AnsiAttributesUtil.updateAnsi(attributes, AnsiEscCode.DEFAULT, ansiColors);
        }

        var matcher = ansiEscCodePattern.matcher(ansiText);
        if (!matcher.find()) {
            doc.insertString(offset, ansiText, attributes); // no ansi codes found
            return;
        }

        var text = ansiText.substring(0, matcher.start());
        if (!text.isBlank()) {
            doc.insertString(0, text, attributes); // no ansi codes found
        }

        int codeEnd;
        do {
            int codeStart = matcher.start();
            codeEnd = matcher.end();
            var ansiCode = ansiText.substring(codeStart, codeEnd);

            attributes = AnsiAttributesUtil.updateAnsi(attributes, AnsiEscCode.fromEscCode(ansiCode), ansiColors);

            var endMatcher = ansiEscCodePattern.matcher(ansiText);
            if (endMatcher.find(codeEnd)) {
                text = ansiText.substring(codeEnd, endMatcher.start());
            } else {
                text = ansiText.substring(codeEnd);
            }
            if (!text.isBlank()) {
                doc.insertString(doc.getLength(), text, attributes);
            }
        }
        while (matcher.find(codeEnd));
    }

    private static String readText(Reader reader) throws IOException {
        try (reader) {
            char[] arr = new char[8 * 1024];
            var buffer = new StringBuilder();
            int numCharsRead;
            while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
                buffer.append(arr, 0, numCharsRead);
            }
            return buffer.toString();
        }
    }
}
