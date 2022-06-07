package com.andreikingsley.ggdsl.ir

import com.andreikingsley.ggdsl.ir.aes.Aes
import com.andreikingsley.ggdsl.ir.bindings.Mapping
import com.andreikingsley.ggdsl.ir.bindings.Setting
import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.feature.FeatureName
import com.andreikingsley.ggdsl.ir.feature.LayerFeature

/**
 * Layer is a collection of data and mappings from it.
 * It is characterized by its [Geom].
 *
 * @param data the dataset of this layer.
 * @param geom the [Geom] that describes this layer.
 * @param mappings the [Map] of the mappings; keys are aesthetic attributes,
 * values are mappings on corresponding attributes.
 * @param settings the [Map] of the setting; keys are aesthetic attributes,
 * values are setting of corresponding attributes.
 * @param features the [Map] of the layer features; keys are feature names,
 * values are features with corresponding names.
 */
data class Layer(
    val data: NamedData? = null,
    val geom: Geom,
    val mappings: Map<Aes, Mapping>,
    val settings: Map<Aes, Setting>,
    val features: Map<FeatureName, LayerFeature> = emptyMap()
)
