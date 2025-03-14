# Análise Comparativa de Políticas de Cache: Implementação e Avaliação de Eficiência

# Introdução
O cache é uma memória de rápido acesso, mas sua capacidade de armazenamento é limitada devido a restrições de custo. Quando é necessário remover um objeto do cache para adicionar um novo, a política de cache eviction define qual elemento será excluído, determinando a lógica de substituição dos dados armazenados.
Existem diversas políticas de cache, cada uma com regras específicas para decidir quais dados devem ser removidos. Este projeto tem como objetivo a implementação de três políticas de cache (FIFO, LFU e LRU) e a comparação de sua eficiência em um mesmo cenário.

## First In First Out (FIFO)
- FIFO, que significa "First In, First Out"  é uma política de substituição de cache na qual o primeiro item inserido no cache é o primeiro a ser removido.
- Funciona como uma fila: os dados são adicionados no final e removidos do início.

## Least Frequently Used (LFU)
- LFU, que significa "Least Frequently Used" é uma política de substituição de cache que remove o item menos recentemente acessado.
- Cada item no cache tem um contador que registra quantas vezes foi acessado.
- Quando o cache atinge sua capacidade, o item com a menor contagem de acessos é removido.

## Least Reacently Used (LRU)
- LRU, que significa "Least Recently Used", é uma ma política de substituição que remove o item menos recentemente acessado.
- Mantém um registro da ordem de acesso dos itens no cache.
- Quando um novo dado precisa ser armazenado, o item que não foi utilizado há mais tempo é removido.
- Essa abordagem é baseada na suposição de que os dados que foram utilizados recentemente têm uma maior probabilidade de serem acessados novamente em um futuro próximo.

  
Esse repositório está organizado da seguinte forma:

├── analise-de-cache
│   ├── dados
│   │   ├── dadosSaida.txt
│   │   ├── dadosSaida2.txt
│   │   ├── dadosSeqRand.txt
│   │   ├── gen_graficos.py
│   │   ├── gen_seq_rand.txt
│   │   └── gen_sequence.txt
│   ├── graficos
│   │   └── grafico_de_comparacao.png
│   ├── src
│   │   └── main
│   │   │  └── java
│   │   │      ├── cacheinterface
│   │   │      │   └── CacheAlgorithm.java
│   │   │      ├── fifo
│   │   │      │   └── FIFOCache.java
│   │   │      ├── lfu
│   │   │      │   ├── dll
│   │   │      │   │   ├── DoublyLinkedList.java
│   │   │      │   │   ├── LFUCache.java
│   │   │      │   │   └── Node.java
│   │   │      │   └── linkedhash
│   │   │      │       └── LFUCache.java
│   │   │      ├── lru
│   │   │      │   ├── LRUCache.java
│   │   │      │   └── Node.java
│   │   │      ├── Main.java
│   │   │      └── CacheEvictionStrategy.java
│   │   └── test
│   │      └── java
│   │          ├── fifo
│   │          │   └── FIFOCacheTest.java
│   │          ├── lfu
│   │          │   ├── dll
│   │          │   │   └── LFUCacheTest.java
│   │          │   └── linkedhash
│   │          │       └── LFUCacheTest.java
│   │          └── lru
│   │              └── LRUCacheTest.java
│   ├── .gitattributes
│   ├── build.gradle
│   ├── gradlew
│   ├── gradlew.bat
│   ├── projeto-cache.iml
│   └── settings.gradle.kts
├── .gitignore
├── README.md
└── eda-projeto-cache.iml
