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
import javax.swing.JOptionPane;
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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JSeparator;
import javax.swing.JTable;

public class SearchPartFrame extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	public static JTextField textfield_search;
	public static JComboBox comboBox_cat;
	private JButton btnBack;
	private JButton btnSearch;
	private JButton btnEditPart;

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

		textfield_search = new JTextField();
		textfield_search.setBounds(268, 137, 249, 20);
		textfield_search.setColumns(10);

		comboBox_cat = new JComboBox();
		comboBox_cat.setBounds(129, 137, 126, 20);

		comboBox_cat.addItem("Description");
		comboBox_cat.addItem("Manufacturer");
		comboBox_cat.addItem("Identification");
		comboBox_cat.addItem("Room");
		comboBox_cat.addItem("Bin");

		contentPane.setBackground(new Color(236, 240, 241));
		contentPane.setForeground(new Color(52, 152, 219));
		contentPane.setLayout(null);
		contentPane.add(lblAddPart);
		contentPane.add(textfield_search);
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

		// setupTable();

		t = Excel.setupSearchTable();

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the submit button is pressed do this action

		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(btnSearch)) {
			System.out.println("Searching");

			Integer columntosearch = comboBox_cat.getSelectedIndex();

			String searchquery = textfield_search.getText();

			if (searchquery.equals("")) {
				Excel.refreshSearchTable();
			} else
				try {
					if(Excel.getRowsMatching(columntosearch, searchquery) == null ){
						JOptionPane.showMessageDialog(this, "No results found!", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						Excel.refreshSearch(columntosearch, searchquery);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		} else if (buttonPressed.equals(btnBack)) {
			System.out.println("Back");
			Inventory.mainmenuframe.setVisible(true);
			Inventory.searchpartframe.setVisible(false);
		} else if (buttonPressed.equals(btnEditPart)) {

			if (t.getSelectedRow() != -1) {
				System.out.println("Edit Part");

				try {
					Inventory.addmanufacturerframe.readManufacturer(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Object[][] allRows = Excel.getSearchRowData();

					Object selected_partName = t.getValueAt(t.getSelectedRow(), 0);
					Object selected_manufacturer = t.getValueAt(t.getSelectedRow(), 1);
					Object selected_idNumber = t.getValueAt(t.getSelectedRow(), 2);
					Object selected_room = t.getValueAt(t.getSelectedRow(), 3);
					Object selected_bin = t.getValueAt(t.getSelectedRow(), 4);
					Object selected_quantity = t.getValueAt(t.getSelectedRow(), 5);

					String partname = selected_partName.toString();
					String manufacturer = selected_manufacturer.toString();
					String identification = selected_idNumber.toString();
					String room = selected_room.toString();
					String bin = selected_bin.toString();
					Double dquantity = (double) selected_quantity;
					Integer quantity = dquantity.intValue();

					Inventory.editpartframe.old_data = new Part(partname, manufacturer, identification, room, bin,
							quantity);

					Inventory.editpartframe.textfield_partname.setText(partname);
					Inventory.editpartframe.comboBox_manufacturer.setSelectedItem(selected_manufacturer);
					Inventory.editpartframe.comboBox_room.setSelectedItem(selected_room);
					Inventory.editpartframe.textField_idnumber.setText(identification);

					Inventory.editpartframe.textField_quantity.setText(String.valueOf(quantity));

					Inventory.editpartframe.textField_binroom.setText(selected_bin.toString());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Inventory.editpartframe.setVisible(true);
				Inventory.searchpartframe.setVisible(false);

			}

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
