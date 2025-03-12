import fifo.FIFOCache;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// como ta baseado no java.Main do comeco do periodo talvez precise de mudanca
public class Main {
    
//    private int miss;
//    private int hit;
    public static void main(String[] args) {
        int hit = 0;
        int miss = 0;


        //ter 3 main um para cada politica ou colocar os 3 aqui?

        FIFOCache fifoCache = new FIFOCache();

        // Caminho para o arquivo de tráfego gerado pelo TRAGEN
        //preciso mudar o codigo para cada carga ou consigo botar para ler o diretorio
        String traceFile = "caminho dos OutPuts que a gente vai usar";


        try {

            //deixar System.in ou o caminho ja no arquivo?
            BufferedReader reader = new BufferedReader(new FileReader(traceFile));
            String line = "";
            //UMASS documentacao -> Each request in the trace is comma seperated list of timestamp, object_id, and object_size (in KB).
            System.out.println("timestamp object_id object_size");
            while ((line = reader.readLine()) != null) {

                long startAddElement = System.nanoTime();
//                fifoCache.add(line);
                long endAddElement = System.nanoTime();

                long startContainsElement = System.nanoTime();
//                if(fifoCache.contains(line)){
//                    this.hit++;
//                }else{
//                    this.miss++;
//                };
                long endContainsElement = System.nanoTime();

                long timeAddElement = endAddElement - startAddElement;
                long timeContainsElement = endContainsElement -  startContainsElement;

                //Mudar isso, apenas teste de raciocinio
                //Guardar em um arquivo?
                //faz mais sentido o end estar fora do while acho
                System.out.println("tempo add Elemento : " + timeAddElement);
                System.out.println("tempo contains Elemento : " + timeContainsElement);
            }


//            System.out.println("Hit ratio: " + this.hit/(this.hit + this.miss));
            System.out.println("Conteúdo do Cache:\n" + fifoCache.toString());

            // botar taxa hit/miss?
            // avaliar a taxa de hit e miss a cada elemento ( faz sentido)?

        } catch (IOException ioe) {

        }
    }
}