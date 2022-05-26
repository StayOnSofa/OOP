package zadanie7;

//class 4 clone class 3 with implement abstract method
import java.io.Serializable;

public class SonyDrone extends VideoCameraDrone{
    public SonyDrone(String title, int fuel) {
        super(title, fuel);
    }

    @Override
    public void DoPrepareVideo() {
        System.out.println("Do SFX");
    }
}
