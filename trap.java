//are we logging which vms queried what on the database or are we logging what the db returns?
//

import java.util.*;
import java.sql.*;
import java.io.*;


public class trap {

    public String virtualMachine = null;
    public String sqlQuery = null;
    public String receivedInput = null;
    public String dbLogs = null;

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

    //Check validity of requesting virtual machine
    public boolean checkValidity(String virtualMachine){
	if(virtualMachine.equals("valid")){
	    return true;
	}
	return false;
    }

    //Query the database for valid Virtual Machines only
    public void queryDatabase(){
        Connection conn = new Connection();
        String SQLQuery = this.sqlQuery;
    }

    //Format Data for GUI
    public void formatData(){

    }

    //Query honeypot in cases of invalid Virtual Machines
    public void queryHoneypot(){
        String hpQuery = this.sqlQuery;
    }

    //Log the output in a text file so honeypot has basic data
    //to be modeled after.
    public void logData(){
        DataLog.log("VM: " + this.virtualMachine + ", ");
        DataLog.log("Query: " + this.sqlQuery + "\n");
    }

    public void checkLogs(){
	try(BufferedReader br = new BufferedReader(new FileReader("dblog.txt")))
	{
	    StringBuilder buildString = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
		buildString.append(line);
		buildString.append(System.lineSeparator());
		line = br.readLine();
	    }
	    
	    this.dbLogs = buildString.toString();
	} catch(Exception e){
	    System.out.println("Error: " + e);
	}
    }

    public void populateHoneypot(){
	//connect to honeypot and populate based on log
        System.out.println(this.dbLogs);
    }

    //print the values of the public variables
    public void pushOutput(){
        System.out.println("VM: " + this.virtualMachine);
        System.out.println("Query: " + this.sqlQuery);
    }
    
    //Spool honeypot when triggered
    public void spoolHoneypot(){
        System.setProperty("user.dir", "C:\Program Files (x86)\VMware\VMware VIX");
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("vmrun start \"C:\Users\Jaken\Documents\Virtual Machines\OpenSUSE.vmx\"");
    }

}
