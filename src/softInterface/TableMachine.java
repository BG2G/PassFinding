package softInterface;

public class TableMachine extends Table {
	
	private static final String tag = "machine";
	private static final int nbCol = 4;
	private static final boolean editable = true;
	
	public TableMachine(int nbLin,int nbLiVi) {
		super(nbCol, nbLin, nbLiVi,tag, editable);
	}
	
	public void add(){
		// TODO
	}

	public void modify(){
		// TODO
	}

}
