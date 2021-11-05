package gsb.modele.dao;

import gsb.modele.Medecin;
import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visite;
import gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.util.ArrayList;

public class VisiteDao {

    public static Visite rechercher(String codeVisite) {
        Visite uneVisite = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITE where REFERENCE='"+codeVisite+"'");
        try {
            if (reqSelection.next()) {
                uneVisite = new Visite(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), MedecinDao.rechercher(reqSelection.getString(5)), VisiteurDao.rechercher(reqSelection.getString(4)));
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from VISITE where REFERENCE ='"+codeVisite+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return uneVisite;
    }
    
    public static ArrayList<Visite> listeVisites(String matricule, String date) {
        ArrayList<Visite> listeVisites = new ArrayList<Visite>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITE where MATRICULE='"+matricule+"' AND DATEVISITE='"+date+"'");
        try {
            while(reqSelection.next()) {
            	Visite uneVisite = new Visite(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), MedecinDao.rechercher(reqSelection.getString(5)), VisiteurDao.rechercher(reqSelection.getString(4)));
                
            	listeVisites.add(uneVisite);
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from VISITE where MATRICULE='"+matricule+"' AND DATEVISITE='"+date+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return listeVisites;
    }

    public static int creer(Visite uneVisite) {
        String requeteInsertion;
        int result = 0;
        String reference = uneVisite.getReference();
        String date = uneVisite.getDate();
        String commentaire = uneVisite.getCommentaire();
        Medecin unMedecin = uneVisite.getUnMedecin();
        Visiteur unVisiteur = uneVisite.getUnVisiteur();
        requeteInsertion = "insert into VISITE values('"+reference+"','"+date+"','"+commentaire+"','"+unVisiteur.getMatricule()+"','"+unMedecin.getCodeMed()+"')";
        try{
            result = ConnexionMySql.execReqMaj(requeteInsertion);
        }
        catch (Exception e){
            System.out.println("echec insertion : "+requeteInsertion);
        }
        ConnexionMySql.fermerConnexionBd();
        return result;
    }
    
    public static int modifier(Visite uneVisite) {
		String requeteModification;
		String reference = uneVisite.getReference();
		String commentaire = uneVisite.getCommentaire();
		
		requeteModification = "UPDATE VISITE SET COMMENTAIRE='"+commentaire+"' WHERE REFERENCE='"+reference+"'";
		int result = ConnexionMySql.execReqMaj(requeteModification);
		ConnexionMySql.fermerConnexionBd();
		return result;
	}
}
