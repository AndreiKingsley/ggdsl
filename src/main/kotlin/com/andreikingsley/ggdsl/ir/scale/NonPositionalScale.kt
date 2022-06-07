package com.andreikingsley.ggdsl.ir.scale

/**
 * Non-positional scale interface. Non-positional scale is used in case
 * of mapping to non-positional aesthetic attribute.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 */
sealed interface NonPositionalScale<DomainType : Any, RangeType : Any> : Scale

/**
 * Non-positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param RangeType the type of the range.
 * @param domainLimits the limits of the domain.
 * @param rangeLimits the limits of the range.
 */
data class NonPositionalContinuousScale<DomainType : Any, RangeType : Any>(
    val domainLimits: Pair<DomainType, DomainType>? = null,
    val rangeLimits: Pair<RangeType, RangeType>? = null,
) : ContinuousScale, NonPositionalScale<DomainType, RangeType>

/**
 * Non-positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param domainCategories the list of the domain categories.
 * @param rangeValues the list of the range values.
 */
data class NonPositionalCategoricalScale<DomainType : Any, RangeType : Any>(
    val domainCategories: List<DomainType>? = null,
    val rangeValues: List<RangeType>? = null,
) : CategoricalScale, NonPositionalScale<DomainType, RangeType>
