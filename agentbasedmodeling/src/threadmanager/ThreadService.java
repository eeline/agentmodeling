package threadmanager;

import handler.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import outputservice.OutputService;

import dao.SQLTask;
/** 
 *
 * Executor service handles the Runnable tasks anonymously.
 * 
 */

public class ThreadService {
	private static final ThreadService INSTANCE = new ThreadService();
	private static ExecutorService queue = Executors.newCachedThreadPool();
	
	private ThreadService(){
		if (INSTANCE != null){
			throw new IllegalStateException("Already Instantiated");
		}
	}
	 
	public static ThreadService getInstance(){
		return ThreadService.INSTANCE;
	}
	public static void push(Task task) throws InterruptedException{
		queue.execute(task);
	}
	
	public static void push(SQLTask task){
		queue.execute(task);
	}
	
	//TODO THINK OF A LESS GHETTO SOLUTION
	public static void die(){
		queue.shutdown();
		try {
			Thread.sleep(10000);
			OutputService.cleanup();
			if(!queue.awaitTermination(60, TimeUnit.SECONDS))
				queue.shutdownNow();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
