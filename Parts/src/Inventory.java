import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;



public class Inventory
{
	public static AddPartFrame addpartframe;
	public static EditPartFrame editpartframe;
	public static MainMenuFrame mainmenuframe;
	
	public static void main(String[] args)
	{
		// use look and feel for my system (Win32)
		try
		{
	//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
		}

		// put the frame in the middle of the display
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		mainmenuframe = new MainMenuFrame();
		mainmenuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainmenuframe.setTitle("Inventory System");
		mainmenuframe.pack();
		mainmenuframe.setSize(800,800);
		mainmenuframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2, dim.height / 2 - mainmenuframe.getSize().height / 2);
		
		mainmenuframe.setVisible(true);
		
		addpartframe = new AddPartFrame();
		addpartframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addpartframe.setTitle("Add Part");
		addpartframe.pack();
		addpartframe.setSize(800,800);
		addpartframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2, dim.height / 2 - mainmenuframe.getSize().height / 2);
		
		addpartframe.setVisible(false);

		editpartframe = new EditPartFrame();
		editpartframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editpartframe.setTitle("Edit Part");
		editpartframe.pack();
		
		editpartframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2, dim.height / 2 - mainmenuframe.getSize().height / 2);
		editpartframe.setSize(800,800);
		editpartframe.setVisible(false);
		
	}
}


