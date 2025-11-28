package RF6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.Duration;

public class CT6_02 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763485802422\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1764000250329\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocIPSVJF5ZjtLwj6MndKM7i1yp2tDbMn13DkkmLcptbfW7FRQ74=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"107226265685191574291\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM5OTk2OTgsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2NDEwNzYxMywiZXhwIjoxNzY0MTExMjEzLCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.OevrmmY7yI6X1FsEUKeLRzzibK-foBs6V0y_WNcHOeBc7LFULjiHpg71djTgdyzj8QYpvDQQvWk9JDv3h5TG0PBVmLa8xW3GjpU2wCRfB8l_yr_9Rb2JMGFDgP2a75Ck_o24y3Ly7QnSpY47n8-n-qZL9R0ArWMUNLhJWPYghgfIOFY_YUVCDKjsF245PjzhKQnMqoSPL2nAIxg1Pv2N39kfjVM8xK2Dx3LQ1px6p97Yhpg5Qf9hbQ-a5_4YQ4YCF3p_6b44kZAvHvdbz71hWVGW72qXOb4FIX6EFnaUaJYhsoBcK3XzCh90KhNP5ntpxarkr8lwOkh9rsu7pQoBYg\",\"expirationTime\":1764111217704,\"refreshToken\":\"AMf-vBzikE_VcC-AzC0oscuLYB4004s7RuPHjz6ysAk39BBBDHDEDvxRYJoNz-Uwpr7GV_tAvg4qU51a-O04Nm916tMlLIY9RdFerEmYWK0Gh6K1qvZfzrFxmGheYucbfTYxY287lOFNEEFJeN1qUz32FRG0rAbiztiJSQQHv_4sgB7oT9T0gto5xkfwOMxN5SwROisuM1Qkxb3de27UyuePOcs5VGVUGJaJQY7cP6VjQuxcq3ySbWC36lZYwxKK4_2ViHXjQubj2--FVdmX0SN3k4xprhRikYUye-tt10i0Jiu6at4lHEdJu5sENr6hUk98FfFDCzy6sENtmUXzr0tiwnwRQUPM_ws-H40iZ5cG1lNfuAKi0nOPtZz1REsumtDwUJ0vtoPwUIyn6U9OeO8xYqCZATjAZYShZJHImrpK--xmUZ1ZKRYFzQIv-0i_vQ3iUvWTnjI3ue4W54_iYA3dC9cciar5Y0DwPIgD6AunP6DSX9JGLyU\"},\"uid\":\"Xv0X6Yea4HXHinLMmBaa1jqDGx62\"}";    @BeforeEach
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
    public void testTentativaEdicaoSemTitulo() throws InterruptedException {
        System.out.println("🚀 Iniciando CT-6.02 - Edição com Título Vazio");

        // 1. Acessar Menu de Gerenciamento
        try {
            WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'MuiAvatar-root')]")));
            avatar.click();
            WebElement menuGerenciar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Gerenciamento de Cursos']")));
            menuGerenciar.click();
            System.out.println("✅ Acessou a tela de Gerenciamento de Cursos.");
        } catch (Exception e) {
            driver.get("https://testes-codefolio.web.app/manage-courses");
        }
        Thread.sleep(2000);

        // 2. Clicar em "Gerenciar Curso" no card "Teste RF6"
        try {
            WebElement btnGerenciar = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'MuiCard-root') and .//*[contains(text(), 'Teste RF6')]]//button[text()='Gerenciar Curso']")
            ));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnGerenciar);
            Thread.sleep(500);
            btnGerenciar.click();
            System.out.println("✅ Entrou no curso 'Teste RF6'.");
        } catch (Exception e) {
            Assertions.fail("❌ Não encontrou o curso 'Teste RF6'.");
        }
        Thread.sleep(3000);

        // 3. Rolar até o final e clicar no lápis
        System.out.println("✏️ Buscando lápis...");
        // Procura a linha que tem o texto 'TESTE RF6' e pega o botão
        WebElement btnLapis = driver.findElement(By.xpath("//li[contains(., 'TESTE RF6')]//button"));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnLapis);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", btnLapis);
        System.out.println("✅ Clicou no lápis.");
        Thread.sleep(1000);

        // 4. APAGAR O TÍTULO
        try {
            System.out.println("✍️ Apagando o título...");
            WebElement campoTitulo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='Título do Vídeo']/..//input")
            ));
            campoTitulo.click();
            campoTitulo.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            campoTitulo.sendKeys(Keys.BACK_SPACE);

            // Clica na URL para tirar o foco do título
            driver.findElement(By.xpath("//label[text()='URL do Vídeo']/..//input")).click();
            System.out.println("✅ Título apagado.");
        } catch (Exception e) {
            Assertions.fail("❌ Erro ao limpar o campo título.");
        }

        // 5. TENTAR SALVAR
        try {
            System.out.println("💾 Buscando botão de salvar...");
            WebElement btnSalvar = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'EDITAR') or contains(text(), 'Editar') or contains(text(), 'SALVAR')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnSalvar);
            Thread.sleep(500);
            try {
                btnSalvar.click();
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", btnSalvar);
            }
            System.out.println("✅ Clicou em 'EDITAR VÍDEO'.");
        } catch (Exception e) {
            Assertions.fail("❌ Botão de salvar não encontrado.");
        }

        // 6. VALIDAR O ERRO
        Thread.sleep(1500);

        // Procura se existe mensagem de erro na tela
        boolean temMensagemErro = !driver.findElements(By.xpath("//*[contains(text(), 'obrigatório') or contains(text(), 'Preencha')]")).isEmpty();
        // Verifica se o título "Editar Vídeo" ainda está na tela
        boolean modalAberto = !driver.findElements(By.xpath("//h6[contains(text(), 'Editar Vídeo')]")).isEmpty();

        if (temMensagemErro) {
            System.out.println("✅ SUCESSO: Mensagem de erro exibida.");
        } else if (modalAberto) {
            System.out.println("✅ SUCESSO: O sistema impediu o salvamento (modal continua aberto).");
        } else {
            Assertions.fail("❌ FALHA: O modal fechou! O sistema pode ter salvo o vídeo sem título.");
        }

        System.out.println("🎉 Teste de Validação Finalizado.");
    }
}