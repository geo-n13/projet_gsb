package gsb.modele.dao;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;

import java.sql.ResultSet;

public class VisiteDao {

    public static Visite rechercher(String codeVisite) {
        Visite uneVisite = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITE where REFERENCE='"+codeVisite+"'");
        try {
            if (reqSelection.next()) {
                uneVisite = new Visite(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), MedecinDao.rechercher(reqSelection.getString(4)), VisiteurDao.rechercher(reqSelection.getString(5)));
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from VISITE where REFERENCE ='"+codeVisite+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return uneVisite;
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
}
