package modele;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FiltresTomatesTests {

	private Tomates tomates;

	@Before
	public void setUp() {
		tomates = new Tomates();

		// Ajout des tomates pour les tests
		tomates.addTomates(Arrays.asList(new Tomate(TypeTomate.TOMATES_CERISES, Couleur.ROUGE, "Tomate Miel du Mexique",
				null, "IMG_5460",
				"Excellente résistance à la sécheresse. Rendement très élevé, fruits de taille moyenne de 15 à 20 gr.\r\n"
						+ "\r\n"
						+ "Produit des fruits jusqu'en fin de belle saison, sans perte de goût et sans s'abîmer.\r\n"
						+ "\r\n"
						+ "Les fruits sont bien ronds, rouge vif, juteux, au goût exquis, doux, non acide. Tomate très sucrée.",
				50, 3.95F, true),
				new Tomate(TypeTomate.TOMATES, Couleur.ROUGE, "Tomate Joie de la Table", null,
						"Tomate-Joie-de-la-Table-ressemble-scaled",
						"Variété rustique, précoce vigoureuse et productive.\r\n" + "\r\n"
								+ "Ses fruits de 150 à 250 g, très légèrement côtelés, ont une chair fine, juteuse et savoureuse.\r\n"
								+ "\r\n" + "Elles sont délicieuses en salade.",
						50, 3.95F, true),
				new Tomate(TypeTomate.TOMATES_CERISES, Couleur.ROUGE, "Tomate Black Zebra", null, "IMG_0130-scaled",
						"C'est une petite tomate cocktail ronde, pourpre et rayée de vert. Les fruits sont très nombreux et regroupés en grappes, de 6 à 8 fruits.\r\n"
								+ "\r\n" + "Variété précoce avec une saveur très douce.",
						50, 3.95F, true),
				new Tomate(TypeTomate.TOMATES_CERISES, Couleur.VERT, "Tomate Claro Verde", null, "Claro-Verde-scaled",
						"Variété connue depuis 2011, améliorée par Tom Wagner à partir de la Green Grappe. Excellent goût, subtile.\r\n"
								+ "\r\n"
								+ "Fruit ovale et vert à la peau translucide qui fait penser à un grain de raisin. À récolter bien mûre. "
								+ "Plante très vigoureuse, à tuteurer absolument !",
						50, 3.85F, true),
				new Tomate(TypeTomate.TOMATES, Couleur.JAUNE, "Tomate Jaune de Thoune", null, "jaune_thoune-scaled",
						"Tomate moyenne orange très productive durant toute la saison.\r\n" + "\r\n"
								+ "Tomate assez sucrée se prêtant bien en coulis ou en sauce où la saveur est exaltée.\r\n"
								+ "\r\n"
								+ "Variété assez précoce, vigoureuse et assez résistante face aux aléas environnementaux. Adaptée au climat difficile de montagne. ",
						50, 3.85F, true),
				new Tomate(TypeTomate.TOMATES, Couleur.MULTICOLORE, "Tomate Tigrella Bicolore", null,
						"Tomate-Tigrella-Bicolore-1-scaled",
						"Variété précoce, originaire d'Angleterre, donnant des fruits de taille moyennes de 70 à 100g , bicolores, rouges à rayures jaunes.\r\n"
								+ "\r\n" + "Variété productive très résistante aux maladies.",
						50, 3.85F, true),
				new Tomate(TypeTomate.TOMATES, Couleur.ORANGE, "Tomate Brandywine", null, "Brandywine-scaled",
						"Grosse tomate américaine de variété ancienne tardive, rouge-orangée.\r\n" + "\r\n"
								+ "Les fruits de 150 à 200 gr. environ ont une chair fine, juteuse et douce au goût raffiné et vineux. Productive et très vigoureuse.",
						50, 3.95F, true),
				new Tomate(TypeTomate.TOMATES, Couleur.NOIR, "Tomate Black Tomato", null, "IMG_8925-scaled",
						"Gros fruit rond et aplati, rouge sombre, ombré près du pédoncule, pesant de 200 à 400 gr.\r\n"
								+ "\r\n" + "Sa chair pourpre est charnue et juteuse. Très bonne saveur.",
						50, 3.95F, true)));
	}

	@Test
	public void testFiltrerTomatesParType() {
		List<Tomate> tomatesCerises = tomates.filtrerTomatesParType(TypeTomate.TOMATES_CERISES);
		assertEquals(3, tomatesCerises.size());

		List<Tomate> tomatesNormales = tomates.filtrerTomatesParType(TypeTomate.TOMATES);
		assertEquals(5, tomatesNormales.size());
	}

	@Test
	public void testFiltrerTomatesParCouleur() {
		List<Tomate> tomatesRouge = tomates.filtrerTomatesParCouleur(Couleur.ROUGE);
		assertEquals(3, tomatesRouge.size());

		List<Tomate> tomatesVerte = tomates.filtrerTomatesParCouleur(Couleur.VERT);
		assertEquals(1, tomatesVerte.size());

		List<Tomate> tomatesJaune = tomates.filtrerTomatesParCouleur(Couleur.JAUNE);
		assertEquals(1, tomatesJaune.size());

		List<Tomate> tomatesOrange = tomates.filtrerTomatesParCouleur(Couleur.ORANGE);
		assertEquals(1, tomatesOrange.size());

		List<Tomate> tomatesBleu = tomates.filtrerTomatesParCouleur(Couleur.BLEU);
		assertEquals(0, tomatesBleu.size());

		List<Tomate> tomatesNoir = tomates.filtrerTomatesParCouleur(Couleur.NOIR);
		assertEquals(1, tomatesNoir.size());

		List<Tomate> tomatesMulticolore = tomates.filtrerTomatesParCouleur(Couleur.MULTICOLORE);
		assertEquals(1, tomatesMulticolore.size());
	}

	@Test
	public void testFiltrerTomatesParTypeEtCouleur() {
		// Filtrer les tomates par type "TOMATES_CERISES" et couleur "ROUGE"
		List<Tomate> tomatesFiltrees = tomates.filtrerTomatesParTypeEtCouleur(TypeTomate.TOMATES_CERISES,
				Couleur.ROUGE);

		// Vérifier le nombre de tomates filtrées
		assertEquals(2, tomatesFiltrees.size());

		// Vérifier les propriétés de chaque tomate filtrée
		for (Tomate tomate : tomatesFiltrees) {
			assertEquals(TypeTomate.TOMATES_CERISES, tomate.getTypeGraine());
			assertEquals(Couleur.ROUGE, tomate.getCouleur());
		}
	}

	@Test
	public void testFiltrerTomatesParCouleur_CouleurInexistante() {
		// Filtrer les tomates par une couleur inexistante
		List<Tomate> tomatesFiltrees = tomates.filtrerTomatesParCouleur(Couleur.BLEU);

		// Vérifier que la liste filtrée est vide
		assertTrue(tomatesFiltrees.isEmpty());
	}

	@Test
	public void testFiltrerTomatesParTypeEtCouleur_AucuneCorrespondance() {
		// Filtrer les tomates par un type et une couleur sans correspondance
		List<Tomate> tomatesFiltrees = tomates.filtrerTomatesParTypeEtCouleur(TypeTomate.TOMATES_CERISES, Couleur.BLEU);

		// Vérifier que la liste filtrée est vide
		assertTrue(tomatesFiltrees.isEmpty());
	}

	@Test
	public void testFiltrerTomatesParTypeEtCouleur_CombinaisonValide() {
		// Filtrer les tomates par type "TOMATES" et couleur "ROUGE"
		List<Tomate> tomatesFiltrees = tomates.filtrerTomatesParTypeEtCouleur(TypeTomate.TOMATES, Couleur.ROUGE);

		// Vérifier le nombre de tomates filtrées
		assertEquals(1, tomatesFiltrees.size());

		// Vérifier les propriétés de la tomate filtrée
		Tomate tomateFiltree = tomatesFiltrees.get(0);
		assertEquals(TypeTomate.TOMATES, tomateFiltree.getTypeGraine());
		assertEquals(Couleur.ROUGE, tomateFiltree.getCouleur());
	}
}
