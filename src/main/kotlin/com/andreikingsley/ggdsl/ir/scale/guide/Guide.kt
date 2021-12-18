package com.andreikingsley.ggdsl.ir.scale.guide

// TODO
sealed class Guide<RangeType> {
    var name: String? = null

    var breaks: List<RangeType>? = null
}

class Axis<RangeType>(): Guide<RangeType>()

class Legend<DomainType, RangeType>(): Guide<RangeType>()
