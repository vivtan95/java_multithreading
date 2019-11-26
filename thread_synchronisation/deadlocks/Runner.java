import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
	
		while(true) {
			// Acquire locks
			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try {
				gotFirstLock = firstLock.tryLock(); //return true if it got the lock
				gotSecondLock = secondLock.tryLock();
			} finally {
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				
				//Check each lock to unlock it
				if(gotFirstLock) {
					firstLock.unlock();
				}
				
				if(gotSecondLock) {
					secondLock.unlock();
				}
			}
			
			// Locks not acquired
			Thread.sleep(1);
			
			
		}
		
	}
	
	public void firstThread() throws InterruptedException{
		
		Random random = new Random();
		
		for(int i=0; i<10000; i++) {
			
			acquireLocks(lock1, lock2);
			
			try {
			Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
		
	}
	
	public void secondThread() throws InterruptedException{
		Random random = new Random();
		
		for(int i=0; i<10000; i++) {
			
			// order is impt if not using acquireLock, will cause deadlock situations
			acquireLocks(lock2, lock1);
			
			try {
			Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished() {
		System.out.println("Acc1 bal: " + acc1.getBalance());
		System.out.println("Acc2 bal: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
	
}
