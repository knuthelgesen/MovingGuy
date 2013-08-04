package no.plasmid.movingguy.gui.template;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import no.plasmid.movingguy.gui.Page;
import no.plasmid.movingguy.gui.dataobject.Color;
import no.plasmid.movingguy.gui.dataobject.Rectangle;
import no.plasmid.movingguy.gui.dataobject.Texture;

public class PageTemplate extends Page implements Template<Page> {

	@Override
	public Class<Page> getComponentClass() {
		return Page.class;
	}

	@Override
	public Color getBackgroundColor() {
		throw new NotImplementedException();
	}

	@Override
	public void setBackgroundColor(Color backgroundColor) {
		throw new NotImplementedException();
	}

	@Override
	public Texture getTexture() {
		throw new NotImplementedException();
	}

	@Override
	public void setTexture(Texture texture) {
		throw new NotImplementedException();
	}

	@Override
	public Rectangle<Double> getTextureCoordinates() {
		throw new NotImplementedException();
	}

	@Override
	public void setTextureCoordinates(Rectangle<Double> textureCoordinates) {
		throw new NotImplementedException();
	}
	
}
