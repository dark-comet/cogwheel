package xyz.darkcomet.cogwheel.framework.primitives

class MapBuilder<K, V> {
    
    private var map: MutableMap<K, V> = HashMap()
    
    val entries: Iterator<Map.Entry<K, V>>
        get() = map.entries.iterator()
    
    fun entry(key: K, value: V): MapBuilder<K, V> {
        map[key] = value
        return this
    }
    
    fun build(): Map<K, V> {
        return map
    }
    
}