package com.andreikingsley.ggdsl.dsl.unit

import com.andreikingsley.ggdsl.dsl.scaled
import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.ir.data.DataSource
import com.andreikingsley.ggdsl.ir.scale.*
import com.andreikingsley.ggdsl.util.color.Color
import com.andreikingsley.ggdsl.util.symbol.Symbol
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SourceScaledTest {
    @Test
    fun testScaledUnspecified() {
        val ds = DataSource<Int>("ds1", typeOf<Int>())
        val scaledSource = ds.scaled()
        assertEquals(SourceScaledUnspecifiedDefault(ds), scaledSource)
    }

    @Test
    fun testScaledPositionalDefault() {
        val ds = DataSource<Double>("ds2", typeOf<Int>())
        val continuousScaledSource = ds.scaled(PositionalContinuousDefaultScale)
        val categoricalScaledSource = ds.scaled(PositionalCategoricalDefaultScale)
        assertEquals(
            SourceScaledPositionalDefault(ds, PositionalContinuousDefaultScale),
            continuousScaledSource
        )
        assertEquals(
            SourceScaledPositionalDefault(ds, PositionalCategoricalDefaultScale),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositionalDefault() {
        val ds = DataSource<Double>("ds3", typeOf<Int>())
        val continuousScaledSource = ds.scaled(NonPositionalContinuousDefaultScale)
        val categoricalScaledSource = ds.scaled(NonPositionalCategoricalDefaultScale)
        assertEquals(
            SourceScaledNonPositionalDefault(ds, NonPositionalContinuousDefaultScale),
            continuousScaledSource
        )
        assertEquals(
            SourceScaledNonPositionalDefault(ds, NonPositionalCategoricalDefaultScale),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledPositional() {
        val ds1 = DataSource<Float>("ds4", typeOf<Float>())
        val scale1 = PositionalContinuousScale(limits = 4.3F to 10F)
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            SourceScaledPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = DataSource<String>("ds10", typeOf<String>())
        val scale2 = PositionalCategoricalScale(listOf<String>())
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            SourceScaledPositional(ds2, scale2),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositional() {
        val ds1 = DataSource<Char>("dsX", typeOf<Char>())
        val scale1 = NonPositionalContinuousScale(
            'a' to 'e',
            Color.fromHex("#000000") to Color.fromName("red")
        )
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            SourceScaledNonPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = DataSource<String>("dsY", typeOf<String>())
        val scale2 = NonPositionalCategoricalScale<String, Symbol>(
            rangeValues = listOf(Symbol.CIRCLE, Symbol.TRIANGLE)
        )
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            SourceScaledNonPositional(ds2, scale2),
            categoricalScaledSource
        )
    }
}
