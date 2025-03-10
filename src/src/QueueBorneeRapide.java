package src;

import src.QueueBorneeIntegerInterface;

public class QueueBorneeRapide implements QueueBorneeIntegerInterface {
    private final int[] queue;
    private final int capacite;
    private int taille;

    public QueueBorneeRapide(int capacite)  throws  IllegalArgumentException{
        if(capacite < 0){
            throw new IllegalArgumentException("La capacité doit être positive");
        }
        this.capacite = capacite;
        queue = new int[capacite];
        this.taille = 0;
    }

    @Override
    public int capacite() {
        return capacite;
    }

    @Override
    public int taille() {
        return this.taille;
    }

    public boolean estVide() {
        return this.taille == 0;
    }

    @Override
    public boolean estPleine() {
        return this.taille >= this.capacite;
    }
    @Override
    public void ajouteElement(int element) {
        if (estPleine()){
            throw new IllegalStateException("La queue est pleine");
        }
        this.queue[taille] = element;
        this.taille++;

    }

    @Override
    public int supprimeElement() {
        if (estVide()){
            throw new IllegalStateException("La queue est vide");
        }
        int element = this.queue[0];
        for (int i = 0; i < this.taille - 1; i++){
            this.queue[i] = this.queue[i + 1];
        }
        this.taille--;
        return element;
    }
}
