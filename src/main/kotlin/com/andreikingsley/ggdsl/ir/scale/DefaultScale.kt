package com.andreikingsley.ggdsl.ir.scale

/**
 * Interface of default scale. TODO behavior TODO rename to unspecified
 */
sealed interface DefaultScale: Scale

/**
 * Unspecified default scale is a
 */
object UnspecifiedDefaultScale: DefaultScale

/**
 * Interface of positional default scale
 */
sealed interface PositionalDefaultScale: DefaultScale
/**
 * Interface of non-positional default scale
 */
sealed interface NonPositionalDefaultScale: DefaultScale

/**
 * Positional continuous scale with an unspecified domain.
 */
object PositionalContinuousDefaultScale: PositionalDefaultScale, ContinuousScale
/**
 * Positional categorical scale with an unspecified domain.
 */
object PositionalCategoricalDefaultScale: PositionalDefaultScale, CategoricalScale

/**
 * Non-positional continuous scale with an unspecified domain and range.
 */
object NonPositionalContinuousDefaultScale: NonPositionalDefaultScale, ContinuousScale
/**
 * Non-positional categorical scale with an unspecified domain and range.
 */
object NonPositionalCategoricalDefaultScale: NonPositionalDefaultScale, CategoricalScale
