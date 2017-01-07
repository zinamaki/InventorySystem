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
	private static CustomTableModel ctm;

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

	public static void editExcel(String partname, String manufacturer, String identification, String room, String bin,
			Integer quantity) throws Exception {

		openSheet();

		// find the index of the row to be deleted and place in "selected_row"

		int selected_row = 0;

		row = spreadsheet.getRow(selected_row);

		// Get part data from textfields

		ArrayList<String> row_data = new ArrayList<String>();

		row_data.add(partname);
		row_data.add(manufacturer);
		row_data.add(identification);
		row_data.add(room);
		row_data.add(bin);

		// row_data.add(EditPartFrame.textField_quantity.getText());

		for (int i = 0; i < 5; i++) {

			cell = row.getCell(i);

			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(row_data.get(i));

		}

		cell = row.getCell(5);

		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(quantity);

		closeSheet();

	}

	public static void deleteExcel(String partname, String manufacturer, String identification, String room, String bin,
			Integer quantity) throws Exception {

		openSheet();

		int selected_row = 0;

		// find the index of the row to be deleted and place in "selected_row"

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
					System.out.print(cell.getNumericCellValue() + " \t\t ");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + " \t\t ");
					break;
				}
			}
			System.out.println();
		}
		closeSheet();
		// fIP.close();

	}

	public static void refreshSpreadsheet() throws IOException {

		file = new File("database.xlsx");
		fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		workbook = new XSSFWorkbook(fIP);
		
		spreadsheet = workbook.getSheet("Employee Info");
		// Write the workbook in file system
				out = new FileOutputStream("database.xlsx");
				workbook.write(out);
				out.close();
	}

	public static JTable setupSearchTable() throws IOException {
		file = new File("database.xlsx");
		fIP = new FileInputStream("database.xlsx");

		// Get the workbook instance for XLSX file

		workbook = new XSSFWorkbook(fIP);
		
		spreadsheet = workbook.getSheet("Employee Info");

		Object[] columnNames = { "Description", "Manufacturer", "Identification", "Room", "Bin", "Quantity" };

		ctm = new CustomTableModel(getSearchRowData(), columnNames, true);
		// ctm.setColumnEditable(3, true);

		JTable fresh_table = new JTable(ctm);

		out = new FileOutputStream("database.xlsx");
		workbook.write(out);
		out.close();

		return fresh_table;

	}

	public static Object[][] getSearchRowData() throws IOException {

		//refreshSpreadsheet();
		
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
	public static void refreshSearchTable(){
		ctm.refresh();
	}
	

}
