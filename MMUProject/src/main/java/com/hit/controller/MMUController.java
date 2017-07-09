package com.hit.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import com.hit.view.MMUView;
import com.hit.model.MMUModel;
import com.hit.model.Model;
import com.hit.view.View;

public class MMUController implements Controller, Observer {
	
	private Model model;
	private View view;
	
	
	
	public MMUController(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0==view)
		{
			((MMUModel)model).setConfiguration((String [])arg1);
			model.start();
			System.out.println("i im in view ");
		}
		else if(arg0==model)
		{
			((MMUView)view).setCommands((ArrayList<String>)arg1);
			view.start();
		}
		else
		{
			((MMUModel)model).setConfiguration((String [])arg1);
			model.start();
			System.out.println("i im in cli");
		}
			
		
		

	}

}
