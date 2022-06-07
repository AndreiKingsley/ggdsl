package com.andreikingsley.ggdsl.util.linetype

/**
 * Line type base interface.
 */
interface LineType {
    companion object {
        val SOLID = CommonLineType("solid")
        val DASHED = CommonLineType("dashed")
        val DOTTED = CommonLineType("dotted")
    }
}

/**
 * Line type described by one string.
 *
 * @param description the string describing this line type.
 */
data class CommonLineType(val description: String): LineType