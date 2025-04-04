import java.io.*;
import java.util.HashMap;
import java.util.Map;

//mudar nomes das variaveis e metodos , apenas mockup da logica
public class ArquivoTesteFrequencia {
    private Map<String, Integer> ElementCount;
    private Map<Integer, Integer> FrequencyCount;

    ArquivoTesteFrequencia() {
        ElementCount = new HashMap<String, Integer>();
        FrequencyCount = new HashMap<Integer, Integer>();
    }

    public void populateElementCount() {
        ElementCount = new HashMap<String, Integer>();

        String traceFile = new File("../../../dados/gen_seq_rand.txt").getAbsolutePath();
        String outPutFile = new File("../../../dados/dadosFootPrint.txt").getAbsolutePath();

        File file = new File(outPutFile);
        boolean isFileEmpty = !file.exists() || file.length() == 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(traceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outPutFile,true))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] element = line.split(",");
                String keyCarga = element[1];

                if(!ElementCount.containsKey(keyCarga)){
                    ElementCount.put(keyCarga,1);
                }else{
                    ElementCount.put(keyCarga,ElementCount.get(keyCarga) + 1);
                }

            }

            populateFrequencyCount();

            if(isFileEmpty){
                writer.write("Quantidade de vezes que um elemento se repete na carga | quantidade de elementos unicos que se repetem \n");

                for (int key : this.FrequencyCount.keySet()) {
                    writer.write( key + "|" + FrequencyCount.get(key) +"\n");

                }

                writer.write("Elementos Distintos\n");
                writer.write( ElementCount.size() + "\n");


            }



        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void populateFrequencyCount() {
        for (Integer v : ElementCount.values()) {
            if (!FrequencyCount.containsKey(v)) {
                FrequencyCount.put(v, 1); // Alterado para iniciar com 1 em vez de 0
            } else {
                FrequencyCount.put(v, FrequencyCount.get(v) + 1);
            }
        }
    }
}
