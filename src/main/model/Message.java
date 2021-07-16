package main.model;

import main.tools.TransCoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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


        try {
            key = Files.readString(keyPath); // lire la clé
            transCoder = new TransCoder(key);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("la clé n'est pas trouvé !");
        }

        if (encoded) {
            try {
                msgEncoded = Files.readAllLines(msgEncodedPath); //lire le fichier à encoder
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String s:
                 msgEncoded) {//lisons les lignes
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
            try {
                msgClear = Files.readAllLines(msgClearPath); //lire le fichier à décoder
            } catch (IOException e) {
                e.printStackTrace();
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
    }
}