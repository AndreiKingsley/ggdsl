package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.data.NamedData

/**
 * Mutual version of [NamedData].
 */
typealias MutableNamedData = MutableMap<String, List<Any>>
