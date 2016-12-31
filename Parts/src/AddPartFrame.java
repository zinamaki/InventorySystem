import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class AddPartFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static  JTextField textField;
	public static  JTextField textField_1;
	public static  JTextField textField_2;
	public static  JTextField textField_3;
	public static  JTextField textField_4;
	public static  JTextField textField_5;
	public static JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPartFrame frame = new AddPartFrame();
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
	public AddPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAddPart = new JLabel("Add Part");
		lblAddPart.setBounds(139, 43, 180, 64);
		lblAddPart.setFont(new Font("Corbel", Font.PLAIN, 40));

		JLabel lblPartName = new JLabel("Part Name:");
		lblPartName.setBounds(77, 201, 69, 17);
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPartName.setForeground(new Color(52, 152, 219));

		JLabel lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setBounds(77, 260, 86, 17);
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(77, 320, 72, 17);
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(77, 373, 42, 17);
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblBin = new JLabel("Bin:");
		lblBin.setBounds(77, 422, 23, 17);
		lblBin.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(77, 473, 58, 17);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setBounds(214, 201, 105, 20);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(214, 260, 105, 20);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(214, 320, 105, 20);
		textField_2.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(214, 373, 105, 20);

		comboBox.addItem("Mezzanine");
		comboBox.addItem("Electrical Room");
		comboBox.addItem("PLC Room");
		comboBox.addItem("Mezzanine Room");
		comboBox.addItem("Plant");

		textField_3 = new JTextField();
		textField_3.setBounds(214, 473, 105, 20);
		textField_3.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(132, 558, 65, 23);

		btnSubmit.addActionListener(this);

		textField_4 = new JTextField();
		textField_4.setBounds(214, 422, 42, 20);
		textField_4.setColumns(10);
		contentPane.setBackground(new Color(236, 240, 241));
		contentPane.setForeground(new Color(52, 152, 219));
		contentPane.setLayout(null);
		contentPane.add(btnSubmit);
		contentPane.add(lblAddPart);
		contentPane.add(lblPartName);
		contentPane.add(lblManufacturer);
		contentPane.add(lblIdNumber);
		contentPane.add(lblRoom);
		contentPane.add(lblBin);
		contentPane.add(lblQuantity);
		contentPane.add(textField_4);
		contentPane.add(textField_3);
		contentPane.add(textField_2);
		contentPane.add(textField_1);
		contentPane.add(textField);
		contentPane.add(comboBox);

		JLabel label = new JLabel("-");
		label.setBounds(266, 425, 14, 14);
		contentPane.add(label);

		textField_5 = new JTextField();
		textField_5.setBounds(276, 422, 42, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
	}

	public void writeExcel() throws Exception {

		// //Create Blank workbook
		// XSSFWorkbook workbook = new XSSFWorkbook();
		// //Create file system using specific name
		// FileOutputStream out = new FileOutputStream(new
		// File("database.xlsx"));
		// //write operation workbook using file out object
		// workbook.write(out);
		// out.close();
		// System.out.println("createworkbook.xlsx written successfully");

		File file = new File("database.xlsx");
		FileInputStream fIP = new FileInputStream("database.xlsx");
		// Get the workbook instance for XLSX file
		XSSFWorkbook workbook = new XSSFWorkbook(fIP);
		if (file.isFile() && file.exists()) {
			System.out.println("openworkbook.xlsx file open successfully.");
		} else {
			System.out.println("Error to open openworkbook.xlsx file.");
		}

		// Create row object
				XSSFRow row;
		
		// Create a blank sheet
		//XSSFSheet spreadsheet = workbook.createSheet("Employee Info");
		XSSFSheet spreadsheet = workbook.getSheet("Employee Info");
		
		Iterator < Row > rowIterator = spreadsheet.iterator();
	      while (rowIterator.hasNext()) 
	      {
	         row = (XSSFRow) rowIterator.next();
	         Iterator < Cell > cellIterator = row.cellIterator();
	         while ( cellIterator.hasNext()) 
	         {
	            Cell cell = cellIterator.next();
	            switch (cell.getCellType()) 
	            {
	               case Cell.CELL_TYPE_NUMERIC:
	               System.out.print( 
	               cell.getNumericCellValue() + " \t\t " );
	               break;
	               case Cell.CELL_TYPE_STRING:
	               System.out.print(
	               cell.getStringCellValue() + " \t\t " );
	               break;
	            }
	         }
	         System.out.println();
	      }
	      fIP.close();
		
		
		
		// This data needs to be written (Object[])
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "PART NAME", "MANUFACTURER", "ID NUMBER", "ROOM","BIN", "Quantity" });
		empinfo.put("2", new Object[] { "tp01", "Gopal", "Technical Manager" });
		empinfo.put("3", new Object[] { "tp02", "", "Proof Reader" });
		empinfo.put("4", new Object[] { "tp03", "Masthan", "Technical Writer" });
		empinfo.put("5", new Object[] { "tp04", "sdfgsdgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgf", "Technical Writer" });
		empinfo.put("6", new Object[] { "tp05", "Krishna", "Technical Writer" });
		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("database.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("Writesheet.xlsx written successfully");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// when the submit button is pressed do this action
		System.out.println("part added");
		try {
			writeExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
