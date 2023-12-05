package java_swing_ansi_support

import java.awt.Color

interface IAnsiColors {
    val black: Color
    val red: Color
    val green: Color
    val yellow: Color
    val blue: Color
    val magenta: Color
    val cyan: Color
    val white: Color

    val brightBlack: Color
    val brightRed: Color
    val brightGreen: Color
    val brightYellow: Color
    val brightBlue: Color
    val brightMagenta: Color
    val brightCyan: Color
    val brightWhite: Color

    val default: Color
}