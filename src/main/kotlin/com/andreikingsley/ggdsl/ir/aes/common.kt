package com.andreikingsley.ggdsl.ir.aes

import com.andreikingsley.ggdsl.ir.util.Symbol

val X = PositionalAes("x")
val Y = PositionalAes("y")

val SIZE = MappableNonPositionalAes<Double>("size")
val COLOR = MappableNonPositionalAes<String>("color")
val ALPHA = MappableNonPositionalAes<Double>("alpha")

val BORDER_WIDTH = NonPositionalAes<Double>("border_size")
val BORDER_COLOR = NonPositionalAes<String>("border_color")

val WIDTH = NonPositionalAes<Double>("width")

// TODO
//val SYMBOL = MappableNonPositionalAes<Symbol>("color")
val SYMBOL = MappableNonPositionalAes<String>("symbol")
