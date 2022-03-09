package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Plot

/*
    New plot = old plot + new layers & features.
 */
fun Plot.add(block: PlotContext.() -> Unit): Plot{
    val newParameters = PlotContext().apply(block)
    return Plot(
        dataset, // Todo mutable??
        layers.toMutableList().apply {
            addAll(newParameters.layers)
        },
        layout,
        features.toMutableMap().apply {
            putAll(newParameters.features)
        },
    )
}
