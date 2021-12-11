package com.andreikingsley.ggdsl.ir.scale

import com.andreikingsley.ggdsl.ir.scale.guide.Axis
import com.andreikingsley.ggdsl.ir.scale.guide.Legend

sealed interface Scale

sealed interface DefaultScale : Scale

class DefaultPositionalScale<DomainType : Any> : DefaultScale

class DefaultNonPositionalScale<DomainType : Any, RangeType : Any> : DefaultScale

sealed class PositionalScale : Scale {
    var axis: Axis? = null
}

sealed class NonPositionalScale : Scale {
    var legend: Legend? = null
}

class CategoricalPositionalScale<DomainType : Any> : PositionalScale() {
    var categories: List<DomainType> = listOf()
}

class ContinuousPositionalScale<DomainType : Any> : PositionalScale() {
    var limits: Pair<DomainType, DomainType>? = null
}

class CategoricalNonPositionalScale<DomainType : Any, RangeType : Any> : NonPositionalScale() {
    var categories: List<DomainType> = listOf()
    var values: List<RangeType> = listOf()
}

class ContinuousNonPositionalScale<DomainType : Any, RangeType : Any> : NonPositionalScale() {
    var domainLimits: Pair<DomainType, DomainType>? = null
    var range: Pair<RangeType, RangeType>? = null
}
