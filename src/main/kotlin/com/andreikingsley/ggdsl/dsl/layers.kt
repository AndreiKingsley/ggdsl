package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Geom
import com.andreikingsley.ggdsl.util.color.Color
import com.andreikingsley.ggdsl.util.symbol.Symbol

/**
 * Adds a new points layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation:
 * ```
 * points {
 *    size(source<Float>("durability")) // mapping from data source to size value
 *    color(Color.RED) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][PointsContext.x]
 *  - [y][PointsContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][PointsContext.color] - this point fill color, of the type [Color], mappable.
 *  - [size][PointsContext.size] - this point size of, of the type [Double], mappable.
 *  - [alpha][PointsContext.alpha] - this point fill alpha, of the type [Double], mappable.
 *  - [symbol][PointsContext.symbol] - this point shape, of the type [Symbol], mappable.
 *
 *  - [borderColor][PointsContext.borderColor] - this point border color, of the type [Color], non-mappable.
 *  - [borderWidth][PointsContext.borderWidth] - this point border width, of the type [Double], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][PointsContext.data].
 *
 *  @see [BaseBindingContext]
 */
inline fun PlotContext.points(block: PointsContext.() -> Unit) {
    layers.add(PointsContext(data).apply { copyFrom(this@points) }.apply(block).toLayer(Geom.POINT))
}

/**
 * Adds a new bars layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute invocation:
 * ```
 * bars {
 *    color(source<String>("type")) // mapping from data source to size value
 *    width(0.5) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][BarsContext.x]
 *  - [y][BarsContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][BarsContext.color] - this point fill color, of the type [Color], mappable.
 *  - [alpha][BarsContext.alpha] - this point fill alpha, of the type [Double], mappable.
 *
 *  - [width][BarsContext.width] - this point border width, of the type [Double], non-mappable.
 *  - [borderColor][BarsContext.borderColor] - this point border color, of the type [Color], non-mappable.
 *  - [borderWidth][BarsContext.borderWidth] - this point border width, of the type [Double], non-mappable.
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][BarsContext.data].
 *
 *  @see [BaseBindingContext]
 */
inline fun PlotContext.bars(block: BarsContext.() -> Unit) {
    layers.add(BarsContext(data).apply { copyFrom(this@bars) }.apply(block).toLayer(Geom.BAR))
}

/**
 * Adds a new bars layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute invocation:
 * ```
 * line {
 *    x(source<Double>("time")) // mapping from data source to size value
 *    lineType(Line) // setting of constant color value
 * }
 * ```
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][LineContext.x]
 *  - [y][LineContext.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [PlotContext] (if they exist).
 *
 *   Non-positional:
 *  - [color][LineContext.color] - this point fill color, of the type [Color], mappable (todo).
 *  - [alpha][LineContext.alpha] - this point fill alpha, of the type [Double], mappable (todo).
 *
 *  - [width][LineContext.width] - this point border width, of the type [Double], non-mappable.
 *  - [lineType][LineContext.lineType] - this point border color, of the type [Color], non-mappable (todo).
 *
 *  By default, the dataset inherited from the parent [PlotContext] is used,
 *  but can be overridden with an assignment to the [data][LineContext.data].
 *
 *  @see [BaseBindingContext]
 */
inline fun PlotContext.line(block: LineContext.() -> Unit) {
    layers.add(LineContext(data).apply { copyFrom(this@line) }.apply(block).toLayer(Geom.LINE))
}
