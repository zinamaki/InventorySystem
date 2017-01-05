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
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JSeparator;
import javax.swing.JTable;

public class AddManufacturerFrame extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	public static JTextField textfield_manufacturer;
	private JButton btnBack;
	private JButton btnSearch;

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
	public AddManufacturerFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAddPart = new JLabel("Add Manufacturer");
		lblAddPart.setBounds(139, 43, 406, 64);
		lblAddPart.setFont(new Font("Corbel", Font.PLAIN, 50));
		lblAddPart.setForeground(text);

		textfield_manufacturer = new JTextField();
		textfield_manufacturer.setBounds(139, 137, 249, 20);
		textfield_manufacturer.setColumns(10);
		contentPane.setBackground(new Color(236, 240, 241));
		contentPane.setForeground(new Color(52, 152, 219));
		contentPane.setLayout(null);
		contentPane.add(lblAddPart);
		contentPane.add(textfield_manufacturer);
		contentPane.setBackground(background);

		btnBack = new JButton("Back");
		btnBack.setBounds(35, 43, 69, 50);
		btnBack.setBackground(accent);
		btnBack.setForeground(button_text);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);

		JLabel lblSearchBy = new JLabel("Manufacturer:");
		lblSearchBy.setForeground(new Color(232, 23, 93));
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchBy.setBounds(35, 137, 86, 17);
		contentPane.add(lblSearchBy);

		btnSearch = new JButton("Add Manufacturer");
		btnSearch.addActionListener(this);
		btnSearch.setForeground(new Color(71, 71, 71));
		btnSearch.setBackground(new Color(168, 167, 168));
		btnSearch.setBounds(410, 137, 147, 21);
		contentPane.add(btnSearch);

		setupTable();

		t = setupTable();

		t.setPreferredScrollableViewportSize(new Dimension(300, 100));

		t.setAutoCreateRowSorter(true);
		
		// The following lines set the default editor and renderer for
		// any column containing Currency objects.

		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(10, 180, 800, 558);
		// -----------------------------------------------------
		// add a listener for mouse clicks on the table's header
		// -----------------------------------------------------

		JTableHeader th = t.getTableHeader();
		th.addMouseListener(this);

		// ------------------
		// arrange components
		// ------------------

		contentPane.add(sp);

	}

	public static void refreshTable() throws IOException {

		// Object[] columnNames = { "Part Name", "Manufacturer", "ID Number",
		// "Room", "Bin", "Quantity" };

		CustomTableModel model = (CustomTableModel) t.getModel();
		model.refresh();
		t.setModel(model);

	}

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

		spreadsheet = workbook.getSheet("Manufacturer");

		Object[] columnNames = { "Manufacturer" };

		ctm = new CustomTableModel(getRowData(), columnNames, false);
		// ctm.setColumnEditable(3, true);

		JTable fresh_table = new JTable(ctm);

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

		return fresh_table;

	}

	public void writeExcel() throws Exception {

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

		spreadsheet = workbook.getSheet("Manufacturer");

		row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);

		// Get part data from textfields

		// ArrayList<String> row_data = new ArrayList<String>();

		// row_data.add(AddManufacturerFrame.textfield_manufacturer.getText());

		// for (int i = 0; i < 6; i++) {
		// cell = row.createCell(i);
		// cell.setCellValue(row_data.get(i));
		// }

		cell = row.createCell(0);
		cell.setCellValue(AddManufacturerFrame.textfield_manufacturer.getText());

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

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

		spreadsheet = workbook.getSheet("Manufacturer");

		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

	}

	public void readManufacturer(boolean isAddPartFrame) throws IOException {

		refreshSpreadsheet();

		Object[][] rowData = AddManufacturerFrame.getRowData();

		for (int i = 0; i < rowData.length; i++) {
			if (isAddPartFrame) {
				
				Inventory.addpartframe.comboBox_manufacturer.addItem(rowData[i][0]);
			} else {
			
				Inventory.editpartframe.comboBox_manufacturer.addItem(rowData[i][0]);
			}

		}

		readExcel();

	}

	public static Object[][] getRowData() throws IOException {

		refreshSpreadsheet();

		Object[][] rowData = new Object[spreadsheet.getLastRowNum()][1];
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
			System.out.println("Adding Manufacturer");

			try {
				writeExcel();
				readExcel();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			ctm.refresh();
		} else if (buttonPressed.equals(btnBack)) {
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		} else if (buttonPressed.equals(btnBack)) {
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {
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
