package bci.core;

import java.io.*;

public class Requests implements Serializable {
    private Date _deaedlineDate;
    private Date _requestDate;
    private User _user;
    private Work _work;

    public Requests(User user, Work work, Date requestDate, Date deadlineDate) {
        this._user = user;
        this._work = work;
        this._requestDate = requestDate;
        this._deaedlineDate = deadlineDate;
    }

    public User getUser() {
        return _user;
    }

    public Work getWork() {
        return _work;
    }

    public Date getRequestDate() {
        return _requestDate;
    }

    public Date getDeadlineDate() {
        return _deaedlineDate;
    }

}
