package com.andreikingsley.ggdsl.ir.util

class Symbol(val name: String) {
    companion object {
        val CIRCLE = Symbol("circle")
        val RECTANGLE = Symbol("rect")
        val TRIANGLE = Symbol("triangle")
    }
}
