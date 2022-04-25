package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.scale.*

fun continuous() = ContinuousDefaultScale()
fun categorical() = CategoricalDefaultScale()

fun <DomainType : Any> continuousPos(limits: Pair<DomainType, DomainType>? = null) =
    PositionalContinuousScale(limits)

fun <DomainType : Any, RangeType : Any> continuous(
    domainLimits: Pair<DomainType, DomainType>? = null,
    range: Pair<RangeType, RangeType>? = null,
) = NonPositionalContinuousScale(domainLimits, range)

fun <DomainType : Any> categoricalPos(categories: List<DomainType>? = null) =
    PositionalCategoricalScale(categories)

fun <DomainType : Any, RangeType : Any> categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
) = NonPositionalCategoricalScale(domainCategories, rangeValues)
