import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RF16 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes.codefolio.com.br/";
    private JavascriptExecutor js;

    // --- SEUS DADOS DE AUTENTICAÇÃO (MANTIDOS) ---
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
            "      \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjU0NTEzMjA5OWFkNmJmNjEzODJiNmI0Y2RlOWEyZGZlZDhjYjMwZjAiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTGVvbmFyZG8gR29uY2FsdmVzIGRhIFNpbHZhIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0p0cTJyaXkyT1hIUHZ6R0tzUjUzUDlhZmtESEJZY3ZNLVMyTUpWZVBDTGhacV9DRm89czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcmVhY3QtbmEtcHJhdGljYSIsImF1ZCI6InJlYWN0LW5hLXByYXRpY2EiLCJhdXRoX3RpbWUiOjE3NjI2MzQ2MTQsInVzZXJfaWQiOiJiTm9ZVHNoNUdjYnFOYlNGVE1XNDkyc0ZmbjEyIiwic3ViIjoiYk5vWVRzaDVHY2JxTmJTRlRNVzQ5MnNGZm4xMiIsImlhdCI6MTc2NjAzNDYxNCwiZXhwIjoxNzY2MDM4MjE0LCJlbWFpbCI6Imxlb25hcmRvZ2RzMy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjExNjAwNTAzNDAzMjkxMjI2MjY1NyJdLCJlbWFpbCI6WyJsZW9uYXJkb2dkczMuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.X3SIJP91z5Obi0HKvP0WOKsbn8GpblUuU6ugPElX1Qbw2TLc64QuQbw2TLc64QuBix_98RpB6BTlXdNcvn2FTf82gZAD9n2I238PaJTDpzHOIBOWOlXDr8DAVA5iTj_TlO9eV0-InKclHehXN5YQA4hjxJOjFfIcuZtL4JsyHvdu12STYX2kdyvjapeNXKM71QwxUkzFL8-z9i5wLuYmJyA9jmaIwG2yrU_TLVk0ZIemCrpKwVVeLW5s9txAME1XgQEy_0YL5XyJuwFNYx8Yu5RZ4OTU1bbAOmp3RJ9CtlHYazxLODMvvwd8KwV4OA7N8VJ5yJp0yAga1Y1udioCrVovyWBIME_rQ\",\n" +
            "      \"expirationTime\": 1766038214857,\n" +
            "      \"refreshToken\": \"AMf-vBxxY5IxETzyATq-YbVMzVS94saun9dMf80DIuAG07H1ONoKHQjeUH0v4l1gBWGnQqrt2Nj--ir8QSTivP3XKA3TuBcyIU1gMy_H8S5qRmlQFL7-3fIbgtXWTQ8_EtdOtUdDLPt1TV30sbiUv5gC1myihDAntEeUx3NJQS4kYthEy63ip67R6udegkgD_xpzop3N3or5uc-bz_c_PZ-2qcsWeiWTV7EwNQe4Uk7Noh6HXQiqOCLUsD40APvj7uA8-z5fox_4enjI9Ls5qdx_EzvnpAnTNabSVdZE3DdFj9GKckz1Ta34ylhUed-IXBUYpAMM46kNIFgU0RNRGokUD-QNYfgOplCBhnNkwkEI9Xr2KX5azoh0KUxtjGHpRPFrD-YQNigVWew506HkuWpROwyxOqpgaRGU7DG4UY4pCZh_4ZuEeHPxpuKigJs8IumKGLSORQgipnGKV5iLmO-KUyATUjLFw\",\n" +
            "      \"tenantId\": null\n" +
            "    },\n" +
            "    \"uid\": \"bNoYTsh5GcbqNbSFTMW492sFfn12\",\n" +
            "    \"_redirectEventId\": null\n" +
            "  }";

    // --- SETUP E TEARDOWN ---

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
            // Garante que o domínio está carregado antes de tentar injetar
            wait.until(ExpectedConditions.urlContains(url));

            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                    Firebase_key,
                    Firebase_value);
            System.out.println("Injeção no local storage bem-sucedida.");

        } catch (Exception e) {
            System.out.println("Falha critica ao injetar no local storage." + e.getMessage());
            driver.quit();
            throw new RuntimeException("Falha no setup do local storage.", e);
        }

        System.out.println("Recarregando a página para autenticar...");
        driver.navigate().refresh();
        Thread.sleep(3000); // Pausa para a aplicação carregar o token.

        // Verificação rápida de login:
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Configurações da Conta']")));
            System.out.println("Login validado com sucesso!");
        } catch (TimeoutException e) {
            fail("FALHA CRÍTICA: Não foi possível se autenticar com o token fornecido.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("A fechar o navegador.");
            driver.quit();
        }
    }

    // --- MÉTODOS DE NAVEGAÇÃO E UTILS (ADAPTADOS PARA QUIZ) ---

    /**
     * Navega até a aba 'QUIZ' dentro da tela de Gerenciamento de um Curso específico.
     */
    public void AcessarAbaQuiz(String nomeDoCurso) throws InterruptedException {
        System.out.println("Iniciando navegação para a aba QUIZ do curso: " + nomeDoCurso);

        // Passo 1: Clicar no menu principal (ícone G)
        WebElement menuPrincipal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Configurações da Conta']")
                )
        );
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", menuPrincipal);
        Thread.sleep(1000);

        // Passo 2: Clicar em 'Gerenciamento de Cursos'
        WebElement linkGerenciarCursos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(., 'Gerenciamento de Cursos')]")
                )
        );
        linkGerenciarCursos.click();
        System.out.println("Navegou para a tela de Gerenciamento de Cursos.");

        // Passo 3: Clicar em 'Gerenciar Curso' no curso alvo
        WebElement botaoGerenciarCurso = localizarBotaoGerenciarCurso(nomeDoCurso);
        js.executeScript("arguments[0].click();", botaoGerenciarCurso);
        System.out.println("Clicou em 'Gerenciar Curso'.");

        // Passo 4: Clicar na aba 'QUIZ'
        WebElement abaQuiz = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'QUIZ')]")
                )
        );
        abaQuiz.click();
        System.out.println("Navegou para a aba QUIZ.");
        Thread.sleep(1500); // Pausa para carregamento da lista de Quizzes
    }

    /**
     * Método auxiliar para localizar o botão 'Gerenciar Curso' pelo nome do curso.
     */
    private WebElement localizarBotaoGerenciarCurso(String nomeDoCurso) {
        System.out.println("Localizando botão 'Gerenciar Curso' para o curso: " + nomeDoCurso);

        var cardsDeCurso = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class, 'MuiPaper-root') and .//h6]")
                )
        );

        WebElement botaoAlvo = null;
        for (WebElement card : cardsDeCurso) {
            String tituloDoCard = card.findElement(By.tagName("h6")).getText();
            if (tituloDoCard.equals(nomeDoCurso)) {
                // Encontra o botão 'Gerenciar Curso' dentro do card
                botaoAlvo = card.findElement(By.xpath(".//button[contains(., 'Gerenciar Curso')]"));
                break;
            }
        }
        assertNotNull(botaoAlvo, "Não foi possível encontrar o botão 'Gerenciar Curso' para o curso: " + nomeDoCurso);
        return botaoAlvo;
    }

    /**
     * Verifica se existem quizzes na lista dentro da aba QUIZ.
     */
    private boolean existemQuizzes(int timeoutEmSegundos) {
        WebDriverWait waitCurto = new WebDriverWait(driver, Duration.ofSeconds(timeoutEmSegundos));
        // Seletor que busca por Quizzes Criados ou itens na lista.
        String xPathSeletorDeQuiz = "//div[contains(text(), 'Quizzes Criados')]/following-sibling::div/* | //div[contains(text(), 'Quiz para:')]";

        try {
            waitCurto.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSeletorDeQuiz))
            );
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica no botão de lixeira de um Quiz específico, usando o texto do título.
     */
    private void clicarNaLixeiraDoQuiz(String nomeDoQuiz) throws InterruptedException {
        System.out.println("Procurando a lixeira do Quiz associado a: '" + nomeDoQuiz + "'...");

        // Localiza o elemento que contém a referência ao Quiz (ex: "Quiz para: Meu Video Cadastrado por Teste")
        WebElement quizItem = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), '" + nomeDoQuiz + "')]/ancestor::div[contains(@class, 'MuiPaper-root')][1] | //div[contains(text(), '" + nomeDoQuiz + "')]/ancestor::li[1]"))
        );

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", quizItem);
        Thread.sleep(500);

        // Localizar o botão de lixeira DENTRO desse item
        WebElement botaoLixeira = wait.until(
                ExpectedConditions.elementToBeClickable(
                        quizItem.findElement(By.xpath(".//button[.//svg[@data-testid='DeleteIcon'] or contains(text(), 'Deletar')]"))
                )
        );

        js.executeScript("arguments[0].click();", botaoLixeira);
        System.out.println("  ✓ Clicou na lixeira do Quiz.");
    }

    // --- CASOS DE TESTE RF16 (APENAS OS 3 ESSENCIAIS) ---

    @Test
    public void CT16_01_confirmarExclusao() throws InterruptedException {
        // **ATENÇÃO:** Defina o nome do curso e o texto de identificação do Quiz que você quer excluir.
        String nomeDoCurso = "Curso Teste de Teste Automatizado (Selenium)"; // Nome do curso que contém o quiz
        String quizParaExcluir = "Meu Video Cadastrado por Teste"; // Texto identificador (ex: "Quiz para: Meu Video...")

        // 1. Navegar até a aba QUIZ
        AcessarAbaQuiz(nomeDoCurso);
        assertTrue(existemQuizzes(3), "Pré-condição falhou: Não há Quizzes na lista para excluir.");

        // 2. Localizar e clicar na lixeira
        System.out.println("A executar CT16_01: Confirmando a exclusão.");
        clicarNaLixeiraDoQuiz(quizParaExcluir);

        // 3. Ação: Clicar em 'Sim, Excluir' (Confirmação)
        WebElement botaoConfirmar = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Sim, Excluir')] | //button[contains(text(), 'Excluir')]"))
        );
        js.executeScript("arguments[0].click();", botaoConfirmar);
        System.out.println("Clicou em 'Excluir'.");

        // 4. Verificação: O Quiz deve desaparecer da lista
        String xPathQuiz = "//*[contains(text(), '" + quizParaExcluir + "')]";
        try {
            boolean desapareceu = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathQuiz)));
            assertTrue(desapareceu, "O Quiz ainda está visível.");

            // Opcional: Verificação de Toast de sucesso
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Quiz excluído com sucesso')]")));

            System.out.println("✓ CT16_01 PASSOU: O Quiz e suas perguntas foram excluídos com sucesso!");
        } catch (TimeoutException e) {
            fail("FALHA: O Quiz ainda está na página. A exclusão falhou!");
        }
    }

    @Test
    public void CT16_02_cancelarExclusao() throws InterruptedException {
        // **ATENÇÃO:** Defina o nome do curso e o texto de identificação do Quiz que você quer testar.
        String nomeDoCurso = "Curso Teste de Teste Automatizado (Selenium)";
        String quizParaCancelar = "Quiz_Para_Cancelar_Exclusao"; // Use um quiz diferente para garantir que ele permaneça.

        // 1. Navegar até a aba QUIZ
        AcessarAbaQuiz(nomeDoCurso);
        assertTrue(existemQuizzes(3), "Pré-condição falhou: Não há Quizzes na lista para cancelar.");

        // 2. Localizar e clicar na lixeira
        List<WebElement> quizzes = driver.findElements(By.xpath("//*[contains(text(), '" + quizParaCancelar + "')]"));
        assertTrue(quizzes.size() > 0, "FALHA NA PRÉ-CONDIÇÃO: Quiz '" + quizParaCancelar + "' não encontrado.");

        System.out.println("A executar CT16_02: Cancelando a exclusão.");
        clicarNaLixeiraDoQuiz(quizParaCancelar);

        // 3. Ação: Clicar em 'Cancelar'
        WebElement botaoCancelar = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Cancelar')]"))
        );
        js.executeScript("arguments[0].click();", botaoCancelar);
        System.out.println("Clicou em 'Cancelar'.");

        // 4. Verificação: O Quiz deve continuar visível
        String xPathQuiz = "//*[contains(text(), '" + quizParaCancelar + "')]";
        try {
            WebElement quizRestante = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathQuiz)));
            assertTrue(quizRestante.isDisplayed(), "O Quiz foi excluído, mas não deveria.");
            System.out.println("✓ CT16_02 PASSOU: O Quiz permaneceu na lista após o cancelamento!");
        } catch (TimeoutException e) {
            fail("FALHA: O Quiz desapareceu da lista. A exclusão ocorreu indevidamente!");
        }
    }

    @Test
    public void CT16_03_excluirComPerguntas() throws InterruptedException {
        // **ATENÇÃO:** Defina o nome do curso e o texto de identificação do Quiz que **tem perguntas** para este teste.
        String nomeDoCurso = "Curso Teste de Teste Automatizado (Selenium)";
        String quizComPerguntas = "Quiz_Com_Multiplas_Perguntas";

        // 1. Navegar até a aba QUIZ
        AcessarAbaQuiz(nomeDoCurso);
        assertTrue(existemQuizzes(3), "Pré-condição falhou: Não há Quizzes na lista.");

        List<WebElement> quizzes = driver.findElements(By.xpath("//*[contains(text(), '" + quizComPerguntas + "')]"));
        assertTrue(quizzes.size() > 0, "FALHA NA PRÉ-CONDIÇÃO: Quiz '" + quizComPerguntas + "' não encontrado.");

        System.out.println("A executar CT16_03: Exclusão de Quiz com Perguntas.");
        clicarNaLixeiraDoQuiz(quizComPerguntas);

        // 2. Ação: Clicar em 'Sim, Excluir' (Confirmação)
        WebElement botaoConfirmar = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Sim, Excluir')] | //button[contains(text(), 'Excluir')]"))
        );
        js.executeScript("arguments[0].click();", botaoConfirmar);
        System.out.println("Clicou em 'Excluir'.");

        // 3. Verificação: O Quiz deve desaparecer (indicando que as perguntas foram limpas)
        String xPathQuiz = "//*[contains(text(), '" + quizComPerguntas + "')]";
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathQuiz)));

            System.out.println("Verificação OK: O Quiz desapareceu da lista.");
            System.out.println("✓ CT16_03 PASSOU: O Quiz com perguntas foi excluído. (Assumindo que o sistema removeu as perguntas do banco de dados, conforme o requisito RF16).");
        } catch (TimeoutException e) {
            fail("FALHA: O Quiz com perguntas ainda está na página. A exclusão falhou!");
        }
    }
}