package com.andreikingsley.ggdsl.ir.scale

import com.andreikingsley.ggdsl.ir.scale.guide.Axis
import com.andreikingsley.ggdsl.ir.scale.guide.Legend
import kotlin.reflect.KType

sealed interface Scale

abstract class PositionalScale<DomainType : Any> : Scale {
    var axis: Axis? = null

    abstract val domainType: KType
}

abstract class NonPositionalScale<DomainType : Any, RangeType : Any> : Scale {
    var legend: Legend? = null

    abstract val domainType: KType
    abstract val rangeType: KType
}

sealed interface DefaultScale : Scale

class DefaultPositionalScale<DomainType : Any>(override val domainType: KType) : PositionalScale<DomainType>(),
    DefaultScale

class DefaultNonPositionalScale<DomainType : Any, RangeType : Any>(
    override val domainType: KType,
    override val rangeType: KType
) : NonPositionalScale<DomainType, RangeType>(), DefaultScale

class CategoricalPositionalScale<DomainType : Any>(override val domainType: KType) : PositionalScale<DomainType>() {
    var categories: List<DomainType> = listOf()
}

class ContinuousPositionalScale<DomainType : Any>(override val domainType: KType) : PositionalScale<DomainType>() {
    var limits: Pair<DomainType, DomainType>? = null
}

class CategoricalNonPositionalScale<DomainType : Any, RangeType : Any>(
    override val domainType: KType,
    override val rangeType: KType
) : NonPositionalScale<DomainType, RangeType>() {
    var categories: List<DomainType> = listOf()
    var values: List<RangeType> = listOf()
}

class ContinuousNonPositionalScale<DomainType : Any, RangeType : Any>(
    override val domainType: KType,
    override val rangeType: KType
) : NonPositionalScale<DomainType, RangeType>() {
    var domainLimits: Pair<DomainType, DomainType>? = null
    var range: Pair<RangeType, RangeType>? = null
}
