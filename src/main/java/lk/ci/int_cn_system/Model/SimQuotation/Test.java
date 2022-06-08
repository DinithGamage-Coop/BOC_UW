package lk.ci.int_cn_system.Model.SimQuotation;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	List<String> createdColumnList = new ArrayList<String>();

	void readExcel() {
		try {
			File ExcelFile = new File("");// uploaded file
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(ExcelFile));
			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator2 = sheet.rowIterator();
			// int cc = 0;
			while (rowIterator2.hasNext()) {
				// Load of rows
				Row row = rowIterator2.next();
				int col_no = 0;
				for (String column : createdColumnList) {

					// Loop of columns
					Cell cell = row.getCell(col_no);
					String cellValue = dataFormatter.formatCellValue(cell);

					// your code here to generate List<FleetCoverData> list

					col_no++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}
}
