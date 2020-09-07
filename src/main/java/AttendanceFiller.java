import java.util.ArrayList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AttendanceFiller
{
    public AttendanceFiller(String firstName, String lastName, String studentId, ArrayList<String> classes)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.classes = classes;
    }
    public void fillAttendance()
    {
        //Set XPath Locations
        String inputFirstName = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/input";
        String inputLastName = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input";
        String inputStudentId = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input";

        //Radio Buttons XPath
        String[] periods = {"//*[@id=\"i20\"]", "//*[@id=\"i23\"]", "//*[@id=\"i26\"]", "//*[@id=\"i35\"]", "//*[@id=\"i38\"]"};
        String loggedIn = "//*[@id=\"i54\"]";

        //Set up browser
        System.setProperty("webdriver.chrome.driver", "src\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Gamer\\AppData\\Local\\Google\\Chrome\\User Data Selenium");
        browser = new ChromeDriver(options);

        //Fill forms
        for (int i = 0; i < classes.size(); i++)
        {
            browser.get(classes.get(i));
            if (alertPresent())
            {
                alert.accept();
            }
            browser.manage().window().maximize();

            browser.findElement(By.xpath(inputFirstName)).sendKeys(firstName);
            browser.findElement(By.xpath(inputLastName)).sendKeys(lastName);
            browser.findElement(By.xpath(inputStudentId)).sendKeys(studentId);
            browser.findElement(By.xpath(periods[i])).click();
            browser.findElement(By.xpath(loggedIn)).click();
            browser.findElement(By.xpath(loggedIn)).submit();
        }
        browser.close();
        System.exit(0);
    }

    private boolean alertPresent()
    {
        try
        {
            alert = browser.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }

    private WebDriver browser;
    private Alert alert;
    private String firstName;
    private String lastName;
    private String studentId;
    private ArrayList<String> classes;
}
