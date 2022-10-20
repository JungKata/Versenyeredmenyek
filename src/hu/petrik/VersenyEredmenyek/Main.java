package hu.petrik.VersenyEredmenyek;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private  static Map<String, List<Eredmeny>> sportagEredmenyek = new HashMap<>();

    public static void main(String[] args) {
        String fajlNev = "eredmenyek.txt";
        try {
            beolvas(fajlNev);
        }catch (FileNotFoundException e){
            System.out.printf("Nem található a %s fajl", fajlNev);;
        }

        System.out.println();
        kiiras();
    }

    private static void kiiras(){
        for (Map.Entry<String, List<Eredmeny>> entry : sportagEredmenyek.entrySet()){
            String sportag = entry.getKey();
            List<Eredmeny> eredmenyek = entry.getValue();
            System.out.println(sportag +":");

            for (Eredmeny eredmeny:eredmenyek) {
                System.out.println("\t" + eredmeny);
            }
        }
    }

    private static void beolvas(String fajlnev) throws FileNotFoundException{
        Scanner file = new Scanner(new File(fajlnev));
        while (file.hasNext()) {
            String[] sor = file.nextLine().split(" ");
            String sportag = sor[0];
            String reszIdo = sor[1];
            String nev = sor[2] + " " + sor[3];
            sportagEredmenyek.putIfAbsent(sportag, new ArrayList<>());
            Eredmeny eredmeny = new Eredmeny(reszIdo, nev);
            sportagEredmenyek.get(sportag).add(eredmeny);

        }
        file.close();
    }
}