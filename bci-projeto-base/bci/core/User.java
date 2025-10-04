package bci.core;

import java.io.Serializable;

public class User implements Serializable {
    private int _id;
    private String _behavior;
    private boolean _isActive;
    private String _name;
    private String _email;
    private int _fine;

    User(int id, String name, String email){
        _id=id;
        _behavior="CUMPRIDOR";
        _name=name;
        _email=email;
        _fine=0;
        _isActive=true;
    }

    boolean _isActive(){
        return _isActive;
    }

    public int getID(){return _id;}

    void changeActive(){
        if(_isActive==true)_isActive=false;
        else _isActive=true;
    }

    public String toString(){
        if (_isActive==true)
            return _id + " - " + _name + " - " + _email + " - " + _behavior + " - " + _isActive;
        else
            return _id + " - " + _name + " - " + _email + " - " + _behavior + " - " + _isActive + " - EUR " + _fine;
    }

}