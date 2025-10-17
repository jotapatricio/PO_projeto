package bci.core;

import java.util.*;

public class Dvd extends Work {
    private final int _igac;

    public Dvd(int id, String title, int copies, Category category, int preco, int igac, List<Creator> diretor) {
        super(id, title, copies, category, preco, diretor);
        _igac = igac;
    }
    public int getIgac() {
        return _igac;
    }

    @Override
    public String toString() {
        return getId() + " - " + getCopies() + " de " + getCopies() + " - DVD - " + getTitle() + " - " + getPreco() + " - " 
        + getCategory() + " - " + getDirector() + " - " + getIgac();
    }
    
}