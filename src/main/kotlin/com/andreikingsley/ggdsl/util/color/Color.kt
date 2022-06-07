package com.andreikingsley.ggdsl.util.color

/**
 * Color base interface.
 */
interface Color {
    companion object {
        /**
         * Creates a new [StandardColor] from hex value of color.
         *
         * @param hexString the color in hexadecimal format
         */
        fun fromHex(hexString: String) = StandardColor(hexString)
        /**
         * Creates a new [StandardColor] from color name.
         *
         * @param name the name of the color //todo enginges???
         */
        fun fromName(name: String) = StandardColor(name)

        /**
         * Creates a new [StandardColor] from rgb value of color.
         *
         * @param r the red component of the color
         * @param g the green component of the color
         * @param b the blue component of the color
         */
        fun fromRGB(r: Int, g: Int, b: Int) = StandardColor(
            String.format("#%02X%02X%02X", r, g, b)
        )

        // todo move to hex???

        val RED = StandardColor("red")
        val BLUE = StandardColor("blue")
        val GREEN = StandardColor("green")

        val BLACK = StandardColor("black")
        val WHITE = StandardColor("white")
        val GREY = StandardColor("grey")

        val YELLOW = StandardColor("yellow")
        val ORANGE = StandardColor("orange")
        /*
        //val PURPLE = StandardColor("purple")

        // todo not work in echarts
        val DARK_RED = StandardColor("dark_red")
        val DARK_BLUE = StandardColor("dark_blue")
        val DARK_GREEN = StandardColor("dark_green")

         */

        val PEACH = StandardColor("#ffe5b4")
    }
}

//TODO
/**
 * Color described by one string.
 *
 * @param description the string describing this color.
 */
data class StandardColor internal constructor(val description: String) : Color
