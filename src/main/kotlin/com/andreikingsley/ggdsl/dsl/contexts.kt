package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.scale.*
import com.andreikingsley.ggdsl.ir.scale.guide.Axis
import com.andreikingsley.ggdsl.ir.scale.guide.Legend
import kotlin.reflect.KType
import kotlin.reflect.typeOf

// TODO internal

class ContextCollector {
    val mappings: MutableMap<Aes, Mapping> = mutableMapOf()
    val settings: MutableMap<Aes, Setting> = mutableMapOf()
    //val scales: MutableMap<Aes, Scale> = mutableMapOf()

    fun copyFrom(other: ContextCollector) {
        mappings.putAll(other.mappings)
        settings.putAll(other.settings)
        //scales.putAll(other.scales)
    }
}

class PositionalScaleContext<DomainType : Any>(domainType: KType) {
    var scale: PositionalScale<DomainType> = DefaultPositionalScale(domainType)
    var axis: Axis? = null
}

operator fun<DomainType : Any> ScalablePositionalMapping<DomainType>.invoke(
    block: PositionalScaleContext<DomainType>.() -> Unit
){
    scale = PositionalScaleContext<DomainType>(domainType).apply(block).let {
        scale.apply {
            this.axis = it.axis
        }
    }
}

operator fun<DomainType : Any, RangeType: Any> NonPositionalMapping<DomainType, RangeType>.invoke(
    block: NonPositionalScaleContext<DomainType, RangeType>.() -> Unit
){
    scale = NonPositionalScaleContext<DomainType, RangeType>(domainType, rangeType).apply(block).let {
        scale.apply {
            this.legend = it.legend
        }
    }
}

class NonPositionalScaleContext<DomainType : Any, RangeType : Any>(domainType: KType, rangeType: KType) {
    var scale: NonPositionalScale<DomainType, RangeType> =
        DefaultNonPositionalScale(domainType, rangeType)
    var legend: Legend? = null
}


inline infix fun <reified DomainType : Any>
        PositionalScaleContext<DomainType>.scaleContinuous(
    block: (ContinuousPositionalScale<DomainType>.() -> Unit)
) {
    scale = ContinuousPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
}

inline infix fun <reified DomainType : Any, reified RangeType : Any>
        NonPositionalScaleContext<DomainType, RangeType>.scaleContinuous(
    block: (ContinuousNonPositionalScale<DomainType, RangeType>.() -> Unit)
) {
    scale = ContinuousNonPositionalScale<DomainType, RangeType>(
            typeOf<DomainType>(), typeOf<RangeType>()
    ).apply(block)
}

inline infix fun <reified DomainType : Any> PositionalScaleContext<DomainType>.scaleCategorical(
    block: (CategoricalPositionalScale<DomainType>.() -> Unit)
) {
    scale = CategoricalPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
}

inline infix fun <reified DomainType : Any, reified RangeType : Any>
        NonPositionalScaleContext<DomainType, RangeType>.scaleCategorical(
    block: (CategoricalNonPositionalScale<DomainType, RangeType>.() -> Unit)
) {
    scale = CategoricalNonPositionalScale<DomainType, RangeType>(
            typeOf<DomainType>(), typeOf<RangeType>()
    ).apply(block)
}


abstract class BaseContext {
    // open var dataset: NamedData = mapOf()

    protected val collector = ContextCollector()

    @PublishedApi
    internal val collectorAccessor: ContextCollector
        get() = collector

    fun copyFrom(other: BaseContext) {
        collector.copyFrom(other.collector)
    }

    inline infix fun <reified DomainType : Any> NonScalablePositionalAes.mapTo(
        dataSource: DataSource<DomainType>,
    ) : NonScalablePositionalMapping<DomainType> {
        return NonScalablePositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>(),
        ).also {
            collectorAccessor.mappings[this] = it
        }
    }

    inline infix fun <reified DomainType : Any> ScalablePositionalAes.mapTo(
        dataSource: DataSource<DomainType>,
    ) : ScalablePositionalMapping<DomainType> {
        return ScalablePositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>(),
            DefaultPositionalScale(typeOf<DomainType>())
        ).also {
            collectorAccessor.mappings[this] = it
        }
    }
    /*
    inline infix fun <reified DomainType : Any>
            PositionalAes.mapTo(collection: Collection<DomainType>):
            PositionalMapping<DomainType> {
        val name = collection::class.qualifiedName!!
        val dataSource = DataSource<DomainType>(name)
        val array: Array<Any> = collection.toTypedArray()
        dataset += name to array
        return mapTo(dataSource)
    }

     */

    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            MappableNonPositionalAes<RangeType>.mapTo(dataSource: DataSource<DomainType>):
            NonPositionalMapping<DomainType, RangeType> {
        return NonPositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>(),
            typeOf<RangeType>(),
            DefaultNonPositionalScale<DomainType, RangeType>(typeOf<DomainType>(), typeOf<RangeType>()),
        ).also {
            collectorAccessor.mappings[this] = it
        }
    }

    // TODO positional set
    infix fun <RangeType : Any> NonPositionalAes<RangeType>.setTo(value: RangeType) {
        collectorAccessor.settings[this] = NonPositionalSetting(this, value)
    }

    // TODO other????
    val x = X
    val y = Y

}

abstract class LayerContext : BaseContext() {
    val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

class PointsContext : LayerContext() {
    val size = SIZE
    val color = COLOR
    val alpha = ALPHA

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR

    val symbol = SYMBOL
}

class LineContext : LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val lineType = LINE_TYPE
}

class BarsContext : LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR
}

class PlotContext(internal val dataset: NamedData) : BaseContext() {

    // todo hide
    val layers: MutableList<Layer> = mutableListOf()
    val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    val layout = Layout()

    // TODO
    internal constructor(plot: Plot) : this(plot.dataset) {
        // TODO add settings?
        collector.mappings.putAll(plot.globalMappings)

        //layers.addAll(plot.layers)
        //features.putAll(plot.features)
        ///layout.copyFrom(plot.layout)
    }

}
