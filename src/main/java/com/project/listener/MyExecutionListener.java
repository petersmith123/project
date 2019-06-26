package com.project.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class MyExecutionListener implements ExecutionListener {
    public void notify(DelegateExecution execution) throws Exception {
        String name=execution.getEventName();
        String key= execution.getProcessBusinessKey();
        String activitiName=execution.getCurrentActivityName();
        System.out.println(name);
        System.out.println(key);
        System.out.println(activitiName);

    }
}
