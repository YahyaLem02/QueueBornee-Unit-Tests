package src;

import java.util.ArrayList;

public class QueueBorneeInteger implements QueueBorneeIntegerInterface {
    private final ArrayList<Integer> queue;
    private final  int capacite;

    public QueueBorneeInteger(int capacite) throws IllegalArgumentException{
        if (capacite < 0){
            throw new IllegalArgumentException("La capacité doit être positive");
        }
        this.capacite = capacite;
        queue = new ArrayList<>();

    }

    @Override
    public int capacite() {
        return this.capacite;
    }

    @Override
    public int taille() {
        return queue.size();
    }

    @Override
    public boolean estVide() {
        return queue.isEmpty();
    }

    @Override
    public boolean estPleine() {
        return queue.size() == capacite;
    }

    @Override
    public void ajouteElement(int element) {
        if (estPleine()){
            throw new IllegalStateException("La queue est pleine");
        }
        queue.add(element);

    }

    @Override
    public int supprimeElement() {
        if (estVide()){
            throw new IllegalStateException("La queue est vide");
        }
        return queue.remove(0);
    }
}