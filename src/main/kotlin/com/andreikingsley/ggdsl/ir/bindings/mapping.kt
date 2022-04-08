package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.scale.NonPositionalScale
import com.andreikingsley.ggdsl.ir.scale.PositionalScale
import com.andreikingsley.ggdsl.ir.scale.Scale
import kotlin.reflect.KType

sealed interface Mapping<DomainType> {
    val aes: Aes
    val source: DataSource<DomainType>
}

sealed interface ScalableMapping {
    val scale: Scale
}

sealed interface PositionalMapping<DomainType>: Mapping<DomainType> {
    override val aes: PositionalAes
    override val source: DataSource<DomainType>
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

    override var scale: PositionalScale<DomainType>
): PositionalMapping<DomainType>, ScalableMapping

data class NonPositionalMapping<DomainType : Any, RangeType : Any>(
    override val aes: NonPositionalAes<RangeType>,
    override val source: DataSource<DomainType>,
    val domainType: KType,
    val rangeType: KType,

    override var scale: NonPositionalScale<DomainType, RangeType>
): Mapping<DomainType>, ScalableMapping

// todo nonscalable???s