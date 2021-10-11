/*
 * Créé le 22 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * @author Isabelle
 * 22 févr. 2015
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class MedecinDao {

    public static Medecin rechercher(String codeMedecin){
        Medecin unMedecin=null;
        Localite uneLocalite= null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
        try {
            if (reqSelection.next()) {
                uneLocalite = LocaliteDao.rechercher(reqSelection.getString(5));
                unMedecin = new Medecin(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), uneLocalite, reqSelection.getString(6), reqSelection.getString(7), reqSelection.getString(8) );
            };
        }
        catch(Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return unMedecin;
    }

    public static ArrayList<Medecin> retournerCollectionDesMedecins(){
        ArrayList<Medecin> collectionDesMedecins = new ArrayList<Medecin>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
        try{
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);
                collectionDesMedecins.add(MedecinDao.rechercher(codeMedecin));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur retournerCollectionDesMedecins()");
        }
        return collectionDesMedecins;
    }

    public static HashMap<String,Medecin> retournerDictionnaireDesMedecins(){
        HashMap<String, Medecin> diccoDesMedecins = new HashMap<String, Medecin>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
        try{
            while (reqSelection.next()) {
                String codeMedecin = reqSelection.getString(1);
                diccoDesMedecins.put(codeMedecin, MedecinDao.rechercher(codeMedecin));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur retournerDiccoDesMedecins()");
        }
        return diccoDesMedecins;
    }

}