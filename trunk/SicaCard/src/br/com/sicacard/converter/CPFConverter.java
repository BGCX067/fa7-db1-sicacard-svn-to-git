package br.com.sicacard.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 * CPFConverter.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 05/02/2011
 * @version 1.0
 */
public class CPFConverter implements Converter {

   /**
    * Esse método irá converter CPF formatado para um sem pontos e traço. Ex.:
    * 355.245.198-87 torna-se 35524519887.
    * 
    */
   @Override
   public Object getAsObject(final FacesContext context,
         final UIComponent component, final String value) {
      String cpf = value;
      if (value != null && !value.equals("")) {
         cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
      }

      return cpf;
   }

   /**
    * Esse mátodo irá converter CPF não formatado para um com pontos e traço.
    * Ex.: 35524519887 torna-se 355.245.198-87.
    */
   @Override
   public String getAsString(final FacesContext context,
         final UIComponent component, final Object value) {
      String cpf = (String) value;
      if (cpf != null && cpf.length() == 11) {
         cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
               + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
      }

      return cpf;
   }
}
