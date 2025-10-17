package bci.core;

import java.io.Serializable;

public interface UserState extends Serializable {
    String getState();
    boolean canRequest();
    void reactivate(User user);
    void suspend(User user);

}
