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
	
	FileInputStream fIP;
	XSSFSheet spreadsheet;
	Cell cell;
	// Create row object
	XSSFRow row;

	Color background = new Color(54,54,54);
	Color text = new Color(232,23,93);
	Color accent = new Color(168,167,168);
	Color button_text = new Color(71,71,71);
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
	 */
	public SearchPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 788);
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
		
		Object[] columnNames = { "Part Name", "Manufacturer", "ID Number", "Room", "Bin", "Quantity" };

		Object[][] rowData = { { "Mr. Small", new Integer(95), new Boolean(false), new Boolean(true), 1.75, 1.75, new Boolean(false), 1.75 },
				{ "Kispy Kunch", new Integer(450), new Boolean(false), new Boolean(false), 1.75, 2.25 },
				{ "Kitch Katch", new Integer(400), new Boolean(false), new Boolean(true), 1.75,  2.75 },
				{ "Wunderbloat", new Integer(1300), new Boolean(false), new Boolean(false), 1.75,  2.65 },
				{ "Saramilk", new Integer(295), new Boolean(false), new Boolean(true), 1.75,  1.25 },
				{ "Big Swede", new Integer(300), new Boolean(false), new Boolean(false), 1.75,  3.10 },
				{ "Oh Hank", new Integer(450), new Boolean(false), new Boolean(false), 1.75,  4.25 },
				{ "Eat-Less", new Integer(333), new Boolean(false), new Boolean(false), 1.75,  2.50 },
				{ "Chewbacca", new Integer(456), new Boolean(false), new Boolean(false), 1.75,  1.85 },
				{ "Arrow", new Integer(375), new Boolean(false), new Boolean(false), 1.75,  1.75 },
				{ "Red Rocket", new Integer(300), new Boolean(false), new Boolean(true), 1.75,  2.3456 },
				 { "Mr. Small", new Integer(95), new Boolean(false), new Boolean(false), 1.75, 1.75 },
					{ "Kispy Kunch", new Integer(450), new Boolean(false), new Boolean(false), 1.75, 2.25 },
					{ "Kitch Katch", new Integer(400), new Boolean(false), new Boolean(true), 1.75,  2.75 },
					{ "Wunderbloat", new Integer(1300), new Boolean(false), new Boolean(false), 1.75,  2.65 },
					{ "Saramilk", new Integer(295), new Boolean(false), new Boolean(true), 1.75,  1.25 },
					{ "Big Swede", new Integer(300), new Boolean(false), new Boolean(false), 1.75,  3.10 },
					{ "Oh Hank", new Integer(450), new Boolean(false), new Boolean(false), 1.75,  4.25 },
					{ "Eat-Less", new Integer(333), new Boolean(false), new Boolean(false), 1.75,  2.50 },
					{ "Chewbacca", new Integer(456), new Boolean(false), new Boolean(false), 1.75,  1.85 },
					{ "Arrow", new Integer(375), new Boolean(false), new Boolean(false), 1.75,  1.75 },
					{ "Red Rocket", new Integer(300), new Boolean(false), new Boolean(true), 1.75,  2.3456 } ,
					 { "Mr. Small", new Integer(95), new Boolean(false), new Boolean(false), 1.75, 1.75 },
						{ "Kispy Kunch", new Integer(450), new Boolean(false), new Boolean(false), 1.75, 2.25 },
						{ "Kitch Katch", new Integer(400), new Boolean(false), new Boolean(true), 1.75,  2.75 },
						{ "Wunderbloat", new Integer(1300), new Boolean(false), new Boolean(false), 1.75,  2.65 },
						{ "Saramilk", new Integer(295), new Boolean(false), new Boolean(true), 1.75,  1.25 },
						{ "Big Swede", new Integer(300), new Boolean(false), new Boolean(false), 1.75,  3.10 },
						{ "Oh Hank", new Integer(450), new Boolean(false), new Boolean(false), 1.75,  4.25 },
						{ "Eat-Less", new Integer(333), new Boolean(false), new Boolean(false), 1.75,  2.50 },
						{ "Chewbacca", new Integer(456), new Boolean(false), new Boolean(false), 1.75,  1.85 },
						{ "Arrow", new Integer(375), new Boolean(false), new Boolean(false), 1.75,  1.75 },
						{ "Red Rocket", new Integer(300), new Boolean(false), new Boolean(true), 1.75,  2.3456 } ,
						 { "Mr. Small", new Integer(95), new Boolean(false), new Boolean(false), 1.75, 1.75 },
							{ "Kispy Kunch", new Integer(450), new Boolean(false), new Boolean(false), 1.75, 2.25 },
							{ "Kitch Katch", new Integer(400), new Boolean(false), new Boolean(true), 1.75,  2.75 },
							{ "Wunderbloat", new Integer(1300), new Boolean(false), new Boolean(false), 1.75,  2.65 },
							{ "Saramilk", new Integer(295), new Boolean(false), new Boolean(true), 1.75,  1.25 },
							{ "Big Swede", new Integer(300), new Boolean(false), new Boolean(false), 1.75,  3.10 },
							{ "Oh Hank", new Integer(450), new Boolean(false), new Boolean(false), 1.75,  4.25 },
							{ "Eat-Less", new Integer(333), new Boolean(false), new Boolean(false), 1.75,  2.50 },
							{ "Chewbacca", new Integer(456), new Boolean(false), new Boolean(false), 1.75,  1.85 },
							{ "Arrow", new Integer(375), new Boolean(false), new Boolean(false), 1.75,  1.75 },
							{ "Red Rocket", new Integer(300), new Boolean(false), new Boolean(true), 1.75,  2.3456 } 
							, { "Mr. Small", new Integer(95), new Boolean(false), new Boolean(false), 1.75, 1.75 },
							{ "Kispy Kunch", new Integer(450), new Boolean(false), new Boolean(false), 1.75, 2.25 },
							{ "Kitch Katch", new Integer(400), new Boolean(false), new Boolean(true), 1.75,  2.75 },
							{ "Wunderbloat", new Integer(1300), new Boolean(false), new Boolean(false), 1.75,  2.65 },
							{ "Saramilk", new Integer(295), new Boolean(false), new Boolean(true), 1.75,  1.25 },
							{ "Big Swede", new Integer(300), new Boolean(false), new Boolean(false), 1.75,  3.10 },
							{ "Oh Hank", new Integer(450), new Boolean(false), new Boolean(false), 1.75,  4.25 },
							{ "Eat-Less", new Integer(333), new Boolean(false), new Boolean(false), 1.75,  2.50 },
							{ "Chewbacca", new Integer(456), new Boolean(false), new Boolean(false), 1.75,  1.85 },
							{ "Arrow", new Integer(375), new Boolean(false), new Boolean(false), 1.75,  1.75 },
							{ "Red Rocket", new Integer(300), new Boolean(false), new Boolean(true), 1.75,  2.3456 } 
							, { "Mr. Small", new Integer(95), new Boolean(false), new Boolean(false), 1.75, 1.75 },
							{ "Kispy Kunch", new Integer(450), new Boolean(false), new Boolean(false), 1.75, 2.25 },
							{ "Kitch Katch", new Integer(400), new Boolean(false), new Boolean(true), 1.75,  2.75 },
							{ "Wunderbloat", new Integer(1300), new Boolean(false), new Boolean(false), 1.75,  2.65 },
							{ "Saramilk", new Integer(295), new Boolean(false), new Boolean(true), 1.75,  1.25 },
							{ "Big Swede", new Integer(300), new Boolean(false), new Boolean(false), 1.75,  3.10 },
							{ "Oh Hank", new Integer(450), new Boolean(false), new Boolean(false), 1.75,  4.25 },
							{ "Eat-Less", new Integer(333), new Boolean(false), new Boolean(false), 1.75,  2.50 },
							{ "Chewbacca", new Integer(456), new Boolean(false), new Boolean(false), 1.75,  1.85 },
							{ "Arrow", new Integer(375), new Boolean(false), new Boolean(false), 1.75,  1.75 },
							{ "Red Rocket", new Integer(300), new Boolean(false), new Boolean(true), 1.75,  2.3456 } };

		CustomTableModel ctm = new CustomTableModel(rowData, columnNames);
		ctm.setColumnEditable(3, true);

		JTable t = new JTable(ctm);
		t.setPreferredScrollableViewportSize(new Dimension(300, 100));

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

	public void readExcel() throws IOException {

		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + " \t\t ");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + " \t\t ");
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

		if (buttonPressed.equals(btnSearch)){
			System.out.println("Searching");
		}else if(buttonPressed.equals(btnBack)){
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		}
		

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
class CustomTableModel extends AbstractTableModel
{
	// the following avoids a "warning" with Java 1.5.0 complier (?)
	static final long serialVersionUID = 42L;

