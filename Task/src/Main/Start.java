package Main;

import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Bancomat;
import model.Card;
import model.IBancomat;
import utils.Constants;
import utils.FileReaderPath;
import utils.FileWriterPath;

public class Start {

	public static void main(String[] args) {

		IBancomat b2 = Bancomat.getCreateState();
		FileReaderPath f = new FileReaderPath();
		b2.setCardList(f.readInto());

		Scanner scanner = new Scanner(System.in);
		String inputCardNum;
		int flagNumber = 0;

		while (flagNumber == 0) {
			System.out.println("Введите номер карты");
			inputCardNum = scanner.nextLine();

			Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}");
			Matcher matcher = pattern.matcher(inputCardNum);

			if (matcher.matches()) {
				System.out.println("Номер соответствует шаблону");
				flagNumber = 1;
				boolean cardExist = false;
				Card userCard = null;
				for (Card cardNum : b2.getCardList()) {
					if (cardNum.getCardNumber().equals(inputCardNum)) {
						userCard = cardNum;
						cardExist = true;
					}
				}
				if (cardExist) {
					System.out.println("Карта существует");
					int flagPinCode = 0;
					int inputPin;
					while (flagPinCode == 0) {
						Date datePin = new Date();
						if ((3 - userCard.getCounterErrLogin() > 0)
								|| (((datePin.getTime() - userCard.getDateLastLog().getTime())
										/ (24 * 60 * 60 * 1000)) >= 1)) {
							System.out.println("Введите Pin код");
							inputPin = scanner.nextInt();
							// todo обработка строки
							if (inputPin == userCard.getPinCode()) {
								System.out.println("Pin код правильный");
								userCard.setDateLastLog(datePin);
								userCard.setCounterErrLogin(0);

								flagPinCode = 1;
								int flagAction = 0;
								int action;
								while (flagAction == 0) {
									System.out.println("Выберите операцию");
									System.out.println("1 Проверить баланс");
									System.out.println("2 Пополнить счет");
									System.out.println("3 Снять со счет");
									System.out.println("4 Завершить работу");
									action = scanner.nextInt();
									switch (action) {
									case 1:
										System.out.println("Текущий баланс=" + String.valueOf(userCard.getBalance()));
										break;
									case 2:
										System.out.println("Пополнить счет: Введите сумму");
										int sumAdd = 0;
										sumAdd = scanner.nextInt();
										userCard.setBalance(userCard.getBalance() + sumAdd);
										break;
									case 3:
										System.out.println("Снять со счета: Введите сумму (лимит 1000000)");
										int sumDel = 0;
										sumDel = scanner.nextInt();
										if (sumDel > 1000000 || userCard.getBalance() < sumDel) {
											System.out.println("Некорректная сумма");
										} else {
											userCard.setBalance(userCard.getBalance() - sumDel);
										}
										break;
									case 4:
										System.out.println("Завершение работы");
										flagAction = 1;
										break;
									default:
										System.out.println("Такой операции нет");
										break;

									}
								}

							} else {
								System.out.println("Pin код не правильный");
								userCard.setCounterErrLogin(userCard.getCounterErrLogin() + 1);
								Date dateNow = new Date();
								userCard.setDateLastLog(dateNow);
								if (3 - userCard.getCounterErrLogin() > 0) {
									System.out.println("Попробовать еще раз? (1=Да 2=Нет)");

									int ansRepPin;
									ansRepPin = scanner.nextInt();
									if (ansRepPin == 2) {
										flagPinCode = 1;
										System.out.println("Завершена работа");
									} else if (ansRepPin != 1) {
										flagPinCode = 1;
										System.out.println("Некорректный ответ");
										System.out.println("Завершена работа");
									}
								}

							}
						} else {
							System.out.println("Карта заблокирована");
							flagPinCode = 1;
						}
					}
				} else {
					System.out.println("Карты не существует");
				}

			} else {
				System.out.println("Номер не соответствует шаблону");
				System.out.println("Попробовать еще раз? (1=Да 2=Нет)");
				int ansRepCardNum = 0;

				try {
					ansRepCardNum = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Доступен только ввод 1 или 2");
				}
				scanner.nextLine();
				if (ansRepCardNum == 2) {
					flagNumber = 1;
					System.out.println("Завершена работа");

				}
			}
		}
		scanner.close();
		
		FileWriterPath fileWriter = new FileWriterPath();
		fileWriter.writeInto(b2.getCardList());

	}

}
