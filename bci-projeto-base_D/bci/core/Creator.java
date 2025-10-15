package bci.core;

import java.io.Serializable;

public class Creator implements Serializable{
    private String _name;

    public Creator (String name){
        _name = name;
    }
    public String getName(){
        return _name;
    }
    public String toString(){
        return _name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return _name.equals(creator._name);
    }

    public int hashCode() {
        return _name.hashCode();
    }
}