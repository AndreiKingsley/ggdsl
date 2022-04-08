package com.andreikingsley.ggdsl.ir.aes

sealed class Aes(val name: String)

sealed class PositionalAes(name: String): Aes(name)

class ScalablePositionalAes(name: String): PositionalAes(name)

class NonScalablePositionalAes(name: String): PositionalAes(name)

open class NonPositionalAes<T>(name: String): Aes(name)

class MappableNonPositionalAes<T>(name: String): NonPositionalAes<T>(name)

// todo scalable???