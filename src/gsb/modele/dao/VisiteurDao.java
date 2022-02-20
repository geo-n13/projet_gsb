package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public static boolean ajouterVisiteur(Visiteur unVisiteur) {

        String requeteInsertion;
        int result = 0;
        String matricule = unVisiteur.getMatricule();
        String nom = unVisiteur.getNom();
        String prenom = unVisiteur.getPrenom();
        String login = unVisiteur.getLogin();
        String motdepasse = unVisiteur.getMdp();
        String telephone = unVisiteur.getTelephone();
        String adresse = unVisiteur.getAdresse();
        String localite = unVisiteur.getUneLocalite().getCodePostal();
        java.sql.Date dateEntree = new java.sql.Date(12);
        try {
            java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(unVisiteur.getDateEntree());
            dateEntree = new java.sql.Date(date.getTime());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        String codeunite = unVisiteur.getCodeUnite();
        String nomunite = unVisiteur.getNomUnite();
        requeteInsertion = "insert into VISITEUR values('"+matricule+"','"+nom+"','"+prenom+"','"+login+"','"+motdepasse+"','"+adresse+"','"+localite+"','"+dateEntree+"','"+codeunite+"','"+nomunite+"')";
        try{
            result = ConnexionMySql.execReqMaj(requeteInsertion);
        }
        catch (Exception e){
            System.out.println("echec insertion : "+requeteInsertion);
        }
        ConnexionMySql.fermerConnexionBd();
        return (result == 1);
    }
}
