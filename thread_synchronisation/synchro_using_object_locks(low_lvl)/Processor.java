import java.util.LinkedList;
import java.util.Random;

public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	final int LIMIT = 10;
	private Object lock = new Object(); //mutex object
	
	public void produce() throws InterruptedException {

		int value = 0;
		
		while(true) {
			
			synchronized (lock) {
				// Keep checking for condition
				while(list.size() == LIMIT) {
					lock.wait(); //call wait on the object
				}
				
				list.add(value++);
				lock.notify();
			}
		}
			
	}
	
	public void consume() throws InterruptedException {
		
		Random random = new Random();
		
		while(true) {
			
			synchronized(lock) {
				
				while(list.size() == 0) {
					lock.wait(); //call wait on the object
				}
				
				System.out.print("List size is:" + list.size());
				int value = list.removeFirst();
				System.out.println("; value is:" + value);
				lock.notify(); //use notifyAll if more than 1 thread
			}
			
			Thread.sleep(random.nextInt(1000));
			
		}
		
		
	}
		
}
