package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.ir.aes.Aes
import com.andreikingsley.ggdsl.ir.bindings.Mapping
import com.andreikingsley.ggdsl.ir.bindings.Setting
import com.andreikingsley.ggdsl.ir.scale.Scale

class Layer(
    //val data: NamedData?,
    var geom: Geom,
    val mappings: Map<Aes, Mapping<*>>,
    val settings: Map<Aes, Setting>,
    //val scales: Map<Aes, Scale>,
    val features: Map<FeatureName, LayerFeature> = emptyMap(), // todo list???
)
