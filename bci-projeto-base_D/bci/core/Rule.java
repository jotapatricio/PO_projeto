package bci.core;

public abstract class Rule {
    private final int _id;

    public Rule(int id) {
        _id = id;
    }
    public int getId() {
        return _id;
    }

    abstract boolean check(Library library, Work work, User user);
}