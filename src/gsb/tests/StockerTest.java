package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visiteur;
import gsb.modele.utils.AffichageModele;

public class StockerTest {
    public static void main(String[] args) {
    	// Données d'exemples
    	Localite uneLocalite1 = new Localite("56270", "Ploemeur");
    	Visiteur unVisiteur1 = new Visiteur("a17", "Andre", "David", "dandre", "oppg5", "3 Rue des Potiers", uneLocalite1, "012345679", "14/10/2021", 0, "CD-012", "Nom Unité");
    	Medicament unMedicament = new Medicament("3YMC", "LE CORVEC", "Triamcoline", "Perte de poids", "Interdit aux hommes enceintes", 10.5f, "CTR", "Cortoïde");
    	Stocker unStock = new Stocker(25, unVisiteur1, unMedicament);
    	
    	// Appel de l'affichage de test
    	AffichageModele.afficherVisiteur(unVisiteur1);
    }
}
