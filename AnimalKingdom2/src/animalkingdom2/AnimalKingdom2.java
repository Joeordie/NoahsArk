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
public class AnimalKingdom2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnimalBuilder tada = new AnimalBuilder();
        tada.printMenu();
    }
    
    public static void exit(){
        System.exit(0);
    }
    
}
