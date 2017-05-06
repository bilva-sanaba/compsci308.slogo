package gui.executables.turtleimage;

import gui.GUI_Configuration;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.language.Language;
import gui.movement.TurtleViewManager;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TurtleImageToggleView implements ITurtleViewChanger{
	private MapProperty<Integer, TurtleViewManager> map;
	private TextAreaWriter t;
	private FireableButton rb;
	private Language l;
	private Stage myStage;
	private Group root;
	private Scene myScene;
	private static final Integer STAGE_HEIGHT = 400;
	private static final Integer STAGE_WIDTH = 1000;
	private static final Integer YSPACING = 100;
	private static final Integer XSPACING = 180;
	public TurtleImageToggleView(MapProperty<Integer, TurtleViewManager> map, TextAreaWriter t, FireableButton rb, Language l){
		this.t = t;
		this.map=map;
		this.rb=rb;
		this.l=l;
		myStage = new Stage();
		root = new Group();
		myScene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
		myScene.getStylesheets().add(GUI_Configuration.DEFAULT_RESOURCE_PACKAGE+GUI_Configuration.STYLESHEET);
		myStage.setScene(myScene);
		bindMap();
	}
	/* (non-Javadoc)
	 * @see gui.executables.turtleimage.ITurtleViewChanger#run()
	 */
	@Override
	public void run(){
		populateScreen();
		myStage.show();
	}
	private void bindMap(){
		map.addListener(new MapChangeListener<Integer, TurtleViewManager>(){
			@Override
			public void onChanged(
					MapChangeListener.Change<? extends Integer, ? extends TurtleViewManager> arg0) {
				populateScreen();
			}
		});
	}
	private void populateScreen(){
		root.getChildren().clear();
		addViewChangeButton();
		addIDs();
		createTurtleImages();
	}

	private void addViewChangeButton(){
		for (Integer i : map.keySet()){
			Node viewChanger = new ViewChangeButton(t,rb,l,i, map.get(i).getPossibleShapes()).getNode();
			viewChanger.setLayoutX(i*XSPACING);
			viewChanger.setLayoutY(YSPACING*3);
			root.getChildren().add(viewChanger);
		}
	}
	private void addIDs(){
		for (Integer i : map.keySet()){
			Text id = new Text(i.toString());
			id.setLayoutX(i*XSPACING);
			id.setLayoutY(YSPACING*2);
			root.getChildren().add(id);
		}
	}
	private void createTurtleImages(){
		for (Integer i : map.keySet()){
			ReadOnlyStringProperty imageString = map.get(i).getTurtleView().getTurtleString();
			ImageView turtle = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageString.get())));
			turtle.setLayoutY(YSPACING*0);
			turtle.setLayoutX(i*XSPACING);
			bindStringToImage(imageString,turtle);
			root.getChildren().add(turtle);
		}
	}
	private void bindStringToImage(ReadOnlyStringProperty s, ImageView i){
		s.addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> o, String oldVal, 
					String newVal){
				i.setImage(new Image(getClass().getClassLoader().getResourceAsStream(newVal)));
			}
		});

	}

}
