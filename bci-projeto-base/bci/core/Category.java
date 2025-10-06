package bci.core;

import java.io.Serializable;
public enum Category implements Serializable{

     // 1. Constantes ENUM em maiúsculas e sem caracteres especiais/espaços
    FICTION("Ficção"),
    REFERENCE("Referência"),
    SCITECH("Técnica e Científica"); // O nome interno (constante) deve ser sem espaços

    // 2. Campo para armazenar o nome amigável (como aparece no ficheiro/output)
    private final String _displayName;

    // 3. Construtor
    Category(String displayName) {
        _displayName = displayName;
    }

    // 4. Método para devolver o nome amigável (usado no toString)
    @Override
    public String toString() {
        return _displayName;
    }

    public static Category fromDisplayName(String displayName) {
        // Itera sobre todas as constantes do enum
        for (Category c : Category.values()) {
            // Compara o nome de exibição (ignora maiúsculas/minúsculas para maior robustez, opcional)
            if (c._displayName.equalsIgnoreCase(displayName.trim())) {
                return c;
            }
        }
        // Se não encontrar, lança exceção (simula o comportamento do valueOf())
        throw new IllegalArgumentException("Categoria não reconhecida: " + displayName);
    }
}
