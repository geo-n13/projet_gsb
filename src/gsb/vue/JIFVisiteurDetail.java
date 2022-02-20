package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

public class JIFVisiteurDetail extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    protected Visiteur visiteur;

    protected JPanel p;
    protected JPanel pDetails;

    protected JLabel JLMatricule;
    protected JLabel JLNom;
    protected JLabel JLPrenom;
    protected JLabel JLTelephone;
    protected JLabel JLAdresse;
    protected JLabel JLDateentree;
    protected JLabel JLPrime;
    protected JLabel JLCodeunite;
    protected JLabel JLNomunite;

    protected JTextField JTMatricule;
    protected JTextField JTNom;
    protected JTextField JTPrenom;
    protected JTextField JTTelephone;
    protected JTextField JTAdresse;
    protected JTextField JTDateentree;
    protected JTextField JTPrime;
    protected JTextField JTCodeunite;
    protected JTextField JTNomunite;

    protected JButton JBFermer;

    public JIFVisiteurDetail(String matricule) {

        this.visiteur = VisiteurDao.rechercher(matricule);
        this.setVisible(true);
        this.setSize(500, 300);

        p = new JPanel();
        pDetails = new JPanel(new GridLayout(9,2));

        JLMatricule = new JLabel("Matricule");
        JLNom = new JLabel("Nom");
        JLPrenom = new JLabel("Prenom");
        JLTelephone = new JLabel("Telephone");
        JLAdresse = new JLabel("Adresse");
        JLDateentree = new JLabel("Date entree");
        JLPrime = new JLabel("Prime");
        JLCodeunite = new JLabel("Code unite");
        JLNomunite = new JLabel("Nom unite");

        JTMatricule = new JTextField(visiteur.getMatricule(), 20);
        JTMatricule.setEditable(false);
        JTNom = new JTextField(visiteur.getNom());
        JTNom.setEditable(false);
        JTPrenom = new JTextField(visiteur.getPrenom());
        JTPrenom.setEditable(false);
        JTTelephone = new JTextField(visiteur.getTelephone());
        JTTelephone.setEditable(false);
        JTAdresse = new JTextField(visiteur.getAdresse());
        JTAdresse.setEditable(false);
        JTDateentree = new JTextField(visiteur.getDateEntree());
        JTDateentree.setEditable(false);
        JTPrime = new JTextField(visiteur.getPrime());
        JTPrime.setEditable(false);
        JTCodeunite = new JTextField(visiteur.getCodeUnite());
        JTCodeunite.setEditable(false);
        JTNomunite = new JTextField(visiteur.getNomUnite());
        JTNomunite.setEditable(false);

        pDetails.add(JLMatricule);
        pDetails.add(JTMatricule);
        pDetails.add(JLNom);
        pDetails.add(JTNom);
        pDetails.add(JLPrenom);
        pDetails.add(JTPrenom);
        pDetails.add(JLTelephone);
        pDetails.add(JTTelephone);
        pDetails.add(JLAdresse);
        pDetails.add(JTAdresse);
        pDetails.add(JLDateentree);
        pDetails.add(JTDateentree);
        pDetails.add(JLPrime);
        pDetails.add(JTPrime);
        pDetails.add(JLCodeunite);
        pDetails.add(JTCodeunite);
        pDetails.add(JLNomunite);
        pDetails.add(JTNomunite);

        JBFermer = new JButton("Fermer");
        JBFermer.addActionListener(this);

        p.add(pDetails);

        p.add(JBFermer);

        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == JBFermer) {
            this.dispose();
        }
    }
}
