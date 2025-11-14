import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.devtools.v140.debugger.Debugger.pause;

public class RF29 {

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";
    private static final String FIREBASE_VALUE = "{\"apiKey\":\"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\",\"appName\":\"[DEFAULT]\",\"createdAt\":\"1761931337511\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"emailVerified\":true,\"isAnonymous\":false,\"lastLoginAt\":\"1762561682156\",\"phoneNumber\":null,\"photoURL\":\"https://lh3.googleusercontent.com/a/ACg8ocI6BhRqR9kQ2w6sIU_wWXSqI2PDtQ1BRzToca1OLhky1rjxQA=s96-c\",\"providerData\":[{\"providerId\":\"google.com\",\"uid\":\"100155757080180289674\",\"displayName\":\"Fade Hassan Husein Kanaan\",\"email\":\"fadekanaan.aluno@unipampa.edu.br\",\"phoneNumber\":null}],\"stsTokenManager\":{\"refreshToken\":\"AMf-vBzbe3Ub88Nrg_9VB3EP68vWGwpIA9Rr5fc3v2ak0bTY0hORkqVyvm22CJfcImTiRdu2q0VLohB0HdYLaw_6BX0G2fp7yFgJlhICFi-oaRXXmUMcDESncCXmAoxUKDXhgjJUWk37BFiAye8C8JBwcfaUJeFBTlVklhHKssPQJ1AYsiyEVI9J2M03fouOeuOpYtjv6XZOGbNC39PmoHf7J-F2KzmMh4nE-Z3uokaAA7YFaa5W_LQZA-SehHrcC8oJVCwI5ZQwKeQM2eSj5fDZTJSfoAH9PYQgJUlRGUUPI5-nbwDkG_8_BtDN7AZKpXgdvR_6NMJFuY8BafETuWKwYZpZkdy88VKYlgWyyGg79RBfx7NxNLgJUWOi6lYBUnlkCD80YwtslN2ZgP-tqQ54BdsdwnMmS0JPA-yUM4Mmcjb7-kHu9ZkNHMcDsEnEpBAL5Mn2BxPjOelInR5ZFIg_VhJpmbqr-g\",\"accessToken\":\"eyJhbGciOiJSUzI1NiIsImtpZCI6IjU0NTEzMjA5OWFkNmJmNjEzODJiNmI0Y2RlOWEyZGZlZDhjYjMwZjAiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRmFkZSBIYXNzYW4gSHVzZWluIEthbmFhbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJNkJoUnFSOWtRMnc2c0lVX3dXWFNxSTJQRHRRMUJSelRvY2ExT0xoa3kxcmp4UUE9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vcmVhY3QtbmEtcHJhdGljYSIsImF1ZCI6InJlYWN0LW5hLXByYXRpY2EiLCJhdXRoX3RpbWUiOjE3NjE5MzEzMzcsInVzZXJfaWQiOiJObFQxcjRDc20xUjlPMDRzZlJyNDlFQk1EVVIyIiwic3ViIjoiTmxUMXI0Q3NtMVI5TzA0c2ZScjQ5RUJNRFVSMiIsImlhdCI6MTc2MjY0ODQ3NiwiZXhwIjoxNzYyNjUyMDc2LCJlbWFpbCI6ImZhZGVrYW5hYW4uYWx1bm9AdW5pcGFtcGEuZWR1LmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDAxNTU3NTcwODAxODAyODk2NzQiXSwiZW1haWwiOlsiZmFkZWthbmFhbi5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.mjn9r_AC_VlUt96za71NZQmgzLXYU6ioUedueeKptnRM99h0OTTqQSMGO59YFxfJKwLvdhtjVCVVFk9SRhO_dltdWZVzfzXbuOw98wZr2Y3KbkXBHQDKs8Eyw94xcfiTbBYbrbOzQIu2hJQEl1PE851NAZ97wNrn_R4_UyJLi7WGyQXYbc-bYFT7hUe8FnLY7z1WxD7kv0dgX05jnF-OOziNFR4JgOIKeDm7RtxSFBu7LEMVzmMsQSZHf1Eg05U2KlN3IthantErscx3NZ6PMRr8gG-El__58svbqC51ok5MBhgoComJ1FTEUf5zV1-OKOdGPYgg58YmcpuZHb4GnQ\",\"expirationTime\":1762652075392},\"tenantId\":null,\"uid\":\"NlT1r4Csm1R9O04sfRr49EBMDUR2\",\"_redirectEventId\":null}";

    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void criarCurso(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String nomeDoCurso, String descDoCurso) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Criar Novo Curso')]")
            )).click();
            System.out.println("Clicou em Criar Novo Curso.");
            pause(2500);

            WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(.), 'Título do Curso')]/following-sibling::div//input")
            ));
            titulo.sendKeys(nomeDoCurso);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", titulo);
            pause(1000);

            WebElement descricao = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(.), 'Descrição do Curso')]/following-sibling::div//textarea")
            ));
            descricao.sendKeys(descDoCurso);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", descricao);
            pause(1200);

            WebElement salvarBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(translate(normalize-space(.), 'salvarcurso', 'SALVARCURSO'), 'SALVAR CURSO')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", salvarBtn);
            pause(1000);
            try {
                salvarBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", salvarBtn);
            }
            System.out.println("Clicou em SALVAR CURSO.");
            pause(2500);

            // Modal OK
            boolean clicouOK = false;
            try {
                WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                ));

                // rola até o botão e força foco
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", okBtn);
                js.executeScript("arguments[0].focus();", okBtn);
                Thread.sleep(600);

                // força o clique via JS
                js.executeScript("arguments[0].click();", okBtn);
                System.out.println("✅ Clicou em 'OK!' com sucesso (via JS).");
                clicouOK = true;

            } catch (Exception e) {
                System.out.println("⚠️ Erro ao tentar clicar no botão 'OK!': " + e.getMessage());
            }

            if (!clicouOK) throw new RuntimeException("❌ Falha ao confirmar criação do curso.");
            pause(2000);

        } catch (Exception e) {
            System.out.println("❌ Erro ao criar curso:");
            e.printStackTrace();
            throw e; // repropaga erro para falhar o teste
        }
    }

    private void adicionarVideo(WebDriver driver, WebDriverWait wait, JavascriptExecutor js,
                                String nomeVideo, String urlVideo, String descVideo) {
        try {
            System.out.println("Iniciando 'adicionarVideo'...");

            // Aguarda campo de título
            WebElement labelTituloVideo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(.), 'Título do Vídeo')]")
            ));
            String idTituloVideo = labelTituloVideo.getAttribute("for");
            WebElement inputTituloVideo = wait.until(ExpectedConditions.elementToBeClickable(By.id(idTituloVideo)));
            inputTituloVideo.sendKeys(nomeVideo);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputTituloVideo);
            pause(500);
            System.out.println("Preencheu Título do Vídeo.");

            // Campo URL
            WebElement labelUrlVideo = driver.findElement(By.xpath("//label[contains(normalize-space(.), 'URL do Vídeo')]"));
            String idUrlVideo = labelUrlVideo.getAttribute("for");
            WebElement inputUrlVideo = driver.findElement(By.id(idUrlVideo));
            inputUrlVideo.sendKeys(urlVideo);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputUrlVideo);
            pause(500);
            System.out.println("Preencheu URL do Vídeo.");

            // Campo Descrição
            WebElement labelDescVideo = driver.findElement(By.xpath("//label[contains(normalize-space(.), 'Descrição do Vídeo')]"));
            String idDescVideo = labelDescVideo.getAttribute("for");
            WebElement inputDescVideo = driver.findElement(By.id(idDescVideo));
            inputDescVideo.sendKeys(descVideo);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputDescVideo);
            pause(500);
            System.out.println("Preencheu Descrição do Vídeo.");

            // Botão "Adicionar Vídeo"
            WebElement adicionarVideoBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Adicionar Vídeo')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", adicionarVideoBtn);
            pause(800);
            try {
                adicionarVideoBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", adicionarVideoBtn);
            }
            System.out.println("🎬 Clicou em 'Adicionar Vídeo'.");
            pause(3000);

            // Espera modal "Vídeo adicionado com sucesso!"
            boolean clicouOK = false;
            for (int i = 0; i < 3 && !clicouOK; i++) {
                try {
                    WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                    ));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", okBtn);
                    js.executeScript("arguments[0].focus();", okBtn);
                    pause(500);
                    js.executeScript("arguments[0].click();", okBtn);
                    System.out.println("✅ Clicou em 'OK!' (modal de vídeo).");
                    clicouOK = true;
                } catch (Exception e) {
                    System.out.println("⚠️ Tentativa " + (i + 1) + " de clicar em OK falhou. Repetindo...");
                    pause(1000);
                }
            }

            if (!clicouOK) throw new RuntimeException("❌ Falha ao confirmar adição do vídeo.");

            pause(1500);

            // Botão "Salvar Curso"
            WebElement salvarCursoBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Salvar Curso')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", salvarCursoBtn);
            pause(800);
            try {
                salvarCursoBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", salvarCursoBtn);
            }
            System.out.println("💾 Clicou em 'Salvar Curso' após adicionar vídeo.");
            pause(3000);

            // Confirmação final (modal de curso salvo)
            boolean clicouOkFinal = false;
            for (int i = 0; i < 3 && !clicouOkFinal; i++) {
                try {
                    WebElement okBtnFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                    ));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", okBtnFinal);
                    js.executeScript("arguments[0].focus();", okBtnFinal);
                    pause(500);
                    js.executeScript("arguments[0].click();", okBtnFinal);
                    System.out.println("✅ Clicou em 'OK!' (modal de salvar curso).");
                    clicouOkFinal = true;
                } catch (Exception e) {
                    System.out.println("⚠️ Tentativa " + (i + 1) + " de clicar no OK final falhou. Repetindo...");
                    pause(1000);
                }
            }

            if (!clicouOkFinal) throw new RuntimeException("❌ Falha ao confirmar salvamento final do curso.");

            System.out.println("✅ Vídeo adicionado e curso salvo com sucesso!");
            pause(1500);

        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar vídeo:");
            e.printStackTrace();
            throw e;
        }
    }

    private void criarQuiz(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        try {
            System.out.println("🧠 Iniciando criação de Quiz...");

            // Abre a aba "Quiz"
            WebElement abaQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Quiz')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", abaQuiz);
            pause(800);
            abaQuiz.click();
            System.out.println("📄 Clicou na aba Quiz.");
            pause(1500);

            // Clica em "Adicionar Quiz"
            WebElement botaoAdicionarQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Adicionar Quiz')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", botaoAdicionarQuiz);
            pause(800);
            botaoAdicionarQuiz.click();
            System.out.println("➕ Clicou em 'Adicionar Quiz'.");
            pause(2500);

            // ✅ Modal "OK" após adicionar quiz
            clicarOkModal(wait, js, "após adicionar quiz");
            pause(4000);

            // === PRIMEIRA QUESTÃO ===
            System.out.println("✏️ Editando quiz para adicionar a primeira questão...");
            clicarBotaoEditarQuiz(wait, js);
            adicionarQuestao(driver, wait, js, "Pergunta", "Correta", "Errada");

            // === SEGUNDA QUESTÃO ===
            System.out.println("🔁 Criando segunda questão...");
            clicarBotaoEditarQuiz(wait, js);
            adicionarQuestao(driver, wait, js, "Pergunta2", "Verdadeira", "Falsa");

            // === SALVAR CURSO ===
            WebElement btnSalvarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Salvar Curso')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btnSalvarCurso);
            pause(1000);
            btnSalvarCurso.click();
            System.out.println("📘 Clicou em 'Salvar Curso'.");
            pause(2500);

            // ✅ Modal "OK" após salvar curso
            clicarOkModal(wait, js, "após salvar curso");

            System.out.println("🎉 Quiz com duas questões criado e salvo com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao criar quiz:");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Clica no botão de Editar Quiz (ícone de lápis / EditIcon)
     */
    private void clicarBotaoEditarQuiz(WebDriverWait wait, JavascriptExecutor js) {
        try {
            System.out.println("🔍 Procurando botão de editar quiz...");

            // Localiza o botão de editar baseado no padrão de ID e no ícone
            WebElement editarQuizIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[id^=':r'] svg[data-testid='EditIcon']")
            ));

            // Sobe do ícone SVG até o botão pai
            WebElement editarQuizBtn = editarQuizIcon.findElement(By.xpath("./ancestor::button"));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", editarQuizBtn);
            Thread.sleep(500);
            js.executeScript("arguments[0].focus();", editarQuizBtn);
            Thread.sleep(300);

            try {
                editarQuizBtn.click();
                System.out.println("✏️ Clicou em 'Editar Quiz' (click direto).");
            } catch (Exception e) {
                System.out.println("⚙️ Tentando clicar via JavaScript (fallback)...");
                js.executeScript("arguments[0].click();", editarQuizBtn);
                System.out.println("✅ Clicou em 'Editar Quiz' (via JS).");
            }

            Thread.sleep(1500);

        } catch (Exception e) {
            System.out.println("❌ Erro ao clicar no botão de editar quiz:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private void adicionarQuestao(WebDriver driver, WebDriverWait wait, JavascriptExecutor js,
                                  String pergunta, String opcao1, String opcao2) {
        try {
            // === CAMPO PERGUNTA ===
            WebElement labelPergunta = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[normalize-space(text())='Pergunta']")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", labelPergunta);

            // Captura o valor do atributo "for" (ex: ":r18o:")
            String idInputPergunta = labelPergunta.getAttribute("for");

            // Localiza o input correspondente
            WebElement inputPergunta = driver.findElement(By.id(idInputPergunta));
            inputPergunta.click();
            inputPergunta.clear();
            inputPergunta.sendKeys(pergunta);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputPergunta);
            pause(500);
            System.out.println("📝 Preencheu Pergunta: " + pergunta);

            // === CAMPO OPÇÃO 1 ===
            WebElement labelOpcao1 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[normalize-space(text())='Opção 1']")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", labelOpcao1);
            String idInputOpcao1 = labelOpcao1.getAttribute("for");

            WebElement inputOpcao1 = driver.findElement(By.id(idInputOpcao1));
            inputOpcao1.click();
            inputOpcao1.clear();
            inputOpcao1.sendKeys(opcao1);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputOpcao1);
            pause(500);
            System.out.println("✅ Preencheu Opção 1: " + opcao1);

            // === CAMPO OPÇÃO 2 ===
            WebElement labelOpcao2 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[normalize-space(text())='Opção 2']")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", labelOpcao2);
            String idInputOpcao2 = labelOpcao2.getAttribute("for");

            WebElement inputOpcao2 = driver.findElement(By.id(idInputOpcao2));
            inputOpcao2.click();
            inputOpcao2.clear();
            inputOpcao2.sendKeys(opcao2);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", inputOpcao2);
            pause(500);
            System.out.println("❌ Preencheu Opção 2: " + opcao2);

            // === BOTÃO SALVAR QUESTÃO ===
            WebElement btnSalvarQuestao = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(normalize-space(.), 'Salvar Questão')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btnSalvarQuestao);
            pause(800);
            try {
                btnSalvarQuestao.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", btnSalvarQuestao);
            }
            System.out.println("💾 Salvou a questão: " + pergunta);
            pause(2000);

        } catch (Exception e) {
            System.out.println("⚠️ Erro ao adicionar questão '" + pergunta + "'");
            e.printStackTrace();
            throw e;
        }
    }

    private void clicarOkModal(WebDriverWait wait, JavascriptExecutor js, String contexto) {
        boolean clicouOK = false;
        for (int i = 0; i < 3 && !clicouOK; i++) {
            try {
                WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='dialog' or contains(@class,'MuiBox-root')]//button[contains(normalize-space(.), 'OK')]")
                ));
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", okBtn);
                js.executeScript("arguments[0].focus();", okBtn);
                pause(500);
                js.executeScript("arguments[0].click();", okBtn);
                System.out.println("✅ Clicou em 'OK' " + contexto + " (via JS).");
                clicouOK = true;
            } catch (Exception e) {
                System.out.println("⚠️ Tentativa " + (i + 1) + " de clicar em OK " + contexto + " falhou.");
                pause(800);
            }
        }
        if (!clicouOK)
            throw new RuntimeException("❌ Falha ao clicar em OK " + contexto + ".");
    }




    @Test
    public void Ct29_01() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String timestamp = Long.toString(System.currentTimeMillis());
        String nomeDoCurso = "Curso Teste RF29 " + timestamp;
        String descDoCurso = "Descrição gerada por teste automatizado.";
        String nomeDoVideo = "Vídeo Padrão " + timestamp;
        String urlDoVideo = "https://www.youtube.com/watch?v=Fw9YW5_MZRs";
        String descVideo = "Descrição de teste para o vídeo.";

        try {
            driver.get("https://testes.codefolio.com.br");

            // 🔐 LOGIN
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // --- ETAPA 1: CRIAR CURSO ---
            driver.get("https://testes.codefolio.com.br/manage-courses");
            criarCurso(driver, wait, js, nomeDoCurso, descDoCurso);
            System.out.println("✅ Curso criado: " + nomeDoCurso);

            // --- ETAPA 2: ADICIONAR VÍDEO ---
            adicionarVideo(driver, wait, js, nomeDoVideo, urlDoVideo, descVideo);
            System.out.println("✅ Vídeo adicionado com sucesso.");

            // --- ETAPA 3: CRIAR QUIZ ---
            criarQuiz(driver, wait, js);
            System.out.println("✅ Quiz criado com sucesso.");

            // --- ETAPA 4: ABRIR LISTA DE CURSOS ---
            driver.get("https://testes.codefolio.com.br/listcurso");
            pause(3000);
            System.out.println("📚 Acessou a aba Cursos.");

            // --- ETAPA 5: LOCALIZAR O CURSO ---
            WebElement cursoCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'MuiCard-root')]//*[self::h6 or self::h3 or self::p][contains(normalize-space(.), '" + nomeDoCurso + "')]")
            ));
            assertNotNull(cursoCard, "❌ Card do curso não encontrado!");
            System.out.println("🔎 Encontrou o curso criado: " + nomeDoCurso);

            // --- ETAPA 6: CLICAR EM 'COMEÇAR' ---
            WebElement botaoComecar = cursoCard.findElement(
                    By.xpath("./ancestor::div[contains(@class,'MuiCard-root')]//button[contains(normalize-space(.), 'Começar')]")
            );
            js.executeScript("arguments[0].click();", botaoComecar);
            pause(4000);
            System.out.println("▶️ Clicou em 'Começar'.");

            // --- ETAPA 7: CLICAR NO QUIZ ---
            WebElement botaoQuizGigi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@title, 'Abrir Quiz Gigi')]")
            ));
            js.executeScript("arguments[0].click();", botaoQuizGigi);
            pause(3000);
            System.out.println("📘 Abriu o quiz Gigi.");

            // --- ETAPA 8: VALIDAR QUE A PERGUNTA 1 ESTÁ VISÍVEL ---
            WebElement pergunta1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(normalize-space(.), 'Pergunta')]")
            ));
            assertTrue(pergunta1.isDisplayed(), "❌ Pergunta 1 não está visível!");
            System.out.println("✅ Pergunta 1 visível.");

            // --- AVANÇAR PARA PRÓXIMA QUESTÃO ---
            try {
                // Localiza o botão de avançar baseado na classe do ícone interno (.css-1q9v4tb)
                WebElement setaAvancar = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button:has(> svg.css-1q9v4tb)")
                ));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", setaAvancar);
                pause(500);

                wait.until(ExpectedConditions.elementToBeClickable(setaAvancar));

                try {
                    setaAvancar.click();
                    System.out.println("➡️ Clique normal na seta (via seletor .css-1q9v4tb).");
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", setaAvancar);
                    System.out.println("⚙️ Clique forçado via JavaScript na seta (overlay possível).");
                }

                pause(2000);

                WebElement pergunta2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(normalize-space(.), 'Pergunta2')]")
                ));
                assertTrue(pergunta2.isDisplayed(), "❌ Pergunta 2 não apareceu após clicar na seta!");
                System.out.println("✅ Pergunta 2 exibida corretamente após clicar na seta ➡️");

            } catch (TimeoutException te) {
                System.out.println("❌ Timeout ao localizar a seta para avançar (ícone .css-1q9v4tb).");
                fail("Seta de avançar não encontrada.");
            } catch (Exception e) {
                e.printStackTrace();
                fail("❌ Erro inesperado ao tentar clicar na seta para avançar: " + e.getMessage());
            }

            // --- ETAPA 10: VALIDAR QUE MUDOU PARA A PERGUNTA 2 ---
            WebElement pergunta2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(normalize-space(.), 'Pergunta2')]")
            ));
            assertTrue(pergunta2.isDisplayed(), "❌ Pergunta 2 não apareceu após avançar!");
            System.out.println("💚 Avançou com sucesso para a Pergunta 2.");

            // --- SALVAR O NOME DO CURSO ---
            Files.writeString(Paths.get("ultimoCursoCriado.txt"), nomeDoCurso);
            System.out.println("📝 Nome do curso salvo: " + nomeDoCurso);

        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO FLUXO DO CT29_01: " + e.getMessage());
        } finally {
            pause(5000);
            driver.quit();
        }
    }

    @Test
    public void Ct29_02() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            String nomeDoCurso = "Curso Teste RF29 1763019755786";
            System.out.println("📘 Curso usado: " + nomeDoCurso);

            // --- LOGIN ---
            driver.get("https://testes.codefolio.com.br");
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
            pause(1500);
            driver.navigate().refresh();
            pause(2500);

            // --- ABRIR CURSOS ---
            driver.get("https://testes.codefolio.com.br/listcurso");
            pause(3000);

            // --- ENCONTRAR O CURSO E CLICAR EM COMEÇAR/CONTINUAR ---
            List<WebElement> cursos = driver.findElements(By.cssSelector(".MuiCard-root"));
            boolean cursoEncontrado = false;
            for (WebElement card : cursos) {
                if (card.getText().toLowerCase().contains(nomeDoCurso.toLowerCase())) {
                    try {
                        WebElement botaoComecar = card.findElement(By.xpath(".//button[contains(normalize-space(.), 'Começar')]"));
                        js.executeScript("arguments[0].click();", botaoComecar);
                        cursoEncontrado = true;
                        System.out.println("▶️ Clicou em 'Começar'.");
                        break;
                    } catch (Exception ignored) {}
                }
            }

            if (!cursoEncontrado) {
                WebElement abaAndamento = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(normalize-space(.), 'Em Andamento')]")
                ));
                js.executeScript("arguments[0].click();", abaAndamento);
                pause(3000);

                cursos = driver.findElements(By.cssSelector(".MuiCard-root"));
                for (WebElement card : cursos) {
                    if (card.getText().toLowerCase().contains(nomeDoCurso.toLowerCase())) {
                        WebElement botaoContinuar = card.findElement(By.xpath(".//button[contains(normalize-space(.), 'Continuar')]"));
                        js.executeScript("arguments[0].click();", botaoContinuar);
                        cursoEncontrado = true;
                        System.out.println("▶️ Clicou em 'Continuar'.");
                        break;
                    }
                }
            }

            assertTrue(cursoEncontrado, "❌ Curso não encontrado em nenhuma aba!");
            pause(4000);

            // --- CLICAR NO QUIZ ---
            WebElement botaoQuizGigi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@title, 'Abrir Quiz Gigi')]")
            ));
            js.executeScript("arguments[0].click();", botaoQuizGigi);
            pause(3000);

            // --- VALIDAR PERGUNTA 1 ---
            WebElement pergunta1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(normalize-space(.), 'Pergunta')]")
            ));
            assertTrue(pergunta1.isDisplayed(), "❌ Pergunta 1 não está visível!");
            System.out.println("✅ Pergunta 1 visível.");

            // --- AVANÇAR PARA PERGUNTA 2 ---
            try {
                WebElement setaAvancar = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button:has(> svg.css-1q9v4tb)")
                ));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", setaAvancar);
                pause(500);

                wait.until(ExpectedConditions.elementToBeClickable(setaAvancar));

                try {
                    setaAvancar.click();
                    System.out.println("➡️ Clique normal na seta (via seletor .css-1q9v4tb).");
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", setaAvancar);
                    System.out.println("⚙️ Clique forçado via JavaScript na seta (overlay possível).");
                }

                pause(2000);

                WebElement pergunta2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(normalize-space(.), 'Pergunta2')]")
                ));
                assertTrue(pergunta2.isDisplayed(), "❌ Pergunta 2 não apareceu após clicar na seta!");
                System.out.println("✅ Pergunta 2 exibida corretamente após clicar na seta ➡️");

            } catch (TimeoutException te) {
                System.out.println("❌ Timeout ao localizar a seta para avançar (ícone .css-1q9v4tb).");
                fail("Seta de avançar não encontrada.");
            }

            // --- VOLTAR PARA PERGUNTA 1 ---
            pause(1500); // tempo para o botão de voltar ficar habilitado
            try {
                WebElement setaVoltar = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button:has(> svg.css-9xkar3)")
                ));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", setaVoltar);
                pause(500);

                wait.until(ExpectedConditions.elementToBeClickable(setaVoltar));

                try {
                    setaVoltar.click();
                    System.out.println("⬅️ Clique normal na seta de voltar (via seletor .css-9xkar3).");
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", setaVoltar);
                    System.out.println("⚙️ Clique forçado via JavaScript na seta de voltar (overlay possível).");
                }

                pause(1500);

                // ✅ Nova verificação robusta para Pergunta 1
                WebElement pergunta1Novamente = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(normalize-space(.), 'Pergunta')]")
                ));

                String textoPergunta = pergunta1Novamente.getText().trim();
                assertTrue(textoPergunta.equalsIgnoreCase("Pergunta") || textoPergunta.equalsIgnoreCase("Pergunta 1"),
                        "❌ O texto exibido não corresponde à Pergunta 1! Texto encontrado: " + textoPergunta);

                System.out.println("💚 Voltou para a Pergunta 1 corretamente.");

            } catch (TimeoutException te) {
                System.out.println("❌ Timeout ao localizar a seta para voltar (.css-9xkar3).");
                fail("Seta de voltar não encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("❌ ERRO NO FLUXO DO CT29_02: " + e.getMessage());
        } finally {
            pause(5000);
            driver.quit();
        }
    }
}