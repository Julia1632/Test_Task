package com.julia.utils;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.julia.model.Card;

public class CheckCards {

	public static boolean checkNumber(String string) {
		Pattern pattern = Pattern.compile(Constants.PATTERN);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	public static boolean checkExistCard(List<Card> cardList, String inputCardNum) {
		boolean isExit = false;
		for (Card cardNum : cardList) {
			if (cardNum.getCardNumber().equals(inputCardNum)) {
				isExit = true;
			}
		}
		return isExit;
	}

	public static Card getCardByNum(List<Card> cardList, String inputCardNum) {
		Card cardRes = new Card();
		for (Card cardNum : cardList) {
			if (cardNum.getCardNumber().equals(inputCardNum)) {
				cardRes = cardNum;
			}
		}
		return cardRes;
	}

	public static boolean checkBlockState(Card card) {
		boolean isExit = false;
		Date datePin = new Date();
		if ((3 - card.getCounterErrLogin() > 0)
				|| (((datePin.getTime() - card.getDateLastLog().getTime()) / (24 * 60 * 60 * 1000)) >= 1)) {
			isExit = true;
		}
		return isExit;
	}

	public static boolean checkSumAdd(int sum) {
		boolean isTrue = false;
		if (sum > 0) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean checkSumDel(int sumDel, int balance) {
		boolean isTrue = false;
		if (sumDel < 1000000 || balance >= sumDel) {
			isTrue = true;
		}
		return isTrue;
	}
}
