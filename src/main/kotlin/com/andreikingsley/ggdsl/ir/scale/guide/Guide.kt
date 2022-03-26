package com.andreikingsley.ggdsl.ir.scale.guide

// TODO
sealed class Guide<RangeType> {
    // todo show
    var name: String? = null

    // todo labels

    // format???
    // TODO var breaks: List<RangeType>? = null
}

class Axis<RangeType>(): Guide<RangeType>()

class Legend<DomainType, RangeType>(): Guide<RangeType>()
