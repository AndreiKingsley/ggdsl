package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.*
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.scale.*

open class BaseContext {
    var dataset: NamedData? = null

    var mappings: MutableMap<Aes, DataSource<Any>> = mutableMapOf()
    var settings: MutableMap<Aes, Any> = mutableMapOf()
    var scales: MutableMap<Aes, Scale> = mutableMapOf()

    infix fun<T: Any> PositionalAes.mapTo(dataSource: DataSource<T>): PositionalMapping<T> {
        mappings[this] = dataSource
        scales[this] = DefaultPositionalScale<T>()
        return PositionalMapping(
            this,
            dataSource
        )
    }

    infix fun<T: Any, R: Any> NonPositionalAes<R>.mapTo(dataSource: DataSource<T>): NonPositionalMapping<T, R> {
        mappings[this] = dataSource
        scales[this] = DefaultNonPositionalScale<T, R>()
        return NonPositionalMapping(
            this,
            dataSource
        )
    }

    // TODO positional set
    infix fun<T: Any> NonPositionalAes<T>.setTo(value: T) {
        settings[this] = value // TODO()
    }

    infix fun<T: Any> PositionalMapping<T>.scaleContinuous(block: (ContinuousPositionalScale<T>.() -> Unit)) {
        scales[this.aes] = ContinuousPositionalScale<T>().apply(block)
    }

    infix fun<T: Any, R: Any> NonPositionalMapping<T, R>.scaleContinuous(block: (ContinuousNonPositionalScale<T, R>.() -> Unit)) {
        scales[this.aes] = ContinuousNonPositionalScale<T, R>().apply(block)
    }

    infix fun<T: Any> PositionalMapping<T>.scaleCategorical(block: (CategoricalPositionalScale<T>.() -> Unit)) {
        scales[this.aes] = CategoricalPositionalScale<T>().apply(block)
    }

    infix fun<T: Any, R: Any> NonPositionalMapping<T, R>.scaleCategorical(block: (CategoricalNonPositionalScale<T, R>.() -> Unit)) {
        scales[this.aes] = CategoricalNonPositionalScale<T, R>().apply(block)
    }

    /* TODO
    infix fun Aes.scale(scale: Scale) {
        scales[this] = scale
    }

     */

    // TODO more
    val x = X
    val y = Y

    val size = SIZE
    val color = COLOR

}

class LayerContext : BaseContext() {
    fun copyFrom(other: BaseContext) {
        dataset = other.dataset?.toMutableMap()
        mappings = other.mappings.toMutableMap()
        settings = other.settings.toMutableMap()
        scales = other.scales.toMutableMap()
    }
}

class PlotContext : BaseContext() {
    val layers: MutableList<Layer> = mutableListOf()
}
