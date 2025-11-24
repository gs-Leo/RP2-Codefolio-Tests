import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class RFTrintaEQuatro {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\n" +
            "  \"apiKey\": \"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\n" +
            "  \"appName\": \"[DEFAULT]\",\n" +
            "  \"createdAt\": \"1763679183776\",\n" +
            "  \"displayName\": \"Leonardo Goncalves da Silva\",\n" +
            "  \"email\": \"leonardogds3.aluno@unipampa.edu.br\",\n" +
            "  \"emailVerified\": true,\n" +
            "  \"isAnonymous\": false,\n" +
            "  \"lastLoginAt\": \"1763752523565\",\n" +
            "  \"phoneNumber\": null,\n" +
            "  \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocJtq2riy2OXHPvzGKsR53P9afkDHBYcvM-S2MJVePCLhZq_CFo=s96-c\",\n" +
            "  \"providerData\": [\n" +
            "    {\n" +
            "      \"providerId\": \"google.com\",\n" +
            "      \"uid\": \"116005034032912262657\",\n" +
            "      \"displayName\": \"Leonardo Goncalves da Silva\",\n" +
            "      \"email\": \"leonardogds3.aluno@unipampa.edu.br\",\n" +
            "      \"phoneNumber\": null\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stsTokenManager\": {\n" +
            "    \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTGVvbmFyZG8gR29uY2FsdmVzIGRhIFNpbHZhIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0p0cTJyaXkyT1hIUHZ6R0tzUjUzUDlhZmtESEJZY3ZNLVMyTUpWZVBDTGhacV9DRm89czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM3NTI1MjMsInVzZXJfaWQiOiJzVTVhWmRQMDRGV0xKTGFvb2hrYm1icXhqUnMxIiwic3ViIjoic1U1YVpkUDA0RldMSkxhb29oa2JtYnF4alJzMSIsImlhdCI6MTc2Mzc1MjUyMywiZXhwIjoxNzYzNzU2MTIzLCJlbWFpbCI6Imxlb25hcmRvZ2RzMy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjExNjAwNTAzNDAzMjkxMjI2MjY1NyJdLCJlbWFpbCI6WyJsZW9uYXJkb2dkczMuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.Iu36YgwatHl2gQUu77ZEUrhok_k0ZvYOF9Tlpkws-zmV79HiYwyriyLURDbFpz5tVORcoLbz6qIoLOq0K2D0yKmifLfnWNeapHkvqosKXlSOXbPtJcmHTnREZB12DM5KGfasioNsz-4HTIvy9eOM4NtgbQ8SBJQHeh9uHTtb90PVGys-w19E8_hAsU0fFQbMvkFGdHT5usCGwLQ56VJ2OHg_BbYp6nPxfgaUb6mqoGd6cZJID5S0wK0mBPUBTI93rc5WxD6mC70PnPX-racR2847QKfGhRs5s3rTV7A263DoVTjKdB3n2d53CkNpRDx_c939COksD36tle3gqma7KQ\",\n" +
            "    \"expirationTime\": 1763756123890,\n" +
            "    \"refreshToken\": \"AMf-vBzcZDX5l8fauOifGjU0Bm49py-TwAFLSeiDLOLv24oz17PA9yU6OMpb2JNhey7qskNNw5HyNHtTS8w2tG09HszjFJW1mdcZP-KIC_R4nyHQANKjNcmX0Dtuib_x9Ycrq7pUwhMqlaGlB5Ih0X0DCfdtGl-hpfCZd60S4GdLJ_tNRx6edEmRoIirA-NrD3Bh0RrbzSBW6gabIbDldFMeewqALcBLj-SWNX_quyBeVSyCM_nfY81c23tEkCzViBaTCA0BFJhToFVnn4mXdgnoTXYK8OIr89IAloNxhxHK3Qplm3U0wv3NukXQ8QxMFc8vLhjbb_SsOL1GCzZCgjgb7-If_oZekmHJ-oYBmU1iYUQRWWRoFYd8zVj2Y1sp3jC3xjlRh_843cYTmY2Uv04d8RXdg4Qiu-v8zWOM0FUERUo9ZELtxpAZKFWT8Ty9JmvA2xLl3cF2kVR_kTMxfLuPRXzTu5M6bA\"\n" +
            "  },\n" +
            "  \"tenantId\": null,\n" +
            "  \"uid\": \"sU5aZdP04FWLJLaoohkbmbqxjRs1\",\n" +
            "  \"_redirectEventId\": null\n" +
            "}";

    @BeforeEach
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
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
    @DisplayName("RF35 - Acesso Curso SEM PIN")
    public void CT35_01_testAcessoCursoSemPin() {
        irParaCursos();

        abrirCurso("Amanhecer");

        WebElement abaConteudo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()='Conteúdo']")
        ));

        assertTrue(abaConteudo.isDisplayed(), "Deveria estar na tela do curso com a aba 'Conteúdo' visível.");
    }

    @Test
    @DisplayName("RF34 - Acesso Curso COM PIN (Correto)")
    public void CT34_01_testAcessoCursoComPinCorreto() {
        irParaCursos();

        abrirCurso("Curso do pin"); // TODO: Use o nome real

        WebElement inputPin = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[role='presentation'] input.MuiInputBase-input")
        ));
        inputPin.sendKeys("00"); // PIN Correto


        WebElement btnEnviar = driver.findElement(By.xpath("//button[text()='Enviar']"));
        btnEnviar.click();

        WebElement abaConteudo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()='Conteúdo']")
        ));

        assertTrue(abaConteudo.isDisplayed(), "Deveria estar na tela do curso com a aba 'Conteúdo' visível.");
    }

    @Test
    @DisplayName("RF34 - Acesso Curso COM PIN (Incorreto)")
    public void CT34_02_testAcessoCursoComPinIncorreto() {
        irParaCursos();
        esperar(1500);
        abrirCurso("Curso Grupo 4 com PIN"); // TODO: Use o nome real

        WebElement inputPin = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[role='presentation'] input.MuiInputBase-input")
        ));
        inputPin.sendKeys("0000");

        driver.findElement(By.xpath("//button[text()='Enviar']")).click();

        WebElement msgErro = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='PIN incorreto. Tente novamente.']")
        ));

        assertTrue(msgErro.isDisplayed());

        assertFalse(driver.getCurrentUrl().contains("/conteudo-curso"));
    }

    @Test
    @DisplayName("RF34 (Extra) - Validar PIN Vazio")
    public void CT34_03_testTentativaPinVazio() {
        irParaCursos();
        abrirCurso("Curso Grupo 4 com PIN");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Enviar']")
        )).click();

        boolean modalVisivel = driver.findElements(By.cssSelector("div[role='presentation']")).size() > 0;
        assertTrue(modalVisivel, "O modal não deve fechar com PIN vazio.");
    }

    private void irParaCursos() {
        try {
            WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/listcurso']")));
            botao.click();
        } catch (Exception e) {
            System.out.println("Clique normal falhou, tentando JS...");
            WebElement botaoTexto = driver.findElement(By.xpath("//span[text()='Cursos']"));
            js.executeScript("arguments[0].click();", botaoTexto);
        }
    }

    private void abrirCurso(String nomeCurso) {

        String xpathBotao = "//h6[normalize-space()='" + nomeCurso + "']/ancestor::div[contains(@class, 'MuiCard-root')]//button[contains(text(), 'Começar')]";

        try {

            WebElement botaoComecar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBotao)));
            botaoComecar.click();
            System.out.println("Clicou no curso: " + nomeCurso);

        } catch (TimeoutException t) {
            throw new RuntimeException("Não encontrei o curso com o nome exato: '" + nomeCurso + "'. Verifique se o nome está correto na lista.", t);

        } catch (ElementClickInterceptedException e) {
            System.out.println("Clique interceptado no curso " + nomeCurso + ". Usando JS.");
            WebElement botao = driver.findElement(By.xpath(xpathBotao));
            js.executeScript("arguments[0].click();", botao);
        }
    }

    private void esperar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
