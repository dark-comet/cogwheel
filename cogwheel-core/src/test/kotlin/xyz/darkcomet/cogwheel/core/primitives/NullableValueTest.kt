package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NullableValueTest {
    
    @Test
    fun testSerialize_nullValueWrapper_nothingSerialized() {
        val obj = TestDataObject<Int>()
        
        assertEquals("{}", Json.encodeToString(TestDataObject.serializer(Int.serializer()), obj))
    }
    
    @Test
    fun testSerialize_nonNullValueWrapper_nullValue_valueSerializedAsNull() {
        val obj = TestDataObject<Int>(property = NullableValue(null))

        assertEquals("""{"property":null}""", Json.encodeToString(TestDataObject.serializer(Int.serializer()), obj))
    }
    
    @Test
    fun testSerialize_nonNullValueWrapper_nonNullValue_primitive_valueSerialized() {
        val byteObj = TestDataObject(property = NullableValue(1.toByte()))
        assertEquals("""{"property":1}""", Json.encodeToString(TestDataObject.serializer(Byte.serializer()), byteObj))

        val shortObj = TestDataObject(property = NullableValue(2.toShort()))
        assertEquals("""{"property":2}""", Json.encodeToString(TestDataObject.serializer(Short.serializer()), shortObj))

        val intObj = TestDataObject(property = NullableValue(3))
        assertEquals("""{"property":3}""", Json.encodeToString(TestDataObject.serializer(Int.serializer()), intObj))

        val longObj = TestDataObject(property = NullableValue(4.toLong()))
        assertEquals("""{"property":4}""", Json.encodeToString(TestDataObject.serializer(Long.serializer()), longObj))

        val booleanObj = TestDataObject(property = NullableValue(true))
        assertEquals("""{"property":true}""", Json.encodeToString(TestDataObject.serializer(Boolean.serializer()), booleanObj))

        val stringObj = TestDataObject(property = NullableValue("test"))
        assertEquals("""{"property":"test"}""", Json.encodeToString(TestDataObject.serializer(String.serializer()), stringObj))

        val floatObj = TestDataObject(property = NullableValue(1.5f))
        assertEquals("""{"property":1.5}""", Json.encodeToString(TestDataObject.serializer(Float.serializer()), floatObj))

        val doubleObj = TestDataObject(property = NullableValue(2.5))
        assertEquals("""{"property":2.5}""", Json.encodeToString(TestDataObject.serializer(Double.serializer()), doubleObj))
    }
    
    @Test
    fun testSerialize_nonNullValueWrapper_nonNullValue_objectValue_objectSerialized() {
        val fooObj = TestDataObject(property = NullableValue(Foo("test")))
        assertEquals("""{"property":{"bar":"test"}}""", Json.encodeToString(TestDataObject.serializer(Foo.serializer()), fooObj))
    }
    
    @Test
    fun testSerialize_nonNullValueWrapper_notNullValue_objectValueWithCustomSerializer_objectSerialized() {
        val fooWithCustomSerializerObj = TestDataObject(property = NullableValue(FooWithCustomSerializer("test2")))
        assertEquals("""{"property":":test2:"}""", Json.encodeToString(TestDataObject.serializer(FooWithCustomSerializer.serializer()), fooWithCustomSerializerObj))
    }
    
    @Test
    fun testSerialize_customSerialNameValueWrapper_objectSerialized() {
        val fooObj = TestDataObjectWithSerialName(property = NullableValue(Foo("test3")))
        assertEquals("""{"renamed_property":{"bar":"test3"}}""", Json.encodeToString(TestDataObjectWithSerialName.serializer(Foo.serializer()), fooObj))
    }
    
    @Test
    fun testDeserialize_jsonFieldAbsent_nullValueWrapper() {
        val obj = Json.decodeFromString<TestDataObject<Int>>("""{}""")
        
        assertNotNull(obj)
        assertEquals(null, obj.property)
    }
    
    @Test
    fun testDeserialize_jsonFieldPresent_nullValue_valueWrapperExistsWithNullValue() {
        val obj = Json.decodeFromString<TestDataObject<Int>>("""{"property": 3}""")

        assertNotNull(obj)
        assertNotNull(obj.property)
        assertEquals(3, obj.property!!.value)
    }
    
    @Test
    fun testDeserialize_jsonFieldPresent_nonNullValue_primitive_valueWrapperValueCorrect() {
        val byteObj = Json.decodeFromString<TestDataObject<Byte>>("""{"property": 1}""")
        assertNotNull(byteObj)
        assertNotNull(byteObj.property)
        assertEquals(1.toByte(), byteObj.property!!.value)

        val shortObj = Json.decodeFromString<TestDataObject<Short>>("""{"property": 2}""")
        assertNotNull(shortObj)
        assertNotNull(shortObj.property)
        assertEquals(2.toShort(), shortObj.property!!.value)
        
        val intObj = Json.decodeFromString<TestDataObject<Int>>("""{"property": 3}""")
        assertNotNull(intObj)
        assertNotNull(intObj.property)
        assertEquals(3, intObj.property!!.value)

        val longObj = Json.decodeFromString<TestDataObject<Long>>("""{"property": 4}""")
        assertNotNull(longObj)
        assertNotNull(longObj.property)
        assertEquals(4.toLong(), longObj.property!!.value)
        
        val booleanObj = Json.decodeFromString<TestDataObject<Boolean>>("""{"property": true}""")
        assertNotNull(booleanObj)
        assertNotNull(booleanObj.property)
        assertEquals(true, booleanObj.property!!.value)
        
        val stringObj = Json.decodeFromString<TestDataObject<String>>("""{"property": "test"}""")
        assertNotNull(stringObj)
        assertNotNull(stringObj.property)
        assertEquals("test", stringObj.property!!.value)
        
        val floatObj = Json.decodeFromString<TestDataObject<Float>>("""{"property": 1.5}""")
        assertNotNull(floatObj)
        assertNotNull(floatObj.property)
        assertEquals(1.5f, floatObj.property!!.value)
        
        val doubleObj = Json.decodeFromString<TestDataObject<Double>>("""{"property": 2.5}""")
        assertNotNull(doubleObj)
        assertNotNull(doubleObj.property)
        assertEquals(2.5, doubleObj.property!!.value)
    }
    
    @Test
    fun testDeserialize_jsonFieldPresent_nonNullValue_objectValue_objectValueDeserialized() {
        val fooObj = Json.decodeFromString<TestDataObject<Foo>>("""{"property": { "bar": "test" }}""")
        assertNotNull(fooObj)
        assertNotNull(fooObj.property)
        assertNotNull(fooObj.property!!.value)
        assertEquals("test", fooObj.property.value!!.bar)
    }
    
    @Test
    fun testDeserialize_jsonFieldPresent_notNullValue_objectValueWithCustomSerializer_objectValueDeserialized() {
        val fooObj = Json.decodeFromString<TestDataObject<FooWithCustomSerializer>>("""{"property": ":test2:" }""")
        assertNotNull(fooObj)
        assertNotNull(fooObj.property)
        assertNotNull(fooObj.property!!.value)
        assertEquals("test2", fooObj.property.value!!.bar)
    }

    @Test
    fun testDeserialize_jsonFieldPresent_notNullValue_objectValueWithCustomSerialName_objectValueDeserialized() {
        val fooObj = Json.decodeFromString<TestDataObjectWithSerialName<Foo>>("""{"renamed_property": { "bar": "test3" } }""")
        assertNotNull(fooObj)
        assertNotNull(fooObj.property)
        assertNotNull(fooObj.property!!.value)
        assertEquals("test3", fooObj.property.value!!.bar)
    }
    
    
    @Serializable
    private data class TestDataObject<T : Any>(val property: NullableValue<T>? = null)

    @Serializable
    private data class TestDataObjectWithSerialName<T : Any>(@SerialName("renamed_property") val property: NullableValue<T>? = null)
    
    @Serializable
    private data class Foo(val bar: String)
    
    @Serializable(with = FooWithCustomSerializer.Serializer::class) 
    private data class FooWithCustomSerializer(val bar: String) {
        
        object Serializer : KSerializer<FooWithCustomSerializer> {
            override val descriptor: SerialDescriptor
                get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): FooWithCustomSerializer {
                return FooWithCustomSerializer(decoder.decodeString().replace(":", ""))
            }

            override fun serialize(encoder: Encoder, value: FooWithCustomSerializer) {
                encoder.encodeString(":" + value.bar + ":")
            }
        }
        
    }
    
    private data class TestEntry<T : Any>(
        val serializer: KSerializer<TestDataObject<T>>, 
        val expectedData: TestDataObject<T>, 
        val expectedSerializedData: String
    )
}