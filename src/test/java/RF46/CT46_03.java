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

public class CT46_03 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration TIMEOUT = Duration.ofSeconds(15);
    private final String URL_BASE = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js; // Tornando o JS Executor global

    // --- 1. DADOS DO FIREBASE ---
    // (Utils.Chave do IndexedDB)
    private final String FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";

    // (Valor JSON - Objeto 'value' formatado para Java)
    private final String FIREBASE_VALUE = "{\"fbase_key\":" +
            "\"firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]\"" +
            ",\"value\":" +
            "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\"," +
            "\"appName\":\"[DEFAULT]\"," +
            "\"createdAt\":\"1763485802422\"," +
            "\"displayName\":\"Marcus Vinicius Morini Querol Junior\"," +
            "\"email\":\"marcusquerol.aluno@unipampa.edu.br\"," +
            "\"emailVerified\":true," +
            "\"isAnonymous\":false," +
            "\"lastLoginAt\":\"1763753093460\"," +
            "\"phoneNumber\":null," +
            "\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocIPSVJF5ZjtLwj6MndKM7i1yp2tDbMn13DkkmLcptbfW7FRQ74=s96-c\"," +
            "\"providerData\":[{\"providerId\":\"google.com\"," +
            "\"uid\":\"107226265685191574291\"," +
            "\"displayName\":\"Marcus Vinicius Morini Querol Junior\"," +
            "\"email\":\"marcusquerol.aluno@unipampa.edu.br\"," +
            "\"phoneNumber\":null}],\"stsTokenManager\":{" +
            "\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM3NTMwOTMsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2Mzc2MjI3OCwiZXhwIjoxNzYzNzY1ODc4LCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.NfyxLarLYC9teopa2xud35hkRb_vzZcUIwYZZIy74-TsV9mxN_KPos-gl4d6M-GeSUfLsaoHkpTOoGuBXzPP3t2bSabKLOencG0EQLmebje3OSyOgtCz1thqIbK_bwXlzWXX1T7gOvAzoVap9r37sL1vXfc_xMp-uQKqimZZQS4-4rgbXHfilhGf9cKjkUq80IgX0Clmbfw5TAtoq-eQSCSxylxfiRX07A3NJhXf9hvFmhvmbhFefE4itH-SjXH3l6vzX2bjAtRblUFXmyPCWqfzXQvrmRRRYe76eL6qrzaNXxStcGtQi-YdTiVRg9bNUICdtQrNxBbPlV4GKMUE0g\"," +
            "\"expirationTime\":1763765878831," +
            "\"refreshToken\":\"AMf-vByKbHhWw2fUiPPrKjs2I7SJSs3DQh2sfLCd2KsqTNs2m-gkAhTGKwwRQTyVr4MJz2hVvFvHgogRxrI1CA8ktoM4505R-56qTzkK2LfFoJqUZDLTTebIHG-bDdmXMYf0svAwVI1mXuFp9b2DCUiTrzF0DJvFhSCAfPaG26s2iqFrGnTRA16fmkiGN5__Hl3nICZx9pIOKlC9VB-YuTXnUJatEPPJDBO617oJF9-V77OWutwI3A0OjhucWf5Lh2WKSFqfGQH0-1YCYmh3XSkEt6FJxvTqcRAIYB_PoYE28OPUr6RDpJOf1RnhG24tPjm8VoWhzgb6xmI6Buz2c38lVQEfNJwKf7dfmV01fky3VveXXI6mchITs7u0SSqOEqn1K30liUX4-UQU_o37ebn59MSZHiGBEdfewufYEK5894dqCRWu1sc7-QQTTlLofSoL1NPSe4__FnoaTShXutX0XJYCftngwWj-ZS2_xqzitnpbDCtzIB0\"}," +
            "\"tenantId\":null,\"uid\":\"Xv0X6Yea4HXHinLMmBaa1jqDGx62\"}";

    @BeforeEach
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, TIMEOUT);
        js = (JavascriptExecutor) driver; // Inicializa o JS Executor

        // 1. Carrega o domínio
        driver.get(URL_BASE);

        // 2. Injeta os dados no Local Storage
        System.out.println("Injetando dados de autenticação no Local Storage...");
        try {
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                    FIREBASE_KEY,
                    FIREBASE_VALUE);
            System.out.println("Injeção no Local Storage bem-sucedida.");

        } catch (Exception e) {
            System.out.println("Falha crítica ao injetar no Local Storage: " + e.getMessage());
            driver.quit();
            throw new RuntimeException("Falha no setup do Local Storage", e);
        }

        // 3. Recarrega a página (agora com o token injetado)
        System.out.println("Recarregando a página...");
        driver.navigate().refresh();
    }
    @Test
    public void testTentativaAcessoCursoBloqueado() throws InterruptedException {
        System.out.println("🚀 Iniciando CT-46.03 - Segurança do PIN");

        // 1. Ir para Lista
        driver.get("https://testes-codefolio.web.app/listcurso");
        Thread.sleep(3000);

        // 2. Clicar no curso bloqueado
        try {
            WebElement botaoBloqueado = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class, 'MuiCard-root') and .//*[contains(text(), 'Curso para Testes')]]//button")
            ));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoBloqueado);
            Thread.sleep(1000);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(botaoBloqueado)).click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", botaoBloqueado);
            }
            System.out.println("✅ Clicou no curso bloqueado.");

        } catch (Exception e) {
            Assertions.fail("❌ Não clicou no curso.");
        }

        // 3. Verificar Modal e Clicar em Enviar Vazio
        try {
            // Verifica se abriu o modal
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Você está tentando acessar')]")
            ));
            System.out.println("✅ Modal de PIN aberto.");

            // Localiza e Clica no botão ENVIAR
            WebElement botaoEnviar = driver.findElement(By.xpath("//button[text()='Enviar']"));
            botaoEnviar.click();
            System.out.println("✅ Clicou em 'Enviar' (vazio).");

            // Validação Final: O modal AINDA deve estar visível (não deixou entrar)
            Thread.sleep(1000);
            boolean modalAindaVisivel = driver.findElement(By.xpath("//*[contains(text(), 'Você está tentando acessar')]")).isDisplayed();

            Assertions.assertTrue(modalAindaVisivel, "O sistema deveria manter o modal aberto ao enviar PIN vazio!");
            System.out.println("✅ Sucesso! O sistema barrou o acesso vazio.");

        } catch (Exception e) {
            Assertions.fail("❌ Erro na validação do modal ou envio: " + e.getMessage());
        }

        System.out.println("🎉 Teste Finalizado com Sucesso!");
    }

    @AfterEach
    public void tearDown() {
        // driver.quit();
    }
}
