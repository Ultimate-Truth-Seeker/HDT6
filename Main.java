import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Map<String, String> cards_desc;
        String type;

        try (Scanner sc = new Scanner(new File("cards_desc.txt"))) {

            System.out.println("Archivo leído con éxito. Seleccione la implementación Map a usar:\n1.HashMap\n2.TreeMap\n3.LinkedHashMap");
            int option;
            do {
                option = s.nextInt();
            } while (option < 1 || option > 3);
            s.nextLine();

            switch (option) {
                case 1:
                    type = "HashMap";
                    break;
                case 2:
                    type = "TreeMap";
                    break;
                default:
                    type = "LinkedHashMap";
                    break;
            }

            cards_desc = FactoryMap.getMap(type);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                cards_desc.put(line.substring(0, line.indexOf("|")), line.substring(line.indexOf("|") + 1));
            }
            
        } catch (Exception e) {
            System.err.println("* Error en la lectura del archivo");
            s.close();
            return;
        }

        System.out.println("Archivo cargado con éxito!");
        Map<String, Integer> userCollection = FactoryMap.getMap(type); // cartas del usuario

        // Opción 5
        for (String key : cards_desc.keySet()) {
            System.out.println("Nombre: " + key + "\nTipo:  " + cards_desc.get(key));
        }

        // Opción 6
        Map<String, Map<String, String>> tipos = FactoryMap.getMap(type);
        for (String card : cards_desc.keySet()) {
            if (!tipos.containsKey(cards_desc.get(card))) {
                tipos.put(cards_desc.get(card), FactoryMap.getMap(type));
            }
            tipos.get(cards_desc.get(card)).put(card, card);
        }
        for (String tp : tipos.keySet()) {
            System.out.println(tp + ": ");
            for (String cd : tipos.get(tp).keySet()) {
                System.out.println("\t" + cd);
            }
        }
        
        s.close();
    }
    
}