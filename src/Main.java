import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;


public class Main {

	public static void main(String[] args) {
		
		
		//Hash hash = new Hash();
	
		System.out.println(DigestUtils.md5("abc".getBytes(StandardCharsets.UTF_8)));
		System.out.println(DigestUtils.md5("sedrfghjkrewh".getBytes(StandardCharsets.UTF_8)));
		System.out.println(DigestUtils.md5("abc".getBytes(StandardCharsets.UTF_8)));
		System.out.println(DigestUtils.md5("abc".getBytes(StandardCharsets.UTF_8)));
		/*
		try {
			Test.test1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true);
		*/
		
		//Test.testHashfileGeneration();
	}

}
