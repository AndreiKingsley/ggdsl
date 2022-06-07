package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.scale.*

/**
 * Creates a new default continuous non-positional scale
 */
fun continuous() = NonPositionalContinuousDefaultScale
/**
 * Creates a new default categorical non-positional scale
 */
fun categorical() = NonPositionalCategoricalDefaultScale
/**
 * Creates a new default continuous positional scale
 */
fun continuousPos() = PositionalContinuousDefaultScale
/**
 * Creates a new default categorical positional scale
 */
fun categoricalPos() = PositionalCategoricalDefaultScale

/**
 * Creates a new continuous positional scale
 *
 * @param DomainType type of domain
 * @param limits segment defining the domain
 * @return new [PositionalContinuousScale] with given limits
 */
fun <DomainType : Any> continuousPos(limits: Pair<DomainType, DomainType>? = null) =
    PositionalContinuousScale(limits)

/**
 * Creates a new continuous non-positional scale
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param domainLimits segment defining the domain
 * @param rangeLimits segment defining the range
 * @return new [NonPositionalContinuousScale] with given limits
 */
fun <DomainType : Any, RangeType : Any> continuous(
    domainLimits: Pair<DomainType, DomainType>? = null,
    rangeLimits: Pair<RangeType, RangeType>? = null,
) = NonPositionalContinuousScale(domainLimits, rangeLimits)

/**
 * Creates a new categorical positional scale
 *
 * @param DomainType type of domain
 * @param categories [List] defining the domain
 * @return new [PositionalCategoricalScale] with given categories
 */
fun <DomainType : Any> categoricalPos(categories: List<DomainType>? = null) =
    PositionalCategoricalScale(categories)

/**
 * Creates a new categorical non-positional scale
 *
 * @param DomainType type of domain
 * @param RangeType type of range
 * @param domainCategories [List] defining the domain
 * @param rangeValues [List] defining the range
 * @return new [NonPositionalCategoricalScale] with given limits
 */
fun <DomainType : Any, RangeType : Any> categorical(
    domainCategories: List<DomainType>? = null,
    rangeValues: List<RangeType>? = null,
) = NonPositionalCategoricalScale(domainCategories, rangeValues)
