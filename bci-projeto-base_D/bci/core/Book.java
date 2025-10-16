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
        for (Creator c : creators) {
            sb.append(c.getName()).append("; ");
        }
        
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); 
        }

        return sb.toString();
    }
    
    public String toString() {
        return getId() + " - " + getCopies() + " de " + getCopies() + " - Livro - " + getTitle() + " - " + getPreco() + " - " + 
        getCategory() + " - " + formatCreators() + " - " + getIsbn();
    }

}
