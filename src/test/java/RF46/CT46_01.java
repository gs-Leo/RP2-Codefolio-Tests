package RF46;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CT46_01 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763485802422\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1764000250329\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocIPSVJF5ZjtLwj6MndKM7i1yp2tDbMn13DkkmLcptbfW7FRQ74=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"107226265685191574291\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM5OTk2OTgsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2NDAxODA3OSwiZXhwIjoxNzY0MDIxNjc5LCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.qPLELxK0C8vmd6FQL-e1Km3tjuvM9SqIAWy8pvCXUzAdCf5hxK65J3-1Bt8M0nZ80g4tOatsuqsyhlacFqAahkPIXRcrs4z2dm8P0vAwzibug-NVS-8FcVcRoKMIuxsJPrbU4lXkqRfAKmk5ZHZuqTBt1Vd6Px71LXg9ufByu_SMdWow3CLuUGk5buYSZRucbi4paRJuEMcCfdmCBF-PgOaR_8z4pCbXNZPUYARu-VOoZRPxro1Ny0y-vIIPZTf6ppnFAgoYZEtIxCnFlSbObF49V9FX0ttkwzBXBjyvkBZ-17sltkVtW7GD8zNfU-sHIOL732-IIeNXkgyZHbDCRw\",\"expirationTime\":1764021680227,\"refreshToken\":\"AMf-vBzikE_VcC-AzC0oscuLYB4004s7RuPHjz6ysAk39BBBDHDEDvxRYJoNz-Uwpr7GV_tAvg4qU51a-O04Nm916tMlLIY9RdFerEmYWK0Gh6K1qvZfzrFxmGheYucbfTYxY287lOFNEEFJeN1qUz32FRG0rAbiztiJSQQHv_4sgB7oT9T0gto5xkfwOMxN5SwROisuM1Qkxb3de27UyuePOcs5VGVUGJaJQY7cP6VjQuxcq3ySbWC36lZYwxKK4_2ViHXjQubj2--FVdmX0SN3k4xprhRikYUye-tt10i0Jiu6at4lHEdJu5sENr6hUk98FfFDCzy6sENtmUXzr0tiwnwRQUPM_ws-H40iZ5cG1lNfuAKi0nOPtZz1REsumtDwUJ0vtoPwUIyn6U9OeO8xYqCZATjAZYShZJHImrpK--xmUZ1ZKRYFzQIv-0i_vQ3iUvWTnjI3ue4W54_iYA3dC9cciar5Y0DwPIgD6AunP6DSX9JGLyU\"},\"uid\":\"Xv0X6Yea4HXHinLMmBaa1jqDGx62\"}";    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, timeout);
        js = (JavascriptExecutor) driver;
        driver.get(url);

        System.out.println("Injetando dados de autenticação no local storage...");
        try {
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                    Firebase_key,
                    Firebase_value);
            System.out.println("Injeção no local storage bem-sucedida.");

        } catch (Exception e) {
            System.out.println("Falha critica ao injetar no local storage." + e.getMessage());
            driver.quit();
            throw new RuntimeException("Falha no setup do local storage.", e);
        }

        System.out.println("Recarregando a página...");
        driver.navigate().refresh();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("A fechar o navegador.");
            //driver.quit();
        }
    }

    @Test
    public void testIniciarCursoSemPin() throws InterruptedException {
        System.out.println("🚀 Iniciando CT-46.01 - Iniciar Curso Aberto");

        // 1. Acessar a lista de cursos
        try {
            WebElement menuCursos = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/listcurso']")));
            menuCursos.click();
        } catch (Exception e) {
            driver.get("https://testes-codefolio.web.app/listcurso");
        }
        Thread.sleep(2000);

        // 2. Identificar um curso sem cadeado e Clicar em "Começar"
        // Vamos pegar o PRIMEIRO botão "Começar" que aparecer na tela.
        // XPath procura por um botão que tenha exatamente o texto "Começar"
        try {
            // XPath explicado:
            // 1. Encontre um Card que nao tenha o ícone de cadeado e dentro desse card, pegue o botão com texto "Começar"
            WebElement botaoComecar = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'MuiCard-root') and not(.//*[@data-testid='LockIcon'])]//button[text()='Começar']")
            ));

            // Garante que o botão está visível (scroll se precisar)
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoComecar);
            Thread.sleep(500);

            System.out.println("✅ Botão 'Começar' de um curso ABERTO encontrado.");
            botaoComecar.click();

        } catch (Exception e) {
            Assertions.fail("❌ Não encontrou nenhum curso aberto (sem cadeado). Verifique a lista.");
        }

        // 3. Verificar redirecionamento (Tela de conteúdo)
        // Esperamos que a URL mude para algo contendo "/watch" ou que apareça o player de vídeo
        try {
            WebElement botaoVerVideo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(), 'Ver Vídeo')]")
            ));

            Assertions.assertTrue(botaoVerVideo.isDisplayed(), "O botão 'Ver Vídeo' deveria estar visível.");
            System.out.println("✅ Sucesso! O sistema entrou na tela do curso e o botão 'Ver Vídeo' está visível.");

        } catch (Exception e) {
            Assertions.fail("❌ O sistema não carregou a tela do curso. O botão 'Ver Vídeo' não foi encontrado.");
        }

        System.out.println("🎉 Teste Finalizado com Sucesso!");
    }
}


