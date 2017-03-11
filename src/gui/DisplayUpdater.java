package gui;

import java.util.Map;

import gui.executables.boxes.Palette;
import gui.movement.TurtleViewManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import model.UnmodifiableWorld;

public class DisplayUpdater {
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
			gc.clearRect(0,0,GUI.BACKGROUND_WIDTH,GUI.BACKGROUND_HEIGHT);
			for (TurtleViewManager t : activeTurtles.values()){
				t.getImage().setX(GUI.BACKGROUND_WIDTH/2);
				t.getImage().setY(GUI.BACKGROUND_HEIGHT/2);
			}
		}
		
		
	}
}
