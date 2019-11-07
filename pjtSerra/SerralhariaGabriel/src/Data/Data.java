/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Bruno Mezenga
 */
public class Data {
    // y = ano, M = mês do ano, d = dia do mês, D = dia no ano
    // H = hora no dia, h = hora no am/pm, m = minuto na hora
    // s = segundos no minuto, S = numeros de milissegundos
    //Pega a data do sistema
    public static String Data_Sistema() {
        Date data = new Date();
        System.out.println("Data: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("hh:mm:ss");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String data_Formatada = form.format(data);
        String hora_Formatada = hour.format(data);
        System.out.println(data_Formatada);
        System.out.println(hora_Formatada);
        return data_Formatada;
    }



}
