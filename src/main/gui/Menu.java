package main.gui;

import main.model.Message;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void start() {
        String home = System.getProperty("user.dir");
        System.out.println(home);

        String header =
                "╔══════════════════════════════════════╗\n" +
                "║   System d'encodage et de décodage   ║\n" +
                "║             de messages              ║\n" +
                "╠══════════════════════════════════════╣\n" +
                "║       1) Décoder un message          ║\n" +
                "║       2) Encode un message           ║\n" +
                "║       3) Quitter                     ║\n" +
                "╚══════════════════════════════════════╝";


        int n = 0;
        Path msgClearPath;
        Path msgEncodedPath;
        Path keyPath;

        do {
            System.out.println(header);
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();

            switch (n) {
                case 1 -> {

                    System.out.println("Entrez le nom du fichier à décoder (sans extension)");
                    String encoded = scanner.next();
                    msgEncodedPath = Paths.get(home, encoded + ".txt");
                    System.out.println("Entrez le nom du fichier contenant la clé de décodage (sans extension)");
                    keyPath = Paths.get(home, scanner.next() + ".txt");
                    msgClearPath = Paths.get(home, encoded + "Decoded.txt");
                    Message message = new Message(true, keyPath, msgClearPath, msgEncodedPath);
                    message.readNwrite();
                }
                case 2 -> {

                    System.out.println("Entrez le nom du fichier à encoder (sans extension)");
                    String encode = scanner.next();
                    msgClearPath = Paths.get(home,  encode + ".txt");
                    System.out.println("Entrez le nom du fichier contenant la clé de décodage (sans extension)");
                    keyPath = Paths.get(home, scanner.next() + ".txt");
                    msgEncodedPath = Paths.get(home, encode + "Encoded.txt");
                    Message message = new Message(false, keyPath, msgClearPath, msgEncodedPath);
                    message.readNwrite();
                }
                default -> System.out.println("entrez invalide !!!");
            }
        } while (n != 3);
    }
}

