/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finansyx.commons.Pronosticos;

import java.util.ArrayList;

/**
 * Un gestor, que ayuda a controlar multiples modelos de pronosticación
 * @author t4r0
 */
public class ModelManager {
    
    
    // Los modelos almacenados en este gestor
    ArrayList<Modelo> models = new ArrayList<>();
    //El modelo que se usará para calcular los valores de salida de este gestor
    Modelo selectedModel = new Modelo();
    //El nivel de confianza que se utilizará para calcular valores estadísticos
    Double confianza = 0.;
    //El indice del modelo seleccionado
    Integer index = -1;
    public ModelManager(){}
    
    /**
     * Inicia una instancia de esta clase con valores inciales
     * @param modelos los modelos dentro de los cuales se calculará el optimo
     * para resolver el problema especifico
     * 
     */
    public ModelManager(ArrayList<Modelo> modelos){
        this.models= modelos;
        chooseModel();
    }
    
    /**
     * Inicia una nueva instancia de esta clase, con los modelos especificados y
     * el grado de confianza dado
     * @param modelos - los modelos dentro de los cuales se escogerá el optimo
     * @param confianza - Los grados de confianza que se usarán para resolver el
     * problema específico
     */
     public ModelManager(ArrayList<Modelo> modelos, Double confianza){
        this.models= modelos;
        this.confianza = confianza;
        chooseModel();
    }
     
     /**
      * Establece los grados de confianza que se usarán para calcular los limites
      * @param confianza - Los grados de confianza para calcular los valores estadisticos a partir de esta clase 
      */
     public void setConfianza(Double confianza)
     {
         this.confianza = confianza;
         chooseModel();
     }
     
     /**
      * Escoge el modelo adecuado 
      */
    public final void chooseModel()
    {
        Modelo choos = models.get(0);
        Modelo mod  = new Modelo();
        index = 0;
        for(int i=0; i< models.size(); i++)
        {
            mod = models.get(i);
            if(isCandidate(mod, choos))
            {    
                choos = mod;
                index = i;
            }
            else
               continue;
        }
        selectedModel= choos;
    }
    
    /***
     * 
     * @return - El modelo escogido para calcular los datos de salida de este 
     * gestor
     */
    public Modelo getSelectedModel()
    {
        return selectedModel;
    }
    
    /**
     * Escoge si el Modelo a es candidato para reemplazar a b
     * @param a - el modelo que se evaluará
     * @param b - el modelo que se pretende sustituir
     * @return si el modelo A, es candidato para sustituir a b.
     */
    protected Boolean isCandidate(Modelo a, Modelo b)
    {
         if(a.getSXY() < b.getSXY())
             return true;
           if (a.getSXY() == b.getSXY())
            {
                if (a.getr() > b.getr())
                    return true;
                if (a.getr() < b.getr() && a.getr() < b.getr())
                    return false;
                if (a.getr() == b.getr() && a.getr2() > b.getr2())
                    return true;                
            }
        return false;
    }
    
    /**
     * Establece el modelo que se quiere escoger, sin tomar en cuenta las
     * politicas de optimización
     * @param i - el indice donde se encuentra el modelo
     */
    public void setSelectedModel(int i)
    {
        if(i >= models.size() || i < 0)
            return;
        selectedModel = models.get(i);                
    }
    
    /**
     * Devuelve el modelo almacenado en el indice i
     * @param i - el indice de donde se obtendrá el modelo
     * @return - el modelo almacenado en el indice i
     */
    public Modelo get(int i)
    {
        index = i;
        return models.get(i);       
    }
    
    public Integer getSelectedModelIndex()
    {
        return index;
    }
    /**
     * Agrega un modelo de pronosticación a este gestor
     * @param modelo - el modelo que se desea agregar
     */
    public void Add(Modelo modelo)
    {
        models.add(modelo);
    }
    
    /**
     * 
     * @return la cantidad de modelos en este gestor
     */
    public int Size()
    {
        return models.size();
    }
    
    /**
     * Calcula un valor en base a un punto específico, haciendo uso del modelo 
     * escogido
     * @param punto - el valor independiente a partir del cual se calculará
     * el valor dado por el modelo
     * @return un valor dado por el modelo
     */
    public Double Calcular(Integer punto)
    {
        return selectedModel.Calcular(punto + selectedModel.n);
    }
    
    /**
     * Calcula el limite superior, con los grados de confianza dados
     * @param punto - el punto que se ploteará
     * @return el limite superior de confianza
     */
    public Double UpperLimit(Integer punto)
    {
         return selectedModel.UpperLimit(punto, confianza);
    }
    
    /**
     * Calcula el limite inferior con los grados de confianza dados
     * @param punto - el punto que se ploteará
     * 
     * @return el limite inferior de confianza
     */
     public Double LowerLimit(Integer punto)
    {
         return selectedModel.LowerLimit(punto, confianza);
    }
}
