import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Semaphore;


public class Test {

	public final static int CONCURRENT_THREADS = 4;
	
	public static void hashFileGeneration(String filename, String[] passwords){
		Path path = Paths.get(filename);
		
		Hash hashFunction = new Hash("MD5");
		byte[] output = new byte[16*passwords.length];
			
			for(int i =0; i< passwords.length; i++){
				byte[] passHash =hashFunction.hashing(passwords[i]);
				for(int j=0; j<16; j++){
					output[16*i+j]=passHash[j];
				}
				
				
			}
			Auxiliary.printByteArray(output);
			try {
				Files.write(path, output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public static void testHashfileGeneration(){
		String[] passwords = new String[3];
		passwords[0]= "patate";
		passwords[1] = "S7ko2";
		passwords[2] = "run";
		Test.hashFileGeneration("hashData.dat", passwords);
	}
	
	public static void test1() throws InterruptedException{
		PassHashData data = new PassHashData("hashData.dat");
		for(int i=0; i<3;i++){
			Auxiliary.printByteArray(data.passHashList[i]);
		}
		PassTask[] tasks = new PassTask[3];
		tasks[0]= new PassTask(3, true, false, false);
		tasks[1]= new PassTask(6, true, false, false);
		tasks[2]= new PassTask(5, true, true, true);
		for (int i = 0; i<3;i++){
			
			softInterface.Update.updateGlobalTask(tasks[i].getname(), softInterface.Update.STARTING);
		}
		
		Semaphore semaphore = new Semaphore(CONCURRENT_THREADS);
		for (int i = 0; i<3;i++){
			
			Result.taskStarted(tasks[i]);
			while(!tasks[i].isDone()){
				
				semaphore.acquire();
				PassTaskThread newThread = tasks[i].nextThread();
				newThread.setSemaphore(semaphore);
				newThread.setPassHashData(data);
				newThread.setDaemon(true);
				newThread.start();
				
			}
			Result.taskOnGoing(tasks[i]);
		}
	}
	
}
