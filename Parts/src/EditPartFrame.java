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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EditPartFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static JTextField textfield_partname;
	public static JTextField textField_idnumber;
	public static JTextField textField_quantity;
	public static JTextField textField_binroom;
	public static JComboBox comboBox_room;
	public static JComboBox comboBox_manufacturer;

	private JButton btnSubmit;
	private JButton btnBack;

	public static Part old_data;

	Color background = new Color(54, 54, 54);
	Color text = new Color(232, 23, 93);
	Color accent = new Color(168, 167, 168);
	Color button_text = new Color(71, 71, 71);
	private JButton btnDeletePart;
	private JLabel lblEquipment;
	private JTextField textField_equipment;

	/**
	 * Create the frame.
	 */
	public EditPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 788);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAddPart = new JLabel("Edit Part");
		lblAddPart.setBounds(139, 43, 377, 102);
		lblAddPart.setFont(new Font("Corbel", Font.PLAIN, 80));
		lblAddPart.setForeground(text);

		JLabel lblPartName = new JLabel("Description:");
		lblPartName.setBounds(77, 201, 200, 36);
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPartName.setForeground(text);

		JLabel lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setBounds(77, 260, 200, 36);
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblManufacturer.setForeground(text);

		JLabel lblIdNumber = new JLabel("Identification:");
		lblIdNumber.setBounds(77, 320, 200, 42);
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblIdNumber.setForeground(text);

		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(77, 373, 200, 38);
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRoom.setForeground(text);

		JLabel lblBin = new JLabel("Bin:");
		lblBin.setBounds(77, 472, 200, 40);
		lblBin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBin.setForeground(text);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(77, 523, 200, 36);
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
		textField_quantity.setBounds(352, 523, 249, 42);
		textField_quantity.setColumns(10);

		textField_quantity.setDocument(new IntegerDocument());

		btnSubmit = new JButton("Save Changes");
		btnSubmit.setBounds(136, 589, 200, 36);
		btnSubmit.setBackground(accent);
		btnSubmit.setForeground(button_text);
		btnSubmit.addActionListener(this);

		textField_binroom = new JTextField();
		textField_binroom.setBounds(352, 472, 249, 40);
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

		btnDeletePart = new JButton("Delete Part");
		btnDeletePart.setForeground(new Color(71, 71, 71));
		btnDeletePart.setBackground(new Color(168, 167, 168));
		btnDeletePart.setBounds(362, 589, 200, 36);
		btnDeletePart.addActionListener(this);
		contentPane.add(btnDeletePart);

		lblEquipment = new JLabel("Equipment:");
		lblEquipment.setForeground(new Color(232, 23, 93));
		lblEquipment.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEquipment.setBounds(77, 421, 200, 40);
		contentPane.add(lblEquipment);

		textField_equipment = new JTextField();
		textField_equipment.setColumns(10);
		textField_equipment.setBounds(352, 422, 249, 40);
		contentPane.add(textField_equipment);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the submit button is pressed do this action

		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(btnSubmit)) {
			try {
				String partname = textfield_partname.getText();
				String manufacturer = (String) comboBox_manufacturer.getSelectedItem();
				String identification = textField_idnumber.getText();
				String room = (String) comboBox_room.getSelectedItem();
				String bin = textField_binroom.getText();
				Integer quantity = Integer.parseInt(textField_quantity.getText());

				Part new_data = new Part(partname, manufacturer, identification, room, bin, quantity);

				Excel.editExcel(old_data, new_data);
				Excel.readExcel();

				comboBox_manufacturer.removeAllItems();

				Excel.refreshSearchTable();

				Inventory.searchpartframe.setVisible(true);
				Inventory.editpartframe.setVisible(false);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (buttonPressed.equals(btnBack)) {

			System.out.println("Back");

			comboBox_manufacturer.removeAllItems();

			Inventory.searchpartframe.setVisible(true);
			Inventory.editpartframe.setVisible(false);

		} else if (buttonPressed.equals(btnDeletePart)) {
			try {

				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this part?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					System.out.println("No button clicked");
				} else if (response == JOptionPane.YES_OPTION) {
					System.out.println("Yes button clicked");
					Excel.deleteExcel(old_data);

					Excel.readExcel();

					comboBox_manufacturer.removeAllItems();
					Excel.refreshSearchTable();

					Inventory.searchpartframe.setVisible(true);
					Inventory.editpartframe.setVisible(false);
				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
