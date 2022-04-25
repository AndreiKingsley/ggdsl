package com.andreikingsley.ggdsl.ir.data

import kotlin.reflect.KType

// todo nullable?
class DataSource<out T: Any>(val id: String, val type: KType)
