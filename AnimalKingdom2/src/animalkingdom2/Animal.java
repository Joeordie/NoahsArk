/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalkingdom2;
import java.util.Map; 
import java.util.HashMap;
     
/**
 *
 * @author joeordie
 */
public class Animal {
    //Set properties or global vairables for the object. 
    public String name; 
    private int legCount; 
    private boolean fly; 
    private boolean swim;
    private String respiratory;
    private String diet;
   
    //special constructor
    public Animal(String passName) {
        this.name = passName;
    }
        
    public void setLeg(int numberLegs){
        this.legCount = numberLegs;
    }
    
    public void setFly(boolean flight){
        this.fly = flight;
    }
        
    public void setSwim(boolean swim){
        this.swim = swim;
    }
    
    public void setDiet(String dietType){
        this.diet = dietType;
    }
    
    public void setRespiratory(String respiratory){
        this.respiratory = respiratory;
    }
    
    public Map stats(){
        Map<String, String> workingMap = new HashMap<String, String>();
        try{ 
            workingMap.put("name", name);
            workingMap.put("legs", Integer.toString(legCount));
            workingMap.put("fly", Boolean.toString(fly));
            workingMap.put("swim", Boolean.toString(swim));
            workingMap.put("respiratory", respiratory);
            workingMap.put("diet", diet);
        }
        catch(Exception E){
            
        }
    return workingMap;
    }
    
}
