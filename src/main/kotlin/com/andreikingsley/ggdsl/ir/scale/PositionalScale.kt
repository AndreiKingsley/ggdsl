package com.andreikingsley.ggdsl.ir.scale

import kotlin.reflect.KType

sealed interface PositionalScale<DomainType: Any>: Scale {
 //   val domainType: KType
}

class PositionalContinuousScale<DomainType: Any>(
    val limits: Pair<DomainType, DomainType>? = null,
   // override val domainType: KType,
): ContinuousScale, PositionalScale<DomainType>

class PositionalCategoricalScale<DomainType: Any>(
    val categories: List<DomainType>? = null,
 //   override val domainType: KType,
): CategoricalScale, PositionalScale<DomainType>
