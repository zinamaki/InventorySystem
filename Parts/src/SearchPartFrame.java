import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JSeparator;
import javax.swing.JTable;

public class SearchPartFrame extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	public static JTextField textfield_partname;
	public static JComboBox comboBox_cat;
	private JButton btnBack;
	private JButton btnSearch;
	private JButton btnEditPart;

	FileInputStream fIP;
	static XSSFSheet spreadsheet;
	static Cell cell;
	// Create row object
	static XSSFRow row;

	static CustomTableModel ctm;
	static JTable t;

	Color background = new Color(54, 54, 54);
	Color text = new Color(232, 23, 93);
	Color accent = new Color(168, 167, 168);
	Color button_text = new Color(71, 71, 71);
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPartFrame frame = new SearchPartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public SearchPartFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1494, 1120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAddPart = new JLabel("Search Part");
		lblAddPart.setBounds(139, 43, 262, 64);
		lblAddPart.setFont(new Font("Corbel", Font.PLAIN, 50));
		lblAddPart.setForeground(text);

		textfield_partname = new JTextField();
		textfield_partname.setBounds(268, 137, 249, 20);
		textfield_partname.setColumns(10);

		comboBox_cat = new JComboBox();
		comboBox_cat.setBounds(129, 137, 126, 20);

		comboBox_cat.addItem("Part Name");
		comboBox_cat.addItem("Manufacturer");
		comboBox_cat.addItem("ID Number");
		comboBox_cat.addItem("Room");
		comboBox_cat.addItem("Bin");
		contentPane.setBackground(new Color(236, 240, 241));
		contentPane.setForeground(new Color(52, 152, 219));
		contentPane.setLayout(null);
		contentPane.add(lblAddPart);
		contentPane.add(textfield_partname);
		contentPane.add(comboBox_cat);
		contentPane.setBackground(background);

		btnBack = new JButton("Back");
		btnBack.setBounds(35, 43, 69, 50);
		btnBack.setBackground(accent);
		btnBack.setForeground(button_text);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);

		JLabel lblSearchBy = new JLabel("Search by: ");
		lblSearchBy.setForeground(new Color(232, 23, 93));
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchBy.setBounds(35, 137, 86, 17);
		contentPane.add(lblSearchBy);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setForeground(new Color(71, 71, 71));
		btnSearch.setBackground(new Color(168, 167, 168));
		btnSearch.setBounds(527, 137, 147, 21);
		contentPane.add(btnSearch);

		setupTable();

		t = setupTable();

		t.setPreferredScrollableViewportSize(new Dimension(300, 100));
		t.setAutoCreateRowSorter(true);
		
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(t.getModel());
		t.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		 
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		 
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		
		
		
		// The following lines set the default editor and renderer for
		// any column containing Currency objects.

		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(10, 180, 1243, 799);
		// -----------------------------------------------------
		// add a listener for mouse clicks on the table's header
		// -----------------------------------------------------

		JTableHeader th = t.getTableHeader();
		th.addMouseListener(this);

		// ------------------
		// arrange components
		// ------------------

		contentPane.add(sp);

		btnEditPart = new JButton("Edit Part");
		btnEditPart.setBounds(1337, 495, 89, 23);
		btnEditPart.addActionListener(this);
		btnEditPart.setBackground(accent);
		btnEditPart.setForeground(button_text);
		contentPane.add(btnEditPart);

	}

	// public static void refreshTable() throws IOException{

	// Object[] columnNames = { "Part Name", "Manufacturer", "ID Number",
	// "Room", "Bin", "Quantity" };

	// CustomTableModel model = (CustomTableModel) t.getModel();
	// model.refresh();
	// t.setModel(model);
	//
	// }

	public static JTable setupTable() throws IOException {
		File file = new File("database.xlsx");
		FileInputStream fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		XSSFWorkbook workbook = new XSSFWorkbook(fIP);

		if (file.isFile() && file.exists()) {
			System.out.println("openworkbook.xlsx file open successfully.");
		} else {
			System.out.println("Error to open openworkbook.xlsx file.");
		}

		// Open the existing sheet

		spreadsheet = workbook.getSheet("Employee Info");

		Object[] columnNames = { "Description", "Manufacturer", "Identification", "Room", "Bin", "Quantity" };

		ctm = new CustomTableModel(getRowData(), columnNames, true);
		// ctm.setColumnEditable(3, true);

		JTable fresh_table = new JTable(ctm);

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

		return fresh_table;

	}

	public static void refreshSpreadsheet() throws IOException {

		File file = new File("database.xlsx");
		FileInputStream fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		XSSFWorkbook workbook = new XSSFWorkbook(fIP);

		if (file.isFile() && file.exists()) {
			System.out.println("openworkbook.xlsx file open successfully.");
		} else {
			System.out.println("Error to open openworkbook.xlsx file.");
		}

		// Open the existing sheet

		spreadsheet = workbook.getSheet("Employee Info");

		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

	}

	public static Object[][] getRowData() throws IOException {

		refreshSpreadsheet();

		Object[][] rowData = new Object[spreadsheet.getLastRowNum()][6];
		int rowCounter;
		int cellCounter;

		// rowData[0][0] = {"Mr.Small"};

		Iterator<Row> rowIterator = spreadsheet.iterator();
		rowCounter = 0;
		row = (XSSFRow) rowIterator.next();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			cellCounter = 0;

			while (cellIterator.hasNext()) {

				cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					rowData[rowCounter][cellCounter] = cell.getNumericCellValue();
					// rowData.add("" + cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					rowData[rowCounter][cellCounter] = cell.getStringCellValue();
					// rowData.add(cell.getStringCellValue());
					break;
				}
				cellCounter++;
				// System.out.println(" Cell = " + cellCounter);
			}

			rowCounter++;
			// System.out.println("Row = " + rowCounter);
		}

		return rowData;
	}

	public void readExcel() throws IOException {

		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + " ");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + " ");
					break;
				}
			}
			System.out.println();
		}
		// fIP.close();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the submit button is pressed do this action

		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(btnSearch)) {
			System.out.println("Searching");
		} else if (buttonPressed.equals(btnBack)) {
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		} else if (buttonPressed.equals(btnEditPart)) {

			if(t.getSelectedRow() != -1){
				System.out.println("Edit Part");
				

				try {
					Object[][] allRows = SearchPartFrame.getRowData();

					Object selected_partName = allRows[t.getSelectedRow()][0];
					Object selected_manufacturer = allRows[t.getSelectedRow()][1];
					Object selected_idNumber = allRows[t.getSelectedRow()][2];
					Object selected_room = allRows[t.getSelectedRow()][3];
					Object selected_bin = allRows[t.getSelectedRow()][4];
					Object selected_quantity = allRows[t.getSelectedRow()][5];

					Inventory.editpartframe.textfield_partname.setText(selected_partName.toString());
					Inventory.editpartframe.comboBox_manufacturer.setSelectedItem(selected_manufacturer);
					Inventory.editpartframe.comboBox_room.setSelectedItem(selected_room);
					Inventory.editpartframe.textField_idnumber.setText(selected_idNumber.toString());
					Inventory.editpartframe.textField_quantity.setText(selected_quantity.toString());
					
					Inventory.editpartframe.textField_binroom.setText(selected_bin.toString());
					
					Inventory.editpartframe.selected_row = t.getSelectedRow()+1;
					
				
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Inventory.editpartframe.setVisible(true);
				Inventory.searchpartframe.setVisible(false);
				
				try {
					Inventory.addmanufacturerframe.readManufacturer(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {
/*
		if (SwingUtilities.isRightMouseButton(me)) {

			System.out.println("Right Click");

		} else if (SwingUtilities.isLeftMouseButton(me)) {

			// TODO Auto-generated method stub
			JTableHeader source = (JTableHeader) me.getSource();

			// get index of selected column IN THE VIEW
			// (Note: this changes if columns are moved by dragging with mouse)

			TableColumnModel tcm = source.getColumnModel();
			int tmp = tcm.getColumnIndexAtX(me.getX());
			// System.out.println("First idx = " + tmp);

			// get index of selected column IN THE MODEL

			TableColumn tc = tcm.getColumn(tmp);
			int idx = tc.getModelIndex();
			// System.out.println("Second idx= " + idx);

			// get the data model, and do the sort

			CustomTableModel ctm = (CustomTableModel) (source.getTable().getModel());
			ctm.sort(idx);
		}
*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
