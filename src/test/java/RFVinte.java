import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RFVinte {

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

    public void AcessarGerenciadorCurso() throws InterruptedException {
        System.out.println("Iniciando o teste...");

        WebElement menuPrincipal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
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



    private void navegarParaAbaAlunos() throws InterruptedException {
        System.out.println("A navegar para a aba 'Alunos'...");
        try {
            WebElement abaAlunos = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(.)='Alunos']")
            ));
            js.executeScript("arguments[0].click();", abaAlunos);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']")
            ));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(), 'Exibindo')]")
            ));

            System.out.println("Navegou para a aba 'Alunos' com sucesso.");
            Thread.sleep(500); // Pequena pausa para a UI estabilizar

        } catch (Exception e) {
            fail("Falha ao navegar para a aba 'Alunos'. Elemento não encontrado: " + e.getMessage(), e);
        }
    }

    private void selecionarOpcaoDropdown(By dropdownLocator, String opcaoTexto) throws InterruptedException {
        System.out.println("A selecionar opção '" + opcaoTexto + "' no dropdown...");

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();

        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[contains(@class, 'MuiMenu-list')]")
        ));

        WebElement option = menu.findElement(By.xpath(".//li[normalize-space(.)='" + opcaoTexto + "']"));
        js.executeScript("arguments[0].click();", option);

        wait.until(ExpectedConditions.invisibilityOf(menu));
        System.out.println("Opção selecionada.");
        Thread.sleep(500); // Pausa para os resultados do filtro atualizarem
    }

    @Test
    public void CT20_01_pesquisarAlunoPorNomeExato() throws InterruptedException {

        String nomeDoCurso = "Curso sem PIN G7";
        String alunoParaBuscar = "Leonardo Goncalves da Silva";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_01: Pesquisando por '" + alunoParaBuscar + "'...");
        WebElement inputBusca = driver.findElement(By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']"));

        inputBusca.sendKeys(alunoParaBuscar);
        Thread.sleep(1000);

        try {
            WebElement contador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[starts-with(normalize-space(.), 'Exibindo 1 de')]")
            ));
            assertTrue(contador.isDisplayed(), "O contador 'Exibindo 1 de...' não foi encontrado.");
            System.out.println("CT20_01 PASSOU: O sistema filtrou e encontrou 1 aluno. (Texto: " + contador.getText() + ")");
        } catch (TimeoutException e) {
            fail("FALHA: O contador 'Exibindo 1 de...' não apareceu. O filtro falhou ou o aluno '" + alunoParaBuscar + "' não foi encontrado.");
        }
    }

    @Test
    public void CT20_02_pesquisarAlunoPorNomeParcial() throws InterruptedException {

        String nomeDoCurso = "Curso sem PIN G7";
        String nomeParcial = "Leonardo Gon";
        String numResultadosEsperado = "1";


        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_02: Pesquisando por nome parcial '" + nomeParcial + "'...");
        WebElement inputBusca = driver.findElement(By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']"));

        inputBusca.sendKeys(nomeParcial);
        Thread.sleep(1000);

        try {
            By seletorContador = By.xpath("//p[starts-with(normalize-space(.), 'Exibindo " + numResultadosEsperado + " de')]");

            WebElement contador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    seletorContador
            ));

            assertTrue(contador.isDisplayed(), "O contador 'Exibindo " + numResultadosEsperado + " de...' não foi encontrado.");
            System.out.println("CT20_02 PASSOU: O sistema filtrou e encontrou " + numResultadosEsperado + " alunos. (Texto: " + contador.getText() + ")");

        } catch (TimeoutException e) {
            fail("FALHA: O contador 'Exibindo " + numResultadosEsperado + " de...' não apareceu. O filtro falhou ou os dados de teste (alunos) estão incorretos.");
        }
    }

    @Test
    public void CT20_03_pesquisarAlunoPorEmail() throws InterruptedException {

        String nomeDoCurso = "Curso sem PIN G7";
        String emailParaBuscar = "leonardogds3.aluno@unipampa.edu.br";
        String numResultadosEsperado = "1";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_03: Pesquisando por email '" + emailParaBuscar + "'...");
        WebElement inputBusca = driver.findElement(By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']"));

        inputBusca.sendKeys(emailParaBuscar);
        Thread.sleep(1000);

        try {
            By seletorContador = By.xpath("//p[starts-with(normalize-space(.), 'Exibindo " + numResultadosEsperado + " de')]");

            WebElement contador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    seletorContador
            ));

            assertTrue(contador.isDisplayed(), "O contador 'Exibindo " + numResultadosEsperado + " de...' não foi encontrado.");
            System.out.println("CT20_03 PASSOU: O sistema filtrou e encontrou 1 aluno pelo email. (Texto: " + contador.getText() + ")");

        } catch (TimeoutException e) {
            fail("FALHA: O contador 'Exibindo " + numResultadosEsperado + " de...' não apareceu. O filtro falhou ou o aluno com email '" + emailParaBuscar + "' não foi encontrado.");
        }
    }

    @Test
    public void CT20_04_pesquisarAlunoInexistente() throws InterruptedException {

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso("Curso sem PIN G7");
        navegarParaAbaAlunos();

        String nomeFalso = "AlunoQueNaoExiste";
        System.out.println("A executar CT20_04: Pesquisando por '" + nomeFalso + "'...");
        WebElement inputBusca = driver.findElement(By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']"));

        inputBusca.sendKeys(nomeFalso);
        Thread.sleep(1000);

        WebElement msgVazia = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[normalize-space(.)='Nenhum estudante corresponde aos filtros aplicados.']")
        ));
        assertTrue(msgVazia.isDisplayed(), "A mensagem de 'Nenhum estudante...' não apareceu.");

        WebElement contador = driver.findElement(By.xpath("//p[starts-with(normalize-space(.), 'Exibindo 0 de')]"));
        assertTrue(contador.isDisplayed(), "O contador 'Exibindo 0 de...' não foi encontrado.");

        System.out.println("CT20_04 PASSOU: O sistema mostrou corretamente o estado de 'nenhum resultado'.");
    }

    @Test
    public void CT20_05_ordenarPorNomeZA() throws InterruptedException {

        String nomeDoCurso = "Curso com PIN G7";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_05: Ordenando por 'Nome (Z-A)'...");
        By dropdownOrdenarLocator = By.xpath("(//div[contains(@class, 'MuiSelect-select')])[1]");
        selecionarOpcaoDropdown(dropdownOrdenarLocator, "Nome (Z-A)");

        System.out.println("Verificando a nova ordem...");
        By seletorNomesAlunos = By.xpath("//tbody[contains(@class, 'MuiTableBody-root')]/tr/td[1]");


        try {
            List<WebElement> nomes = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    seletorNomesAlunos, 1 // Espera que pelo menos 2 nomes estejam visíveis
            ));

            String primeiroNome = nomes.get(0).getText();
            String ultimoNome = nomes.get(nomes.size() - 1).getText();

            assertTrue(primeiroNome.compareTo(ultimoNome) > 0,
                    "A ordem Z-A falhou. Primeiro nome: " + primeiroNome + ", Último nome: " + ultimoNome);

            System.out.println("CT20_05 PASSOU: A lista foi ordenada Z-A. (Primeiro: " + primeiroNome + ")");

        } catch (Exception e) {
            fail("FALHA: Não foi possível encontrar a lista de nomes dos alunos. Verifique o 'seletorNomesAlunos'. Erro: " + e.getMessage(), e);
        }
    }

    @Test
    public void CT20_06_filtrarPorProgresso() throws InterruptedException {

        String nomeDoCurso = "Curso com PIN G7";
        String opcaoProgresso = "Iniciante (0-24%)";
        String numResultadosEsperado = "1";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_06: Filtrando por progresso '" + opcaoProgresso + "'...");

        By dropdownProgressoLocator = By.xpath("(//div[contains(@class, 'MuiSelect-select')])[2]");

        selecionarOpcaoDropdown(dropdownProgressoLocator, opcaoProgresso);

        try {
            WebElement contador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[starts-with(normalize-space(.), 'Exibindo " + numResultadosEsperado + " de')]")
            ));

            assertTrue(contador.isDisplayed(), "O contador não atualizou corretamente para o filtro de progresso.");
            System.out.println("CT20_06 PASSOU: Filtro de progresso aplicado com sucesso.");

        } catch (TimeoutException e) {
            fail("FALHA: O contador não exibiu o valor esperado (" + numResultadosEsperado + ") após filtrar por progresso.");
        }
    }

    @Test
    public void CT20_07_filtrarPorRole() throws InterruptedException {

        String nomeDoCurso = "Curso com PIN G7";
        String opcaoRole = "Estudante";
        String numResultadosEsperado = "1";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_07: Filtrando por role '" + opcaoRole + "'...");
        By dropdownRoleLocator = By.xpath("(//div[contains(@class, 'MuiSelect-select')])[3]");
        selecionarOpcaoDropdown(dropdownRoleLocator, opcaoRole);

        try {
            WebElement contador = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[starts-with(normalize-space(.), 'Exibindo " + numResultadosEsperado + " de')]")
            ));

            assertTrue(contador.isDisplayed(), "O contador não atualizou corretamente para o filtro de role.");
            System.out.println("CT20_07 PASSOU: Filtro de role aplicado com sucesso.");

        } catch (TimeoutException e) {
            fail("FALHA: O contador não exibiu o valor esperado (" + numResultadosEsperado + ") após filtrar por role.");
        }
    }

    @Test
    public void CT20_08_limparFiltros() throws InterruptedException {

        String nomeDoCurso = "Curso com PIN G7";
        String filtroParaAplicar = "Estudante";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("A executar CT20_08: Aplicando filtro inicial para depois limpar...");
        By dropdownRoleLocator = By.xpath("(//div[contains(@class, 'MuiSelect-select')])[3]");
        selecionarOpcaoDropdown(dropdownRoleLocator, filtroParaAplicar);

        WebElement chipFiltroAtivo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'MuiChip-root') and contains(., 'filtro ativo')]")
        ));
        System.out.println("Chip de 'Filtro Ativo' detectado.");
        System.out.println("Limpando os filtros...");
        WebElement iconeFechar = chipFiltroAtivo.findElement(By.xpath(".//*[contains(@class, 'MuiChip-deleteIcon')]"));

        try {
            iconeFechar.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Clique nativo interceptado, forçando com JS Event...");
            js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", iconeFechar);
        }
        Thread.sleep(1000);

        boolean chipDesapareceu = wait.until(ExpectedConditions.invisibilityOf(chipFiltroAtivo));
        assertTrue(chipDesapareceu, "O botão de limpar filtros (chip roxo) deveria ter desaparecido.");

        System.out.println("CT20_08 PASSOU: Filtros limpos com sucesso.");
    }

}