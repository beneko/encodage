package main.model;

import main.tools.TransCoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Message {

    private boolean encoded;
    List<String> msgClear = new ArrayList<>();
    List<String> msgEncoded = new ArrayList<>();
    private Path msgEncodedPath;
    private Path msgClearPath;
    private Path keyPath;
    private String key;
    private TransCoder transCoder;

    public Message(boolean b, Path key, Path msgClear, Path msgEncoded) {

        encoded = b;
        keyPath = key;
        msgEncodedPath = msgEncoded;
        msgClearPath = msgClear;

    }

    public void readNwrite() {

        //on peut tester si la clé existe
        if (Files.exists(keyPath))
        {
            try {
                key = Files.readString(keyPath); // lire la clé
                if (key != null) {
                    transCoder = new TransCoder(key);
                }else {
                    System.out.println("le fichier contenant la clé est vide !");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("la clé n'est pas lisible !");
            }
        }
        else
        {
            System.out.println("la clé n'existe pas");
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Entrez la nouvelle clé: ");
                key = sc.next();
                transCoder = new TransCoder(key);
                try {
                    Files.writeString(keyPath,key + System.lineSeparator(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


        if (encoded)
        {

            if (Files.exists(msgEncodedPath))
            {

                try {
                    msgEncoded = Files.readAllLines(msgEncodedPath); //lire le fichier à décoder
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                for (String s: msgEncoded) {//lisons les lignes
                        msgClear.add(transCoder.decode(s));
                }
                for (String s : msgClear) { //écrivons les lignes dans le fichier
                    try {
                        Files.writeString(msgClearPath,s + System.lineSeparator(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }

                System.out.println("le messsage a été bien décoder");
                System.out.println("Le message décodé se trouve: ");
                System.out.println(msgClearPath);

            }
            else
            {
                System.out.println("le fichier à décoder n'existe pas");
            }

        }
        else
        {
            //on peut tester si le fichier à encoder existe
            if (Files.exists(msgClearPath))
            {

                try {
                    msgClear = Files.readAllLines(msgClearPath); //lire le fichier à encoder
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                for (String s:
                        msgClear) {//lisons les lignes
                    msgEncoded.add(transCoder.encode(s));
                }
                for (String s : msgEncoded) { //écrivons les lignes dans le fichier
                    try {
                        Files.writeString(msgEncodedPath,s + System.lineSeparator(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }

                System.out.println("le messsage a été bien encoder");
                System.out.println("Le message encodé se trouve: ");
                System.out.println(msgEncodedPath);

            }
            else
            {
                System.out.println("le fichier à encoder n'existe pas");
            }
        }
    }
}
