package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Geom

/**
 * Creates new points layer in this [PlotContext]
 * TODO
 */
inline fun PlotContext.points(block: PointsContext.() -> Unit) {
    layers.add(PointsContext(data).apply { copyFrom(this@points) }.apply(block).toLayer(Geom.POINT))
}

/**
 * Creates new bars layer in this [PlotContext]
 * TODO
 */
inline fun PlotContext.bars(block: BarsContext.() -> Unit) {
    layers.add(BarsContext(data).apply { copyFrom(this@bars) }.apply(block).toLayer(Geom.BAR))
}

/**
 * Creates new line layer in this [PlotContext]
 * TODO
 */
inline fun PlotContext.line(block: LineContext.() -> Unit) {
    layers.add(LineContext(data).apply { copyFrom(this@line) }.apply(block).toLayer(Geom.LINE))
}
