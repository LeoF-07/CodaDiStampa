package coda_di_stampa;

import java.io.*;
import java.util.Scanner;

public class TesterCodaDiStampa {

    public static int NUMERO_FILE = 5;

    static PrintQueue printQueue;
    static File fileDiStampa;

    public static void inizializzaVariabili(){
        printQueue = new PrintQueue();
        fileDiStampa = creaFile("Print.txt");
    }

    public static void main(String[] args) {
        inizializzaVariabili();

        File[] files = creaFiles();
        riempiFiles(files);
        inserisciFilesNellaCoda(files);

        riempiFileDiStampa();
    }

    public static File[] creaFiles(){
        File[] files = new File[NUMERO_FILE];

        for(int i = 0; i < NUMERO_FILE; i++){
            String nomeFile = "file" + (i + 1) + ".txt";
            File file = creaFile(nomeFile);
            files[i] = file;
        }

        return files;
    }

    public static File creaFile(String nomeFile){
        File file = new File(nomeFile);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    public static void riempiFiles(File[] files){
        for(File file : files){
            riempiFile(file);
        }
    }

    public static void riempiFile(File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int numeroDiRighe = (int) (Math.random() * NUMERO_FILE + 1);

            for (int i = 0; i < numeroDiRighe; i++){
                bufferedWriter.write("Riga" + (i + 1) + "\n");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void inserisciFilesNellaCoda(File[] files){
        for (int i = 0; i < NUMERO_FILE; i++){
            inserisciFileNellaCoda(files);
        }
    }

    public static void inserisciFileNellaCoda(File[] files){
        int posizioneCasuale;

        do{
            posizioneCasuale = (int) (Math.random() * NUMERO_FILE);
        }while(files[posizioneCasuale] == null);

        String nomeFile = files[posizioneCasuale].getName();
        String contenutoFile = leggiFile(files[posizioneCasuale]);

        printQueue.enqueue(nomeFile + "\n" + contenutoFile); // nella prima riga metto il nome, nelle altre le righe del contenuto
        files[posizioneCasuale] = null;
    }

    public static void riempiFileDiStampa(){
        String contenutoFileDiStampa = "";

        while (printQueue.count() > 0){
            String file = printQueue.dequeue();

            Scanner scanner = new Scanner(file);
            String nomeFile = scanner.nextLine(); // nella prima riga ho messo il nome

            String contenuto = "";
            while(scanner.hasNext()){
                contenuto += scanner.nextLine() + "\n";
            }

            contenutoFileDiStampa += "*****INIZIO " + "<" + nomeFile + ">" + " *****" + "\n" + contenuto + "***** FINE *****" + "\n\n\n";
        }

        scriviSulFileDiStampa(contenutoFileDiStampa);
    }

    public static String leggiFile(File file){
        String contenutoFile = "";

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while(bufferedReader.ready()){
                contenutoFile += bufferedReader.readLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contenutoFile;
    }

    public static void scriviSulFileDiStampa(String contenuto){
        try {
            FileWriter fileWriter = new FileWriter(fileDiStampa);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(contenuto);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}