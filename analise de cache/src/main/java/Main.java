package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.java.fifo.FIFOCache;
import main.java.lfu.LFUCache;
import main.java.lru.LRUCache;

// como ta baseado no Main do comeco do periodo talvez precise de mudanca
public class Main {
    private static int miss;
    private static int hit;
        public static void main(String[] args) {
            hit = 0;
            miss = 0;
        
        int tamanhoCache = 100;
        // isso daqui nao existe ta, vai ser so o da seu cache, quando for testar colocar o tamanho do array inicializado no parametro
        FIFOCache cache = new FIFOCache(tamanhoCache);
        LFUCache cache2 = new LFUCache();
        LRUCache cache3 = new LRUCache();

        // Caminho para o arquivo de tráfego gerado pelo TRAGEN
        //preciso mudar o codigo para cada carga ou consigo botar para ler o diretorio

        String traceFile = "eda-projeto-cache\\analise de cache\\Dados\\gen_sequence.txt";
        
        //lembrar de trocar o path pra cada projeto
        String outPutFile = "eda-projeto-cache\\analise de cache\\Dados\\dadosSaida.txt";

        try {

            //deixar System.in ou o caminho ja no arquivo?

            BufferedReader reader = new BufferedReader(new FileReader(traceFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPutFile));
            String line = "";
           
            //UMASS documentacao -> Each request in the trace is comma seperated list of timestamp, object_id, and object_size (in KB). 
            //ai pega so o segundo index que é o object_id
            
            writer.write("Element|TimeAdd");
            while ((line = reader.readLine()) != null) {

                String[] element = line.split(",");
                long startAddElement = System.nanoTime();
                
                writer.write(element[1]);
                
                if(cache.put(element[1])){
                    
                    hit++;
                    writer.write(" hit");
                } else {
                    
                    miss++;
                    writer.write(" miss");
                }

                long endAddElement = System.nanoTime();

                long timeAddElement = endAddElement - startAddElement;
               
                writer.write(timeAddElement + "\n");

            }

            //isso pode ficar em outro arquivo se pa, era mais facil so deixar em virugla ne filha
            writer.write("Total Hits|Total Misses|HitRatio| \n");
            int hitRatio = (hit)/(hit + miss);
            writer.write(hit+ "|" + miss + "|" + hitRatio + "\n");
            writer.write("Cache Content:\n" + cache.toString());



            // avaliar a taxa de hit e miss a cada elemento (faz sentido)?

        } catch (IOException ioe) {

        }
    }
}