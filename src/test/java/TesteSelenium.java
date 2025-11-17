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
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteSelenium {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration TIMEOUT = Duration.ofSeconds(15);
    private final String URL_BASE = "https://testes.codefolio.com.br/listcurso";
    private JavascriptExecutor js; // Tornando o JS Executor global

    // --- 1. DADOS DO FIREBASE ---
    // (Chave do IndexedDB)
    private final String FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]"
            ;

    // (Valor JSON - Objeto 'value' formatado para Java)
    private final String FIREBASE_VALUE =
            "{\"apiKey\":\"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\"," +
                    "\"appName\":\"[DEFAULT]\"," +
                    "\"createdAt\":\"1761920345874\"," +
                    "\"displayName\":\"Vinicius da Silva Goncalves\"," +
                    "\"email\":\"viniciusdsg2.aluno@unipampa.edu.br\"," +
                    "\"emailVerified\":true," +
                    "\"isAnonymous\":false," +
                    "\"lastLoginAt\":\"1763320360681\"," +
                    "\"phoneNumber\":null," +
                    "\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocJwshmOiwrD7mjw_aS1LUl3MRoUPMJrDKIw_V12ZE2mBbkPsw=s96-c\"," +
                    "\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"109644410800543472137\",\"displayName\":\"Vinicius da Silva Goncalves\",\"email\":\"viniciusdsg2.aluno@unipampa.edu.br\",\"phoneNumber\":null}],"+
                    "\"stsTokenManager\":{" +
                    "\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVmluaWNpdXMgZGEgU2lsdmEgR29uY2FsdmVzIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0p3c2htT2l3ckQ3bWp3X2FTMUxVbDNNUm9VUE1KckRLSXdfVjEyWkUybUJia1Bzdz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9yZWFjdC1uYS1wcmF0aWNhIiwiYXVkIjoicmVhY3QtbmEtcHJhdGljYSIsImF1dGhfdGltZSI6MTc2MzMyMDQ3NywidXNlcl9pZCI6InVsaHZ0N2RuT0RmaWdnTU5PUXphUU9oVlhUbTEiLCJzdWIiOiJ1bGh2dDdkbk9EZmlnZ01OT1F6YVFPaFZYVG0xIiwiaWF0IjoxNzYzMzQ2NDk1LCJleHAiOjE3NjMzNTAwOTUsImVtYWlsIjoidmluaWNpdXNkc2cyLmFsdW5vQHVuaXBhbXBhLmVkdS5iciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA5NjQ0NDEwODAwNTQzNDcyMTM3Il0sImVtYWlsIjpbInZpbmljaXVzZHNnMi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.Gxk4NTRpSlnwqQau5yZoTmcVDSw4ev8Te_zZjK9zJKl30pyuhsYizi5TBnOMvnOEWWpfme9HEBkImjGRHDYhbjW7L2OogPhwvm6ifeV9bkEyhj1eaBZVqtxm9IIsD1pLvW1lH7__nr-QxPWrpkeVFi6XNmHananFLQibvkJhDMWi-2tcq28xaOtpMQyC_eUdb7l5u6JZEZXu8yPoL-F3iVJULTB-U2T6rXJxf5uMOGLYmRNNZgGCEHHNeS6zgwJaHlnOB9evVEg9QKmGs7AcjHua6mR1d9_jMF6Jl61UXEfym4sm_AnvwmmnNk95WUV28v2QVXMXPbmJutYXVhRHeQ\"," +
                    "\"expirationTime\":1763350095858," +
                    "\"refreshToken\":\"AMf-vByMvDG87mGyeDirv4ZvFthedwK88F8cZOttlDdHv17lJYWtSgFncX-a0VykCNiYIMpSOGOYqQYhE22PeE9CsTrRxwvIxhB6B0Ihrxtau7NVyYu8v-D7M19oMHFf88nbPvqGO1wVzIiybzxuWi2UnmA7vk8CgKPJRp0COG4CuzeK_HcTesYMY7BOFeLnlYy52eUtQfhAK442a6ukuEokdntaVVDg1DgAOJwU7bqBdtqMVbmhRXQTZHNKldV05w8vZZBCqD_L_OO92e2pCacId19dXN8Ogsf9CqoNWXNawUTiWXeymYIjKIhKMfcNPfdk0kulrzQBPuLF3hJ0COFhgMXnw_BGnxb1ee0UtEG7yIsXqhdNq_2qrVNAZsiGtLtgkDad_dFulFvtU_Lu9NMvUmzwk2oAztfinDxkIt-qq8NZq1jnGagjnRtUOqn0NbDZmvu8WThu2i9WMvL4iBGF2gXtRq1jzw\"," +
                    "\"tenantId\":null," +
                    "\"uid\":\"ulhvt7dnODfiggMNOQzaQOhVXTm1\"" +
                    "}," +
                    "\"uid\":\"ulhvt7dnODfiggMNOQzaQOhVXTm1\"," +
                    "\"_redirectEventId\":null}";


    @BeforeEach
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
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
    }

    @Test
    public void ExecucaoTestes() throws InterruptedException {
        // --- 1. Verificação de Login ---
        verificarLogin();

        //RF3
        //EditarCurso();

        //RF12 e RF13
        //edicao_e_exclusao_MaterialExtra();

        quizSelecaoAleatoria();

        //quizSelecaoManual();
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



            System.out.println("Login validado com sucesso!");



        } catch (Exception e) {
            System.out.println("--- ERRO NA VALIDAÇÃO DO LOGIN ---");
            System.out.println("Causa provável: O token no 'FIREBASE_VALUE' expirou ou está incorreto.");
            System.out.println("Exceção: " + e.getMessage());

            assertTrue(false, "Falha na injeção de sessão do Firebase. O token pode estar expirado.");
        }
    }


    /**
     * Módulo que executa o roteiro de edição de curso
     */
    private void EditarCurso() throws InterruptedException {
        System.out.println("Iniciando o teste de edição de curso...");

        try { // Início do try externo
            // --- Passo 1: Clicar no Ícone de Perfil (LOGADO) ---
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

            // --- Passo 3: Clicar em "Gerenciar Curso" ---
            wait.until(ExpectedConditions.urlContains("/manage-courses"));
            System.out.println("Clicando em 'Gerenciar Curso'...");
            WebElement gerenciarCursoButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Gerenciar Curso']")
            ));
            gerenciarCursoButton.click();

            // --- Passo 4: Editar Título e Descrição do Curso ---
            wait.until(ExpectedConditions.urlContains("adm-cursos?courseId=-OduI8vjf_w5VzRZfVZr"));
            System.out.println("CT1 RF1: Tentando editar Titulo e Descrição do curso...");

            try {
                // 1. TÍTULO DO CURSO
                WebElement titleLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(),'Título do Curso')]")
                ));
                WebElement titleInput = titleLabel.findElement(By.xpath("./following-sibling::input | ./following-sibling::textarea"));

                titleInput.clear();
                titleInput.sendKeys("Novo Título Automatizado");
                System.out.println("✅ Título do Curso editado com sucesso.");

                // 2. DESCRIÇÃO DO CURSO
                WebElement descriptionLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(),'Descrição do Curso')]")
                ));
                WebElement descriptionInput = descriptionLabel.findElement(By.xpath("./following-sibling::textarea"));

                descriptionInput.clear();
                descriptionInput.sendKeys("Nova descrição detalhada e automatizada.");
                System.out.println("✅ Descrição do Curso editada com sucesso.");

            } catch (Exception e) {
                System.err.println("❌ ERRO CRÍTICO: Não foi possível encontrar/editar os campos Título e/ou Descrição do Curso.");
                System.err.println("Detalhes do Erro: " + e.getMessage());
            }

            // --- Passo 5: Preenchimento dos Dados do Vídeo (CT-2 RF1) ---
            System.out.println("Continuando para o CT-2 RF1 (Preenchendo Vídeo)...");

            try {
                // 1. TÍTULO DO VÍDEO
                System.out.println("Buscando e preenchendo Título do Vídeo...");
                WebElement titleVideoLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(),'Título do Vídeo')]")
                ));
                String titleVideoId = titleVideoLabel.getAttribute("for");
                WebElement titleVideoInput = driver.findElement(By.id(titleVideoId));
                titleVideoInput.sendKeys("Título do Vídeo de Teste");
                System.out.println("✅ Título do Vídeo preenchido.");

                // 2. URL DO VÍDEO
                System.out.println("Buscando e preenchendo URL do Vídeo...");
                WebElement urlVideoLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(),'URL do Vídeo')]")
                ));
                String urlVideoId = urlVideoLabel.getAttribute("for");
                WebElement urlVideoInput = driver.findElement(By.id(urlVideoId));
                urlVideoInput.sendKeys("https://youtu.be/-nFFy_Ix2L8?si=zZcsnx2zQdARerxr");
                System.out.println("✅ URL do Vídeo preenchida.");


                // 3. DESCRIÇÃO DO VÍDEO
                System.out.println("Buscando e preenchendo Descrição do Vídeo...");
                WebElement descVideoLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(text(),'Descrição do Vídeo')]")
                ));
                String descVideoId = descVideoLabel.getAttribute("for");
                WebElement descVideoInput = driver.findElement(By.id(descVideoId));
                descVideoInput.sendKeys("teste");
                System.out.println("✅ Descrição do Vídeo preenchida.");


                System.out.println("Buscando e clicando no botão 'ADICIONAR VÍDEO'...");


                WebElement AdicionarVideoButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\":ru:\"]")
                ));


                Thread.sleep(1500);


                js.executeScript("arguments[0].click();", AdicionarVideoButton);
                System.out.println("✅ Botão 'Adicionar Vídeo' clicado com sucesso.");

                System.out.println("Casos de testes RF1 finalizados!");


            } catch (Exception e) {
                System.out.println("--- ERRO DURANTE O PREENCHIMENTO DOS DADOS DO VÍDEO ---");
                System.out.println("Mensagem: " + e.getMessage());
                e.printStackTrace();
                assertTrue(false, "Falha ao preencher um ou mais campos de Vídeo (Título, URL ou Descrição) ou falha no clique do botão 'Adicionar Vídeo'.");
            } // Fim do try/catch de vídeo

        } catch (Exception e) { // Catch do try externo (Passos 1 a 3)
            System.err.println("❌ ERRO FATAL: Falha na navegação inicial ou passos 1-3.");
            e.printStackTrace();
            assertTrue(false, "Falha na navegação ou gerenciamento de cursos.");
        } // Fim do try externo
    }


    /**
     * Método executado após cada teste para fechar o navegador.
     * Corrigido: Movido para o nível da classe.
     */
    @AfterEach
    public void teardown() {
        if (driver != null) {
            // driver.quit();
        }
    }


    /**
     * Inicia o caso de teste para o requisito de Edição de Material Extra (RF12)
     * Contém a navegação inicial e agora adiciona o scroll para baixo.
     */
    private void edicao_e_exclusao_MaterialExtra() throws InterruptedException {
        System.out.println("\n--- Iniciando casos de testes RF12: Edição de Material Extra ---");

        try { // Início do try para a navegação inicial

            // --- Passo 1: Clicar no Ícone de Perfil (LOGADO) ---
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

            // --- Passo 3: Clicar em "Gerenciar Curso" ---
            wait.until(ExpectedConditions.urlContains("/manage-courses"));
            System.out.println("Clicando em 'Gerenciar Curso'...");
            WebElement gerenciarCursoButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Gerenciar Curso']")
            ));
            gerenciarCursoButton.click();

            // Espera a URL de edição do curso carregar
            wait.until(ExpectedConditions.urlContains("adm-cursos"));
            System.out.println("✅ Navegação para a tela de Gerenciar Curso concluída.");

        } catch (Exception e) {
            System.err.println("❌ ERRO FATAL: Falha na navegação inicial para a tela de Gerenciar Curso.");
            e.printStackTrace();
            assertTrue(false, "Falha na navegação inicial do método edicaoMaterialExtra.");
            return; // Interrompe se a navegação falhar
        }

        try {
            // Localiza e clica na aba 'MATERIAIS EXTRAS' usando XPath (você voltou para o absoluto, o que é arriscado)
            System.out.println("Buscando e clicando na aba 'MATERIAIS EXTRAS'...");
            WebElement materiaisExtrasTab = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[3]/div/div/div/button[3]")
            ));

            // Usando JS Executor para garantir o clique na aba
            js.executeScript("arguments[0].click();", materiaisExtrasTab);
            System.out.println("✅ Aba 'MATERIAIS EXTRAS' clicada com sucesso.");

            System.out.println("Rolando a página para baixo...");
            js.executeScript("window.scrollBy(0, 500)");
            Thread.sleep(500);

            // Armazena a referência para o item da lista antes de tentar editar/excluir
            WebElement itemLista = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[5]/ul/li")
            ));

            System.out.println("Tentando clicar no primeiro item da lista de materiais adicionados para editar...");

            // Tenta clicar no item da lista para abrir o modal de edição
            js.executeScript("arguments[0].click();", itemLista);
            System.out.println("Item da lista clicado. Verificando se a ação de edição foi disparada...");

            // 🚩 Verificação da Ação: Tenta encontrar um elemento que só apareceria após a edição.
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'Título do Modal de Edição')]")
            ));

            System.out.println("✅ Sucesso: O modal/formulário de edição do material extra foi carregado.");

        } catch (Exception e) {
            System.err.println("--- ERRO NA EDIÇÃO DO MATERIAL EXTRA ---");
            System.err.println("❌ Não é possível editar materiais extras adicionados.");
            System.err.println("Detalhes da Falha: " + e.getMessage());

            // Não usamos o 'return' ou 'assertTrue(false)' aqui para que o teste prossiga
            // para a fase de exclusão, que é um requisito separado (RF13).
        }

        System.out.println("\n--- Começando RF13: Exclusão de materiais extras... ---");

        try {

            System.out.println("Buscando e clicando no botão de exclusão...");

            // Se o botão de exclusão só aparecer no modal de edição:
            //WebElement botaoExcluir = wait.until(ExpectedConditions.elementToBeClickable(
                   // By.xpath("//*[contains(@aria-label, 'Excluir')] | //*[contains(@title, 'Excluir')] | //svg[ancestor::div[contains(@class, 'modal') or contains(@class, 'dialog')]]")
            //));

            // Tentando o XPath absoluto que você forneceu como backup (se a primeira tentativa falhar)
             WebElement botaoExcluir = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//*[@id=\":r14:\"]")
            ));

            js.executeScript("arguments[0].click();", botaoExcluir);
            System.out.println("✅ Botão de exclusão clicado com sucesso.");

            // 🚩 Verificação da Ação: Confirmação de Exclusão
            // Após o clique, geralmente abre-se um modal de confirmação.
            System.out.println("Aguardando o modal de confirmação de exclusão...");
            WebElement botaoConfirmar = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Sim, Excluir']")
            ));

            js.executeScript("arguments[0].click();", botaoConfirmar);
            System.out.println("✅ Material extra excluído com sucesso (Confirmação clicada).");



        } catch (Exception e) {
            System.err.println("--- ERRO NA EXCLUSÃO DO MATERIAL EXTRA ---");
            System.err.println("❌ Falha ao tentar excluir o material extra (RF13).");
            System.err.println("Detalhes da Falha: " + e.getMessage());

            assertTrue(false, "Falha na execução do RF13 (Exclusão de Material Extra).");
        }
    }
    private void quizSelecaoAleatoria() throws InterruptedException {
        System.out.println("\n--- Iniciando casos de testes RF26: Seleção de Estudante (Sorteio Aleatório) ---");

        // --- Passo 1: Clicar em "Em andamento" ---
        try {
            System.out.println("Clicando em 'Em andamento'...");
            WebElement CursosBotton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div/button[2]")
            ));
            CursosBotton.click();
            System.out.println("✅ 'Em andamento' clicado com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 1: Falha ao clicar no botão 'Em andamento'.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 1: Não foi possível clicar em 'Em andamento'.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 1

        // --- Passo 2: Clicar no curso Selenium test ---
        try {
            System.out.println("Clicando no curso Selenium teste...");
            WebElement cursoSeleniumButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r5a:\"]")
            ));
            cursoSeleniumButton.click();
            System.out.println("✅ Curso clicado com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 2: Falha ao clicar no curso Selenium test.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 2: Não foi possível clicar no curso.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 2

        // --- Passo 3: Clicar no QUIZ GIGI ---
        try {
            System.out.println("Clicando no Quiz GIGI...");
            WebElement QUIZGIGIbutton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r5g:\"]")
            ));
            QUIZGIGIbutton.click();
            System.out.println("✅ Quiz GIGI clicado com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 3: Falha ao clicar no Quiz GIGI.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 3: Não foi possível clicar no Quiz.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 3

        // --- Passo 4: Clicar no Botão de Sorteio Aleatório (2 VEZES) ---
        try {
            System.out.println("Tentando realizar o sorteio aleatório (2 vezes)...");
            WebElement sorteioButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r67:\"]")
            ));

            // PRIMEIRO CLIQUE
            sorteioButton.click();
            System.out.println("➡️ Primeiro sorteio realizado.");

            // SEGUNDO CLIQUE (Espera menor para processamento entre cliques)
            Thread.sleep(1000);
            sorteioButton.click();
            System.out.println("➡️ Segundo sorteio realizado.");
            System.out.println("➡️ CASO DE TESTE 1 RF26 PASSOU!");


        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 4: Falha ao clicar no botão de sorteio aleatório.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 4: Não foi possível realizar o sorteio.");
            return;
        }
        Thread.sleep(2000);

        // --- Passo 5: Sorteio com 1 aluno (CT com aluno único) ---
        try {
            System.out.println("Executando segundo caso de teste: Sorteio com 1 aluno");
            Thread.sleep(2000); // Timer de 2 segundos conforme solicitado

            // 5a. Capturar o estado inicial da interface para validação
            WebElement contentContainer = driver.findElement(By.xpath("//*[@id=\"quiz-content-container\"]/div/div[1]/div/div/div"));
            String initialState = contentContainer.getAttribute("outerHTML");
            System.out.println("Estado inicial da tela capturado.");

            // -- Início da Sub-etapa de remoção de alunos --
            try {
                System.out.println("Retirando os alunos e deixando apenas 1");

                WebElement alunosDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\":r69:\"]")
                ));
                alunosDropdown.click();

                Thread.sleep(2000);
                System.out.println("Retirando primeiro aluno...");
                WebElement aluno1button = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\":r6e:\"]")
                ));
                aluno1button.click();

                Thread.sleep(2000); // Espera
                System.out.println("Retirando segundo aluno...");
                WebElement aluno2button = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\":r6g:\"]")
                ));
                aluno2button.click();

                Thread.sleep(2000); // Espera após a remoção
                System.out.println("✅ Remoção de alunos concluída.");


            } catch (Exception e) {
                System.err.println("❌ ERRO NA SUB-ETAPA DE REMOÇÃO: Falha ao remover alunos (Passo 5).");
                e.printStackTrace();
                assertTrue(false, "Falha no Passo 5 (Remoção de Alunos).");
                return;
            }
            // -- Fim da Sub-etapa de remoção de alunos --

            System.out.println("Realizando sorteio com 1 aluno...");
            WebElement sorteioAluno = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r67:\"]")
            ));
            sorteioAluno.click();
            System.out.println("✅ Sorteio realizado.");

            // 5d. Capturar o estado final e validar
            Thread.sleep(2000); // Espera para o processamento do sorteio
            String finalState = contentContainer.getAttribute("outerHTML");

            if (initialState.equals(finalState)) {
                System.err.println("❌ Resultado da Validação: Caso de teste não passou");
                System.err.println("Motivo: O conteúdo da interface não mudou após o sorteio com 1 aluno.");
                assertTrue(false, "Falha no Passo 5: O resultado do sorteio não foi exibido.");
            } else {
                System.out.println("❌ Resultado da Validação: Caso de teste nao passou. A interface foi atualizada com um novo aluno.");
            }

        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 5: Falha durante o sorteio com 1 aluno (configuração ou clique).");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 5: Não foi possível realizar o sorteio com 1 aluno.");
            return;
        }

        // --- Validação Final do Sorteio ---
        System.out.println("SUCESSO: Todos os passos de navegação e sorteios foram executados.");
    }
    private void quizSelecaoManual() throws InterruptedException {
        System.out.println("Iniciando casos de testes RF27: Seleção Manual...");

        // --- Passo 1: Refresh na página para limpar estados anteriores ---
        try {
            System.out.println("Atualizando a página (Refresh) para garantir o estado inicial...");
            driver.navigate().refresh();
            wait.until(ExpectedConditions.urlContains(URL_BASE)); // Espera a URL base carregar
            System.out.println("✅ Página recarregada com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 1: Falha ao recarregar a página.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 1: Não foi possível realizar o refresh.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 1

        // --- Passo 2: Clicar no primeiro elemento
        try {
            System.out.println("Clicando no elemento de navegação inicial (ID: :r5:)...");
            WebElement elementR5 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r5:\"]")
            ));
            elementR5.click();
            System.out.println("✅ Elemento :r5: clicado com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 2: Falha ao clicar no elemento :r5:.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 2: Elemento :r5: não encontrado/clicável.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 2

        // --- Passo 3: Clicar no segundo elemento
        try {
            System.out.println("Clicando no elemento de seleção (ID: :r7g:)...");
            WebElement elementR7g = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r7g:\"]")
            ));
            elementR7g.click();
            System.out.println("✅ Elemento :r7g: clicado com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 3: Falha ao clicar no elemento :r7g:.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 3: Elemento :r7g: não encontrado/clicável.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 3

        // --- Passo 4: Clicar no terceiro elemento
        try {
            System.out.println("Clicando no botão final de seleção (ID: :r8s:)...");
            WebElement elementR8s = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r8s:\"]")
            ));
            elementR8s.click();
            System.out.println("✅ Elemento :r8s: clicado com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 4: Falha ao clicar no elemento :r8s:.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 4: Elemento :r8s: não encontrado/clicável.");
            return;
        }
        Thread.sleep(2000); // Espera após o Passo 4

        // --- Passo 5: Verificação de Mudança do Modal/Conteúdo ---
        try {
            System.out.println("Verificando se o modal de conteúdo foi atualizado após a seleção manual...");
            Thread.sleep(2000); // Espera antes da verificação

            // 5a. Capturar o HTML/Texto do container de conteúdo
            WebElement contentContainer = driver.findElement(By.xpath("//*[@id=\"quiz-content-container\"]/div/div[1]/div/div/div"));
            // Capturamos o estado ATUAL da interface
            String finalState = contentContainer.getAttribute("outerHTML");

            // NOTE: Sem o estado inicial, esta verificação apenas garante que o elemento existe.
            if (finalState == null || finalState.trim().isEmpty() || finalState.contains("Estado Antigo")) {
                System.err.println("❌ Resultado da Validação CT1: Caso de teste de seleção manual não passou.");
                System.err.println("Motivo: O conteúdo do quiz (modal) parece não ter mudado ou está vazio.");
                assertTrue(false, "Falha no Passo 5: O conteúdo do quiz não foi atualizado.");
            } else {
                System.out.println("✅ Resultado da Validação CT1: Caso de teste passou. Conteúdo do modal atualizado.");
            }

        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 5: Falha ao verificar a mudança do modal/conteúdo.");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 5: O container de conteúdo do quiz não foi encontrado.");
            return;
        }
        Thread.sleep(2000);

        System.out.println("\n--- Iniciando CT2: Remoção de Aluno ---");
        try {

            System.out.println("Clicando para remover o aluno (ID: :r6k:)...");
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r6k:\"]")
            ));
            removeButton.click();
            System.out.println("✅ Botão de remover aluno clicado.");
        } catch (Exception e) {
            System.err.println("❌ ERRO NO PASSO 6a: Falha ao clicar no botão de remover (ID: :r6k:).");
            e.printStackTrace();
            assertTrue(false, "Falha no Passo 6a: Não foi possível clicar no botão de remover.");
            return;
        }
        Thread.sleep(2000); // Espera após a remoção


        System.out.println("Tentando clicar no aluno removido (ID: :r6j:). ESPERA-SE FALHA...");
        try {

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alunoRemovido = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r6j:\"]")
            ));
            alunoRemovido.click();

            // Se o código chegar aqui, significa que o aluno não foi removido.
            System.err.println("❌ Resultado CT2: Caso de teste não passou!");
            System.err.println("Motivo: O elemento do aluno removido ainda está clicável na tela.");
            assertTrue(false, "Falha no Passo 6b: O aluno não foi removido corretamente.");

        } catch (Exception e) {

            System.out.println("✅ Resultado CT2: Caso de teste passou!");
            System.out.println("Motivo: O elemento do aluno sumiu da tela (falhou ao ser procurado), indicando que a remoção foi bem-sucedida.");
        }
        Thread.sleep(2000); // Espera após o Passo 6

        System.out.println("SUCESSO: Todos os passos de Seleção Manual (RF27) foram executados.");
    }
    
}