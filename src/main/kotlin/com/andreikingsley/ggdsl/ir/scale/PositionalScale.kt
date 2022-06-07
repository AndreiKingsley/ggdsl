package com.andreikingsley.ggdsl.ir.scale

/**
 * Positional scale interface. Positional scale is used in case
 * of mapping to positional aesthetic attribute.
 * It has an implicit range.
 *
 * @param DomainType the type of the domain.
 */
sealed interface PositionalScale<DomainType: Any>: Scale

/**
 * Positional continuous scale.
 *
 * @param DomainType the type of the domain.
 * @param limits the limits of the domain.
 */
data class PositionalContinuousScale<DomainType: Any>(
    val limits: Pair<DomainType, DomainType>? = null,
): ContinuousScale, PositionalScale<DomainType>

/**
 * Positional categorical scale.
 *
 * @param DomainType the type of the domain.
 * @param categories the list of the domain categories.
 */
data class PositionalCategoricalScale<DomainType: Any>(
    val categories: List<DomainType>? = null,
): CategoricalScale, PositionalScale<DomainType>
