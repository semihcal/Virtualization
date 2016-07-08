public class trap {

    public String virtualMachine = null;
    public String sqlQuery = null;
    public String receivedInput = null;

    //receive input
    public void getInput(String receivedInput){
	this.receivedInput = receivedInput;
    }

    /*parse the input into which virtual machine
      sent the command, and what command that vm
      sent. */
    public void parseInput(){
	String[] parsedInput = this.receivedInput.split("\\,", 2);
        this.virtualMachine = parsedInput[0];
	this.sqlQuery = parsedInput[1];
    }

    //print the values of the public variables
    public void pushOutput(){
	System.out.println("VM: " + this.virtualMachine);
	System.out.println("Query: " + this.sqlQuery);
    }

}
