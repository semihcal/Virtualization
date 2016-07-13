public class DataLog {
    public static void log(String message) {
	PrintWriter out = new PrintWriter(new FileWrite("dblog.txt", true), true);
	out.write(message);
	out.close();
    }
}
