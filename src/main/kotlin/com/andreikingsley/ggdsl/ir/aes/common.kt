package com.andreikingsley.ggdsl.ir.aes

import com.andreikingsley.ggdsl.util.symbol.Symbol
import com.andreikingsley.ggdsl.util.color.Color
import com.andreikingsley.ggdsl.util.linetype.LineType

val X = ScalablePositionalAes("x")
val Y = ScalablePositionalAes("y")

val SIZE = MappableNonPositionalAes<Double>("size")
val COLOR = MappableNonPositionalAes<Color>("color")
val ALPHA = MappableNonPositionalAes<Double>("alpha")

val BORDER_WIDTH = NonPositionalAes<Double>("border_size")
//val Mappable_BORDER_WIDTH = MappableNonPositionalAes<Double>("border_size")
val BORDER_COLOR = NonPositionalAes<Color>("border_color")

val WIDTH = NonPositionalAes<Double>("width")

val SYMBOL = MappableNonPositionalAes<Symbol>("symbol")

val LINE_TYPE = NonPositionalAes<LineType>("line_type")
