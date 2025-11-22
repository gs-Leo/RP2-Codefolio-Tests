
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

public class RFTrintaESeis {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration TIMEOUT = Duration.ofSeconds(30);
    private final String URL_BASE = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js; // Tornando o JS Executor global

    // --- 1. DADOS DO FIREBASE (PEGUE UM TOKEN NOVO!) ---
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
            // driver.quit();
        }
    }

    @Test
    public void CT36_01_RetornoModoNormal() {
        //1. CRIAR CURSO, ADICIONAR VÍDEO E ADICIONAR QUIZ
        criarCurso();
        adicionarVideo();
        adicionarQuizDeVideo();
        System.out.println("--- Iniciando CT-36.01: Retorno ao Modo Normal ---");

        //2.CLICAR EM CURSOS
        System.out.println("Tentando navegar para Cursos...");
        WebElement btnCursos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/listcurso'] | //*[text()='Cursos']")));
        js.executeScript("arguments[0].click();", btnCursos);
        System.out.println("Navegou para Cursos.");

        // 3. ENTRAR NO CURSO ESPECÍFICO
        System.out.println("Procurando curso: " + tituloCurso);

        WebElement elementoTitulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + tituloCurso + "')]")));

        WebElement btnComecarCurso = elementoTitulo.findElement(
                By.xpath("./following::button[contains(., 'Começar')][1]"));

        // Scroll para garantir que o botão apareça
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnComecarCurso);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        js.executeScript("arguments[0].click();", btnComecarCurso);
        System.out.println("Entrou no Curso.");

        // 4. INICIAR QUIZ GIGI
        System.out.println("Buscando botão para iniciar Quiz Gigi...");

        WebElement btnStartQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[name()='path' and @d='M5 13.18v4L12 21l7-3.82v-4L12 17zM12 3 1 9l11 6 9-4.91V17h2V9z']/ancestor::button")
        ));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnStartQuiz);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        js.executeScript("arguments[0].click();", btnStartQuiz);
        System.out.println("Quiz iniciado!");

        // 5. ATIVAR "PERGUNTA PERSONALIZADA" (Botão +)
        System.out.println("Procurando botão de Pergunta Personalizada...");
        WebElement btnPersonalizada;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        btnPersonalizada = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Pergunta Personalizada']")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnPersonalizada);
        js.executeScript("arguments[0].click();", btnPersonalizada);
        System.out.println("Botão (+) clicado com sucesso");

        System.out.println("Aguardando modo personalizado abrir...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        // 6. RETORNAR AO MODO NORMAL
        System.out.println("Clicando em Voltar ao Modo Normal...");

        WebElement btnVoltar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Voltar ao modo normal']")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnVoltar);
        js.executeScript("arguments[0].click();", btnVoltar);
        System.out.println("Botão (?) clicado com sucesso");

        // Se retornou, o botão de "Pergunta Personalizada" deve estar visível novamente
        try {
            boolean botaoMaisVisivel;
            botaoMaisVisivel = wait.until(ExpectedConditions.visibilityOf(btnPersonalizada)).isDisplayed();
            assertTrue(botaoMaisVisivel, "Erro: O sistema não retornou para a tela normal do Quiz.");
            System.out.println("CT-36.01 PASSOU");
        } catch (Exception e) {
            System.out.println("FALHA: O botão de pergunta personalizada não reapareceu.");
            throw e;
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

    private void adicionarQuizDeVideo() {
        System.out.println("Iniciando criação do Quiz de Vídeo...");

        // 1. Clicar na aba "Quiz"
        WebElement tabQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Quiz']")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", tabQuiz);
        tabQuiz.click();

        // 2. Clicar na sub-aba "Quizzes de Vídeos"
        WebElement tabQuizVideo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Quizzes de Vídeos']")
        ));
        tabQuizVideo.click();

        // 3. Clicar em "Adicionar Quiz" 
        WebElement btnAddQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Adicionar Quiz')]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnAddQuiz);
        btnAddQuiz.click();

        // 4. Clicar no "OK" do Modal de Sucesso
        WebElement btnOk = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='OK']")
        ));
        btnOk.click();

        // Pequena pausa para a lista atualizar
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        // 5. Clicar no botão de Editar 
        System.out.println("Tentando clicar no botão de editar o Quiz...");

        WebElement btnEditar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[descendant::*[contains(@data-testid, 'Edit')]])[1]")));

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnEditar);
        js.executeScript("arguments[0].click();", btnEditar); // Força o clique com JS
        System.out.println("Entrou na edição do Quiz.");

        // 6. Loop para adicionar 2 questões
        for (int i = 1; i <= 2; i++) {
            System.out.println("--- Criando questão " + i);

            // 1. Esperar o campo de pergunta estar visível e habilitado
            WebElement labelPergunta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Pergunta')]")));
            WebElement inputPergunta = driver.findElement(By.id(labelPergunta.getAttribute("for")));

            inputPergunta.clear();
            inputPergunta.sendKeys("Pergunta " + i + " do teste?");

            // 2. Preencher Opção 1
            WebElement labelOp1 = driver.findElement(By.xpath("//label[contains(text(), 'Opção 1')]"));
            WebElement inputOp1 = driver.findElement(By.id(labelOp1.getAttribute("for")));
            inputOp1.clear();
            inputOp1.sendKeys("Certa " + i);

            // 3. Preencher Opção 2
            WebElement labelOp2 = driver.findElement(By.xpath("//label[contains(text(), 'Opção 2')]"));
            WebElement inputOp2 = driver.findElement(By.id(labelOp2.getAttribute("for")));
            inputOp2.clear();
            inputOp2.sendKeys("Errada " + i);

            // 4. Clicar em Salvar Questão
            WebElement btnSalvarQuestao = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Salvar Questão')]")
            ));

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnSalvarQuestao);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            js.executeScript("arguments[0].click();", btnSalvarQuestao);

            System.out.println("Clicou em salvar questão " + i);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
        }

        // 7. Salvar Curso Final
        System.out.println("Salvando o curso completo...");
        WebElement btnSalvarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Salvar Curso')]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnSalvarCurso);
        btnSalvarCurso.click();

        // Lidar com modais finais se houver (OK/Close)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='OK!']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close']"))).click();
        } catch (Exception e) {
            // Ignora se não aparecer
        }

        System.out.println("Quiz criado e salvo com sucesso!");
    }
}
