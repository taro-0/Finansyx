/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finansyx.commons.FlujoDeCaja.Rules;

import finansyx.commons.FlujoDeCaja.Financiera;
import java.util.ArrayList;
/**
 *
 * @author t4r0
 */
public class StaticCalcRule extends Rule{
    
    public StaticCalcRule()
    {
        
    }
    
    public StaticCalcRule(Double value)
    {
        Value = value;
    }

    @Override
    public Financiera Calcular() {
       ArrayList<Double> val = new ArrayList<Double>();
       for(int i =0; i < largo; i++);
            val.add(Value);
       return new Financiera(val);
    }

    @Override
    public Financiera Calcular(Integer inicio) {
       ArrayList<Double> val = new ArrayList<Double>();
      for(int i=inicio; i < largo; i++)
          val.add(Value);
      return new Financiera(val);
    }
    
    
    
    
}
