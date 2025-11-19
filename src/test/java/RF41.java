import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.devtools.v140.debugger.Debugger.pause;

public class RF41 extends MetodosTeste{

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";

    private static final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763434890844\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1763434890845\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocI6BhRqR9kQ2w6sIU_wWXSqI2PDtQ1BRzToca1OLhky1rjxQA=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"100155757080180289674\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"refreshToken\":\"AMf-vBzVLKL5CyOUHtVyVqZKixvXJ6xDhca65hBRYDBZ4jbdALrMe2V0MlagvLC3Jtw06HutBu9zYzXvByJnJYAcNo_3F33OUTvk-QsG9YMMkY1kSrDOFLxghFsyjqT4lSFnZPjJQM9W4DkTK80qJDl1cbLgZ1RdT0SYobLMOTlqnCjuc89C52JNpoOnf7NA31XB48U0AjotnC0m7tchRHcUOTRZHYKuGV7_1UuUnArJhXNnUREj06FOWUaZPDyJP35elnQ0fPupe61LaYxQUXLSILdVuW8Qj5XQhADoL1EayWmS3jRdqT35VzAMv901KWtFM8KXnSiaGafvhj6FbtBNuCJJX-J5Bk1-4OFzQjuTQH9CzKLhgsT--hFHJ0sVz9iDA8D2XAnI5oin2A3tutEDqGD4brNwVzGD4tqMTiNjrPZ8rfyRVrQIivUcEiNEKCkWE9gO1PIqSMsxuM98uguepyO8GJvLEQ\",\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRmFkZSBIYXNzYW4gSHVzZWluIEthbmFhbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJNkJoUnFSOWtRMnc2c0lVX3dXWFNxSTJQRHRRMUJSelRvY2ExT0xoa3kxcmp4UUE9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM0MzQ5MDMsInVzZXJfaWQiOiJTRzVMU3c1VnduWXZJeEx0aU9FVEQzZVdWUTAyIiwic3ViIjoiU0c1TFN3NVZ3bll2SXhMdGlPRVREM2VXVlEwMiIsImlhdCI6MTc2MzUyNDYxNCwiZXhwIjoxNzYzNTI4MjE0LCJlbWFpbCI6ImZhZGVrYW5hYW4uYWx1bm9AdW5pcGFtcGEuZWR1LmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDAxNTU3NTcwODAxODAyODk2NzQiXSwiZW1haWwiOlsiZmFkZWthbmFhbi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.eyehgmPYuTGonAQv7yrlgwqKr929SP5XvOF8y_fgHHiv1nXnka3cQcFZs5Zo06U-DX8Sq32gHFGvXzoVIB7Zp7LXufyrTFZjMRCtRc4GGVED0LDfROrVSGnPzaV8t4L6gvxlbi69eedhJDZX4KtlSAOdjx5o-NEVqK_Jlb-LMJM2MuWvhJ53eNCVSCkCc1HA_akNTULyFEKFgugnTpNU0QIeIgDPtwwfa-hGtVTBj-fhUOsiiq2Ok4GkkcdLZWe4VQLr5W2D_tqKOo-6GbcNwr_K_-VrmrZ3cZqwuUAsWRYFJKcP8YCzcpfsYE8fQYMLIp-yzcVfKRIGZ9unrcheHw\",\"expirationTime\":1763528213368},\"tenantId\":null,\"uid\":\"SG5LSw5VwnYvIxLtiOETD3eWVQ02\",\"_redirectEventId\":null}";

    @Test
    public void Ct41_01() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            System.out.println("🔄 Abrindo página...");
            driver.get("https://testes-codefolio.web.app/");

            // 🔐 LOGIN
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            Thread.sleep(1200);
            driver.navigate().refresh();
            Thread.sleep(2000);

