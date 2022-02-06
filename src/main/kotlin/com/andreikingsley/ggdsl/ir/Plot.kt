package com.andreikingsley.ggdsl.ir

data class Plot(
    val dataset: NamedData?,
    val layers: MutableList<Layer>,
    val layout: Layout,
    val features: Map<FeatureName, PlotFeature> = emptyMap()
)
