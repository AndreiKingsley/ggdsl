package com.andreikingsley.ggdsl.ir.aes

val X = PositionalAes("x")
val Y = PositionalAes("y")

val SIZE = NonPositionalAes<Double>("size")
val COLOR = NonPositionalAes<String>("color")
val ALPHA = NonPositionalAes<Double>("alpha")

//TODO
//val BORDER_WIDTH = NonPositionalAes<Double>("border_size")
//val BORDER_COLOR = NonPositionalAes<String>("border_color")

val WIDTH = NonPositionalAes<Double>("width")

// TODO GROUP
