package fifo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cacheinterface.CacheAlgorithm;

public class FIFOCacheTest {

        private CacheAlgorithm<Integer, String> cache;

        @BeforeEach
        void setUp(){
            cache = new FIFOCache<>(2);
        }

        @Test
        public void testBasicOperations() {
            // Adiciona elementos ao cache
            cache.put(1, "A");
            cache.put(2, "B");
            cache.put(3, "C");// Remove 1 (FIFO)
            cache.put(4, "D");// Remove 2 (FIFO)

            // Verifica que as chaves 1 e 2 foram removidas
            assertNull(cache.get(1));
            assertNull(cache.get(2));

            // Verifica que as chaves 3 e 4 permanecem no cache
            assertEquals("C", cache.get(3));
            assertEquals("D", cache.get(4));

        }

        @Test
        public void testNullOperations() {

            // Verifica que as chaves não estão no cache inicialmente;
            assertNull(cache.get(1));
            assertNull(cache.get(2));

            // Adiciona valores ao cache
            cache.put(1, "A");
            cache.put(2, "B");

            // Confirma que os valores foram armazenados corretamente
            assertEquals("A", cache.get(1));
            assertEquals("B", cache.get(2));

            // Adiciona um terceiro item, removendo o primeiro (FIFO)
            cache.put(3, "C");

            // Chave 1 deve ter sido removida
            assertNull(cache.get(1));
            assertEquals("B", cache.get(2));
            assertEquals("C", cache.get(3));

        }
        @Test
        public void testFIFOEviction() {
            // Inicializa um cache FIFO com capacidade para 3 elementos
            cache = new FIFOCache<>(3); // Capacidade 3

            // Adiciona elementos ao cache
            cache.put(1, "A");
            cache.put(2, "B");
            cache.put(3, "C");

            cache.put(4, "D"); // Remove 1 (FIFO)

            // Chave 1 foi removida
            assertNull(cache.get(1));

            // Chaves restantes devem permanecer
            assertEquals("B", cache.get(2));
            assertEquals("C", cache.get(3));
            assertEquals("D", cache.get(4));
    }

        @Test
        public void testUpdateValue() {
            cache.put(1, "A");
            cache.put(2, "B");

            // Atualiza o valor da chave 1
            cache.put(1, "Updated A");

            // Confirma que o valor foi atualizado corretamente
            assertEquals("Updated A", cache.get(1));
            assertEquals("B", cache.get(2));

            // Adiciona um terceiro item, removendo o mais antigo (FIFO)
            cache.put(3, "C");

            // O cache deve remover 1, mesmo que tenha sido atualizado
            assertNull(cache.get(2));// Aqui espera-se que 1 seja removido, mas foi atualizado antes
            assertEquals("Updated A", cache.get(1));
            assertEquals("C", cache.get(3));
        }


    @Test
        public void testSameFrequency() {
            cache.put(1, "A");
            cache.put(2, "B");

            cache.put(3, "C");// Remove 1

            // Verifica remoção correta (FIFO)
            assertNull(cache.get(1));
            assertEquals("B", cache.get(2));
            assertEquals("C", cache.get(3));
        }

        @Test
        public void testSingleElementCache() {
            FIFOCache<Integer, String> cache = new FIFOCache<>(1);
            cache.put(1, "A");
            cache.put(2, "B"); // Deve remover 1 para inserir 2

            // Verifica a remoção de 1 e a manutenção de 2
            assertNull(cache.get(1));
            assertEquals("B", cache.get(2));
        }

}

