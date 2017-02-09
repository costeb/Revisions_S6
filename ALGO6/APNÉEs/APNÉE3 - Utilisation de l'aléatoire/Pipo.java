import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Hashtable;


class Pipo {
    String f1; // the learning file
    Scanner in1; // the scanner associated to the file
    Hashtable<String,Hashtable<String,Integer>> LangModel; // the language model
    Random generator;
    
    Pipo(String f1) {
        this.f1 = f1;
        try {in1 = new Scanner(new FileInputStream(f1)); }
        catch (Exception e) {System.out.println(e);}
        LangModel = new  Hashtable<String,Hashtable<String,Integer>>();
        generator=new Random(); // Seed to be given... Eventually
    }
    
    public void newWorsSeq(String w1, String w2) {
        //System.out.println(" "+w1+"  "+w2+" ");
        // This is were you need to update the language model (hash of hashes)
    }
    
    public void Learn() {
        String word1;
        word1="."; // A ghost word beeing before the first word of the text
        try {
            while (in1.hasNext()) {
                String word2 = in1.next();
                if (word2.matches("(.*)[.,!?<>=+-/]")) {
                    // word2 is glued with a punctuation mark
                    String[] splitedWord= word2.split("(?=[.,!?<>=+-/])|(?<=])");
                    for (String s : splitedWord) {
                        newWorsSeq(word1,s); // update de language model
                        word1=s;
                    }
                } else { // word2 is a single word
                    newWorsSeq(word1,word2); // update de language model
                    word1=word2;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Talk(int nbWord) {
        // Taking advantage of the generative skills of the language model
        System.out.println("Compte rendu de l'apnée 3 algo 6 :");
        System.out.println();
        System.out.println("To be implemented !!");
        System.out.println("To be implemented !!");
        System.out.println();
        System.out.println("Fin du compte rendu");
    }
}