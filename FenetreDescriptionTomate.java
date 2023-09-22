package ihm;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import modele.Tomate;
import modele.Tomates;
import modele.GenerationArticles;
import modele.Panier;

public class FenetreDescriptionTomate extends JFrame {
	private JPanel contentPane;
	private Tomate tomate;
	private JTextField champNbGraines;
	private JTextField champPrix;
	public static JSpinner spinnerQuantite;

	public FenetreDescriptionTomate(Tomate tomate, Tomates tomates) {
		this.tomate = tomate;
		spinnerQuantite = new JSpinner();
		String[] valeurs = new String[11];
		valeurs = tomate.getTexte();
		new GenerationArticles();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BorderLayout());

		JLabel labelDesignation = new JLabel();
		labelDesignation.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 16));
		labelDesignation.setForeground(Color.GREEN);
		labelDesignation.setText(valeurs[0]);
		panelGauche.add(labelDesignation, BorderLayout.NORTH);

		JLabel imageTomate = new JLabel();
		imageTomate.setIcon(new ImageIcon(FenetreDescriptionTomate.class.getResource(valeurs[2])));
		panelGauche.add(imageTomate, BorderLayout.CENTER);

		contentPane.add(panelGauche, BorderLayout.WEST);

		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new BorderLayout());

		JLabel labelDescription = new JLabel("Description");
		labelDescription.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		panelCentre.add(labelDescription, BorderLayout.NORTH);

		JTextArea descriptionTomate = new JTextArea();
		descriptionTomate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		descriptionTomate.setEditable(false);
		descriptionTomate.setText(valeurs[1]);
		descriptionTomate.setLineWrap(true);
		descriptionTomate.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(descriptionTomate);
		panelCentre.add(scrollPane, BorderLayout.CENTER);

		JPanel panelInfos = new JPanel();
		panelInfos.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel labelDisponibilite = new JLabel("Disponibilité");
		labelDisponibilite.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		panelInfos.add(labelDisponibilite);

		JLabel labelValeurDispo = new JLabel();
		labelValeurDispo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		labelValeurDispo.setText(valeurs[3]);
		if (valeurs[3] == "En Stock") {
			labelValeurDispo.setForeground(Color.GREEN);
		} else {
			labelValeurDispo.setForeground(Color.RED);
		}

		panelInfos.add(labelValeurDispo);

		JLabel labelNbGraines = new JLabel("Nombre de grains");
		labelNbGraines.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		panelInfos.add(labelNbGraines);

		champNbGraines = new JTextField();
		champNbGraines.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		champNbGraines.setEditable(false);
		panelInfos.add(champNbGraines);
		champNbGraines.setColumns(7);
		champNbGraines.setText(valeurs[9]);

		JLabel labelPrix = new JLabel("Prix ");
		labelPrix.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		panelInfos.add(labelPrix);

		JPanel panelPrixSpinner = new JPanel();
		panelPrixSpinner.setLayout(new BorderLayout());

		champPrix = new JTextField();
		champPrix.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		champPrix.setEditable(false);
		panelPrixSpinner.add(champPrix, BorderLayout.WEST);
		champPrix.setColumns(5);
		champPrix.setText(valeurs[8]);

		// ...
		FenetreDescriptionTomate.spinnerQuantite = new JSpinner();
		FenetreDescriptionTomate.spinnerQuantite.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		FenetreDescriptionTomate.spinnerQuantite.setToolTipText(valeurs[10]);

		int quantite = Integer.parseInt(valeurs[10]);
		int min = 0;
		int max = 100;

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(quantite, min, max, 1);
		FenetreDescriptionTomate.spinnerQuantite.setModel(spinnerModel);

		// Bloquer les boutons du spinner si la disponibilité est différente de "En Stock"
		if (!valeurs[3].equals("En Stock")) {
		    FenetreDescriptionTomate.spinnerQuantite.setEnabled(false);
		}

		panelPrixSpinner.add(spinnerQuantite, BorderLayout.EAST);

		panelInfos.add(panelPrixSpinner);

		panelCentre.add(panelInfos, BorderLayout.SOUTH);

		contentPane.add(panelCentre, BorderLayout.CENTER);

		JPanel panelDroit = new JPanel();
		panelDroit.setLayout(new BorderLayout());

		JComboBox<String> comboBoxTomatesSimilaires = new JComboBox<>();
		comboBoxTomatesSimilaires.setModel(new DefaultComboBoxModel<>(new String[] { "Produits similaires" }));
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("Produits similaires");
		comboBoxModel.addElement(valeurs[4]);
		comboBoxModel.addElement(valeurs[5]);
		comboBoxModel.addElement(valeurs[6]);
		if (valeurs[7] != null && valeurs[7] != valeurs[4] && valeurs[7] != valeurs[5] && valeurs[7] != valeurs[6]) {
			comboBoxModel.addElement(valeurs[7]);
		}
		comboBoxTomatesSimilaires.setModel(comboBoxModel);
		panelDroit.add(comboBoxTomatesSimilaires, BorderLayout.NORTH);
		ContrôleurProduitsSimilaires(tomates, comboBoxTomatesSimilaires);

		JPanel panelBoutons = new JPanel();
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton boutonAjouterPanier = new JButton("Ajouter au panier");
		panelBoutons.add(boutonAjouterPanier);
		ajouterControleurBoutonAjouterPanier(boutonAjouterPanier);

		JButton boutonAnnuler = new JButton("Annuler");
		ajouterControleurBoutonAnnuler(boutonAnnuler);
		panelBoutons.add(boutonAnnuler);

		panelDroit.add(panelBoutons, BorderLayout.SOUTH);

		contentPane.add(panelDroit, BorderLayout.EAST);
	}

	private void ajouterControleurBoutonAnnuler(JButton boutonAnnuler) {
		boutonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void ajouterControleurBoutonAjouterPanier(JButton boutonAjouterPanier) {
		boutonAjouterPanier.addActionListener(new ActionListener() {
			List<String[]> informations = new ArrayList<>();

			public void actionPerformed(ActionEvent e) {
				Integer quantite;

				if (spinnerQuantite != null) {
					quantite = ((Number) FenetreDescriptionTomate.spinnerQuantite.getValue()).intValue();
				} else {
					quantite = 0; 
				}

				Panier panier = Panier.getPanier(); // Récupérer l'instance du panier
				panier.ajouterTomate(tomate, quantite); // Passer la quantité au panier
				panier.miseAJour();
				informations = panier.getTableau();
				FenetrePanier pagePanier = new FenetrePanier(informations, panier); // Créer une instance de FenetrePanier
				pagePanier.setVisible(true);

				FenetreAccueil.labelTotalPanier.setText(panier.getTotal() + " €");
				FenetrePanier.montantTotal.setText(Panier.getPanier().getTotalLivraison() + " €");
				FenetrePanier.sousTotal.setText(panier.getTotal() + " €");
			}
		});
	}

	private void ContrôleurProduitsSimilaires(Tomates tomates, JComboBox<String> comboBox) {
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				String tomateSelectionnee = (String) source.getSelectedItem();

				if (!tomateSelectionnee.equals("Produits similaires")) {
					// Trouver la tomate correspondante dans la liste tomates
					Tomate tomateSelectionneeliste = tomates.getTomateByDesignation(tomateSelectionnee);
					if (tomateSelectionneeliste != null) {
						// Ouvrir une nouvelle fenêtre de description pour la tomate sélectionnée
						FenetreDescriptionTomate selectedTomateDescription = new FenetreDescriptionTomate(tomateSelectionneeliste, tomates);
						selectedTomateDescription.setVisible(true);
					}
				}
			}
		});
	}
}