package bci.core;

public class FaltosoBehavior implements UserBehavior {
    private static final int MAX_WORKS = 1;
    private static final int RETURN_DEADLINE = 2;

    @Override
    public int getMaxWorks() {
        return MAX_WORKS;
    }

    @Override
    public int getReturnDeadline(Work work) {
        return RETURN_DEADLINE;
    }

    @Override
    public String getBehaviorClass() {
        return "FALTOSO";
    }

}