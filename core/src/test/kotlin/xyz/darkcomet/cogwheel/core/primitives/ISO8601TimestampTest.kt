package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ISO8601TimestampTest {
    
    @Test
    fun testFromFormattedString_EpochMillisecondsCorrect() {
        val timestamp = ISO8601Timestamp.fromFormattedString("2017-07-11T17:27:07.299000+00:00")
        assertEquals(1499794027299, timestamp.epochMilliseconds)
    }
    
    @Test
    fun testFromEpochMilliseconds_formattedStringCorrect() {
        val timestamp = ISO8601Timestamp.fromEpochMilliseconds(1499794027299)
        assertEquals("2017-07-11T17:27:07.299Z", timestamp.stringValue)
    }
    
    @Test
    fun testJsonSerialization_encodedValueCorrect() {
        val timestamp = ISO8601Timestamp.fromEpochMilliseconds(1499794027299)
        val encodedValue = Json.encodeToString(timestamp)
        assertEquals("\"2017-07-11T17:27:07.299Z\"", encodedValue)
    }
    
    @Test
    fun testJsonDeserialization_decodedValueCorrect() {
        val testDecodedMap = Json.decodeFromString<Map<String, ISO8601Timestamp>>(
            "{\"timestamp\": \"2017-07-11T17:27:07.299Z\"}"
        )
        
        val decodedObject = testDecodedMap["timestamp"]
        
        assertEquals("2017-07-11T17:27:07.299Z", decodedObject!!.stringValue)
        assertEquals(1499794027299, decodedObject.epochMilliseconds)
    }
    
}