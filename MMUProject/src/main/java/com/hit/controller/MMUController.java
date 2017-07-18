package com.hit.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.hit.view.LoginView;
import com.hit.view.MMUView;
import com.hit.model.MMUClient;
import com.hit.model.MMUModel;
import com.hit.model.Model;
import com.hit.view.View;

public class MMUController implements Controller, Observer {
	
	private Model model;
	private View[] view;
	
	
	
	public MMUController(Model model, View[] view) {
		super();
		this.model = model;
		this.view = view;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0==view[0])
		{
			((MMUModel)model).setConfiguration((String [])arg1);
			model.start();
			System.out.println("i im in view ");
		}
		else if(arg0==model)
		{
			((MMUView)view[0]).setCommands((ArrayList<String>)arg1);
			view[0].start();
		}
		else if(arg0==view[1])
		{
			MMUClient client=new MMUClient((String [])arg1);
			Thread th =  new Thread(client);
			th.start();
			try {
				th.join();
				if(client.getError().equals("error"))
				{
					((LoginView)view[1]).clearTextfileds();
					((LoginView)view[1]).putMassege("Wrong user name or password please try again");
				}
				else{
					((LoginView)view[1]).clearFrame();
					((MMUModel)model).setConfiguration((String [])arg1);
					((MMUModel)model).setRemoteFlug(true);
					model.start();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		else
		{
			String arr[]=(String[])arg1;
			if(arr[2].equals("LOCAL")){
			((MMUModel)model).setConfiguration((String [])arg1);
			((MMUModel)model).setRemoteFlug(false);
			model.start();
			}
			else{
				((LoginView)view[1]).start();
			}
			System.out.println("i im in cli");
		}
			
		
		

	}

}
