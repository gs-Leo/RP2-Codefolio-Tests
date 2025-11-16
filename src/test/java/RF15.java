import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RF15 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes.codefolio.com.br/";
    private JavascriptExecutor js;

    // --- SEUS DADOS DE AUTENTICAÇÃO (JÁ FORMATADOS) ---
    private final String MINHA_FIREBASE_KEY = "firebase:authUser:AIzaSyARn2qVrSSndFu9JSo5mexrQCMxmORZzCg:[DEFAULT]";

    // Formatei os dados que você me mandou para o formato JSON Java:
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
    // -----------------------------------------------------

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, timeout);
        js = (JavascriptExecutor) driver;

        // 1. Acessa a página (deslogado)
        driver.get(url);

        // 2. Injeta o token de autenticação
        System.out.println("Injetando token de login...");
        try {
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                    MINHA_FIREBASE_KEY,
                    MINHA_FIREBASE_VALUE);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao injetar token: " + e.getMessage());
        }

        // 3. Recarrega a página (O site vai ler o token e logar sozinho)
        System.out.println("Recarregando página para aplicar login...");
        driver.navigate().refresh();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            // driver.quit(); // Comentei para você ver o resultado
        }
    }

    private boolean existemCursos(int timeoutEmSegundos) {
        WebDriverWait waitCurto = new WebDriverWait(driver, Duration.ofSeconds(timeoutEmSegundos));

        System.out.println("Verifica se existem cursos (à espera de um 'h6')...");
        try {
            waitCurto.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.tagName("h6")
                    )
            );
            System.out.println("Verificação: Cursos encontrados.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Verificação: Nenhum curso encontrado (Timeout).");
            return false;
        }
    }
    public void AcessarGerenciadorCurso() throws InterruptedException {
        System.out.println("Iniciando o teste...");

        WebElement menuPrincipal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        // Voltamos a procurar o <button>
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

    @Test
    public void editarQuizComSucesso() throws InterruptedException {

       AcessarGerenciadorCurso();

        System.out.println("Clicando em Gerenciamento de Cursos...");
        WebElement linkGerenciar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(), 'Gerenciamento de Cursos')]") // Texto exato do menu
        ));
        linkGerenciar.click();

        // 3. Clicar em "Gerenciar Curso" (Baseado na foto image_bba87b.png)
        System.out.println("Clicando no botão roxo 'Gerenciar Curso'...");
        // Procura o botão roxo específico. Usamos o texto para garantir.
        WebElement btnGerenciarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Gerenciar Curso')]")
        ));
        btnGerenciarCurso.click();

        // 4. Clicar na aba "QUIZ" (Baseado na foto image_bba8d7.png)
        System.out.println("Clicando na aba QUIZ...");
        // As abas são botões de texto. Procuramos o que diz "QUIZ".
        WebElement abaQuiz = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'QUIZ')]")
        ));
        abaQuiz.click();

        // 5. Clicar no Lápis de Editar (Baseado na foto image_bba915.png)
        // A foto mostra ícones de lápis e lixeira. O lápis é o de editar.
        System.out.println("Procurando o ícone de lápis (Editar)...");

        // Este seletor procura um botão que tenha um ícone SVG de edição (path data-testid="EditIcon")
        // OU, se for mais simples, pegamos o primeiro botão da lista de ações.
        WebElement btnEditar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[ .//*[contains(@data-testid, 'Edit')] ])[1]")
        ));
        btnEditar.click();

        // --- AQUI COMEÇA A EDIÇÃO (Ajuste conforme o formulário de Quiz) ---
        System.out.println("Editando o Quiz...");

        // Exemplo: Alterar o título do Quiz (Se houver um campo título)
        WebElement campoTitulo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
        campoTitulo.clear();
        campoTitulo.sendKeys("Quiz Editado Automaticamente");

        // 6. Salvar (Procura botão Salvar ou Atualizar)
        System.out.println("Salvando...");
        driver.findElement(By.xpath("//button[contains(text(), 'Salvar') or contains(text(), 'Atualizar')]")).click();

        // 7. Verificação Final
        System.out.println("Verificando sucesso...");
        Thread.sleep(2000);
        WebElement tituloNaTela = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Quiz Editado Automaticamente')]")
        ));

        System.out.println("SUCESSO: Quiz editado e encontrado na lista!");
    }
}
