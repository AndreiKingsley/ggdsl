package com.andreikingsley.ggdsl.ir

/**
 * Plot layout settings
 *
 * @param title the title of this plot
 * @param size the size of this plot
 */
data class Layout(
    var title: String? = null,
    // todo width height?
    var size: Pair<Int, Int> ?= null
)
