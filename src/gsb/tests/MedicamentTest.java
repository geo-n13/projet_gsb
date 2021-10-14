package gsb.tests;

import gsb.modele.Medicament;
import gsb.modele.utils.AffichageModele;

public class MedicamentTest {
    public static void main(String[] args) {
    	Medicament unMedicament = new Medicament("3YMC", "LE CORVEC", "Triamcoline", "Perte de poids", "Interdit aux hommes enceintes", 10.5f, "CTR", "Cortoïde");
    	
    	AffichageModele.afficherMedicament(unMedicament);
    }
}
