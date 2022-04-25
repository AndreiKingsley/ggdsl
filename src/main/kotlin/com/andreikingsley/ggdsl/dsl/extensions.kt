package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.data.NamedData

fun PlotContext.toPlot(): Plot {
    return Plot(data, layers, layout, /*collectorAccessor.mappings,*/ features)
}

//todo remove>
fun plot(dataset: NamedData, block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply{
        data = dataset.toMutableMap()
        apply(block)
    }.toPlot()
}

fun plot(block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply(block).toPlot()
}

fun LayerContext.toLayer(geom: Geom): Layer {
    return Layer(
        data,
        geom,
        collectorAccessor.mappings,
        collectorAccessor.settings,
        //collectorAccessor.scales,
        features
    )
}

/*
operator fun <RangeType> Axis<RangeType>.invoke(block: Axis<RangeType>.() -> Unit) {
    this.apply(block)
}

 */

operator fun Layout.invoke(block: Layout.() -> Unit) {
    this.apply(block)
}

/*
operator fun Guide.invoke(block: Guide.() -> Unit) {
    this.apply(block)
}

 */
