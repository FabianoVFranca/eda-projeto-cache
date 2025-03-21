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

## Explicação sobre organização do experimento:
- A carga gerada para a análise está presente em dois arquivos:

  - gen_Seq.txt: contém a carga organizada de forma sequencial com tamanho de 150000 dados a serem armazenados.

  - gen_seq_rand.txt: contém a mesma carga, mas com as linhas randomizadas a partir do comando shuf gen_Seq.txt gen_seq_rand.txt.

  - dadosSaida.txt: corresponde aos resultados obtidos a partir da carga presente em gen_Seq.txt. Neste caso, o hitRatio é maior desde o início devido à formatação sequencial dos dados pela forma de criação da carga.

  - dadosSaida2.txt: corresponde aos resultados obtidos a partir da carga presente em gen_seq_rand.txt. Este arquivo foi utilizado para a plotagem do gráfico, pois o comportamento da taxa de hitRatio pode ser interpretado de forma mais clara e consistente.
 
- Dentro do package de src temos os arquivos:

  - CacheAlgorithm: É a implementação da interface Cache para abstrair a lógica de evicção (CacheEviction) usada no main.

  - CacheEvictionStrategy: Responsável por ser chamado pela função main para calcular as métricas de miss e hit, independentemente do tipo de cache utilizado. com isto nao é necessário refatorar o main para cada estratégia.

  - Main: Recebe os dados de entrada, invoca o CacheEvictionStrategy e escreve os dados de saída em um novo arquivo, recebemos a chave e valor do dado armazenado para o cachePolicy.

  - Implementações dos algoritmos: Três implementações distintas que implementam a interface CacheAlgorithm sendo eles FIFO,LFU e LRU(a LRU foi implementada duas vezes, a escolhida para o experimento foi usada com dll).

  - Testes: a pasta conta com um conjunto de testes para validar as três implementações dos algoritmos modularizados para cada tipo.

- Plotagem de gráfico
 
  - O gráfico gerado está salvo no diretório gráficos com o nome gráfico_de_comparação, nele temos a representação no eixo X do HitRatio de cada política em porcentagem, no eixo X temos o tamanho do cache usado para implementar cada tipo de cache.
  
### Esse repositório está organizado da seguinte forma:
```txt
.
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
```
