import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RechercheMotif {

    // Recherche du motif {motif} dans la chaîne {texte}
    static void recherche(String texte, String motif) {
	// indice de parcours du texte
	int i;
	// indice de parcours du motif
	int j;

	i = 0;
	j = 0;
	while ((i < texte.length() - motif.length() + 1)) {

	    // recherche du motif à l'indice i
	    j = 0;
	    while ((j < motif.length()) && (texte.charAt(i+j) == motif.charAt(j))) {
		// invariant : texte[i..i+j-1] = motif[0..j-1]
		j = j + 1;
	    }

	    if (j == motif.length()) {
		System.out.println(i);
	    }

	    i = i + 1;
	}
    }

    // Renvoie le hashcode de la sous-chaîne s[debut..fin]
    static int hash(String s, int debut, int fin) {
	int h = 0;
	for(int i = debut; i < fin; i++) {
	    h += (int)s.charAt(i);
	}
	return h;
    }

    // Renvoie le hashcode de la sous-chaîne s[debut..fin], à l'aide
    // de la valeur hash = hashcode(s,debut-1,fin-1)
    static int updateHash(String s, int hash, int debut, int fin) {
	// À COMPLÉTER
	return 0;
    }

    static void rechercheKR(String texte, String motif) {
	// indice de parcours du texte
	int i;
	// indice de parcours du motif
	int j;
	// valeurs de hachage du motif et des sous-chaînes
	int hmotif, hcourant;

	hmotif = hash(motif,0,motif.length());

	i = 0;
	j = 0;
	while ((i < texte.length() - motif.length() + 1)) {
	    // hachage de la sous-chaîne texte[i..i+m-1]
	    hcourant = hash(texte,i, i + motif.length());
	    if (hcourant == hmotif) {
		// recherche du motif à l'indice i
		j = 0;
		while ((j < motif.length()) && (texte.charAt(i+j) == motif.charAt(j))) {
		    // invariant : texte[i..i+j-1] = motif[0..j-1]
		    j = j + 1;
		}

		if (j == motif.length()) {
		    System.out.println(i);
		}
	    }
	    i = i + 1;
	}
    }

    public static void main(String args[]) {

	String texte;
	String motif;

	FileInputStream input;
	BufferedReader reader;

	for (int i = 0; i < args.length; i++) {
	    try {
		// Ouverture du fichier passé en argument
		input = new FileInputStream(args[i]);
		reader = new BufferedReader(new InputStreamReader(input));

		// Lecture de la chaîne
		texte = reader.readLine();
		// Lecture du motif
		motif = reader.readLine();

		// date de début
		long startTime = System.nanoTime();

		rechercheKR(texte,motif);

		// date de fin pour le calcul du temps écoulé
		long endTime = System.nanoTime();

		// Impression de la longueur du texte et du temps d'exécution
		System.out.println(texte.length() + "\t" + ((endTime - startTime)/1.0E9));

	    } catch (FileNotFoundException e) {
		System.err.println("Erreur lors de l'ouverture du fichier " + args[i]);
	    } catch (IOException e) {
		System.err.println("Erreur de lecture dans le fichier");
	    }
	}
    }
}
