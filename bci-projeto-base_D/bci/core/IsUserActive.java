package bci.core;

public class IsUserActive extends Rule {
    public IsUserActive(int id) { ///////////////////////////////
        super(2);
    }

    @Override
    boolean check(Library library, Work work, User user) {
        return user.isActiveBoolean();
    }
}