package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import src.QueueBorneeRapide;

public class QueueBorneeRapideTest {

    private QueueBorneeRapide queueVide;
    private QueueBorneeRapide queueCapaciteZero;
    private QueueBorneeRapide queueNormale;

    @Before
    public void setUp() {
        queueVide = new QueueBorneeRapide(3);
        queueCapaciteZero = new QueueBorneeRapide(0);
        queueNormale = new QueueBorneeRapide(3);
        queueNormale.ajouteElement(1);
        queueNormale.ajouteElement(2);
    }

    @After
    public void tearDown() {

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
        new QueueBorneeRapide(-1);
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
        QueueBorneeRapide queueUnElement = new QueueBorneeRapide(3);
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
    public void testDecalageElementsApresSuppression() {
        // La queue normale contient déjà les éléments 1 et 2
        queueNormale.ajouteElement(3);

        queueNormale.supprimeElement(); // Supprime 1
        queueNormale.ajouteElement(4); // Ajoute 4 (maintenant la queue contient: 2, 3, 4)

        assertEquals(2, queueNormale.supprimeElement());
        assertEquals(3, queueNormale.supprimeElement());
        assertEquals(4, queueNormale.supprimeElement());

        assertTrue(queueNormale.estVide());
    }
}