package java_swing_ansi_support;

import javax.swing.*;
import javax.swing.text.EditorKit;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * The AnsiEditorPane is a specialized {@link JEditorPane} which will automatically set up an {@link AnsiEditorKit} and
 * {@link StyledDocument} for the pane.
 * It also sets the background color to dark grey to better see the ANSI colors, and enables anti-aliased text painting.
 */
public class AnsiEditorPane extends JEditorPane {

    public AnsiEditorPane() {
        setBackground(Color.darkGray);

        EditorKit ansiKit = new AnsiEditorKit();
        setEditorKit(ansiKit);
        setDocument(ansiKit.createDefaultDocument());
    }

    /**
     * {@inheritDoc}
     */
    public void paintComponent(Graphics g) {
        // Enable anti-aliased text painting
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
    }
}
