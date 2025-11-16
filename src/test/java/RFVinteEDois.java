
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RFVinteEDois {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration TIMEOUT = Duration.ofSeconds(30);
    private final String URL_BASE = "https://testes.codefolio.com.br/";
    private JavascriptExecutor js; // Tornando o JS Executor global

    // --- 1. DADOS DO FIREBASE (PEGUE UM TOKEN NOVO!) ---
    // (Chave do IndexedDB)
    private final String FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";

    // (Valor JSON - Objeto 'value' formatado para Java)
    private final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1760400691226\",\"displayName\":\"Rodrigo Thoma da Silva\",\"email\":\"rodrigothoma.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1762636773459\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocLwnrKxW0oUL58-rcdaYs5RaOPQw48A54q2_oTORwB1NOCgnw=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"106869220410814831244\",\"displayName\":\"Rodrigo Thoma da Silva\",\"email\":\"rodrigothoma.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDI5MzRmZTBlZWM0NmE1ZWQwMDA2ZDE0YTFiYWIwMWUzNDUwODMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiUm9kcmlnbyBUaG9tYSBkYSBTaWx2YSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NMd25yS3hXMG9VTDU4LXJjZGFZczVSYU9QUXc0OEE1NHEyX29UT1J3QjFOT0Nnbnc9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcmVhY3QtbmEtcHJhdGljYSIsImF1ZCI6InJlYWN0LW5hLXByYXRpY2EiLCJhdXRoX3RpbWUiOjE3NjI1Mzk2NTUsInVzZXJfaWQiOiJ5VzczSWRwSHRUWDFPdTdxbWpLQXZNcUpwVkcyIiwic3ViIjoieVc3M0lkcEh0VFgxT3U3cW1qS0F2TXFKcFZHMiIsImlhdCI6MTc2Mjk3ODUyNCwiZXhwIjoxNzYyOTgyMTI0LCJlbWFpbCI6InJvZHJpZ290aG9tYS5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuYY29tIjpbIjEwNjg2OTIyMDQxMDgxNDgzMTI0NCJdLCJlbWFpbCI6WyJyb2RyaWdvdGhvbWEuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.jMMo40wekA0T22wL9CVeMOWoaVvksZ3WBicjoZV2M4s0xZtGzy7H9Pa6O6gmpCnIuFBNGzzSZ4p6-sid_UyepQuT11srhJv795eqQq2IvODnSw4H0akQ_CeoibJ5nbLotwZPMYnWM5tYaex1eps-A8SuLHC0LrcCKl68c1R96TeNnLLiRu47WtWTTK4DjNZcnPWJH_l9H7vSma5KIXBYA2luwCQwlmSxHBWwtDvfXkSuuBxPwwdFbLWeXo-ILXrdMgtHekBh2_hnPQ7ENrLHuiMC489Kr5auBIJIWY7xq5pWthIHH7LXouX73vhOm2De0p-qpclO7w_5I21adEwKyQ\",\"expirationTime\":1762982122470,\"refreshToken\":\"AMf-vBy9JlK4zfGx9S5QnnyQIdly9tlJlSY57DajZ4WWoNJVnoHc4SE3MsLWJKXz_K9XX4AGUGasRLV8bUAQhCSWHXnpH4e9OMFTqd7zWIIbkH4cRtqSAxlgkz9r5K0H8bxj7qIhomucpLaFVG31Pv_i325JaUiMlchA5wke2jUUEbgd177msk5twJk5g4qjmeOxSrsEAhNHpnWof46pWVnhxF3J9SdXUV6e_BBfs7NIDu9hyR_FEEDd1LfkmJoM-ulxLh01cnvdWBXDZhADC8f0g8HATKPeOb9L_tUp2GZbSCe6koyA0TnEm6XDkosXo4CJieGHBHLAVUu6wBAOGTzAP8T29G2iCbl1HzARybYGchNP8LrCK3nszH8q1uq2i3M6fcrzQ2w5vU5pNdKzqXEo8aB6vzG8jDsL3H7DnINowgK4HvfMvNxK5pWTxEDvWYZGox9aHC4fNDLq5M6arW3J8OZE0zTmFQ\",\"tenantId\":null},\"uid\":\"yW73IdpHtTX1Ou7qmjKAvMqJpVG2\",\"_redirectEventId\":null}";

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, TIMEOUT);
        js = (JavascriptExecutor) driver; // Inicializa o JS Executor

        // --- ESTRATÉGIA LOCAL STORAGE (Formato Correto) ---
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

        verificarLogin();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void CT22_01_CadastroAvaliacaoSucesso() {
        criarCurso();

        // Clicando em "Avaliações"
        WebElement tabAvaliacoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avaliações']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabAvaliacoes);
        tabAvaliacoes.click();
        System.out.println("Aba Avaliações acessada com sucesso!");

        //Dando nome à avaliação
        WebElement NomeAvaliacao;
        NomeAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
        String idNomeAvaliacao = NomeAvaliacao.getAttribute("for");
        WebElement NomeAvInput = driver.findElement(By.id(idNomeAvaliacao));
        NomeAvInput.sendKeys("Prova 1");

        System.out.println("Nome atribuído com Sucesso!");

        // Dando porcentagem à avaliação
        WebElement LabelPercentual;
        LabelPercentual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
        String idPercentual = LabelPercentual.getAttribute("for");
        WebElement InputPercentual = driver.findElement(By.id(idPercentual));
        InputPercentual.clear();
        InputPercentual.sendKeys("40");

        System.out.println("Percentual atribuído com Sucesso!");

        // Clicando no botão "Adicionar Avaliação"
        WebElement botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        // Procura pelo elemento que contenha o texto "Prova 1"
        try {
            WebElement avaliacaoNaLista;
            avaliacaoNaLista = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Prova 1')]")));
            assertTrue(avaliacaoNaLista.isDisplayed(), "Erro: A avaliação 'Prova 1' não apareceu na lista após salvar.");
            System.out.println("A avaliação 'Prova 1' foi encontrada na lista!");

            //Procura pelo percentual "40"
            WebElement percentualAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space()='40%']")));
            assertTrue(percentualAvaliacao.isDisplayed(), "Erro: Porcentagem '40%' não encontrada.");
            System.out.println("Percentual '40%' encontrado");;

        } catch (Exception e) {
            System.out.println("FALHA: O elemento não foi encontrado ou demorou demais para aparecer.");
            throw e; // Relança o erro para o JUnit marcar o teste como falho
        }
    }

    @Test
    @Order(2)
    public void CT22_02_TentativaCadastroSemNome() {
        criarCurso();

        // Clicando em "Avaliações"
        WebElement tabAvaliacoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avaliações']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabAvaliacoes);
        tabAvaliacoes.click();
        System.out.println("Aba Avaliações acessada com sucesso!");

        // Não atribuindo nome propositalmente
        // Dando porcentagem à avaliação
        WebElement LabelPercentual;
        LabelPercentual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
        String idPercentual = LabelPercentual.getAttribute("for");
        WebElement InputPercentual = driver.findElement(By.id(idPercentual));
        InputPercentual.clear();
        InputPercentual.sendKeys("30");

        System.out.println("Percentual atribuído com Sucesso!");

        // Clicando no botão "Adicionar Avaliação"
        WebElement botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        try {
            WebElement labelNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
            WebElement inputNome = driver.findElement(By.id(labelNome.getAttribute("for")));

            String mensagemNavegador = (String) js.executeScript("return arguments[0].validationMessage;", inputNome);

            System.out.println("Mensagem capturada do navegador: " + mensagemNavegador);

            boolean contemMensagem = mensagemNavegador.contains("Preencha este campo");

            assertTrue(contemMensagem, "Erro: O campo Nome não apresentou a validação 'Preencha este campo'. Mensagem atual: " + mensagemNavegador);

            System.out.println("O navegador bloqueou o envio e mostrou a mensagem correta.");

        } catch (Exception e) {
            System.out.println("Mensagem de erro não encontrada: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Order(3)
    public void CT22_03_TentativaCadastroSemPercentual() {
        criarCurso();

        // Clicando em "Avaliações"
        WebElement tabAvaliacoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avaliações']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabAvaliacoes);
        tabAvaliacoes.click();
        System.out.println("Aba Avaliações acessada com sucesso!");

        //Dando nome à avaliação
        WebElement NomeAvaliacao;
        NomeAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
        String idNomeAvaliacao = NomeAvaliacao.getAttribute("for");
        WebElement NomeAvInput = driver.findElement(By.id(idNomeAvaliacao));
        NomeAvInput.sendKeys("Prova 2");

        System.out.println("Nome atribuído com sucesso!");

        // Não colocando percentagem propositalmente
        // Clicando no botão "Adicionar Avaliação"
        WebElement botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        try {
            WebElement labelPerc;
            labelPerc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
            WebElement inputPerc = driver.findElement(By.id(labelPerc.getAttribute("for")));

            String mensagemNavegador = (String) js.executeScript("return arguments[0].validationMessage;", inputPerc);

            System.out.println("Mensagem capturada do navegador: " + mensagemNavegador);

            boolean contemMensagem = mensagemNavegador.contains("Preencha este campo");

            assertTrue(contemMensagem, "Erro: O campo Porcentagem na Nota Final não apresentou a validação 'Preencha este campo'. Mensagem atual: " + mensagemNavegador);

            System.out.println("O navegador bloqueou o envio e mostrou a mensagem correta.");

        } catch (Exception e) {
            System.out.println("Mensagem de erro não encontrada: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Order(4)
    public void CT22_04_TentativaCadastroPercentualNaoNumerico() {
        criarCurso();

        // Clicando em "Avaliações"
        WebElement tabAvaliacoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avaliações']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabAvaliacoes);
        tabAvaliacoes.click();
        System.out.println("Aba Avaliações acessada com sucesso!");

        //Dando nome à avaliação
        WebElement NomeAvaliacao;
        NomeAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
        String idNomeAvaliacao = NomeAvaliacao.getAttribute("for");
        WebElement NomeAvInput = driver.findElement(By.id(idNomeAvaliacao));
        NomeAvInput.sendKeys("Prova 3");

        System.out.println("Nome atribuído com sucesso!");

        // Tentando atribuir um valor não numérico no campo de Percentagem
        WebElement LabelPercentual;
        LabelPercentual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
        String idPercentual = LabelPercentual.getAttribute("for");
        WebElement InputPercentual = driver.findElement(By.id(idPercentual));
        InputPercentual.clear();
        InputPercentual.sendKeys("abc");

        System.out.println("Valor 'abc' enviado no campo de percentagem");

        // Clicando no botão "Adicionar Avaliação"
        WebElement botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        try {
            WebElement labelPerc;
            labelPerc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
            WebElement inputPerc = driver.findElement(By.id(labelPerc.getAttribute("for")));

            String mensagemNavegador = (String) js.executeScript("return arguments[0].validationMessage;", inputPerc);

            System.out.println("Mensagem capturada do navegador: " + mensagemNavegador);

            boolean contemMensagem = mensagemNavegador.contains("Preencha este campo");

            assertTrue(contemMensagem, "Erro: O campo Porcentagem na Nota Final não apresentou a validação 'Preencha este campo'. Mensagem atual: " + mensagemNavegador);

            System.out.println("O navegador bloqueou o envio e mostrou a mensagem correta.");

        } catch (Exception e) {
            System.out.println("Mensagem de erro não encontrada: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Order(5)
    public void CT22_05_TentativaCadastroPercentualMaiorQue100() {
        criarCurso();

        // Clicando em "Avaliações"
        WebElement tabAvaliacoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avaliações']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabAvaliacoes);
        tabAvaliacoes.click();
        System.out.println("Aba Avaliações acessada com sucesso!");

        //Dando nome à avaliação
        WebElement NomeAvaliacao;
        NomeAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
        String idNomeAvaliacao = NomeAvaliacao.getAttribute("for");
        WebElement NomeAvInput = driver.findElement(By.id(idNomeAvaliacao));
        NomeAvInput.sendKeys("Prova 4");

        System.out.println("Nome atribuído com sucesso!");

        // Tentando atribuir um valor não numérico no campo de Percentagem
        WebElement LabelPercentual;
        LabelPercentual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
        String idPercentual = LabelPercentual.getAttribute("for");
        WebElement InputPercentual = driver.findElement(By.id(idPercentual));
        InputPercentual.clear();
        InputPercentual.sendKeys("101");

        System.out.println("Valor '101' enviado no campo de percentagem");

        // Clicando no botão "Adicionar Avaliação"
        WebElement botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        try {
            WebElement labelPerc;
            labelPerc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
            WebElement inputPerc = driver.findElement(By.id(labelPerc.getAttribute("for")));

            String mensagemNavegador = (String) js.executeScript("return arguments[0].validationMessage;", inputPerc);

            System.out.println("Mensagem capturada do navegador: " + mensagemNavegador);

            boolean contemMensagem = mensagemNavegador.contains("O valor deve ser menor ou igual a 100.");

            assertTrue(contemMensagem, "Erro: O campo Porcentagem na Nota Final não apresentou validação para número maior que 100. Mensagem atual: " + mensagemNavegador);

            System.out.println("O navegador bloqueou o envio e mostrou a mensagem correta.");

        } catch (Exception e) {
            System.out.println("Mensagem de erro não encontrada: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Order(6)
    public void CT22_06_TentativaCadastroSomaMaiorQue100() {
        criarCurso();

        // Clicando em "Avaliações"
        WebElement tabAvaliacoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avaliações']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabAvaliacoes);
        tabAvaliacoes.click();
        System.out.println("Aba Avaliações acessada com sucesso!");

        //Dando nome à avaliação
        WebElement NomeAvaliacao;
        NomeAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
        String idNomeAvaliacao = NomeAvaliacao.getAttribute("for");
        WebElement NomeAvInput = driver.findElement(By.id(idNomeAvaliacao));
        NomeAvInput.sendKeys("Prova 1");

        System.out.println("Nome atribuído com Sucesso!");

        // Dando porcentagem à avaliação
        WebElement LabelPercentual;
        LabelPercentual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
        String idPercentual = LabelPercentual.getAttribute("for");
        WebElement InputPercentual = driver.findElement(By.id(idPercentual));
        InputPercentual.clear();
        InputPercentual.sendKeys("60");

        System.out.println("Percentual atribuído com Sucesso!");

        // Clicando no botão "Adicionar Avaliação"
        WebElement botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        System.out.println("Prova 1 criada com Sucesso!");

        //Dando nome à segunda avaliação
        NomeAvaliacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Nome da Avaliação')]")));
        idNomeAvaliacao = NomeAvaliacao.getAttribute("for");
        NomeAvInput = driver.findElement(By.id(idNomeAvaliacao));
        NomeAvInput.sendKeys("Prova 2");

        System.out.println("Nome atribuído com Sucesso!");

        // Dando porcentagem à segunda avaliação
        LabelPercentual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Percentual')]")));
        idPercentual = LabelPercentual.getAttribute("for");
        InputPercentual = driver.findElement(By.id(idPercentual));
        InputPercentual.clear();
        InputPercentual.sendKeys("50");

        System.out.println("Percentual atribuído com Sucesso!");

        System.out.println("Prova 2 criada com Sucesso!");

        // Clicando no botão "Adicionar Avaliação"
        botaoAdicionar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar Avaliação')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botaoAdicionar);
        botaoAdicionar.click();

        System.out.println("Botão 'Adicionar Avaliação' clicado com sucesso!");

        // Procura pelo alerta
        try {
            WebElement alertaErro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiAlert-message")));

            String textoAlerta = alertaErro.getText();
            System.out.println("Alerta encontrado! Texto: " + textoAlerta);

            boolean contemMensagem = textoAlerta.contains("excede 100%");
            assertTrue(contemMensagem, "Erro: O texto do alerta não corresponde. Texto atual: " + textoAlerta);
        } catch (Exception e) {
            System.out.println("Alerta não encontrado");
            throw e;
        }
    }

    //-----------Métodos Auxiliares-----------------------------------------
    /**
     * Módulo que executa o roteiro de criação de curso
     */
    String tituloCurso;

    private void criarCurso() {
        System.out.println("Iniciando o roteiro de criação de curso...");

        try {
            // --- Passo 1: Clicar no Ícone de Perfil (LOGADO) ---
            // (Já estamos logados, apenas clicamos no ícone de perfil)
            System.out.println("Clicando no ícone de perfil (logado)...");
            WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Configurações da Conta']")
            ));
            js.executeScript("arguments[0].click();", profileButton);

            // --- Passo 2: Clicar em "Gerenciamento de Cursos" ---
            System.out.println("Clicando em 'Gerenciamento de Cursos'...");
            WebElement gerenciamentoButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[normalize-space()='Gerenciamento de Cursos']")
            ));
            gerenciamentoButton.click();

            // --- Passo 3: Clicar em "Criar Novo Curso" ---
            // Espera a URL de gerenciamento carregar
            wait.until(ExpectedConditions.urlContains("/manage-courses"));
            System.out.println("Clicando em 'Criar Novo Curso'...");
            WebElement criarCursoButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Criar Novo Curso']")
            ));
            criarCursoButton.click();

            // --- Passo 4: Preencher o Formulário ---
            // Espera a URL de admin carregar
            wait.until(ExpectedConditions.urlContains("/adm-cursos"));
            System.out.println("Preenchendo formulário...");

            // Título (Método robusto para IDs dinâmicos)
            WebElement titleLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//label[contains(text(),'Título do Curso')]")
            ));
            String titleId = titleLabel.getAttribute("for");
            WebElement titleInput = driver.findElement(By.id(titleId));
            tituloCurso = "Curso Selenium " + System.currentTimeMillis();
            titleInput.sendKeys(tituloCurso);

            // Descrição (Método robusto para IDs dinâmicos)
            WebElement descLabel = driver.findElement(By.xpath("//label[contains(text(),'Descrição do Curso')]"));
            String descId = descLabel.getAttribute("for");
            WebElement descInput = driver.findElement(By.id(descId));
            descInput.sendKeys("Descrição gerada por teste automatizado.");

            // --- Passo 5: Salvar o Curso ---
            // O botão começa desabilitado. Esperamos ele ficar clicável.
            System.out.println("Aguardando botão 'Salvar Curso' ser habilitado...");
            WebElement salvarButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Salvar Curso']")
            ));
            salvarButton.click();
            System.out.println("Formulário submetido.");

            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='OK!']")));
            okButton.click();

            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close']")));
            closeButton.click();

            System.out.println(" Criação de Curso concluído com sucesso!");

        } catch (Exception e) {
            System.out.println("--- ERRO DURANTE A CRIAÇÃO DO CURSO ---");
            System.out.println("Mensagem: " + e.getMessage());
            e.printStackTrace(); // Mostra o erro detalhado
            assertTrue(false, "Falha durante a criação do curso.");
        }
    }

    /**
     * Verifica se o login foi bem-sucedido
     */
    private void verificarLogin() throws InterruptedException {
        System.out.println("Verificando se o login foi processado...");
        try {
            // 1. Espera forçada de 5s para o Firebase (JS assíncrono) processar o login
            System.out.println("Aguardando 5s para o Firebase SDK processar o login...");
            Thread.sleep(5000);

            // 2. Clicar no botão de perfil (logado)
            WebElement profileButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("button[aria-label='Configurações da Conta']")
            ));

            js.executeScript("arguments[0].click();", profileButton);
            System.out.println("Clicou no botão de perfil para abrir o menu.");

            // 3. Esperar o menu abrir e o botão "Sair" ficar VISÍVEL
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//li[normalize-space()='Sair']")
            ));

            System.out.println("Login validado com sucesso!");

            // Clica fora para fechar o menu (opcional)
            js.executeScript("document.body.click();");

        } catch (Exception e) {
            System.out.println("--- ERRO NA VALIDAÇÃO DO LOGIN ---");
            System.out.println("Causa provável: O token no 'FIREBASE_VALUE' expirou ou está incorreto.");
            System.out.println("Exceção: " + e.getMessage());

            assertTrue(false, "Falha na injeção de sessão do Firebase. O token pode estar expirado.");
        }
    }
}
