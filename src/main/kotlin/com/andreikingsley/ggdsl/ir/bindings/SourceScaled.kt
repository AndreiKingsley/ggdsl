package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.scale.*

sealed interface SourceScaled<DomainType: Any> {
    val source: DataSource<DomainType>
    val scale: Scale
}

class SourceScaledDefault<DomainType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: DefaultScale
) : SourceScaled<DomainType>

class SourceScaledPositional<DomainType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: PositionalScale<DomainType>
): SourceScaled<DomainType>

class SourceScaledNonPositional<DomainType: Any, RangeType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: NonPositionalScale<DomainType, RangeType>
): SourceScaled<DomainType>
