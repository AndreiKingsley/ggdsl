package com.andreikingsley.ggdsl.util

interface Color {
    companion object {
        fun fromHex(hexString: String) = StandardColor(hexString)
        fun fromName(name: String) = StandardColor(name)
        fun fromRGB(r: Double, g: Double, b: Double) = StandardColor("todo")

        val RED = StandardColor("red")
        val BLUE = StandardColor("blue")
        val GREEN = StandardColor("green")
        val BLACK = StandardColor("black")
    }
}
//TODO
class StandardColor internal constructor(val description: String) : Color
