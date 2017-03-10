package GUI;

import GUI_Objects.Palette;
import model.UnmodifiableWorld;

public class DisplayUpdater {
	public void updatePalette(UnmodifiableWorld w,Palette palette){
		palette.setPalette(w.getPalleteUpdates());
	}
}
