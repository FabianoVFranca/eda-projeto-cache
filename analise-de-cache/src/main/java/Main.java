import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cacheinterface.CacheAlgorithm;
import fifo.FIFOCache;
import lfu.dll.LFUCache;
import lru.LRUCache;

public class Main {
    public static void main(String[] args) {

        // Verifica a validade dos argumentos
        if (args.length < 2) {
            System.out.println("Uso: java Main <tipo_cache> <tamanho_cache>");
            return;
        }

        int hit = 0;
        int miss = 0;
        String cacheType = args[0].toUpperCase();  // Recebe da linha de comando os args
        int tamanhoCache = Integer.parseInt(args[1]);
        
        String traceFile = System.getProperty("user.dir") + "/dados/gen_seq_rand.txt";
        String outPutFile = System.getProperty("user.dir") + "/dados/dadosSaida2.txt";

        File file = new File(outPutFile);
        boolean isFileEmpty = !file.exists() || file.length() == 0;
        
        CacheAlgorithm<String, String> cache;
        switch (cacheType) {
            case "FIFO":
                cache = new FIFOCache<>(tamanhoCache);
                break;
            case "LFU":
                cache = new LFUCache<>(tamanhoCache);
                break;
            case "LRU":
                cache = new LRUCache<>(tamanhoCache);
                break;
            default:
                System.out.println("Tipo de cache inválido. Use FIFO, LFU ou LRU.");
                return;
        }
        
        CacheEvictionStrategy<String, String> strategy = new CacheEvictionStrategy<>(cache);

        try (BufferedReader reader = new BufferedReader(new FileReader(traceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outPutFile,true))) {
                    if (isFileEmpty)
                        writer.write("Tamanho do Cache | Total Hits | Total Misses | HitRatio\n");
            
            String line;
            while ((line = reader.readLine()) != null) {
               
                // Formato: timestamp, object_id, object_size
                String[] element = line.split(",");
                String objectId = element[1]; 
                String object = element[2]; 
        
                if (strategy.get(objectId))
                    hit++;
                else {
                    miss++; // Cache miss
                    strategy.put(objectId, object);
                }
            }

            double divide = hit + miss; 
            double hitRatio = (divide == 0) ? 0 : (double) hit / divide; 

            // Formatação do hitRatio para exibir como porcentagem
            String hitRatioFormatted = String.format("%.2f", hitRatio * 100); // Multiplica por 100 para exibir como porcentagem

            writer.write(tamanhoCache + "|" + hit + "|" + miss + "|" + hitRatioFormatted + "\n");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
