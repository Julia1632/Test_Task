package com.julia.model;

import java.util.ArrayList;
import java.util.List;

import com.julia.utils.Constants;
import com.julia.utils.FileWorker;

public class Bancomat implements IBancomat {

	private List<Card> cardList = new ArrayList<Card>();
	private static IBancomat state;

	private Bancomat() {
		cardList = new ArrayList<Card>();
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void addCard(Card card) {
		cardList.add(card);
	}

	public static IBancomat getCreateState() {
		if (state == null) {
			state = new Bancomat();
			state.setCardList(FileWorker.readCardsForFile(Constants.PATH));
		}
		return state;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

}
