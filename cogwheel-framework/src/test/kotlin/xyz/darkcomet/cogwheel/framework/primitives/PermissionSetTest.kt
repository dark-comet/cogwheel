package xyz.darkcomet.cogwheel.framework.primitives

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
    fun testCreateFromString() {
        val permissionSet = PermissionSet.from("2112")
        assertTrue(permissionSet.contains(Permission.SEND_MESSAGES))
        assertTrue(permissionSet.contains(Permission.ADD_REACTIONS))
    }
}