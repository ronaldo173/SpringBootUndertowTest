package ua.ronaldo173.springbootundertowtest;

public class TestEvrth {
	public static void main(String[] args) {
		new Thread(new Z()).start();
	}

}

class Z extends Thread {
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(i);
		}
	}
}

interface Count {

	void count();
}

class GG implements Count {
	short counter = 0;

	public void count() {
		System.out.println("counter--" + counter);
		for (int i = 6; i > counter; i--, ++counter) {
			System.out.println(counter + "----i=" + i);
		}

	}

}

abstract class A1 {

}