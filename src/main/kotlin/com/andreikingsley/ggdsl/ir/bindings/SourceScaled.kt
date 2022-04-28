package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.scale.*

sealed interface SourceScaled<DomainType: Any> {
    val source: DataSource<DomainType>
    val scale: Scale
}

sealed interface SourceScaledDefault<DomainType: Any>: SourceScaled<DomainType> {
    override val scale: DefaultScale
}

class SourceScaledUnspecifiedDefault<DomainType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: UnspecifiedDefaultScale
): SourceScaled<DomainType>

class SourceScaledPositionalDefault<DomainType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: PositionalDefaultScale
): SourceScaled<DomainType>

class SourceScaledNonPositionalDefault<DomainType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: NonPositionalDefaultScale
): SourceScaled<DomainType>

class SourceScaledPositional<DomainType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: PositionalScale<DomainType>
): SourceScaled<DomainType>

class SourceScaledNonPositional<DomainType: Any, RangeType: Any>(
    override val source: DataSource<DomainType>,
    override val scale: NonPositionalScale<DomainType, RangeType>
): SourceScaled<DomainType>
