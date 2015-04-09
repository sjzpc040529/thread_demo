 
public class JoinTest { 
	public static void main(String[] args) throws InterruptedException { 
		Thread t1 = new Thread() { 
			public void run() { 
				int i = 0; 
				while(i++<100) { 
					try { 
						Thread.sleep(1000); 
						} catch (InterruptedException e) { 
							e.printStackTrace(); 
							} 
					System.out.println("t1:"+i); 
					} 
				} 
			}; 
			t1.start(); 
			t1.join(); 
			for(int i = 0;i<100;i++) { 
				Thread.sleep(1000); 
				System.out.println("main:"+i); 
				} 
			} 
	}