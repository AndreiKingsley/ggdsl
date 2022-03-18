package com.andreikingsley.ggdsl.util

//TODO
class Color internal constructor(val description: String) {
    companion object {
        fun fromHex(hexString: String) = Color(hexString)
        fun fromName(name: String) = Color(name)
        fun fromRGB(r: Double, g: Double, b: Double) = Color("todo")
    }
}
