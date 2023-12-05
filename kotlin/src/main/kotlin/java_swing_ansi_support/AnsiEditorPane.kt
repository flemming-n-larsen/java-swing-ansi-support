package java_swing_ansi_support

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JEditorPane
import javax.swing.text.StyledDocument


class AnsiEditorPane : JEditorPane() {

    init {
        background = Color.darkGray

        val ansiKit = AnsiEditorKit(fontSize = 14, ansiColors = DefaultAnsiColors)
        val ansiDoc = ansiKit.createDefaultDocument() as StyledDocument

        editorKit = ansiKit
        document = ansiDoc
    }

    // Enable anti-aliased text painting
    public override fun paintComponent(g: Graphics) {
        val graphics2d = g as Graphics2D
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        super.paintComponent(g)
    }
}