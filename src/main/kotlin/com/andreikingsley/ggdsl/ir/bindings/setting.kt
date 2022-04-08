package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.aes.NonPositionalAes
import kotlin.reflect.KType

sealed interface Setting

data class NonPositionalSetting<T: Any>(
    val aes: NonPositionalAes<T>,
    val value: T,
): Setting
