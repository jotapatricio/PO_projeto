package bci.core;

public class ActiveState implements UserState {
    private static final ActiveState INSTANCE = new ActiveState();

    private ActiveState() {}
    public static ActiveState getInstance() {
        return INSTANCE;
    }
    @Override
    public String getState() {
        return "ACTIVO";    
    }
    @Override
    public boolean canRequest() {
        return true;
    }
    @Override
    public void suspend(User user) {
        // Regra de Transição: Utente ACTIVO passa a SUSPENSO
        user.setState(SuspendedState.getInstance());
    }
    @Override
    public void reactivate(User user) {
        // Não faz nada, já está ativo
    }

}
