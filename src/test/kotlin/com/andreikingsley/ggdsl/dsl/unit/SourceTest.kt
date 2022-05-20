package com.andreikingsley.ggdsl.dsl.unit

import com.andreikingsley.ggdsl.dsl.source
import com.andreikingsley.ggdsl.ir.data.DataSource
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SourceTest {
    @Test
    fun testSource(){
        val id = "ID__X"
        val source = source<Int>(id)
        assertEquals(DataSource(id, typeOf<Int>()), source)
    }
}
