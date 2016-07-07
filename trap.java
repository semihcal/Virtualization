public class trap {

    public String virtualMachine = null;
    public String sqlQuery = null;

    public void getInput(String virtualMachine, String sqlQuery){
	this.virtualMachine = virtualMachine;
	this.sqlQuery = sqlQuery;
    }

    public void pushOutput(){
	System.out.println("VM: " + this.virtualMachine);
	System.out.println("Query: " + this.sqlQuery);
    }

}
