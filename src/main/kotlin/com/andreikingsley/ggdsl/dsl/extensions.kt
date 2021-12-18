package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.scale.guide.*

fun PlotContext.toPlot(): Plot {
    return Plot(dataset, layers)
}

fun plot(block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply(block).toPlot()
}

fun LayerContext.toLayer(geom: Geom): Layer {
    return Layer(dataset, geom, mappings.map { it.key to it.value.id }.toMap(), settings, scales)
}

/*
operator fun Axis.invoke(block: Axis.() -> Unit) {
    this.apply(block)
}

operator fun Guide.invoke(block: Guide.() -> Unit) {
    this.apply(block)
}

 */
