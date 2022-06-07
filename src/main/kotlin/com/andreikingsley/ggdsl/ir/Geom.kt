package com.andreikingsley.ggdsl.ir

// todo interface and move to geom package
/**
 *  Geometrical entity that characterizes the [Layer].
 *
 *  @param name the name of entity
 */
data class Geom(val name: String){
    companion object {
        val POINT = Geom("point")
        val BAR = Geom("bar")
        val LINE = Geom("line")
    }
}
