import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private static XSSFSheet spreadsheet;
	private static Cell cell;
	private static XSSFRow row;
	private static XSSFWorkbook workbook;
	private static File file;
	private static FileInputStream fIP;
	private static FileOutputStream out;
	private static CustomTableModel ctm_search;
	private static CustomTableModel ctm_manufacturer;

	public static void openSheet() throws IOException {
		file = new File("database.xlsx");
		fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		workbook = new XSSFWorkbook(fIP);

		spreadsheet = workbook.getSheet("Employee Info");

	}

	public static void closeSheet() throws IOException {

		// Write the workbook in file system
		out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();
	}

	public static void writeManufacturer(String manufacturer) throws Exception {

		File file = new File("database.xlsx");
		FileInputStream fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		XSSFWorkbook workbook = new XSSFWorkbook(fIP);

		// Open the existing sheet

		XSSFSheet spreadsheet = workbook.getSheet("Manufacturer");

		row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);

		cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(manufacturer);

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

	}

	public static void writeExcel(String partname, String manufacturer, String identification, String room, String bin,
			Integer quantity) throws Exception {

		openSheet();

		// Open the existing sheet
		row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);

		// Get part data from textfields

		ArrayList<String> row_data = new ArrayList<String>();

		row_data.add(partname);
		row_data.add(manufacturer);
		row_data.add(identification);
		row_data.add(room);
		row_data.add(bin);

		for (int i = 0; i < 5; i++) {
			cell = row.createCell(i);

			cell.setCellType(Cell.CELL_TYPE_STRING);

			cell.setCellValue(row_data.get(i));
		}

		cell = row.createCell(5);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(quantity);

		closeSheet();

	}

	public static void editExcel(Part old_data, Part new_data) throws Exception {

		// find the index of the row to be deleted and place in "selected_row"

		int indexOfRow = findMatchingRow(old_data);

		System.out.println(indexOfRow);

		if (indexOfRow != -1) {

			openSheet();

			row = spreadsheet.getRow(indexOfRow);

			// Get part data from textfields

			ArrayList<String> row_data = new ArrayList<String>();

			row_data.add(new_data.partname);
			row_data.add(new_data.manufacturer);
			row_data.add(new_data.identification);
			row_data.add(new_data.room);
			row_data.add(new_data.bin);

			for (int i = 0; i < 5; i++) {

				cell = row.getCell(i);

				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(row_data.get(i));

				System.out.println(row_data.get(i));

			}

			cell = row.getCell(5);

			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(new_data.quantity);

			closeSheet();
		}

	}

	public static int findMatchingRow(Part old_data) throws IOException {

		openSheet();

		int indexOfRow = -1;

		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			for (int cellCounter = 0; cellCounter < 6; cellCounter++) {

				if (row.getCell(0).getStringCellValue().equals(old_data.partname)
						&& row.getCell(1).getStringCellValue().equals(old_data.manufacturer)
						&& row.getCell(2).getStringCellValue().equals(old_data.identification)
						&& row.getCell(3).getStringCellValue().equals(old_data.room)
						&& row.getCell(4).getStringCellValue().equals(old_data.bin)
						&& row.getCell(5).getNumericCellValue() == old_data.quantity) {
					// we have found the correct row, so return its index

					return row.getRowNum();
				}

			}

		}
		closeSheet();

		return indexOfRow;
	}

	public static void deleteExcel(Part old_data) throws Exception {

		int selected_row = findMatchingRow(old_data);

		if (selected_row != -1) {

			openSheet();

			// find the index of the row to be deleted and place in
			// "selected_row"

			row = spreadsheet.getRow(selected_row);

			if (row != null) {
				spreadsheet.removeRow(row);
			}

			int lastRowNum = spreadsheet.getLastRowNum();

			if (selected_row >= 0 && selected_row < lastRowNum) {
				spreadsheet.shiftRows(selected_row, lastRowNum, -1);
			}

			closeSheet();

		}

	}

	public static void readExcel() throws IOException {

		openSheet();

		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					// System.out.print(cell.getNumericCellValue() + " \t\t ");
					break;
				case Cell.CELL_TYPE_STRING:
					// System.out.print(cell.getStringCellValue() + " \t\t ");
					break;
				}
			}
			// System.out.println();
		}
		closeSheet();
		// fIP.close();

	}

	public static void refreshSpreadsheet() throws IOException {

		openSheet();
		closeSheet();
	}

	public static JTable setupSearchTable() throws IOException {
		openSheet();

		Object[] columnNames = { "Description", "Manufacturer", "Identification", "Room", "Bin", "Quantity" };

		ctm_search = new CustomTableModel(getSearchRowData(), columnNames, true);
		// ctm.setColumnEditable(3, true);

		JTable fresh_table = new JTable(ctm_search);

		closeSheet();
		return fresh_table;

	}

	public static JTable setupManufacturerTable() throws IOException {
		File file = new File("database.xlsx");
		FileInputStream fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		XSSFWorkbook workbook = new XSSFWorkbook(fIP);

		// Open the existing sheet

		XSSFSheet spreadsheet = workbook.getSheet("Manufacturer");

		Object[] columnNames = { "Manufacturer" };

		ctm_manufacturer = new CustomTableModel(getManufacturerRowData(), columnNames, false);
		// ctm.setColumnEditable(3, true);

		JTable fresh_table = new JTable(ctm_manufacturer);

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

		return fresh_table;

	}

	public static Object[][] getRowsMatching(Integer columntosearch, String searchquery) throws IOException {

		ArrayList<Integer> indexes = searchExcel(columntosearch, searchquery);

		System.out.println("Spreadsheet last row number" + spreadsheet.getLastRowNum());

		Object[][] rowData = new Object[indexes.size()][6];

		int rowCounter = 0;
		
		for (int i : indexes) {
			System.out.println(i);
			
			row = spreadsheet.getRow(i);
			int j;
			for (j = 0; j < 5; j++) {

				cell = row.getCell(j);
				rowData[rowCounter][j] = cell.getStringCellValue();

			}
			cell = row.getCell(5);			
			rowData[rowCounter][5] = cell.getNumericCellValue();

			rowCounter++;
			
		}

		return rowData;

	}

	public static Object[][] getSearchRowData() throws IOException {

		// refreshSpreadsheet();

		Object[][] rowData = new Object[spreadsheet.getLastRowNum()][6];
		int rowCounter;
		int cellCounter;

		Iterator<Row> rowIterator = spreadsheet.iterator();
		rowCounter = 0;
		row = (XSSFRow) rowIterator.next();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			cellCounter = 0;

			while (cellIterator.hasNext()) {

				cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					rowData[rowCounter][cellCounter] = cell.getNumericCellValue();

					break;
				case Cell.CELL_TYPE_STRING:
					rowData[rowCounter][cellCounter] = cell.getStringCellValue();

					break;
				}
				cellCounter++;

			}

			rowCounter++;

		}

		return rowData;
	}

	public static Object[][] getManufacturerRowData() throws IOException {

		File file = new File("database.xlsx");
		FileInputStream fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		XSSFWorkbook workbook = new XSSFWorkbook(fIP);

		// Open the existing sheet

		XSSFSheet spreadsheet = workbook.getSheet("Manufacturer");

		Object[][] rowData = new Object[spreadsheet.getLastRowNum()][1];
		int rowCounter;
		int cellCounter;

		// rowData[0][0] = {"Mr.Small"};

		Iterator<Row> rowIterator = spreadsheet.iterator();
		rowCounter = 0;
		row = (XSSFRow) rowIterator.next();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			cellCounter = 0;

			while (cellIterator.hasNext()) {

				cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					rowData[rowCounter][cellCounter] = cell.getNumericCellValue();
					// rowData.add("" + cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					rowData[rowCounter][cellCounter] = cell.getStringCellValue();
					// rowData.add(cell.getStringCellValue());
					break;
				}
				cellCounter++;
				// System.out.println(" Cell = " + cellCounter);
			}

			rowCounter++;
			// System.out.println("Row = " + rowCounter);
		}

		return rowData;
	}

	public static void refreshSearchTable() {
		ctm_search.refresh();
	}

	public static void refreshManufacturerTable() {

		ctm_manufacturer.refresh();

	}

	public static void refreshSearch(Integer columntosearch,String searchquery) {
		ctm_search.refreshSearch(columntosearch,searchquery);
	}
	
	public static ArrayList<Integer> searchExcel(Integer columntosearch, String searchquery) throws IOException {
		// TODO Auto-generated method stub

		// search through the column "searchby" and find all results that
		// contain searchquery and update the search table

		openSheet();

		//int indexOfRow = -1;

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			//Iterator<Cell> cellIterator = row.cellIterator();

			if (row.getCell(columntosearch).getStringCellValue().contains(searchquery)) {
				// we have found the correct row, so return its index
				indexes.add(row.getRowNum());
				 System.out.println(row.getRowNum());
			}

		}
		closeSheet();

		return indexes;

	}

}
