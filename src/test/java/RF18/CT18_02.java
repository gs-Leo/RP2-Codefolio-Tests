package RF18;

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

public class CT18_02 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;
    // Nome do aluno
    private final String ALUNO_ALVO = "Julia";
    // Nome do curso alvo
    private final String CURSO_ALVO = "Teste RF6";

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763485802422\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1764000250329\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocIPSVJF5ZjtLwj6MndKM7i1yp2tDbMn13DkkmLcptbfW7FRQ74=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"107226265685191574291\",\"displayName\":\"Marcus Vinicius Morini Querol Junior\",\"email\":\"marcusquerol.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM5OTk2OTgsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2NDEzMzAwNiwiZXhwIjoxNzY0MTM2NjA2LCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.1mAMv2Ew_O2k1r4GIFL_kyQf7aBmUfVuMqKmaTM4JG2ySKNtEYSiHaXNq0aUgCuFubPzYMCrq7V7FU4LlxpUqdk-aFJAyG6waJtZgMNg85leJyVucHMc1skG6OgsMn5Xjc-Gol2JJE9QjrLZYNkdRnqfuQ1J-r2m_Z5jvv2LW8twRPEk_cJBjVIfQux4p8Nxd4GnIiEGkM5Q_-Q4RCr7YTkDkC18NyTLT1rl8WVKD920afI8g1KBwEVd3B9vE9IPEp3ba2w4L_BvL9aXfMc_OH1vA13SAlLvCd8w2R87Oe0YvKzdt48hbWxXu6vR3FTudh4j0LSkQy_wMmoLee9VnQ\",\"expirationTime\":1764136607523,\"refreshToken\":\"AMf-vBzikE_VcC-AzC0oscuLYB4004s7RuPHjz6ysAk39BBBDHDEDvxRYJoNz-Uwpr7GV_tAvg4qU51a-O04Nm916tMlLIY9RdFerEmYWK0Gh6K1qvZfzrFxmGheYucbfTYxY287lOFNEEFJeN1qUz32FRG0rAbiztiJSQQHv_4sgB7oT9T0gto5xkfwOMxN5SwROisuM1Qkxb3de27UyuePOcs5VGVUGJaJQY7cP6VjQuxcq3ySbWC36lZYwxKK4_2ViHXjQubj2--FVdmX0SN3k4xprhRikYUye-tt10i0Jiu6at4lHEdJu5sENr6hUk98FfFDCzy6sENtmUXzr0tiwnwRQUPM_ws-H40iZ5cG1lNfuAKi0nOPtZz1REsumtDwUJ0vtoPwUIyn6U9OeO8xYqCZATjAZYShZJHImrpK--xmUZ1ZKRYFzQIv-0i_vQ3iUvWTnjI3ue4W54_iYA3dC9cciar5Y0DwPIgD6AunP6DSX9JGLyU\"},\"uid\":\"Xv0X6Yea4HXHinLMmBaa1jqDGx62\"}";    @BeforeEach
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
    public void CT18_02_CancelarExclusaoAluno() throws InterruptedException {
        // --- PASSO 1: Navegar para Gerenciar Curso ---
        System.out.println("Navegando para gerenciamento...");
        // Navegação direta
        driver.get("https://testes-codefolio.web.app/manage-courses");

        // Localiza o botão "Gerenciar Curso" dentro do card correto
        WebElement btnGerenciar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h6[contains(text(), '" + CURSO_ALVO + "')]/ancestor::div[contains(@class, 'MuiCard-root')]//button[contains(text(), 'Gerenciar')]")
        ));

        // Scroll para garantir visibilidade
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnGerenciar);
        Thread.sleep(1000);
        btnGerenciar.click();

        // --- PASSO 2: Clicar na aba ALUNOS ---
        System.out.println("Acessando aba Alunos...");
        Thread.sleep(1000);
        WebElement abaAlunos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Alunos']")
        ));
        abaAlunos.click();

        // 3. ESPERAR A LISTA CARREGAR
        System.out.println("PASSO 3: Aguardando lista carregar...");
        Thread.sleep(3000);

        // Localiza o texto do nome para garantir que ela está lá
        WebElement textoNomeAluno = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + ALUNO_ALVO + "')]")
        ));

        // Scroll até a aluna
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", textoNomeAluno);
        System.out.println("Aluna encontrada na tela.");
        Thread.sleep(2000); // Pausa para você ler o nome

        // 4. Clicar na Lixeira
        System.out.println("PASSO 4: Clicando na lixeira...");

        WebElement btnExcluir = driver.findElement(
                By.xpath("//*[contains(text(), '" + ALUNO_ALVO + "')]/ancestor::*[self::tr or contains(@class, 'MuiGrid-item') or contains(@class, 'row')]//button")
        );

        // Clica via JS para evitar erro de sobreposição
        js.executeScript("arguments[0].click();", btnExcluir);

        // --- PASSO 5: Clicar em CANCELAR ---
        System.out.println("Aguardando");


        WebElement btnCancelar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']//button[contains(text(), 'CANCELAR') or contains(text(), 'Cancelar')]")
        ));

        js.executeScript("arguments[0].style.border='3px solid blue'", btnCancelar);
        System.out.println("PASSO 5: Clicando em CANCELAR");

        // Clique via JS
        js.executeScript("arguments[0].click();", btnCancelar);

        // Validação imediata: O botão deve sumir
        wait.until(ExpectedConditions.invisibilityOf(btnCancelar));
        System.out.println("Modal fechado.");

        // --- PASSO 6: Validação
        System.out.println("Validando se a aluna PERMANECE na lista...");
        Thread.sleep(1000);

        try {
            // Tenta encontrar o nome dela de novo
            boolean alunaVisivel = driver.findElement(By.xpath("//*[contains(text(), '" + ALUNO_ALVO + "')]")).isDisplayed();

            if (alunaVisivel) {
                System.out.println("TESTE PASSOU: A aluna " + ALUNO_ALVO + " continua na lista após cancelar.");
            }
        } catch (Exception e) {
            // Se der erro ao procurar, significa que ela sumiu
            Assertions.fail("ERRO GRAVE: A aluna sumiu da lista! O botão cancelar pode ter excluído o registro.");
        }
    }
}
