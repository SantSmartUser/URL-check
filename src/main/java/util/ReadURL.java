package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadURL extends Base{
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;

	private static XSSFSheet sheet = null;
	// private XSSFRow row = null;
	private XSSFCell cell = null;
	DataFormatter df = new DataFormatter();

	public List<String> readURLsFromExcel() throws IOException {
		List<String> urls = new ArrayList<>();
// mention URL excel location.
		fis = new FileInputStream(props.getProperty("url_excel"));
		// fileOut = new FileOutputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);

		for (Row row : sheet) {
			XSSFCell cell = (XSSFCell) row.getCell(0); // Assuming the URLs are in the first column (column index 0)

			if (cell != null) {
				String url = cell.getStringCellValue();
				urls.add(url);

			}
		}

		return urls;
	}

}
