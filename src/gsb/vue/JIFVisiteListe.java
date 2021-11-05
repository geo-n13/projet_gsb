package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

public class JIFVisiteListe extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	protected JPanel p;
	protected JPanel pCherche;
	protected JPanel pListe;
	protected JPanel pButtons;
	protected JPanel pDetails;
	protected JScrollPane psListe;
	
	protected JTable table;
	
	protected JLabel JLMatricule;
	protected JLabel JLDate;
	protected JLabel JLReference;
	
	protected JTextField JTMatricule;
	protected JFormattedTextField JTDate;
	protected JTextField JTReference;
	
	protected JButton JBRechercher;
	protected JButton JBDetails;
	protected JButton JBRowEdit;
	protected JButton JBRowDetails;
	
	public JIFVisiteListe() {
		p = new JPanel();
		pCherche = new JPanel(new GridLayout(2,2));
		pListe = new JPanel();
		pButtons = new JPanel(new GridLayout(2,1));
		pDetails = new JPanel(new GridLayout(1, 3));
		
		//Cherche
		
		JLMatricule = new JLabel("Matricule visiteur");
		JLDate = new JLabel("Date visite");
		
		JTMatricule = new JTextField(20);
		
		MaskFormatter mask = null;
		
		try {
			mask = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JTDate = new JFormattedTextField(mask);
		
		pCherche.add(JLMatricule);
		pCherche.add(JTMatricule);
		pCherche.add(JLDate);
		pCherche.add(JTDate);
		
		JBRechercher = new JButton("Rechercher");
		JBRechercher.addActionListener(this);
		
		p.add(pCherche);
		p.add(JBRechercher);
		
		//Liste
		String[] columnNames = {"Référence", "Code médecin", "Lieu"};
		
		String[][] data = new String[0][3];
		
		table = new JTable(data, columnNames);
		
		psListe = new JScrollPane(table);
		psListe.setPreferredSize(new Dimension(300, 100));
		
		JBRowEdit = new JButton("Modifier");
		JBRowEdit.addActionListener(this);
		JBRowDetails = new JButton("Détails");
		JBRowDetails.addActionListener(this);
		
		pListe.add(psListe);
		pButtons.add(JBRowEdit);
		pButtons.add(JBRowDetails);
		
		pListe.add(pButtons);
		
		p.add(pListe);
		
		//Details
		JLReference = new JLabel("Référence");
		JTReference = new JTextField();
		
		JBDetails = new JButton("Visite détaillée");
		JBDetails.addActionListener(this);
		
		pDetails.add(JLReference);
		pDetails.add(JTReference);
		pDetails.add(JBDetails);
		
		p.add(pDetails);
		
		Container contentPane = getContentPane();
		contentPane.add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == JBRechercher) {
			if((!JTMatricule.getText().isEmpty()) && (JTDate.getText().replaceAll(" ", "").length() == 10)) {
				String matricule = JTMatricule.getText();
				String date = JTDate.getText();
				
				if(VisiteurDao.rechercher(matricule) != null) {
					ArrayList<Visite> listeVisites = VisiteDao.listeVisites(matricule, date);
					String[] columnNames = {"Référence", "Code médecin", "Lieu"};
					
					int i = 0;
					Object[][] data = new Object[listeVisites.size()][3];
					
					for(Visite visite : listeVisites) {
						data[i][0] = visite.getReference();
						data[i][1] = visite.getUnMedecin().getCodeMed();
						data[i][2] = visite.getUnMedecin().getLaLocalite().getCodePostal() + " " + visite.getUnMedecin().getLaLocalite().getVille();
						i++;
					}
					
					DefaultTableModel tableur = new DefaultTableModel(data, columnNames);	
					
					table.setModel(tableur);
				}
				else {
					System.out.println("Ce visiteur n'existe pas");
				}
			}
		}
		else if(source == JBDetails) {
			if((!JTReference.getText().isEmpty())) {
				
				String reference = JTReference.getText();
				
				if(VisiteDao.rechercher(reference) != null) {
					new JFVisiteDetails(reference);
				}
				else {
					System.out.println("Cette référence n'existe pas");
				}
			}
		}
		else if(source == JBRowEdit) {
			if(table.getSelectedRow() != -1) {
				new JFVisiteEdit(table.getValueAt(table.getSelectedRow(), 0).toString());
			}
			else {
				System.out.println("Veuillez sélectionner une ligne");
			}
		}
		else if(source == JBRowDetails) {
			if(table.getSelectedRow() != -1) {
				new JFVisiteDetails(table.getValueAt(table.getSelectedRow(), 0).toString());
			}
			else {
				System.out.println("Veuillez sélectionner une ligne");
			}
		}
	}

}
