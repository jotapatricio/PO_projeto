package bci.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Work implements Serializable{
    private int _id;
    private int _copies;
    private String _title;
    private int _preco;
    private Category _category;
    private List <Creator> _creators;

    public Work (int id, String title, int copies, Category category, int preco, List<Creator> creators){
        _id = id;
        _copies = copies;
        _title = title;
        _preco = preco;
        _category = category;
        _creators = new ArrayList<>(creators);

    }

    public int getId(){
        return _id;
    }
    
    public int getCopies(){
        return _copies;
    }

    public String getTitle(){
        return _title;
    }

    public int getPreco(){
        return _preco;
    }   

    public Category getCategory(){
        return _category;
    }

    public List <Creator> getCreators(){
        return Collections.unmodifiableList(_creators);
    }

    public Creator getDirector() {
        return getCreators().get(0);
    }

    public abstract String toString();
}
