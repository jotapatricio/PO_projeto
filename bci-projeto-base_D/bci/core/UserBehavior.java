package bci.core;

import java.io.Serializable;

public interface UserBehavior extends Serializable {
    
    int getMaxWorks();
    int getReturnDeadline(Work work);
    String getBehaviorClass();

}

