package bci.core;

public class SuspendedState implements UserState {
    private static final SuspendedState INSTANCE = new SuspendedState();

    private SuspendedState() {}
    public static SuspendedState getInstance() {
        return INSTANCE;
    }
    @Override
    public String getState() {
        return "SUSPENSO";
    }
    @Override
    public boolean canRequest() {
        return false;
    }
    @Override
    public void reactivate(User user) {
        user.setState(ActiveState.getInstance());
    }
    @Override
    public void suspend(User user) {
    }

}
