package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FenetreImprimer extends JFrame {

	private JComboBox<String> imprimantes;
	private JComboBox<String> choixRectoVerso;
	private JComboBox<String> choixNoirBlanc;
	private JRadioButton toutesPages;
	private JRadioButton plagePages;
	private JTextField debutPlage;
	private JTextField finPlage;
	private JSpinner spinnerCopies; 
	private JButton btnImprimer;
	private JButton btnAnnuler;

	public FenetreImprimer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Paramètres d'impression");
		setPreferredSize(new Dimension(600, 400));

		JPanel paneauPrincipal = new JPanel(new BorderLayout());
		paneauPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

		JLabel lblImprimante = new JLabel("Imprimante :");
		formPanel.add(lblImprimante);

		JLabel lblRectoVerso = new JLabel("Recto-Verso :");
		choixRectoVerso = new JComboBox<>();
		choixRectoVerso.addItem("Oui");
		choixRectoVerso.addItem("Non");
		formPanel.add(lblRectoVerso);
		formPanel.add(choixRectoVerso);

		JLabel lblNoirBlanc = new JLabel("Noir et Blanc :");
		choixNoirBlanc = new JComboBox<>();
		choixNoirBlanc.addItem("Oui");
		choixNoirBlanc.addItem("Non");
		formPanel.add(lblNoirBlanc);
		imprimantes = new JComboBox<>();
		imprimantes.addItem("Imprimante 1");
		imprimantes.addItem("Imprimante 2");
		formPanel.add(imprimantes);
		formPanel.add(choixNoirBlanc);

		JLabel lblPlageImpression = new JLabel("Plage d'impression :");
		formPanel.add(lblPlageImpression);

		JPanel plagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toutesPages = new JRadioButton("Toutes les pages");
		plagePanel.add(toutesPages);
		formPanel.add(new JPanel());
		formPanel.add(plagePanel);

		JPanel panelPlage = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plagePages = new JRadioButton("De la page :");
		debutPlage = new JTextField(5);
		finPlage = new JTextField(5);
		panelPlage.add(plagePages);
		panelPlage.add(debutPlage);
		panelPlage.add(new JLabel("à"));
		panelPlage.add(finPlage);
		formPanel.add(new JPanel());
		formPanel.add(panelPlage);

		JLabel lblNbCopies = new JLabel("Nombre de copies :");
		spinnerCopies = new JSpinner();
		JComponent editor = spinnerCopies.getEditor();
		Dimension editorSize = editor.getPreferredSize();
		editor.setPreferredSize(new Dimension(80, editorSize.height)); // Réduire la taille du spinner

		JPanel copiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		copiesPanel.add(lblNbCopies);
		copiesPanel.add(spinnerCopies);
		formPanel.add(copiesPanel);

		paneauPrincipal.add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnImprimer = new JButton("Imprimer");
		ControleurBoutonImprimer();
		buttonPanel.add(btnImprimer);

		btnAnnuler = new JButton("Annuler");
		ControleurBoutonAnnuler();
		buttonPanel.add(btnAnnuler);

		paneauPrincipal.add(buttonPanel, BorderLayout.SOUTH);

		getContentPane().add(paneauPrincipal);
		pack();
		setLocationRelativeTo(null);
	}

	private void ControleurBoutonAnnuler() {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
	}

	private void ControleurBoutonImprimer() {
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estFormulaireValide()) {
					System.exit(0); // Fermer toute l'application
				} else {
					JOptionPane.showMessageDialog(FenetreImprimer.this, "Veuillez remplir tous les champs", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private boolean estFormulaireValide() {
		// Vérifier que tous les champs sont renseignés
		boolean plageSelectionnee = plagePages.isSelected();
		String debut = debutPlage.getText().trim();
		String fin = finPlage.getText().trim();
		int copies = (int) spinnerCopies.getValue();

		return ((!plageSelectionnee && !toutesPages.isSelected())
				|| (plageSelectionnee && !debut.isEmpty() && !fin.isEmpty())) && copies > 0;
	}
}
