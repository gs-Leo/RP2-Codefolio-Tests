import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class RF7 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes.codefolio.com.br/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";
    private final String Firebase_value = "{\n" +
            "    \"apiKey\": \"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\",\n" +
            "    \"appName\": \"[DEFAULT]\",\n" +
            "    \"createdAt\": \"1762283223425\",\n" +
            "    \"displayName\": \"Leonardo Goncalves da Silva\",\n" +
            "    \"email\": \"leonardogds3.aluno@unipampa.edu.br\",\n" +
            "    \"emailVerified\": true,\n" +
            "    \"isAnonymous\": false,\n" +
            "    \"lastLoginAt\": \"1762634614405\",\n" +
            "    \"phoneNumber\": null,\n" +
            "    \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocJtq2riy2OXHPvzGKsR53P9afkDHBYcvM-S2MJVePCLhZq_CFo=s96-c\",\n" +
            "    \"providerData\": [\n" +
            "      {\n" +
            "        \"providerId\": \"google.com\",\n" +
            "        \"uid\": \"116005034032912262657\",\n" +
            "        \"displayName\": \"Leonardo Goncalves da Silva\",\n" +
            "        \"email\": \"leonardogds3.aluno@unipampa.edu.br\",\n" +
            "        \"phoneNumber\": null\n" +
            "      }\n" +
            "    ],\n" +
            "    \"stsTokenManager\": {\n" +
            "      \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjU0NTEzMjA5OWFkNmJmNjEzODJiNmI0Y2RlOWEyZGZlZDhjYjMwZjAiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTGVvbmFyZG8gR29uY2FsdmVzIGRhIFNpbHZhIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0p0cTJyaXkyT1hIUHZ6R0tzUjUzUDlhZmtESEJZY3ZNLVMyTUpWZVBDTGhacV9DRm89czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcmVhY3QtbmEtcHJhdGljYSIsImF1ZCI6InJlYWN0LW5hLXByYXRpY2EiLCJhdXRoX3RpbWUiOjE3NjI2MzQ2MTQsInVzZXJfaWQiOiJiTm9ZVHNoNUdjYnFOYlNGVE1XNDkyc0ZmbjEyIiwic3ViIjoiYk5vWVRzaDVHY2JxTmJTRlRNVzQ5MnNGZm4xMiIsImlhdCI6MTc2MjYzNDYxNCwiZXhwIjoxNzYyNjM4MjE0LCJlbWFpbCI6Imxlb25hcmRvZ2RzMy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjExNjAwNTAzNDAzMjkxMjI2MjY1NyJdLCJlbWFpbCI6WyJsZW9uYXJkb2dkczMuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.X3SIJP91z5Obi0HKvP0WOKsbn8GpblUuU6ugPElX1Qbw2TLc64QuBix_98RpB6BTlXdNcvn2FTf82gZAD9n2I238PaJTDpzHOIBOWOlXDr8DAVA5iTj_TlO9eV0-InKclHehXN5YQA4hjxJOjFfIcuZtL4JsyHvdu12STYX2kdyvjapeNXKM71QwxUkzFL8-z9i5wLuYmJyA9jmaIwG2yrU_TLVk0ZIemCrpKwFVeLW5s9txAME1XgQEy_0YL5XyJuwFNYx8Yu5RZ4OTU1bbAOmp3RJ9CtlHYazxLODMvvwd8KwV4OA7N8VJ5yJp0yAga1Y1udioCrjovyWBIME_rQ\",\n" +
            "      \"expirationTime\": 1762638214857,\n" +
            "      \"refreshToken\": \"AMf-vBxxY5IxETzyATq-YbVMzVS94saun9dMf80DIuAG07H1ONoKHQjeUH0v4l1gBWGnQqrt2Nj--ir8QSTivP3XKA3TuBcyIU1gMy_H8S5qRmlQFL7-3fIbgtXWTQ8_EtdOtUdDLPt1TV30sbiXkm5gC1myihDAntEeUx3NJQS4kYthEy63ip67R6udegkgD_xpzop3N3or5uc-bz_c_PZ-2qcsWeiWTV7EwNQe4Uk7Noh6HXQiqOCLUsD40APvj7uA8-z5fox_4enjI9Ls5qdx_EzvnpAnTNabSVdZE3DdFj9GKckz1Ta34ylhUed-IXBUYpAMM46kNIFgU0RNRGokUD-QNYfgOplCBhnNkwkEI9Xr2KX5azoh0KUxtjGHpRPFrD-YQNigVWew506HkuWpROwyxOqpgaRGU7DG4UY4pCZh_4ZuEeHPxpuKigJs8IumKGLSORQgipnGKV5iLmO-KUyATUjLFw\",\n" +
            "      \"tenantId\": null\n" +
            "    },\n" +
            "    \"uid\": \"bNoYTsh5GcbqNbSFTMW492sFfn12\",\n" +
            "    \"_redirectEventId\": null\n" +
            "  }";

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
            driver.quit();
        }
    }

    public void AcessarGerenciadorCurso() throws InterruptedException {
        System.out.println("Iniciando o teste...");

        WebElement menuPrincipal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        // Voltamos a procurar o <button>
                        By.xpath("//button[@aria-label='Configurações da Conta']")
                )
        );
        Thread.sleep(3000);

        js.executeScript("arguments[0].click();", menuPrincipal);
        Thread.sleep(3000);

        WebElement linkGerenciarCursos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(., 'Gerenciamento de Cursos')]")
                )
        );
        linkGerenciarCursos.click();
    }

    private boolean existemCursos(int timeoutEmSegundos) {
        WebDriverWait waitCurto = new WebDriverWait(driver, Duration.ofSeconds(timeoutEmSegundos));

        System.out.println("Verifica se existem cursos (à espera de um 'h6')...");
        try {
            waitCurto.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.tagName("h6")
                    )
            );
            System.out.println("Verificação: Cursos encontrados.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Verificação: Nenhum curso encontrado (Timeout).");
            return false;
        }
    }

    private boolean existemVideos(int timeoutEmSegundos) {
        WebDriverWait waitCurto = new WebDriverWait(driver, Duration.ofSeconds(timeoutEmSegundos));

        String xPathSeletorDeVideo = "//span[contains(@class, 'MuiListItemText-primary')]";

        System.out.println("Verifica se existem vídeos (à espera de um '" + xPathSeletorDeVideo + "')...");
        try {
            waitCurto.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(xPathSeletorDeVideo)
                    )
            );
            System.out.println("Verificação: Vídeos encontrados.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Verificação: Nenhum vídeo encontrado (Timeout).");
            return false;
        }
    }

    private void clicarEmGerenciarCurso(String nomeDoCurso) {
        System.out.println("Localizando botão 'Gerenciar Curso' para o curso: " + nomeDoCurso);

        var cardsDeCurso = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class, 'MuiPaper-root') and .//h6]")
                )
        );

        WebElement botaoAlvo = null;
        for (WebElement card : cardsDeCurso) {
            String tituloDoCard = card.findElement(By.tagName("h6")).getText();
            System.out.println("A verificar card: " + tituloDoCard);

            if (tituloDoCard.equals(nomeDoCurso)) {
                botaoAlvo = card.findElement(By.xpath(".//button[contains(., 'Gerenciar Curso')]"));
                break;
            }
        }
        assertNotNull(botaoAlvo, "Não foi possível encontrar o botão 'Gerenciar Curso' para o curso: " + nomeDoCurso);

        js.executeScript("arguments[0].click();", botaoAlvo);
        System.out.println("Cliquei no botão 'Gerenciar Curso' do curso correto.");
    }


    private void clicarNaLixeiraDoVideo(String nomeDoVideo) throws InterruptedException {
        System.out.println("Procurando a lixeira do vídeo '" + nomeDoVideo + "'...");

        WebElement spanVideo = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + nomeDoVideo + "')]")
                )
        );

        System.out.println("Buscando o li pai e scrollando...");
        WebElement liPai = spanVideo.findElement(By.xpath("./ancestor::li[1]"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", liPai);
        Thread.sleep(1000);

        List<WebElement> todosBotoes = liPai.findElements(By.tagName("button"));

        WebElement botaoLixeira = null;

        if (todosBotoes.size() >= 2) {
            // O segundo botão é sempre o de deletar (primeiro é editar, segundo é deletar)
            botaoLixeira = todosBotoes.get(1);
            System.out.println("  ✓ Botão deletar identificado (2º botão)!");
        } else if (todosBotoes.size() == 1) {
            botaoLixeira = todosBotoes.get(0);
        } else {
            try {
                botaoLixeira = liPai.findElement(By.xpath(".//button[.//svg[@data-testid='DeleteIcon']]"));
                System.out.println("Botão encontrado pelo data-testid!");
            } catch (Exception e) {
                fail("Não foi possível encontrar o botão de deletar para o vídeo '" + nomeDoVideo + "'.");
            }
        }

        if (botaoLixeira == null) {
            fail("Não foi possível encontrar a lixeira para o vídeo '" + nomeDoVideo + "'.");
        }

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoLixeira);
        Thread.sleep(500);

        wait.until(ExpectedConditions.elementToBeClickable(botaoLixeira));

        js.executeScript("arguments[0].click();", botaoLixeira);
        System.out.println("  ✓ Clicou na lixeira do vídeo '" + nomeDoVideo + "'.");
    }

    @Test
    public void CT7_01_confirmarExclusao() throws InterruptedException {

        AcessarGerenciadorCurso();

        boolean cursosExistem = existemCursos(1); // Mudei para 1 (só precisa de 1 curso)
        assertTrue(cursosExistem, "Pré-condição falhou: A página não tem cursos.");
        System.out.println("Validação passou: Cursos foram encontrados.");

        String nomeDoCurso = "G7 - Teste videos";
        clicarEmGerenciarCurso(nomeDoCurso);

        boolean videosExistem = existemVideos(1); // Mudei para 1 (só precisa que a lista carregue)
        assertTrue(videosExistem, "Pré-condição falhou: O curso não tem vídeos.");
        System.out.println("Validação passou: Vídeos foram encontrados.");

        String videoParaTestar = "teste3";
        System.out.println("Verificando pré-condição: O vídeo '" + videoParaTestar + "' existe...");

        List<WebElement> videos = driver.findElements(By.xpath(
                "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaTestar + "')]"
        ));


        assertTrue(videos.size() > 0,
                "FALHA NA PRÉ-CONDIÇÃO: O vídeo '" + videoParaTestar + "' não foi encontrado na lista. Adicione este vídeo ao curso '" + nomeDoCurso + "' para o teste passar.");

        System.out.println("Pré-condição OK: Vídeo '" + videoParaTestar + "' está na lista.");


        System.out.println("A executar CT7_01: Testando confirmação de exclusão...");
        clicarNaLixeiraDoVideo(videoParaTestar);

        System.out.println("A aguardar pelo modal e clicando em 'Sim, Excluir'...");
        WebElement botaoExcluir = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Sim, Excluir')]")
                )
        );
        js.executeScript("arguments[0].click();", botaoExcluir);
        System.out.println("Clicou em 'Excluir'.");

        System.out.println("Modal confirmado. A verificar se o vídeo desapareceu...");
        String xPathVideo = "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaTestar + "')]";

        try {
            boolean desapareceu = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathVideo))
            );
            assertTrue(desapareceu, "O vídeo '" + videoParaTestar + "' ainda está visível.");
            System.out.println("CT7_01 PASSOU: O vídeo '" + videoParaTestar + "' foi excluído com sucesso!");
        } catch (TimeoutException e) {
            fail("FALHA: O vídeo '" + videoParaTestar + "' ainda foi encontrado na página. A exclusão falhou!");
        }
    }

    @Test
    public void CT7_02_cancelarExclusao() throws InterruptedException {

        AcessarGerenciadorCurso();

        boolean cursosExistem = existemCursos(1);
        assertTrue(cursosExistem, "Pré-condição falhou: A página não tem cursos.");
        System.out.println("Validação passou: Cursos foram encontrados.");

        String nomeDoCurso = "G7 - Teste videos";
        clicarEmGerenciarCurso(nomeDoCurso);

        boolean videosExistem = existemVideos(1);
        assertTrue(videosExistem, "Pré-condição falhou: O curso não tem vídeos.");
        System.out.println("Validação passou: Vídeos foram encontrados.");

        String videoParaTestar = "teste4";
        System.out.println("Verificando pré-condição: O vídeo '" + videoParaTestar + "' existe...");

        List<WebElement> videos = driver.findElements(By.xpath(
                "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaTestar + "')]"
        ));

        assertTrue(videos.size() > 0,
                "FALHA NA PRÉ-CONDIÇÃO: O vídeo '" + videoParaTestar + "' não foi encontrado na lista. Adicione este vídeo ao curso '" + nomeDoCurso + "' para o teste passar.");

        System.out.println("Pré-condição OK: Vídeo '" + videoParaTestar + "' está na lista.");

        System.out.println("A executar CT7_02: Testando cancelamento de exclusão...");
        clicarNaLixeiraDoVideo(videoParaTestar);

        System.out.println("A aguardar pelo modal e clicando em 'Cancelar'...");
        WebElement botaoCancelar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Cancelar')]")
                )
        );
        js.executeScript("arguments[0].click();", botaoCancelar);
        System.out.println("Clicou em 'Cancelar'.");

        System.out.println("Modal cancelado. Re-entrando no curso para verificar...");
        Thread.sleep(1500);
        clicarEmGerenciarCurso(nomeDoCurso);
        System.out.println("Entrou novamente no curso '" + nomeDoCurso + "'.");
        Thread.sleep(1000);

        System.out.println("A verificar se o '" + videoParaTestar + "' ainda está visível...");
        try {
            WebElement itemVideoFinal = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaTestar + "')]")
                    )
            );
            assertTrue(itemVideoFinal.isDisplayed(), "O vídeo '" + videoParaTestar + "' não deveria ter sido excluído.");
            System.out.println("CT7_02 PASSOU: O vídeo '" + videoParaTestar + "' ainda está na lista!");
        } catch (TimeoutException e) {
            fail("FALHA: O vídeo '" + videoParaTestar + "' não foi encontrado. Ele foi excluído mesmo após clicar em Cancelar!");
        }
        Thread.sleep(1000);
    }

    @Test
    public void CT7_03_excluirUnicoVideo() throws InterruptedException {

        String nomeDoCurso = "G7 - Teste videos";
        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);

        boolean videosExistem = existemVideos(1);
        assertTrue(videosExistem, "Pré-condição falhou: O curso não tem vídeos para excluir.");
        System.out.println("Validação passou: Vídeos foram encontrados.");

        List<WebElement> videos = driver.findElements(
                By.xpath("//span[contains(@class, 'MuiListItemText-primary')]")
        );

        assertEquals(1, videos.size(), "Pré-condição falhou: Este teste requer um curso com EXATAMENTE 1 vídeo, mas " + videos.size() + " foram encontrados.");

        String videoParaTestar = videos.get(0).getText();
        System.out.println("Vídeo alvo: '" + videoParaTestar + "'");

        System.out.println("A executar CT7_03: Testando exclusão do vídeo...");
        clicarNaLixeiraDoVideo(videoParaTestar);

        System.out.println("A aguardar pelo modal e clicando em 'Excluir'...");
        WebElement botaoExcluir = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Excluir')]")
                )
        );
        js.executeScript("arguments[0].click();", botaoExcluir);
        System.out.println("Clicou em 'Excluir'.");

        System.out.println("Modal confirmado. A verificar aviso e a lista...");

        String xPathToast = "//div[contains(., 'Vídeo deletado com sucesso')]";
        try {
            WebElement toastSucesso = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathToast))
            );
            assertTrue(toastSucesso.isDisplayed(), "O aviso de 'deletado com sucesso' não apareceu.");
        } catch (TimeoutException e) {
            fail("FALHA: O aviso de sucesso 'Vídeo deletado com sucesso' NUNCA apareceu.");
        }
    }


    @Test
    public void CT7_04_excluirPrimeiroVideo() throws InterruptedException {

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso("G7 - Teste videos");

        boolean videosExistem = existemVideos(1); // Apenas verifica se a lista não está vazia
        assertTrue(videosExistem, "Pré-condição falhou: O curso não tem vídeos.");
        System.out.println("Validação passou: Vídeos foram encontrados.");

        List<WebElement> videos = driver.findElements(
                By.xpath("//span[contains(@class, 'MuiListItemText-primary')]")
        );

        assertTrue(videos.size() >= 2,
                "Pré-condição do teste falhou: Este teste (CT7_04) requer pelo menos 2 vídeos para ser executado. Só foi encontrado " + videos.size());


        String videoParaExcluir = videos.get(0).getText();
        String videoParaPermanecer = videos.get(1).getText();

        System.out.println("A executar CT7_04: Excluindo o primeiro vídeo ('" + videoParaExcluir + "')...");
        clicarNaLixeiraDoVideo(videoParaExcluir);

        WebElement botaoExcluir = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Excluir')]")
                )
        );
        js.executeScript("arguments[0].click();", botaoExcluir);
        System.out.println(" Clicou em 'Excluir'.");

        System.out.println("Modal confirmado. A verificar se o vídeo 1 desapareceu e o 2 ficou...");

        String xPathVideoExcluido = "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaExcluir + "')]";
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathVideoExcluido)));
        System.out.println("Vídeo '" + videoParaExcluir + "' desapareceu.");

        String xPathVideoRestante = "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaPermanecer + "')]";
        try {
            WebElement videoRestante = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVideoRestante))
            );
            assertTrue(videoRestante.isDisplayed(), "O vídeo '" + videoParaPermanecer + "' também desapareceu!");
            System.out.println("✓✓✓ CT7_05 PASSOU: O primeiro vídeo foi excluído e o segundo permaneceu! ✓✓✓");
        } catch (TimeoutException e) {
            fail("FALHA: O vídeo '" + videoParaPermanecer + "' não foi encontrado após a exclusão.");
        }
    }

    @Test
    public void CT7_05_excluirUltimoVideo() throws InterruptedException {


        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso("G7 - Teste videos");

        boolean videosExistem = existemVideos(1); // Apenas espera a lista carregar
        assertTrue(videosExistem, "Pré-condição falhou: O curso não tem vídeos.");
        System.out.println("Validação passou: Vídeos foram encontrados.");

        System.out.println("A fazer scroll até o fim da página para encontrar todos os vídeos...");
        long alturaAtual = 0;
        long novaAltura = -1;
        while (alturaAtual != novaAltura) {
            alturaAtual = (long) js.executeScript("return window.scrollY;");
            js.executeScript("window.scrollBy(0, 750);");
            Thread.sleep(750);
            novaAltura = (long) js.executeScript("return window.scrollY;");
        }



        List<WebElement> videos = driver.findElements(
                By.xpath("//span[contains(@class, 'MuiListItemText-primary')]")
        );
        System.out.println(videos.size() + " vídeos encontrados no total.");

        assertTrue(videos.size() >= 2,
                "Pré-condição do teste falhou: Este teste (CT7_05) requer pelo menos 2 vídeos para ser executado. Só foi encontrado " + videos.size());


        String videoParaExcluir = videos.get(videos.size() - 1).getText();
        String videoParaPermanecer = videos.get(0).getText();

        System.out.println("A executar CT7_05: Excluindo o último vídeo ('" + videoParaExcluir + "')...");
        clicarNaLixeiraDoVideo(videoParaExcluir);

        WebElement botaoExcluir = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Excluir')]")
                )
        );
        js.executeScript("arguments[0].click();", botaoExcluir);
        System.out.println("Clicou em 'Excluir'.");

        System.out.println("Modal confirmado. A verificar se o último desapareceu e o primeiro ficou...");

        String xPathVideoExcluido = "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaExcluir + "')]";
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathVideoExcluido)));
        System.out.println("Vídeo '" + videoParaExcluir + "' desapareceu.");

        String xPathVideoRestante = "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoParaPermanecer + "')]";
        try {
            WebElement videoRestante = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVideoRestante))
            );
            assertTrue(videoRestante.isDisplayed(), "O vídeo '" + videoParaPermanecer + "' (primeiro) também desapareceu!");
            System.out.println("CT7_05 PASSOU: O último vídeo foi excluído e o primeiro permaneceu!");
        } catch (TimeoutException e) {
            fail("FALHA: O vídeo '" + videoParaPermanecer + "' (primeiro) não foi encontrado após a exclusão.");
        }
    }

    @Test
    public void CT7_06_tentarExcluirPreRequisito() throws InterruptedException {

        AcessarGerenciadorCurso();
        boolean cursosExistem = existemCursos(1);
        assertTrue(cursosExistem, "Pré-condição falhou: A página não tem cursos.");

        String nomeDoCurso = "G7 - Teste videos";
        clicarEmGerenciarCurso(nomeDoCurso);

        boolean videosExistem = existemVideos(2); // Precisa de pelo menos 2
        assertTrue(videosExistem, "Pré-condição falhou: O curso não tem vídeos suficientes (precisa de 'teste1' e 'teste2').");

        String videoPreRequisito = "teste1";
        String videoDependente = "teste2";

        List<WebElement> video1List = driver.findElements(By.xpath("//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoPreRequisito + "')]"));
        assertTrue(video1List.size() > 0, "FALHA NA PRÉ-CONDIÇÃO: O vídeo pré-requisito '" + videoPreRequisito + "' não foi encontrado.");

        List<WebElement> video2List = driver.findElements(By.xpath("//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoDependente + "')]"));
        assertTrue(video2List.size() > 0, "FALHA NA PRÉ-CONDIÇÃO: O vídeo dependente '" + videoDependente + "' não foi encontrado.");

        System.out.println("Validação passou: Vídeos de pré-requisito e dependente encontrados.");

        System.out.println("Executando CT7_06: Testando exclusão de pré-requisito '" + videoPreRequisito + "'...");
        clicarNaLixeiraDoVideo(videoPreRequisito);

        WebElement botaoExcluir = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Sim, Excluir')]")
                )
        );
        js.executeScript("arguments[0].click();", botaoExcluir);
        System.out.println("Clicou em 'Excluir' (esperando um erro)...");

        System.out.println("Modal confirmado. A verificar se o aviso DE ERRO aparece...");

        String xPathToastErro = "//div[contains(., 'não pode ser excluído') or contains(., 'pré-requisito')]";

        try {
            WebElement toastErro = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathToastErro))
            );
            assertTrue(toastErro.isDisplayed(), "O aviso de erro de pré-requisito não apareceu.");
            System.out.println("Toast de erro encontrado: " + toastErro.getText());
        } catch (TimeoutException e) {
            fail("FALHA: O aviso de erro 'não pode ser excluído' NUNCA apareceu.");
        }

        Thread.sleep(3000);

        System.out.println("Recarregando o curso para verificar a persistência...");
        clicarEmGerenciarCurso(nomeDoCurso);
        String xPathVideo = "//span[contains(@class, 'MuiListItemText-primary') and contains(text(), '" + videoPreRequisito + "')]";
        try {
            WebElement videoRestante = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathVideo))
            );
            assertTrue(videoRestante.isDisplayed(), "O vídeo pré-requisito foi excluído (NÃO DEVERIA)!");
            System.out.println("CT7_07 PASSOU: O toast de erro apareceu e o vídeo NÃO foi excluído!");
        } catch (TimeoutException e) {
            fail("FALHA: O vídeo pré-requisito desapareceu da lista (NÃO DEVERIA)!");
        }
    }


}