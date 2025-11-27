import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RF52 extends MetodosTeste {

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";

    private static final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763434890844\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1763434890845\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocI6BhRqR9kQ2w6sIU_wWXSqI2PDtQ1BRzToca1OLhky1rjxQA=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"100155757080180289674\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"refreshToken\":\"AMf-vBzVLKL5CyOUHtVyVqZKixvXJ6xDhca65hBRYDBZ4jbdALrMe2V0MlagvLC3Jtw06HutBu9zYzXvByJnJYAcNo_3F33OUTvk-QsG9YMMkY1kSrDOFLxghFsyjqT4lSFnZPjJQM9W4DkTK80qJDl1cbLgZ1RdT0SYobLMOTlqnCjuc89C52JNpoOnf7NA31XB48U0AjotnC0m7tchRHcUOTRZHYKuGV7_1UuUnArJhXNnUREj06FOWUaZPDyJP35elnQ0fPupe61LaYxQUXLSILdVuW8Qj5XQhADoL1EayWmS3jRdqT35VzAMv901KWtFM8KXnSiaGafvhj6FbtBNuCJJX-J5Bk1-4OFzQjuTQH9CzKLhgsT--hFHJ0sVz9iDA8D2XAnI5oin2A3tutEDqGD4brNwVzGD4tqMTiNjrPZ8rfyRVrQIivUcEiNEKCkWE9gO1PIqSMsxuM98uguepyO8GJvLEQ\",\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRmFkZSBIYXNzYW4gSHVzZWluIEthbmFhbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJNkJoUnFSOWtRMnc2c0lVX3dXWFNxSTJQRHRRMUJSelRvY2ExT0xoa3kxcmp4UUE9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM0MzQ5MDMsInVzZXJfaWQiOiJTRzVMU3c1VnduWXZJeEx0aU9FVEQzZVdWUTAyIiwic3ViIjoiU0c1TFN3NVZ3bll2SXhMdGlPRVREM2VXVlEwMiIsImlhdCI6MTc2MzUyNDYxNCwiZXhwIjoxNzYzNTI4MjE0LCJlbWFpbCI6ImZhZGVrYW5hYW4uYWx1bm9AdW5pcGFtcGEuZWR1LmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDAxNTU3NTcwODAxODAyODk2NzQiXSwiZW1haWwiOlsiZmFkZWthbmFhbi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.eyehgmPYuTGonAQv7yrlgwqKr929SP5XvOF8y_fgHHiv1nXnka3cQcFZs5Zo06U-DX8Sq32gHFGvXzoVIB7Zp7LXufyrTFZjMRCtRc4GGVED0LDfROrVSGnPzaV8t4L6gvxlbi69eedhJDZX4KtlSAOdjx5o-NEVqK_Jlb-LMJM2MuWvhJ53eNCVSCkCc1HA_akNTULyFEKFgugnTpNU0QIeIgDPtwwfa-hGtVTBj-fhUOsiiq2Ok4GkkcdLZWe4VQLr5W2D_tqKOo-6GbcNwr_K_-VrmrZ3cZqwuUAsWRYFJKcP8YCzcpfsYE8fQYMLIp-yzcVfKRIGZ9unrcheHw\",\"expirationTime\":1763528213368},\"tenantId\":null,\"uid\":\"SG5LSw5VwnYvIxLtiOETD3eWVQ02\",\"_redirectEventId\":null}";

    // ----------------------------------------------------------
    // CT-52.01 — Quiz deve estar BLOQUEADO antes de assistir o vídeo
    // ----------------------------------------------------------
    @Test
    public void Ct52_01_QuizBloqueado() {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String timestamp = Long.toString(System.currentTimeMillis());
        String nomeCurso = "RF52 Curso Auto " + timestamp;
        String descCurso = "Curso criado automaticamente.";
        String nomeVideo = "Vídeo RF52 " + timestamp;
        String descVideo = "Vídeo para teste RF52.";
        String urlVideo = "https://youtu.be/qQ-_Nm7HgLo";

        try {
            // LOGIN
            driver.get("https://testes-codefolio.web.app/");
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // CRIAR CURSO + VÍDEO + QUIZ
            driver.get("https://testes-codefolio.web.app/manage-courses");

            criarCurso(driver, wait, js, nomeCurso, descCurso);
            adicionarVideo(driver, wait, js, nomeVideo, urlVideo, descVideo);
            criarQuiz(driver, wait, js);

            // Defender o valor para CT-52.02
            Files.writeString(Paths.get("ultimoCursoRF52.txt"), nomeCurso);

            // IR PARA LISTA DE CURSOS
            driver.get("https://testes-codefolio.web.app/listcurso");
            pause(4000);

            // ACHAR O CURSO
            WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'MuiCard-root')]//*[contains(text(),'" + nomeCurso + "')]")
            ));

            assertNotNull(card, "Curso não encontrado na lista!");

            // CLICAR EM COMEÇAR
            WebElement btnComecar = card.findElement(By.xpath("./ancestor::div[contains(@class,'MuiCard-root')]//button"));
            js.executeScript("arguments[0].click();", btnComecar);

            pause(4000);

            // Aguarda o botão "Quiz Bloqueado" aparecer
            WebElement quizBloqueado = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//button[contains(., 'Quiz Bloqueado')]")
                    )
            );

            // Verifica se o quiz está bloqueado mesmo
            assertTrue(quizBloqueado.isDisplayed());
            System.out.println("✔ Botão 'Quiz Bloqueado' encontrado e visível!");
            List<WebElement> liberado = driver.findElements(
                    By.xpath("//button[contains(., 'Fazer Quiz')]")
            );
            assertTrue(liberado.isEmpty(), "ERRO: Quiz não deveria estar liberado!");
            System.out.println("✅ CT52.01 — O botão de Quiz está BLOQUEADO corretamente.");

        } catch (Exception e) {
            e.printStackTrace();
            fail("ERRO no CT52.01: " + e.getMessage());
        } finally {
            pause(4000);
            driver.quit();
        }
    }


    // ----------------------------------------------------------
    // CT-52.02 — Assistir vídeo → Quiz deve DESBLOQUEAR
    // ----------------------------------------------------------
    @Test
    public void Ct52_02_QuizDesbloqueado() {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // LER O ÚLTIMO CURSO CRIADO NO CT52_01
            String nomeCurso = Files.readString(Paths.get("ultimoCursoRF52.txt")).trim();
            System.out.println("Curso carregado: " + nomeCurso);

            // LOGIN
            driver.get("https://testes-codefolio.web.app/");
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // IR PARA LISTA DE CURSOS
            driver.get("https://testes-codefolio.web.app/listcurso");
            pause(4000);

            WebElement abaEmAndamento = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(., 'Em Andamento')]")
                    )
            );

            abaEmAndamento.click();
            System.out.println("✔ Clicou na aba Em Andamento");


            // ENCONTRAR O CURSO
            WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'MuiCard-root')]//*[contains(text(),'" + nomeCurso + "')]")
            ));

            assertNotNull(card);

            // COMEÇAR / CONTINUAR
            WebElement btn = card.findElement(By.xpath("./ancestor::div[contains(@class,'MuiCard-root')]//button"));
            js.executeScript("arguments[0].click();", btn);
            pause(4000);

            try {
                // 1. Espera o iframe do YouTube aparecer
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("iframe[src*='youtube.com'], iframe[src*='youtube-nocookie.com']")
                ));

                // 2. Entra dentro do iframe
                driver.switchTo().frame(iframe);

                // 3. Espera o botão grande de play aparecer
                WebElement botaoPlay = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".ytp-large-play-button")
                ));

                // 4. Clica no play via JavaScript (mais confiável)
                js.executeScript("arguments[0].click();", botaoPlay);

                // 5. Espera até o player sair do "paused-mode"
                wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(
                        By.id("movie_player"), "class", "paused-mode"
                )));

                System.out.println("🎉 VÍDEO ESTÁ TOCANDO!");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.switchTo().defaultContent();
            }

            pause(3000);

            WebElement indicador100 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(normalize-space(.), '100%')]")
            ));

            assertTrue(indicador100.isDisplayed());
            System.out.println("🎉 Vídeo concluído! Indicador encontrado: " + indicador100.getText());



            By locatorFazerQuiz = By.xpath("//button[contains(., 'Fazer Quiz')]");

            System.out.println("Aguardando o botão mudar para 'Fazer Quiz'...");

            WebElement botaoFinal = wait.until(
                    ExpectedConditions.elementToBeClickable(locatorFazerQuiz)
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoFinal);
            Thread.sleep(800); // Pausa para a rolagem terminar e a animação do botão assentar
            try {
                botaoFinal.click();
                System.out.println("✔ Clique normal no botão 'Fazer Quiz' funcionou.");
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoFinal);
                System.out.println("✔ Clique via JS no botão 'Fazer Quiz' executado.");
            }


            // VALIDAR QUESTÃO
            WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(text(),'Questão')]")
            ));

            assertTrue(titulo.isDisplayed(), "Tela do quiz não abriu!");
            System.out.println("🎉 CT52.02 — Tela do Quiz aberta com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            fail("ERRO no CT52.02: " + e.getMessage());
        } finally {
            pause(4000);
            driver.quit();
        }
    }
}
