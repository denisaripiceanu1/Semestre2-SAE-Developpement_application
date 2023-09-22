package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FenetreFacture extends JFrame {

	private JPanel panneauPrincipal;
	private JTextArea informations;

	public FenetreFacture(List<String> donnees) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 600);
		panneauPrincipal = new JPanel();
		panneauPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panneauPrincipal);
		panneauPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panneauTitre = new JPanel();
		panneauTitre.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panneauPrincipal.add(panneauTitre, BorderLayout.NORTH);
		panneauTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitre = new JLabel("Votre facture");
		lblTitre.setIcon(null);
		lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
		panneauTitre.add(lblTitre);

		JPanel panneauContenu = new JPanel();
		panneauContenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panneauPrincipal.add(panneauContenu, BorderLayout.CENTER);
		panneauContenu.setLayout(new BorderLayout(0, 0));

		JPanel panneauHaut = new JPanel();
		panneauHaut.setBorder(new EmptyBorder(10, 10, 10, 10));
		panneauContenu.add(panneauHaut, BorderLayout.NORTH);
		panneauHaut.setLayout(new BorderLayout(0, 0));

		JTextArea sousTitre = new JTextArea();
		sousTitre.setTabSize(6);
		sousTitre.setEditable(false);
		sousTitre.setFont(new Font("Arial", Font.BOLD, 14));
		sousTitre.setText("Merci de votre visite !");
		panneauHaut.add(sousTitre, BorderLayout.NORTH);

		JPanel panneauCentre = new JPanel();
		panneauCentre.setBorder(new EmptyBorder(10, 10, 10, 10));
		panneauContenu.add(panneauCentre, BorderLayout.CENTER);
		panneauCentre.setLayout(new BorderLayout(0, 0));

		informations = new JTextArea();
		informations.setFont(new Font("Arial", Font.PLAIN, 12));
		informations.setText("");
		informations.setLineWrap(true);
		informations.setWrapStyleWord(true);
		informations.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(informations);
		panneauCentre.add(scrollPane, BorderLayout.CENTER);

		JPanel panneauBas = new JPanel();
		panneauPrincipal.add(panneauBas, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panneauBas.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);

		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setFont(new Font("Arial", Font.PLAIN, 14));
		ajouterControleurBoutonImprimer(btnImprimer);
		panneauBas.add(btnImprimer);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Arial", Font.PLAIN, 14));
		panneauBas.add(btnQuitter);
		ajouterControleurBoutonQuitter(btnQuitter);

		afficherInformations(donnees);
	}

	private void ajouterControleurBoutonQuitter(JButton btnQuitter) {
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
	}

	private void ajouterControleurBoutonImprimer(JButton btnImprimer) {
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenetreImprimer impression = new FenetreImprimer();
				impression.setVisible(true);
			}
		});
	}

	private void afficherInformations(List<String> donnees) {
		StringBuilder sb = new StringBuilder();
		for (String info : donnees) {
			sb.append(info).append("\n");
		}
		informations.setText(sb.toString());
	}
}
