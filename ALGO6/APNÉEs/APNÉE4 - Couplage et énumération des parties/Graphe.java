import java.util.*;
import java.io.*;

class Graphe {
    Maillon [] sommets;

    Graphe(InputStream in) throws Exception {
        lire(in);
    }

    int nombreSommets() {
        return sommets.length;
    }

    int nombreArcs() {
        int resultat = 0;

        for (int i=0; i<sommets.length; i++) {
            Maillon courant;

            courant = sommets[i];
            while (courant != null) {
                resultat ++;
                courant = courant.suivant;
            }
        }
        return resultat;
    }

    boolean existe(int source, int destination) {
        Maillon courant;

        courant = sommets[source];
        while (courant != null) {
            if (courant.arc.destination == destination)
                return true;
            courant = courant.suivant;
        }
        return false;
    }

    int [] successeurs(int source) {
        Maillon courant;
        int nombre = 0, indice = 0;
        int [] resultat;

        courant = sommets[source];
        while (courant != null) {
            nombre++;
            courant = courant.suivant;
        }
        resultat = new int[nombre];
        courant = sommets[source];
        while (courant != null) {
            resultat[indice++] = courant.arc.destination;
            courant = courant.suivant;
        }
        return resultat;
    }

    int degre() {
        int resultat=0;
        int [] degre_sommet;

        degre_sommet = new int[nombreSommets()];
        for (int i=0; i<nombreSommets(); i++)
            degre_sommet[i] = 0;

        for (int i=0; i<nombreSommets(); i++) {
            int [] succ;

            succ = successeurs(i);
            degre_sommet[i] += succ.length;
            for (int j=0; j<succ.length; j++)
                degre_sommet[succ[j]] += 1;
        }

        for (int i=0; i<nombreSommets(); i++)
            if (degre_sommet[i] > resultat)
                resultat = degre_sommet[i];

        return resultat;
    }

    boolean arcsIndependants() {
        return degre() <= 1;
    }

    boolean estCouplage() {
        return arcsIndependants() && (nombreSommets() == 2*nombreArcs());
    }

    void lire(InputStream in) throws Exception {
        int nombre_sommets;
        String specification_arc;
        String[] parties;
        int numero, source, destination;

        Scanner s = new Scanner(in);
        nombre_sommets = s.nextInt();
        sommets = new Maillon[nombre_sommets];

        while (s.hasNext()) {
            specification_arc = s.next();
            if (!specification_arc.matches("[0-9]+/[0-9]+\\+[0-9]+/"))
                throw new Exception("Arc mal forme : " + specification_arc);

            parties = specification_arc.split("/");
            numero = Integer.valueOf(parties[0]);

            parties = parties[1].split("\\+");
            source = Integer.valueOf(parties[0]) - 1;
            destination = Integer.valueOf(parties[1]) - 1;

            Maillon nouveau = new Maillon();
            nouveau.arc = new Arc(numero, source, destination);
            nouveau.suivant = null;

            if (sommets[source] == null) {
                sommets[source] = nouveau;
            } else {
                Maillon courant = sommets[source];
                while (courant.suivant != null)
                    courant = courant.suivant;
                courant.suivant = nouveau;
            }
        }
    }

    public String toString() {
        String resultat = sommets.length + "\n";

        for (int i=0; i<sommets.length; i++) {
            Maillon courant = sommets[i];
            while (courant != null) {
                resultat += courant.arc + "\n";
                courant = courant.suivant;
            }
        }

        return resultat;
    }
}
