package com.packtpub.java7.concurrency.chapter4.recipe1.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This class simulates a server, for example, a web server, that receives
 * requests and uses a ThreadPoolExecutor to execute those requests
 *
 */
public class Server {
	
	/**
	 * ThreadPoolExecutors to manage the execution of the request
	 */
	private ThreadPoolExecutor executor;
	
	/**
	 * Constructor of the class. Creates the executor object
	 */
	public Server(){
		executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
	}
	
	/**
	 * This method is called when a request to the server is made. The 
	 * server uses the executor to execute the request that it receives
	 * @param task The request made to the server
	 */
	public void executeTask(Task task){
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: 线程池大小: %d\n",executor.getPoolSize());
		System.out.printf("Server: 活动线程数量: %d\n",executor.getActiveCount());
		System.out.printf("Server: 完成任务数量: %d\n",executor.getCompletedTaskCount());
		System.out.printf("Server: 线程池池中最大线程数: %d\n",executor.getCompletedTaskCount());
	}

	/**
	 * This method shuts down the executor
	 */
	public void endServer() {
		executor.shutdown();
	}

}
