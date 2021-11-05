package gsb.modele.dao;

import gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisiteurDao {

    public static Visiteur rechercher(String codeVisiteur) {
        Visiteur unVisiteur = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITEUR where MATRICULE='"+codeVisiteur+"'");
        try {
            if (reqSelection.next()) {
                unVisiteur = new Visiteur(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getString(6), LocaliteDao.rechercher(reqSelection.getString(7)), "Pas en BDD", reqSelection.getString(8), 0, reqSelection.getString(9), reqSelection.getString(10));
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from VISITEUR where MATRICULE='"+codeVisiteur+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return unVisiteur;
    }
    
    public static ArrayList<Visiteur> retournerCollectionDesVisiteurs(){
        ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<Visiteur>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select MATRICULE from VISITEUR");
        try{
            while (reqSelection.next()) {
                String matriculeVisiteur = reqSelection.getString(1);
                collectionDesVisiteurs.add(VisiteurDao.rechercher(matriculeVisiteur));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur retournerCollectionDesVisiteurs()");
        }
        return collectionDesVisiteurs;
    }
}
