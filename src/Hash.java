import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {
	
	
	
	private MessageDigest md;
	private String hashAlgorithm = "MD5";
	
	public Hash(String algo){
		this.hashAlgorithm = algo;
		try {
			this.md = MessageDigest.getInstance(algo);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Hash(){
		try {
			this.md = MessageDigest.getInstance(hashAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] hashing (String password){
		this.md.reset();
		this.md.update(password.getBytes());
		return md.digest();
	}
	

}
