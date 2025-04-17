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

## Explicação sobre a organização do experimento:
### Carga de trabalho
- A carga gerada para a análise está presente no diretório data:

  - SequentialWorkload.txt: contém a carga organizada de forma sequencial com tamanho de 150000 dados a serem armazenados.

  - RandomizedWorkload.txt: contém a mesma carga, mas com as linhas randomizadas a partir do comando shuf SequentialWorkload.txt.

  - SequentialWorkloadOutput.txt: corresponde aos resultados obtidos a partir da carga presente em gen_Seq.txt. Neste caso, o hitRatio é maior desde o início devido à formatação sequencial dos dados pela forma de criação da carga.

  - RandomizedWorkloadOutput.txt: corresponde aos resultados obtidos a partir da carga presente em RandomizedWorkload.txt. Este arquivo foi utilizado para a plotagem do gráfico, pois o comportamento da taxa de hitRatio pode ser interpretado de forma mais clara e consistente.
 
### Implementação
- Dentro do package de src temos os arquivos:

  - CacheAlgorithm: É a implementação da interface Cache para abstrair a lógica de evicção (CacheEviction) usada no main.

  - CacheEvictionStrategy: Responsável por ser chamado pela função main para calcular as métricas de miss e hit, independentemente do tipo de cache utilizado. com isto nao é necessário refatorar o main para cada estratégia.

  - Main: Recebe os dados de entrada, invoca o CacheEvictionStrategy e escreve os dados de saída em um novo arquivo, recebemos a chave e valor do dado armazenado para o cachePolicy.

  - Implementações dos algoritmos: Três implementações distintas que implementam a interface CacheAlgorithm sendo eles FIFO,LFU e LRU(a LRU foi implementada duas vezes, a escolhida para o experimento foi usada com dll).

  - Testes: a pasta conta com um conjunto de testes para validar as três implementações dos algoritmos modularizados para cada tipo.

## Rodando o Main
Primeiro, é nescessário digitar o seguinte comando a partir do root para estar dentro do diretório analise-de-cache antes de rodar o Main
```sh
cd analise-de-cache 
```
Em seguida, digite o comando abaixo na linha de terminal considerando tipo de cache: ```FIFO```, ```LFU``` ou ```LRU```:
```sh
gradle run --args="<Tipo cache> <Capacidade do cache>" 
```

## Plotando os gráficos

Para poder rodar o script feito, deve-se estar em graphics/script, logo, digite o seguinte comando no terminal a partir de root:
```sh
cd analise-de-cache/graphics/script
```
Em seguida, para plotar os gráficos use o comando:
```sh
python3 gen_graf.py & python3 gen_graf_foot.py
```
Os arquivos de imagens gerados serão direcionados para graphics/images no qual ficarão armazenados.

### Esse repositório está organizado da seguinte forma:
```txt
.
├── analise-de-cache
│   ├── data
│   │   ├── OutputFile.txt
│   │   ├── RandomizedWorkload.txt
│   │   ├── RandomizedWorkloadOutput.txt
│   │   ├── SequentialWorkload.txt
│   │   ├── SequentialWorkloadOutput.txt
│   │   └── WorkloadFootprint.txt
│   ├── graphics
│   │   ├── images
│   │   │   ├── all_cache_comparative_plot.png
│   │   │   ├── frequency_distribution_plot.png
│   │   │   ├── unit_fifo_plot.png
│   │   │   ├── unit_lfu_plot.png
│   │   │   └── unit_lru_plot.png
│   │   └── scripts
│   │       ├── gen_graf_foot.png
│   │       └── gen_graf.py
│   ├── src
│   │   └── main
│   │   │  └── java
│   │   │      ├── cacheinterface
│   │   │      │   └── CacheAlgorithm.java
│   │   │      ├── fifo
│   │   │      │   └── FIFOCache.java
│   │   │      ├── lfu
│   │   │      │   ├── DoublyLinkedList.java
│   │   │      │   ├── LFUCache.java
│   │   │      │   └── Node.java
│   │   │      ├── lru
│   │   │      │   ├── LRUCache.java
│   │   │      │   └── Node.java
│   │   │      ├── CacheEvictionStrategy.java
│   │   │      ├── FootPrintMeter.java
│   │   │      └── Main.java
│   │   └── test
│   │      └── java
│   │          ├── fifo
│   │          │   └── FIFOCacheTest.java
│   │          ├── lfu
│   │          │   └── LFUCacheTest.java
│   │          └── lru
│   │              └── LRUCacheTest.java
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
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
