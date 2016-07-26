import java.util.*;
import java.sql.*;
import java.io.*;

//ResultSet toString for Log


public class TrapSwitch {
	public String virtualMachine = null;
    public String sqlQuery = null;
    public String receivedInput = null;
    public String dbLogs = null;

    //receive input
    public void getInput(){
        try (BufferedReader br = new BufferedReader(new FileReader("trap_commands/log.txt"))) {
            String last = "";
            String line;
            while ((line = br.readLine()) != null) {
                last = line;
             }
             this.receivedInput = last;
             //parse the received input
             parseInput();
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    /*parse the input into which virtual machine
      sent the command, and what command that vm
      sent. */
    private void parseInput(){
        String[] parsedInput = this.receivedInput.split("\\,", 2);
        this.virtualMachine = parsedInput[0];
        this.sqlQuery = parsedInput[1];

        //Now that input is parsed, we can check validity
        checkValidity(this.virtualMachine);
    }

    private void checkValidity(String vm){
    	try (BufferedReader br = new BufferedReader(new FileReader("vm_validity.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.substring(0, 3).equalsIgnoreCase(vm)){
                	if(Integer.parseInt(line.substring(5, 6)) == 0){
                		//real data
                		//Semih connect this
                		System.out.println("Real Data");
                	} else {
                		//fake data
                		//Jaken connect this
                		System.out.println("Fake Data");
                	}
                }
             }
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
}
