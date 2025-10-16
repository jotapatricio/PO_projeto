package bci.core;

public class NormalBehavior implements UserBehavior {
    private static final int MAX_WORKS = 3;

    @Override
    public int getMaxWorks() {
        return MAX_WORKS;
    }

    @Override
    public int getReturnDeadline(Work work) {
        int copies = work.getCopies();
        
        if (copies == 1) {
            return 3; 
        } else if (copies <= 5) {
            return 8; 
        } else { 
            return 15; 
        }
    }

    @Override
    public String getBehaviorClass() {
        return "NORMAL";
    }

}
