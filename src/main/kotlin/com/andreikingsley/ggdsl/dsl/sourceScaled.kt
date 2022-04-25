package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.scale.*

fun <DomainType : Any> DataSource<DomainType>.scaled() =
    SourceScaledDefault(this, UnspecifiedDefaultScale())

fun <DomainType : Any> DataSource<DomainType>.scaled(scale: DefaultScale) =
    SourceScaledDefault(this, scale)

fun <DomainType : Any> DataSource<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this, scale)

fun <DomainType : Any, RangeType: Any> DataSource<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this, scale)
