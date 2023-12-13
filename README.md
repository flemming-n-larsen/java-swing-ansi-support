# ANSI Support classes for Java Swing

This repository provides basic ANSI support for the [Java Swing] GUI Framework and supports basic [ANSI escape code]
styles and colors, but _not_ all escape codes for various reasons.

Classes and interfaces are provided for both Java and Kotlin.

Here is a demo of how a [JEditorPane] can be styled by using an `AnsiEditorKit`, which is a [EditorKit] used to created
styled documents based on [ANSI escape code].

![Screen shot of the AnsiDemoFrame](/gfx/AnsiDemoFrame.png)

[Java Swing]: https://docs.oracle.com/javase/tutorial/uiswing/ "Oracle docs: The Swing Tutorial"

[ANSI escape code]: https://en.wikipedia.org/wiki/ANSI_escape_code "WikiPedia: ANSI escape code"

[JEditorPane]: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JEditorPane.html "Oracle docs: JEditorPane"

[EditorKit]: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/text/EditorKit.html "Oracle docs: EditorKit documentation"
