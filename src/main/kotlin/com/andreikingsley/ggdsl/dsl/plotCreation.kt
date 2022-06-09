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
 * Returns a new plot.
 *
 * Creates a [PlotContext]. In this context, functions for creating new layers are defined
 * (there are 3 in common API: [points], [bars] and [line]).
 *
 * Also [x][PlotContext.x] and [y][PlotContext.y] aesthetic attributes are defined in a [PlotContext].
 * You can create global mappings on them, which will be inherited in following layers in this context.
 *
 * In a [PlotContext], the dataset can be overridden with an assignment to the [PlotContext.data]
 * (for example, to use one dataset for some layers and another for others).
 *
 *```
 * plot {
 *   data = datasetA
 *   x(srcX1)
 *   y(srcY1)
 *   points {
 *      x(srcX2)
 *   }
 *   points {
 *      data = datasetB
 *   }
 *   data = datasetC
 *   y(srcY2)
 *   bars { }
 *   line { }
 * }
 * ```
 *
 * @param dataset the initial plot dataset
 * @return new [Plot]
 * @see [BaseBindingContext]
 */
inline fun plot(dataset: NamedData = mapOf(), block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply{
        data = dataset.toMutableMap()
        block()
    }.toPlot()
}
