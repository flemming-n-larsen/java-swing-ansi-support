package java_swing_ansi_support

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JEditorPane
import javax.swing.text.StyledDocument


/**
 * The AnsiEditorPane is a specialized [JEditorPane] which will automatically set up an [AnsiEditorKit] and
 * [StyledDocument] for the pane.
 * It also sets the background color to dark grey to better see the ANSI colors, and enables anti-aliased text painting.
 */
class AnsiEditorPane : JEditorPane() {

    init {
        background = Color.darkGray

        val ansiKit = AnsiEditorKit(fontSize = 14, ansiColors = DefaultAnsiColors)
        editorKit = ansiKit
        document = ansiKit.createDefaultDocument() as StyledDocument
    }

    /** {@inheritDoc} */
    public override fun paintComponent(g: Graphics) {
        // Enable anti-aliased text painting
        val graphics2d = g as Graphics2D
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        super.paintComponent(g)
    }
}