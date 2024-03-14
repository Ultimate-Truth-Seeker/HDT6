import java.io.File;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

public class tests {
    public void main(String type) {
        Map<String, String> cards_desc;
        ;

        try (Scanner sc = new Scanner(new File("cards_desc.txt"))) {

            ;

            cards_desc = FactoryMap.getMap(type);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                cards_desc.put(line.substring(0, line.indexOf("|")), line.substring(line.indexOf("|") + 1));
            }
            
        } catch (Exception e) {
            System.err.println("* Error en la lectura del archivo");
            return;
        }

        // Opción 5
        for (String key : cards_desc.keySet()) {
            System.out.println("Nombre: " + key + "\nTipo:  " + cards_desc.get(key));
        }

        // Opción 6
        
        

    }

    @Test
    public void testsHash() {
        main("HashMap");
    }

    @Test 
    public void testTree() {
        main("TreeMap");
    }

    @Test
    public void testsLinkedHash() {
        main("LinkedHashMap");
    }
}
