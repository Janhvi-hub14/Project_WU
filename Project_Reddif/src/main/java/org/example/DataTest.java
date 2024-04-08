package org.example;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DataTest{

    public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {

        Map<String, String> Testdata1 = ExcelSheetData();

        for (Entry<String, String> Map1 : Testdata1.entrySet()) {

            System.out.println("Key is: " + Map1.getKey() + " ," + "Value is: " + Map1.getValue());

        }

        Map<String, String> Testdata2 = WebtableData();
        for (Entry<String, String> Map2 : Testdata2.entrySet()) {

            System.out.println("Key is: " + Map2.getKey() + " ," + "Value is: " + Map2.getValue());

        }

        System.out.println("Comparing Two Hashmap for All keys");

        System.out.println(Testdata1.keySet().equals(Testdata2.keySet()));

        System.out.println("Comparision of Two Hashmap");

        boolean isValid = validateValues(Testdata1, Testdata2, " ");
        System.out.println("Values are valid for keys of stock price ? " + isValid);
        // System.out.println(isValid);;

    }

    public static boolean validateValues(Map<String, String> testdata1, Map<String, String> testdata2, String key) {
        if (testdata1.containsKey(key) && testdata2.containsKey(key)) {
            return testdata1.get(key).equals(testdata2.get(key));
        } else {
            return false;
        }
    }

    public static Map<String, String> ExcelSheetData() throws EncryptedDocumentException, IOException {
        System.out.println("Fetching Data from Excel sheet");
        Map<String, String> excelmap = new HashMap<String, String>();

        FileInputStream file = new FileInputStream("C:/Users/acer_/OneDrive/Desktop/NewData/ExelSheet/NewData.xls");
        // FileInputStream file1 = new FileInputStream(InputStream);
        Sheet sh = WorkbookFactory.create(file).getSheet("Sheet1");
        int LastRow = sh.getLastRowNum();
        for (int i = 0; i <= LastRow; i++) {
            Row row = sh.getRow(i);
            Cell KeyCell = row.getCell(0);
            String Key = KeyCell.getStringCellValue().trim();
            Cell ValueCell = row.getCell(1);
            String Value = ValueCell.getStringCellValue().trim();

            excelmap.put(Key, Value);
        }
        return excelmap;
    }

    public static Map<String, String> WebtableData() throws InterruptedException {

        System.out.println("Fetching Data from Website sheet");

        System.setProperty("web driver.chrome.driver","C:/Users/acer_/OneDrive/Desktop/ChromD/chromedriver.exe" );
        WebDriver driver = new ChromeDriver();
        driver.get("https:/money.rediff.com/losers/bse/daily/groupall");
        driver.manage().window().maximize();
        Thread.sleep(5000);

        Map<String, String> webtablemap = new HashMap<String, String>();

        List<WebElement> companyName = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
        List<WebElement> currentPrize = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));

        for (int i = 0; i <= 20; i++) {
            String coloum1data = companyName.get(i).getText();
            String coloum2data = currentPrize.get(i).getText();
            webtablemap.put(coloum1data, coloum2data);
        }
        driver.quit();
        return webtablemap;
    }

}
