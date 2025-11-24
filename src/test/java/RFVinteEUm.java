import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RFVinteEUm {

    private WebDriver driver;
    private WebDriverWait wait;
    private final Duration timeout = Duration.ofSeconds(15);
    private final String url = "https://testes-codefolio.web.app/";
    private JavascriptExecutor js;

    private final String Firebase_key = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private final String Firebase_value = "{\n" +
            "  \"apiKey\": \"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\n" +
            "  \"appName\": \"[DEFAULT]\",\n" +
            "  \"createdAt\": \"1763679183776\",\n" +
            "  \"displayName\": \"Leonardo Goncalves da Silva\",\n" +
            "  \"email\": \"leonardogds3.aluno@unipampa.edu.br\",\n" +
            "  \"emailVerified\": true,\n" +
            "  \"isAnonymous\": false,\n" +
            "  \"lastLoginAt\": \"1763752523565\",\n" +
            "  \"phoneNumber\": null,\n" +
            "  \"photoURL\": \"https://lh3.googleusercontent.com/a/ACg8ocJtq2riy2OXHPvzGKsR53P9afkDHBYcvM-S2MJVePCLhZq_CFo=s96-c\",\n" +
            "  \"providerData\": [\n" +
            "    {\n" +
            "      \"providerId\": \"google.com\",\n" +
            "      \"uid\": \"116005034032912262657\",\n" +
            "      \"displayName\": \"Leonardo Goncalves da Silva\",\n" +
            "      \"email\": \"leonardogds3.aluno@unipampa.edu.br\",\n" +
            "      \"phoneNumber\": null\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stsTokenManager\": {\n" +
            "    \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTGVvbmFyZG8gR29uY2FsdmVzIGRhIFNpbHZhIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0p0cTJyaXkyT1hIUHZ6R0tzUjUzUDlhZmtESEJZY3ZNLVMyTUpWZVBDTGhacV9DRm89czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM3NTI1MjMsInVzZXJfaWQiOiJzVTVhWmRQMDRGV0xKTGFvb2hrYm1icXhqUnMxIiwic3ViIjoic1U1YVpkUDA0RldMSkxhb29oa2JtYnF4alJzMSIsImlhdCI6MTc2Mzc1MjUyMywiZXhwIjoxNzYzNzU2MTIzLCJlbWFpbCI6Imxlb25hcmRvZ2RzMy5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjExNjAwNTAzNDAzMjkxMjI2MjY1NyJdLCJlbWFpbCI6WyJsZW9uYXJkb2dkczMuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.Iu36YgwatHl2gQUu77ZEUrhok_k0ZvYOF9Tlpkws-zmV79HiYwyriyLURDbFpz5tVORcoLbz6qIoLOq0K2D0yKmifLfnWNeapHkvqosKXlSOXbPtJcmHTnREZB12DM5KGfasioNsz-4HTIvy9eOM4NtgbQ8SBJQHeh9uHTtb90PVGys-w19E8_hAsU0fFQbMvkFGdHT5usCGwLQ56VJ2OHg_BbYp6nPxfgaUb6mqoGd6cZJID5S0wK0mBPUBTI93rc5WxD6mC70PnPX-racR2847QKfGhRs5s3rTV7A263DoVTjKdB3n2d53CkNpRDx_c939COksD36tle3gqma7KQ\",\n" +
            "    \"expirationTime\": 1763756123890,\n" +
            "    \"refreshToken\": \"AMf-vBzcZDX5l8fauOifGjU0Bm49py-TwAFLSeiDLOLv24oz17PA9yU6OMpb2JNhey7qskNNw5HyNHtTS8w2tG09HszjFJW1mdcZP-KIC_R4nyHQANKjNcmX0Dtuib_x9Ycrq7pUwhMqlaGlB5Ih0X0DCfdtGl-hpfCZd60S4GdLJ_tNRx6edEmRoIirA-NrD3Bh0RrbzSBW6gabIbDldFMeewqALcBLj-SWNX_quyBeVSyCM_nfY81c23tEkCzViBaTCA0BFJhToFVnn4mXdgnoTXYK8OIr89IAloNxhxHK3Qplm3U0wv3NukXQ8QxMFc8vLhjbb_SsOL1GCzZCgjgb7-If_oZekmHJ-oYBmU1iYUQRWWRoFYd8zVj2Y1sp3jC3xjlRh_843cYTmY2Uv04d8RXdg4Qiu-v8zWOM0FUERUo9ZELtxpAZKFWT8Ty9JmvA2xLl3cF2kVR_kTMxfLuPRXzTu5M6bA\"\n" +
            "  },\n" +
            "  \"tenantId\": null,\n" +
            "  \"uid\": \"sU5aZdP04FWLJLaoohkbmbqxjRs1\",\n" +
            "  \"_redirectEventId\": null\n" +
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

    public void AcessarGerenciadorCurso() throws InterruptedException {
        System.out.println("Iniciando o teste...");

        WebElement menuPrincipal = wait.until(
                ExpectedConditions.presenceOfElementLocated(
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

    private void clicarEmGerenciarCurso(String nomeDoCurso) {
        System.out.println("Localizando botão 'Gerenciar Curso' para o curso: " + nomeDoCurso);

        var cardsDeCurso = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class, 'MuiPaper-root') and .//h6]")
                )
        );

        WebElement botaoAlvo = null;
        for (WebElement card : cardsDeCurso) {
            String tituloDoCard = card.findElement(By.tagName("h6")).getText();
            System.out.println("A verificar card: " + tituloDoCard);

            if (tituloDoCard.equals(nomeDoCurso)) {
                botaoAlvo = card.findElement(By.xpath(".//button[contains(., 'Gerenciar Curso')]"));
                break;
            }
        }
        assertNotNull(botaoAlvo, "Não foi possível encontrar o botão 'Gerenciar Curso' para o curso: " + nomeDoCurso);

        js.executeScript("arguments[0].click();", botaoAlvo);
        System.out.println("Cliquei no botão 'Gerenciar Curso' do curso correto.");
    }


    private void navegarParaAbaAlunos() throws InterruptedException {
        System.out.println("A navegar para a aba 'Alunos'...");
        try {
            WebElement abaAlunos = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(.)='Alunos']")
            ));
            js.executeScript("arguments[0].click();", abaAlunos);


            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Buscar estudante por nome ou email...']")
            ));


            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(), 'Exibindo')]")
            ));

            System.out.println("Navegou para a aba 'Alunos' com sucesso.");
            Thread.sleep(500);

        } catch (Exception e) {
            fail("Falha ao navegar para a aba 'Alunos'. Elemento não encontrado: " + e.getMessage(), e);
        }
    }

    private void alterarRoleDoUsuario(String nomeAluno, String novaRole) throws InterruptedException {
        System.out.println("--- INICIANDO ALTERAÇÃO DE ROLE ---");
        System.out.println("Aluno: " + nomeAluno + " | Nova Role: " + novaRole);

        WebElement linhaAluno = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[contains(., '" + nomeAluno + "')]")
        ));

        WebElement dropdownRole = linhaAluno.findElement(By.xpath(".//div[contains(@class, 'MuiSelect-select')]"));
        System.out.println("Role atual: " + dropdownRole.getText());

        boolean menuAbriu = false;
        for (int i = 0; i < 3; i++) {
            try {
                new org.openqa.selenium.interactions.Actions(driver)
                        .moveToElement(dropdownRole)
                        .click()
                        .perform();

                wait.withTimeout(Duration.ofSeconds(2))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'MuiMenu-list')]")));

                menuAbriu = true;
                System.out.println("Menu aberto com sucesso na tentativa " + (i + 1));
                break;
            } catch (Exception e) {
                System.out.println("  Tentativa " + (i + 1) + " falhou. O menu não apareceu. Tentando novamente...");
                Thread.sleep(1000);
            }
        }

        if (!menuAbriu) {
            throw new RuntimeException("Falha crítica: O menu de opções não abriu após 3 cliques.");
        }

        try {
            WebElement opcao = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class, 'MuiMenuItem-root') and contains(normalize-space(.), '" + novaRole + "')]")
            ));
            opcao.click();
            System.out.println("  Clicou na opção '" + novaRole + "'.");
        } catch (Exception e) {
            System.out.println("ERRO: Não encontrei a opção '" + novaRole + "'. Opções visíveis:");
            List<WebElement> ops = driver.findElements(By.xpath("//li[contains(@class, 'MuiMenuItem-root')]"));
            for(WebElement op : ops) System.out.println("   > " + op.getText());
            throw e;
        }

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ul[contains(@class, 'MuiMenu-list')]")));
        } catch (Exception ignored) {}

        Thread.sleep(1000);
    }


    @Test
    public void CT21_01_promoverEstudanteParaProfessor() throws InterruptedException {

        String nomeDoCurso = "Curso com PIN G7";
        String alunoAlvo = "Marcus Vinicius Morini Querol Junior";
        String novaRole = "Professor";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        alterarRoleDoUsuario(alunoAlvo, novaRole);

        WebElement linhaAluno = driver.findElement(By.xpath("//tr[contains(., '" + alunoAlvo + "')]"));
        WebElement dropdownRole = linhaAluno.findElement(By.xpath(".//div[contains(@class, 'MuiSelect-select')]"));

        assertTrue(dropdownRole.getText().contains(novaRole),
                "ERRO: A role não mudou. Valor na tela: " + dropdownRole.getText());

        System.out.println("CT21_01 PASSOU: Usuário promovido para Professor.");
    }

    @Test
    public void CT21_02_tentarRebaixarAdminProprio() throws InterruptedException {
        // 1. DADOS DE TESTE
        String nomeDoCurso = "Curso sem PIN G7";
        String alunoAlvo = "Leonardo Goncalves Da Silva";

        AcessarGerenciadorCurso();
        clicarEmGerenciarCurso(nomeDoCurso);
        navegarParaAbaAlunos();

        System.out.println("Verificando se o Admin está bloqueado para edição...");

        WebElement linhaAluno = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[contains(., '" + alunoAlvo + "')]")
        ));

        WebElement dropdownRole = linhaAluno.findElement(By.xpath(".//div[contains(@class, 'MuiSelect-select')]"));

        WebElement containerSelect = linhaAluno.findElement(By.xpath(".//div[contains(@class, 'MuiInputBase-root')]"));

        boolean estaDesabilitado = containerSelect.getAttribute("class").contains("Mui-disabled")
                || "true".equals(dropdownRole.getAttribute("aria-disabled"));

        if (estaDesabilitado) {
            System.out.println(" SUCESSO: O dropdown de Role está desabilitado para o próprio Admin.");
        } else {
            try {
                dropdownRole.click();

                wait.withTimeout(Duration.ofSeconds(2))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'MuiMenu-list')]")));

                fail("FALHA DE SEGURANÇA: O menu abriu! O Admin não deveria poder alterar o seu próprio papel.");
            } catch (TimeoutException e) {
                System.out.println("SUCESSO: O menu não abriu ao clicar (bloqueio funcional confirmado).");
            }
        }

        System.out.println("CT21_02 PASSOU: Validação de segurança concluída.");
    }

}