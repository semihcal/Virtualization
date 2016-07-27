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
                	Class.forName("org.sqlite.JDBC");
							Connection connection = null;
							try {
							 connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
							  Statement statement = connection.createStatement();
							  statement.setQueryTimeout(30);
							  statement.executeUpdate("drop table if exists person");
							  statement.executeUpdate("create table person (id integer, name string)");
							  statement.executeUpdate("insert into person values(1, 'leo')");
							  statement.executeUpdate("insert into person values(2, 'yui')");
							  ResultSet rs = statement.executeQuery("select * from person");
							  while(rs.next())
							  {
								// read the result set
								System.out.println("name = " + rs.getString("name"));
								System.out.println("id = " + rs.getInt("id"));
							  }
							} catch ( Exception e ) {
							  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
							  System.exit(0);
							}
							System.out.println("Opened database successfully");
							System.out.println("Real Data");
                		
                	} else {
                		//fake data
                		//Jaken connect this
                		System.out.println("Fake Data");
                        shuffleData();
                	}
                }
             }
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    private void shuffleData(){
        try (BufferedReader br = new BufferedReader(new FileReader("dblog.txt"))){
            String line;
            int linecount = 0;
            
            while((line = br.readLine()) != null){
                linecount++;
            }

            String[] bPolicyNumbers = new String[linecount];
            String[] businessNames = new String[linecount];
            String[] taxIDNumbers = new String[linecount];
            String[] bAccountNumbers = new String[linecount];
            String[] pPolicyNumbers = new String[linecount];
            String[] personalNames = new String[linecount];
            String[] socialSecurities = new String[linecount];
            String[] pAccountNumbers = new String[linecount];

            int i = 0;
            while((line = br.readLine()) != null){
                String[] parsedLog = line.split("\\,", 5);

                if(parsedLog[0].equalsIgnoreCase("B")){
                    //handle business
                    bPolicyNumbers[i] = parsedLog[1];
                    businessNames[i] = parsedLog[2];
                    taxIDNumbers[i] = parsedLog[3];
                    bAccountNumbers[i] = parsedLog[4];
                    i++;
                }
                else{
                    //handle personal
                    pPolicyNumbers[i] = parsedLog[1];
                    personalNames[i] = parsedLog[2];
                    socialSecurities[i] = parsedLog[3];
                    pAccountNumbers[i] = parsedLog[4];
                    i++;
                }
            }
            populateHoneypot(bPolicyNumbers, businessNames, taxIDNumbers, bAccountNumbers, pPolicyNumbers,
                            personalNames, socialSecurities, pAccountNumbers, linecount);
        } catch(Exception e){
            System.out.println("Error " + e);
        }
    }

    private void populateHoneypot(String[] bPolicyNumbers, String[] businessNames, String[] taxIDNumbers, String[] bAccountNumbers,
                                    String[] pPolicyNumbers, String[] personalNames, String[] socialSecurities, String[] pAccountNumbers,
                                    int linecount){
        //connect to honey
        //insert into these statements
        Random rand = new Random();
        int b = rand.nextInt(linecount) + 0;
        int c = rand.nextInt(linecount) + 0;
        int d = rand.nextInt(linecount) + 0;
        int e = rand.nextInt(linecount) + 0;


        //linecount is the maximum and the 0 is our minimum 
        int j = rand.nextInt(1) + 0;
        if(j == 0){
            System.out.println("B"+","+bPolicyNumbers[b]+","+businessNames[c]+","+taxIDNumbers[d]+","+bAccountNumbers[e]);
        } 
        else{ 
            System.out.println("P"+","+pPolicyNumbers[b]+","+personalNames[c]+","+socialSecurities[d]+","+pAccountNumbers[e]);
        }

    }
}
