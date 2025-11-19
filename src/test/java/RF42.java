import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
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

public class RF42 extends MetodosTeste{

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";

    private static final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763434890844\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1763434890845\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocI6BhRqR9kQ2w6sIU_wWXSqI2PDtQ1BRzToca1OLhky1rjxQA=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"100155757080180289674\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"refreshToken\":\"AMf-vBzVLKL5CyOUHtVyVqZKixvXJ6xDhca65hBRYDBZ4jbdALrMe2V0MlagvLC3Jtw06HutBu9zYzXvByJnJYAcNo_3F33OUTvk-QsG9YMMkY1kSrDOFLxghFsyjqT4lSFnZPjJQM9W4DkTK80qJDl1cbLgZ1RdT0SYobLMOTlqnCjuc89C52JNpoOnf7NA31XB48U0AjotnC0m7tchRHcUOTRZHYKuGV7_1UuUnArJhXNnUREj06FOWUaZPDyJP35elnQ0fPupe61LaYxQUXLSILdVuW8Qj5XQhADoL1EayWmS3jRdqT35VzAMv901KWtFM8KXnSiaGafvhj6FbtBNuCJJX-J5Bk1-4OFzQjuTQH9CzKLhgsT--hFHJ0sVz9iDA8D2XAnI5oin2A3tutEDqGD4brNwVzGD4tqMTiNjrPZ8rfyRVrQIivUcEiNEKCkWE9gO1PIqSMsxuM98uguepyO8GJvLEQ\",\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRmFkZSBIYXNzYW4gSHVzZWluIEthbmFhbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJNkJoUnFSOWtRMnc2c0lVX3dXWFNxSTJQRHRRMUJSelRvY2ExT0xoa3kxcmp4UUE9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM0MzQ5MDMsInVzZXJfaWQiOiJTRzVMU3c1VnduWXZJeEx0aU9FVEQzZVdWUTAyIiwic3ViIjoiU0c1TFN3NVZ3bll2SXhMdGlPRVREM2VXVlEwMiIsImlhdCI6MTc2MzUyNDYxNCwiZXhwIjoxNzYzNTI4MjE0LCJlbWFpbCI6ImZhZGVrYW5hYW4uYWx1bm9AdW5pcGFtcGEuZWR1LmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDAxNTU3NTcwODAxODAyODk2NzQiXSwiZW1haWwiOlsiZmFkZWthbmFhbi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.eyehgmPYuTGonAQv7yrlgwqKr929SP5XvOF8y_fgHHiv1nXnka3cQcFZs5Zo06U-DX8Sq32gHFGvXzoVIB7Zp7LXufyrTFZjMRCtRc4GGVED0LDfROrVSGnPzaV8t4L6gvxlbi69eedhJDZX4KtlSAOdjx5o-NEVqK_Jlb-LMJM2MuWvhJ53eNCVSCkCc1HA_akNTULyFEKFgugnTpNU0QIeIgDPtwwfa-hGtVTBj-fhUOsiiq2Ok4GkkcdLZWe4VQLr5W2D_tqKOo-6GbcNwr_K_-VrmrZ3cZqwuUAsWRYFJKcP8YCzcpfsYE8fQYMLIp-yzcVfKRIGZ9unrcheHw\",\"expirationTime\":1763528213368},\"tenantId\":null,\"uid\":\"SG5LSw5VwnYvIxLtiOETD3eWVQ02\",\"_redirectEventId\":null}";

    @Test
    public void Ct42_01() {

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

            // --- 1. LOCALIZAR O BOTÃO COMPARTILHAR ---
            WebElement botaoCompartilhar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Compartilhar']/parent::*")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoCompartilhar);
            Thread.sleep(300);

            js.executeScript("arguments[0].click();", botaoCompartilhar);
            System.out.println("📤 Botão COMPARTILHAR clicado!");


            // --- 2. VALIDAR TOAST ---
            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(text(),'Link copiado para a área de transferência')]")
                    )
            );

            assertTrue(toast.isDisplayed(), "❌ O toast não apareceu!");
            System.out.println("✅ Toast detectado com sucesso!");

            Thread.sleep(800); // pequeno delay enquanto o sistema copia o link


            // --- 3. VALIDAR ÁREA DE TRANSFERÊNCIA ---
            String clipboardText = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .getData(DataFlavor.stringFlavor);

            System.out.println("📋 Conteúdo da área de transferência: " + clipboardText);

            assertNotNull(clipboardText, "❌ A área de transferência está vazia!");
            assertTrue(
                    clipboardText.contains("https://"),
                    "❌ O texto copiado NÃO parece ser um link válido!"
            );

            System.out.println("💚 Link copiado para a área de transferência VALIDADO!");


        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO CT42_01: " + e.getMessage());

        } finally {
            pause(5000);
            driver.quit();
        }
    }
}