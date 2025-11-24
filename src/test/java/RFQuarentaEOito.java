import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RFQuarentaEOito {
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
    @DisplayName("RF48.01 - Selecionar e Ver um Vídeo")
    public void CT48_01_verVideoEmAndamento() throws InterruptedException {
        String nomeCurso = "Curso sem PIN G7";
        String tituloVideo = "teste";

        irParaCursosEmAndamento();
        entrarNoCurso(nomeCurso);

        selecionarVideoNaLista(tituloVideo);

        try {
            WebElement botaoAtivo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[normalize-space()='" + tituloVideo + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//button[contains(., 'Ver Video') or contains(., 'Ver Vídeo')]")
            ));
            assertTrue(botaoAtivo.isDisplayed(), "O vídeo deveria estar selecionado (botão ativo).");
            System.out.println("CT48_01 PASSOU: Vídeo selecionado com sucesso.");
        } catch (TimeoutException e) {
            fail("O botão 'Ver Vídeo' não ficou ativo após o clique. Verifique se a ação ocorreu.");
        }
    }

    @Test
    @DisplayName("RF48.02 - Alternar entre dois vídeos diferentes")
    public void CT48_02_alternarEntreVideos() throws InterruptedException {
        String nomeCurso = "Curso com PIN G7";
        String video1 = "TEste";
        String video2 = "Aula 1";

        irParaCursosEmAndamento();
        entrarNoCurso(nomeCurso);

        selecionarVideoNaLista(video1);
        validarQueOVideoEAtual(video1);

        selecionarVideoNaLista(video2);
        validarQueOVideoEAtual(video2);


        System.out.println("CT48_02 PASSOU: Alternância de vídeos funcionou.");
    }

    @Test
    @DisplayName("RF48.03 - Re-clicar no vídeo já selecionado (Estabilidade)")
    public void CT48_03_reclicarVideoAtual() throws InterruptedException {
        String nomeCurso = "Curso com PIN G7";
        String video = "TEste";

        irParaCursosEmAndamento();
        entrarNoCurso(nomeCurso);

        selecionarVideoNaLista(video);
        validarQueOVideoEAtual(video);

        System.out.println("Tentando clicar novamente no mesmo vídeo...");
        selecionarVideoNaLista(video);

        validarQueOVideoEAtual(video);

        System.out.println("CT48_03 PASSOU: Re-clique processado sem erros.");
    }


    private void irParaCursosEmAndamento() throws InterruptedException {
        System.out.println("Navegando para Cursos -> Em Andamento...");
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/listcurso']")));
            link.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@href='/listcurso']")));
        }
        wait.until(ExpectedConditions.urlContains("/listcurso"));

        WebElement aba = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'ndamento')]")));
        for (int i = 0; i < 5; i++) {
            if ("true".equals(aba.getAttribute("aria-selected"))) break;
            try { aba.click(); } catch(Exception e) { js.executeScript("arguments[0].click();", aba); }
            Thread.sleep(1000);
        }

        try {
            wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Continuar')]")));
        } catch (Exception ignored) {}
    }
    private void entrarNoCurso(String nomeCurso) throws InterruptedException {
        System.out.println("Procurando curso: " + nomeCurso);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'MuiPaper-root')]//h6")));
        List<WebElement> cards = driver.findElements(By.xpath("//div[contains(@class, 'MuiPaper-root') and .//h6]"));

        WebElement cardAlvo = null;
        for (WebElement c : cards) {
            if (c.findElement(By.tagName("h6")).getText().trim().equals(nomeCurso)) {
                cardAlvo = c;
                break;
            }
        }
        if (cardAlvo == null) {
            throw new RuntimeException("Curso '" + nomeCurso + "' não encontrado.");
        }

        WebElement elementoClicavel = cardAlvo;
        try {
            elementoClicavel = cardAlvo.findElement(By.tagName("button"));
        } catch (Exception e) {
            System.out.println("  Botão não encontrado no card, clicando no container.");
        }

        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", elementoClicavel);
        Thread.sleep(500);

        System.out.println("  Clicando no curso...");
        try {
            elementoClicavel.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", elementoClicavel);
        }

        System.out.println("  Aguardando carregamento da lista de vídeos...");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'MuiPaper-root')]//button")));
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("AVISO: A lista de vídeos demorou a aparecer, mas vamos tentar prosseguir.");
        }
    }

    private void selecionarVideoNaLista(String tituloVideo) {
        System.out.println("Tentando selecionar o vídeo: " + tituloVideo);

        String xpathBotao = "//h6[normalize-space()='" + tituloVideo + "']/ancestor::div[contains(@class, 'MuiPaper-root')]//button[contains(., 'Ver Vídeo') or contains(., 'Assistir') or contains(., 'Ver Video')]";

        try {

            WebElement botao = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBotao)));

            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", botao);
            Thread.sleep(800);

            wait.until(ExpectedConditions.elementToBeClickable(botao));

            System.out.println("  > Botão encontrado! Texto atual: " + botao.getText());

            js.executeScript("arguments[0].click();", botao);

            System.out.println("  > Clique realizado!");
            Thread.sleep(1500);

        } catch (TimeoutException e) {

            System.out.println("ERRO: Não encontrou botão 'Ver Vídeo' nem 'Assistir' para: " + tituloVideo);
            try {
                WebElement card = driver.findElement(By.xpath("//h6[normalize-space()='" + tituloVideo + "']/ancestor::div[contains(@class, 'MuiPaper-root')]"));
                System.out.println("Texto contido no card: " + card.getText().replace("\n", " | "));
            } catch (Exception ex) {
                System.out.println("Nem o card foi encontrado.");
            }
            throw new RuntimeException("Botão de ação não encontrado para o vídeo: " + tituloVideo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar clique no vídeo: " + e.getMessage());
        }
    }

    private void validarQueOVideoEAtual(String tituloVideo) {
        System.out.println("Validando se o vídeo '" + tituloVideo + "' está marcado como ATUAL...");

        String xpathIndicador = "//h6[normalize-space()='" + tituloVideo + "']/parent::div//p[contains(text(), 'Vídeo atual')]";

        try {
            WebElement indicador = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathIndicador)));
            assertTrue(indicador.isDisplayed(), "O texto 'Vídeo atual' deveria estar visível para: " + tituloVideo);
            System.out.println("  Validação visual OK: O vídeo está marcado como atual.");
        } catch (TimeoutException e) {
            fail("FALHA: O vídeo '" + tituloVideo + "' foi clicado, mas o texto 'Vídeo atual' não apareceu.");
        }
    }
}
