package bci.core;

import java.io.Serializable;
public enum Category implements Serializable{

    //Constantes ENUM 
    FICTION("Ficção"),
    REFERENCE("Referência"),
    SCITECH("Técnica e Científica"); 

    
    private final String _displayName;

    //Construtor
    Category(String displayName) {
        _displayName = displayName;
    }

    public String toString() {
        return _displayName;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category c : Category.values()) {
            if (c._displayName.equalsIgnoreCase(displayName.trim())) {
                return c;
            }
        }
        throw new IllegalArgumentException("Categoria não reconhecida: " + displayName);
    }
}
