package com.julia.ui;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.julia.model.Card;
import com.julia.model.IBancomat;
import com.julia.utils.CheckCards;
import com.julia.utils.FileWorker;

public class ConsoleUI {

	private static Scanner scanner;
	private static String inputCardNum;
	private static int flagNumber = 0;

	public static void runUI(IBancomat bancomat) {
		scanner = new Scanner(System.in);
		List<Card> cardList = bancomat.getCardList();

		while (flagNumber == 0) {
			System.out.println("Enter card number(ееее-ееее-ееее-ееее)");
			inputCardNum = scanner.nextLine();

			if (CheckCards.checkNumber(inputCardNum)) {
				System.out.println("The number matches the pattern");
				flagNumber = 1;

				if (CheckCards.checkExistCard(cardList, inputCardNum)) {
					System.out.println("This card exists");
					Card userCard = CheckCards.getCardByNum(cardList, inputCardNum);
					int flagPinCode = 0;
					int inputPin = 0;
					while (flagPinCode == 0) {
						Date datePin = new Date();
						if (CheckCards.checkBlockState(userCard)) {
							System.out.println("Enter Pin Code");
							inputPin = scanner.nextInt();
							scanner.nextLine();
							if (inputPin == userCard.getPinCode()) {
								System.out.println("Pin code is correct");
								userCard.setDateLastLog(datePin);
								userCard.setCounterErrLogin(0);

								flagPinCode = 1;
								int flagAction = 0;
								int action;
								while (flagAction == 0) {
									System.out.println("Select operation");
									System.out.println("1 Check Balance");
									System.out.println("2 Top up account");
									System.out.println("3 Withdraw from account");
									System.out.println("4 Shut Down");
									action = scanner.nextInt();
									switch (action) {
									case 1:
										System.out.println("Current Balance=" + String.valueOf(userCard.getBalance()));
										break;
									case 2:
										System.out.println("Top up account: Enter the amount");
										int sumAdd = 0;
										sumAdd = scanner.nextInt();
										if (CheckCards.checkSumAdd(sumAdd)) {
											userCard.setBalance(userCard.getBalance() + sumAdd);
										} else {
											System.out.println("Incorrect amount");
										}
										break;
									case 3:
										System.out.println("Withdraw from account: Enter the amount (limit 1000000)");
										int sumDel = 0;
										sumDel = scanner.nextInt();
										if (CheckCards.checkSumDel(sumDel, userCard.getBalance())) {
											userCard.setBalance(userCard.getBalance() - sumDel);
										} else {
											System.out.println("Incorrect amount");
										}
										break;
									case 4:
										System.out.println("Shut Down");
										flagAction = 1;
										break;
									default:
										System.out.println("There is no such operation");
										System.out.println("Shut Down");
										break;

									}
								}

							} else {
								System.out.println("Pin code is not correct");
								userCard.setCounterErrLogin(userCard.getCounterErrLogin() + 1);
								Date dateNow = new Date();
								userCard.setDateLastLog(dateNow);
								if (3 - userCard.getCounterErrLogin() > 0) {
									System.out.println("To try one more time? (1 = Yes 2 = No)");

									int ansRepPin;
									ansRepPin = scanner.nextInt();
									if (ansRepPin == 2) {
										flagPinCode = 1;
										System.out.println("Shut Down");
									} else if (ansRepPin != 1) {
										flagPinCode = 1;
										System.out.println("Invalid answer");
										System.out.println("Shut Down");
									}
								}

							}

						} else {
							System.out.println("The card is locked");
							flagPinCode = 1;
						}
					}
				} else {
					System.out.println("The card does not exist");
				}

			} else {
				System.out.println("The number does not match the pattern");
				System.out.println("To try one more time? (1 = Yes 2 = No)");
				int ansRepCardNum = 0;

				try {
					ansRepCardNum = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Only input 1 or 2 is available");
				}
				scanner.nextLine();
				if (ansRepCardNum == 2) {
					flagNumber = 1;
					System.out.println("Shut Down");

				}
			}
		}
		scanner.close();
		FileWorker.writeCardsInFile(cardList);
	}

}
