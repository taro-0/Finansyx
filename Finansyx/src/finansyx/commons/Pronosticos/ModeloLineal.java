/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finansyx.commons.Pronosticos;

import finansyx.commons.Finanzas.Finanzas;
import java.util.ArrayList;

/**
 *
 * @author t4r0
 */
public class ModeloLineal extends Modelo{

    public ModeloLineal() {
        super ();
        setNombre("Lineal");
        
    }
    
    public ModeloLineal(ArrayList<Integer> x, ArrayList<Double> y)
    {       
        super(x, y);
        setNombre("Lineal");
    }
    
    @Override
    public Double Calcular(Integer x)
    {
        return a*x + b;
    }
    
    @Override
    public void Sumatorias(ArrayList<Integer> x, ArrayList<Double> y)           
    {
        Double varY = 0.0;
        Integer varX = 0;
        super.Sumatorias(x, y);
        for(int i =0; i < y.size(); i++)
        {
            varX = x.get(i);
            varY = y.get(i);
            sumX += varX;
            sumY += varY;
            sumXY += varX * varY;
            sumX2 += Math.pow(varX, 2);
            sumY2 += Math.pow(varY, 2);
        }
        n = y.size();
    }
    
    @Override
    public void CalcularVariables()
    {
        Double numerador = (n*sumXY) - (sumX*sumY);
        Double denominador = (n*sumX2) - Math.pow(sumX, 2);        
        a = numerador / denominador;        
        b = Finanzas.Aproximar((sumY / n) - a*(sumX/n), 4);
        a = Finanzas.Aproximar(a, 4);
    }
}
