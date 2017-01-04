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

public class AddPartFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static JTextField textfield_partname;
	public static JTextField textField_idnumber;
	public static JTextField textField_quantity;
	public static JTextField textField_binroom;
	public static JTextField textField_binid;
	public static JComboBox comboBox_room;
	public static JComboBox comboBox_manufacturer;

	private JButton btnSubmit;
	private JButton btnBack;

	FileInputStream fIP;
	XSSFSheet spreadsheet;
	Cell cell;
	// Create row object
	XSSFRow row;

	Color background = new Color(54, 54, 54);
	Color text = new Color(232, 23, 93);
	Color accent = new Color(168, 167, 168);
	Color button_text = new Color(71, 71, 71);

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
		setBounds(100, 100, 888, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAddPart = new JLabel("Add Part");
		lblAddPart.setBounds(139, 43, 377, 102);
		lblAddPart.setFont(new Font("Corbel", Font.PLAIN, 80));
		lblAddPart.setForeground(text);

		JLabel lblPartName = new JLabel("Part Name:");
		lblPartName.setBounds(77, 201, 200, 36);
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPartName.setForeground(text);

		JLabel lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setBounds(77, 260, 200, 36);
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblManufacturer.setForeground(text);

		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setBounds(77, 320, 200, 42);
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblIdNumber.setForeground(text);

		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(77, 373, 200, 38);
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRoom.setForeground(text);

		JLabel lblBin = new JLabel("Bin:");
		lblBin.setBounds(77, 422, 200, 40);
		lblBin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBin.setForeground(text);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(77, 473, 200, 36);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblQuantity.setForeground(text);

		textfield_partname = new JTextField();
		textfield_partname.setBounds(352, 201, 249, 36);
		textfield_partname.setColumns(10);

		textField_idnumber = new JTextField();
		textField_idnumber.setBounds(352, 320, 249, 42);
		textField_idnumber.setColumns(10);

		comboBox_room = new JComboBox();
		comboBox_room.setBounds(352, 373, 249, 38);

		comboBox_room.addItem("Mezzanine");
		comboBox_room.addItem("Electrical Room");
		comboBox_room.addItem("PLC Room");
		comboBox_room.addItem("Mezzanine Room");
		comboBox_room.addItem("Plant");

		textField_quantity = new JTextField();
		textField_quantity.setBounds(352, 479, 249, 30);
		textField_quantity.setColumns(10);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(139, 546, 200, 36);
		btnSubmit.setBackground(accent);
		btnSubmit.setForeground(button_text);
		btnSubmit.addActionListener(this);

		textField_binroom = new JTextField();
		textField_binroom.setBounds(352, 422, 86, 40);
		textField_binroom.setColumns(10);
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
		contentPane.add(textField_binroom);
		contentPane.add(textField_quantity);
		contentPane.add(textField_idnumber);
		contentPane.add(textfield_partname);
		contentPane.add(comboBox_room);

		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setBounds(465, 428, 34, 14);
		contentPane.add(label);
		label.setForeground(text);
		textField_binid = new JTextField();
		textField_binid.setBounds(515, 422, 86, 40);
		contentPane.add(textField_binid);
		textField_binid.setColumns(10);
		contentPane.setBackground(background);

		btnBack = new JButton("Back");
		btnBack.setBounds(35, 43, 69, 50);
		btnBack.setBackground(accent);
		btnBack.setForeground(button_text);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);

		comboBox_manufacturer = new JComboBox();
		comboBox_manufacturer.setBounds(352, 260, 249, 36);
		contentPane.add(comboBox_manufacturer);

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

	public void setupExcel() {

		// This data needs to be written (Object[])

		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();

		empinfo.put("1", new Object[] { "PART NAME", "MANUFACTURER", "ID NUMBER", "ROOM", "BIN", "Quantity" });
		empinfo.put("2", new Object[] { "tp01", "Gopal", "Technical Manager" });
		empinfo.put("3", new Object[] { "tp02", "", "Proof Reader" });
		empinfo.put("4", new Object[] { "tp03", "Masthan", "Technical Writer" });
		empinfo.put("5", new Object[] { "tp04", "sdfggfgfgf", "Technical Writer" });
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

		spreadsheet = workbook.getSheet("Employee Info");

		row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);

		// Get part data from textfields

		ArrayList<String> row_data = new ArrayList<String>();

		row_data.add(AddPartFrame.textfield_partname.getText());
		row_data.add((String) AddPartFrame.comboBox_manufacturer.getSelectedItem());
		row_data.add(AddPartFrame.textField_idnumber.getText());
		row_data.add((String) AddPartFrame.comboBox_room.getSelectedItem());
		row_data.add(AddPartFrame.textField_binroom.getText() + "-" + this.textField_binid.getText());
		row_data.add(this.textField_quantity.getText());

		for (int i = 0; i < 6; i++) {
			cell = row.createCell(i);
			cell.setCellValue(row_data.get(i));
		}

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

	}

	public void resetPage() {
		textfield_partname.setText("");
		comboBox_manufacturer.setSelectedIndex(0);
		textField_idnumber.setText("");
		comboBox_room.setSelectedItem("Mezzanine");
		textField_binroom.setText("");
		textField_binid.setText("");
		textField_quantity.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the submit button is pressed do this action

		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(btnSubmit)) {
			try {
				writeExcel();
				readExcel();
				resetPage();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (buttonPressed.equals(btnBack)) {
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.addpartframe.setVisible(false);
		}

	}
}
