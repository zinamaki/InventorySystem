import java.io.IOException;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

class CustomTableModel extends AbstractTableModel {
	// the following avoids a "warning" with Java 1.5.0 complier (?)
	static final long serialVersionUID = 42L;

	private Object[][] rowData; // the table's data
	private Object[] columnNames; // column header names

	private boolean editable[]; // true for any column that is editable
	private int sortedColumn; // column on which table is currently sorted
	private boolean sortOrder; // true = ascending, false = descending

	private boolean partsTable;

	public CustomTableModel(Object[][] rowData, Object[] columnNames, boolean partsTable) {
		this.rowData = rowData;
		this.columnNames = columnNames;
		editable = new boolean[rowData.length];

		this.partsTable = partsTable;

		sortedColumn = -1; // means table not sorted initially
	}
/*
	public void sort(int idx) {
		if (sortedColumn == idx)
			sortOrder = !sortOrder; // reverse the sort (clicked again)
		else
			sortOrder = true; // ascending sort (first click)

		sortedColumn = idx;

		Arrays.sort(rowData, new CustomComparator(idx, sortOrder));
	}
*/
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return rowData.length;
	}

	@Override
	public String getColumnName(int idx) {
		return (String) columnNames[idx];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return rowData[row][col];
	}

	@Override
	public Class<? extends Object> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		rowData[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return editable[col];
	}

	public void setColumnEditable(int colArg, boolean editableArg) {
		editable[colArg] = editableArg;
	}

	public void refresh() {
		try {

			if (this.partsTable) {
				this.rowData = Excel.getSearchRowData();
			} else {
				this.rowData = Excel.getManufacturerRowData();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireTableDataChanged();
	}
	
	public void refreshSearch(Integer columntosearch,String searchquery) {
		try {

			Object[][] rowData = Excel.getRowsMatching(columntosearch,searchquery);
			
			if(rowData != null){
				this.rowData = rowData;
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireTableDataChanged();
	}
	
	

}