package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.data.*
import kotlin.reflect.typeOf

/**
 * Returns a new [DataSource].
 *
 * @param T the type of source
 * @param id the name of source in [NamedData]
 */
inline fun <reified T: Any> source(id: String) : DataSource<T> =
    DataSource(id, typeOf<T>())
