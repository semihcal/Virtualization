public class mainController {

    public static void main(String[] args){
		mainController mc = new mainController();
		mc.controller();
    }

    public void controller(){
		System.out.println("File changed, creating trap instance");
		TrapSwitch system_trap = new TrapSwitch();
		system_trap.getInput();
    }
}
