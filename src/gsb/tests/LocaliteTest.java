package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.utils.AffichageModele;

public class LocaliteTest {
    public static void main(String[] args) {
    	// Données d'exemple
    	Localite uneLocalite = new Localite("56270", "Ploemeur");
    	AffichageModele.afficherLocalite(uneLocalite);
    }
}
