package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class VisiteurDao {

    public static Visiteur rechercher(String matricule)
    {
        Visiteur unVisiteur = null;
        String req = "SELECT * FROM VISITEUR WHERE MATRICULE = '" + matricule + "' LIMIT 1;";
        ResultSet resultat = ConnexionMySql.execReqSelection(req);

        try {
            if(resultat.next())
            {
                String nom = resultat.getString(2);
                String prenom = resultat.getString(3);
                String login = resultat.getString(4);
                String mdp = resultat.getString(5);
                String adresse = resultat.getString(6);
                String codePostal = resultat.getString(7);
                Localite laLocalite = LocaliteDao.rechercher(codePostal);
                String telephone = "3630";
                String dateEntree = resultat.getString(8);
                int prime = 0;
                String codeUnite = resultat.getString(9);
                String nomUnite = resultat.getString(10);

                unVisiteur = new Visiteur(matricule, nom, prenom, login, mdp, adresse, laLocalite, telephone, dateEntree, prime, codeUnite, nomUnite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public static TreeMap<String, Visiteur> recupList()
    {
        TreeMap<String, Visiteur> lesVisiteurs = new TreeMap<String, Visiteur>();
        String req = "SELECT * FROM VISITEUR;";
        ResultSet resultat = ConnexionMySql.execReqSelection(req);

        try
        {
            while(resultat.next())
            {
                String matricule = resultat.getString(1);
                String nom = resultat.getString(2);
                String prenom = resultat.getString(3);
                String login = resultat.getString(4);
                String mdp = resultat.getString(5);
                String adresse = resultat.getString(6);
                String codePostal = resultat.getString(7);
                Localite laLocalite = LocaliteDao.rechercher(codePostal);
                String telephone = "3630";
                String dateEntree = resultat.getString(8);
                int prime = 0;
                String codeUnite = resultat.getString(9);
                String nomUnite = resultat.getString(10);

                Visiteur unVisiteur = new Visiteur(matricule, nom, prenom, login, mdp, adresse, laLocalite, telephone, dateEntree, prime, codeUnite, nomUnite);
                lesVisiteurs.put(matricule, unVisiteur);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lesVisiteurs;
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
