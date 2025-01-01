package xyz.darkcomet.cogwheel.aspects

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicReference

class AspectTest {
    
    @Test
    fun testAddAdvice_works() {
        val aspect = Aspect<Any>()
        val capturedInvocation = AtomicReference<Any>(null)
        
        aspect.addAdvice {
            capturedInvocation.set(it)
        }
        
        val args = "test"
        aspect.applyAdvices(args)
        
        assertEquals("test", capturedInvocation.get())
    }
    
    @Test
    fun testRemoveAdvice_works() {
        val aspect = Aspect<Any>()
        val capturedInvocation = AtomicReference<Any>(null)

        val advice = Advice<Any> { capturedInvocation.set(it) }
        
        aspect.addAdvice(advice)
        aspect.removeAdvice(advice)

        val args = "test"
        aspect.applyAdvices(args)

        assertEquals(null, capturedInvocation.get())
    }
}