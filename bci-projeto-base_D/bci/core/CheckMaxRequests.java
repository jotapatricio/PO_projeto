package bci.core;

import java.util.List;

public class CheckMaxRequests extends Rule {
    // Regra 4, para ordenação
    public CheckMaxRequests() { 
        super(4); 
    }

    @Override
    boolean check(Library library, Work work, User user) {
        int max;
        
        switch (user.getBehavior()) {
            case CUMPRIDOR: max = 5; break;
            case FALTOSO:   max = 1; break;
            default:        max = 3; break; // NORMAL
        }
        List<Requests> openRequests = library.getRequestsByUser(user);
        
        int currentRequests = openRequests.size();
        
        if (currentRequests >= max) {
            return false; 
        }
        return true;
    }

}
