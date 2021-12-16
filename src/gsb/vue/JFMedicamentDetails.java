package gsb.vue;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

public class JFMedicamentDetails extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Variables utilisées
	protected Medicament medicament;
	
	// Panneau d'affichage
	protected JPanel panel;
	protected JPanel content;
	protected JTable table;
	
	// Variables utilisées
	protected JLabel JLDepotLegal;
	protected JLabel JLNomCommercial;
	protected JLabel JLComposition;
	protected JLabel JLEffet;
	protected JLabel JLContreIndication;
	protected JLabel JLPrixEchantillon;
	protected JLabel JLCode;
	protected JLabel JLLibelle;
	
	// Variables récupérées
	protected JTextField JTDepotLegal;
	protected JTextField JTNomCommercial;
	protected JTextField JTComposition;
	protected JTextArea JTEffet;
	protected JTextArea JTContreIndication;
	protected JTextField JTPrixEchantillon;
	protected JTextField JTCode;
	protected JTextField JTLibelle;
	protected JScrollPane JSText;
	protected JButton JBClose;
		
	
		
		
		
	public JFMedicamentDetails(String codeMedicament) {
		// Informations fenêtre
		super("Détails sur le médicament : " + codeMedicament);
		this.medicament = MedicamentDao.rechercher(codeMedicament);
		content = new JPanel(new GridBagLayout());
		
		// Dimension de la fenêtre
		this.setVisible(true);
		this.setSize(500, 385);
		// Affichage des labels à gauche
		JLDepotLegal = new JLabel("Dépôt légal");
		JLNomCommercial = new JLabel("Nom commercial");
		JLComposition = new JLabel("Composition");
		JLEffet = new JLabel("Effets");
		JLContreIndication = new JLabel("Contre indication");
		JLPrixEchantillon = new JLabel("Prix échantillon");
		JLCode = new JLabel("Code");
		JLLibelle = new JLabel("Libellé");
		
		// Valeurs récupérées non éditables
		JTDepotLegal = new JTextField(medicament.getDepotLegal(),20);
		JTDepotLegal.setEditable(false);
		JTNomCommercial = new JTextField(medicament.getNomCommercial());
		JTNomCommercial.setEditable(false);
		JTComposition = new JTextField(medicament.getComposition());
		JTComposition.setEditable(false);
		// Longue chaîne de caractères
		JTEffet = new JTextArea(1, 20);
		JTEffet.setMaximumSize(JTEffet.getPreferredSize());
		JTEffet.setLineWrap(true);
		JTEffet.setWrapStyleWord(true);
		JTEffet.setText(medicament.getEffets());
		JTEffet.setEditable(false);
		// Longue chaîne de caractères
		JTContreIndication = new JTextArea(1, 20);
		JTContreIndication.setMaximumSize(JTContreIndication.getPreferredSize());
		JTContreIndication.setLineWrap(true);
		JTContreIndication.setWrapStyleWord(true);
		JTContreIndication.setText(medicament.getContreIndication());
		JTContreIndication.setEditable(false);
		// JScrollTab + ajouter au content
		JTPrixEchantillon = new JTextField(medicament.getPrixEchantillon() + "");
		JTPrixEchantillon.setMaximumSize(JTPrixEchantillon.getPreferredSize());
		JTPrixEchantillon.setEditable(false);
		JTCode = new JTextField(medicament.getCodeFamille());
		JTCode.setMaximumSize(JTCode.getPreferredSize());
		JTCode.setEditable(false);
		JTLibelle = new JTextField(medicament.getLibellefamille());
		JTLibelle.setMaximumSize(JTLibelle.getPreferredSize());
		JTLibelle.setEditable(false);
		
		// Ajout de la contrainte horizontale 
		GridBagConstraints contraint = new GridBagConstraints();
		contraint.fill = GridBagConstraints.HORIZONTAL;
		contraint.insets = new Insets(2, 10, 2, 10);
		
		// Nouvelle fenêtre
		panel = new JPanel();
		
		// Positionnement des éléments
		contraint.gridy = 0;
		contraint.gridx = 0;
		content.add(JLDepotLegal, contraint);
		contraint.gridx = 1;
		content.add(JTDepotLegal, contraint);
		
		contraint.gridy = 1;
		contraint.gridx = 0;
		content.add(JLNomCommercial, contraint);
		contraint.gridx = 1;
		content.add(JTNomCommercial, contraint);
		
		
		contraint.gridy = 2;
		contraint.gridx = 0;
		content.add(JLComposition, contraint);
		contraint.gridx = 1;
		content.add(JTComposition, contraint);
		
		contraint.gridy = 3;
		contraint.gridx = 0;
		content.add(JLEffet, contraint);
		contraint.gridx = 1;
		content.add(JTEffet, contraint);
		
		contraint.gridy = 4;
		contraint.gridx = 0;
		content.add(JLContreIndication, contraint);
		contraint.gridx = 1;
		content.add(JTContreIndication, contraint);
		
		
		contraint.gridy = 5;
		contraint.gridx = 0;
		content.add(JLPrixEchantillon, contraint);
		contraint.gridx = 1;
		content.add(JTPrixEchantillon, contraint);
		
		contraint.gridy = 6;
		contraint.gridx = 0;
		content.add(JLCode, contraint);
		contraint.gridx = 1;
		content.add(JTCode, contraint);
		
		contraint.gridy = 7;
		contraint.gridx = 0;
		content.add(JLLibelle, contraint);
		contraint.gridx = 1;
		content.add(JTLibelle, contraint);
	
	   JBClose = new JButton("Fermer");
	   JBClose.addActionListener(this);
	
	   panel.add(content);
	   panel.add(JBClose);
		
		Container contentPane = getContentPane();
		contentPane.add(panel);
			
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == JBClose) {
				this.dispose();
				System.out.println("Fermeture de la fenêtre de détails sur : " + JLDepotLegal.getText());
			}
			
		}
}
