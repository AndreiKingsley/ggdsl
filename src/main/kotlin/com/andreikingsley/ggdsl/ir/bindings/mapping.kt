package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.aes.*
import kotlin.reflect.KType

/**
 * Mapping base interface
 *
 * @property aes the aesthetic attribute to be mapped to.
 */
sealed interface Mapping {
    val aes: MappableAes
}

/**
 * Mapping with an implicit scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property source the source to which the mapping is applied
 * @property domainType the type of domain
 */
data class NonScalablePositionalMapping<DomainType: Any>(
    override val aes: NonScalablePositionalAes,
    val source: DataSource<DomainType>,
    val domainType: KType
): Mapping

/**
 * Mapping interface with an explicit scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled source to which the mapping is applied
 * @property domainType the type of domain
 */
sealed interface ScaledMapping<DomainType: Any>: Mapping {
    override val aes: ScalableAes
    val sourceScaled: SourceScaled<DomainType>
    val domainType: KType
}

/**
 * Mapping interface with a default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default source to which the mapping is applied
 * @property domainType the type of domain
 */
sealed interface ScaledDefaultMapping<DomainType: Any>: ScaledMapping<DomainType>{
    override val sourceScaled: SourceScaledDefault<DomainType>
}

/**
 * Mapping with an unspecified default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default unspecified source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledUnspecifiedDefaultMapping<DomainType: Any>(
    override val aes: ScalableAes,
    override val sourceScaled: SourceScaledUnspecifiedDefault<DomainType>,
    override val domainType: KType
): ScaledMapping<DomainType>

/**
 * Mapping with a positional default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledPositionalDefaultMapping<DomainType: Any>(
    override val aes: ScalableAes,
    override val sourceScaled: SourceScaledPositionalDefault<DomainType>,
    override val domainType: KType
): ScaledMapping<DomainType>

/**
 * Mapping with a non-positional default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default non-positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledNonPositionalDefaultMapping<DomainType: Any>(
    override val aes: ScalableAes,
    override val sourceScaled: SourceScaledNonPositionalDefault<DomainType>,
    override val domainType: KType
): ScaledMapping<DomainType>

/**
 * Mapping with a positional scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledPositionalMapping<DomainType: Any>(
    override val aes: ScalablePositionalAes,
    override val sourceScaled: SourceScaledPositional<DomainType>,
    override val domainType: KType
): ScaledMapping<DomainType>


/**
 * Mapping with a non-positional scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled non-positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledNonPositionalMapping<DomainType: Any, RangeType: Any>(
    override val aes: MappableNonPositionalAes<RangeType>,
    override val sourceScaled: SourceScaledNonPositional<DomainType, RangeType>,
    override val domainType: KType,
    val rangeType: KType,
): ScaledMapping<DomainType>
