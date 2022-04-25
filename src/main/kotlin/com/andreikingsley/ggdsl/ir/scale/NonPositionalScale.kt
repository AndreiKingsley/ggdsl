package com.andreikingsley.ggdsl.ir.scale

import kotlin.reflect.KType

sealed interface NonPositionalScale<DomainType: Any, RangeType: Any> : Scale {
//    val domainType: KType
//    val rangeType: KType
}

class NonPositionalContinuousScale<DomainType: Any, RangeType: Any>(
    val domainLimits: Pair<DomainType, DomainType>? = null,
    val rangeLimits: Pair<RangeType, RangeType>? = null,
  //  override val domainType: KType,
  //  override val rangeType: KType,
):
    ContinuousScale, NonPositionalScale<DomainType, RangeType>

class NonPositionalCategoricalScale<DomainType: Any, RangeType: Any>(
    val rangeCategories: List<DomainType>? = null,
    val rangeValues: List<RangeType>? = null,
 //   override val domainType: KType,
  //  override val rangeType: KType,
):
    CategoricalScale, NonPositionalScale<DomainType, RangeType>
