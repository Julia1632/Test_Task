package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Card;

public class FileReaderPath {

	private static Scanner scan;

	public List<Card> readInto() {
		List<Card> cardList = new ArrayList<Card>();
		File file = new File(Constants.PATH);

		try (FileReader fr = new FileReader(Constants.PATH)) {
			scan = new Scanner(fr);
			while (scan.hasNextLine()) {
				Card card=new Card();
				String[] res = scan.nextLine().split(Constants.SPACE);
				//System.out.println(scan.nextLine());
				card.setCardNumber(res[0]);

				card.setPinCode(Integer.parseInt(res[1]));
				card.setBalance(Integer.parseInt(res[2]));

				card.setCounterErrLogin(Integer.parseInt(res[3]));

				Date date = Constants.FORMAT.parse(res[4]);
				card.setDateLastLog(date);				
				cardList.add(card);

			}
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("FILE_DOSNT_EXIST");
		} catch (Exception e) {
			System.out.println("SOME_OTHER_EXCEPTION");
		}
		return cardList;

	}
}
