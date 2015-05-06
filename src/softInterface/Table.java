package softInterface;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;



public class Table {

	private ArrayList list;
	private JTable tab;
	private JScrollPane scroll;
	private int col;
	private int lin;
	private int vis;
	private DynamicModel model;
	private boolean edit;

	public Table(int nbCol, int nbLin,int nbLiVi, String tag, boolean editable){

		list = new ArrayList();
		col = nbCol;
		lin = nbLin;
		edit = editable;
		vis = nbLiVi;
		model = new DynamicModel(tag, nbLin, editable);
		tab = new JTable();
		tab.setModel(model);
		if(editable==false){
			tab.setFocusable(false);
			tab.setRowSelectionAllowed(false);
		}
		scroll = new JScrollPane(tab);
		tab.setFillsViewportHeight(true);
		int hLi = 16;
		int width = (int) tab.getPreferredSize().getWidth()+1;
		int height = nbLiVi*hLi;
		Dimension dim = new Dimension(width,height);
		tab.setPreferredScrollableViewportSize(dim);

	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public JTable getTab() {
		return tab;
	}

	public void setTab(JTable tab) {
		this.tab = tab;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getLin() {
		return lin;
	}

	public void setLin(int lin) {
		this.lin = lin;
	}

	public int getVis() {
		return vis;
	}

	public void setVis(int vis) {
		this.vis = vis;
	}

	
	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public DynamicModel getModel() {
		return model;
	}

	public void setModel(DynamicModel model) {
		this.model = model;
	}

	public void addLine(String[] elements){
		model.addLine(elements);
	}

	public void modify(){
		// TODO
	}


}
