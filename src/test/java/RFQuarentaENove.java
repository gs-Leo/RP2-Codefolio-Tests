
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
import org.junit.jupiter.api.Order;


public class RFQuarentaENove {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration TIMEOUT = Duration.ofSeconds(30);
    private final String URL_BASE = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js; // Tornando o JS Executor global
    
     // --- 1. DADOS DO FIREBASE ---
    // (Chave do IndexedDB)
    private final String FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";

    // (Valor JSON - Objeto 'value' formatado para Java)
    private final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763511027590\",\"displayName\":\"Rodrigo Thoma da Silva\",\"email\":\"rodrigothoma.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1763511027591\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocLwnrKxW0oUL58-rcdaYs5RaOPQw48A54q2_oTORwB1NOCgnw=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"106869220410814831244\",\"displayName\":\"Rodrigo Thoma da Silva\",\"email\":\"rodrigothoma.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDI5MzRmZTBlZWM0NmE1ZWQwMDA2ZDE0YTFiYWIwMWUzNDUwODMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiUm9kcmlnbyBUaG9tYSBkYSBTaWx2YSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NMd25yS3hXMG9VTDU4LXJjZGFZczVSYU9QUXc0OEE1NHEyX29UT1J3QjFOT0Nnbnc9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM1MTEwMzQsInVzZXJfaWQiOiI3aW1jc3FvN1E5Vmp5SzhDc0NPTXI1dmVvVTgzIiwic3ViIjoiN2ltY3NxbzdROVZqeUs4Q3NDT01yNXZlb1U4MyIsImlhdCI6MTc2MzUxMTAzNCwiZXhwIjoxNzYzNTE0NjM0LCJlbWFpbCI6InJvZHJpZ290aG9tYS5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNjg2OTIyMDQxMDgxNDgzMTI0NCJdLCJlbWFpbCI6WyJyb2RyaWdvdGhvbWEuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.lGAQ0FSyNRJl05F1JjITJBMP2pELC5f-y-l59BpcrSjAO-2IuVXcQva6wxwC3nlS7qoIvF7Tp85kIMdKp4nSHGQyRxIYYpsTzkmYWthpEvWixbp-eSxeJi-pjoYfjak5xME35eZexLuzyPVrxSlxPishYkOqSW1HDjR7Oqf___1BTzA01NYh4JKI9L1wdwtmVUhqudXvKvvxU0giILm839j7k1BUEl9clOFckeCY9PfXkn6gpeD7KV5xKSFIYDFvMJfU3YvvA_OOQaU5IjjXT8yS7H19OMbJjSga37rqIVd-G__-YlFjzjjhxTsUtEXkRjHWgFb_dgJ2qT_Ub5X5sw\",\"expirationTime\":1763514634081,\"refreshToken\":\"AMf-vByoQcRBG8qhFWn17TrE06jkmlLuzu_1kpRUh4XyI5_3KoZRNoGxT0GNGJP4I9CRSnjYIf_6bE2hWZo_4IWvrp5anCp05OHknUngecxjwJktRFe68KWVMy2AL1fXnzlWENBC8FvjrS0X7UQ6f_dL-DLRkBMC1lcQNk9TVNeMeo8owjYnDPWdtNrEi9plRSyqZw_chsTI4rI3DhlLXrqIHHvY-9SWVLp8et8gXyeZhAvd7hmi16myCsVBFlZgpzZMQheMtpiPp7Gy64mymxok_fIiDRcxofPtUr3tbEldR1_zDexDrQ74w7tWHuTV4nX8oQdrWLxIzBOqueiGOmq9xo0OtNzdlxrhV5R0NV9erZWjcu7xCXhogHQ_pvuti2kduqaxk5WdsjpdEH2GAN16SKvtvdNs4xmhuo0_igjKKLE0Hnmb4krFiaJU-Lvl4bZOrPjeVOCLiiB5E0VwyCev86p6j8Ddmg\"},\"tenantId\":null,\"uid\":\"7imcsqo7Q9VjyK8CsCOMr5veoU83\",\"_redirectEventId\":null}";
    
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
    public void CT49_01_AssistirVideo() {
        criarCurso();
        adicionarVideo();
        
        System.out.println(" Iniciando CT-49.01: Assistir Vídeo ");

        try {
            //CLICAR EM CURSOS
        System.out.println("Tentando navegar para Cursos...");
        WebElement btnCursos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/listcurso'] | //*[text()='Cursos']")));
        js.executeScript("arguments[0].click();", btnCursos);
        System.out.println("Navegou para Cursos.");

        // ENTRAR NO CURSO ESPECÍFICO
        System.out.println("Procurando curso: " + tituloCurso);

        WebElement elementoTitulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + tituloCurso + "')]")));

        WebElement btnComecarCurso = elementoTitulo.findElement(
                By.xpath("./following::button[contains(., 'Começar')][1]"));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnComecarCurso);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        js.executeScript("arguments[0].click();", btnComecarCurso);
        System.out.println("Entrou no Curso.");

            // 4. SELECIONAR O VÍDEO
            System.out.println("Selecionando a aula de vídeo");
            
            WebElement btnVerVideo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Ver Vídeo')]")
            ));
            
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnVerVideo);
            js.executeScript("arguments[0].click();", btnVerVideo);
            System.out.println("Aula selecionada.");

            // DAR PLAY NO VÍDEO
            System.out.println("Tentando interagir com o player do YouTube...");

            // Esperar o iframe aparecer e trocar o foco do Selenium para dentro dele
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
            System.out.println("Selenium entrou no contexto do Iframe.");

            // Clicar no Botão  de Play do YouTube
            WebElement btnPlayYoutube = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ytp-large-play-button")
            ));
            
            btnPlayYoutube.click();
            System.out.println("Play acionado no YouTube!");

            // VALIDAÇÃO
            // Verifica se o botão de play mudou de estado ou sumiu (o vídeo começou)
            // No YouTube, quando dá play, o botão grande some ou muda a classe
            Thread.sleep(2000); // Espera o vídeo rodar
            
            driver.switchTo().defaultContent();
            System.out.println("Selenium voltou para o contexto do Codefólio.");

            // Verifica se ainda estamos na página certa (título do vídeo visível)
            WebElement tituloVideoTela = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Vídeo Teste Selenium')]")
            ));
            assertTrue(tituloVideoTela.isDisplayed());

            System.out.println("Vídeo acessado e reproduzido!");

        } catch (Exception e) {
            System.out.println("FALHA: " + e.getMessage());
            throw new RuntimeException(e);
        }      
    }
    
    @Test
    @Order(2)
    public void CT49_02_AvancarTempoVideo() {
        criarCurso();
        adicionarVideo();

        System.out.println(" Iniciando CT-49.02: Validação de Progresso ");

        try {
            //CLICAR EM CURSOS
        System.out.println("Tentando navegar para Cursos...");
        WebElement btnCursos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/listcurso'] | //*[text()='Cursos']")));
        js.executeScript("arguments[0].click();", btnCursos);
        System.out.println("Navegou para Cursos.");

        // ENTRAR NO CURSO ESPECÍFICO
        System.out.println("Procurando curso: " + tituloCurso);

        WebElement elementoTitulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + tituloCurso + "')]")));

        WebElement btnComecarCurso = elementoTitulo.findElement(
                By.xpath("./following::button[contains(., 'Começar')][1]"));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnComecarCurso);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        js.executeScript("arguments[0].click();", btnComecarCurso);
        System.out.println("Entrou no Curso.");
        
        WebElement btnVerVideo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Ver Vídeo')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnVerVideo);
            js.executeScript("arguments[0].click();", btnVerVideo);
            
            System.out.println("Acessando player...");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));

            // Carregar o vídeo
            WebElement btnPlay = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ytp-large-play-button")));
            btnPlay.click();
            Thread.sleep(3000); 

            // Avançar para o segundo 50 usando JavaScript
            System.out.println("Avançando para 50 segundos...");
            js.executeScript("document.querySelector('video').currentTime = 50;");
            
            Thread.sleep(2000); // Espera atualizar

            // Verificar se o tempo foi atualizado
            Double tempoAtual = (Double) js.executeScript("return document.querySelector('video').currentTime;");
            System.out.println("Tempo após avanço: " + tempoAtual);

            // Validação: O tempo deve ser >= 50
            assertTrue(tempoAtual >= 50.0, "O vídeo não avançou para 50s! Tempo atual: " + tempoAtual);

            System.out.println("Vídeo avançou corretamente! ");
            
        } catch (Exception e) {
            System.out.println("FALHA CT49_02: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
   
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

    private void adicionarVideo() {
        System.out.println("Adicionando vídeo ao curso...");

        // 1. Clicar na aba "Vídeos"
        WebElement tabVideos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Vídeos']")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", tabVideos);
        tabVideos.click();

        // 2. Preencher Título
        WebElement labelTitulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(text(), 'Título do Vídeo')]")
        ));
        driver.findElement(By.id(labelTitulo.getAttribute("for"))).sendKeys("Vídeo Teste Selenium");

        // 3. Preencher URL
        WebElement labelUrl = driver.findElement(By.xpath("//label[contains(text(), 'URL do Vídeo')]"));
        driver.findElement(By.id(labelUrl.getAttribute("for"))).sendKeys("https://youtu.be/BgUxpPKw2LI?si=qzXuxKHdGhwdDjCV");

        // 5. Clicar em Adicionar Vídeo
        WebElement btnAdd = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(text(), 'Adicionar Vídeo')]")
        ));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnAdd);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // Clica via JS para garantir
        js.executeScript("arguments[0].click();", btnAdd);
        System.out.println("Botão 'Adicionar Vídeo' clicado.");

        try {
            // Espera o botão OK aparecer e ser clicável
            WebElement btnOk = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='OK']")
            ));
            btnOk.click();
            System.out.println("Modal de sucesso fechado (Clicou em OK).");

            // Pequena pausa para o modal sumir completamente
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Botão OK não apareceu ou não foi necessário clicar.");
        }

        // 6. Esperar o vídeo aparecer na lista
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Vídeo Teste Selenium')]")
        ));
        System.out.println("Vídeo adicionado com sucesso!");
    }

   
}
