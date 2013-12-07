/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package finansyx.commons.CashFlow;

import finansyx.commons.Statistics;
import java.util.HashMap;

/**
 *
 * @author t4r0
 */
public class MARRManager{
    
    private Double inflation=0.;
    private HashMap<String,MARRStructure> MARRp = new HashMap<>();
    private Double marr = 0.;
    
    public MARRManager(){}
    
    public MARRManager(Double f){ this.inflation = f;}
    
     public MARRManager(String f){ this.inflation = Statistics.percentageFromString(f);}
    
    public void addInvestor(String name, MARRStructure marr)
    {
        this.MARRp.put(name, marr);
        this.marr += marr.getPonderateMarr();       
    }
    
    public Double getMarr(){return marr;}
}
