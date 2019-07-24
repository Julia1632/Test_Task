package utils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Card;

public class FileWriterPath {

	public void writeInto(List<Card> cardList) {

		List<String> stringList = new ArrayList<String>();
		for (Card cardItem : cardList) {
			stringList.add(cardItem.getCardNumber() + Constants.SPACE+ String.valueOf(cardItem.getPinCode()) + Constants.SPACE
					+ String.valueOf(cardItem.getBalance()) + Constants.SPACE + String.valueOf(cardItem.getCounterErrLogin()) + Constants.SPACE
					+ Constants.FORMAT.format(cardItem.getDateLastLog())+Constants.NEWLINE);
		}
		
		
		try {
			FileWriter fr = new FileWriter(Constants.PATH,false);
			for (String string:stringList)
			{
				fr.write(string);
			}
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Ошибка при записи в файл");
		}
		
	}
}