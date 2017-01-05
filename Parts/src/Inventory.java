import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Inventory {
	public static AddPartFrame addpartframe;
	public static EditPartFrame editpartframe;
	public static MainMenuFrame mainmenuframe;
	public static SearchPartFrame searchpartframe;
	public static AddManufacturerFrame addmanufacturerframe;

	public static void main(String[] args) throws IOException {
		// use look and feel for my system (Win32)
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		// put the frame in the middle of the display
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		mainmenuframe = new MainMenuFrame();
		mainmenuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainmenuframe.setTitle("Inventory System");
		mainmenuframe.pack();
		mainmenuframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainmenuframe.setSize(900, 800);
		mainmenuframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2,
				dim.height / 2 - mainmenuframe.getSize().height / 2);

		mainmenuframe.setVisible(true);

		addpartframe = new AddPartFrame();
		addpartframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addpartframe.setTitle("Add Part");
		addpartframe.pack();
		addpartframe.setSize(900, 800);
		addpartframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		addpartframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2,
				dim.height / 2 - mainmenuframe.getSize().height / 2);

		addpartframe.setVisible(false);

		editpartframe = new EditPartFrame();
		editpartframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editpartframe.setTitle("Edit Part");
		editpartframe.pack();
		editpartframe.setSize(900, 800);
		editpartframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		editpartframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2,
				dim.height / 2 - mainmenuframe.getSize().height / 2);
		
		editpartframe.setVisible(false);

		searchpartframe = new SearchPartFrame();
		searchpartframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchpartframe.setTitle("Search Part");
		searchpartframe.pack();
		searchpartframe.setSize(900, 800);
		searchpartframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		searchpartframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2,
				dim.height / 2 - mainmenuframe.getSize().height / 2);

		searchpartframe.setVisible(false);

		addmanufacturerframe = new AddManufacturerFrame();
		addmanufacturerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addmanufacturerframe.setTitle("Add Manufacturer");
		addmanufacturerframe.pack();
		addmanufacturerframe.setSize(900, 800);
		addmanufacturerframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		addmanufacturerframe.setLocation(dim.width / 2 - mainmenuframe.getSize().width / 2,
				dim.height / 2 - mainmenuframe.getSize().height / 2);

		addmanufacturerframe.setVisible(false);

	}
}
