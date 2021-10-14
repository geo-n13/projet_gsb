package gsb.modele.utils;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Stocker;
import gsb.modele.Visite;
import gsb.modele.Visiteur;

public class AffichageModele {
	// Méthode afficher Modèle
	public static void afficherVisiteur(Visiteur unVisiteur) {
		System.out.println("Matricule : "+unVisiteur.getMatricule());
		System.out.println("Nom : "+unVisiteur.getNom());
		System.out.println("Prénom : "+unVisiteur.getPrenom());
		System.out.println("Login : "+unVisiteur.getLogin());
		System.out.println("MDP : "+unVisiteur.getMdp());
		System.out.println("Adresse : "+unVisiteur.getAdresse());
		afficherLocalite(unVisiteur.getUneLocalite());
		System.out.println("Téléphone : "+unVisiteur.getTelephone());
		System.out.println("Date entrée : "+unVisiteur.getDateEntree());
		System.out.println("Prime : "+unVisiteur.getPrime());
		System.out.println("Code unité : "+unVisiteur.getCodeUnite());
		System.out.println("Nom unité : "+unVisiteur.getNomUnite());
	}
	
	// Méthode afficher Localité
	public static void afficherLocalite(Localite uneLocalite) {
		System.out.println("Code postal : "+uneLocalite.getCodePostal());
		System.out.println("Ville : "+uneLocalite.getVille());
	}
	
	// Méthode afficher Stocker
	public static void afficherStocker(Stocker unStock) {
		System.out.println(unStock.getQteStock());
		afficherVisiteur(unStock.getUnVisiteur());
		afficherMedicament(unStock.getUnMedicament());
		
	}
	
	// Méthode afficher Médicament
	public static void afficherMedicament(Medicament unMedicament) {
		System.out.println("Dépot légal : "+unMedicament.getDepotLegal());
		System.out.println("Nom commercial : "+unMedicament.getNomCommercial());
		System.out.println("Composition :"+unMedicament.getComposition());
		System.out.println("Effets : "+unMedicament.getEffets());
		System.out.println("Contre indication : "+unMedicament.getContreIndication());
		System.out.println("Prix échantillon : "+unMedicament.getPrixEchantillon());
		System.out.println("Code famille : "+unMedicament.getCodeFamille());
		System.out.println("Libellé : "+unMedicament.getLibellefamille());
	}
	
	// Méthode afficher Médecin
	public static void afficherMedecin(Medecin unMedecin) {
		System.out.println("Code médecin : "+unMedecin.getCodeMed());
		System.out.println("Nom : "+unMedecin.getNom());
		System.out.println("Prénom : "+unMedecin.getPrenom());
		System.out.println("Adresse : "+unMedecin.getAdresse());
		afficherLocalite(unMedecin.getLaLocalite());
		System.out.println("Téléphone : "+unMedecin.getTelephone());
		System.out.println("Potentiel : "+unMedecin.getPotentiel());
		System.out.println("Spécialité : "+unMedecin.getSpecialite());
	}
	
	// Méthode afficher Visite
	public static void afficherVisite(Visite uneVisite) {
		System.out.println("Référence : "+uneVisite.getReference());
		System.out.println("Date : "+uneVisite.getDate());
		System.out.println("Commentaire : "+uneVisite.getCommentaire());
		afficherMedecin(uneVisite.getUnMedecin());
		afficherVisiteur(uneVisite.getUnVisiteur());
	}
	
	// Méthode afficher Offrir
	public static void afficherOffrir(Offrir uneOffre) {
		afficherMedicament(uneOffre.getUnMedicament());
		afficherVisite(uneOffre.getUneVisite());
		System.out.println("Quantité : "+uneOffre.getQteOfferte());
	}
}
