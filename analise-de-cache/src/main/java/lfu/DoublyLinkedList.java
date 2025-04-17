package lfu;

/**
 * Lista duplamente encadeada utilizada para armazenar nós com a mesma frequência de uso.
 * Suporta operações eficientes de inserção e remoção.
 *
 * @param <K> Tipo da chave.
 * @param <V> Tipo do valor.
 */
public class DoublyLinkedList<K, V> {
    
    Node<K, V> head, tail;
    
    /**
     * Construtor da lista.
     * Inicializa com nós sentinela head e tail conectados entre si.
     */
    public DoublyLinkedList() {
        this.head = new Node<>(null, null);  // Dummy head
        this.tail = new Node<>(null, null);  // Dummy tail
        this.head.next = tail;
        this.tail.prev = head;
    }

    /**
     * Adiciona um nó na frente da lista (logo após o head).
     *
     * @param node Nó a ser adicionado.
     */
    public void addToFront(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Remove um nó da lista.
     *
     * @param node Nó a ser removido.
     */
    public void remove(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Remove e retorna o último nó da lista (o menos recentemente usado).
     *
     * @return O último nó, ou null se a lista estiver vazia.
     */
    public Node<K, V> removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<K, V> last = this.tail.prev;
        remove(last);
        return last;
    }

    /**
     * Verifica se a lista está vazia (sem nós reais entre head e tail).
     *
     * @return true se estiver vazia, false caso contrário.
     */
    public boolean isEmpty() {
        return this.head.next == this.tail;
    }
}