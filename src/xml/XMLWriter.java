// This entire file is part of my masterpiece.
// Alexander Blumenstock
//This code allows a user to take any moment of a simulation and save all of the parameters and cell states into an XML file that can 
//be loaded back into the interface to effectively "unpause" the simulation at some later time.

//This code is well designed for many reasons.  Firstly, it has very clear dependencies by taking in every piece of data
//it plans to use in the constructor.   Secondly,  it has relatively short methods, with all of them being around 20 lines or fewer.
//It uses a lot of cool Javafx things, some of which we learned in class, like the TextInputBox and the FileChooser.
//The parts of the code that clearly belong together, like the author and title elements, have been factored out into a seperate method
//that deals with them because they are handled so similarly.  While the code does look a little repetitive in how it handles the other
//three elements and the two attributes, considering the different data types being dealt with (ints, Strings, and booleans) as well as the
//different getter methods that need to be called, it seems to me that any attempts to futher factor this code would actually make it longer
//and more complicated.
package xml;
import java.io.File;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import error.SlogoAlert;
import gui.executables.boxes.TurtleComboBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class XMLWriter {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Default myDefault;
	   public static final String TYPE_ATTRIBUTE = "type"; 
	    public XMLWriter(Default d){
	    	myDefault=d;
	    }
 //Prepares the Document in a DocumentBuilder by appending elements and attributes
	public void getXML(List<String> imageString,Paint backgroundColor,String language){
		Document doc=DOCUMENT_BUILDER.newDocument();
	Element root=doc.createElement(TYPE_ATTRIBUTE); 
	doc.appendChild(root);
	setAttributes( root, doc);
	XMLData xmlData=new XMLData(imageString,backgroundColor,language);
	Map<String,String>xmlMap=xmlData.getParameters();
	for(String s:xmlMap.keySet()){
		Element element=doc.createElement(s);
		element.appendChild(doc.createTextNode(xmlMap.get(s)));
		root.appendChild(element);
	}
	printFile(doc);
 }

 
 private void setAttributes(Element root, Document doc){
	 
		Attr type=doc.createAttribute(TYPE_ATTRIBUTE);
		type.setValue(myDefault.getType());
		root.setAttributeNode(type);
 }
 private void printFile(Document doc){
	 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			
			SlogoAlert alert=new SlogoAlert("Could not generate XML",e.getMessage());
			alert.showAlert();
		}
		DOMSource source = new DOMSource(doc);
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("Select location to save XML file");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".xml files","*.xml"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		Stage ownerWindow=new Stage();
		Result result=null;
		 result = new StreamResult(fileChooser.showSaveDialog(ownerWindow));		
		try {		
			transformer.transform(source, result);
		} catch (TransformerException e) {
			SlogoAlert alert=new SlogoAlert("Could not generate XML",e.getMessage());
			alert.showAlert();
		}
 }
 private static DocumentBuilder getDocumentBuilder () {
	        try {
	            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        }
	        catch (ParserConfigurationException e) {
	            throw new XMLException(e);
	        }
	    }
}