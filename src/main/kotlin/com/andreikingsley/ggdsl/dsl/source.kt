package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.data.DataSource
import kotlin.reflect.typeOf

inline fun <reified T: Any> source(id: String) =
    DataSource<T>(id, typeOf<T>())
