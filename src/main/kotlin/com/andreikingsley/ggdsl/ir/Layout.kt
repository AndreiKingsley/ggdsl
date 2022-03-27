package com.andreikingsley.ggdsl.ir
//todo
class Layout {
    // todo
    fun copyFrom(layout: Layout) {
        title = layout.title
        size = layout.size
    }

    var title: String? = null
    // todo width height?
    var size: Pair<Int, Int> = 800 to 600
}
