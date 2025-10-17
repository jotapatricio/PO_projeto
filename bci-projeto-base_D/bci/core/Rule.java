package bci.core;

import bci.core.Library;
import bci.core.Work;
import bci.core.User;

public abstract class Rule {
    private final int id;

    public Rule(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    
    abstract boolean check(Library library, Work work, User user);
}