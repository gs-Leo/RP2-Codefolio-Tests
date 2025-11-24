import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class RFTrintaECinco {

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
    public void CT35_01_acessarDashboardRanking() throws InterruptedException {
        String nomeCurso = "Curso sem PIN G7";
        String nomeQuiz = "teste";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeCurso);
        navegarParaAbaQuiz();

        abrirRankingDoQuiz(nomeQuiz);

        WebElement tituloPagina = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Dashboard de Estudantes')]")
        ));
        assertTrue(tituloPagina.isDisplayed());

        System.out.println("CT35_01 PASSOU.");
    }

    @Test
    public void CT35_02_ordenarRankingPorNota() throws InterruptedException {
        String nomeCurso = "Curso sem PIN G7";
        String nomeQuiz = "teste";
        String criterioOrdenacao = "Nota (Maior-Menor)";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeCurso);
        navegarParaAbaQuiz();
        abrirRankingDoQuiz(nomeQuiz);

        System.out.println("Ordenando por: " + criterioOrdenacao);

        WebElement dropdownOrdenacao = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'MuiSelect-select')]")
        ));
        dropdownOrdenacao.click();

        WebElement opcao = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(), '" + criterioOrdenacao + "')]")
        ));
        opcao.click();
        Thread.sleep(1500);

        WebElement dropdownAtualizado = driver.findElement(By.xpath("//div[contains(@class, 'MuiSelect-select')]"));
        assertEquals(criterioOrdenacao, dropdownAtualizado.getText());

        System.out.println("CT35_02 PASSOU.");
    }

    @Test
    public void CT35_03_pesquisarEstudanteNoRanking() throws InterruptedException {

        String nomeCurso = "Curso sem PIN G7";
        String nomeQuiz = "teste";
        String termoBusca = "Leonardo";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeCurso);
        navegarParaAbaQuiz();
        abrirRankingDoQuiz(nomeQuiz);

        System.out.println("Pesquisando por: " + termoBusca);

        WebElement inputBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']")
        ));

        inputBusca.clear();
        inputBusca.sendKeys(termoBusca);
        Thread.sleep(1000);

        WebElement linhaAluno = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[contains(., '" + termoBusca + "')]")
        ));
        assertTrue(linhaAluno.isDisplayed());

        int numeroLinhas = driver.findElements(By.xpath("//tbody/tr")).size();
        assertTrue(numeroLinhas > 0, "A tabela deveria mostrar resultados.");

        System.out.println("CT35_03 PASSOU: Pesquisa no ranking funcionou.");
    }


    public void AcessarGerenciadorCurso() throws InterruptedException {
        WebElement menuPrincipal = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Configurações da Conta']")));
        Thread.sleep(1500);
        js.executeScript("arguments[0].click();", menuPrincipal);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(., 'Gerenciamento de Cursos')]")));
        link.click();
    }

    private void clicarEmGerenciarCurso(String nomeDoCurso) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'MuiPaper-root')]")));
        String xpathBotao = "//h6[normalize-space()='" + nomeDoCurso + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//button[contains(., 'Gerenciar Curso')]";
        WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBotao)));
        botao.click();
    }

    private void navegarParaAbaQuiz() throws InterruptedException {
        System.out.println("Navegando para a aba 'Quiz'...");
        WebElement abaQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@role='tab' and contains(., 'Quiz')]")
        ));
        abaQuiz.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Quizzes Criados')]")
        ));
        System.out.println("  Aba Quiz carregada.");
    }

    private void abrirRankingDoQuiz(String nomeQuiz) {
        System.out.println("Procurando card com h6: 'Quiz para: " + nomeQuiz + "'");

        try {

            WebElement cardQuiz = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(normalize-space(.), 'Quiz para: " + nomeQuiz + "')]/ancestor::div[contains(@class, 'MuiPaper-root')]")
            ));

            System.out.println(" Card do quiz encontrado.");

            List<WebElement> botoes = cardQuiz.findElements(By.tagName("button"));

            boolean clicou = false;
            for (WebElement btn : botoes) {
                String innerHTML = btn.getAttribute("innerHTML");
                if (innerHTML.contains("PersonIcon") || innerHTML.contains("M12 12")) {
                    System.out.println("  Ícone de pessoa (SVG) encontrado. Clicando...");
                    new Actions(driver).moveToElement(btn).click().perform();
                    clicou = true;
                    break;
                }
            }

            if (!clicou) {
                System.out.println("  Ícone SVG não identificado. Tentando pelo índice (Penúltimo botão)...");
                if (botoes.size() >= 2) {
                    WebElement btnAlvo = botoes.get(botoes.size() - 2);
                    js.executeScript("arguments[0].click();", btnAlvo);
                } else {
                    throw new RuntimeException("Não há botões suficientes no card.");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar abrir o ranking do quiz: " + e.getMessage());
        }
    }
}

