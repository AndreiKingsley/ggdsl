package com.andreikingsley.ggdsl.ir.scale

/**
 * Scale base interface. Scale wraps a function that converts
 * a value from a data source into some geometric value (value of aesthetic attribute).
 */
sealed interface Scale

/**
 * Continuous scale interface. Continuous scale wraps a function
 * that acts from one segment to another.
 */
sealed interface ContinuousScale: Scale

/**
 * Categorical scale interface. Categorical scale wraps a function
 * that acts from one finite set to another.
 */
sealed interface CategoricalScale: Scale
