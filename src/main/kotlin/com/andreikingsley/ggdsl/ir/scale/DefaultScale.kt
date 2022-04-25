package com.andreikingsley.ggdsl.ir.scale

sealed interface DefaultScale: Scale

class UnspecifiedDefaultScale: DefaultScale
class ContinuousDefaultScale: DefaultScale, ContinuousScale
class CategoricalDefaultScale: DefaultScale, CategoricalScale
