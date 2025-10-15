package bci.core;

import java.io.Serializable;

class Date implements Serializable {

    private static final long serialVersionUID = 202501101348L;
    private int _currentDate;

    Date() {
        _currentDate = 1;
    }

    int getCurrentDate() {
        return _currentDate;
    }

    void advanceDays(int days) {
        if (days>=0) _currentDate += days;
        else return;
    }
}
