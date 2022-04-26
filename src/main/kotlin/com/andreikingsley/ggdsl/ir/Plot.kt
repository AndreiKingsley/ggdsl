package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.feature.FeatureName
import com.andreikingsley.ggdsl.ir.feature.PlotFeature

data class Plot(
    val dataset: NamedData,
    val layers: List<Layer>,
    val layout: Layout,
   // val globalMappings: Map<Aes, Mapping>, // todo
    val features: Map<FeatureName, PlotFeature> = emptyMap()
)
