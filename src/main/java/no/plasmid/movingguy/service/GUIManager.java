package no.plasmid.movingguy.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import no.plasmid.movingguy.gui.Component;
import no.plasmid.movingguy.gui.Page;
import no.plasmid.movingguy.gui.event.KeyboardEvent;

public class GUIManager implements Service {

	/**
	 * The currently active page, which will be displayed
	 */
	private Page activePage = null;
	
	/**
	 * The component which currently has focus
	 */
	private Component focusedComponent = null;
	
	/**
	 * The stack of focused components, used for "back" like operations in focus
	 */
	private LinkedList<Component> focusStack = new LinkedList<Component>();
	
	/**
	 * Map of named components
	 */
	private Map<String, Component> namedComponents;
	
	/**
	 * Set which page shall be displayed
	 * @param activePage
	 */
	public void setActivePage(Page activePage) {
		this.activePage = activePage;
		this.focusedComponent = namedComponents.get(activePage.getDefaultFocusedComponentName());
	}
	
	/**
	 * @return the currently active page
	 */
	public Page getActivePage() {
		return activePage;
	}
	
	public Component getFocusedComponent() {
		return focusedComponent;
	}
	
	public void setFocusedComponent(Component focusedComponent) {
		this.focusedComponent = focusedComponent;
	}
	
	public void pushComponentToFocusStack(Component component) {
		focusStack.addFirst(component);
	}
	
	public void popComponentFromFocusStack() {
		focusedComponent = focusStack.pollFirst();
		if (null == focusedComponent) {
			focusedComponent = namedComponents.get(activePage.getDefaultFocusedComponentName());
		}
	}
	
	public void sendKeyboardEvent(KeyboardEvent e) {
		if (focusedComponent != null) {
			focusedComponent.handleKeyboardEvent(e);
		}
	}
	
	public void registerNamedComponent(Component component) {
		if (component.getName() == null || "".equals(component.getName())) {
			throw new IllegalArgumentException("Component name must be set if it's going to be registered.");
		}
		if (namedComponents.containsKey(component.getName())) {
			//TODO warn that duplicate names exists
		}
		namedComponents.put(component.getName(), component);
	}
	
	public void unregisterNamedComponent(Component component) {
		if (null == namedComponents.remove(component.getName())) {
			//TODO warn that the component was not in the map
		}
	}
	
	public Component getNamedComponent(String name) {
		return namedComponents.get(name);
	}

	@Override
	public void initializeService() {
		namedComponents = new HashMap<String, Component>();
	}

}
