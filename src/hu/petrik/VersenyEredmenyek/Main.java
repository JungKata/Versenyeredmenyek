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
            kiiras();
            System.out.println("Sportagak száma:" + getSportagakSzama());
            System.out.printf("Az olimpián %d versenyző vett részt:\n", getVersenyzokSzama());

        }catch (FileNotFoundException e){
            System.out.printf("Nem található a %s fajl", fajlNev);;
        }
    }

    private static int getVersenyzokSzama() {
        List<String> versenyzok = new ArrayList<>();
        for (Map.Entry<String, List<Eredmeny>> entry: sportagEredmenyek.entrySet()) {
            List<Eredmeny> eremenyek = entry.getValue();
            for (Eredmeny e:eremenyek) {
                if (!versenyzok.contains(e.getNev())){
                    versenyzok.add(e.getNev());
                }
            }
        }
        return versenyzok.size();
    }

    private static int getSportagakSzama(){
        return sportagEredmenyek.keySet().size();
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