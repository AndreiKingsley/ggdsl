package com.andreikingsley.ggdsl.ir.aes

sealed class Aes(val name: String)

class PositionalAes(name: String): Aes(name)

open class NonPositionalAes<T>(name: String): Aes(name)

class MappableNonPositionalAes<T>(name: String): NonPositionalAes<T>(name)
