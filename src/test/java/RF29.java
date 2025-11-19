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

public class RF29 extends MetodosTeste{

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";
    private static final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1761931337511\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1762561682156\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocI6BhRqR9kQ2w6sIU_wWXSqI2PDtQ1BRzToca1OLhky1rjxQA=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"100155757080180289674\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"refreshToken\":\"AMf-vBzbe3Ub88Nrg_9VB3EP68vWGwpIA9Rr5fc3v2ak0bTY0hORkqVyvm22CJfcImTiRdu2q0VLohB0HdYLaw_6BX0G2fp7yFgJlhICFi-oaRXXmUMcDESncCXmAoxUKDXhgjJUWk37BFiAye8C8JBwcfaUJeFBTlVklhHKssPQJ1AYsiyEVI9J2M03fouOeuOpYtjv6XZOGbNC39PmoHf7J-F2KzmMh4nE-Z3uokaAA7YFaa5W_LQZA-SehHrcC8oJVCwI5ZQwKeQM2eSj5fDZTJSfoAH9PYQgJUlRGUUPI5-nbwDkG_8_BtDN7AZKpXgdvR_6NMJFuY8BafETuWKwYZpZkdy88VKYlgWyyGg79RBfx7NxNLgJUWOi6lYBUnlkCD80YwtslN2ZgP-tqQ54BdsdwnMmS0JPA-yUM4Mmcjb7-kHu9ZkNHMcDsEnEpBAL5Mn2BxPjOelInR5ZFIg_VhJpmbqr-g\",\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjU0NTEzMjA5OWFkNmJmNjEzODJiNmI0Y2RlOWEyZGZlZDhjYjMwZjAiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRmFkZSBIYXNzYW4gSHVzZWluIEthbmFhbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJNkJoUnFSOWtRMnc2c0lVX3dXWFNxSTJQRHRRMUJSelRvY2ExT0xoa3kxcmp4UUE9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcmVhY3QtbmEtcHJhdGljYSIsImF1ZCI6InJlYWN0LW5hLXByYXRpY2EiLCJhdXRoX3RpbWUiOjE3NjE5MzEzMzcsInVzZXJfaWQiOiJObFQxcjRDc20xUjlPMDRzZlJyNDlFQk1EVVIyIiwic3ViIjoiTmxUMXI0Q3NtMVI5TzA0c2ZScjQ5RUJNRFVSMiIsImlhdCI6MTc2MjY0ODQ3NiwiZXhwIjoxNzYyNjUyMDc2LCJlbWFpbCI6ImZhZGVrYW5hYW4uYWx1bm9AdW5pcGFtcGEuZWR1LmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDAxNTU3NTcwODAxODAyODk2NzQiXSwiZW1haWwiOlsiZmFkZWthbmFhbi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.mjn9r_AC_VlUt96za71NZQmgzLXYU6ioUedueeKptnRM99h0OTTqQSMGO59YFxfJKwLvdhtjVCVVFk9SRhO_dltdWZVzfzXbuOw98wZr2Y3KbkXBHQDKs8Eyw94xcfiTbBYbrbOzQIu2hJQEl1PE851NAZ97wNrn_R4_UyJLi7WGyQXYbc-bYFT7hUe8FnLY7z1WxD7kv0dgX05jnF-OOziNFR4JgOIKeDm7RtxSFBu7LEMVzmMsQSZHf1Eg05U2KlN3IthantErscx3NZ6PMRr8gG-El__58svbqC51ok5MBhgoComJ1FTEUf5zV1-OKOdGPYgg58YmcpuZHb4GnQ\",\"expirationTime\":1762652075392},\"tenantId\":null,\"uid\":\"NlT1r4Csm1R9O04sfRr49EBMDUR2\",\"_redirectEventId\":null}";

    @Test
    public void Ct29_01() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String timestamp = Long.toString(System.currentTimeMillis());
        String nomeDoCurso = "Curso Teste RF29 " + timestamp;
        String descDoCurso = "Descrição gerada por teste automatizado.";
        String nomeDoVideo = "Vídeo Padrão " + timestamp;
        String urlDoVideo = "https://www.youtube.com/watch?v=Fw9YW5_MZRs";
        String descVideo = "Descrição de teste para o vídeo.";

