public class mainController {

    public static void main(String[] args){
	mainController mc = new mainController();
	mc.controller();
    }

    public void controller(){
	trap system_trap = new trap();

	system_trap.getInput("V1, Select * from *");
	system_trap.parseInput();
	system_trap.pushOutput();
	system_trap.logData();
	system_trap.checkLogs();
	system_trap.populateHoneypot();
    }
}
