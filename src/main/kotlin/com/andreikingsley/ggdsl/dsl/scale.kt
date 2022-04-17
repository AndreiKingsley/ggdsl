package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.ir.scale.*
import com.andreikingsley.ggdsl.ir.scale.guide.*
import kotlin.reflect.*

// TODO hide scale
class PositionalScaleContext<DomainType : Any>(domainType: KType) {
    var scale: PositionalScale<DomainType> = DefaultPositionalScale(domainType)
    var axis: Axis? = null
}

inline infix fun<DomainType : Any> ScalablePositionalMapping<DomainType>.scale(
    block: PositionalScaleContext<DomainType>.() -> Unit
){
    scale = PositionalScaleContext<DomainType>(domainType).apply(block).let {
        it.scale.apply {
            this.axis = it.axis
        }
    }
}

inline infix fun<DomainType : Any, RangeType: Any> NonPositionalMapping<DomainType, RangeType>.scale(
    block: NonPositionalScaleContext<DomainType, RangeType>.() -> Unit
){
    scale = NonPositionalScaleContext<DomainType, RangeType>(domainType, rangeType).apply(block).let {
        it.scale.apply {
            this.legend = it.legend
        }
    }
}

class NonPositionalScaleContext<DomainType : Any, RangeType : Any>(domainType: KType, rangeType: KType) {
    var scale: NonPositionalScale<DomainType, RangeType> =
        DefaultNonPositionalScale(domainType, rangeType)
    var legend: Legend? = null
}

inline infix fun <reified DomainType : Any>
        PositionalScaleContext<DomainType>.continuous(
    block: (ContinuousPositionalScale<DomainType>.() -> Unit)
) {
    scale = ContinuousPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
}

inline infix fun <reified DomainType : Any, reified RangeType : Any>
        NonPositionalScaleContext<DomainType, RangeType>.continuous(
    block: (ContinuousNonPositionalScale<DomainType, RangeType>.() -> Unit)
) {
    scale = ContinuousNonPositionalScale<DomainType, RangeType>(
            typeOf<DomainType>(), typeOf<RangeType>()
    ).apply(block)
}

inline infix fun <reified DomainType : Any> PositionalScaleContext<DomainType>.categorical(
    block: (CategoricalPositionalScale<DomainType>.() -> Unit)
) {
    scale = CategoricalPositionalScale<DomainType>(typeOf<DomainType>()).apply(block)
}

inline infix fun <reified DomainType : Any, reified RangeType : Any>
        NonPositionalScaleContext<DomainType, RangeType>.categorical(
    block: (CategoricalNonPositionalScale<DomainType, RangeType>.() -> Unit)
) {
    scale = CategoricalNonPositionalScale<DomainType, RangeType>(
            typeOf<DomainType>(), typeOf<RangeType>()
    ).apply(block)
}
