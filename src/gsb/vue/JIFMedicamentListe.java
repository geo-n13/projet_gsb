package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

public class JIFMedicamentListe extends JInternalFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Medicament> listeMedicament;
	
	protected JPanel panel;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeMedicament;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;
	
	public JIFMedicamentListe(MenuPrincipal uneFenetreContainer) {
		fenetreContainer = uneFenetreContainer;
		// Méthode Dao permettant de récupérer l'intégralité des médicaments
		listeMedicament = MedicamentDao.retournerCollectionDesMedicaments();
	
		int nbLignes = listeMedicament.size();
		
		panel = new JPanel();
		// Affichage du tableau
		int i = 0;
		String[][] data = new String[nbLignes][3];
		// Récupération du nom + affichage des autres médicaments
		for(Medicament med : listeMedicament) {
			data[i][0] = med.getDepotLegal();
			data[i][1] = med.getNomCommercial();
			data[i][2] = med.getLibellefamille();
			i++;
		}
		// Création du tableau (cellules)
		String[] columnNames = {"Code", "Nom", "Famille"};
		table = new JTable(data, columnNames);
		
		table.getSelectionModel().addListSelectionListener(table);
		// Dimension de la taille
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollPane);
		
		// Ajout des éléments
		pSaisie = new JPanel();
		JTcodeMedicament = new JTextField(20);
		JTcodeMedicament.setMaximumSize(JTcodeMedicament.getPreferredSize());
		JBafficherFiche = new JButton("Afficher Fiche médicament");
		JBafficherFiche.addActionListener(this);
		pSaisie.add(JTcodeMedicament);
		pSaisie.add(JBafficherFiche);
		panel.add(pSaisie);
		
		Container contentPane = getContentPane();
		contentPane.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// Source btn afficher détails
		if(source == JBafficherFiche) {
			if((!JTcodeMedicament.getText().isEmpty())) {
				String codeMedicament = JTcodeMedicament.getText();
				// Recherche si le code existe
				if(MedicamentDao.rechercher(codeMedicament) != null) {
					new JFMedicamentDetails(codeMedicament);
					System.out.println("Ouverture de la fenêtre de détails du médicament " + codeMedicament);
				}
				// Le code n'existe pas
				else {
					System.out.println("Saisie utilisateur : " + codeMedicament + ". Cette référence n'existe pas");
				}
				
			}
		}
		
	}
}
