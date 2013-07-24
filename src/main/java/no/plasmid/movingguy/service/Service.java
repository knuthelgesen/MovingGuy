package no.plasmid.movingguy.service;

/**
 * Base interface for all services.
 * 
 * Defines methods that all services must implement.
 * 
 * @author helgesk
 *
 */
public interface Service {
	
	/**
	 * Will initialize a service. This method is meant to be called from the {@link ServiceManager}
	 */
	public void initializeService();

}
