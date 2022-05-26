package zadanie7;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {

    private void WriteDronesToXML(ArrayList<SonyDrone> drones) throws FileNotFoundException, IOException
    {
         FileOutputStream fileOutputStream
          = new FileOutputStream("drones.txt");
        ObjectOutputStream objectOutputStream 
          = new ObjectOutputStream(fileOutputStream);
        
        objectOutputStream.writeObject(drones.size());
    
        for (int i = 0; i < drones.size(); i++) {
            objectOutputStream.writeObject(drones.get(i));
        }
        
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private ArrayList<SonyDrone> RestoreDronesFormXML() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<SonyDrone> list = new ArrayList<SonyDrone>();
        
            
          FileInputStream fileInputStream
      = new FileInputStream("drones.txt");
    ObjectInputStream objectInputStream
      = new ObjectInputStream(fileInputStream);
    int count = (int)objectInputStream.readObject();

          for (int i = 0; i < count; i++) {
           SonyDrone drone =  (SonyDrone)objectInputStream.readObject();
           list.add(drone);
        }
    
    objectInputStream.close(); 
        
        return list;
    }
    
    
    public void Run(int N)
    {
        ArrayList<SonyDrone> dronesList = new ArrayList<>();
        HashSet<SonyDrone> dronesHashSet = new HashSet<>();
        HashMap<SonyDrone, String> dronesHash = new HashMap<>();

        for (int i = 0; i < N; i++)
        {
            String title = "SonyDrone: " + i;
            SonyDrone drone = new SonyDrone(title, 10);

            dronesList.add(drone);
            dronesHash.put(drone, title);
            dronesHashSet.add(drone);

            System.out.println(title + " created");
        }

        SonyDrone droneToDelete = dronesList.get(0);
        dronesHashSet.remove(droneToDelete);
        dronesHash.remove(droneToDelete);

        dronesList.remove(droneToDelete);

        int index = 0;
        SonyDrone lastDrone = null;

        for(Map.Entry<SonyDrone, String> entry : dronesHash.entrySet()) {

            SonyDrone key = entry.getKey();

            if (index == dronesHash.size() - 1)
            {
                lastDrone = key;
            }

            index +=1;

        }

        lastDrone.SetTitle("Changed");
        dronesList.get(dronesList.size()-1).SetTitle("Changed");

        //iterate map
        for(Map.Entry<SonyDrone, String> entry : dronesHash.entrySet()) {

            SonyDrone key = entry.getKey();
            System.out.println("DroneKey in HashMap: " + key.GetTitle());
        }

        //iterate list
        for (int i = 0; i < dronesList.size(); i++)
        {
            SonyDrone drone = dronesList.get(i);
            System.out.println("DroneKey in List: " + drone.GetTitle());
        }

        //iterate set
        for (SonyDrone drone : dronesHashSet) {
            System.out.println("DroneKey in Set: " + drone.GetTitle());
        }
        
        try {
            //Save Drones List to XML
            WriteDronesToXML(dronesList);
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Restore Drones From XML
            dronesList = RestoreDronesFormXML();
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
