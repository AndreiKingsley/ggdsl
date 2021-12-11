package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.aes.*

class PositionalMapping<DomainType>(
    val aes: PositionalAes,
    val source: DataSource<DomainType>
)

class NonPositionalMapping<DomainType, RangeType>(
    val aes: NonPositionalAes<RangeType>,
    val source: DataSource<DomainType>
)
