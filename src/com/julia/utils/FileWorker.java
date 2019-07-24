package com.julia.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.julia.model.Card;

public class FileWorker {
	private static Scanner scan;
	private static FileReader fr;
	private static FileWriter fw;

	public static List<Card> readCardsForFile(String path) {
		List<Card> cardList = new ArrayList<Card>();

		try {
			fr = new FileReader(path);
			scan = new Scanner(fr);
			while (scan.hasNextLine()) {
				Card card = new Card();
				String[] res = scan.nextLine().split(Constants.SPACE);
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

	public static void writeCardsInFile(List<Card> cardList) {

		List<String> stringList = new ArrayList<String>();
		for (Card cardItem : cardList) {
			stringList.add(cardItem.getCardNumber() + Constants.SPACE + String.valueOf(cardItem.getPinCode())
					+ Constants.SPACE + String.valueOf(cardItem.getBalance()) + Constants.SPACE
					+ String.valueOf(cardItem.getCounterErrLogin()) + Constants.SPACE
					+ Constants.FORMAT.format(cardItem.getDateLastLog()) + Constants.NEWLINE);
		}

		try {
			fw = new FileWriter(Constants.PATH, false);
			for (String string : stringList) {
				fw.write(string);
			}
			fw.close();
		} catch (IOException e) {

			System.out.println("IOEXCEPTION");
		}

	}

}
