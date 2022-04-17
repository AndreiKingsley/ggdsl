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
    val mappings: MutableMap<Aes, Mapping<*>> = mutableMapOf()
    val settings: MutableMap<Aes, Setting> = mutableMapOf()
    //val scales: MutableMap<Aes, Scale> = mutableMapOf()

    fun copyFrom(other: ContextCollector) {
        mappings.putAll(other.mappings)
        settings.putAll(other.settings)
        //scales.putAll(other.scales)
    }
}


abstract class BaseContext {
    abstract var data: MutableNamedData

    protected val collector = ContextCollector()

    @PublishedApi
    internal val collectorAccessor: ContextCollector
        get() = collector

    fun copyFrom(other: BaseContext) {
        data = other.data
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


    inline infix fun <reified DomainType : Any>
            NonScalablePositionalAes.mapTo(iterable: Iterable<DomainType>):
            NonScalablePositionalMapping<DomainType> {
        // generate new name????
        val dataSource = DataSource<DomainType>(name)
        val list = iterable.toList()
        this@BaseContext.data[dataSource.id] = list
        return mapTo(dataSource)
    }


    inline infix fun <reified DomainType : Any>
            ScalablePositionalAes.mapTo(iterable: Iterable<DomainType>):
            ScalablePositionalMapping<DomainType> {
        // generate new name????
        val dataSource = DataSource<DomainType>(name)
        val list = iterable.toList()
        this@BaseContext.data[dataSource.id] = list
        return mapTo(dataSource)
    }


    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            MappableNonPositionalAes<RangeType>.mapTo(iterable: Iterable<DomainType>):
            NonPositionalMapping<DomainType, RangeType> {
        // generate new name????
        val dataSource = DataSource<DomainType>(name)
        val list = iterable.toList()
        this@BaseContext.data[dataSource.id] = list
        return mapTo(dataSource)
    }


    // TODO other????
    val x = X
    val y = Y

}

abstract class LayerContext : BaseContext() {

    // todo hide
    val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

class PointsContext(override var data: MutableNamedData) : LayerContext() {
    val size = SIZE
    val color = COLOR
    val alpha = ALPHA

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR

    val symbol = SYMBOL
}

class LineContext (override var data: MutableNamedData) : LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val lineType = LINE_TYPE
}

class BarsContext (override var data: MutableNamedData) : LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR
}

class PlotContext() : BaseContext() {

    override var data: MutableNamedData = mutableMapOf()

    // todo hide
    val layers: MutableList<Layer> = mutableListOf()
    val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    val layout = Layout()

    // TODO
    internal constructor(plot: Plot) : this() {
        // TODO add settings?
        data = plot.dataset.toMutableMap()
        collector.mappings.putAll(plot.globalMappings)

        //layers.addAll(plot.layers)
        //features.putAll(plot.features)
        ///layout.copyFrom(plot.layout)
    }

}
