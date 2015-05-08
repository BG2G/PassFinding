package mainPackage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PassHashData {
	
	public byte[][] passHashList;
	public String hashAlgorithm = "MD5";
	
	public PassHashData(String filename, String algo){
	
		Path path = Paths.get(filename);
		try {
			byte[] data = Files.readAllBytes(path);
			int n = data.length/16;
			System.out.println("data length : "+data.length);
			// TODO implement SHA1
			if(algo.equals("MD5")){
				this.passHashList = new byte[n][16];
				for(int i =0; i<n ; i++){
					for(int j=0; j<16; j++){
						this.passHashList[i][j]=data[16*i+j];
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO
		//Auxiliary.sort(this.passHashList);
		
	    
	    this.hashAlgorithm = algo;
	        
		
	}
	
	public PassHashData(String filename){
		this(filename, "MD5");
	}
	
	public PassHashData(String hashAlgorithm, byte[][] passHashList){
		this.hashAlgorithm = hashAlgorithm;
		this.passHashList = passHashList;
	}
	
	public void setHashAlgorithm (String algo){
		this.hashAlgorithm = algo;
	}
	public String getAlgorithm(){
		return this.hashAlgorithm;
	}
	
	public void removeElement(byte[] element){
		int i = Auxiliary.searchIndex(this.passHashList, element);
		if(i>=0){
			int n=this.passHashList.length;
			byte[][] newPassHashList = new byte[n-1][element.length];
			boolean b =false;
			for(int j = 0; j <n;j++){
				if(j!=i){
					if(b){
						Auxiliary.transfer(this.passHashList[j], newPassHashList[j-1]);
					}else{
						Auxiliary.transfer(this.passHashList[j], newPassHashList[j]);
					}
				}else{
					b =true;
				}
			}
			this.passHashList = newPassHashList;
			for(int k=0; k<newPassHashList.length; k++){
				Auxiliary.printByteArray(newPassHashList[k]);
			}
		}
		
	}

}
