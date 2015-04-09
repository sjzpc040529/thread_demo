class ClassA {
	public synchronized void A() {

		System.out.println("AAAAAAAAAAAAAAAAA");
		while (true) { // do nothing
		}
	}

	public synchronized void B() {
		System.out.println("BBBBBBBBBBBBBBBBB");
		while (true) {

		}
	}
}

public class MethodSynchronizedTest {
	public static void main(String[] args) {
		final ClassA clazz = new ClassA(); // 启动一个线程
		new Thread(new Runnable() {
			public void run() {
				clazz.A();
			}
		}).start(); // 启动另一个线程
		new Thread(new Runnable() {
			public void run() {
				clazz.B();
			}
		}).start();
	}

}