        try {
            driver.get("https://testes.codefolio.com.br");

            // 🔐 LOGIN
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // --- ETAPA 1: CRIAR CURSO ---
            driver.get("https://testes.codefolio.com.br/manage-courses");
            criarCurso(driver, wait, js, nomeDoCurso, descDoCurso);
            System.out.println("✅ Curso criado: " + nomeDoCurso);

            // --- ETAPA 2: ADICIONAR VÍDEO ---
            adicionarVideo(driver, wait, js, nomeDoVideo, urlDoVideo, descVideo);
            System.out.println("✅ Vídeo adicionado com sucesso.");

            // --- ETAPA 3: CRIAR QUIZ ---
            criarQuiz(driver, wait, js);
            System.out.println("✅ Quiz criado com sucesso.");

            // --- ETAPA 4: ABRIR LISTA DE CURSOS ---
            driver.get("https://testes.codefolio.com.br/listcurso");
            pause(3000);
            System.out.println("📚 Acessou a aba Cursos.");

            // --- ETAPA 5: LOCALIZAR O CURSO ---
            WebElement cursoCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'MuiCard-root')]//*[self::h6 or self::h3 or self::p][contains(normalize-space(.), '" + nomeDoCurso + "')]")
            ));
            assertNotNull(cursoCard, "❌ Card do curso não encontrado!");
            System.out.println("🔎 Encontrou o curso criado: " + nomeDoCurso);

            // --- ETAPA 6: CLICAR EM 'COMEÇAR' ---
            WebElement botaoComecar = cursoCard.findElement(
                    By.xpath("./ancestor::div[contains(@class,'MuiCard-root')]//button[contains(normalize-space(.), 'Começar')]")
            );
            js.executeScript("arguments[0].click();", botaoComecar);
            pause(4000);
            System.out.println("▶️ Clicou em 'Começar'.");

            // --- ETAPA 7: CLICAR NO QUIZ ---
            WebElement botaoQuizGigi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@title, 'Abrir Quiz Gigi')]")
            ));
            js.executeScript("arguments[0].click();", botaoQuizGigi);
            pause(3000);
            System.out.println("📘 Abriu o quiz Gigi.");

            // --- ETAPA 8: VALIDAR QUE A PERGUNTA 1 ESTÁ VISÍVEL ---
            WebElement pergunta1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(normalize-space(.), 'Pergunta')]")
            ));
            assertTrue(pergunta1.isDisplayed(), "❌ Pergunta 1 não está visível!");
            System.out.println("✅ Pergunta 1 visível.");

            // --- AVANÇAR PARA PRÓXIMA QUESTÃO ---
            try {
                // Localiza o botão de avançar baseado na classe do ícone interno (.css-1q9v4tb)
                WebElement setaAvancar = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button:has(> svg.css-1q9v4tb)")
                ));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", setaAvancar);
                pause(500);

                wait.until(ExpectedConditions.elementToBeClickable(setaAvancar));

                try {
                    setaAvancar.click();
                    System.out.println("➡️ Clique normal na seta (via seletor .css-1q9v4tb).");
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", setaAvancar);
                    System.out.println("⚙️ Clique forçado via JavaScript na seta (overlay possível).");
                }

                pause(2000);

                WebElement pergunta2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(normalize-space(.), 'Pergunta2')]")
                ));
                assertTrue(pergunta2.isDisplayed(), "❌ Pergunta 2 não apareceu após clicar na seta!");
                System.out.println("✅ Pergunta 2 exibida corretamente após clicar na seta ➡️");

            } catch (TimeoutException te) {
                System.out.println("❌ Timeout ao localizar a seta para avançar (ícone .css-1q9v4tb).");
                fail("Seta de avançar não encontrada.");
            } catch (Exception e) {
                e.printStackTrace();
                fail("❌ Erro inesperado ao tentar clicar na seta para avançar: " + e.getMessage());
            }

            // --- SALVAR O NOME DO CURSO ---
            Files.writeString(Paths.get("ultimoCursoCriado.txt"), nomeDoCurso);
            System.out.println("📝 Nome do curso salvo: " + nomeDoCurso);

        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO FLUXO DO CT29_01: " + e.getMessage());
        } finally {
            pause(5000);
            driver.quit();
        }
    }

    @Test
    public void Ct29_02() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            String nomeDoCurso = "Curso Teste RF29 1763019755786";
            System.out.println("📘 Curso usado: " + nomeDoCurso);

            // --- LOGIN ---
            driver.get("https://testes.codefolio.com.br");
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // --- ABRIR CURSOS ---
            driver.get("https://testes.codefolio.com.br/listcurso");
            pause(3000);

            // --- ENCONTRAR O CURSO E CLICAR EM COMEÇAR/CONTINUAR ---
            List<WebElement> cursos = driver.findElements(By.cssSelector(".MuiCard-root"));
            boolean cursoEncontrado = false;
            for (WebElement card : cursos) {
                if (card.getText().toLowerCase().contains(nomeDoCurso.toLowerCase())) {
                    try {
                        WebElement botaoComecar = card.findElement(By.xpath(".//button[contains(normalize-space(.), 'Começar')]"));
                        js.executeScript("arguments[0].click();", botaoComecar);
                        cursoEncontrado = true;
                        System.out.println("▶️ Clicou em 'Começar'.");
                        break;
                    } catch (Exception ignored) {}
                }
            }

            if (!cursoEncontrado) {
                WebElement abaAndamento = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(normalize-space(.), 'Em Andamento')]")
                ));
                js.executeScript("arguments[0].click();", abaAndamento);
                pause(3000);

                cursos = driver.findElements(By.cssSelector(".MuiCard-root"));
                for (WebElement card : cursos) {
                    if (card.getText().toLowerCase().contains(nomeDoCurso.toLowerCase())) {
                        WebElement botaoContinuar = card.findElement(By.xpath(".//button[contains(normalize-space(.), 'Continuar')]"));
                        js.executeScript("arguments[0].click();", botaoContinuar);
                        cursoEncontrado = true;
                        System.out.println("▶️ Clicou em 'Continuar'.");
                        break;
                    }
                }
            }

            assertTrue(cursoEncontrado, "❌ Curso não encontrado em nenhuma aba!");
            pause(4000);

            // --- CLICAR NO QUIZ ---
            WebElement botaoQuizGigi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@title, 'Abrir Quiz Gigi')]")
            ));
            js.executeScript("arguments[0].click();", botaoQuizGigi);
            pause(3000);

            // --- VALIDAR PERGUNTA 1 ---
            WebElement pergunta1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(normalize-space(.), 'Pergunta')]")
            ));
            assertTrue(pergunta1.isDisplayed(), "❌ Pergunta 1 não está visível!");
            System.out.println("✅ Pergunta 1 visível.");

            // --- AVANÇAR PARA PERGUNTA 2 ---
            try {
                WebElement setaAvancar = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button:has(> svg.css-1q9v4tb)")
                ));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", setaAvancar);
                pause(500);

                wait.until(ExpectedConditions.elementToBeClickable(setaAvancar));

                try {
                    setaAvancar.click();
                    System.out.println("➡️ Clique normal na seta (via seletor .css-1q9v4tb).");
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", setaAvancar);
                    System.out.println("⚙️ Clique forçado via JavaScript na seta (overlay possível).");
                }

                pause(2000);

                WebElement pergunta2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(normalize-space(.), 'Pergunta2')]")
                ));
                assertTrue(pergunta2.isDisplayed(), "❌ Pergunta 2 não apareceu após clicar na seta!");
                System.out.println("✅ Pergunta 2 exibida corretamente após clicar na seta ➡️");

            } catch (TimeoutException te) {
                System.out.println("❌ Timeout ao localizar a seta para avançar (ícone .css-1q9v4tb).");
                fail("Seta de avançar não encontrada.");
            }

            // --- VOLTAR PARA PERGUNTA 1 ---
            pause(1500); // tempo para o botão de voltar ficar habilitado
            try {
                WebElement setaVoltar = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button:has(> svg.css-9xkar3)")
                ));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", setaVoltar);
                pause(500);

                wait.until(ExpectedConditions.elementToBeClickable(setaVoltar));

                try {
                    setaVoltar.click();
                    System.out.println("⬅️ Clique normal na seta de voltar (via seletor .css-9xkar3).");
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", setaVoltar);
                    System.out.println("⚙️ Clique forçado via JavaScript na seta de voltar (overlay possível).");
                }

                pause(1500);

                // ✅ Nova verificação robusta para Pergunta 1
                WebElement pergunta1Novamente = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(normalize-space(.), 'Pergunta')]")
                ));

                String textoPergunta = pergunta1Novamente.getText().trim();
                assertTrue(textoPergunta.equalsIgnoreCase("Pergunta") || textoPergunta.equalsIgnoreCase("Pergunta 1"),
                        "❌ O texto exibido não corresponde à Pergunta 1! Texto encontrado: " + textoPergunta);

                System.out.println("💚 Voltou para a Pergunta 1 corretamente.");

            } catch (TimeoutException te) {
                System.out.println("❌ Timeout ao localizar a seta para voltar (.css-9xkar3).");
                fail("Seta de voltar não encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO FLUXO DO CT29_02: " + e.getMessage());
        } finally {
            pause(5000);
            driver.quit();
        }
    }
}