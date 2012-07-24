package com.fdm.platform.actions;

import java.io.IOException;

import com.fdm.platform.displays.ReaderUtility;
import com.fdm.platform.displays._DisplayManager;
import com.fdm.platform.giveToTrainees.ActionManagerException;
import com.fdm.platform.giveToTrainees._ActionManager;

public class UnbanUserAction implements _Action {

	@Override
	public void execute(_DisplayManager displayManager,
			_ActionManager actionManager, ReaderUtility reader)
			throws ActionExecutionException 
	{
		try {
			displayManager.declareUnbanUser();
			displayManager.promptForUsername();
			String username;
			username = reader.getInputAsString();
			actionManager.unbanUser(username);
			displayManager.declareSuccess();
		} catch (IOException ioe) {
			throw new ActionExecutionException(ioe);
		} catch (ActionManagerException ame) {
			throw new ActionExecutionException(ame);
		}
	}

}
