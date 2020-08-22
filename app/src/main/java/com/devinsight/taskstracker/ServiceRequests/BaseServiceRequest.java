package com.devinsight.taskstracker.ServiceRequests;

import com.devinsight.taskstracker.Infrastructure.TaskTrackerApp;
import com.squareup.otto.Bus;

public class BaseServiceRequest {

    protected TaskTrackerApp trackerApp;
    protected Bus bus;


    public BaseServiceRequest(TaskTrackerApp trackerApp) {
        this.trackerApp = trackerApp;
        bus=trackerApp.getBus();
        bus.register(this);
    }
}
