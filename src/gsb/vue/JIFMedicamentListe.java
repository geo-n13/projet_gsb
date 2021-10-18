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
	
	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeMedicament;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;
	
	public JIFMedicamentListe(MenuPrincipal uneFenetreContainer) {
		fenetreContainer = uneFenetreContainer;
		
		listeMedicament = MedicamentDao.retournerCollectionDesMedicaments();
	
		int nbLignes = listeMedicament.size();
		
		p = new JPanel();
		
		int i = 0;
		String[][] data = new String[nbLignes][3];
		
		for(Medicament med : listeMedicament) {
			data[i][0] = med.getCodeFamille();
			data[i][1] = med.getNomCommercial();
			data[i][2] = med.getLibellefamille();
		}
		
		String[] columnNames = {"Code", "Nom", "Famille"};
		table = new JTable(data, columnNames);
		
		table.getSelectionModel().addListSelectionListener(table);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		pSaisie = new JPanel();
		JTcodeMedicament = new JTextField(20);
		JTcodeMedicament.setMaximumSize(JTcodeMedicament.getPreferredSize());
		JBafficherFiche = new JButton("Afficher Fiche médicament");
		JBafficherFiche.addActionListener(this);
		pSaisie.add(JTcodeMedicament);
		pSaisie.add(JBafficherFiche);
		p.add(pSaisie);
		
		Container contentPane = getContentPane();
		contentPane.add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
