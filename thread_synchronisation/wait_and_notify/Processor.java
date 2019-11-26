import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized(this) {
			System.out.println("Producer thread running ...");
			wait(); // java object method and will handover the mutex(this)
			System.out.println("Resume.");
		}
	}
	
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		
		Thread.sleep(2000);
		
		synchronized(this) {
			System.out.println("Waiting for enter key ...");
			scanner.nextLine();
			System.out.println("Enter key has been pressed.");
			notify(); //synchronized code block method. This will notify all other threads that is waiting to be awake soon. However it does not handover mutexes until this code block is ran completely.	
			Thread.sleep(5000); // Simulate a 5secs task in this block to prove that it doesn't handover mutex
		}
	}
		
}
