/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalkingdom2;

/**
 *
 * @author joeordie
 */
public class Mammal extends Animal{
    //sub class of animal
    
    public Mammal(String passName){
        super(passName);
        super.setRespiratory("lungs");
    }
    
    public void liveBirth(){
        //something involving birth.
    }
    
}
