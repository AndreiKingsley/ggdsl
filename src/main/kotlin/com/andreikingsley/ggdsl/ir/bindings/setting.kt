package com.andreikingsley.ggdsl.ir.bindings

import com.andreikingsley.ggdsl.ir.aes.NonPositionalAes

sealed interface Setting

data class NonPositionalSetting<T>(
    val aes: NonPositionalAes<T>,
    val value: T
): Setting
