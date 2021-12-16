package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gsb.modele.Medicament;
import gsb.modele.Visiteur;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.VisiteurDao;

public class JIFMedicamentFamille extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Variables utilisées 
	
		// Panneau
		protected JPanel panel;
		protected JPanel content;
		
		// Permet de scroll
		protected JScrollPane scrollListe;
		private ArrayList<Medicament> listeMedicament;
		
		// Création du tableau
		protected JTable table;
		
		
		protected JComboBox<String> JCBFamille;
		protected JLabel JLDepotLegal;
		protected JLabel JLNomCommercial;
		protected JLabel JLComposition;
		protected JLabel JLEffet;
		protected JLabel JLContreIndication;
		protected JLabel JLPrixEchantillon;
		protected JLabel JLFamille;
		
		// Informations BDD
		protected JTextField JTDepotLegal;
		protected JTextField JTNomCommercial;
		protected JTextField JTComposition;
		protected JTextField JTEffet;
		protected JTextField JTContreIndication;
		protected JTextField JTPrixEchantillon;
		
		protected JButton JBClose;
		
		public JIFMedicamentFamille() {
			panel = new JPanel();
			content = new JPanel(new GridLayout(5,2));
			JLFamille = new JLabel("Famille ");
			JCBFamille = new JComboBox<String>();
			
			for(String famille : MedicamentDao.retournerListeFamille()) {
				JCBFamille.addItem(famille);
			}
			JCBFamille.addActionListener(this);
			
			// Création du tableau
			String[] columnNames = {"Dépot légal", "Nom commercial", "Composition", "Effets", "Contre indications"};
			String[][] data = new String[0][5];
			
			table = new JTable(data, columnNames);
			
			scrollListe = new JScrollPane(table);
			scrollListe.setPreferredSize(new Dimension(500, 100));
			// Btn fermer
			JBClose = new JButton("Fermer");
			JBClose.addActionListener(this);
			
			panel.add(JLFamille);
			panel.add(JCBFamille);
			panel.add(scrollListe);
			panel.add(JBClose);
			
			
			Container contentPane = getContentPane();
			contentPane.add(panel);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == JCBFamille)
		{
			System.out.println("La famille de médicaments : "+JCBFamille.getSelectedItem().toString()+ " a été sélectionnée.");
		
				System.out.println("Les données vont à présent être affichées.");
				
				// Mise à jour du tableau
				ArrayList<Medicament> listeFamilleMedicament = MedicamentDao.retournerCollectionFamille(JCBFamille.getSelectedItem().toString());
				String[] columnNames = {"Dépot légal", "Nom commercial", "Composition", "Effets", "Contre indications"};
				int i = 0;
				Object[][] data = new Object[listeFamilleMedicament.size()][5];
				
				// Mise à jour du tableau
				for(Medicament medicament : listeFamilleMedicament) {
					data[i][0] = medicament.getDepotLegal();
					data[i][1] = medicament.getNomCommercial();
					data[i][2] = medicament.getComposition();
					data[i][3] = medicament.getEffets();
					data[i][4] = medicament.getContreIndication();
					i++;
				}
				DefaultTableModel tableur = new DefaultTableModel(data, columnNames);
				
				table.setModel(tableur);
			}
			
		
	}

}
