package bci.core;

import java.util.*;

public class Book extends Work {
    private final String _isbn;

    public Book(int id, String title, int copies, Category category, int preco, String isbn, List<Creator> creators) {
        super(id, title, copies, category, preco, creators);
        _isbn = isbn;
    }

    public String getIsbn() {
        return _isbn;
    }

    private String formatCreators() {
        StringBuilder sb = new StringBuilder();
        List<Creator> creators = getCreators();
        for (int i = 0; i < creators.size(); i++) {
            sb.append(creators.get(i).getName());
            if (i < creators.size() - 1) {
                sb.append("; ");
            }
        }
        return sb.toString();
    }
    
    public String toString() {
        return getId() + " - " + getCopies() + " de " + getCopies() + " - Livro - " + getTitle() + " - " + getPreco() + " - " + 
        getCategory() + " - " + formatCreators() + " - " + getIsbn();
    }

}