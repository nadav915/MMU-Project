package com.hit.driver;
import com.hit.controller.MMUController;
import com.hit.model.MMUModel;
import com.hit.view.LoginView;
import com.hit.view.MMUView;
import com.hit.view.View;

public class MMUDriver {
	
	public static void main(String[] args) throws Exception{
		CLI cli = new CLI(System.in, System.out);
		MMUModel model = new MMUModel();
		MMUView view = new MMUView();
		LoginView loginView = new LoginView();
		View[] views = { view, loginView};
		MMUController controller = new MMUController(model,views);
		model.addObserver(controller);
		view.addObserver(controller);
		cli.addObserver(controller);
		loginView.addObserver(controller);
		Thread th =  new Thread(cli);
		th.start();
		th.join();
		
		}
	
}
