package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Geom

fun PlotContext.points(block: PointsContext.() -> Unit) {
    featureCollectorAccessor.layers.add(PointsContext(data).apply { copyFrom(this@points) }.apply(block).toLayer(Geom.POINT))
}

fun PlotContext.bars(block: BarsContext.() -> Unit) {
    featureCollectorAccessor.layers.add(BarsContext(data).apply { copyFrom(this@bars) }.apply(block).toLayer(Geom.BAR))
}

fun PlotContext.line(block: LineContext.() -> Unit) {
    featureCollectorAccessor.layers.add(LineContext(data).apply { copyFrom(this@line) }.apply(block).toLayer(Geom.LINE))
}
