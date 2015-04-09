package com.packtpub.java7.concurrency.chapter2.recipe6.core;

import com.packtpub.java7.concurrency.chapter2.recipe6.task.Job;
import com.packtpub.java7.concurrency.chapter2.recipe6.task.PrintQueue;

/**
 * Main class of the example
 *
 */
public class Main {
	
	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main (String args[]){
		// Creates the print queue
		PrintQueue printQueue=new PrintQueue();
		
		// Cretes ten jobs and the Threads to run them
		Thread thread[]=new Thread[10];
		for (int i=0; i<10; i++){
			thread[i]=new Thread(new Job(printQueue),"Thread "+i);
		}
		
		// Launch a thread ever 0.1 seconds
		for (int i=0; i<10; i++){
			thread[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
