package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.dsl.DataSource
import com.andreikingsley.ggdsl.ir.aes.Aes

data class Plot(
    val dataset: NamedData,
    val layers: List<Layer>,
    val layout: Layout,
    val globalMappings: Map<Aes, DataSource<Any>>, // todo?
    val features: Map<FeatureName, PlotFeature> = emptyMap()
)
