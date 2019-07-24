package utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
	
	public static final String PATH = System.getProperty("user.dir")+"\\src\\resource\\File.txt";
	public static DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss", Locale.ENGLISH);
	public static String SPACE = " ";
	public static String NEWLINE="\n";


}
