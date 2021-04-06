package com.curso.cucumber.steps;

import cucumber.api.PendingException;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AprenderCucumberSteps {

    @Dado("^que criei o arquivo corretamente$")
    public void que_criei_o_arquivo_corretamente() throws Throwable {
        System.out.println("Passou aqui");
    }

    @Quando("^executá-lo$")
    public void executá_lo() throws Throwable {
    }

    @Então("^a especificação deve finalizar com sucesso$")
    public void a_especificação_deve_finalizar_com_sucesso() throws Throwable {
    }


    private int contador = 0;

    @Dado("^que valor do contador é (\\d+)$")
    public void queValorDoContadorÉ(int arg1) throws Throwable {
        contador = arg1;
    }

    @Quando("^eu incrementar em (\\d+)$")
    public void euIncrementarEm(int arg1) throws Throwable {
        contador = contador + arg1;
    }

    @Então("^o valor do contador será (\\d+)$")
    public void oValorDoContadorSerá(int arg1) throws Throwable {
        System.out.println(arg1);
        System.out.println(contador);
        System.out.println(arg1 == contador);
        //Assert.assertTrue(arg1 == contador);
        Assert.assertEquals(arg1, contador);
        //throw new RuntimeException();
    }

/*Passo para aprender regex, expressoes regulares
Forma antiga de calcular data
    Date entrega = new Date();
    @Dado("^que a entrega é dia (\\d+)/(\\d+)/(\\d+)$")
    public void queAEntregaÉDia(int dia, int mes, int ano) throws Throwable {
        Calendar cal = Calendar.getInstance(); //inicializando calendario
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes - 1); //no java mes 0 é igual janeiro, coisas do java
        cal.set(Calendar.YEAR, ano);
        entrega = cal.getTime();
    }

 */

    Date entrega = new Date();
    @Dado("que a entrega é dia {data}")  //recebendo tipo data do registry, tive que tirar a parte do regrex ^ $
    public void queAEntregaÉDia(Date data) throws Throwable {

        entrega = data;
    }

    @Quando("^a entrega atrasar em (\\d+) (dia|dias|mes|meses)$") // (.+) com mudança da expressao para atender dois cenarios, temos que tratar a diferença de mes e dias
    public void aEntregaAtrasarEmDias(int arg1, String tempo) throws Throwable {
        //adicionando dias a data inicial
        Calendar cal = Calendar.getInstance();
        cal.setTime(entrega);
        if(tempo.equals("dias")) {
            cal.add(Calendar.DAY_OF_MONTH, arg1);
        }
        if(tempo.equals("meses")) {
            cal.add(Calendar.MONTH, arg1);
        }
        entrega = cal.getTime();
    }

    @Então("^a entrega será efetuada em (\\d{2}+\\/\\d{2}+\\/\\d{4}+)$") //passo para aprender o regex101  (\d{2}+\/\d{2}+\/\d{4}+)
    public void aEntregaSeráEfetuadaEm(String data) throws Throwable {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); //MMMM em extenso = abril
        String dataFormatada = format.format(entrega);
        Assert.assertEquals(data, dataFormatada);

    }


}
