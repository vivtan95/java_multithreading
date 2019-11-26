import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Thread starting ...");
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Random random = new Random();
				
				// simulate a long process
				for(int i=0; i<1E8; i++) {
					
					//Check if thread is interrupted each time it loops
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted!");
						break;
					}
					
					// This would also cause an interrupt exception
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						System.out.println("Interrupted!");
//						break;
//					}
					
					Math.sin(random.nextDouble());
				}
			}
			
		});
		t1.start();
		
		Thread.sleep(500);
		
		t1.interrupt(); //does not stop the thread running
		
		t1.join();
		
		System.out.println("Thread finished.");
	}		
	
}