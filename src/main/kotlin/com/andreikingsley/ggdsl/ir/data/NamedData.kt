package com.andreikingsley.ggdsl.ir.data

/**
 * Dataset in the form of [Map].
 * Keys are column names.
 * Values are columns of data.
 * All values lists must be the same size.
 */
typealias NamedData = Map<String, List<Any>>
