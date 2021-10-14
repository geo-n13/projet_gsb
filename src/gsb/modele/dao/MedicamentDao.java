package gsb.modele.dao;

import gsb.modele.Medicament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentDao {

    public static Medicament rechercher(String codeMedicament) {
        Medicament unMedicament = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDICAMENT where MED_DEPOTLEGAL='"+codeMedicament+"'");
        try {
            if (reqSelection.next()) {
                unMedicament = new Medicament(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getFloat(6), reqSelection.getString(7), reqSelection.getString(8));
            }
        }
        catch (Exception e) {
            System.out.println("erreur reqSelection.next() pour la requête - select * from MEDICAMENT where MED_DEPOTLEGAL='"+codeMedicament+"'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return unMedicament;
    }

    public static int creer(Medicament unMedicament) {
        String requeteInsertion;
        int result = 0;
        String depotLegal = unMedicament.getDepotLegal();
        String nomCommercial = unMedicament.getNomCommercial();
        String composition = unMedicament.getComposition();
        String effets = unMedicament.getEffets();
        String contreIndication = unMedicament.getContreIndication();
        float prixEchantillon = unMedicament.getPrixEchantillon();
        String codeFamille = unMedicament.getCodeFamille();
        String libellefamille = unMedicament.getLibellefamille();
        requeteInsertion = "insert into MEDICAMENT values('"+depotLegal+"','"+nomCommercial+"','"+composition+"','"+effets+"','"+contreIndication+"','"+prixEchantillon+"','"+codeFamille+"','"+libellefamille+"')";
        try{
            result = ConnexionMySql.execReqMaj(requeteInsertion);
        }
        catch (Exception e){
            System.out.println("echec insertion : "+requeteInsertion);
        }
        ConnexionMySql.fermerConnexionBd();
        return result;
    }

    public static int supprimer(String unMedicament){
        String requeteSuppression = "delete from MEDICAMENT where MED_DEPOTLEGAL='"+unMedicament+"'";
        int result = ConnexionMySql.execReqMaj(requeteSuppression);
        ConnexionMySql.fermerConnexionBd();
        return result;
    }

    public static ArrayList<Medicament> retournerCollectionDesMedicaments(){
        ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select MED_DEPOTLEGAL from MEDICAMENT");
        try{
            while (reqSelection.next()) {
                String codeMedicament = reqSelection.getString(1);
                collectionDesMedicaments.add(MedicamentDao.rechercher(codeMedicament));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur retournerCollectionDesMedicaments()");
        }
        return collectionDesMedicaments;
    }


}
