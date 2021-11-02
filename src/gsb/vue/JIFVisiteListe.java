package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class JIFVisiteListe extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	protected JPanel p;
	protected JPanel pCherche;
	protected JScrollPane pListe;
	protected JPanel pDetails;
	
	protected JLabel JLCode;
	protected JLabel JLDate;
	protected JLabel JLReference;
	
	protected JTextField JTCode;
	protected JFormattedTextField JTDate;
	protected JTextField JTReference;
	
	protected JButton JBRechercher;
	protected JButton JBDetails;
	
	public JIFVisiteListe() {
		p = new JPanel();
		pCherche = new JPanel(new GridLayout(2,2));
		pDetails = new JPanel(new GridLayout(1, 3));
		
		//Cherche
		
		JLCode = new JLabel("Code visiteur");
		JLDate = new JLabel("Date visite");
		
		JTCode = new JTextField(20);
		
		MaskFormatter mask = null;
		
		try {
			mask = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JTDate = new JFormattedTextField(mask);
		
		pCherche.add(JLCode);
		pCherche.add(JTCode);
		pCherche.add(JLDate);
		pCherche.add(JTDate);
		
		JBRechercher = new JButton("Rechercher");
		JBRechercher.addActionListener(this);
		
		p.add(pCherche);
		p.add(JBRechercher);
		
		//Liste
		String[] columnNames = {"Référence", "Code médecin", "Lieu", "Modifier", "Détails"};
		
		String[][] data = new String[10][5];
		
		JTable table = new JTable(data, columnNames);
		
		pListe = new JScrollPane(table);
		pListe.setPreferredSize(new Dimension(400, 100));
		
		p.add(pListe);
		
		//Details
		JLReference = new JLabel("Référence");
		JTReference = new JTextField();
		
		JBDetails = new JButton("Visite détaillée");
		
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
			
		}
		else if(source == JBDetails) {
			
		}
	}

}
