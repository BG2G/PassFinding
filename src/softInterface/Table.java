package softInterface;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;



public class Table {

	private ArrayList list;
	private JTable tab;
	private JScrollPane scroll;

	public Table(int nbCol, int nbLin,int nbLiVi, String[] name, boolean editable){

		list = new ArrayList();
		String data[][]= new String[nbLin][nbCol];
		if(editable==false){
			tab = new JTable();
			tab.setModel(new NonEditableModel(data,name));
			tab.setFocusable(false);
			tab.setRowSelectionAllowed(false);
		}else{
			tab = new JTable(data,name);
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

	public void add(){
		// TODO
	}

	public void modify(){
		// TODO
	}


}
