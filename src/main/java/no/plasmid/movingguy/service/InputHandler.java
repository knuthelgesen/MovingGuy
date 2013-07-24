package no.plasmid.movingguy.service;

import no.plasmid.movingguy.gui.event.KeyboardEvent;
import no.plasmid.movingguy.service.ProgramStateManager.ProgramState;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class InputHandler implements Service {

	private GUIManager guiManager;
	private ProgramStateManager programStateManager;
	
	private boolean[] keyStatus;

	public InputHandler() {
		keyStatus = new boolean[256];
	}
	
	public void handleInput() {
    	//Check if the user wants to close the window
    	if (Display.isCloseRequested()) {
    		programStateManager.setCurrentProgramState(ProgramState.EXITING);
    	}
    	
		//Handle keyboard input
    	handleKeyboardInput();
    	
    	//Handle mouse output
    	handleMouseInput();
	}
	
	public boolean[] getKeyStatus() {
		return keyStatus;
	}
	
	@Override
	public void initializeService() {
		//Get GUI manager
		guiManager = ServiceManager.getInstance().getGUIManager();
		//Get program state manager
		programStateManager = ServiceManager.getInstance().getProgramStateManager();
	}
	
	private void handleKeyboardInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				//Key down
				int key = Keyboard.getEventKey();
				switch (key) {
					default: {
						getKeyStatus()[key] = true;
						guiManager.sendKeyboardEvent(generateKeyboardEvent());
					}
				}
			} else {
				//Key up
				int key = Keyboard.getEventKey();
				switch (key) {
					default: {
						getKeyStatus()[key] = false;
						guiManager.sendKeyboardEvent(generateKeyboardEvent());
					}
				}
			}
		}
	}
	
	private void handleMouseInput() {
		//TODO
	}
	
	private KeyboardEvent generateKeyboardEvent() {
		KeyboardEvent.Type type;
		if (Keyboard.getEventKeyState()) {
			//Key down
			type = KeyboardEvent.Type.DOWN;
		} else {
			//Key up
			type = KeyboardEvent.Type.UP;
		}
		
		return new KeyboardEvent(type, Keyboard.getEventKey());
	}
}
