package junitproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseclass {
	public static WebDriver driver;
	public static void LaunchBrowser()
	{
		WebDriverManager.edgedriver().setup();
		driver = new ChromeDriver();
	}

	public static void launchurl(String url) {
		driver.get(url);

	}

	public static void pagetitel() {
		String title = driver.getTitle();
		System.out.println(title);

	}

	private void pageurl() {
		String url = driver.getCurrentUrl();
		System.out.println(url);

	}

	public static void passtext(String text, WebElement ele) {
		ele.sendKeys(text);

	}

	public static void closeEntirebrowser() {
		driver.quit();

	}

	public static void clickbtn(WebElement ele) {
		ele.click();

	}

	public static void screenshot(String imgname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File as = ts.getScreenshotAs(OutputType.FILE);
		File f = new File("location+mgname.png");
		FileUtils.copyFile(as, f);

	}

	public static Actions a;

	public static void movethecursor(WebElement targetwebelement) {
		a = new Actions(driver);
		a.moveToElement(targetwebelement).perform();

	}

	public static void dragdrop(WebElement dragandElement, WebElement drobElement) {
		a = new Actions(driver);
		a.dragAndDrop(dragandElement, drobElement).perform();

	}

	public static JavascriptExecutor js;

	public static void scrollthepage(WebElement tarwebelElement) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollintoview(true)", tarwebelElement);

	}

	public static void scroll(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollintoview(false)", element);

	}

	public static void excelread(String sheetname, int rownum, int cellnum) throws IOException {
		File f = new File("excelloction.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("data");
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();
		String value = "";
		if (cellType == 1) {
			String value2 = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			Date date = cell.getDateCellValue();
			SimpleDateFormat s = new SimpleDateFormat();
			String format = s.format(date);
		} else {
			double d = cell.getNumericCellValue();
			long l = (long) d;
			String valueOf = String.valueOf(l);
		}

	}

	public static void createnewexcellfile(int rownum, int cellnum, String writedata) throws IOException {
		File f = new File("excelloction.xlsx");
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("data");
		Row row = sheet.createRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(writedata);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}

	public static void createcell(int getrow, int crecell, String newdata) throws IOException {
		File f = new File("excel file.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("data");
		Row row = sheet.createRow(getrow);
		Cell cell = row.createCell(crecell);
		cell.setCellValue(newdata);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);

	}

	public static void createrow(int crerow, int crecell, String newdata) throws IOException {
		File f = new File("excel file.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("data");
		Row row = sheet.createRow(crerow);
		Cell cell = row.createCell(crecell);
		cell.setCellValue(newdata);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}

	public static void updatedatetoparticulercell(int gettherow, int getthecell, String existingdata,
			String writenewdata) throws IOException {
		File f = new File("excelloction.xlsx");
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.getSheet("data");
		Row row = sheet.getRow(gettherow);
		Cell cell = row.getCell(getthecell);
		String str = cell.getStringCellValue();
		if (str.equals(existingdata)) {
			cell.setCellValue(writenewdata);

		}
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);

	}
}


