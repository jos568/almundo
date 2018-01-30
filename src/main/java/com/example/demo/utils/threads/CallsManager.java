package com.example.demo.utils.threads;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.example.demo.service.DispatcherService;

 public class CallsManager implements Job {
	
	 @Autowired
	    private DispatcherService service;

	    @Override
	    public void execute(JobExecutionContext jobExecutionContext) {
	        service.dispatcher();
	    }

}
