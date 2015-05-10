package softInterface;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DynamicModel extends AbstractTableModel {

	private static final String[] nameCode = {"Password","Hash", "Time"};
	private static final String[] nameGT = {"Task #", "Starting Prefixe", "Ending Prefixe", "Lower Case", "Upper Case", "Numbers", "Special Characters", "Length", "Status", "Time"};
	private static final String[] nameLT = {"Lower Case", "Upper Case", "Numbers", "Special Characters", "Length", "Prefixe"};
	private static final String[] nameMachine = {"Machine Name","I.P.", "Status", "Time"};

	private boolean editable;
	private String[] colNames;
	private ArrayList<String[]> data;


	public DynamicModel(String tag, int nbLin, boolean edit){
		super();
		editable = edit;
		int nbCol = 0;
		if(tag.equals("code")){
			colNames = nameCode;
			nbCol = nameCode.length;
		}
		if(tag.equals("gt")){
			colNames = nameGT;
			nbCol = nameGT.length;
		}
		if(tag.equals("lt")){
			colNames = nameLT;
			nbCol = nameLT.length;
		}
		if(tag.equals("machine")){
			colNames = nameMachine;
			nbCol = nameMachine.length;
		}

		data = new ArrayList();


	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return editable;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }
	
	
	public void addLine(String[] cols) {
        data.add(cols);
 
        fireTableRowsInserted(data.size() -1, data.size() -1);
    }


}
