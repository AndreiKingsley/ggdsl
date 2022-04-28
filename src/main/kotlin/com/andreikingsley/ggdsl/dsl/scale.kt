package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.scale.*

fun continuous() = NonPositionalContinuousDefaultScale()
fun categorical() = NonPositionalCategoricalDefaultScale()
fun continuousPos() = PositionalContinuousDefaultScale()
fun categoricalPos() = PositionalCategoricalDefaultScale()

fun <DomainType : Any> continuousPos(limits: Pair<DomainType, DomainType>? = null) =
    PositionalContinuousScale(limits)

fun <DomainType : Any, RangeType : Any> continuous(
    domainLimits: Pair<DomainType, DomainType>? = null,
    rangeLimits: Pair<RangeType, RangeType>? = null,
) = NonPositionalContinuousScale(domainLimits, rangeLimits)

fun <DomainType : Any> categoricalPos(categories: List<DomainType>? = null) =
    PositionalCategoricalScale(categories)

fun <DomainType : Any, RangeType : Any> categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
) = NonPositionalCategoricalScale(domainCategories, rangeValues)
