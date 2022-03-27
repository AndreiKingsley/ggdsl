package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.scale.guide.*

fun PlotContext.toPlot(): Plot {
    return Plot(dataset, layers, layout, collectorAccessor.mappings, features)
}

fun plot(dataset: NamedData, block: PlotContext.() -> Unit): Plot {
    return PlotContext(dataset).apply(block).toPlot()
}

fun LayerContext.toLayer(geom: Geom): Layer {
    return Layer(
        geom,
        collectorAccessor.mappings.map { it.key to it.value.id }.toMap(),
        collectorAccessor.settings,
        collectorAccessor.scales,
        features
    )
}


operator fun <RangeType> Axis<RangeType>.invoke(block: Axis<RangeType>.() -> Unit) {
    this.apply(block)
}

operator fun Layout.invoke(block: Layout.() -> Unit) {
    this.apply(block)
}

/*
operator fun Guide.invoke(block: Guide.() -> Unit) {
    this.apply(block)
}

 */
