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
			System.out.println("������� ����� �����");
			inputCardNum = scanner.nextLine();

			Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}");
			Matcher matcher = pattern.matcher(inputCardNum);

			if (matcher.matches()) {
				System.out.println("����� ������������� �������");
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
					System.out.println("����� ����������");
					int flagPinCode = 0;
					int inputPin;
					while (flagPinCode == 0) {
						Date datePin = new Date();
						if ((3 - userCard.getCounterErrLogin() > 0)
								|| (((datePin.getTime() - userCard.getDateLastLog().getTime())
										/ (24 * 60 * 60 * 1000)) >= 1)) {
							System.out.println("������� Pin ���");
							inputPin = scanner.nextInt();
							// todo ��������� ������
							if (inputPin == userCard.getPinCode()) {
								System.out.println("Pin ��� ����������");
								userCard.setDateLastLog(datePin);
								userCard.setCounterErrLogin(0);

								flagPinCode = 1;
								int flagAction = 0;
								int action;
								while (flagAction == 0) {
									System.out.println("�������� ��������");
									System.out.println("1 ��������� ������");
									System.out.println("2 ��������� ����");
									System.out.println("3 ����� �� ����");
									System.out.println("4 ��������� ������");
									action = scanner.nextInt();
									switch (action) {
									case 1:
										System.out.println("������� ������=" + String.valueOf(userCard.getBalance()));
										break;
									case 2:
										System.out.println("��������� ����: ������� �����");
										int sumAdd = 0;
										sumAdd = scanner.nextInt();
										userCard.setBalance(userCard.getBalance() + sumAdd);
										break;
									case 3:
										System.out.println("����� �� �����: ������� ����� (����� 1000000)");
										int sumDel = 0;
										sumDel = scanner.nextInt();
										if (sumDel > 1000000 || userCard.getBalance() < sumDel) {
											System.out.println("������������ �����");
										} else {
											userCard.setBalance(userCard.getBalance() - sumDel);
										}
										break;
									case 4:
										System.out.println("���������� ������");
										flagAction = 1;
										break;
									default:
										System.out.println("����� �������� ���");
										break;

									}
								}

							} else {
								System.out.println("Pin ��� �� ����������");
								userCard.setCounterErrLogin(userCard.getCounterErrLogin() + 1);
								Date dateNow = new Date();
								userCard.setDateLastLog(dateNow);
								if (3 - userCard.getCounterErrLogin() > 0) {
									System.out.println("����������� ��� ���? (1=�� 2=���)");

									int ansRepPin;
									ansRepPin = scanner.nextInt();
									if (ansRepPin == 2) {
										flagPinCode = 1;
										System.out.println("��������� ������");
									} else if (ansRepPin != 1) {
										flagPinCode = 1;
										System.out.println("������������ �����");
										System.out.println("��������� ������");
									}
								}

							}
						} else {
							System.out.println("����� �������������");
							flagPinCode = 1;
						}
					}
				} else {
					System.out.println("����� �� ����������");
				}

			} else {
				System.out.println("����� �� ������������� �������");
				System.out.println("����������� ��� ���? (1=�� 2=���)");
				int ansRepCardNum = 0;

				try {
					ansRepCardNum = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("�������� ������ ���� 1 ��� 2");
				}
				scanner.nextLine();
				if (ansRepCardNum == 2) {
					flagNumber = 1;
					System.out.println("��������� ������");

				}
			}
		}
		scanner.close();
		
		FileWriterPath fileWriter = new FileWriterPath();
		fileWriter.writeInto(b2.getCardList());

	}

}
