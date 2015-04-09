package com.packtpub.java7.concurrency.chapter2.recipe3.task;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements an Event storage. Producers will storage
 * events in it and Consumers will process them. An event will
 * be a java.util.Date object
 *
 */
public class EventStorage {
   
	/**
	 * Maximum size of the storage
	 */
	private int maxSize;
	/**
	 * Storage of events
	 */
	private List<Date> storage;
	private int input=0 ;
	private int out=0 ;
	
	/**
	 * Constructor of the class. Initializes the attributes.
	 */
	public EventStorage(){
		maxSize=10;
		storage=new LinkedList<>();
	}
	
	/**
	 * This method creates and storage an event.
	 */
	public synchronized void set(){
			while (storage.size()==maxSize){
				try {
					
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			storage.add(new Date());
			input++ ;
			System.out.printf("Set: %d",storage.size());
			notify(); 
//			notifyAll();
	}
	
	/**
	 * This method delete the first event of the storage.
	 */
	public synchronized void get(){
			while (storage.size()==0){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			out++ ;
			System.out.printf("Get: %d: %s",storage.size(),((LinkedList<?>)storage).poll());
			notify();
//			notifyAll();
	}
	
	
	public String getInput(){
		return String.valueOf(input) ;
	}
	
	
	public String getOut(){
		return String.valueOf(out);
	}
	
}
