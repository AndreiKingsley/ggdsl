package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.bindings.NonPositionalMapping
import com.andreikingsley.ggdsl.ir.bindings.PositionalMapping
import com.andreikingsley.ggdsl.ir.bindings.ScalablePositionalMapping
import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.scale.*
import kotlin.reflect.typeOf

// TODO internal

class ContextCollector {
    val mappings: MutableMap<Aes, DataSource<Any>> = mutableMapOf()
    val settings: MutableMap<Aes, Any> = mutableMapOf()
    val scales: MutableMap<Aes, Scale> = mutableMapOf()

    fun copyFrom(other: ContextCollector) {
        mappings.putAll(other.mappings)
        settings.putAll(other.settings)
        scales.putAll(other.scales)
    }
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

    inline infix fun <reified DomainType : Any>
            ScalablePositionalAes.mapTo(dataSource: DataSource<DomainType>):
            PositionalMapping<DomainType> {
        collectorAccessor.mappings[this] = dataSource
        collectorAccessor.scales[this] = DefaultPositionalScale<DomainType>(typeOf<DomainType>())
        return ScalablePositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>(),
            TODO()
        )
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
        collectorAccessor.mappings[this] = dataSource
        collectorAccessor.scales[this] =
            DefaultNonPositionalScale<DomainType, RangeType>(typeOf<DomainType>(), typeOf<RangeType>())
        return NonPositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>(),
            typeOf<RangeType>(),
            TODO()
        )
    }

    // TODO positional set
    infix fun <RangeType : Any> NonPositionalAes<RangeType>.setTo(value: RangeType) {
        collectorAccessor.settings[this] = value
    }

    inline infix fun <reified DomainType : Any>
            PositionalMapping<DomainType>.scaleContinuous(
        block: (ContinuousPositionalScale<DomainType>.() -> Unit)
    ) {
        collectorAccessor.scales[this.aes] =
            ContinuousPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
    }

    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            NonPositionalMapping<DomainType, RangeType>.scaleContinuous(
        block: (ContinuousNonPositionalScale<DomainType, RangeType>.() -> Unit)
    ) {
        collectorAccessor.scales[this.aes] =
            ContinuousNonPositionalScale<DomainType, RangeType>(
                typeOf<DomainType>(), typeOf<RangeType>()
            ).apply(block)
    }

    inline infix fun <reified DomainType : Any> PositionalMapping<DomainType>.scaleCategorical(
        block: (CategoricalPositionalScale<DomainType>.() -> Unit)
    ) {
        collectorAccessor.scales[this.aes] = CategoricalPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
    }

    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            NonPositionalMapping<DomainType, RangeType>.scaleCategorical(
        block: (CategoricalNonPositionalScale<DomainType, RangeType>.() -> Unit)
    ) {
        collectorAccessor.scales[this.aes] =
            CategoricalNonPositionalScale<DomainType, RangeType>(typeOf<DomainType>(), typeOf<RangeType>()).apply(block)
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
