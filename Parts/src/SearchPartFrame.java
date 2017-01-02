import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class SearchPartFrame extends JFrame implements ActionListener {

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
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSearch.setForeground(new Color(71, 71, 71));
		btnSearch.setBackground(new Color(168, 167, 168));
		btnSearch.setBounds(527, 137, 147, 21);
		contentPane.add(btnSearch);
		
		table = new JTable();
		table.setBounds(43, 191, 780, 528);
	
		contentPane.add(table);

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
			
		}else if(buttonPressed.equals(btnBack)){
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		}
		

	}
}
