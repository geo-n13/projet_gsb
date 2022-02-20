package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.LocaliteDao;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import gsb.service.VisiteurService;

public class JIFVisiteurAjouter extends JInternalFrame implements ActionListener{

    private static final long serialVersionUID = 1L;

    protected JPanel p;
    protected JPanel pTexte;

    protected JLabel JLMatricule;
    protected JLabel JLNom;
    protected JLabel JLPrenom;
    protected JLabel JLLogin;
    protected JLabel JLMotdepasse;
    protected JLabel JLTelephone;
    protected JLabel JLAdresse;
    protected JLabel JLLocalite;
    protected JLabel JLDateentree;
    protected JLabel JLPrime;
    protected JLabel JLCodeunite;
    protected JLabel JLNomunite;

    protected JTextField JTMatricule;
    protected JTextField JTNom;
    protected JTextField JTPrenom;
    protected JTextField JTLogin;
    protected JTextField JTMotdepasse;
    protected JTextField JTTelephone;
    protected JTextField JTAdresse;
    protected JTextField JTLocalite;
    protected JTextField JTDateentree;
    protected JTextField JTPrime;
    protected JTextField JTCodeunite;
    protected JTextField JTNomunite;

    protected JButton JBAjouter;

    public JIFVisiteurAjouter() {

        p = new JPanel();
        pTexte = new JPanel(new GridLayout(12, 2));

        JLMatricule = new JLabel("Matricule");
        JLNom = new JLabel("Nom");
        JLPrenom = new JLabel("Prenom");
        JLLogin = new JLabel("Login");
        JLMotdepasse = new JLabel("Mot de passe");
        JLTelephone = new JLabel("Telephone");
        JLAdresse = new JLabel("Adresse");
        JLLocalite = new JLabel("Code postal");
        JLDateentree = new JLabel("Date entree");
        JLPrime = new JLabel("Prime");
        JLCodeunite = new JLabel("Code unite");
        JLNomunite = new JLabel("Nom unite");

        JTMatricule = new JTextField(10);
        JTNom = new JTextField();
        JTPrenom = new JTextField();
        JTLogin = new JTextField();
        JTMotdepasse = new JPasswordField();
        JTTelephone = new JTextField(10);
        JTAdresse = new JTextField();
        JTLocalite = new JTextField();
        JTDateentree = new JTextField();
        JTPrime = new JTextField();
        JTCodeunite = new JTextField();
        JTNomunite = new JTextField();

        pTexte.add(JLMatricule);
        pTexte.add(JTMatricule);
        pTexte.add(JLNom);
        pTexte.add(JTNom);
        pTexte.add(JLPrenom);
        pTexte.add(JTPrenom);
        pTexte.add(JLLogin);
        pTexte.add(JTLogin);
        pTexte.add(JLMotdepasse);
        pTexte.add(JTMotdepasse);
        pTexte.add(JLTelephone);
        pTexte.add(JTTelephone);
        pTexte.add(JLAdresse);
        pTexte.add(JTAdresse);
        pTexte.add(JLLocalite);
        pTexte.add(JTLocalite);
        pTexte.add(JLDateentree);
        pTexte.add(JTDateentree);
        pTexte.add(JLPrime);
        pTexte.add(JTPrime);
        pTexte.add(JLCodeunite);
        pTexte.add(JTCodeunite);
        pTexte.add(JLNomunite);
        pTexte.add(JTNomunite);

        JBAjouter = new JButton("Ajouter");
        JBAjouter.addActionListener(this);

        p.add(pTexte);

        p.add(JBAjouter);

        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    private Visiteur getFields()
    {
        String matricule = JTMatricule.getText();
        String nom = JTNom.getText();
        String prenom = JTPrenom.getText();
        String login = JTLogin.getText();
        String motdepasse = JTMotdepasse.getText();
        String telephone = JTTelephone.getText();
        String adresse = JTAdresse.getText();
        Localite localite = LocaliteDao.rechercher(JTLocalite.getText());
        String dateentree = JTDateentree.getText();
        int prime = Integer.parseInt(JTPrime.getText());
        String codeunite = JTCodeunite.getText();
        String nomunite = JTNomunite.getText();

        Visiteur nouveauVisiteur = new Visiteur(matricule, nom, prenom, login, motdepasse, adresse, localite, telephone, dateentree, prime, codeunite, nomunite);
        return nouveauVisiteur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == JBAjouter) {
            VisiteurService.AjoutVisiteur(getFields());
        }

    }
}
