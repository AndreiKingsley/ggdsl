package com.andreikingsley.ggdsl.ir.aes

/**
 * Base interface for aesthetic attribute.
 *
 * @property name the name of this attribute
 */
sealed interface Aes {
    val name: String
}

/**
 * Interface for aesthetic attributes that can be mapped to.
 *
 * @property name the name of this attribute
 */
sealed interface MappableAes: Aes

/**
 * Interface for aesthetic attributes that can be given an explicit scale.
 *
 * @property name the name of this attribute
 */
sealed interface ScalableAes: MappableAes

/**
 * Interface for positional aesthetic attributes.
 *
 * @property name the name of this attribute
 */
sealed interface PositionalAes: MappableAes

/**
 * Ordinary positional aesthetic attribute.
 *
 * @property name the name of this attribute
 */
class ScalablePositionalAes(override val name: String): PositionalAes, ScalableAes

/**
 * Positional aesthetic attribute with an implicit scale ("sub-positional").
 *
 * @property name the name of this attribute
 */
class NonScalablePositionalAes(override val name: String): PositionalAes

// todo interface and data
/**
 * Non-positional aesthetic attribute.
 *
 * @property name the name of this attribute
 */
open class NonPositionalAes<in T: Any>(override val name: String): Aes

/**
 * Non-positional aesthetic attribute, that can be mapped to and have an explicit scale.
 *
 * @property name the name of this attribute
 */
class MappableNonPositionalAes<in T: Any>(name: String): NonPositionalAes<T>(name), ScalableAes

// TODO Other exists??? Todo Settable?