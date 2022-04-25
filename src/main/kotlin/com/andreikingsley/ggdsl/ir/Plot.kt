package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.ir.aes.Aes
import com.andreikingsley.ggdsl.ir.bindings.Mapping
import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.data.NamedData

data class Plot(
    val dataset: NamedData,
    val layers: List<Layer>,
    val layout: Layout,
   // val globalMappings: Map<Aes, Mapping>, // todo
    val features: Map<FeatureName, PlotFeature> = emptyMap()
)
