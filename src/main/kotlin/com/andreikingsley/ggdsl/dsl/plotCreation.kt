package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Plot
import com.andreikingsley.ggdsl.ir.data.NamedData

/**
 * Creates a new [Plot] from this [PlotContext]
 *
 * @return new [Plot]
 */
fun PlotContext.toPlot(): Plot {
    return Plot(data, layers, layout, features)
}

/**
 * Returns a new [Plot].
 *
 * TODO PlotContext desc
 *
 * @param dataset initial plot dataset
 * @return new [Plot]
 */
inline fun plot(dataset: NamedData = mapOf(), block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply{
        data = dataset.toMutableMap()
        block()
    }.toPlot()
}

