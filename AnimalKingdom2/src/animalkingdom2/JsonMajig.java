/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalkingdom2;

//this is the simple.json library created by google.
//used for Hadoop and other high performance cases where 
//JSON must be writted to a database and transmitted. 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author joeordie
 */
public class JsonMajig {
    //you must change your filepath to where you want the data to land
    String filepath = "/home/joeordie/ark.txt";

    File file = new File(filepath);    
     
    public void arkWriteOut(ArrayList<Animal> ark){
        //this method writes the entire Array List out to the file. 
        //recast... because java.
        ArrayList<Animal> passedArk = ark;
        //create root JSONobject
        JSONObject JSONark = new JSONObject();
        //create the JSONarray of objects
        JSONArray list = new JSONArray();
        //load objects
        for(Animal animal: ark){
            //create sub JSONobject Animal
            JSONObject JSONanimal = new JSONObject();
            //read string of stats (native Animal Class method) into sub object
            JSONanimal.putAll(animal.stats());
            //add sub object into JSONarray
            list.add(JSONanimal);
        }
        //load JSONArray into root JSONObject
        JSONark.put("Ark", list);
        //flatten into a string
        String stringOut = JSONark.toString();
        //write to file. 
        writeFile(stringOut);
    }
     
    public ArrayList<Animal> readFile(){
        //This method creates reads a TEXT string into JSON parser 
        ArrayList<Animal> writtenArk = new ArrayList();
        
        if(checkFile()){
            try{
                //create parser object
                JSONParser parser = new JSONParser();
                //create generalized object to hold text output
                Object genObj = parser.parse(new FileReader(filepath));
                //instantiate in a weird way which i dont understand the JSONobject
                JSONObject jsonObject = (JSONObject) genObj;
                //create and instantiate JSONArray from JSONObject array is named Ark
                JSONArray jsonArk = (JSONArray) jsonObject.get("Ark");
                
                //this is the Animal constructor. 
                for(Object object: jsonArk){//for every object called object in jsonArk do: 
                    //create a new JSONobject
                    JSONObject jsonAnimal = (JSONObject) object;     
                    String bucket; //create a variable to hold text
                    //retrieve name and instantiate Animal Class with it. 
                    bucket = (String) jsonAnimal.get("name");
                    Animal building = new Animal(bucket);
                    //retrieve and convert strings to proper data types and create 
                    //Animal object per instructions
                    bucket = (String) jsonAnimal.get("legs");
                    building.setLeg(Integer.parseInt(bucket));
                    bucket = (String) jsonAnimal.get("fly");
                    building.setFly(Boolean.parseBoolean(bucket));
                    bucket = (String) jsonAnimal.get("swim");
                    building.setSwim(Boolean.parseBoolean(bucket));
                    bucket = (String) jsonAnimal.get("respiratory");
                    building.setRespiratory(bucket);
                    bucket = (String) jsonAnimal.get("diet");
                    building.setDiet(bucket);
                    //add built Animal object to a ArrayList
                    writtenArk.add(building);
                    
                }
            }
            catch (Exception Ex3){
                Ex3.printStackTrace();
            }
        }
        //send the list back to main where its set as actual manifest. 
        return writtenArk;
    }
    
    public void writeFile(String SerializedJSON){
        
        try{
            //write stuff... standard
            FileWriter fWriter = new FileWriter(filepath);
            fWriter.write(SerializedJSON);
            fWriter.flush();
        }
        catch (Exception Ex){
        
        }        
    }
    
    public boolean checkFile(){
        //verify that the file exists, if not create it and put Noah on the ark. 
        boolean response = false;
        if (file.exists()){
                response = true; 
        }
        else {
            //ut-oh there is not a file, make one. 
            try {
                file.createNewFile();
                //buld a captain creature named Noah. 
                putCaptain();
                System.out.println("\nNo file existed, Created one!\n");
                response = true;
            }
            catch(Exception Ex2){
                System.out.println("Couldnt create file, check permissions");               
            }
        }
        return response;
    }
    public void putCaptain(){
        //the venerable captain created automagically.
        Animal noah = new Animal("Noah");
        noah.setDiet("omnivore");
        noah.setFly(false);
        noah.setSwim(true);
        noah.setRespiratory("lungs");
        noah.setLeg(2);
        //send noah to the serializer.
        // **Note** this needs to be fixed to be an array with Noah in it.
        ArrayList<Animal> ark = new ArrayList<>();
        ark.add(noah);
        arkWriteOut(ark);
    }
}
