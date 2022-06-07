package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.feature.FeatureName
import com.andreikingsley.ggdsl.ir.feature.PlotFeature

/**
 * Plot consists of a set of layers.
 *
 * @param layers the [List] of [Layer]
 * @param layout layout settings
 * @param features the [Map] of the plot features; keys are feature names,
 * values are features with corresponding names.
 */
data class Plot(
    val dataset: NamedData, // todo remove???
    val layers: List<Layer>,
    val layout: Layout,
    val features: Map<FeatureName, PlotFeature> = emptyMap()
)
