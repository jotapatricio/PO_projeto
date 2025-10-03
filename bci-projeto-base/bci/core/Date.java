package bci.core;

import java.io.Serializable;

class Date implements Serializable {
    @java.io.Serial 
    private static final long serialVersionUID = 202501101348L;

    private int _currentDate;

    Date() {
        _currentDate = 0;
    }

    int getCurrentDate() {
        return _currentDate;
    }

    void advanceDays(int days) {
        _currentDate += days;
    }
}
