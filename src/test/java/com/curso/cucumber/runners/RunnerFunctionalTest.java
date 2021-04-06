package com.curso.cucumber.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        //obs: para o junit erro e falha são diferentes, tem tratamento distinto, para o cumber não possui distinção
        //features = "src/test/resources/features/inserir_conta.feature",
        features = "src/test/resources/features/", // mudando o Runner para executar todos testes com hooks
        glue = {"com.curso.cucumber.steps", "com.curso.cucumber.converters"},
        tags = {"@funcionais", "not @ignore"}, //colocando tag na feature
        plugin = {"pretty", "html:target/report-html", "json:target/report.json"}, //log e relatório
        monochrome = true,  //melhorar log de execuçao, sem caracteres
        snippets = CucumberOptions.SnippetType.CAMELCASE, //seguir padrao de camelcase nos nomes da  otaçao dos metodos, em vez de usar underline
        dryRun = false, //usado para verificar se os passos estão corretos, se mudarmos para true, daria para executar os testes e validar o mapeamento dos passos com métodos, quando tiver true vai pular tudo
        strict = false
        )

public class RunnerFunctionalTest {
        @BeforeClass  //do junit
        public static void reset(){  //tem que ser static por causa do junit
                System.setProperty("webdriver.chrome.driver", "/Users/ricardoveiga/Drivers/chromedriver");
                WebDriver driver = new ChromeDriver();
                driver.get("https://seubarriga.wcaquino.me/");
                driver.manage().window().maximize();
                driver.findElement(By.id("email")).sendKeys("ricardoveiga.ti@gmail.com");
                driver.findElement(By.name("senha")).sendKeys("123456");
                driver.findElement(By.tagName("button")).click();
                driver.findElement(By.linkText("reset")).click();
                System.out.println("Terminado beforeclass");
                driver.quit();
        }

}
