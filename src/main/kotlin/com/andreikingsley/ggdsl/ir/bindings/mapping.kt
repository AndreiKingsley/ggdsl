package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.scale.NonPositionalScale
import com.andreikingsley.ggdsl.ir.scale.PositionalScale
import kotlin.reflect.KType

sealed interface Mapping

sealed interface PositionalMapping<DomainType>: Mapping {
    val aes: PositionalAes
    val source: DataSource<DomainType>
    val domainType: KType
}

data class NonScalablePositionalMapping<DomainType>(
    override val aes: PositionalAes,
    override val source: DataSource<DomainType>,
    override val domainType: KType
): PositionalMapping<DomainType>

data class ScalablePositionalMapping<DomainType : Any>(
    override val aes: PositionalAes,
    override val source: DataSource<DomainType>,
    override val domainType: KType,

    val scale: PositionalScale<DomainType>
): PositionalMapping<DomainType>

data class NonPositionalMapping<DomainType : Any, RangeType : Any>(
    val aes: NonPositionalAes<RangeType>,
    val source: DataSource<DomainType>,
    val domainType: KType,
    val rangeType: KType,

    val scale: NonPositionalScale<DomainType, RangeType>
): Mapping

// todo nonscalable???s
