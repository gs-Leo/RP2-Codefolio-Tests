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

public class CT6_03 {
        private WebDriver driver;
        private WebDriverWait wait;
        private final Duration timeout = Duration.ofSeconds(15);
        private final String url = "https://testes-codefolio.web.app/";
        private JavascriptExecutor js;

        private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
        private final String Firebase_value = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763485802422\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1764000250329\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocIPSVJF5ZjtLwj6MndKM7i1yp2tDbMn13DkkmLcptbfW7FRQ74=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"107226265685191574291\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM5OTk2OTgsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2NDEyNjQwNSwiZXhwIjoxNzY0MTMwMDA1LCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.CrDV9pW381BhS9gga_f3FAlHG5_S1-fqeVzyVKc4b-NLxCMZF8MTcQYq_12k7NIUZ2t1UmMkJYLDCN7KF08V7tw6jPRrez7pvw4OUOcv78k7MhxjHkCE4mvnKSgI6evv4OaSjKk1gz3OvXlO6X7_tQYFBCVxjl0qaZ-Suw938HnUWA44KFQL_A81aJFZCVcTnK8087oCgKs14J-2iJBUwPpfeK0q3edW8Bkj4L4rD8MQTqi4yGSE_K6GqTrh23JytWAK8Mi735XB0e_Rm0ay22pJVIW0jak_VbvQTGcKYPbH7-NdmtiIeQS-AhZ7JNEeS_bI1dK23m538C_DHpNTwQ\",\"expirationTime\":1764130005974,\"refreshToken\":\"AMf-vBzikE_VcC-AzC0oscuLYB4004s7RuPHjz6ysAk39BBBDHDEDvxRYJoNz-Uwpr7GV_tAvg4qU51a-O04Nm916tMlLIY9RdFerEmYWK0Gh6K1qvZfzrFxmGheYucbfTYxY287lOFNEEFJeN1qUz32FRG0rAbiztiJSQQHv_4sgB7oT9T0gto5xkfwOMxN5SwROisuM1Qkxb3de27UyuePOcs5VGVUGJaJQY7cP6VjQuxcq3ySbWC36lZYwxKK4_2ViHXjQubj2--FVdmX0SN3k4xprhRikYUye-tt10i0Jiu6at4lHEdJu5sENr6hUk98FfFDCzy6sENtmUXzr0tiwnwRQUPM_ws-H40iZ5cG1lNfuAKi0nOPtZz1REsumtDwUJ0vtoPwUIyn6U9OeO8xYqCZATjAZYShZJHImrpK--xmUZ1ZKRYFzQIv-0i_vQ3iUvWTnjI3ue4W54_iYA3dC9cciar5Y0DwPIgD6AunP6DSX9JGLyU\"},\"uid\":\"Xv0X6Yea4HXHinLMmBaa1jqDGx62\"}";        @BeforeEach
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
    public void testTentativaEdicaoUrlInvalida() throws InterruptedException {
        System.out.println("🚀 Iniciando CT-6.03 - Edição com URL Inválida");

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

        // 4. INSERIR URL INVÁLIDA
        try {
            System.out.println("✍️ Inserindo URL quebrada...");

            // Acha o campo URL
            WebElement campoUrl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='URL do Vídeo']/..//input")
            ));

            campoUrl.click();
            campoUrl.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            campoUrl.sendKeys(Keys.BACK_SPACE);
            campoUrl.sendKeys("link_quebrado_teste.com.br"); // DADO INVÁLIDO

            // Clica no Título para tirar o foco da URL
            driver.findElement(By.xpath("//label[text()='Título do Vídeo']/..//input")).click();

            System.out.println("✅ URL inválida inserida.");

        } catch (Exception e) {
            Assertions.fail("❌ Erro ao interagir com o campo URL.");
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

        // 6. VALIDAR O RESULTADO (Erro esperado)
        Thread.sleep(1500);

        // Verifica mensagens de erro comuns ("Inválida", "valid URL", etc)
        boolean temMensagemErro = !driver.findElements(By.xpath("//*[contains(text(), 'inválida') or contains(text(), 'valid') or contains(text(), 'incorreta')]")).isEmpty();

        // Verifica se continua aberto
        boolean Aberto = !driver.findElements(By.xpath("//h6[contains(text(), 'Editar Vídeo')]")).isEmpty();

        if (temMensagemErro) {
            System.out.println("✅ SUCESSO: Mensagem de erro de URL exibida.");
        } else if (Aberto) {
            System.out.println("✅ SUCESSO: O sistema impediu o salvamento (modal continua aberto) ao detectar URL inválida.");
        } else {
            Assertions.fail("❌ FALHA CRÍTICA: O modal fechou! O sistema aceitou uma URL inválida.");
        }

        System.out.println("🎉 Teste de URL Inválida Finalizado.");
    }
}

