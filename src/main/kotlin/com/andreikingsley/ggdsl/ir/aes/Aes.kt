package com.andreikingsley.ggdsl.ir.aes

sealed interface Aes{
    val name: String
}

sealed interface MappableAes: Aes
sealed interface ScalableAes: MappableAes

sealed interface PositionalAes: MappableAes

class ScalablePositionalAes(override val name: String): PositionalAes, ScalableAes

class NonScalablePositionalAes(override val name: String): PositionalAes

open class NonPositionalAes<T: Any>(override val name: String): Aes

class MappableNonPositionalAes<T: Any>(name: String): NonPositionalAes<T>(name), ScalableAes

// TODO Other exists??? Todo Settable?