package bci.core;

public class HasRequestedWork extends Rule {
    public HasRequestedWork(int id) {
        super(1);
    }

    @Override
    boolean check(Library library, Work work, User user) {
        return library.userHasRequestedWork(user, work);
    }

}
