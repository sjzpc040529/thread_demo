/**
 * * 封装了数据生产工厂，该工厂中提供了生产和消费方法 * @author <a
 * href='wangwenjun62@gmail.com'>wangwenjun</a>
 * */
class Factory {
	private int i = 0;
	private boolean created = false;

	public synchronized void create() {
		while(created){
			try {
				wait();// (1)
			} catch (InterruptedException e) {

			}
		}
		i=i+1 ;
		System.out.println(Thread.currentThread().getName()
				+ "-create-" + i);
		this.created = true;
		notifyAll();
//		synchronized (this) {
//			if (created) {
//				try {
//					wait();// (1)
//				} catch (InterruptedException e) {
//
//				}
//			} else {
//				i = i + 1;
//				System.out.println(Thread.currentThread().getName()
//						+ "-create-" + i);
//				notify();// (2)
//				this.created = true;
//			}
//		}
	}

	public synchronized void consume() {
		while(created){
			System.out.println(Thread.currentThread().getName()
					+ "-consume-" + i);
			
			this.created = false;
			notifyAll();
		}
		try {
			wait();// (4)
		} catch (InterruptedException e) {

		}
//		synchronized (this) {
//			if (created)// (3)
//			{
//				System.out.println(Thread.currentThread().getName()
//						+ "-consume-" + i);
//				notify();
//				this.created = false;
//			} else {
//				try {
//					wait();// (4)
//				} catch (InterruptedException e) {
//
//				}
//			}
//		}
	}
}

/**
 * * 生产者与消费者的基类 * * @author <a href='wangwenjun62@gmail.com'>wangwenjun</a>
 * */
abstract class AbsFactory implements Runnable {
	protected Factory factory = null;

	public AbsFactory(Factory factory) {
		this.factory = factory;
	}

	abstract protected void execute();

	public void run() {
		while (true) {
			execute();
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//
//			}
		}
	}

}

class Producer extends AbsFactory {
	public Producer(Factory factory) {
		super(factory);
	}

	@Override
	protected void execute() {
		factory.create();
	}
}

class Consumer extends AbsFactory {
	public Consumer(Factory factory) {
		super(factory);
	}

	@Override
	protected void execute() {
		factory.consume();
	}
}

public class ProducerCustomer {
	public static void main(String[] args) {
//		if (args.length == 0) {
//			System.out
//					.println("Usage:java com.wenhuisoft.chapter4.ProducerCustomer number");
//			System.out.println("Please restart again....");
//			System.exit(0);
//		}
		int count = 0;

		try {
			count = Integer.parseInt("1");
		} catch (Throwable t) {
			System.out.println("Please enter a integer type number...");
			System.exit(0);
		}
		final Factory factory = new Factory();
		for (int i = 0; i < count; i++) {
			new Thread(new Producer(factory)).start();
			new Thread(new Consumer(factory)).start();
		}
	}
}