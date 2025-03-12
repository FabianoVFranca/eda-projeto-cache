import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fifo.Cache;
import fifo.FIFOCache;
import fifo.EvictionStrategy;


public class Main {
    private static int miss;
    private static int hit;

    public static void main(String[] args) {
        // Verifica se os argumentos tao certos
        if (args.length < 2) {
            System.out.println("Uso: java Main <tipo_cache> <tamanho_cache>");
            return;
        }

        //recebe da linha de comando os args        
        String cacheType = args[0].toUpperCase(); 
        int tamanhoCache = Integer.parseInt(args[1]);
        

        String traceFile = "/home/fabiano.victor.franca.araujo/EDA_LEDA/eda-projeto-cache/analise de cache/Dados/gen_sequence.txt";

        // Caminho para o arquivo de saída
        String outPutFile = "/home/fabiano.victor.franca.araujo/EDA_LEDA/eda-projeto-cache/analise de cache/Dados/dadosSaida.txt";


        // tem que implementar a interface do LFU e LRU
        Cache<String> cache;
        switch (cacheType) {
            case "FIFO":
                cache = new FIFOCache<>(tamanhoCache);
                break;
            
            default:
                System.out.println("Tipo de cache inválido. Use FIFO, LFU ou LRU.");
                return;
        }

        hit = 0;
        miss = 0;
        EvictionStrategy strategy = new EvictionStrategy<>(cache);


        try (BufferedReader reader = new BufferedReader(new FileReader(traceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outPutFile))) {

            // Escreve o cabeçalho no arquivo de saída
            writer.write("Element|TimeAdd|Resultado\n");
            
            String line;
            while ((line = reader.readLine()) != null) {
               
                // Formato: timestamp, object_id, object_size
                String[] element = line.split(",");
                String objectId = element[1]; 

                // Mede o tempo de adição do elemento ao cache
                long startAddElement = System.nanoTime();

                
                if (strategy.get(objectId)) {
                    hit++; 
                    //writer.write(objectId + "|" + (System.nanoTime() - startAddElement) + "|hit\n");
                } else {
                    miss++; // Cache miss
                    strategy.add(objectId); 
                    //writer.write(objectId + "|" + (System.nanoTime() - startAddElement) + "|miss\n");
                }
            }
            
            int hitRatio =  hit / (hit + miss);

            
            writer.write("\nTamanho do Cache | Total Hits|Total Misses|HitRatio\n");
            writer.write(tamanhoCache+"|" + hit +"|" + miss + "|" + hitRatio + "\n");

            // Escreve o conteúdo final do cache no arquivo de saída, nao sei se quero isso
            writer.write("\nCache Content:\n");
            // tem que printar o conteudo do cache
            writer.write(cacheType.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}