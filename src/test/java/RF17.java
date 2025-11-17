import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class RF17 {

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
            "    \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDI5MzRmZTBlZWM0NmE1ZWQwMDA2ZDE0YTFiYWIwMWUzNDUwODMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiR3VzdGF2byBGZXJuYW5kZXMgZG9zIEFuam9zIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0x4TVhoalRGTVdCamZRNTRhN1V5eDIzNTNTMnNTQVJnUmJFcHprRlphM2xuUkE3dz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9yZWFjdC1uYS1wcmF0aWNhIiwiYXVkIjoicmVhY3QtbmEtcHJhdGljYSIsImF1dGhfdGltZSI6MTc2Mjk4Njc1OSwidXNlcl9pZCI6InFXYTRKcVB3UlBiWm9HQkpoNjlJNWdmRzNnMzIiLCJzdWIiOiJxV2E0SnFQd1JQYlpvR0JKaDY5STVnZkczZzMyIiwiaWF0IjoxNzYyOTg2NzU5LCJleHAiOjE3NjI5OTAzNTksImVtYWlsIjoiZ3VzdGF2b2Fuam9zLmFsdW5vQHVuaXBhbXBhLmVkdS5iciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA5NjUzNTE4MzI2ODg5NTU2NTY2Il0sImVtYWlsIjpbImd1c3Rhdm9hbmpvcy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.MvKZJEneqQhp3bKk93N0-m3KGkjC6lR0NeDI7UJA9GqddSPHs83QnfqAbgKmIAEhf7yuQvOKavPYopDwYIDe5saozag8F6TrMf2Y7BSwAly-zT1kTV4FDVuN-MBSx-IPvBqru4noI1m4fD14ReOAlE1lY8MrooGDGbOYUbVIYUCKYLuCoHwkgxJEsEcFnnrqCM_67ppkxdPCBoGSqoizKEJDMLW9oPAAZE5WQSiPNRz-aWqx855MVapVixzu_spIKgsOsRim6N2FY-NCy4Lebt3Q7l-Dh6SUuzuCpOpyM1v2Q5LV_M2puNKJJhrlQ5VFEBlW_wuxArLFaMYBKCBKAw\",\n" +
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Configurações da Conta']")));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            System.out.println("A fechar o navegador.");
            driver.quit();
        }
    }

    public void AcessarGerenciadorCurso() {
        System.out.println("Acessando o Gerenciador de Cursos...");
        WebElement menuPrincipal = driver.findElement(By.xpath("//button[@aria-label='Configurações da Conta']"));
        js.executeScript("arguments[0].click();", menuPrincipal);

        WebElement linkGerenciarCursos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(., 'Gerenciamento de Cursos')]")
                )
        );
        linkGerenciarCursos.click();
    }

    @Test
    public void CT17_01_visualizarAlunosESalvar() throws InterruptedException {

        AcessarGerenciadorCurso();
        WebElement btnGerenciarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(text(), 'Gerenciar Curso')])[1]")
        ));
        btnGerenciarCurso.click();
        WebElement abaAlunos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(.)='Alunos']")
        ));
        abaAlunos.click();

        System.out.println("Verificando se o aluno 'Gustavo Fernandes dos Anjos' e seus dados estão visíveis...");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("tbody")));

        String xPathLinhaGustavo = "//tr[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'gustavo fernandes dos anjos')]";

        WebElement linhaGustavo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(xPathLinhaGustavo)
        ));
        assertNotNull(linhaGustavo, "A linha do aluno 'Gustavo Fernandes dos Anjos' não foi encontrada!");


        String textoDaLinha = linhaGustavo.getText();
        System.out.println("Verificando Email na linha...");
        assertTrue(textoDaLinha.contains("gustavoanjos.aluno@unipampa.edu.br"), "O email não foi encontrado na linha do aluno!");

        WebElement progressoCell = linhaGustavo.findElement(By.xpath("./td[2]"));
        String progresso = progressoCell.getText();
        System.out.println("Verificando Progresso: " + progresso);
        assertTrue(progresso.contains("%"), "A coluna 'Progresso' (td[2]) não contém o símbolo '%'!");


        System.out.println("Informações básicas (Nome, Email, Progresso) verificadas com sucesso.");

        System.out.println("Clicando em 'Salvar Curso'...");
        WebElement btnSalvarCurso = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(.)='Salvar Curso']")
        ));
        btnSalvarCurso.click();

        System.out.println("Verificando sucesso (procurando o pop-up verde)...");

        WebElement popupSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space(.)='Curso atualizado com sucesso!']")
        ));

        assertNotNull(popupSucesso, "O pop-up de sucesso 'Curso atualizado' não foi encontrado!");

        System.out.println("*************************************************");
        System.out.println("SUCESSO: Aba Alunos visualizada, dados verificados e pop-up verificado!");
        System.out.println("*************************************************");
    }
}
