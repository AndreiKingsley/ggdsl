package com.andreikingsley.ggdsl.util.symbol

interface Symbol {
    val name: String

    companion object {
        val CIRCLE = CommonSymbol("circle")
        val RECTANGLE = CommonSymbol("rect")
        val TRIANGLE = CommonSymbol("triangle")
    }
}
