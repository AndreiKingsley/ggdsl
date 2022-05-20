package com.andreikingsley.ggdsl.util.linetype

import javax.sound.sampled.Line

interface LineType {
    companion object {
        val SOLID = CommonLineType("solid")
        val DASHED = CommonLineType("dashed")
        val DOTTED = CommonLineType("dotted")
    }
}

data class CommonLineType(val description: String): LineType