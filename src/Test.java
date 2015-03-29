import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Semaphore;


public class Test {

	public final static int CONCURRENT_THREADS = 20;
	
	public static void hashFileGeneration(String filename, String[] passwords){
		PrintWriter writer;
		Hash hashFunction = new Hash("MD5");
		try {
			writer = new PrintWriter(filename, "UTF-8");
			for(int i =0; i< passwords.length; i++){
				writer.println(hashFunction.hashing(passwords[i]));
			}
			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void testHashfileGeneration(){
		String[] passwords = new String[3];
		passwords[0]= "patate";
		passwords[1] = "run";
		passwords[2] = "S7ko2";
		Test.hashFileGeneration("hashData.txt", passwords);
	}
	
	public static void test1() throws InterruptedException{
		PassHashData data = new PassHashData("hashData.txt");
		PassTask[] tasks = new PassTask[3];
		tasks[0]= new PassTask(3, true, false, false);
		tasks[1]= new PassTask(6, true, false, false);
		tasks[2]= new PassTask(5, true, true, true);
		
		Semaphore semaphore = new Semaphore(CONCURRENT_THREADS);
		for (int i = 0; i<3;i++){
			while(!tasks[i].isDone()){
				
				semaphore.acquire();
				PassTaskThread newThread = tasks[i].nextThread();
				newThread.setSemaphore(semaphore);
				newThread.setPassHashData(data);
				newThread.setDaemon(true);
				newThread.start();
				
			}
		}
	}
	
}
