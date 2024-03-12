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
                String[] pair = sc.nextLine().split("|");
                cards_desc.put(pair[0], pair[1]);
            }
            
        } catch (Exception e) {
            System.err.println("* Error en la lectura del archivo");
            s.close();
            return;
        }

        System.out.println("Archivo cargado con éxito!");
        Map<String, String> userCollection = FactoryMap.getMap(type); // cartas del usuario

        //TODO: implement menu

        s.close();
    }
    
}