import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Inventory
{
	public static void main(String[] args)
	{
		// use look and feel for my system (Win32)
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
		}

		DemoButtonModelFrame frame = new DemoButtonModelFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Add Part");
		frame.pack();

		// put the frame in the middle of the display
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setSize(300,250);
		frame.setVisible(true);
	}
}

// -----------------------------
// define the application window
// -----------------------------

class DemoButtonModelFrame extends JFrame implements ActionListener
{
	// the following avoids a "warning" with Java 1.5.0 complier (?)
	static final long serialVersionUID = 42L;

	private JLabel title;
	
	private JButton submit;
	private JLabel partName;
	private JLabel manufacturer;
	private JLabel quantity;
	
	private JTextField t_partName;
	private JTextField t_manufacturer;
	private JTextField t_quantity;
	
	public static Font titleFont = new Font("Myriad Pro", Font.PLAIN, 48);

	public DemoButtonModelFrame()
	{
		// -------------------------------
		// create and configure components
		// -------------------------------

		title = new JLabel("Add Part");
		title.setFont(titleFont);
		
		submit = new JButton("Submit");
		
		manufacturer = new JLabel("Manufacturer:");
		partName = new JLabel("Part Name:");
		quantity = new JLabel("Quantity:");

		t_partName = new JTextField(15);
		t_manufacturer = new JTextField(15);
		t_quantity = new JTextField(15);
		// -------------
		// add listeners
		// -------------

		submit.addActionListener(this);

		// ------------------
		// arrange components
		// ------------------

		// put components in a panel

		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		p.add(title);
		p.add(partName);
		p.add(t_partName);
		p.add(manufacturer);
		p.add(t_manufacturer);
		p.add(quantity);
		p.add(t_quantity);
		p.add(submit);


		// make the panel this extended JFrame's content pane

		this.setContentPane(p);
	}

	// -------------------------------
	// implement ActionListener method
	// -------------------------------

	public void actionPerformed(ActionEvent ae)
	{
		System.out.println("BUTTON CLICKED!");
	}

}
