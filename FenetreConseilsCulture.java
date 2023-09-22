package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.Tomates;

public class FenetreConseilsCulture extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreConseilsCulture frame = new FenetreConseilsCulture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FenetreConseilsCulture() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNord = new JPanel();
		panelNord.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panelNord, BorderLayout.NORTH);
		panelNord.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lbTitre = new JLabel("Conseils de culture");
		lbTitre.setIcon(null);
		lbTitre.setFont(new Font("Arial", Font.BOLD, 16));
		panelNord.add(lbTitre);

		JLabel icone = new JLabel("");
		icone.setIcon(new ImageIcon(FenetreConseilsCulture.class.getResource("/Tomates40x40/2.jpg")));
		panelNord.add(icone);

		JPanel panelCentre = new JPanel();
		panelCentre.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new BorderLayout(0, 0));

		JPanel panelText = new JPanel();
		panelText.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelCentre.add(panelText, BorderLayout.NORTH);
		panelText.setLayout(new BorderLayout(0, 0));

		JTextArea ConseilsDeCultureTitre = new JTextArea();
		ConseilsDeCultureTitre.setTabSize(6);
		ConseilsDeCultureTitre.setEditable(false);
		ConseilsDeCultureTitre.setFont(new Font("Arial", Font.BOLD, 14));
		ConseilsDeCultureTitre.setText(Tomates.CONSEILS_DE_CULTURE_TITRE);
		panelText.add(ConseilsDeCultureTitre, BorderLayout.NORTH);

		JPanel panelScroll = new JPanel();
		panelScroll.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelCentre.add(panelScroll, BorderLayout.CENTER);
		panelScroll.setLayout(new BorderLayout(0, 0));

		JTextArea ConseilsDeCulture = new JTextArea();
		ConseilsDeCulture.setFont(new Font("Arial", Font.PLAIN, 12));
		ConseilsDeCulture.setText(Tomates.CONSEILS_DE_CULTURE);
		ConseilsDeCulture.setLineWrap(true);
		ConseilsDeCulture.setWrapStyleWord(true);
		ConseilsDeCulture.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(ConseilsDeCulture);
		panelScroll.add(scrollPane, BorderLayout.CENTER);

		JPanel panelSud = new JPanel();
		contentPane.add(panelSud, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panelSud.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);

		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Arial", Font.PLAIN, 14));
		ContrôleurBoutonOK(btnOK);
		panelSud.add(btnOK);
	}

	private void ContrôleurBoutonOK(JButton btnOK) {
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
