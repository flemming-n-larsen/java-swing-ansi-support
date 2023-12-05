package java_swing_ansi_support

import java_swing_ansi_support.AnsiAttributesExt.updateAnsi
import java.awt.Color
import java.io.*
import javax.swing.text.*

class AnsiEditorKit(
    private val fontSize: Int = 14,
    private val ansiColors: IAnsiColors = DefaultAnsiColors
) : StyledEditorKit() {

    private val ansiEscCodeRegex = Regex("\u001b\\[(\\d+;?)+m")

    override fun getContentType() = "text/x-ansi"

    override fun read(inputStream: InputStream, doc: Document, pos: Int) {
        read(BufferedReader(InputStreamReader(inputStream)), doc, pos)
    }

    override fun read(reader: Reader, doc: Document, pos: Int) {
        require(doc is StyledDocument) { "The document must be a StyledDocument for this kit" }
        insertAnsi(doc, reader.readText(), pos)
    }

    override fun write(outputStream: OutputStream, doc: Document, pos: Int, len: Int) {
        write(BufferedWriter(OutputStreamWriter(outputStream)), doc, pos, len)
    }

    override fun write(writer: Writer, doc: Document, pos: Int, len: Int) {
        writer.write(doc.getText(pos, len))
    }

    fun insertAnsi(doc: StyledDocument, ansiText: String, offset: Int = doc.length) {
        require(offset >= 0) { "Offset cannot be negative. Was: $offset" }

        var attributes: MutableAttributeSet = SimpleAttributeSet(doc.getCharacterElement(offset).attributes)
        StyleConstants.setFontFamily(attributes, "Monospaced")
        StyleConstants.setFontSize(attributes, fontSize)

        // Set the foreground color to the default ANSI color if no foreground color has been set previously
        if (StyleConstants.getForeground(attributes) == Color.black) { // if no foreground color is set, black is returned?!
            attributes = attributes.updateAnsi(AnsiEscCode.DEFAULT, ansiColors)
        }

        val match = ansiEscCodeRegex.find(ansiText, 0)
        if (match == null) {
            doc.insertString(offset, ansiText, attributes) // no ansi codes found
            return
        }

        var codeStart = match.range.first

        var text = ansiText.substring(0, codeStart)
        if (text.isNotEmpty()) {
            doc.insertString(0, text, attributes) // no ansi codes found
        }

        ansiEscCodeRegex.findAll(ansiText, codeStart).forEach { m ->
            val ansiCode = m.value
            codeStart = m.range.first
            val codeEnd = m.range.last + 1

            attributes = attributes.updateAnsi(AnsiEscCode.fromCode(ansiCode), ansiColors)

            val endMatch = ansiEscCodeRegex.find(ansiText, codeEnd)

            text = if (endMatch == null) {
                ansiText.substring(codeEnd)
            } else {
                ansiText.substring(codeEnd, endMatch.range.first)
            }
            if (text.isNotEmpty()) {
                doc.insertString(doc.length, text, attributes)
            }
        }
    }
}