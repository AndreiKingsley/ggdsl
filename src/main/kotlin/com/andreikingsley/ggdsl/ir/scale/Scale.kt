package com.andreikingsley.ggdsl.ir.scale

sealed interface Scale
sealed interface ContinuousScale: Scale
sealed interface CategoricalScale: Scale
