package com.andreikingsley.ggdsl.dsl.unit

import com.andreikingsley.ggdsl.dsl.*
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.util.color.Color
import com.andreikingsley.ggdsl.util.linetype.LineType
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BindingTest {
    class TestContext() : BaseBindingContext() {
        override var data: MutableNamedData = mutableMapOf()
    }

    @Test
    fun testSetting() {
        val mockAesDouble = NonPositionalAes<Double>("mock_aes")
        val valueDouble = 34.831
        val context = TestContext().apply {
            mockAesDouble(valueDouble)
        }
        assertEquals<Map<Aes, Setting>>(
            mapOf(mockAesDouble to NonPositionalSetting(mockAesDouble, valueDouble)),
            context.bindingCollectorAccessor.settings.toMap()
        )

        val mockAesString = NonPositionalAes<String>("mock_aes_string")
        val valueString = "MOCK_VALUE"
        context.apply {
            mockAesString(valueString)
        }
        assertEquals<Map<Aes, Setting>>(
            mapOf(
                mockAesDouble to NonPositionalSetting(mockAesDouble, valueDouble),
                mockAesString to NonPositionalSetting(mockAesString, valueString)
            ),
            context.bindingCollectorAccessor.settings.toMap()
        )
    }

    @Test
    fun testMappingNonScalable() {
        val mockAes = NonScalablePositionalAes("mock_aes")
        val mockSource = source<Double>("mock_source")
        val context = TestContext().apply {
            mockAes(mockSource)
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(mockAes to NonScalablePositionalMapping(mockAes, mockSource, typeOf<Double>())),
            context.bindingCollectorAccessor.mappings
        )
    }

    @Test
    fun testMappingUnscaled() {
        val mockAes = MappableNonPositionalAes<Int>("mock_aes")
        val mockSource = source<Int>("mock_source")
        val context = TestContext().apply {
            mockAes(mockSource)
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(mockAes to ScaledUnspecifiedDefaultMapping(mockAes, mockSource.scaled(), typeOf<Int>())),
            context.bindingCollectorAccessor.mappings
        )
    }

    @Test
    fun testMappingScaledUnspecified() {
        val mockAes = ScalablePositionalAes("mock_aes")
        val mockSource = source<String>("mock_source")
        val context = TestContext().apply {
            mockAes(mockSource.scaled())
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(mockAes to ScaledUnspecifiedDefaultMapping(mockAes, mockSource.scaled(), typeOf<String>())),
            context.bindingCollectorAccessor.mappings
        )
    }

    @Test
    fun testMappingScaledPositionalDefault() {
        val mockAes = ScalablePositionalAes("mock_aes")
        val mockSource = source<Float>("mock_source")
        val context = TestContext().apply {
            mockAes(mockSource.scaled(continuousPos()))
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                mockAes to ScaledPositionalDefaultMapping(
                    mockAes, mockSource.scaled(
                        continuousPos()
                    ), typeOf<Float>()
                )
            ),
            context.bindingCollectorAccessor.mappings
        )
    }

    @Test
    fun testMappingScaledNonPositionalDefault() {
        val mockAes = MappableNonPositionalAes<LineType>("mock_aes")
        val mockSource = source<String>("mock_source")
        val context = TestContext().apply {
            mockAes(mockSource.scaled(categorical()))
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                mockAes to ScaledNonPositionalDefaultMapping(
                    mockAes, mockSource.scaled(
                        categorical()
                    ), typeOf<String>()
                )
            ),
            context.bindingCollectorAccessor.mappings
        )
    }

    @Test
    fun testMappingScaledPositional() {
        val mockAes = ScalablePositionalAes("mock_aes")
        val mockSource = source<String>("mock_source")
        val scale = categoricalPos(
            categories = listOf("cat1", "cat2", "cat3")
        )
        val context = TestContext().apply {
            mockAes(mockSource.scaled(scale))
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                mockAes to ScaledPositionalMapping(mockAes, mockSource.scaled(scale), typeOf<String>())
            ),
            context.bindingCollectorAccessor.mappings
        )
    }

    @Test
    fun testMappingScaledNonPositional() {
        val mockAes = MappableNonPositionalAes<Color>("mock_aes")
        val mockSource = source<Int>("mock_source")
        val scale = continuous<Int, Color>(
            rangeLimits = Color.fromRGB(1, 1, 1) to Color.fromRGB(1, 100, 100)
        )
        val context = TestContext().apply {
            mockAes(mockSource.scaled(scale))
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                mockAes to ScaledNonPositionalMapping(
                    mockAes,
                    mockSource.scaled(scale),
                    typeOf<Int>(),
                    //typeOf<Color>()
                )
            ),
            context.bindingCollectorAccessor.mappings
        )
    }
}