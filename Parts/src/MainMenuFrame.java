import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenuFrame extends JFrame implements ActionListener {

	private JButton btnSearchPart;
	private JButton btnNewButton;
	
	private JPanel contentPane;
	
	Color tron_orange = new Color(223,116,12);

	Color tron_yellow = new Color(255,230,77);

	Color tron_pane = new Color(230,255,255);

	Color tron_blue = new Color(111,195,223);

	Color tron_black = new Color(12,20,31);
	Color background = new Color(54,54,54);
	Color text = new Color(232,23,93);
	Color accent = new Color(168,167,168);
	Color button_text = new Color(71,71,71);
	
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
		setBounds(100, 100, 873, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("Add Part");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(524, 267, 233, 38);
		btnNewButton.setBackground(accent);
		btnNewButton.setForeground(button_text);
		contentPane.add(btnNewButton);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblInventory.setBounds(84, 255, 264, 121);
		lblInventory.setForeground(text);
		contentPane.add(lblInventory);
		
		btnSearchPart = new JButton("Search Part");
		btnSearchPart.setBounds(524, 316, 233, 38);
		btnSearchPart.addActionListener(this);
		btnSearchPart.setBackground(accent);
		btnSearchPart.setForeground(button_text);
		contentPane.add(btnSearchPart);
		
		contentPane.setBackground(background);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// when the add part button is pressed do this action
		
		JButton buttonPressed = (JButton) e.getSource();
		
		if (buttonPressed.equals(btnSearchPart)){
			System.out.println("Search for a Part");
			Inventory.mainmenuframe.setVisible(false);
			Inventory.searchpartframe.setVisible(true);
		}else if(buttonPressed.equals(btnNewButton)){
			System.out.println("Add a Part");
			Inventory.mainmenuframe.setVisible(false);
			Inventory.addpartframe.setVisible(true);
		}
		
		
	}
}
