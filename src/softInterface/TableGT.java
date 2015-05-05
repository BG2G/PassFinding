package softInterface;

public class TableGT extends Table{

	private static final String[] name = {"Task #", "Lower Case", "Upper Case", "Numbers", "Special Characters", "Length", "Status", "Time"};
	private static final int nbCol = 8;
	private static final boolean editable = false;
	
	public TableGT(int nbLin,int nbLiVi) {
		super(nbCol, nbLin, nbLiVi,name, editable);
	}
	
	public void add(){
		// TODO
	}

	public void modify(){
		// TODO
	}
	
}
