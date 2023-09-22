package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import modele.Panier;

public class FenetrePanier extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static JEditorPane sousTotal;
	static JEditorPane montantTotal;

	/**
	 * Create the frame.
	 */
	public FenetrePanier(List<String[]> informations, Panier panier) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNord = new JPanel();
		contentPane.add(panelNord, BorderLayout.NORTH);

		JLabel iconePanier = new JLabel("");
		iconePanier.setIcon(new ImageIcon(FenetrePanier.class.getResource("/Tomates40x40/Design sans titre.png")));
		panelNord.add(iconePanier);

		JLabel lblTitre = new JLabel("Votre panier");
		panelNord.add(lblTitre);

		JPanel panelCentre = new JPanel();
		contentPane.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panelCentre.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelRecalcul = new JPanel();
		panel.add(panelRecalcul);

		JButton btnRecalculer = new JButton("Recalculer le panier");
		panelRecalcul.add(btnRecalculer);
		ajouterControleurRecalculerPanier(btnRecalculer);

		JPanel panelPrix = new JPanel();
		panel.add(panelPrix);
		panelPrix.setLayout(new GridLayout(0, 2, 0, 10));

		JLabel lblSousTotal = new JLabel("Sous-total :");
		panelPrix.add(lblSousTotal);

		sousTotal = new JEditorPane();
		sousTotal.setText(" ");
		panelPrix.add(sousTotal);

		JLabel lblExpedition = new JLabel("Expédition (forfait) :");
		panelPrix.add(lblExpedition);

		JEditorPane montantExpedition = new JEditorPane();
		montantExpedition.setText("4.5 €");
		panelPrix.add(montantExpedition);

		JLabel lblTotal = new JLabel("TOTAL :");
		panelPrix.add(lblTotal);

		montantTotal = new JEditorPane();
		montantTotal.setText(String.valueOf(Panier.getPanier().getTotalLivraison()));
		panelPrix.add(montantTotal);

		JPanel panelScroll = new JPanel();
		panelCentre.add(panelScroll, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panelScroll.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(500, 200));

		// Créer un DefaultTableModel personnalisé pour rendre la colonne "Quantité"
		// éditable
		DefaultTableModel model = new DefaultTableModel(0, 4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
		};

		String[] columnNames = { "Images", "Produit", "Prix", "Quantité", "Total" };
		Object[][] data = new Object[informations.size()][5];

		for (int i = 0; i < informations.size(); i++) {
			String[] info = informations.get(i);
			ImageIcon imageIcon = new ImageIcon(FenetrePanier.class.getResource("/Tomates40x40/" + info[0] + ".jpg"));
			JLabel imageLabel = new JLabel(imageIcon);
			data[i][0] = imageLabel;
			data[i][1] = info[1];
			data[i][2] = info[2] + " €";
			data[i][3] = info[3];
			data[i][4] = info[4];
		}
		model.setDataVector(data, columnNames);

		table = new JTable(model) {
			@Override
			public TableCellRenderer getCellRenderer(int row, int column) {
				if (column == 0) {
					return new ImageRenderer();
				}
				return super.getCellRenderer(row, column);
			}
		};

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
		columnModel.getColumn(1).setPreferredWidth(200);

		scrollPane.setViewportView(table.getTableHeader());
		scrollPane.setColumnHeaderView(table.getTableHeader());
		scrollPane.setViewportView(table);

		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setMaxWidth(60);

		JPanel panelSud = new JPanel();
		panelSud.setBorder(new EmptyBorder(40, 0, 0, 0));
		contentPane.add(panelSud, BorderLayout.SOUTH);

		JButton btnValider = new JButton("Valider le panier");
		panelSud.add(btnValider);
		ContrôleurBoutonValider(btnValider);

		JButton btnVider = new JButton("Vider le panier");
		panelSud.add(btnVider);
		ContrôleurBoutonVider(btnVider);

		JButton btnContinuerAchats = new JButton("Continuer les achats");
		panelSud.add(btnContinuerAchats);
		ajouterControleurContinuerLesAchats(btnContinuerAchats);
	}

	private void ContrôleurBoutonVider(JButton btnVider) {
		btnVider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir vider le panier ?",
						"Confirmation", JOptionPane.OK_CANCEL_OPTION);
				if (choice == JOptionPane.OK_OPTION) {
					Panier.getPanier().viderPanier();
					String totalAvecEuro = "0.0 €";
					FenetreAccueil.labelTotalPanier.setText(totalAvecEuro);
					dispose();
				}
			}
		});
	}

	private void ContrôleurBoutonValider(JButton btnValider) {
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonnees coordonnees = new FenetreCoordonnees();
				coordonnees.setVisible(true);
				dispose();
			}
		});
	}

	private void ajouterControleurContinuerLesAchats(JButton btnContinuerAchats) {
		btnContinuerAchats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void ajouterControleurRecalculerPanier(JButton btnRecalculer) {
		btnRecalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				System.out.println(row);
				if (row != -1) {
					String quantiteString = table.getValueAt(row, 3).toString();

					try {
						float quantite = Float.parseFloat(quantiteString);

						Panier.getPanier().setQuantite(quantite, row);

						table.setValueAt(Panier.getPanier().getTableau().get(row)[4], row, 4);

						String sousTotalText = String.format("%.2f", Panier.getPanier().getTotal()) + " €";
						sousTotal.setText(sousTotalText);

						String montantTotalText = String.format("%.2f", Panier.getPanier().getTotalLivraison()) + " €";
						montantTotal.setText(montantTotalText);

						FenetreAccueil.labelTotalPanier.setText(sousTotalText);

					} catch (NumberFormatException ex) {
						System.out.println("Invalid number format: " + ex.getMessage());
						ex.printStackTrace();
					}
				}
			}
		});
	}

	private class ImageRenderer extends DefaultTableCellRenderer {
		@Override
		public void setValue(Object value) {
			if (value instanceof JLabel) {
				JLabel label = (JLabel) value;
				setIcon(label.getIcon());
			} else {
				super.setValue(value);
			}
		}
	}

}