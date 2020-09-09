import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class mainFunc {


      private WebDriver driver = new ChromeDriver();
      private variables myVar = new variables();


    @Before
    public  void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(myVar.BASE_URL);

    }

    @After
    public void close() {
        driver.close();
    }


    private void openpage() {
        driver.get(myVar.BASE_URL);
    }


    //проверяем наличие логотипа банка
    public void logoCheck() throws Exception {
        try {
            myVar.LOGO = driver.findElement(myVar.LOGO_SVG).getAttribute("src");
            Check(myVar.LOGO, myVar.LOGO_ORIGINAL);
            System.out.println("logoCheck - OK");
        } catch (Throwable T) {
            System.out.println("logoCheck - Failed");
        }
    }

    public void linesCkeck() throws Exception {
                try {
            driver.findElement(By.xpath("//a[contains(text(),'Careers')]")).click();
            driver.findElement(By.xpath("//h1[contains(.,'Vacancies')]")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Test Automation Engineer')]")).click();

                    Thread.sleep(2000); // Да, полохая практика, но так надо ;)
                    List <WebElement> elements  =  driver.findElements(By.xpath("//p/strong/em[contains(text(), 'Professional skills and qualification:')]//following::p[1]"));
                    List<String> strings = new ArrayList<>();
                    for (WebElement object : elements) {
                        String text = object.getText();

                        if (!text.trim().isEmpty())
                            strings.add(text);
                    }

                    ArrayList arrayList = new ArrayList(Arrays.asList(strings.get(0).split(";")));
                    System.out.println("Lines in list "+arrayList.size());
                    int lines = arrayList.size();
                    Assert.assertEquals(lines, 5);
            System.out.println("LinesCheck - OK");
        } catch (Throwable T) {
            System.out.println("LinesCheck - Failed");

        }


    }


    private void Check(String variable, String staticVariable) {
        Assert.assertEquals(variable, staticVariable);
    }

}
