package lru;

import cacheinterface.CacheAlgorithm;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    private CacheAlgorithm<Integer, String> cache;

    @BeforeEach
    void setUp(){
        cache = new LRUCache<>(2);
    }

    @Test
    public void testBasicOperations() {

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D");

        // verifica se o para voleres iguais remoção respeita a ordem de inserção .
        assertNull(cache.get(1));
        assertNull(cache.get(2));

        assertEquals("C", cache.get(3)); // Chave 1 ainda deve estar presente
        assertEquals("D", cache.get(4));

    }
    @Test
    public void TestNullOperations() {

        // verifica a existencia antes de adicionar e depois se está no cache;
        assertNull(cache.get(1));
        cache.put(1,"A");
        assertEquals("A", cache.get(1));

        assertNull(cache.get(2));
        cache.put(2,"B");
        assertEquals("B", cache.get(2));

        assertNull(cache.get(3));
        cache.put(3,"C");
        assertEquals("B", cache.get(2));

    }

    @Test
    public void testLFURemove() {
       LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        // aumento de frequencias
        cache.get(1);
        cache.get(2);
        cache.get(1);

        cache.put(4, "D");

        // Chave 3 deve ter sido removida
        assertNull(cache.get(3));

        // chaves que permaneceram
        assertEquals("A", cache.get(1));
        assertEquals("B", cache.get(2));
        assertEquals("D", cache.get(4));
    }

    @Test
    public void testUpdateValue() {
        cache.put(1, "A");
        cache.put(2, "B");

        cache.put(1, "Updated A"); // Atualiza o valor da chave 1
        assertEquals("Updated A", cache.get(1));
    }

    @Test
    public void testSameFrequency() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "A");
        cache.put(2, "B");

        cache.put(3, "C"); // Como 1 e 2 têm a mesma frequência, o mais antigo (1) deve ser removido

        assertNull(cache.get(1));
        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(3));
    }

    @Test
    public void testSingleElementCache() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.put(1, "A");
        cache.put(2, "B"); // Deve remover 1 para inserir 2

        assertNull(cache.get(1));
        assertEquals("B", cache.get(2));
    }
}