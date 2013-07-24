package no.plasmid.movingguy.service;

public class ProgramStateManager implements Service {

	private ProgramState currentProgramState;	//Initialize to starting in the beginning
	
	public ProgramState getCurrentProgramState() {
		return currentProgramState;
	}
	
	public void setCurrentProgramState(ProgramState currentProgramState) {
		this.currentProgramState = currentProgramState;
	}
	
	@Override
	public void initializeService() {
		//Nothing to do here
	}
	
	public enum ProgramState {
		STARTING, RUNNING, EXITING;
	}

}
