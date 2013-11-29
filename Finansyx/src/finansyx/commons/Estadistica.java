/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finansyx.commons;
import finansyx.commons.Finanzas.Finanzas;
import org.apache.commons.math3.distribution.*;
/**
 *
 * @author t4r0
 */
public class Estadistica {
    
    /**
     * Calcula el valor z, en una distribución normal estándar
     * con los grados de  confianza dados
     * @param confianza = los grados para los cuales se calculará Z
     * @return el valor de Z de acuerdo a una distribución normal estándar
     */
    public static Double zNormal(Double confianza)
    {
        
        NormalDistribution dist = new NormalDistribution(0, 1);
        Double a2 = (1 - confianza) / 2;
        Double z = -1 * dist.inverseCumulativeProbability(a2);        
        return Finanzas.Aproximar(z, 3);
                
    }
    
    /**
     * Calcula los valores pre-establecidos para 3 niveles de confianza,
     * en limites bilaterales con los grados de confianza dados.
     * 
     * @param confianza-cualquiera de los valores 90, 95, 99
     * @return el valor de z de acuerdo a una distribución normal estándar
     */
    public static Double FixedZ(int confianza)
    {
        switch(confianza)
        {
            case 90:
                return 1.65;
            case 95:
                return 1.96;
            case 99:
                return 2.58;
        }
        return 0.;
    }
}
