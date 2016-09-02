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
public class Invert extends Animal{
    //sub class of animal. 
    public Invert(String passName){
        super(passName);
        super.setRespiratory("gills or skin related oxygen absorbtion");
    }
    public void layEggs(){
        //something about eggs.
    }
}
