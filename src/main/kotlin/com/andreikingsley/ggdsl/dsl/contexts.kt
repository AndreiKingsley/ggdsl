package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.scale.*
import kotlin.reflect.typeOf

// TODO internal

open class BaseContext {
    var dataset: NamedData? = null

    var mappings: MutableMap<Aes, DataSource<Any>> = mutableMapOf()
    var settings: MutableMap<Aes, Any> = mutableMapOf()
    var scales: MutableMap<Aes, Scale> = mutableMapOf()

    inline infix fun <reified DomainType : Any>
            PositionalAes.mapTo(dataSource: DataSource<DomainType>):
            PositionalMapping<DomainType> {
        mappings[this] = dataSource
        scales[this] = DefaultPositionalScale<DomainType>(typeOf<DomainType>())
        return PositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>()
        )
    }

    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            MappableNonPositionalAes<RangeType>.mapTo(dataSource: DataSource<DomainType>):
            NonPositionalMapping<DomainType, RangeType> {
        mappings[this] = dataSource
        scales[this] = DefaultNonPositionalScale<DomainType, RangeType>(typeOf<DomainType>(), typeOf<RangeType>())
        return NonPositionalMapping(
            this,
            dataSource,
            typeOf<DomainType>(),
            typeOf<RangeType>(),
        )
    }

    // TODO positional set
    infix fun <RangeType : Any> NonPositionalAes<RangeType>.setTo(value: RangeType) {
        settings[this] = value // TODO()
    }

    inline infix fun <reified DomainType : Any>
            PositionalMapping<DomainType>.scaleContinuous(
        block: (ContinuousPositionalScale<DomainType>.() -> Unit)
    ) {
        scales[this.aes] = ContinuousPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
    }

    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            NonPositionalMapping<DomainType, RangeType>.scaleContinuous(
        block: (ContinuousNonPositionalScale<DomainType, RangeType>.() -> Unit)
    ) {
        scales[this.aes] =
            ContinuousNonPositionalScale<DomainType, RangeType>(typeOf<DomainType>(), typeOf<RangeType>()).apply(block)
    }

    inline infix fun <reified DomainType : Any> PositionalMapping<DomainType>.scaleCategorical(
        block: (CategoricalPositionalScale<DomainType>.() -> Unit)
    ) {
        scales[this.aes] = CategoricalPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
    }

    inline infix fun <reified DomainType : Any, reified RangeType : Any>
            NonPositionalMapping<DomainType, RangeType>.scaleCategorical(
        block: (CategoricalNonPositionalScale<DomainType, RangeType>.() -> Unit)
    ) {
        scales[this.aes] =
            CategoricalNonPositionalScale<DomainType, RangeType>(typeOf<DomainType>(), typeOf<RangeType>()).apply(block)
    }

    // TODO Scales
    /*
    infix fun <T : Any> PositionalMapping<T>.scale(scale: PositionalScale<T>) {
        scales[this.aes] = scale
    }

    infix fun <T : Any, R : Any> NonPositionalMapping<T, R>.scale(scale: NonPositionalScale<T, R>) {
        scales[this.aes] = scale
    }

     */

    // TODO other????
    val x = X
    val y = Y
}

abstract class LayerContext : BaseContext() {
    fun copyFrom(other: BaseContext) {
        dataset = other.dataset?.toMutableMap() // TODO
        mappings = other.mappings.toMutableMap()
        settings = other.settings.toMutableMap()
        scales = other.scales.toMutableMap()
    }
}

class PointsContext : LayerContext() {
    val size = SIZE
    val color = COLOR
    val alpha = ALPHA

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR

    // todo symbol
}

class LineContext : LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    // todo linetype
}

class BarsContext : LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR
}

class PlotContext : BaseContext() {
    val layers: MutableList<Layer> = mutableListOf()
}
