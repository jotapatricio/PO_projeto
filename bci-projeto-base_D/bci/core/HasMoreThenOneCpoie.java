package bci.core;

public class HasMoreThenOneCpoie extends Rule {
    public HasMoreThenOneCpoie(int id) { ///////////////////////////////
        super(3);
    }

    @Override
    boolean check(Library library, Work work, User user) {
        return library.getAvailableCopies(work) > 0;
    }
}
