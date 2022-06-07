package com.andreikingsley.ggdsl.ir.data

import kotlin.reflect.KType

// todo nullable?
/**
 * A source of data, i.e. a pointer to a column with the corresponding name.
 *
 * @param T a type of data
 * @property id the name of source in a dataset
 * @property type reified type of data
 */
data class DataSource<T: Any>(val id: String, val type: KType)
