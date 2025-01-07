package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PermissionSetTest {
    
    @Test
    fun testAnd() {
        val permissions = Permission.CREATE_INSTANT_INVITE and Permission.KICK_MEMBERS
        assertEquals("3", permissions.toString())
    }

    @Test
    fun testContains() {
        val permissions = Permission.CREATE_INSTANT_INVITE and Permission.KICK_MEMBERS
        
        for (entry in Permission.entries) {
            if (entry == Permission.CREATE_INSTANT_INVITE || entry == Permission.KICK_MEMBERS) {
                assertTrue(permissions.contains(entry), "Does not contain $entry")
            } else {
                assertFalse(permissions.contains(entry), "Contains $entry")
            }
        }
    }

    @Test
    fun testSerialization_encodedValueIsCorrectString() {
        val permissions = Permission.CREATE_INSTANT_INVITE and Permission.KICK_MEMBERS and Permission.BAN_MEMBERS
        val serializedValue = Json.encodeToString(PermissionSet.serializer(), permissions)
        assertEquals("\"7\"", serializedValue)
    }
    
    @Test
    fun testDeserialization_fromNumber_isCorrect() {
        testDeserializationImpl("7")
    }
    
    @Test
    fun testDeserialization_fromDecimalString_isCorrect() {
        testDeserializationImpl("\"7\"")
    }

    private fun testDeserializationImpl(jsonValue: String) {
        val json = Json { isLenient = true }
        val testDecodedMap = json.decodeFromString<Map<String, PermissionSet>>(
            "{\"permissions\": ${jsonValue}}"
        )

        val permissions = testDecodedMap["permissions"]

        assertNotNull(permissions)
        assertTrue(permissions!!.contains(Permission.CREATE_INSTANT_INVITE))
        assertTrue(permissions.contains(Permission.KICK_MEMBERS))
        assertTrue(permissions.contains(Permission.BAN_MEMBERS))

        for (entry in Permission.entries) {
            if (entry == Permission.CREATE_INSTANT_INVITE || entry == Permission.KICK_MEMBERS || entry == Permission.BAN_MEMBERS) {
                continue
            } else {
                assertFalse(permissions.contains(entry), "Contains $entry")
            }
        }
    }
}