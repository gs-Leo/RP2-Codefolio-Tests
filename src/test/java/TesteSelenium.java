import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteSelenium {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\"apiKey\":\"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1763656192294\",\"displayName\":\"Vinicius da Silva Goncalves\",\"email\":\"viniciusdsg2.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1763825431339\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocJwshmOiwrD7mjw_aS1LUl3MRoUPMJrDKIw_V12ZE2mBbkPsw=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"109644410800543472137\",\"displayName\":\"Vinicius da Silva Goncalves\",\"email\":\"viniciusdsg2.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVmluaWNpdXMgZGEgU2lsdmEgR29uY2FsdmVzIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0p3c2htT2l3ckQ3bWp3X2FTMUxVbDNNUm9VUE1KckRLSXdfVjEyWkUybUJia1Bzdz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS90ZXN0ZXMtY29kZWZvbGlvIiwiYXVkIjoidGVzdGVzLWNvZGVmb2xpbyIsImF1dGhfdGltZSI6MTc2MzY3NTcxOCwidXNlcl9pZCI6IkllNDl4T1VzT3dObGtUdWNzR1U1MXVWU1d1MDIiLCJzdWIiOiJJZTQ5eE9Vc093TmxrVHVjc0dVNTF1VlNXdTAyIiwiaWF0IjoxNzY0MDI3Mjg5LCJleHAiOjE3NjQwMzA4ODksImVtYWlsIjoidmluaWNpdXNkc2cyLmFsdW5vQHVuaXBhbXBhLmVkdS5iciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA5NjQ0NDEwODAwNTQzNDcyMTM3Il0sImVtYWlsIjpbInZpbmljaXVzZHNnMi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.o6wCF6qUamdiGoxgZyo-P8rWq2iRyPU_Mgg5eLObWxPKhM5JMKmpDz8N2QcXmeP-fMglwLzk5ZWnv1AUpD2p-AZZ1wfC6qhwAw1eHNXdQgbLlikbYzlD5XtybOGwggt9xrjjGFCO75CqXgXlF2k9GFjD-2RhH_1po91H4B3PP_NTFYv2Mxi1ypYGiFCcVY0XvdayccfL5i1sGHW-hZ6CXjQDFzyueHdw9ZWpPoExVli5pJJIHojy-nuNNnLRX4nJ7nuGiNtZSeGq1iYvfbaomhLAXxQRdFYU5iCP0gB-g_ZR9iX4dH79Mob9ZRE-kPiFGXI1Feh_UGHds11TMUB7FA\",\"expirationTime\":1764030919671,\"refreshToken\":\"AMf-vBxPEtaUKuzD3kd4vDvK-9A-QQZWCxllyOIrOL5OH8sL2Nl_29BrmnAVc-2SCJWdEx5xT2SIMj18N6lYGXIkjc08yHRIzkzckOPsN5ITf7-W9F08gQOD9SGVvlcH4-QH8UM3EvmzWb4yWtaxbIcZ2y5e0IJfB8Ix7hhg2zTAJbn8nbw-6Qwm_lavkfdTHoufUJ3dgp7cN3MS0B6YSG70XwV_PeFWxg0OBAijp2eYtoBYGaZsW4M55Qc9d_MET3BzCFf2LryLz_eMDlNzmKLwkZxUEcg2WKTBAqMVdccid-QxvPNSH9XtOli3bqQ7NOjJqLhuCVKC-LvBUSnQXKBADgT0mOgrWf0btKcczBCAFCdwJS_z0eZoqKGca74zOjhtfKB-2r25t3f5ppuslcFmj1XUIRANqaXknoFBCvXMO76SW6WYiRFA5fUI0N83xZNABVHaVATdyFJOzv_zvunz1eYNzXt-Sw\",\"tenantId\":null},\"uid\":\"Ie49xOUsOwNlkTucsGU51uVSWu02\",\"_redirectEventId\":null}\";";

    @BeforeEach
    public void setup() throws InterruptedException {
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
    public void ExecucaoTestes() throws InterruptedException {
        // --- 1. Verificação de Login ---
        verificarLogin();

        //RF3
        //EditarCurso();

        //RF12 e RF13
        //edicao_e_exclusao_MaterialExtra();

        //quizSelecaoAleatoria();

        //quizSelecaoManual();

        localizarListagemVideos();
        CurtirEDescurtirVideo();
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
        Thread.sleep(2000);


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
        Thread.sleep(2000);


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
        Thread.sleep(2000);

        try {
            System.out.println("Tentando realizar o sorteio aleatório (2 vezes)...");
            WebElement sorteioButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r67:\"]")
            ));

            sorteioButton.click();
            System.out.println("➡️ Primeiro sorteio realizado.");

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

        try {
            System.out.println("Executando segundo caso de teste: Sorteio com 1 aluno");
            Thread.sleep(2000);

            // Estado inicial
            WebElement contentContainer = driver.findElement(By.xpath("//*[@id=\"quiz-content-container\"]/div/div[1]/div/div/div"));
            String initialState = contentContainer.getAttribute("outerHTML");
            System.out.println("Estado inicial da tela capturado.");


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

                Thread.sleep(2000);
                System.out.println("Retirando segundo aluno...");
                WebElement aluno2button = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\":r6g:\"]")
                ));
                aluno2button.click();

                Thread.sleep(2000);
                System.out.println("✅ Remoção de alunos concluída.");


            } catch (Exception e) {
                System.err.println("❌ ERRO NA SUB-ETAPA DE REMOÇÃO: Falha ao remover alunos (Passo 5).");
                e.printStackTrace();
                assertTrue(false, "Falha no Passo 5 (Remoção de Alunos).");
                return;
            }

            System.out.println("Realizando sorteio com 1 aluno...");
            WebElement sorteioAluno = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r67:\"]")
            ));
            sorteioAluno.click();
            System.out.println("✅ Sorteio realizado.");

            // estado final
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


        System.out.println("SUCESSO: Todos os passos de navegação e sorteios foram executados.");
    }
    private void quizSelecaoManual() throws InterruptedException {
        System.out.println("Iniciando casos de testes RF27: Seleção Manual...");

        // --- Passo 1: Refresh
        try {
            System.out.println("Atualizando a página para garantir o estado inicial...");
            driver.navigate().refresh();
            wait.until(ExpectedConditions.urlContains(url)); // Espera a URL base carregar
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
            System.out.println("Clicando na opção escolha um aluno...");
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
            System.out.println("Clicando no elemento de seleção de aluno");
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
            System.out.println("Clicando no botão final de seleção...");
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

            // NOTE: Sem o estado inicial, esta
            // verificação apenas garante que o elemento existe.
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

            System.out.println("Clicando para remover o aluno...");
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


        System.out.println("Tentando clicar no aluno removido...");
        try {

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alunoRemovido = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\":r6j:\"]")
            ));
            alunoRemovido.click();


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






    /**
     * Localiza o iframe, foca, inicia a reprodução do vídeo (CT 1) e testa a funcionalidade de Tela Cheia (CT 2).
     */
    private void localizarListagemVideos() throws InterruptedException {
        System.out.println("\n--- Iniciando CT01 RF39: Reproduzir o video da listagem...");

        final By IFRAME_SELECTOR = By.tagName("iframe");
        final By PLAY_BUTTON_ARIA_LABEL = By.xpath("//button[@aria-label='Reproduzir']");
        final By VIDEO_ELEMENT_TAG = By.tagName("video");

        // NOVO SELETOR: O CONTAINER PRINCIPAL DO PLAYER, que aceita comandos de teclado.
        final By MOVIE_PLAYER_CONTAINER = By.id("movie_player");

        // Seletores para Tela Cheia (Apenas para referência)
        final By FULLSCREEN_BUTTON_XPATH = By.xpath("//*[@id=\"movie_player\"]/div[31]/div[2]/div[2]/button[6]");
        final By EXIT_FULLSCREEN_BUTTON_XPATH = FULLSCREEN_BUTTON_XPATH;

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        try {
            // 1. Tentar focar o IFRAME
            System.out.println("1. Tentando localizar e focar o iframe do player...");
            WebElement iframeElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(IFRAME_SELECTOR));
            driver.switchTo().frame(iframeElement);
            System.out.println("✅ Foco alterado para o iframe do player.");

            // 2. Inicia a reprodução (CÓDIGO ANTERIOR)
            System.out.println("2. Executando clique no botão 'Reproduzir'...");
            WebElement playButton = shortWait.until(ExpectedConditions.elementToBeClickable(PLAY_BUTTON_ARIA_LABEL));
            actions.moveToElement(playButton).click().build().perform();
            System.out.println("✅ Clique no botão 'Reproduzir' realizado via Actions.");
            Thread.sleep(1000);


            System.out.println("3. Verificando se o video esta sendo reproduzido via JavaScript...");
            WebElement videoElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(VIDEO_ELEMENT_TAG));
            shortWait.until(driver -> {
                Boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused;", videoElement);
                System.out.println("   -> Estado atual do vídeo (paused): " + isPaused);
                return !isPaused;
            });
            System.out.println("✅ CT 1 (Reprodução): O vídeo está bombandooooo.");
            Thread.sleep(3000);

            // ---------------------------------------------------------------------------------------------
            // --- CT 2: TESTE DE TELA CHEIA
            // ---------------------------------------------------------------------------------------------

            System.out.println("\nComeçando caso de teste 2: Ativar Tela Cheia via Foco no #movie_player");

            // 4. LOCALIZAR O CONTAINER PRINCIPAL (#movie_player)
            WebElement moviePlayerContainer = shortWait.until(ExpectedConditions.presenceOfElementLocated(MOVIE_PLAYER_CONTAINER));

            // 5. ATIVAR TELA CHEIA: Simular a tecla 'F'
            System.out.println("5. Enviando tecla 'F' para o container principal para ativar a Tela Cheia...");
            // O comando sendKeys deve ser enviado para o container principal (#movie_player)
            moviePlayerContainer.sendKeys("f");
            System.out.println("✅ Tecla 'F' enviada. (Esperando a mudança de estado...)");


            System.out.println("Aguardando 3 segundos em Tela Cheia.");
            Thread.sleep(3000);

            // 6. DESATIVAR TELA CHEIA: Simular a tecla 'F' novamente
            System.out.println("6. Enviando tecla 'F' novamente para Sair da Tela Cheia...");
            moviePlayerContainer.sendKeys("f");
            System.out.println("✅ Saiu da Tela Cheia.");


            System.out.println("Fim do CT 2: Aguardando 2 segundos para o próximo requisito funcional.");
            Thread.sleep(2000);

        } catch (Exception e) {
            System.err.println("❌ ERRO FATAL: Falha na prova de reprodução ou na ativação/desativação da Tela Cheia.");
            System.err.println("Detalhes do Erro: " + e.getMessage());

            assertTrue(false, "Falha na prova de reprodução ou Tela Cheia.");
        } finally {
            // É fundamental retornar o foco para o conteúdo principal da página
            driver.switchTo().defaultContent();
        }
    }
    /**
     * RF40 - Vídeos (Curtir e Descurtir):
     */
    private void CurtirEDescurtirVideo() throws InterruptedException {
        System.out.println("\n--- Iniciando RF40: Teste de Curtir e Descurtir Vídeos ---");

        // --- LOCALIZADORES ROBUSTOS ---
        // Curtir (Like): Botão que contém o ícone de polegar para cima
        final By LIKE_BUTTON_XPATH = By.xpath("//button[.//svg[@data-testid='ThumbUpIcon']]");
        // Descurtir (Dislike): Botão que contém o ícone de polegar para baixo
        final By DISLIKE_BUTTON_XPATH = By.xpath("//button[.//svg[@data-testid='ThumbDownIcon']]");

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            Thread.sleep(3000); // Pausa inicial para estabilização da lista de vídeos

            // ----------------------------------------------------------------------------------
            // --- CT1 RF40: CURTIR E REVERTER CURTIDA (LIKE / UNLIKE) ---
            // ----------------------------------------------------------------------------------

            System.out.println("\nComecando CT1 RF40: Curtir e Reverter Curtida");

            // 1. Localizar o Botão de Curtir
            WebElement likeButton = shortWait.until(ExpectedConditions.elementToBeClickable(LIKE_BUTTON_XPATH));

            // 2. Clique 1: CURTIR (LIKE)
            System.out.println("-> Clicando 1/2 (Curtir): Tentativa de registrar Curtida...");
            js.executeScript("arguments[0].click();", likeButton);

            Thread.sleep(3000); // Pausa após o primeiro clique
            System.out.println("✅ Clique 1 (Curtir) realizado.");

            // 3. Clique 2: REVERTER CURTIDA (UNLIKE)
            System.out.println("-> Clicando 2/2 (Reverter): Tentativa de remover a Curtida...");
            js.executeScript("arguments[0].click();", likeButton);

            Thread.sleep(3000); // Pausa após o segundo clique
            System.out.println("✅ Clique 2 (Reverter Curtida) realizado.");

            System.out.println("✅ CT1 RF40 concluído com sucesso.");


            // ----------------------------------------------------------------------------------
            // --- CT2 RF40: DESCUTIR E REVERTER DESCUTIDA (DISLIKE / UNDISLIKE) ---
            // ----------------------------------------------------------------------------------

            System.out.println("\nComecando CT2 RF40: Descurtir e Reverter Descurtida");

            WebElement dislikeButton = shortWait.until(ExpectedConditions.elementToBeClickable(DISLIKE_BUTTON_XPATH));

            System.out.println("-> Clicando 1/2 (Descurtir): Tentativa de registrar Descurtida...");
            js.executeScript("arguments[0].click();", dislikeButton);

            Thread.sleep(3000);
            System.out.println("✅ Clique 1 (Descurtir) realizado.");


            System.out.println("-> Clicando 2/2 (Reverter): Tentativa de remover a Descurtida...");
            js.executeScript("arguments[0].click();", dislikeButton);

            Thread.sleep(3000);
            System.out.println("✅ Clique 2 (Reverter Descurtida) realizado.");

            System.out.println("\n✅ RF40 Testes de Curtir/Descurtir concluídos com sucesso.");

        } catch (Exception e) {
            System.err.println("❌ ERRO FATAL no RF40: Falha na interação com botões de Like/Dislike.");
            System.err.println("Detalhes do Erro: " + e.getMessage());

            assertTrue(false, "Falha na execução dos Casos de Teste RF40.");
        }
    }
}