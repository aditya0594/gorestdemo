package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class Utils {


    public static RequestSpecification requestspec;

    public RequestSpecification requestspecification() throws IOException {
        String env = System.getProperty("env", "dev");
        ConfigLoader.load(env);
        String baseurl1 = ConfigLoader.get("baseUrl");
        String token = ConfigLoader.get("token");
        System.out.println("Token from the properties file: " + token);
        System.out.println("baseurl from the properties file: " + baseurl1);


        if (requestspec == null) {

            // here this printStream is a object which asking for the lRequestLoggingFilter.logRequestTo()
            PrintStream log = new PrintStream(new FileOutputStream("Logging.txt")); // here we are creating the file of logs
            requestspec = new RequestSpecBuilder().setBaseUri(baseurl1)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))//this is for the logs in the text file
                    .addHeader("Authorization", token)
                    .setContentType(ContentType.JSON)
                    .build();
            return requestspec;
        }
        return requestspec;
    }

    public static void validateErrorResponse(String responseBody, String expectedField, String expectedMessage) {
        JsonPath js = new JsonPath(responseBody);
        List<Map<String, String>> errors = js.getList("");

        boolean matchFound = false;

        for (Map<String, String> error : errors) {
            String field = error.get("field");
            String message = error.get("message");
            if (field.equals(expectedField) && message.equals(expectedMessage)) {
                matchFound = true;
                break;
            }
        }
        Assert.assertTrue(matchFound, "Expected error for field '" + expectedField + "' with message '" + expectedMessage + "' was not found");
    }



    public static String getJsonPath(String response, String key) {
        String resp = response.toString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "aditya" + uuid + "@yopmail.com";
    }

    public static void writeExcel(String sheetName, int rowNumber, int colNumber, String value) {
        String filePath = "src/test/java/Resources/ExcelFile.xlsx"; // Excel file path

        try {
            File file = new File(filePath);
            Workbook workbook;
            Sheet sheet;

            // Check if file exists
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    sheet = workbook.createSheet(sheetName);
                }
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(sheetName);
            }

            // Ensure the row exists
            Row row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }

            // Create and set cell value at the specified column
            Cell cell = row.createCell(colNumber);
            cell.setCellValue(value);

            // Save the file
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            System.out.println("Value '" + value + "' written successfully to Excel at (Row: " + (rowNumber + 1) + ", Column: " + (colNumber + 1) + ")");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue()); // Convert double to int to avoid decimal
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    public static String readExcel(String sheetName, int rowNumber, int colNumber) {
        String filePath = "src/test/java/Resources/ExcelFile.xlsx"; // Excel file path
        String cellValue = "";

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Excel file does not exist: " + filePath);
                return "";
            }

            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet '" + sheetName + "' not found.");
                workbook.close();
                return "";
            }

            Row row = sheet.getRow(rowNumber);
            if (row == null) {
                System.out.println("Row " + rowNumber + " not found.");
                workbook.close();
                return "";
            }

            Cell cell = row.getCell(colNumber);
            if (cell == null) {
                System.out.println("Cell at Row: " + rowNumber + ", Column: " + colNumber + " is empty.");
            } else {
                cellValue = getCellValueAsString(cell);
            }

            workbook.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellValue;
    }


}

