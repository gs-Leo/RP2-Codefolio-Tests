import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.Keys;

public class RF15 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes.codefolio.com.br/";
    private JavascriptExecutor js;

    private final String MINHA_FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";
    private final String MINHA_FIREBASE_VALUE = "{\n" +
            "  \"apiKey\": \"AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg\",\n" +
            "  \"appName\": \"[DEFAULT]\",\n" +
            "  \"createdAt\": \"1762118008137\",\n" +
            "  \"displayName\": \"Gustavo Fernandes dos Anjos\",\n" +
            "  \"email\": \"gustavoanjos.aluno@unipampa.edu.br\",\n" +
            "  \"emailVerified\": true,\n" +
            "  \"isAnonymous\": false,\n" +
            "  \"lastLoginAt\": \"1762986759310\",\n" +
            "  \"phoneNumber\": null,\n" +
            "  \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocLxMXhjTFMWBjfQ54a7_yx2353S2sSARgRbEpzkFZa3lnRA7w=s96-c\",\n" +
            "  \"providerData\": [\n" +
            "    {\n" +
            "      \"displayName\": \"Gustavo Fernandes dos Anjos\",\n" +
            "      \"email\": \"gustavoanjos.aluno@unipampa.edu.br\",\n" +
            "      \"phoneNumber\": null,\n" +
            "      \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocLxMXhjTFMWBjfQ54a7_yx2353S2sSARgRbEpzkFZa3lnRA7w=s96-c\",\n" +
            "      \"providerId\": \"google.com\",\n" +
            "      \"uid\": \"109653518326889556566\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stsTokenManager\": {\n" +
            "    \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDI5MzRmZTBlZWM0NmE1ZWQwMDA2ZDE0YTFiYWIwMWUzNDUwODMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiR3VzdGF2byBGZXJuYW5kZXMgZG9zIEFuam9zIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0x4TVhoalRGTVdCamZRNTRhN195eDIzNTNTMnNTQVJnUmJFcHprRlphM2xuUkE3dz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9yZWFjdC1uYS1wcmF0aWNhIiwiYXVkIjoicmVhY3QtbmEtcHJhdGljYSIsImF1dGhfdGltZSI6MTc2Mjk4Njc1OSwidXNlcl9pZCI6InFXYTRKcVB3UlBiWm9HQkpoNjlJNWdmRzNnMzIiLCJzdWIiOiJxV2E0SnFQd1JQYlpvR0JKaDY5STVnZkczZzMyIiwiaWF0IjoxNzYyOTg2NzU5LCJleHAiOjE3NjI5OTAzNTksImVtYWlsIjoiZ3VzdGF2b2Fuam9zLmFsdW5vQHVuaXBhbXBhLmVkdS5iciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA5NjUzNTE4MzI2ODg5NTU2NTY2Il0sImVtYWlsIjpbImd1c3Rhdm9hbmpvcy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.MvKZJEneqQhp3bKk93N0-m3KGkjC6lR0NeDI7UJA9GqddSPHs83QnfqAbgKmIAEhf7yuQvOKavPYopDwYIDe5saozag8F6TrMf2Y7BSwAly-zT1kTV4FDVuN-MBSx-IPvBqru4noI1m4fD14ReOAlE1lY8MrooGDGbOYUbVIYUCKYLuCoHwkgxJEsEcFnnrqCM_67ppkxdPCBoGSqoizKEJDMLW9oPAAZE5WQSiPNRz-aWqx855MVapVixzu_spIKgsOsRim6N2FY-NCy4Lebt3Q7l-Dh6SUuzuCpOpyM1v2Q5LV_M2puNKJJhrlQ5VFEBlW_wuxArLFaMYBKCBKAw\",\n" +
            "    \"expirationTime\": 1762990360035,\n" +
            "    \"refreshToken\": \"AMf-vBxpUhVLaURkFkF_ROOiXhlNRIb5d3perR67zwpupkfDnMFOerTvvYoyDD4Ps6p7dOv8ubG46DRbX9YcVCXuJERTsSur-QUtuSWAWNFM9-BKRNkd0bVPEynCbVbmaCIO7TukcE4L7bZ1nP1-jVhUK-HdNAmOoWu52WaHz_yIFcE6ouRn1BtiebpoGJloHcF_VSk0EQs4CNzcWEHAMVWZN-QgYgyBHsw0fJ1KcoFz3OElNeaHYH_d-m_E8PALXxQm0mZPIYDp9Bjx5_tglK6Mp0kSClQh8rVCCehYYsNHt7Gf7HyEnUJYWmXB-iaCuIZ4X3Ofh6mwyub2ihbuaJXznEcNPqHxtmHjtp735YY9KqukmFUCEZpe-Ga7ArmHwH3DuuvjiLYOzxDd98LdIwgHG9LMe1A2apgWblay2QaniGJdwRmtjCHriEZtsfoOUVTEyhUD91DS4t8TsaY5sFDHlWM9SfUEBQ\"\n" +
            "  },\n" +
            "  \"uid\": \"qWa4JqPwRPbZoGBJh69I5gfG3g32\"\n" +
            "}";

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, timeout);
        js = (JavascriptExecutor) driver;

        driver.get(url);
        System.out.println("Injetando token de login...");
        try {
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                    MINHA_FIREBASE_KEY,
                    MINHA_FIREBASE_VALUE);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao injetar token: " + e.getMessage());
        }
        System.out.println("Recarregando página para aplicar login...");
        driver.navigate().refresh();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("A fechar o navegador.");
            driver.quit();
        }
    }

    // Método auxiliar para navegar até a página de gerenciamento de cursos
    public void AcessarGerenciadorCurso() {
        System.out.println("Acessando o Gerenciador de Cursos...");

        WebElement menuPrincipal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Configurações da Conta']")
                )
        );
        js.executeScript("arguments[0].click();", menuPrincipal);

        // Espera o link "Gerenciamento de Cursos" aparecer
        WebElement linkGerenciarCursos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(., 'Gerenciamento de Cursos')]")
                )
        );
        linkGerenciarCursos.click();
    }


    @Test
    public void editarQuizComSucesso() throws InterruptedException {

        AcessarGerenciadorCurso();
        WebElement btnGerenciarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(text(), 'Gerenciar Curso')])[1]")
        ));
        btnGerenciarCurso.click();
        WebElement abaQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(.)='Quiz']")
        ));
        abaQuiz.click();
        WebElement btnDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[ .//*[contains(@data-testid, 'ExpandMoreIcon')] ]")
        ));
        btnDropdown.click();
        Thread.sleep(1000);

        System.out.println("Procurando o ícone de lápis DA PERGUNTA (o segundo)...");
        WebElement btnEditar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[ .//*[contains(@data-testid, 'EditIcon')] ])[2]")
        ));
        btnEditar.click();

        System.out.println("Formulário de 'Editar Questão' (real) encontrado! Preenchendo...");
        String xPathPergunta = "//input[@id=(//label[text()='Pergunta']/attribute::for)]";
        String xPathOpcao1 = "//input[@id=(//label[text()='Opção 1']/attribute::for)]";
        String xPathOpcao2 = "//input[@id=(//label[text()='Opção 2']/attribute::for)]";

        String xPathBtnSalvar = "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'salvar edição')]";

        String textoEditado = "PERGUNTA REALMENTE EDITADA - RF15";

        WebElement campoPergunta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPergunta)));

        campoPergunta.clear();
        campoPergunta.sendKeys(textoEditado);

        WebElement campoOpcao1 = driver.findElement(By.xpath(xPathOpcao1));
        campoOpcao1.clear();
        campoOpcao1.sendKeys("Opção Editada 1");

        WebElement campoOpcao2 = driver.findElement(By.xpath(xPathOpcao2));
        campoOpcao2.clear();
        campoOpcao2.sendKeys("Opção Editada 2");

        // 7. Salvar a Edição
        System.out.println("Salvando a QUESTÃO EDITADA...");
        driver.findElement(By.xpath(xPathBtnSalvar)).click();

        // 8. Verificação Final (Assert)
        System.out.println("Verificando sucesso (procurando o pop-up verde)...");
        WebElement popupSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space(.)='Questão atualizada com sucesso!']")
        ));

        assertNotNull(popupSucesso, "O pop-up de sucesso da EDIÇÃO não foi encontrado!");

        System.out.println("*************************************************");
        System.out.println("SUCESSO: Questão EDITADA e pop-up verificado!");
        System.out.println("*************************************************");
    }

    @Test
    public void cancelarEdicaoQuestao() throws InterruptedException {

        AcessarGerenciadorCurso();
        WebElement btnGerenciarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(text(), 'Gerenciar Curso')])[1]")
        ));
        btnGerenciarCurso.click();
        WebElement abaQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(.)='Quiz']")
        ));
        abaQuiz.click();
        WebElement btnDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[ .//*[contains(@data-testid, 'ExpandMoreIcon')] ]")
        ));
        btnDropdown.click();
        Thread.sleep(1000);

        System.out.println("Procurando o ícone de lápis DA PERGUNTA (o segundo)...");
        WebElement btnEditar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[ .//*[contains(@data-testid, 'EditIcon')] ])[2]")
        ));
        btnEditar.click();

        System.out.println("Formulário de 'Editar Questão' encontrado! Clicando em CANCELAR...");

        String xPathBtnCancelar = "//button[normalize-space(.)='Cancelar']"; // Corrigido para "Cancelar"

        WebElement btnCancelar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathBtnCancelar)));
        btnCancelar.click();

        System.out.println("Verificando sucesso (procurando o botão 'Adicionar Questão')...");

        WebElement btnAdicionar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space(.)='Adicionar Questão']")
        ));

        assertNotNull(btnAdicionar, "O botão 'Adicionar Questão' não foi encontrado. O teste falhou em retornar.");

        System.out.println("*************************************************");
        System.out.println("SUCESSO: Edição CANCELADA e teste retornou à tela de lista!");
        System.out.println("*************************************************");
    }
    @Test
    public void validarEdicaoPerguntaVazia() throws InterruptedException {

        AcessarGerenciadorCurso();
        WebElement btnGerenciarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(text(), 'Gerenciar Curso')])[1]")
        ));
        btnGerenciarCurso.click();
        WebElement abaQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(.)='Quiz']")
        ));
        abaQuiz.click();
        WebElement btnDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[ .//*[contains(@data-testid, 'ExpandMoreIcon')] ]")
        ));
        btnDropdown.click();
        Thread.sleep(1000);
        WebElement btnEditar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[ .//*[contains(@data-testid, 'EditIcon')] ])[2]")
        ));
        btnEditar.click();

        System.out.println("Formulário de 'Editar Questão' encontrado! Limpando campo 'Pergunta'...");
        String xPathPergunta = "//input[@id=(//label[text()='Pergunta']/attribute::for)]";
        WebElement campoPergunta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathPergunta)));
        campoPergunta.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        String xPathBtnSalvar = "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'salvar edição')]";
        System.out.println("Tentando salvar com campo vazio...");
        driver.findElement(By.xpath(xPathBtnSalvar)).click();

        System.out.println("Verificando pop-up de erro de validação...");

        WebElement popupContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'Toastify__toast--error')]")
        ));

        String textoDoPopup = popupContainer.getText();

        assertTrue(textoDoPopup.contains("A pergunta não pode estar vazia"),
                "O texto do pop-up de erro não era o esperado. Texto encontrado: " + textoDoPopup);

        assertNotNull(popupContainer, "O pop-up de erro para campo vazio não foi encontrado!");

        System.out.println("*************************************************");
        System.out.println("SUCESSO: Pop-up de validação de campo vazio verificado!");
        System.out.println("*************************************************");
    }


}