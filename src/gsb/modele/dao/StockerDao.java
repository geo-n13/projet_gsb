package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visiteur;

public class StockerDao {
    
	public static Stocker rechercher(String depotLegal, String visiteur) {
        Stocker unStock = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCKER where MEDICAMENT='"+depotLegal+"' AND VISITEUR='"+visiteur+"'");
        try {
            if (reqSelection.next()) {
                unStock = new Stocker(reqSelection.getInt(1), VisiteurDao.rechercher(reqSelection.getString(2)), MedicamentDao.rechercher(reqSelection.getString(3)));
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from STOCKER where MEDICAMENT='"+depotLegal+"' AND VISITEUR='"+visiteur+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return unStock;
    }
	
	public static ArrayList<Stocker> listeStock(String visiteur) {
		ArrayList<Stocker> listeStock = new ArrayList<Stocker>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCKER where VISITEUR='"+visiteur+"'");
        try {
            while(reqSelection.next()) {
            	Stocker unStock = new Stocker(reqSelection.getInt(1), VisiteurDao.rechercher(reqSelection.getString(2)), MedicamentDao.rechercher(reqSelection.getString(3)));
                
            	listeStock.add(unStock);
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from STOCKER where MATRICULE='"+visiteur+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return listeStock;
	}
	
	public static int modifier(Stocker unStock) {
		String requeteModification;
		int quantite = unStock.getQteStock();
		Medicament medicament = unStock.getUnMedicament();
		Visiteur visiteur = unStock.getUnVisiteur();
		
		requeteModification = "UPDATE STOCKER SET QTESTOCK='"+quantite+"' WHERE MEDICAMENT='"+medicament.getDepotLegal()+"' AND VISITEUR='"+visiteur.getMatricule()+"'";
		int result = ConnexionMySql.execReqMaj(requeteModification);
		ConnexionMySql.fermerConnexionBd();
		return result;
	}
	
}
