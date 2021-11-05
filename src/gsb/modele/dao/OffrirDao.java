package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;

public class OffrirDao {
    
	public static ArrayList<Offrir> recherche(String reference) {
        ArrayList<Offrir> listeOffrir = new ArrayList<Offrir>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from OFFRIR where VISITE='"+reference+"'");
        try {
            while(reqSelection.next()) {
            	Offrir offre = new Offrir(MedicamentDao.rechercher(reqSelection.getString(1)), VisiteDao.rechercher(reqSelection.getString(2)), reqSelection.getInt(3));
                
            	listeOffrir.add(offre);
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from OFFRIR where VISITE='"+reference+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return listeOffrir;
    }
	
	public static int creer(Offrir uneOffre) {
        String requeteInsertion;
        int result = 0;
        Medicament medicament = uneOffre.getUnMedicament();
        Visite visite = uneOffre.getUneVisite();
        int quantite = uneOffre.getQteOfferte();
        requeteInsertion = "insert into OFFRIR values('"+medicament.getDepotLegal()+"','"+visite.getReference()+"','"+quantite+"')";
        try{
            result = ConnexionMySql.execReqMaj(requeteInsertion);
        }
        catch (Exception e){
            System.out.println("echec insertion : "+requeteInsertion);
        }
        ConnexionMySql.fermerConnexionBd();
        return result;
    }
	
	public static int modifier(Offrir uneOffre) {
		String requeteModification;
		int quantite = uneOffre.getQteOfferte();
		Medicament medicament = uneOffre.getUnMedicament();
		Visite visite = uneOffre.getUneVisite();
		
		requeteModification = "UPDATE OFFRIR SET QTEOFFERTE='"+quantite+"' WHERE MEDICAMENT='"+medicament.getDepotLegal()+"' AND VISITE='"+visite.getReference()+"'";
		int result = ConnexionMySql.execReqMaj(requeteModification);
		ConnexionMySql.fermerConnexionBd();
		return result;
	}
	
}
