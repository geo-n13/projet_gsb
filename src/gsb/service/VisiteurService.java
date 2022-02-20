package gsb.service;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

public class VisiteurService {

    public static boolean AjoutVisiteur(Visiteur unVisiteur) {

        boolean isOk = false;

        try {
            if(unVisiteur.getPrenom().isEmpty() || unVisiteur.getNom().isEmpty())
                throw new Exception("Aucun champ ne peut être vide");

            isOk = VisiteurDao.ajouterVisiteur(unVisiteur);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return isOk;
    }
}
