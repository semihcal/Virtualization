public class mainController {

    public static void main(String[] args){
	mainController mc = new mainController();
	mc.controller();
	mc.controller_test();
    }

    public void controller(){
	trap system_trap = new trap();

	system_trap.getInput("V1", "Select * from *");
	system_trap.pushOutput();
    }
}