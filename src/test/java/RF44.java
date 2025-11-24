import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class RF44 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String MINHA_FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String MINHA_FIREBASE_VALUE = "{\n" +
            "  \"apiKey\": \"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\n" +
            "  \"appName\": \"[DEFAULT]\",\n" +
            "  \"createdAt\": \"1763463289629\",\n" +
            "  \"displayName\": \"Gustavo Fernandes dos Anjos\",\n" +
            "  \"email\": \"gustavoanjos.aluno@unipampa.edu.br\",\n" +
            "  \"emailVerified\": true,\n" +
            "  \"isAnonymous\": false,\n" +
            "  \"lastLoginAt\": \"1763589299577\",\n" +
            "  \"phoneNumber\": null,\n" +
            "  \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocLxMXhjTFMWBjfQ54a7_yx2353S2sSARgRbEpzkFZa3lnRA7w=s96-c\",\n" +
            "  \"providerData\": [\n" +
            "    {\n" +
            "      \"displayName\": \"Gustavo Fernandes dos Anjos\",\n" +
            "      \"email\": \"gustavoanjos.aluno@unipampa.edu.br\",\n" +
            "      \"phoneNumber\": null,\n" +
            "      \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocLxMXhjTFMWBjfQ54a7_yx2353S2sSARgRbEpzkFZa3lnRA7w=s96-c\",\n" +
            "      \"providerId\": \"google.com\",\n" +
            "      \"uid\": \"109653518326889556566\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stsTokenManager\": {\n" +
            "    \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiR3VzdGF2byBGZXJuYW5kZXMgZG9zIEFuam9zIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0x4TVhoalRGTVdCamZRNTRhN195eDIzNTNTMnNTQVJnUmJFcHprRlphM2xuUkE3dz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS90ZXN0ZXMtY29kZWZvbGlvIiwiYXVkIjoidGVzdGVzLWNvZGVmb2xpbyIsImF1dGhfdGltZSI6MTc2MzU4OTI5OSwidXNlcl9pZCI6IjhQbEdLVVdLT3paOFIybkNuMk5QbVpISldJcDEiLCJzdWIiOiI4UGxHS1VXS096WjhSMm5DbjJOUG1aSEpXSXAxIiwiaWF0IjoxNzYzNTg5Mjk5LCJleHAiOjE3NjM1OTI4OTksImVtYWlsIjoiZ3VzdGF2b2Fuam9zLmFsdW5vQHVuaXBhbXBhLmVkdS5iciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA5NjUzNTE4MzI2ODg5NTU2NTY2Il0sImVtYWlsIjpbImd1c3Rhdm9hbmpvcy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.unCpNp7hpPipadFnaENxLlA6AuzN7j_bzeW9EvREuaBVnhEsHtcgJPDtfepLMhvFOJDWjmSeYPmKmPq_2eAS-5MYc5MbptRtSucFfLU0fl5Q_eHPQVJcTMYBCcODbX6d4uZLuuIcIwpu1CcsHHdk5oROGQl-4L6NaKlZsSm8tGzucsHnF-Aj_tnXaVNJ4XfjYvSagUR5s8daj3Q_fqsucletzWHJtrP4ns8Ifp0p-ebtyI-ikcAqyWb8BH4EHrSULyXApukcCt00eVL5dKYlMey_7hxVppa8MOkafYAw8Fslqx5to5MT8W13FOXT2aaYRvlOOa6I-XUIPFPw_nvp2w\",\n" +
            "    \"expirationTime\": 1763592898955,\n" +
            "    \"refreshToken\": \"AMf-vBwAVM6yxJYuWD3t6e_kbeKgVfbc-oeIxUCB5BqS1p2dUXAfZTXQwSJxN0fisJcMruFOOKlrnAyDFcCdn-_q_uZaOvs-YnAS_u-H8ptn2MbYgKBA-vU5v0DzQvNHx6PmcX5_BsYH-39nyRmWNhqXcvh0HiKUjv2ZulfNfsOXM0RocEBFZiQ_GJe6npZwNgZRbrdsqp_9ng6qIUjXJfOR0Y22z_m3rnGVuPax2W-LS3jQzJyUGJQsX-SeuLsk1VxYhtqodX5Z3yXHcx_ErcKNVsi1Ajb9_-Orb2xvKlFgc3CurjYqARZg5rxiQUZ_twVU7-J-eLpR9jtSLbT3C_qJE5LGGiuXqlcwpZE5tStX806keigzrrdtsQrGGrRdkrx1AFE5kRiw2d_-PBVGM2r-4u6r40evoCDhIfw6_i99GXt2XB9C4bPQXKmASdVGdXw1KJRFo5e2O1RUJYDYOheEDLNpfJBtRw\"\n" +
            "  },\n" +
            "  \"uid\": \"8PlGKUWKOzZ8R2nCn2NPmZHJWIp1\",\n" +
            "  \"_redirectEventId\": null\n" +
            "}";

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, timeout);
        js = (JavascriptExecutor) driver;

        driver.get(url);
        System.out.println("Injetando token de login...");
        try {
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                    MINHA_FIREBASE_KEY,
                    MINHA_FIREBASE_VALUE);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao injetar token: " + e.getMessage());
        }
        System.out.println("Recarregando página para aplicar login...");
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Configurações da Conta']")));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("A fechar o navegador.");
            driver.quit();
        }
    }

    public void AcessarGerenciadorCurso() {
        System.out.println("Acessando o Gerenciador de Cursos...");
        WebElement menuPrincipal = driver.findElement(By.xpath("//button[@aria-label='Configurações da Conta']"));
        js.executeScript("arguments[0].click();", menuPrincipal);

        WebElement linkGerenciarCursos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(., 'Gerenciamento de Cursos')]")
                )
        );
        linkGerenciarCursos.click();
    }

    @Test
    public void acessarCursoComPinCorreto() {
        String nomeCursoComPin = "Curso do pin";
        String pinCorreto = "00";

        System.out.println("Procurando curso COM PIN: " + nomeCursoComPin);

        String xpathCadeado = "//h6[normalize-space(.)='" + nomeCursoComPin + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//*[contains(@data-testid, 'LockIcon')]";
        WebElement cadeado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCadeado)));
        assertNotNull(cadeado, "O ícone de cadeado não está visível para o curso com PIN!");
        System.out.println("Cadeado verificado.");

        String xpathBotaoAcessar = "//h6[normalize-space(.)='" + nomeCursoComPin + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//button[normalize-space(.)='Acessar']";
        driver.findElement(By.xpath(xpathBotaoAcessar)).click();

        // 3. Esperar Modal de PIN
        System.out.println("Aguardando modal de PIN...");
        WebElement modalTitulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'PIN de Acesso')]") // Texto do título ou label
        ));

        System.out.println("Digitando PIN...");
        WebElement inputPin = driver.findElement(By.xpath("//label[text()='PIN de Acesso']/following-sibling::div//input"));
        inputPin.sendKeys(pinCorreto);

        System.out.println("Clicando em Enviar...");
        driver.findElement(By.xpath("//button[normalize-space(.)='Enviar']")).click();

        System.out.println("Verificando acesso...");
        WebElement abaVideos = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(), 'Conteúdo')]")
        ));

        assertNotNull(abaVideos, "Não entrou na página do curso após PIN correto!");
        System.out.println("SUCESSO: Acesso com PIN realizado!");
    }

    @Test
    public void acessoNegadoPinIncorreto() {
        String nomeCursoComPin = "Curso do pin";
        String pinErrado = "0";

        String xpathBotaoAcessar = "//h6[normalize-space(.)='" + nomeCursoComPin + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//button[normalize-space(.)='Acessar']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBotaoAcessar))).click();

        System.out.println("Testando PIN incorreto...");
        WebElement inputPin = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='PIN de Acesso']/following-sibling::div//input")
        ));
        inputPin.sendKeys(pinErrado);

        driver.findElement(By.xpath("//button[normalize-space(.)='Enviar']")).click();

        System.out.println("Verificando mensagem de erro...");
        WebElement msgErro = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'PIN incorreto. Tente novamente.')]")
        ));

        assertNotNull(msgErro, "A mensagem de erro de PIN não apareceu!");
        System.out.println("SUCESSO: Acesso bloqueado com PIN errado!");
    }
    @Test
    public void cancelarInsercaoPin() {
        String nomeCursoComPin = "Curso do pin";

        String xpathBotaoAcessar = "//h6[normalize-space(.)='" + nomeCursoComPin + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//button[normalize-space(.)='Acessar']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBotaoAcessar))).click();

        WebElement tituloModal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'PIN de Acesso')]")
        ));

        System.out.println("Pressionando ESC para cancelar...");
        new org.openqa.selenium.interactions.Actions(driver).sendKeys(Keys.ESCAPE).perform();

        wait.until(ExpectedConditions.invisibilityOf(tituloModal));
        WebElement btnAcessar = driver.findElement(By.xpath(xpathBotaoAcessar));
        assertTrue(btnAcessar.isDisplayed(), "O modal não fechou ou o botão Acessar sumiu.");

        System.out.println("SUCESSO: Modal de PIN fechado com ESC.");
    }


}