package bci.core;

import java.io.Serializable;

public class User implements Serializable {
    private final int _id;
    private String _behavior;
    private String _isActive;
    private String _name;
    private String _email;
    private int _fine;

    User(int id, String name, String email){
        _id=id;
        _behavior="NORMAL";
        _name=name;
        _email=email;
        _fine=0;
        _isActive="ACTIVO";
    }

    String _isActive(){
        return _isActive;
    }

    public int getID(){return _id;}

    void changeActive(){
        if (_isActive.equals("ACTIVO"))
            _isActive = "SUSPENSO";
        else
            _isActive = "ACTIVO";
    }
    public String getName(){
        return _name;
    }

    public String getEmail(){
        return _email;
    }

    public String getBehavior() {
        return _behavior;
    }

    public void setBehavior(String behavior) {
        _behavior = behavior;
    }

    public int getFine() {
        return _fine;
    }

    public void addFine(int amount) {
        _fine += amount;
    }

    public void payFine() {
        _fine = 0;
    }

    public String toString(){
        if (_isActive.equals("ACTIVO"))
            return _id + " - " + _name + " - " + _email + " - " + _behavior + " - " + _isActive;
        else
            return _id + " - " + _name + " - " + _email + " - " + _behavior + " - " + _isActive + " - EUR " + _fine;
    }

}