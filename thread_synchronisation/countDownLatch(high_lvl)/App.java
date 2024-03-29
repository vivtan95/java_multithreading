import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		
		System.out.println("Started.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
		
	}
	
}

public class App {
	
	public static void main(String[] args) {
		
		// Set number of countdowns for the threads to complete all their tasks
		// CountDownLatch is also Synchronization safe.
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<3; i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await(); // Latch awaits until all CountDownLatches have been counted down.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed.");
	}
}
