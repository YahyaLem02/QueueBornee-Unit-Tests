package src;

import src.QueueBorneeIntegerInterface;

/**
 * Implémentation d'une queue bornée d'entiers utilisant un tableau normal
 * avec l'élément en tête pouvant se trouver n'importe où dans le tableau.
 */
public class QueueBorneeLente implements QueueBorneeIntegerInterface {

    private final int capacite;
    private final int[] elements;
    private int taille;
    private int debut;
    private int fin;


    public QueueBorneeLente(int capacite) {
        if (capacite < 0) {
            throw new IllegalArgumentException("La capacité ne peut pas être négative");
        }
        this.capacite = capacite;
        this.elements = new int[capacite];
        this.taille = 0;
        this.debut = 0;
        this.fin = 0;
    }

    @Override
    public int capacite() {
        return this.capacite;
    }

    @Override
    public int taille() {
        return this.taille;
    }

    @Override
    public boolean estVide() {
        return this.taille == 0;
    }

    @Override
    public boolean estPleine() {
        return this.taille >= this.capacite;
    }

    @Override
    public void ajouteElement(int element) throws IllegalStateException {
        if (estPleine()) {
            throw new IllegalStateException("La queue est pleine");
        }

        this.elements[this.fin] = element;
        this.fin = (this.fin + 1) % this.capacite;
        this.taille++;
    }

    @Override
    public int supprimeElement() throws IllegalStateException {
        if (estVide()) {
            throw new IllegalStateException("La queue est vide");
        }

        int element = this.elements[this.debut];
        this.debut = (this.debut + 1) % this.capacite;
        this.taille--;

        return element;
    }
}