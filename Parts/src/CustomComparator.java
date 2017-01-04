import java.util.Comparator;

public class CustomComparator implements Comparator<Object> {
	// the following avoids a "warning" with Java 1.5.0 complier (?)
	static final long serialVersionUID = 42L;

	private boolean sortOrder; // true = ascending, false = decending
	private int idx; // column index

	CustomComparator(int idxArg, boolean sortOrderArg) {
		sortOrder = sortOrderArg;
		idx = idxArg;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// cast the objects into row arrays

		Object[] a1 = (Object[]) o1;
		Object[] a2 = (Object[]) o2;

		// retrieve the cell contents at the specified column index

		Object c1 = a1[idx];
		Object c2 = a2[idx];

		// determine the data type, and perform the comparison

		if (c1 instanceof Number) {
			double d1 = ((Number) c1).doubleValue();
			double d2 = ((Number) c2).doubleValue();

			if (d1 == d2)
				return 0;

			if (sortOrder)
				return (d1 - d2) > 0 ? 1 : -1;
			else
				return (d1 - d2) > 0 ? -1 : 1;
		}

		else if (c1 instanceof Boolean) {
			boolean b1 = ((Boolean) c1).booleanValue();
			boolean b2 = ((Boolean) c2).booleanValue();

			if (b1 == b2)
				return 0;

			if (sortOrder)
				return b1 ? 0 : 1; // 'ascending' (trues first)
			else
				return b1 ? 1 : 0; // 'descending' (falses first)
		}

		else
		// treat all other classes as strings
		{
			String s1 = c1.toString();
			String s2 = c2.toString();

			if (sortOrder)
				return s1.compareTo(s2); // ascending
			else
				return s2.compareTo(s1); // descending
		}
	}
}