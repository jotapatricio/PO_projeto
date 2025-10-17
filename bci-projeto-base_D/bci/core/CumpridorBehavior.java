package bci.core;

public class CumpridorBehavior implements UserBehavior {
    private static final int MAX_WORKS = 5;

    @Override
    public int getMaxWorks() {
        return MAX_WORKS;
    }

    @Override
    public int getReturnDeadline(Work work) {
        int copies = work.getCopies();
        
        if (copies == 1) {
            return 8; 
        } else if (copies <= 5) {
            return 15; 
        } else { 
            return 30; 
        }
    }

    @Override
    public String getBehaviorClass() {
        return "CUMPRIDOR";
    }

}
