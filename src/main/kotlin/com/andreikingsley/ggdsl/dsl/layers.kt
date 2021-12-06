package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Geom

fun PlotContext.points(block: LayerContext.() -> Unit) {
    // TODO dataset
    layers.add(LayerContext().apply { copyFrom(this@points) }.apply(block).toLayer(Geom.POINT))
}

fun PlotContext.bars(block: LayerContext.() -> Unit) {
    // TODO dataset
    layers.add(LayerContext().apply { copyFrom(this@bars) }.apply(block).toLayer(Geom.BAR))
}
fun PlotContext.line(block: LayerContext.() -> Unit) {
    // TODO dataset
    layers.add(LayerContext().apply { copyFrom(this@line) }.apply(block).toLayer(Geom.LINE))
}
