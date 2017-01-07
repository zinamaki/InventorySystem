import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainMenuFrame extends JFrame implements ActionListener {

	private JButton btnSearchPart;
	private JButton btnNewButton;

	private JButton btnAddManufacturer;

	private JPanel contentPane;

	Color tron_orange = new Color(223, 116, 12);

	Color tron_yellow = new Color(255, 230, 77);

	Color tron_pane = new Color(230, 255, 255);

	Color tron_blue = new Color(111, 195, 223);

	Color tron_black = new Color(12, 20, 31);
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
					MainMenuFrame frame = new MainMenuFrame();
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
	public MainMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 886);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnNewButton = new JButton("Add Part");
		btnNewButton.setFont(new Font("Corbert", Font.BOLD, 20));
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(accent);
		btnNewButton.setForeground(button_text);

		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Corbert", Font.PLAIN, 80));
		lblInventory.setForeground(text);

		btnSearchPart = new JButton("Search Part");
		btnSearchPart.setFont(new Font("Corbert", Font.BOLD, 20));
		btnSearchPart.addActionListener(this);
		btnSearchPart.setBackground(accent);
		btnSearchPart.setForeground(button_text);

		contentPane.setBackground(background);

		btnAddManufacturer = new JButton("Add Manufacturer");
		btnAddManufacturer.setForeground(new Color(71, 71, 71));
		btnAddManufacturer.setFont(new Font("Corbert", Font.BOLD, 20));
		btnAddManufacturer.setBackground(new Color(168, 167, 168));
		btnAddManufacturer.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(84)
						.addComponent(lblInventory, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(433)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnAddManufacturer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSearchPart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 329,
										Short.MAX_VALUE))
						.addGap(277)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(338).addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSearchPart, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAddManufacturer, GroupLayout.PREFERRED_SIZE, 59,
										GroupLayout.PREFERRED_SIZE)
								.addGap(317))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(21)
								.addComponent(lblInventory, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE).addGap(384)))
						.addGap(0)));
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the add part button is pressed do this action

		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(btnSearchPart)) {
			System.out.println("Search for a Part");

			Excel.refreshSearchTable();

			Inventory.mainmenuframe.setVisible(false);
			Inventory.searchpartframe.setVisible(true);
		} else if (buttonPressed.equals(btnNewButton)) {
			System.out.println("Add a Part");
			try {
				Inventory.addmanufacturerframe.readManufacturer(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Inventory.mainmenuframe.setVisible(false);
			Inventory.addpartframe.setVisible(true);
		} else if (buttonPressed.equals(btnAddManufacturer)) {
			System.out.println("Add a Manufacturer");
			Inventory.mainmenuframe.setVisible(false);
			Inventory.addmanufacturerframe.setVisible(true);
		}

	}
}
