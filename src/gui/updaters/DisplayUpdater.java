package gui.updaters;

import java.util.Map;

import gui.GUI;
import gui.executables.boxes.Palette;
import gui.movement.TurtleViewManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import model.UnmodifiableWorld;
/**
 * Class which updates all of the display components ie palette, background, and clearing
 * @author Bilva
 *
 */
public class DisplayUpdater {
	/**
	 * Takes in needed parameters to change all GUI components through backend commands
	 * @param w Current World displayed
	 * @param palette Current Palette of colors
	 * @param background World background objects
	 * @param gc
	 * @param activeTurtles
	 */
	public void updateDisplay(UnmodifiableWorld w, Palette palette, Shape background, GraphicsContext gc, Map<Integer, TurtleViewManager> activeTurtles){
		updatePalette(w,palette);
		updateBackground(w, background, palette);
		checkClear(w, gc, activeTurtles);
	}

	public void updatePalette(UnmodifiableWorld w,Palette palette){
		palette.setPalette(w.getPalleteUpdates());
	}
	public void updateBackground(UnmodifiableWorld w, Shape background, Palette palette){
		if (palette.evalPalette(w.getBackground())!=null){
			if (w.isBackgroundSet()){
				background.setFill(palette.evalPalette(w.getBackground()));
			}
		};
	}
	public void checkClear(UnmodifiableWorld w, GraphicsContext gc, Map<Integer, TurtleViewManager> activeTurtles){
		if (w.shouldClear()){
			for (TurtleViewManager t : activeTurtles.values()){
				t.setX(GUI.BACKGROUND_WIDTH/2);
				t.setY(GUI.BACKGROUND_HEIGHT/2);
			}
			gc.clearRect(0,0,GUI.BACKGROUND_WIDTH,GUI.BACKGROUND_HEIGHT);
		}
	}
}
