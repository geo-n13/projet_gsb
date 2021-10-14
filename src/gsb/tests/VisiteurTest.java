package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Visiteur;
import gsb.modele.utils.AffichageModele;

public class VisiteurTest {
    public static void main(String[] args) {
    	Localite uneLocalite = new Localite("56270", "Ploemeur");
    	Visiteur unVisiteur = new Visiteur("a17", "Andre", "David", "dandre", "oppg5", "3 Rue des Potiers", uneLocalite, "012345679", "14/10/2021", 0, "CD-012", "Nom Unité");
    	
    	AffichageModele.afficherVisiteur(unVisiteur);
    }
}
