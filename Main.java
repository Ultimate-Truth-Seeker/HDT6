import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

        //Implementacion del menu:
        boolean exit = false;
        while (!exit){
            System.out.println("Seleccione una opcion:\n1.Agregar una carta a la colección\n2.Mostrar el tipo de una carta específica\n3.Mostrar la colección del usuario\n4.Mostrar la colección del usuario ordenada por tipo\n5.Mostrar todas las cartas existentes\n6.Mostrar todas las cartas existentes ordenadas por tipo\n7.Salir");
            int opcion = s.nextInt();
            s.nextLine();
            switch (opcion){
                case 1:
                    // Opción 1: Agregar una carta a la colección del usuario
                    System.out.println("Ingrese el nombre de la carta que desea agregar:");
                    String cardName = s.nextLine();
                    if (cards_desc.containsKey(cardName)) {
                        userCollection.put(cardName, userCollection.getOrDefault(cardName, 0) + 1);
                        System.out.println("Carta agregada con éxito!");
                    } 
                        else {
                        System.out.println("Error: la carta no se encuentra entre las cartas disponibles.");
                    }
                    break;
                    // Opción 2: Mostrar el tipo de una carta específica
                    case 2:
                    System.out.println("Ingrese el nombre de la carta cuyo tipo desea ver:");
                    cardName = s.nextLine();
                    if (cards_desc.containsKey(cardName)) {
                        System.out.println("Tipo de la carta " + cardName + ": " + cards_desc.get(cardName));
                    } else {
                        System.out.println("Error: la carta no se encuentra entre las cartas disponibles.");
                    }
                    break;
                case 3:
                    // Opción 3: Mostrar el nombre, tipo y cantidad de cada carta que el usuario tiene en su colección
                    for (Map.Entry<String, Integer> entry : userCollection.entrySet()) {
                        System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + cards_desc.get(entry.getKey()) + ", Cantidad: " + entry.getValue());
                }
                    break;
                case 4:
                    // Ordenamos un mapa ordenado por tipo de carta
                    Map<String, Map<String, Integer>> sortedCollection = new TreeMap<>();
                    for (Map.Entry<String, Integer> entry : userCollection.entrySet()) {
                        String cardType = cards_desc.get(entry.getKey());
                        if (!sortedCollection.containsKey(cardType)) {
                            sortedCollection.put(cardType, new TreeMap<>());
                        }
                    sortedCollection.get(cardType).put(entry.getKey(), entry.getValue());
                }
                for (Map.Entry<String, Map<String, Integer>> entry : sortedCollection.entrySet()) {
                    System.out.println(entry.getKey() + ": ");
                    for (Map.Entry<String, Integer> cardEntry : entry.getValue().entrySet()) {
                        System.out.println("Nombre: " + cardEntry.getKey() + ", Cantidad: " + cardEntry.getValue()+ "\n");
                    }
                }
                    break;
                case 5:
                for (String key : cards_desc.keySet()) {
                    System.out.println("Nombre: " + key + "\nTipo:  " + cards_desc.get(key));
                }
                    break;
                case 6:
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
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no existente, selecciona otra opcion");
                    break;
            }
        }

        s.close();
       
    }
    
}