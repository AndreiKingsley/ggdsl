package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.aes.NonPositionalAes

/**
 * Setting base interface.
 */
sealed interface Setting

/**
 * Setting of a non-positional aesthetic attribute.
 *
 * @param T the type specifying a non-positional attribute
 * @property aes the non-positional aesthetic attribute to be set to
 * @property value the assigned value
 */
data class NonPositionalSetting<T: Any>(
    val aes: NonPositionalAes<T>,
    val value: T,
): Setting
