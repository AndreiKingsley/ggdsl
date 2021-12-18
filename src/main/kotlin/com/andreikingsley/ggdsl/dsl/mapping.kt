package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.aes.*
import kotlin.reflect.KType

class PositionalMapping<DomainType>(
    val aes: PositionalAes,
    val source: DataSource<DomainType>,
    val domainType: KType
)

class NonPositionalMapping<DomainType, RangeType>(
    val aes: NonPositionalAes<RangeType>,
    val source: DataSource<DomainType>,
    val domainType: KType,
    val rangeType: KType
)
