/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalkingdom2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


/**
 *
 * @author joeordie
 */
public class AnimalBuilder {
    //Set up input object for periodic use
    Scanner input = new Scanner(System.in);
    //Set up arrayList of Animal classed objects
    ArrayList<Animal> ark = new ArrayList();
    String pause;
    Animal workingAnimal;
    
    public void main(){
        //created a main loop to get rid of the recursion. 
        while (true){
            userMenu();
        }
    }
    
        
    public void userMenu() {
        System.out.println("Welcome to Animal Builder!"
        + "\nPlease chose what you want to do:\n"
        + "1. create animal\n"
        + "2. Delete an Animal\n"
        + "3. Print Manifest\n"
        + "4. See an animal's stats\n"
        + "5. last working ark!\n"
        + "6. Write out to file.\n"
        + "7. Exit");
        
         String choice = input.nextLine();
        
        JsonMajig tablet = new JsonMajig();
        if(choice.equals("1")|choice.equals("2")|choice.equals("3")|choice.equals("4")|choice.equals("5")|choice.equals("6")|choice.equals("7")){
                switch (choice) {
                case "1":
                        buildCreature();
                        
                        break;
                case "2":
                    // moved the validity check out of the animalchoosermethod. 
                        workingAnimal = animalChooser();
                        if (workingAnimal == null){
                            System.out.println("Sorry that wasn't recognized. Try again.");
                            workingAnimal = animalChooser();
                        }
                        deleteCreature(workingAnimal);
                        
                        break;
                case "3":
                        arkManifest();
                        
                        break;
                case "4":
                        workingAnimal = animalChooser();
                        if (workingAnimal == null){
                            System.out.println("Sorry that wasn't recognized. Try again.");
                            workingAnimal = animalChooser();
                        }
                        discription(workingAnimal);
                        
                        break;
                case "5":
                        ark = tablet.readFile();
                        arkManifest();
                        
                        break;
                case "6":
                        tablet.arkWriteOut(ark);
                        
                        break;
                case "7":
                        AnimalKingdom2.exit();
                        //need to change this so that it writes out the ark when were done. 

                }
        }
        else{
            System.out.println("that is not an allowed input!\nTry again.");
        }
    }
    
    public Animal animalChooser(){
        //A simple method that allows for a specific animal in the ark to be chosen then passed to delete or print functions. 
        workingAnimal = null;
        
        arkManifest();
       
        System.out.println("\nPlease type the name of one of the above animals.");
        String AnimalName = input.nextLine();
        for (Animal name: ark){
            if (AnimalName.equals(name.name)){
                workingAnimal = name;
            } 
        }
       
        return workingAnimal;
    }
    
    public void buildCreature(){
        //Dialog to create a creature.....
        System.out.println("");
        System.out.println("what kind of creature will we make today?");
        System.out.println(" Type: invert, reptile, mammal");
        String response = input.nextLine(); 
        //used an Array because IDE freaks out that a single variable might not
        //be initialized
        String[] type = new String[3];
        //initiate object Animal called 'passable'
        Animal passable;
        //switch for response
        switch(response)
        {
            case "mammal": 
                type[0] = nameit();
                //initiate mammal class inherited from Animal. 
                Mammal hotblooded = new Mammal(type[0]);
                passable = hotblooded;
                //ask if you want to load it onto the ark? 
                build(passable);
                break;
            case "reptile": 
                type[1] = nameit();
                Reptile coldblooded = new Reptile(type[1]);
                passable = coldblooded;
                build(passable);
                break;
            case "invert": 
                type[2] = nameit();
                Invert bug = new Invert(type[2]);
                passable= bug;
                build(passable);
                break;
            default :
                System.out.println("invalid type!");
                buildCreature();
        } 
    }
     
    public void build(Animal creature){
        //set creature type then ask questions about characteristics follows buildCreature() method.
        Animal workingCreature = creature;
        workingCreature.setDiet(diet());
        workingCreature.setSwim(YoN("Does the creature swim?"));
        workingCreature.setFly(YoN("Does the creature fly"));
        workingCreature.setLeg(legs());
        discription(workingCreature);
        ark(workingCreature);
        }
    
     private void ark(Animal passable){
         // load the ark up!!!
        Animal workingAnimal = passable;
        System.out.println("Would you like to add it to the ark?");
        System.out.println("Type: (y/n):");
        String answer = input.nextLine();
        if (answer.equals("y")|answer.equals("Y")){
            ark.add(workingAnimal);
        }
        else if(answer.equals("n")|answer.equals("N")){
            System.out.println(workingAnimal.name + " is set free!");
        }
        else{
            System.out.println("invalid entry");
        }
        arkManifest();
    }
    
    private void arkManifest(){
        //print out the creatures on the ark. now with Iterator class. 
       System.out.println("\nNow on board are:\n");
       Iterator itr = ark.iterator();
        while(itr.hasNext()){
            Animal localAnimal = (Animal) itr.next();
            System.out.println(localAnimal.name);
        }  
        System.out.println("\nhit any key to continue.\n");
        pause = input.nextLine();
    }
    
    private String nameit(){
        //method to name the creature. 
        String name;
        System.out.println("What will you name your creature?");
        name = input.nextLine();
        return name; 
    }
    
    
    private boolean YoN(String passQuestion){
        //method to speed questions during building
        String strQues = passQuestion;
        boolean answer = false;
        System.out.println(strQues);
        System.out.print("y/n?: ");
        String response;
        response = input.nextLine();
        if (response.equals("y")|response.equals("Y")){
            answer = true;
        }
        return answer;
    }
    
    private int legs(){
        //number of legs during building
        int answer = 0;
        System.out.println("How many legs does this creature have?");
        String response = input.nextLine();
        //expecting a number so input control
        try{
            answer = Integer.parseInt(response);
        }
        catch(Exception E){
            System.out.println("that was not a number!");
            legs();
        }
        if (answer == 0){
            System.out.println("you should call it lucky");
        }
        return answer;
    }
    
    private String diet(){
        //logic to decide how a creature eats. 
        String answer = "unknown";
        boolean meatResponse = YoN("does this creature eat meat?");
        boolean vegResponse = YoN("does this creature eat vegetation?");
        if (meatResponse == true && vegResponse == false) answer = "carnivore";
        if (meatResponse == false && vegResponse == true) answer = "herbivore";
        if (meatResponse == true && vegResponse == true) answer = "omnivore";     
        return answer;
    }           
    
    public void discription(Animal workingCreature){
        //prints creature stats, object should be passed to method. 
        Map<String, String> stats = workingCreature.stats();
        for (Map.Entry<String, String> entry : stats.entrySet()){
            if (entry.getKey()== "name"){
                System.out.println("name: " + entry.getValue());
            }
            
        }
        for (Map.Entry<String, String> entry : stats.entrySet()){
            if (entry.getKey() != "name"){
                System.out.println(entry.getKey()+": "+entry.getValue());
            } 
            
        }
        System.out.println("\nany key to continue.\n");
        pause = input.nextLine();
        
    }

    private void deleteCreature(Animal workingAnimal) {
        //get that thing off the ark. 
        Animal local = workingAnimal;
        ark.remove(local);
        System.out.println("\n"+local.name + " has been removed from the ark.\n");
    }
        
}
    