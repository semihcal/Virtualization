import java.io.*;
import java.util.*;

public class DataLog {
    public static void log(String message) {
		try{
			PrintWriter out = new PrintWriter(new FileWriter("dblog.txt", true), true);
			out.write(message);
			out.close();
		} catch (Exception e){
			System.out.println("Error: " + e);
		}
	}
}
