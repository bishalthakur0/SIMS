public class InMemoryCache<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Map<K, Long> expiryMap = new ConcurrentHashMap<>();
    private final long ttlInMillis = 300_000; // 5 minutes

    public void put(K key, V value) {
        cache.put(key, value);
        expiryMap.put(key, System.currentTimeMillis() + ttlInMillis);
    }

    public V get(K key) {
        if (expiryMap.containsKey(key) && expiryMap.get(key) < System.currentTimeMillis()) {
            cache.remove(key);
            expiryMap.remove(key);
            return null;
        }
        return cache.get(key);
    }
}
