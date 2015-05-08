package mainPackage;



public class Main {

	public static void main(String[] args) {
		
		

		Test.testHashfileGeneration();
		
		try {
			Test.test1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