	private Object[][] rowData; // the table's data
	private Object[] columnNames; // column header names

	private boolean editable[]; // true for any column that is editable
	private int sortedColumn; // column on which table is currently sorted
	private boolean sortOrder; // true = ascending, false = descending

	public CustomTableModel(Object[][] rowData, Object[] columnNames)
	{
		this.rowData = rowData;
		this.columnNames = columnNames;
		editable = new boolean[rowData.length];

		sortedColumn = -1; // means table not sorted initially
	}

	public void sort(int idx)
	{
		if (sortedColumn == idx)
			sortOrder = !sortOrder; // reverse the sort (clicked again)
		else
			sortOrder = true; // ascending sort (first click)

		sortedColumn = idx;

		//Arrays.sort(rowData, new Comparator(idx, sortOrder));
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return rowData.length;
	}

	@Override
	public String getColumnName(int idx)
	{
		return (String) columnNames[idx];
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		return rowData[row][col];
	}

	@Override
	public Class<? extends Object> getColumnClass(int col)
	{
		return getValueAt(0, col).getClass();
	}

	@Override
	public void setValueAt(Object value, int row, int col)
	{
		rowData[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return editable[col];
	}

	public void setColumnEditable(int colArg, boolean editableArg)
	{
		editable[colArg] = editableArg;
	}
}