package com.devinsight.taskstracker.ServiceRequests;

import com.devinsight.taskstracker.Infrastructure.TaskTrackerApp;

public class Module {
    public static void register(TaskTrackerApp trackerApp){

        new LiveAccountService(trackerApp);

    }
}
