package com.curso.cucumber.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class InserirContasSteps {

    private WebDriver driver; //global para poder ser usada em outros metodos

    @Dado("^que desejo adicionar uma conta$")
    public void queDesejoAdicionarUmaConta() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "/Users/ricardoveiga/Drivers/chromedriver"); //configuraçãp por estar no mac e não saber configurar
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("ricardoveiga.ti@gmail.com");
        driver.findElement(By.name("senha")).sendKeys("123456");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("^adiciono a conta \"([^\"]*)\"$")
    public void adicionoAConta(String arg1) throws Throwable {
        driver.findElement(By.id("nome")).sendKeys(arg1);
        driver.findElement(By.tagName("button")).click();
    }


    @Dado("^que estou acessando a aplicação$")
    public void queEstouAcessandoAAplicação() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "/Users/ricardoveiga/Drivers/chromedriver"); //configuraçãp por estar no mac e não saber configurar
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");

    }

    @Quando("^informo o usuário \"([^\"]*)\"$")
    public void informoOUsuário(String arg1) throws Throwable {
        driver.findElement(By.id("email")).sendKeys(arg1);
    }

    @Quando("^a senha \"([^\"]*)\"$")
    public void aSenha(String arg1) throws Throwable {
        driver.findElement(By.name("senha")).sendKeys(arg1);
    }

    @Quando("^seleciono entrar$")
    public void selecionoEntrar() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^visualizo a página inicial$")
    public void visualizoAPáginaInicial() throws Throwable {
        String texto = driver.findElement((By.xpath("//div[@class='alert alert-success']"))).getText();
        Assert.assertEquals("Bem vindo, ricardo!", texto);
    }

    @Quando("^seleciono Contas$")
    public void selecionoContas() throws Throwable {
        driver.findElement(By.linkText("Contas")).click();
    }

    @Quando("^seleciono Adicionar$")
    public void selecionoAdicionar() throws Throwable {
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("^informo a conta \"([^\"]*)\"$")
    public void informoAConta(String arg1) throws Throwable {
        driver.findElement(By.id("nome")).sendKeys(arg1);
    }

    @Quando("^seleciono Salvar$")
    public void selecionoSalvar() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^a conta é inserida com sucesso$")
    public void aContaÉInseridaComSucesso() throws Throwable {
        String texto = driver.findElement((By.xpath("//div[@class='alert alert-success']"))).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", texto);

    }

    @Então("^sou notificar que o nome da conta é obrigatório$")
    public void souNotificarQueONomeDaContaÉObrigatório() throws Throwable {
        String texto = driver.findElement((By.xpath("//div[@class='alert alert-danger']"))).getText();
        Assert.assertEquals("Informe o nome da conta", texto);
    }

    @Então("^sou notificado que já existe uma conta com esse nome$")
    public void souNotificadoQueJáExisteUmaContaComEsseNome() throws Throwable {
        String texto = driver.findElement((By.xpath("//div[@class='alert alert-danger']"))).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", texto);
    }

    @Então("^recebo a mensagem \"([^\"]*)\"$")
    public void receboAMensagem(String arg1) throws Throwable {
        //usando starts-with para deixar mais genérica para atender todas validaçoes da mensagem
        String texto = driver.findElement((By.xpath("//div[starts-with(@class, 'alert alert-')]"))).getText();
        Assert.assertEquals(arg1, texto);
    }

    @Before(order = 1,  value = "@funcionais") // Before começa na ordem 0, 1, 2 do io.cucumber
    public void inicio(){
        System.out.println("Começando aqui!");
    }

    @Before(order = 0,  value = "@funcionais") // incluido a tag funcionais value se não os outros runners vão executar antes
    public void inicio2(){
        System.out.println("Começando aqui, parte 2!");
    }

    //after do io.cucumber
    @After(order = 1, value = "@funcionais") // After começa na ordem do maior para menor diferente do before
    public void screenShot(Scenario cenario){  //se der erro lanca exceção, basta clicar q a ide complementa
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //@After(order = 0, value = "~@unitários") //para executar depois do screenshot, value serve para tirar o after do hook @unitários e não ser executado
    @After(order = 0, value = "@funcionais")
    public void fecharBrowser(){
        driver.quit();
        System.out.println("Terminando");
    }

}
