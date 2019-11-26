import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition(); //get condition obj from lock
	
	private void increment() {
		for(int i=0; i<10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		
		System.out.println("Waiting ...");
		cond.await();
		
		System.out.println("Woken up");
		
		try {
			increment();
		}
		finally { //finally will definitely run even if there is an exception in try block
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException{
		
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press Enter key.");
		new Scanner(System.in).nextLine();
		System.out.println("Enter key pressed.");
		
		cond.signal(); //awakens the cond that is waiting
		
		try {
			increment();
		}
		finally { //finally will definitely run even if there is an exception in try block
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Count is: " + count);
	}
	
}
