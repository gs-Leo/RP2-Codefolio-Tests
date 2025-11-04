import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
// import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.junit.jupiter.api.Test;


public class RF7 {
    @Test
    public void Exemplo(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://testes.codefolio.com.br");

            WebElement cursos = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("/html/body/div/section[2]/div[2]/div/div[1]/div/div[2]/div/a[2]") // <-- MUDÁMOS O XPATH AQUI
                    )
            );

            System.out.println("Elemento 'Cursos' (span) encontrado. A tentar clicar...");
            cursos.click();

            System.out.println("Cliquei em 'Cursos' com sucesso! A aguardar 10 segundos.");
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("A fechar o navegador.");
            driver.quit();
        }
    }
}