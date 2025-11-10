import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor; // <-- Importante para a injeção
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.openqa.selenium.devtools.v140.debugger.Debugger.pause;

public class RF14 {

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";
    private static final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1761931337511\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1762561682156\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocI6BhRqR9kQ2w6sIU_wWXSqI2PDtQ1BRzToca1OLhky1rjxQA=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"100155757080180289674\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"refreshToken\":\"AMf-vBzbe3Ub88Nrg_9VB3EP68vWGwpIA9Rr5fc3v2ak0bTY0hORkqVyvm22CJfcImTiRdu2q0VLohB0HdYLaw_6BX0G2fp7yFgJlhICFi-oaRXXmUMcDESncCXmAoxUKDXhgjJUWk37BFiAye8C8JBwcfaUJeFBTlVklhHKssPQJ1AYsiyEVI9J2M03fouOeuOpYtjv6XZOGbNC39PmoHf7J-F2KzmMh4nE-Z3uokaAA7YFaa5W_LQZA-SehHrcC8oJVCwI5ZQwKeQM2eSj5fDZTJSfoAH9PYQgJUlRGUUPI5-nbwDkG_8_BtDN7AZKpXgdvR_6NMJFuY8BafETuWKwYZpZkdy88VKYlgWyyGg79RBfx7NxNLgJUWOi6lYBUnlkCD80YwtslN2ZgP-tqQ54BdsdwnMmS0JPA-yUM4Mmcjb7-kHu9ZkNHMcDsEnEpBAL5Mn2BxPjOelInR5ZFIg_VhJpmbqr-g\",\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjU0NTEzMjA5OWFkNmJmNjEzODJiNmI0Y2RlOWEyZGZlZDhjYjMwZjAiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRmFkZSBIYXNzYW4gSHVzZWluIEthbmFhbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJNkJoUnFSOWtRMnc2c0lVX3dXWFNxSTJQRHRRMUJSelRvY2ExT0xoa3kxcmp4UUE9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcmVhY3QtbmEtcHJhdGljYSIsImF1ZCI6InJlYWN0LW5hLXByYXRpY2EiLCJhdXRoX3RpbWUiOjE3NjE5MzEzMzcsInVzZXJfaWQiOiJObFQxcjRDc20xUjlPMDRzZlJyNDlFQk1EVVIyIiwic3ViIjoiTmxUMXI0Q3NtMVI5TzA0c2ZScjQ5RUJNRFVSMiIsImlhdCI6MTc2MjY0ODQ3NiwiZXhwIjoxNzYyNjUyMDc2LCJlbWFpbCI6ImZhZGVrYW5hYW4uYWx1bm9AdW5pcGFtcGEuZWR1LmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDAxNTU3NTcwODAxODAyODk2NzQiXSwiZW1haWwiOlsiZmFkZWthbmFhbi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.mjn9r_AC_VlUt96za71NZQmgzLXYU6ioUedueeKptnRM99h0OTTqQSMGO59YFxfJKwLvdhtjVCVVFk9SRhO_dltdWZVzfzXbuOw98wZr2Y3KbkXBHQDKs8Eyw94xcfiTbBYbrbOzQIu2hJQEl1PE851NAZ97wNrn_R4_UyJLi7WGyQXYbc-bYFT7hUe8FnLY7z1WxD7kv0dgX05jnF-OOziNFR4JgOIKeDm7RtxSFBu7LEMVzmMsQSZHf1Eg05U2KlN3IthantErscx3NZ6PMRr8gG-El__58svbqC51ok5MBhgoComJ1FTEUf5zV1-OKOdGPYgg58YmcpuZHb4GnQ\",\"expirationTime\":1762652075392},\"tenantId\":null,\"uid\":\"NlT1r4Csm1R9O04sfRr49EBMDUR2\",\"_redirectEventId\":null}";

    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void criarCurso(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String nomeDoCurso, String descDoCurso) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Criar Novo Curso')]")
            )).click();
            System.out.println("Clicou em Criar Novo Curso.");
            pause(2500);

            WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(.), 'Título do Curso')]/following-sibling::div//input")
            ));
            titulo.sendKeys(nomeDoCurso);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", titulo);
            pause(1000);

            WebElement descricao = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(.), 'Descrição do Curso')]/following-sibling::div//textarea")
            ));
            descricao.sendKeys(descDoCurso);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", descricao);
            pause(1200);

            WebElement salvarBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(translate(normalize-space(.), 'salvarcurso', 'SALVARCURSO'), 'SALVAR CURSO')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", salvarBtn);
            pause(1000);
            try {
                salvarBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", salvarBtn);
            }
            System.out.println("Clicou em SALVAR CURSO.");
            pause(2500);

            // Modal OK
            boolean clicouOK = false;
            try {
                WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                ));

                // rola até o botão e força foco
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", okBtn);
                js.executeScript("arguments[0].focus();", okBtn);
                Thread.sleep(600);

                // força o clique via JS
                js.executeScript("arguments[0].click();", okBtn);
                System.out.println("✅ Clicou em 'OK!' com sucesso (via JS).");
                clicouOK = true;

            } catch (Exception e) {
                System.out.println("⚠️ Erro ao tentar clicar no botão 'OK!': " + e.getMessage());
            }

            if (!clicouOK) throw new RuntimeException("❌ Falha ao confirmar criação do curso.");
            pause(2000);

        } catch (Exception e) {
            System.out.println("❌ Erro ao criar curso:");
            e.printStackTrace();
            throw e; // repropaga erro para falhar o teste
        }
    }

    private void adicionarVideo(WebDriver driver, WebDriverWait wait, JavascriptExecutor js,
                                String nomeVideo, String urlVideo, String descVideo) {
        try {
            System.out.println("Iniciando 'adicionarVideo'...");

            // Aguarda campo de título
            WebElement labelTituloVideo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(.), 'Título do Vídeo')]")
            ));
            String idTituloVideo = labelTituloVideo.getAttribute("for");
            WebElement inputTituloVideo = wait.until(ExpectedConditions.elementToBeClickable(By.id(idTituloVideo)));
            inputTituloVideo.sendKeys(nomeVideo);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputTituloVideo);
            pause(500);
            System.out.println("Preencheu Título do Vídeo.");

            // Campo URL
            WebElement labelUrlVideo = driver.findElement(By.xpath("//label[contains(normalize-space(.), 'URL do Vídeo')]"));
            String idUrlVideo = labelUrlVideo.getAttribute("for");
            WebElement inputUrlVideo = driver.findElement(By.id(idUrlVideo));
            inputUrlVideo.sendKeys(urlVideo);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputUrlVideo);
            pause(500);
            System.out.println("Preencheu URL do Vídeo.");

            // Campo Descrição
            WebElement labelDescVideo = driver.findElement(By.xpath("//label[contains(normalize-space(.), 'Descrição do Vídeo')]"));
            String idDescVideo = labelDescVideo.getAttribute("for");
            WebElement inputDescVideo = driver.findElement(By.id(idDescVideo));
            inputDescVideo.sendKeys(descVideo);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputDescVideo);
            pause(500);
            System.out.println("Preencheu Descrição do Vídeo.");

            // Botão "Adicionar Vídeo"
            WebElement adicionarVideoBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Adicionar Vídeo')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", adicionarVideoBtn);
            pause(800);
            try {
                adicionarVideoBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", adicionarVideoBtn);
            }
            System.out.println("🎬 Clicou em 'Adicionar Vídeo'.");
            pause(3000);

            // Espera modal "Vídeo adicionado com sucesso!"
            boolean clicouOK = false;
            for (int i = 0; i < 3 && !clicouOK; i++) {
                try {
                    WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                    ));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", okBtn);
                    js.executeScript("arguments[0].focus();", okBtn);
                    pause(500);
                    js.executeScript("arguments[0].click();", okBtn);
                    System.out.println("✅ Clicou em 'OK!' (modal de vídeo).");
                    clicouOK = true;
                } catch (Exception e) {
                    System.out.println("⚠️ Tentativa " + (i + 1) + " de clicar em OK falhou. Repetindo...");
                    pause(1000);
                }
            }

            if (!clicouOK) throw new RuntimeException("❌ Falha ao confirmar adição do vídeo.");

            pause(1500);

            // Botão "Salvar Curso"
            WebElement salvarCursoBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Salvar Curso')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", salvarCursoBtn);
            pause(800);
            try {
                salvarCursoBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", salvarCursoBtn);
            }
            System.out.println("💾 Clicou em 'Salvar Curso' após adicionar vídeo.");
            pause(3000);

            // Confirmação final (modal de curso salvo)
            boolean clicouOkFinal = false;
            for (int i = 0; i < 3 && !clicouOkFinal; i++) {
                try {
                    WebElement okBtnFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                    ));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", okBtnFinal);
                    js.executeScript("arguments[0].focus();", okBtnFinal);
                    pause(500);
                    js.executeScript("arguments[0].click();", okBtnFinal);
                    System.out.println("✅ Clicou em 'OK!' (modal de salvar curso).");
                    clicouOkFinal = true;
                } catch (Exception e) {
                    System.out.println("⚠️ Tentativa " + (i + 1) + " de clicar no OK final falhou. Repetindo...");
                    pause(1000);
                }
            }

            if (!clicouOkFinal) throw new RuntimeException("❌ Falha ao confirmar salvamento final do curso.");

            System.out.println("✅ Vídeo adicionado e curso salvo com sucesso!");
            pause(1500);

        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar vídeo:");
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void testFluxoCompletoCursoQuiz() {
        WebDriver driver = new ChromeDriver();
        // Aumentei o wait principal para 60s, pois o fluxo está longo
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // --- Nossos dados de teste ---
        String timestamp = Long.toString(System.currentTimeMillis());
        String nomeDoCurso = "Curso Teste RF14 " + timestamp;
        String descDoCurso = "Descrição gerada por teste automatizado.";
        String nomeDoVideo = "Vídeo Padrão " + timestamp;
        String urlDoVideo = "https://www.youtube.com/watch?v=Fw9YW5_MZRs";
        String descVideo = "Descrição de teste para o vídeo.";
        // -----------------------------

        try {
            driver.get("https://testes.codefolio.com.br");

            // 🔐 Login via localStorage
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // --- ETAPA 1: CRIAR CURSO ---
            driver.get("https://testes.codefolio.com.br/manage-courses");
            criarCurso(driver, wait, js, nomeDoCurso, descDoCurso);
            System.out.println("--- Método criarCurso() concluído. ---");

            // --- ETAPA 2: ADICIONAR VÍDEO ---
            // (Já estamos na página de gerenciamento do curso)
            adicionarVideo(driver, wait, js, "TesteVídeo", "https://youtu.be/Fw9YW5_MZRs", "TesteVídeo");
            System.out.println("--- Método adicionarVideo() concluído. ---");

            // TODO: Próximo passo (Adicionar Método de Criar Quiz)

            System.out.println("FLUXO DE TESTE CONCLUÍDO COM SUCESSO!");

        } catch (Exception e) {
            System.out.println("❌ ERRO NO FLUXO PRINCIPAL ❌");
            e.printStackTrace();
        } finally {
            pause(7000); // Pausa final para ver o resultado
            driver.quit();
        }
    }
}