package br.com.sicacard.validator;



import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sicacard.util.ApplicationConstants;


/**
 * CPFValidator.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 05/02/2011
 * @version 1.0
 */
public class CPFValidator implements Validator {

   /*
    * (non-Javadoc)
    * 
    * @see
    * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
    * javax.faces.component.UIComponent, java.lang.Object)
    */
   @Override
   public void validate(final FacesContext arg0, final UIComponent arg1,
         final Object arg2) {
      
      final String cpf = (String) arg2;
      
      if (cpf.length() != ApplicationConstants.QUANTIDADE_NUMEROS_CPF) {
         defineErroValidacao();
      }
      
      final String valoresInvalidos[] = { "00000000000", "11111111111",
            "22222222222", "33333333333", "44444444444", "55555555555",
            "66666666666", "77777777777", "88888888888", "99999999999" };
      
      for (final String valoresInvalido : valoresInvalidos) {
         if (cpf.equals(valoresInvalido)) {
            defineErroValidacao();
         }
      }

      // Aqui começa a checagem do CPF
      int posicao, i, soma, dv, dvInformado;
      final int digitos[] = new int[11];
      //Retira os dois últimos dígitos do número informado
      dvInformado = Integer.parseInt(cpf.substring(9, 11));

      // Desemembra o número do CPF na array DIGITO
      for (i = 0; i <= 8; i++) {
         digitos[i] = Integer.parseInt(cpf.substring(i, i + 1));
      }

      // Calcula o valor do 10ª dígito da verificação
      posicao = 10;
      soma = 0;
      for (i = 0; i <= 8; i++) {
         soma = soma + digitos[i] * posicao;
         posicao = posicao - 1;
      }
      digitos[9] = soma % 11;
      if (digitos[9] < 2) {
         digitos[9] = 0;
      } else {
         digitos[9] = 11 - digitos[9];
      }

      // Calcula o valor do 11ª dígito da verificação
      posicao = 11;
      soma = 0;
      for (i = 0; i <= 9; i++) {
         soma = soma + digitos[i] * posicao;
         posicao = posicao - 1;
      }
      digitos[10] = soma % 11;
      if (digitos[10] < 2) {
         digitos[10] = 0;
      } else {
         digitos[10] = 11 - digitos[10];
      }

      // Verifica se os valores dos dígitos verificadores conferem
      dv = digitos[9] * 10 + digitos[10];
      if (dv != dvInformado) {
         defineErroValidacao();

      }

   }

   /**
    * Método responsável por definir o erro de validação de CPF
    * e lançar uma exceção apropriada.
    */
   private void defineErroValidacao() {
      FacesMessage message = new FacesMessage();
      message.setDetail("CPF inválido!");
      message.setSummary("CPF inválido!");
      message.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(message);
   }

}
