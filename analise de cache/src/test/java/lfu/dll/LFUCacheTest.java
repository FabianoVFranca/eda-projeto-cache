package lfu.dll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class LFUCacheTest {
    private LFUCache<Integer, String> cache;

    @BeforeEach
    void setUp(){
        cache = new LFUCache<>(2);
    }

    @Test
    public void testBasicOperations() {

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D");

        // verifica se para valores com frequências iguais, a remoção respeita a ordem de inserção.
        assertNull(cache.get(1));
        assertNull(cache.get(2));

        assertEquals("C", cache.get(3)); // Chave 1 ainda deve estar presente.
        assertEquals("D", cache.get(4));

    }
    @Test
    public void TestNullOperations() {

        // verifica a existencia do elemento antes de adicioná-lo e depois se ele está no cache.
        assertNull(cache.get(1));
        cache.put(1,"A");
        assertEquals("A", cache.get(1));

        assertNull(cache.get(2));
        cache.put(2,"B");
        assertEquals("B", cache.get(2));

        assertNull(cache.get(3));
        cache.put(3,"C");
        assertEquals("C", cache.get(3));

    }

    @Test
    public void testLFURemove() {
        LFUCache<Integer, String> cache = new LFUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        // aumento de frequencias.
        cache.get(1);
        cache.get(2);
        cache.get(1);

        cache.put(4, "D");

        // chave 3 deve ter sido removida.
        assertNull(cache.get(3));

        // chaves que permaneceram.
        assertEquals("A", cache.get(1));
        assertEquals("B", cache.get(2));
        assertEquals("D", cache.get(4));
    }

    @Test
    public void testUpdateValue() {
        cache.put(1, "A");
        cache.put(2, "B");

        cache.put(1, "Updated A"); // atualiza o valor da chave 1.
        assertEquals("Updated A", cache.get(1));
    }

    @Test
    public void testSameFrequency() {
        LFUCache<Integer, String> cache = new LFUCache<>(2);
        cache.put(1, "A");
        cache.put(2, "B");

        cache.put(3, "C"); // como 1 e 2 têm a mesma frequência, o valor mais antigo (1) deve ser removido.

        assertNull(cache.get(1));
        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(3));
    }

    @Test
    public void testSingleElementCache() {
        LFUCache<Integer, String> cache = new LFUCache<>(1);
        cache.put(1, "A");
        cache.put(2, "B"); // deve remover 1 para inserir 2.

        assertNull(cache.get(1));
        assertEquals("B", cache.get(2));
    }
}
