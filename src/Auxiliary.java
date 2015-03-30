
public  class Auxiliary {

	public static void sort(byte[][] array){
		
		//TODO implement a better algorithm
		
		byte[][] sortingArray = array.clone();
		for(int i = 0; i<array.length;i++){
			for (int j =i; j<array.length-1;j++){
				if(!compare(sortingArray[i], sortingArray[j])){
					byte[] temp = new byte[(sortingArray[i]).length];
					transfer(sortingArray[i], temp);
					transfer(sortingArray[j], sortingArray[i]);
					transfer(temp, sortingArray[j]);
				}
			}
		}
		
	}
	
	public static void transfer(byte[] a, byte[]b){
		for (int i =0; i< a.length;i++){
			a[i]=b[i];
		}
	}
	
	public static int search(byte[][] array, byte[] element){
		for (int i =0; i< array.length; i++){
			if (array[i].equals(element)){
				return i;
			}
		}
		return -1;
	}
	
	public static boolean contains(byte[][] array, byte[] element){
		for (int i =0; i< array.length; i++){
			if (areEquals(array[i],element)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean compare(byte[] a, byte[] b){
		
		int n = a.length;
		for(int i =n-1; i>=0;i--){
			if(a[i]>b[i]){
				return true;
			}
			if(b[i]>a[i]){
				return false;
			}
		}
		
		
		return true;
	}
	
	public static boolean areEquals(byte[] a, byte[] b){
		
		int n = a.length;
		if(n!= b.length){
			return false;
		}
		for(int i =0; i<n;i++){
			if(a[i]!=b[i]){
				return false;
			}
			
		}		
		
		return true;
	}
	
	public static void printByteArray(byte[] a){
		for(int i =0; i<a.length; i++){
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
