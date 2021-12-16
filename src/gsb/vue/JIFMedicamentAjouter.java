package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gsb.modele.Medicament;
import gsb.modele.Visite;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

// Extension JFrame avec actions
public class JIFMedicamentAjouter extends JInternalFrame implements ActionListener {
	
	// Variables utilisées 
	
	// Panneau
	protected JPanel panel;
	protected JPanel content;
	
	// Texte écrit situé à gauche
	protected JLabel JLDepotLegal;
	protected JLabel JLNomCommercial;
	protected JLabel JLComposition;
	protected JLabel JLEffet;
	protected JLabel JLContreIndication;
	protected JLabel JLPrixEchantillon;
	protected JLabel JLCode;
	protected JLabel JLLibelle;
	
	// Texte saisi
	protected JTextField JTDepotLegal;
	protected JTextField JTNomCommercial;
	protected JTextField JTComposition;
	protected JTextField JTEffet;
	protected JTextField JTContreIndication;
	protected JTextField JTPrixEchantillon;
	protected JTextField JTCode;
	protected JTextField JTLibelle;
	
	// Bouton ajouter
	protected JButton JBAjouter;
	
	
	public JIFMedicamentAjouter() {
		panel = new JPanel();
			// Ajustement des éléments saisis (8 lignes, 2 colonnes)
			content = new JPanel(new GridLayout(8,2));
			JLDepotLegal = new JLabel("Dépôt légal");
			JLNomCommercial = new JLabel("Nom commercial");
			JLComposition = new JLabel("Composition");
			JLEffet = new JLabel("Effets");
			JLContreIndication = new JLabel("Contre indication");
			JLPrixEchantillon = new JLabel("Prix échantillon");
			JLCode = new JLabel("Code");
			JLLibelle = new JLabel("Libellé");
			
			// Saisir une taille fixe
			JTDepotLegal = new JTextField(20);
			JTNomCommercial = new JTextField();
			JTComposition = new JTextField();
			JTEffet = new JTextField();
			JTContreIndication = new JTextField();
			JTPrixEchantillon = new JTextField();
			JTCode = new JTextField();
			JTLibelle = new JTextField();
			
			content.add(JLDepotLegal);
			content.add(JTDepotLegal);
			
			content.add(JLNomCommercial);
			content.add(JTNomCommercial);
			
			content.add(JLComposition);
			content.add(JTComposition);
			
			content.add(JLEffet);
			content.add(JTEffet);
			
			content.add(JLContreIndication);
			content.add(JTContreIndication);
			
			content.add(JLPrixEchantillon);
			content.add(JTPrixEchantillon);
			
			content.add(JLCode);
			content.add(JTCode);
			
			content.add(JLLibelle);
			content.add(JTLibelle);
		
		JBAjouter = new JButton("Ajouter");
		JBAjouter.addActionListener(this);
		
		
		panel.add(content);
		panel.add(JBAjouter);
		
		// Récupère le container de la Frame + ajout du pannel
		Container contentPane = getContentPane();
		contentPane.add(panel);
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// Medicament unMedicament() ...
		//Visite uneVisite = new Visite(reference, date, commentaire, MedecinDao.rechercher(medecin), VisiteurDao.rechercher(visiteur));
		//VisiteDao.creer(uneVisite);
		
		if(source == JBAjouter) {
			//if((!JTDepotLegal.getText().isEmpty()) && (!JTNomCommercial.getText().isEmpty())) { 
				String depotLegal = JTDepotLegal.getText();
				String nomCommercial = JTNomCommercial.getText();
				String composition = JTComposition.getText();
				String effet = JTEffet.getText();
				String contreIndication = JTContreIndication.getText();
				Float prixEchantillon = Float.parseFloat(JTPrixEchantillon.getText());
				String code = JTCode.getText();
				String libelle = JTLibelle.getText();	
				
				if(MedicamentDao.rechercher(code) == null) {
					Medicament unMedicament = new Medicament(depotLegal, nomCommercial, composition, effet, contreIndication, prixEchantillon, code, libelle);
					MedicamentDao.creer(unMedicament);
					System.out.println("Le médicament vient d'être ajouté.");
				}
				else {
					System.out.println("Une erreur est survenue : Ce médicament existe déjà.");
				}
				//else {
				//	System.out.println("Merci de compléter tous les champs");
				//}
			}
		}
	}


