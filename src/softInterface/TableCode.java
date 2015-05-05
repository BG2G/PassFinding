package softInterface;

public class TableCode extends Table{
	
	private static final String[] name = {"Password","Hash", "Time"};
	private static final int nbCol = 3;
	private static final boolean editable = false;
	
	public TableCode(int nbLin,int nbLiVi) {
		super(nbCol, nbLin, nbLiVi,name, editable);
	}
	
	public void add(){
		// TODO
	}

	public void modify(){
		// TODO
	}
	
}
