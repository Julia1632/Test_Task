package model;
import java.util.ArrayList;
import java.util.List;

public class Bancomat implements IBancomat {

	private List<Card> cardList = new ArrayList<Card>();
	private static IBancomat state;

	private Bancomat() {
		cardList=new ArrayList<Card>();
	}

	public List<Card> getCardList() {
		/*for (Card card : cardList){
			System.out.println(card.toString());
		}
		*/
		return cardList;
	}

	public void addCard(Card card) {
		cardList.add(card);
	}
	
	public static IBancomat getCreateState(){
		if (state==null){
			state=new Bancomat();
		}
		return state;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}



}
