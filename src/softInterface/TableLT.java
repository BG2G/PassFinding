package softInterface;

public class TableLT extends Table {
	
	private static final String[] name = {"Thread #", "Type", "Length", "Prefixe", "Status", "Time"};
	private static final int nbCol = 6;
	private static final boolean editable = false;
	
	public TableLT(int nbLin,int nbLiVi) {
		super(nbCol, nbLin, nbLiVi,name, editable);
	}
	
	public void add(){
		// TODO
	}

	public void modify(){
		// TODO
	}

}
