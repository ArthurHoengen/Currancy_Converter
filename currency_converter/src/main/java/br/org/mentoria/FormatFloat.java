package br.org.mentoria;

import com.ibm.icu.text.DecimalFormat;

public class FormatFloat {

    public String formatarFloat(float numero){
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#.00");
        try{
          retorno = formatter.format(numero);
        }catch(Exception ex){
          System.err.println("Erro ao formatar numero: " + ex);
        }
        return retorno;
      }
}
