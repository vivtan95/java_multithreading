import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	// BlockingQueue is thread safe, auto synchronization (better than using low level synchronized keyword)
	// Setting max size of queue to 10
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);	
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}
	
	private static void producer() throws InterruptedException {
	
		Random random = new Random();
			
		while(true) {
			queue.put(random.nextInt(100));
		}
	}
	
	private static void consumer() throws InterruptedException {

		Random random = new Random();

		while(true) {
			Thread.sleep(100); //make consumer take an item 10 times a second
			
			if(random.nextInt(10) == 0) {
				Integer value = queue.take();
				
				System.out.println("Taken value: " + value + "; Queue size is " + queue.size());
			}
			
		}
	}
		
}
