package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.ir.aes.Aes
import com.andreikingsley.ggdsl.ir.scale.Scale

class Layer(
    val data: NamedData?,
    var geom: Geom,
    val mappings: Map<Aes, String>,
    val settings: Map<Aes, Any>,
    val scales: Map<Aes, Scale>,
    val features: Map<FeatureName, LayerFeature> = emptyMap(),
)
