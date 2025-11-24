package RF45;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CT45_02 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\n" +
            "  \"fbase_key\": \"firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]\",\n" +
            "  \"value\": {\n" +
            "    \"apiKey\": \"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\n" +
            "    \"appName\": \"[DEFAULT]\",\n" +
            "    \"createdAt\": \"1763485802422\",\n" +
            "    \"displayName\": \"Marcus Vinicius Morini Querol Junior\",\n" +
            "    \"email\": \"marcusquerol.aluno@unipampa.edu.br\",\n" +
            "    \"emailVerified\": true,\n" +
            "    \"isAnonymous\": false,\n" +
            "    \"lastLoginAt\": \"1764000250329\",\n" +
            "    \"phoneNumber\": null,\n" +
            "    \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocIPSVJF5ZjtLwj6MndKM7i1yp2tDbMn13DkkmLcptbfW7FRQ74=s96-c\",\n" +
            "    \"providerData\": [\n" +
            "      {\n" +
            "        \"providerId\": \"google.com\",\n" +
            "        \"uid\": \"107226265685191574291\",\n" +
            "        \"displayName\": \"Marcus Vinicius Morini Querol Junior\",\n" +
            "        \"email\": \"marcusquerol.aluno@unipampa.edu.br\",\n" +
            "        \"phoneNumber\": null\n" +
            "      }\n" +
            "    ],\n" +
            "    \"stsTokenManager\": {\n" +
            "      \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjQwMDAyNTAsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2NDAwMDI1MCwiZXhwIjoxNzY0MDAzODUwLCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.xl0qtamf3HAW7oIk8lK69xsb9zQ0EawqQa7yMmwVBksfmWv4DE-wJt9E9mIpP9-thvTIrnw1o4QCtLiX6gqSqaSVbn2NexWNorFzl7GNmlCIwFWIuHPPt6NLdDeclVGZiNYs_5jYjD7xjzZqMPC6zkeDuT8TaWIF1E6g7mIdyx7dWO7-zA5asK7DV8lagAlMljhiP4N2q0hjJ5f7RGXwV5q1E5OkUmCp-iN7iyKH8YVwHAdJD8HWeWUYBoKp0QAlp2AhoTQs5HRc-4SZR4vYPyYl-oD9vYkkcWaMoC7Epp9GE037REZ1yD2ZutsVaqKmEil9dVgz52zJp_zbCQ_RrQ\",\n" +
            "      \"expirationTime\": 1764003850688,\n" +
            "      \"refreshToken\": \"AMf-vBxRZRi3NBAZMzXr-iFRTNTy2CN_JMwXUYL1bJa9I6Sz89ajSIkENs6DuJbBfwJQVOLoaJPuo5kofG3u8411UzVhm2Yxz-wloPMJxIA_QtMZTW0hfZRlgQEmJG3n7cWh_uZGKuZpF_Pe4uMvMrviPmGMlqLMwqaWXgAexpvVIIeJcJ4cPhKiri3yFV-Q1hJgBoUnGnEvgQ-wzP92OKlTeL7JfTrSzbXLb0aPrFZHZzvazkVDwnzCTS3AN8Ed454ORIoDjt-UqaRtRJMUu2O2orcW_kih0V4hOxCIPd9TN-RbC_go-o9ZXl0MxNBTBVNRdh-AhywG6cIAz5BKm2rtgS2oQPYQ2PPC2LyoEo0B5tAObULQt18dnUiULWZGCKUkowJdA91mQgTrbKcdvKxW2wfM9dhs9KKqjIykbnbJcP2SHPMC2bhfgffAwoETEcdKFVkaZq6P-fMmYnPFhzOLNqdgtYdGTSTFz14rGzwiHt6f4Tp3uGk\"\n" +
            "    },\n" +
            "    \"tenantId\": null,\n" +
            "    \"uid\": \"Xv0X6Yea4HXHinLMmBaa1jqDGx62\",\n" +
            "    \"_redirectEventId\": null\n" +
            "  }\n" +
            "}";

    @BeforeEach
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
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
    public void testVerificacaoListaVazia() throws InterruptedException {
        System.out.println("🚀 Iniciando CT-45.02 - Verificação de Lista Vazia");

        // 1. Ir para o menu Cursos
        try {
            WebElement menuCursos = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/listcurso']")));
            menuCursos.click();
            System.out.println("✅ Acessou menu Cursos");
        } catch (Exception e) {
            driver.get("https://testes-codefolio.web.app/listcurso");
        }
        Thread.sleep(2000);

        // 2. Clicar na aba "Concluídos"
        WebElement abaConcluidos = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Concluídos']")));
        abaConcluidos.click();
        System.out.println("✅ Clicou na aba 'Concluídos'");

        // Pausa para garantir que a mensagem carregou
        Thread.sleep(2000);

        // 3. Verificar a mensagem "Nenhum curso encontrado."
        WebElement mensagemErro = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Nenhum curso encontrado.']")
        ));

        // Validação (O teste passa se isso for verdade)
        Assertions.assertTrue(mensagemErro.isDisplayed(), "A mensagem de lista vazia deveria estar visível!");

        System.out.println("✅ Validação concluída: Mensagem '" + mensagemErro.getText() + "' encontrada.");
        System.out.println("🎉 Teste Finalizado com Sucesso!");
    }
}