            // --- 1. CLICAR NO BOTÃO "Comentários" ---
            WebElement botaoComentarios = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Comentários']/parent::*")
                    )
            );
            js.executeScript("arguments[0].click();", botaoComentarios);
            System.out.println("💬 Botão Comentários clicado!");

            // --- 2. AGUARDAR FORM APARECER ---
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("form.comentarios-form")
            ));
            System.out.println("📄 Formulário de comentários visível!");

            // --- 3. LOCALIZAR INPUT ---
            WebElement campoComentario = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("input.comentarios-input")
                    )
            );
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", campoComentario);
            Thread.sleep(200);

            campoComentario.click();
            campoComentario.sendKeys("Teste automatizado de comentário");
            System.out.println("✏️ Comentário digitado!");

            // --- 4. ESPERAR BOTÃO HABILITAR ---
            WebElement botaoEnviar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("form.comentarios-form button.comentarios-button")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", botaoEnviar);
            Thread.sleep(300);

            js.executeScript("arguments[0].click();", botaoEnviar);
            System.out.println("📤 Botão ENVIAR clicado!");


            js.executeScript("arguments[0].scrollIntoView({block:'center'});", botaoEnviar);
            Thread.sleep(200);

            js.executeScript("arguments[0].click();", botaoEnviar);
            System.out.println("📤 Botão ENVIAR clicado!");

            Thread.sleep(1000);

            // --- 5. CLICAR NO BOTÃO VER ---
            WebElement botaoVer = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'comentarios-toggleButton')]")
            ));
            js.executeScript("arguments[0].click();", botaoVer);
            System.out.println("📂 Botão VER clicado!");

            Thread.sleep(1200);

            // --- 6. VERIFICAR SE O COMENTÁRIO ESTÁ NA LISTA ---
            List<WebElement> comentarios = driver.findElements(
                    By.xpath("//*[contains(text(),'Teste automatizado de comentário')]")
            );

            if (comentarios.size() > 0) {
                System.out.println("💚 Comentário encontrado na lista!");
            } else {
                System.out.println("❌ Comentário NÃO encontrado — erro do sistema!");
            }

            assertTrue(comentarios.size() > 0,
                    "Comentário não apareceu na lista (falha do backend).");

        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO FLUXO DO CT41_01: " + e.getMessage());
        } finally {
            pause(5000);
            driver.quit();
        }
    }

    @Test
    public void Ct41_02() {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            System.out.println("🔄 Abrindo página...");
            driver.get("https://testes-codefolio.web.app/");

            // 🔐 LOGIN
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            Thread.sleep(1200);
            driver.navigate().refresh();
            Thread.sleep(2000);

            // --- 1. CLICAR NO BOTÃO "Comentários" ---
            WebElement botaoComentarios = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Comentários']/parent::*")
                    )
            );

            js.executeScript("arguments[0].click();", botaoComentarios);
            System.out.println("💬 Botão Comentários clicado!");


            // --- 2. AGUARDAR FORM APARECER ---
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("form.comentarios-form")
            ));
            System.out.println("📄 Formulário de comentários visível!");


            // --- 3. LOCALIZAR INPUT ---
            WebElement campoComentario = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("input.comentarios-input")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", campoComentario);
            Thread.sleep(300);

            // Verificar se o campo está vazio
            assertEquals("", campoComentario.getAttribute("value"),
                    "O campo de comentário deveria iniciar vazio.");
            System.out.println("✔ Campo inicia vazio!");


            // --- 4. VERIFICAR BOTÃO ENVIAR DESABILITADO ---
            WebElement botaoEnviar = driver.findElement(
                    By.cssSelector("form.comentarios-form button.comentarios-button")
            );

            boolean desabilitadoInicial = !botaoEnviar.isEnabled();
            assertTrue(desabilitadoInicial, "O botão deveria estar desabilitado com campo vazio.");

            System.out.println("🛑 Botão ENVIAR inicialmente desabilitado (OK).");


            // --- 5. DIGITAR UM CARACTERE ---
            campoComentario.sendKeys("A");
            Thread.sleep(300);

            // Verificar se botão agora está habilitado
            boolean habilitadoDepois = botaoEnviar.isEnabled();
            assertTrue(habilitadoDepois,
                    "O botão deveria habilitar após digitar um caractere.");

            System.out.println("🟢 Botão ENVIAR habilitado após digitar — OK!");


        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO CT41_02: " + e.getMessage());

        } finally {
            pause(4000);
            driver.quit();
        }
    }
}