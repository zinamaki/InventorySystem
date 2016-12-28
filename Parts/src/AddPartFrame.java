import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class AddPartFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPartFrame frame = new EditPartFrame();
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(214, 373, 105, 20);
		
		comboBox.addItem("Mezzanine");
		comboBox.addItem("Electrical Room");
		comboBox.addItem("PLC Room");
		comboBox.addItem("Mezzanine Room");
		comboBox.addItem("Plant");
		
		textField_3 = new JTextField();
		textField_3.setBounds(214, 473, 86, 20);
		textField_3.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(132, 558, 65, 23);
		
		textField_4 = new JTextField();
		textField_4.setBounds(214, 422, 86, 20);
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
	}
}
