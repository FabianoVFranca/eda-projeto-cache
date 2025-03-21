package lfu.linkedhash;

import cacheinterface.CacheAlgorithm;
import java.util.*;

public class LFUCache< K,V > implements CacheAlgorithm<K, V> {

    // Limita o tamanho do cache.
    private int capacity;

    // Armazenamento e a recuperação dos dados reais associados a uma chave.
    private Map <K, V> cache;

    // Quantas vezes cada chave foi acessada, objeto associado a frequencia.
    private Map <K, Integer> frequency;

    // frequencia associada ao linked que preserva a ordem de inserção, é possível verificar entre as frequencias, qual foi a ultima chave inserida 
    private Map <Integer, LinkedHashSet<K> > frequencyGroups;

    // a menor frequencia atual
    private int minFrequency;

    public LFUCache(int capacity){
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequency = new HashMap<>();
        this.frequencyGroups = new HashMap<>();
        this.minFrequency = 0;
    }

    @Override
    public V get(K key) {

        // verifica se o objeto existe no cache
        if (!cache.containsKey(key)) {
            return null;
        }
        updateFreq(key);

        return cache.get(key);

    }

    @Override
    public void put(K key,V Value) {
        if(capacity == 0) return;

        // se a chave existe eu preciso adicionar o novo valor associado a ela no cache.
        if(cache.containsKey(key)) {
            cache.put(key, Value);
            updateFreq(key);
            return;
        }
        if (cache.size() >= capacity) {
            eviction();
        }
        // adiciona o k,v no cache 
        cache.put(key,Value);

        // adiciona dentro das frequencia a quantas vezes a chave foi acessada
        frequency.put(key,1);

        // verifico se 1 existe no grupo de frequencia
        containsFreq(1,key);
        minFrequency = 1;

    }

    private void containsFreq(int i,K key) {
        
        if(!frequencyGroups.containsKey(i)) {
            frequencyGroups.put(i, new LinkedHashSet<K>());
        }
        frequencyGroups.get(i).add(key);
    }

    private void updateFreq(K key) {
        int freq =  frequency.get(key);
        
        // incremento o valor diretamente no hashMap de frequencia
        frequency.put(key, freq + 1);
        
        // remove do grupo de frequencia anterior a chave
        frequencyGroups.get(freq).remove(key);

        // se minha frequencia for a menor, e meu grupo de frequencia estiver vazio para a frequencia anterior 
        // eu adiciono 1 na menor frequencia e removo ela do grupo de frequencia
        if (freq == minFrequency && frequencyGroups.get(freq).isEmpty()) {
            frequencyGroups.remove(freq);
            minFrequency++;
        }
        containsFreq(freq + 1, key);
    }

    @Override
    public void eviction() {

        // pega o linkedHashSet associado a menor frequencia:
        LinkedHashSet<K> lowerFreqGrup = frequencyGroups.get(minFrequency);
        // leastFreqGroup

        // utiliza o linkedHashSet anterior para verificar qual foi o primeiro adicionado, não aceita acesso indexado
        K removeKey = lowerFreqGrup.iterator().next();

        lowerFreqGrup.remove(removeKey);
        
        if (lowerFreqGrup.isEmpty()){
            frequencyGroups.remove(minFrequency);

        }
        cache.remove(removeKey);
        frequency.remove(removeKey);
    }
}