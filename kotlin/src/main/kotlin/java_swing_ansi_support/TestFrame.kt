package java_swing_ansi_support

import javax.swing.JFrame
import javax.swing.JScrollPane

object AnsiTestFrame : JFrame() {
    private fun readResolve(): Any = AnsiTestFrame // to avoid warning

    val editorPane = AnsiEditorPane().apply {
        isEditable = false
    }

    init {
        setBounds(100, 100, 1000, 800)

        val scrollPane = JScrollPane(editorPane)

        contentPane.add(scrollPane)
    }
}

fun main() {

    val ansi = AnsiTextBuilder()
        .bold().text("bold").newline().bold(false)
        .faint().text("faint").newline().faint(false)
        .italic().text("italic").newline().italic(false)
        .underline().text("underline").newline().underline(false)
        .normal().text("normal style").newline()

        .black().text("black foreground").newline()
        .red().text("red foreground").newline()
        .green().text("green foreground").newline()
        .yellow().text("yellow foreground").newline()
        .blue().text("blue foreground").newline()
        .magenta().text("magenta foreground").newline()
        .cyan().text("cyan foreground").newline()
        .white().text("white foreground").newline()

        .brightBlack().text("bright black foreground").newline()
        .brightRed().text("bright red foreground").newline()
        .brightGreen().text("bright green foreground").newline()
        .brightYellow().text("bright yellow foreground").newline()
        .brightBlue().text("bright blue foreground").newline()
        .brightMagenta().text("bright magenta foreground").newline()
        .brightCyan().text("bright cyan foreground").newline()
        .brightWhite().text("bright white foreground").newline()

        .default().text("default foreground color").newline()

        .blackBg().text("black background").newline()
        .redBg().text("red background").newline()
        .greenBg().text("green background").newline()
        .yellowBg().text("yellow background").newline()
        .blueBg().text("blue background").newline()
        .magentaBg().text("magenta background").newline()
        .cyanBg().text("cyan background").newline()
        .black()
        .whiteBg().text("white background").newline()

        .brightBlackBg().text("bright black background").newline()
        .brightRedBg().text("bright red background").newline()
        .brightGreenBg().text("bright green background").newline()
        .brightYellowBg().text("bright yellow background").newline()
        .brightBlueBg().text("bright blue background").newline()
        .brightMagentaBg().text("bright magenta background").newline()
        .brightCyanBg().text("bright cyan background").newline()
        .brightWhiteBg().text("bright white background").newline()

        .defaultBg().text("default background color").newline()


    AnsiTestFrame.apply {
        editorPane.text = ansi.build()
        isVisible = true
    }
}