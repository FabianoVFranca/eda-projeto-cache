package lfu;

/**
 * Representa um nó utilizado na estrutura de cache LFU.
 * Armazena chave, valor, frequência de uso e referências para nós adjacentes.
 *
 * @param <K> Tipo da chave.
 * @param <V> Tipo do valor.
 */
public class Node<K, V> {

    K key;  // identificador do dado armazenado
    V value;  // dado armazenado
    int freq;  // frequencia de uso do nó

    Node<K, V> prev;  // informação sobre o nó anterior
    Node<K, V> next;  // informação sobre o próximo nó

    
    /**
     * Construtor do nó.
     *
     * @param key   Chave do item.
     * @param value Valor do item.
     */
    public Node(K key, V value){
        this.key = key;
        this.value = value;
        this.freq = 1;  // todo nó inicia com frequência de uso igual a 1
    }
}