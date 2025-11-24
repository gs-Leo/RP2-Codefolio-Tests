package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Chave {

    private static final String FIREBASE_KEY = "firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]";
    private static final String FIREBASE_VALUE = "{\n" +
            "  \"fbase_key\": \"firebase:authUser:AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co:[DEFAULT]\",\n" +
            "  \"value\": {\n" +
            "    \"apiKey\": \"AIzaSyAPX5N0upfNK5hYS2iQzof-XNTcDDYL7Co\",\n" +
            "    \"appName\": \"[DEFAULT]\",\n" +
            "    \"createdAt\": \"1763485802422\",\n" +
            "    \"displayName\": \"Marcus Vinicius Morini Querol Junior\",\n" +
            "    \"email\": \"marcusquerol.aluno@unipampa.edu.br\",\n" +
            "    \"emailVerified\": true,\n" +
            "    \"isAnonymous\": false,\n" +
            "    \"lastLoginAt\": \"1763826702496\",\n" +
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
            "      \"accessToken\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ1YTZjMGMyYjgwMDcxN2EzNGQ1Y2JiYmYzOWI4NGI2NzYxMjgyNjUiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTWFyY3VzIFZpbmljaXVzIE1vcmluaSBRdWVyb2wgSnVuaW9yIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0lQU1ZKRjVaanRMd2o2TW5kS003aTF5cDJ0RGJNbjEzRGtrbUxjcHRiZlc3RlJRNzQ9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGVzLWNvZGVmb2xpbyIsImF1ZCI6InRlc3Rlcy1jb2RlZm9saW8iLCJhdXRoX3RpbWUiOjE3NjM4MjY3MDIsInVzZXJfaWQiOiJYdjBYNlllYTRIWEhpbkxNbUJhYTFqcURHeDYyIiwic3ViIjoiWHYwWDZZZWE0SFhIaW5MTW1CYWExanFER3g2MiIsImlhdCI6MTc2MzkxMDA3MiwiZXhwIjoxNzYzOTEzNjcyLCJlbWFpbCI6Im1hcmN1c3F1ZXJvbC5hbHVub0B1bmlwYW1wYS5lZHUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwNzIyNjI2NTY4NTE5MTU3NDI5MSJdLCJlbWFpbCI6WyJtYXJjdXNxdWVyb2wuYWx1bm9AdW5pcGFtcGEuZWR1LmJyIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.h2BYQXiv92am039GwPXTn_NZ3j0cMKICl97iEYGhUYwS8ZBxERdFuobhzho_R0c4vCxs982jOPJkr52vhecOjkKt4PGu-zfBG-u5cyfmJTf6JLoe-yw-x5XzmlhMG6BwFoGnJiPWxhZMWo8j4fWRqLH2REoIA_krATdJRNQ3v-AHB1b_hmQq-3MZGXdgbbyhOCmAs7pGh0bND8t60tFBa9XcDWfTbxRCQpZGovtoPdcXNDqwXwNpRIAM_BTWc6Ab4I3FVtV_WVXeLYrSGITNKwNdKRarxRylWB-ofoYPrNmgOoRhHkF_-SVJ_dSwblOomrNnULRCZrxjqWEuLvDg7w\",\n" +
            "      \"expirationTime\": 1763913673688,\n" +
            "      \"refreshToken\": \"AMf-vBz5jnW0LigejTyKNDxu3QcshADb50svRxmcAzuvqucEhrdKbppKZDJBhqF2ldKXZXtOuIi6RDGInnE2mLE5IFxG1PLxay-ntpvUYstRB9PZtBSTaoOHcmIVXaQvzrmi6j57GuyHuok5UoKOQngOhrhMsRGoYgCryTBPjwjqM9Apq4phtOqrFX0GkAJ6qPImPH2-1cESxkZwZLHSzSUEnQpKlA5Zd3fCQLced_WGRNPIeR6vGYD7w-yBSMSijNF_L8TqjM0orY_iVG4VPQykXaraAop9vq_VZmOEA8twIRGCE4vbg-Dsr17c_6CC22NNsnMLevCoXDFiu68LrYSGFXtUmF8p0rCl67FV9nrQRczMFrD-Sxe1IvSvnxj6UfFE8VIlxpCFXSXAJE7Mi6NUul5oP8w9xQ9MiOCGK-GOGsBpqlzPFwLjKmYNm9Xzf4cB7uxZc-g8MkHzuJzAlaU_-G3OvIhV8RXeiTOfyigNEDxA-iyj33o\"\n" +
            "    },\n" +
            "    \"tenantId\": null,\n" +
            "    \"uid\": \"Xv0X6Yea4HXHinLMmBaa1jqDGx62\",\n" +
            "    \"_redirectEventId\": null\n" +
            "  }\n" +
            "}";

    // --- URL (Agora fica aqui dentro!) ---
    private static final String URL_BASE = "https://testes-codefolio.web.app/";

    /**
     * Método atualizado para receber APENAS o driver.
     * A URL ele pega ali de cima.
     */
    public static void realizarLogin(WebDriver driver) throws InterruptedException {
        // 1. Abre o site
        driver.get(URL_BASE);

        // 2. Injeta o Token
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println("💉 [Chave Mestra] Injetando token...");

        try {
            js.executeScript("window.localStorage.clear();");
            js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", FIREBASE_KEY, FIREBASE_VALUE);
        } catch (Exception e) {
            System.out.println("❌ Erro na injeção da Chave: " + e.getMessage());
        }

        // 3. Atualiza para logar
        driver.navigate().refresh();
        Thread.sleep(3000);
    }
}