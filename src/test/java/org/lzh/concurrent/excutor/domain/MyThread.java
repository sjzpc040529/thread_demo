package org.lzh.concurrent.excutor.domain;
public class MyThread extends Thread {
	private String name ;
	public MyThread(String name){
		this.name = name ;
	}
     @Override
     public void run() {
         System.out.println("线程"+Thread.currentThread().getName() + "正在执行。。。"+"任务"+name);
     }
}