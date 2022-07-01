package com.ezen.product.controller;

import com.ezen.product.controller.action.Action;
import com.ezen.product.controller.action.DeleteAction;
import com.ezen.product.controller.action.IndexAction;
import com.ezen.product.controller.action.ProductViewAction;
import com.ezen.product.controller.action.ProductWriteAction;
import com.ezen.product.controller.action.ProductWriteFormAction;
import com.ezen.product.controller.action.UpdateAction;
import com.ezen.product.controller.action.UpdateFormAction;

public class ActionFactory {
	
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if(command.equals("index")) ac = new IndexAction();
		else if(command.equals("productView")) ac = new ProductViewAction();
		else if(command.equals("productWriteForm")) ac = new ProductWriteFormAction();
		else if(command.equals("productWrite")) ac = new ProductWriteAction();
		else if(command.equals("updateForm")) ac = new UpdateFormAction();
		else if(command.equals("update")) ac = new UpdateAction();
		else if(command.equals("delete")) ac = new DeleteAction();
		
		return ac;
	}
		
}
