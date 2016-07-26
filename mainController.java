//import static java.nio.file.*;

public class mainController {

    public static void main(String[] args){
		mainController mc = new mainController();
		mc.controller();
    }

    public void controller(){
		System.out.println("File changed, creating trap instance");
		trap system_trap = new trap();
		system_trap.getInput();
		system_trap.logData();
    }
}
