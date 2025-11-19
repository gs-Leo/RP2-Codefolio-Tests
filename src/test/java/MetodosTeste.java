import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MetodosTeste {

    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void criarCurso(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String nomeDoCurso, String descDoCurso) {
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

    protected void adicionarVideo(WebDriver driver, WebDriverWait wait, JavascriptExecutor js,
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

    protected void criarQuiz(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
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
    protected void clicarBotaoEditarQuiz(WebDriverWait wait, JavascriptExecutor js) {
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


    protected void adicionarQuestao(WebDriver driver, WebDriverWait wait, JavascriptExecutor js,
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

    protected void clicarOkModal(WebDriverWait wait, JavascriptExecutor js, String contexto) {
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
}