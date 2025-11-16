import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class RF5 {

    @Test
    public void preencherFormularioVideo() {

        System.out.println("Tentando conectar ao Chrome na porta 9222...");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        System.out.println("SUCESSO: Conectado ao Chrome!");

        String XPATH_TITULO = "//input[@id=(//label[text()='Título do Vídeo']/attribute::for)]";
        String XPATH_URL    = "//input[@id=(//label[text()='URL do Vídeo']/attribute::for)]";
        String XPATH_DESCRICAO = "//textarea[@id=(//label[text()='Descrição do Vídeo']/attribute::for)]";
        String XPATH_BOTAO_ADICIONAR = "//button[@type='button' and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'adicionar')]";

        String videoTitulo = "Meu Vídeo Cadastrado por Teste";
        String videoUrl = "https://youtu.be/zfZlNBe__pE?si=fFQZY2ID9nQYkmt2";
        String videoDescricao = "Cadastrado via Selenium.";

        try {
            System.out.println("Procurando o campo 'Título do Vídeo' na página...");
            WebElement campoTitulo = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_TITULO))
            );

            System.out.println("Formulário encontrado! Preenchendo campos...");
            campoTitulo.sendKeys(videoTitulo);
            driver.findElement(By.xpath(XPATH_URL)).sendKeys(videoUrl);
            driver.findElement(By.xpath(XPATH_DESCRICAO)).sendKeys(videoDescricao);

            System.out.println("Campos preenchidos. Clicando no botão (case-insensitive)...");
            driver.findElement(By.xpath(XPATH_BOTAO_ADICIONAR)).click();

            System.out.println("Formulário enviado. Teste concluído com sucesso!");


        } catch (Exception e) {
            System.out.println("Ocorreu um erro na etapa final.");
            e.printStackTrace();
            throw new AssertionError("Teste falhou: " + e.getMessage());
        } finally {
            System.out.println("Teste finalizado. Deixando o navegador aberto.");
        }
    }
}