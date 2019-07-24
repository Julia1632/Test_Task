package com.julia.model;

import java.util.List;

public interface IBancomat {

	void addCard(Card card);

	List<Card> getCardList();

	void setCardList(List<Card> cardList);

}
