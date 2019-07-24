package com.julia.main;

import com.julia.model.Bancomat;
import com.julia.model.IBancomat;
import com.julia.ui.ConsoleUI;

public class Start {

	public static void main(String[] args) {

		IBancomat bancomatMain = Bancomat.getCreateState();
		ConsoleUI.runUI(bancomatMain);

	}
}
