package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import src.QueueBorneeLente;

public class QueueBorneeLenteTest {

    private QueueBorneeLente queueVide;
    private QueueBorneeLente queueCapaciteZero;
    private QueueBorneeLente queueNormale;

    @Before
    public void setUp() {
        // Ce code s'exécute avant chaque méthode de test
        queueVide = new QueueBorneeLente(3);
        queueCapaciteZero = new QueueBorneeLente(0);
        queueNormale = new QueueBorneeLente(3);
        // Remplir la queue normale avec des éléments
        queueNormale.ajouteElement(1);
        queueNormale.ajouteElement(2);
    }

    @After
    public void tearDown() {
        // Ce code s'exécute après chaque méthode de test
        queueVide = null;
        queueCapaciteZero = null;
        queueNormale = null;
    }

    @Test
    public void testConstructeurAvecCapacitePositive() {
        assertEquals(3, queueVide.capacite());
        assertEquals(0, queueVide.taille());
        assertTrue(queueVide.estVide());
        assertFalse(queueVide.estPleine());
    }

    @Test
    public void testConstructeurAvecCapaciteZero() {
        assertEquals(0, queueCapaciteZero.capacite());
        assertEquals(0, queueCapaciteZero.taille());
        assertTrue(queueCapaciteZero.estVide());
        assertTrue(queueCapaciteZero.estPleine());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurAvecCapaciteNegative() {
        new QueueBorneeLente(-1);
    }

    @Test
    public void testAjouteElementDansQueueVide() {
        queueVide.ajouteElement(5);
        assertEquals(1, queueVide.taille());
        assertFalse(queueVide.estVide());
        assertFalse(queueVide.estPleine());
    }

    @Test
    public void testAjouteElementJusquaCapacite() {
        // La queue normale contient déjà 2 éléments
        queueNormale.ajouteElement(3);
        assertEquals(3, queueNormale.taille());
        assertFalse(queueNormale.estVide());
        assertTrue(queueNormale.estPleine());
    }

    @Test(expected = IllegalStateException.class)
    public void testAjouteElementDansQueuePleine() {
        // Remplir complètement la queue
        queueNormale.ajouteElement(3);
        // Cet ajout devrait lever une exception
        queueNormale.ajouteElement(4);
    }

    @Test
    public void testSupprimeElementDansQueueAvecUnElement() {
        QueueBorneeLente queueUnElement = new QueueBorneeLente(3);
        queueUnElement.ajouteElement(5);

        int element = queueUnElement.supprimeElement();
        assertEquals(5, element);
        assertEquals(0, queueUnElement.taille());
        assertTrue(queueUnElement.estVide());
    }

    @Test
    public void testSupprimeElementDansQueueAvecPlusieursElements() {
        // La queue normale contient déjà les éléments 1 et 2
        queueNormale.ajouteElement(3);

        int premier = queueNormale.supprimeElement();
        assertEquals(1, premier);
        assertEquals(2, queueNormale.taille());

        int deuxieme = queueNormale.supprimeElement();
        assertEquals(2, deuxieme);
        assertEquals(1, queueNormale.taille());

        int troisieme = queueNormale.supprimeElement();
        assertEquals(3, troisieme);
        assertEquals(0, queueNormale.taille());
        assertTrue(queueNormale.estVide());
    }

    @Test(expected = IllegalStateException.class)
    public void testSupprimeElementDansQueueVide() {
        queueVide.supprimeElement(); // Devrait lever une exception
    }

    @Test
    public void testCycleAjoutSuppressionMultiple() {
        // La queue normale contient déjà les éléments 1 et 2
        assertEquals(1, queueNormale.supprimeElement());

        queueNormale.ajouteElement(3);
        queueNormale.ajouteElement(4);

        assertEquals(2, queueNormale.supprimeElement());
        assertEquals(3, queueNormale.supprimeElement());
        assertEquals(4, queueNormale.supprimeElement());

        assertTrue(queueNormale.estVide());
    }

    @Test
    public void testComportementCirculaire() {
        // Créer une queue avec une capacité limitée pour tester le comportement circulaire
        QueueBorneeLente queueCirculaire = new QueueBorneeLente(3);

        // Remplir la queue
        queueCirculaire.ajouteElement(1);
        queueCirculaire.ajouteElement(2);
        queueCirculaire.ajouteElement(3);

        // Supprimer pour faire de la place
        assertEquals(1, queueCirculaire.supprimeElement());
        assertEquals(2, queueCirculaire.supprimeElement());

        // Ajouter de nouveaux éléments
        queueCirculaire.ajouteElement(4);
        queueCirculaire.ajouteElement(5);

        // Vérifier les éléments dans l'ordre
        assertEquals(3, queueCirculaire.supprimeElement());
        assertEquals(4, queueCirculaire.supprimeElement());
        assertEquals(5, queueCirculaire.supprimeElement());

        assertTrue(queueCirculaire.estVide());
    }

    @Test
    public void testComportementCirculaireComplet() {
        // Créer une queue avec une capacité limitée pour tester le comportement circulaire
        QueueBorneeLente queueCirculaire = new QueueBorneeLente(3);

        // Premier cycle
        queueCirculaire.ajouteElement(1);
        queueCirculaire.ajouteElement(2);
        queueCirculaire.ajouteElement(3);

        assertEquals(1, queueCirculaire.supprimeElement());
        assertEquals(2, queueCirculaire.supprimeElement());
        assertEquals(3, queueCirculaire.supprimeElement());

        // Deuxième cycle (pour tester le comportement circulaire)
        queueCirculaire.ajouteElement(4);
        queueCirculaire.ajouteElement(5);
        queueCirculaire.ajouteElement(6);

        assertEquals(4, queueCirculaire.supprimeElement());

        queueCirculaire.ajouteElement(7);

        assertEquals(5, queueCirculaire.supprimeElement());
        assertEquals(6, queueCirculaire.supprimeElement());
        assertEquals(7, queueCirculaire.supprimeElement());

        assertTrue(queueCirculaire.estVide());
    }
}