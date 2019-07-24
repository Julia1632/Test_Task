package model;
import java.util.Date;

public class Card {
	private String cardNumber;
	private int pinCode;
	private int balance;
	private int counterErrLogin;
	private Date dateLastLog;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getCounterErrLogin() {
		return counterErrLogin;
	}
	public void setCounterErrLogin(int counterErrLogin) {
		this.counterErrLogin = counterErrLogin;
	}
	public Card() {
		super();
	}
	public Date getDateLastLog() {
		return dateLastLog;
	}
	public void setDateLastLog(Date dateLastLog) {
		this.dateLastLog = dateLastLog;
	}

	
	@Override
	public String toString() {
		return "Card [cardNumber=" + cardNumber + ", pinCode=" + pinCode + ", balance=" + balance + ", counterErrLogin="
				+ counterErrLogin + ", dateLastLog=" + dateLastLog + "]";
	}
	
	

}
