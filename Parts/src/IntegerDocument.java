import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class IntegerDocument extends PlainDocument
	{
		// the following avoids a "warning" with Java 1.5.0 complier (?)
		static final long serialVersionUID = 42L;

		@Override
		public void insertString(int offset, String s, AttributeSet as) throws BadLocationException
		{
			if (s == null)
				return;

			// get the 'tentative' new content of the text field

			String tentative;
			int length = this.getLength();
			if (length == 0)
			{
				tentative = s;
			} else
			{
				String current = this.getText(0, length);
				StringBuffer sb = new StringBuffer(current);
				sb.insert(offset, s);
				tentative = sb.toString();
			}

			// see if the 'tentative' new content is OK
			// ... if so, perform the insert
			// ... if not, beep and leave as is

			try
			{
				Integer.parseInt(tentative);
				super.insertString(offset, s, as);
			} catch (NumberFormatException nfe)
			{
				Toolkit.getDefaultToolkit().beep();
				//System.out.print("\07");
				//System.out.flush();
			}
		}
	} // IntegerDocument