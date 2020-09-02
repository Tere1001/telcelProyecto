package com.telcel;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepsDefs {
    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void setUpTest() {
       // WebDriver driver;
     //   ChromeOptions opt = new ChromeOptions();
      //  opt.addArguments("--disable-notifications");
      //  driver = new ChromeDriver(opt);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

    @Given("I am on telcel Page")
    public void i_am_on_telcel_Page() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.telcel.com");
        throw new cucumber.api.PendingException();
    }

    @Given("I am on Cell phone page")
    public void iAmOnCellPhonePage() {
        driver.get("https://www.telcel.com/personas/equipos/telefonos-y-smartphones");
        
    }


    @When("I search cell phones by state")
    public void iSearchCellPhonesByState() {
        WebElement  modal = driver.findElement(By.cssSelector(".chosen-single span"));
        if(modal.isEnabled()){
            System.out.println("Modal presente");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-search input"))).sendKeys("Guanajuato");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-results li"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#entrarPerfilador"))).click();
        }

    }

    @Then("It displays cell phone by state")
    public void itDisplaysCellPhoneByState() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[ng-repeat*='devices']"), 10));
    }

    @When("I store the first cell phone info")
    public void  iStoreTheFirstCellPhoneInfo(int i) {

        List<WebElement> listaCelulares = driver.findElements(By.cssSelector("[ng-repeat*='devices']"));
        WebElement mosaicoN = listaCelulares.get(i - 1);

        WebElement nombreEquipo = mosaicoN.findElement(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"));
        String nombreEquipoCel = nombreEquipo.getText();

        WebElement elemPrecioEquipo = mosaicoN.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));
        String precioEquipo = elemPrecioEquipo.getText();
        double precio = Double.parseDouble(precioEquipo.replace("$", "").replace(",", ""));
        //.telcel-mosaico-equipos-capacidad-numero

        WebElement elemCapacidad = mosaicoN.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));

        String capacidad = elemCapacidad.getText();
        int capac = Integer.parseInt(capacidad.split(" ")[0]);

      //  return new cel(nombreEquipoCel, capac, precio); // revisar ******
    }

    @And("I click on this cell phone")
    public void iClickOnThisCellPhone() {
        List<WebElement> listaCelulares = driver.findElements(By.cssSelector("[ng-repeat*='devices']"));
        WebElement mosaicoN = listaCelulares.get(2 - 1);
        mosaicoN.click();

    }

    @Then("It displays the phone information")
    public void itDisplaysThePhoneInformation(String cel) throws Exception {
        //.ecommerce-ficha-tecnica-opciones-compra-titulo #ecommerce-ficha-tecnica-nombre
        //.ecommerce-ficha-tecnica-precio-pagos #ecommerce-ficha-tecnica-precio-obj
        //li[ng-repeat*='capacidades'] a
        WebElement nombreEquipo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-titulo #ecommerce-ficha-tecnica-nombre"));
        String nombreEquipoCel = nombreEquipo.getText();
        WebElement elemPrecioEquipo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-precio-pagos #ecommerce-ficha-tecnica-precio-obj"));
        String precioEquipo = elemPrecioEquipo.getText();
        double precio = Double.parseDouble(precioEquipo.replace("$", "").replace(",", ""));
        //.telcel-mosaico-equipos-capacidad-numero
        WebElement elemCapacidad = driver.findElement(By.cssSelector("li[ng-repeat*='capacidades'] a"));
        String capacidad = elemCapacidad.getText();
        int capac = Integer.parseInt(capacidad.split(" ")[0]);
     //   if(cel.getNombre().equals(nombreEquipoCel)
       //         && cel.getPrecio() == precio
         //       && cel.getCapacidad() == capac) {
            System.out.println("Los datos coinciden");
      //  } else {
            System.out.println("Los datos  no coinciden");
            throw new Exception("Los datos  no coinciden");
       // }
    }
}
