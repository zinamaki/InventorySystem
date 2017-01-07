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

	static JTable t;

	Color background = new Color(54, 54, 54);
	Color text = new Color(232, 23, 93);
	Color accent = new Color(168, 167, 168);
	Color button_text = new Color(71, 71, 71);


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

		t = Excel.setupManufacturerTable();

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



	public void readManufacturer(boolean isAddPartFrame) throws IOException {

		Excel.refreshSpreadsheet();

		Object[][] rowData = Excel.getManufacturerRowData();

		for (int i = 0; i < rowData.length; i++) {
			if (isAddPartFrame) {
				
				Inventory.addpartframe.comboBox_manufacturer.addItem(rowData[i][0]);
			} else {
			
				Inventory.editpartframe.comboBox_manufacturer.addItem(rowData[i][0]);
			}

		}

		Excel.readExcel();

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the submit button is pressed do this action

		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(btnSearch)) {
			System.out.println("Adding Manufacturer");

			try {
				Excel.writeManufacturer(textfield_manufacturer.getText());
				Excel.readExcel();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			Excel.refreshManufacturerTable();
		} else if (buttonPressed.equals(btnBack)) {
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		} 

	}

	@Override
	public void mouseClicked(MouseEvent me) {
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
