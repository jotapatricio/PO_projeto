package bci.core;

public class HasMoreThenOneCopie extends Rule {
    public HasMoreThenOneCopie(int id) { ///////////////////////////////
        super(3);
    }

    @Override
    boolean check(Library library, Work work, User user) {
        return library.getAvailableCopies(work) > 0;
    }
}
