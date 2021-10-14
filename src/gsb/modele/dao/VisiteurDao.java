package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medicament;
import gsb.modele.Visiteur;

import java.sql.ResultSet;

public class VisiteurDao {

    public static Visiteur rechercher(String codeVisiteur) {
        Visiteur unVisiteur = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITEUR where MATRICULE='"+codeVisiteur+"'");
        try {
            if (reqSelection.next()) {
                unVisiteur = new Visiteur(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getString(6), LocaliteDao.rechercher(reqSelection.getString(7)), reqSelection.getString(8), reqSelection.getString(9), reqSelection.getInt(10), reqSelection.getString(11), reqSelection.getString(12));
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from VISITEUR where MATRICULE='"+codeVisiteur+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return unVisiteur;
    }
}
