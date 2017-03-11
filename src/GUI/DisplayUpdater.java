package GUI;

import GUI_Objects.Palette;
import javafx.scene.shape.Shape;
import model.UnmodifiableWorld;

public class DisplayUpdater {
	public void updatePalette(UnmodifiableWorld w,Palette palette){
		palette.setPalette(w.getPalleteUpdates());
	}
	public void updateBackground(UnmodifiableWorld w, Shape background, Palette palette){
		 background.setFill(palette.evalPalette(w.getBackground()));
	}
}
