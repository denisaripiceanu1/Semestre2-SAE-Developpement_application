package modele;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanierTests {

	private Panier panier;
	private Tomate tomate1;
	private Tomate tomate2;
	private Tomate tomate3;


	@Before
	public void setUp() {
		panier = new Panier();
		tomate1 = new Tomate(TypeTomate.TOMATES_CERISES, Couleur.ROUGE, "Tomate Miel du Mexique", null, "IMG_5460",
				"Description de la tomate 1", 50, 3.95F, true);
		tomate2 = new Tomate(TypeTomate.TOMATES, Couleur.ROUGE, "Tomate Joie de la Table", null,
				"Tomate-Joie-de-la-Table-ressemble-scaled", "Description de la tomate 2", 50, 3.95F, true);
		tomate3 = new Tomate(TypeTomate.TOMATES_CERISES, Couleur.ROUGE, "Tomate Black Zebra", null, "IMG_0130-scaled",
				"C'est une petite tomate cocktail ronde, pourpre et rayée de vert. Les fruits sont très nombreux et regroupés en grappes, de 6 à 8 fruits.\r\n"
						+ "\r\n" + "Variété précoce avec une saveur très douce.",
				50, 3.95F, true);

	}

	@Test
	public void testAjouterTomate() {
		// Cas 1: Ajout d'une tomate pour la première fois
		panier.ajouterTomate(tomate1, 3);
		assertFalse(Panier.tomateDouble);

		// Cas 2: Ajout d'une tomate déjà présente dans le panier
		panier.ajouterTomate(tomate1, 2);
		assertTrue(Panier.tomateDouble);
	}

	@Test
	public void testMiseAJour() {
		// Cas : ajouter une seule tomate
		panier.ajouterTomate(tomate3, 3);
		panier.miseAJour();

		assertEquals(1, panier.getTableau().size());
		assertEquals(11.85F, panier.getTotal(), 0.001);
		
		// Cas : ajouter deux fois une tomate du meme type
		panier.ajouterTomate(tomate3, 3);
		panier.ajouterTomate(tomate3, 3);
		panier.miseAJour();

		assertEquals(1, panier.getTableau().size());
		
		 // Cas : Ajout de plusieurs tomates différentes
	    panier.ajouterTomate(tomate1, 3);
	    panier.ajouterTomate(tomate2, 2);
	    panier.miseAJour();

	    assertEquals(2, panier.getTableau().size());

	    // Cas : Modification de la quantité d'une tomate déjà présente dans le panier
	    panier.ajouterTomate(tomate1, 1);
	    panier.miseAJour();

	    assertEquals(2, panier.getTableau().size());

	    // Cas 3: Modification de la quantité d'une tomate déjà présente dans le panier
	    // avec une valeur initiale non nulle
	    panier.ajouterTomate(tomate1, 2);
	    panier.miseAJour();

	    assertEquals(2, panier.getTableau().size());
	}

	@Test
	public void testViderPanier() {
		// Cas 1: Vider un panier vide
		panier.viderPanier();

		assertEquals(0, panier.getTableau().size());
		assertEquals(0, panier.getTotal(), 0.001);

		// Cas 2: Vider un panier avec des tomates
		panier.ajouterTomate(tomate1, 3);
		panier.ajouterTomate(tomate2, 2);
		panier.viderPanier();

		assertEquals(0, panier.getTableau().size());
		assertEquals(0, panier.getTotal(), 0.001);
	}

	@Test
	public void testTotalEnStringPanierNul() {
		assertEquals("0.0 €", panier.totalEnString());
	}

	@Test
	public void testTotalEnStringPanierUneTomate1() {
		panier.ajouterTomate(tomate1, 1);
		panier.miseAJour();
		assertEquals("3.95 €", panier.totalEnString());
	}

}
