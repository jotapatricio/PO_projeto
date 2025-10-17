package bci.core;

import java.io.Serializable;

public class User implements Serializable {
    private final int _id;
    private UserBehavior _behavior;
    private UserState _state;
    private String _name;
    private String _email;
    private int _fine;

    User(int id, String name, String email){
        _id = id;
        _behavior = new NormalBehavior();
        _name = name;
        _email = email;
        _fine = 0;
        _state = ActiveState.getInstance();
    }


    public int getID(){return _id;}

    public void suspend() {
        _state.suspend(this);
    }

    public void reactivate() {
        _state.reactivate(this);
    }

    public void setState(UserState newState) {
        _state = newState;
    }

    public boolean canRequest() {
        return _state.canRequest();
    }

    public UserState getState() {
        return _state;
    }

    public String getEmail(){
        return _email;
    }

    public String getName() {
    return _name;
}

    public String getBehavior() {
        return _behavior.getBehaviorClass();
    }

    public void setBehavior(UserBehavior behavior) {
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
    public UserBehavior getUserBehavior() {
        return _behavior;
    }

    public String toString(){
        // O estado (ACTIVO ou SUSPENSO) agora é obtido do objeto _state
        String stateName = _state.getState();
        String suspensionText = stateName.equals("SUSPENSO") ? " (Suspenso)" : "";
        
        return _id + " - " + _name + " - " + _email + " - " + getBehavior() + " - " + stateName + suspensionText;
    }

}