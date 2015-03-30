import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;


public class Hash {
	
	
	

	private String hashAlgorithm = "MD5";
	
	public Hash(String algo){
		this.hashAlgorithm = algo;
		
	}
	
	public Hash(){
		
	}
	
	public byte[] hashing (String password){
		if(this.hashAlgorithm.equals("MD5")){
			return DigestUtils.md5(password);
		}else if(this.hashAlgorithm.equals("SHA1")){
			return DigestUtils.sha1(password);
		}
		return DigestUtils.md5(password);
	} 
	

}
