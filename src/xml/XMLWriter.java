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

import GUI.TurtleComboBox;

import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
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
	    public static final String IMAGE_ELEMENT = "image";
	    public static final String BACKGROUNDCOLOR_ELEMENT="backgroundColor";
	    public static final String PENCOLOR_ELEMENT="penColor";
	    public static final String LANGUAGE_ELEMENT="language";
	    public XMLWriter(Default d){
	    	myDefault=d;
	    }
 //Prepares the Document in a DocumentBuilder by appending elements and attributes
	public void getXML(String imageString,Paint backgroundColor,Paint penColor,String language){
	Document doc=DOCUMENT_BUILDER.newDocument();
	Element root=doc.createElement("Default"); 
	doc.appendChild(root);
	setAttributes( root, doc);
	
	
	Element element=doc.createElement(IMAGE_ELEMENT);
	element.appendChild(doc.createTextNode(imageString));
	//t.getTurtleChooser().getSelectionModel().getSelectedItem())
	root.appendChild(element);
	Element bgc=doc.createElement(BACKGROUNDCOLOR_ELEMENT);
	bgc.appendChild(doc.createTextNode(backgroundColor.toString()));
	root.appendChild(bgc);
	Element pc=doc.createElement(PENCOLOR_ELEMENT);
	pc.appendChild(doc.createTextNode(penColor.toString()));
	root.appendChild(pc);
	Element lang=doc.createElement(LANGUAGE_ELEMENT);
	lang.appendChild(doc.createTextNode(language));
	root.appendChild(lang);
	printFile(doc);
 }

 private String promptUser(String prompt){
	 String s="";
	 TextInputDialog input=new TextInputDialog(String.format("Please enter the %s for this simulation",prompt));
	 input.setWidth(100);
	 Optional<String> response = input.showAndWait();
	 if (response.isPresent()){
		 input.close();
		 s=response.get();
	 }
 return s;
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
			
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		FileChooser filechooser=new FileChooser();
		filechooser.setTitle("Select location to save XML file");
		filechooser.getExtensionFilters().addAll(new ExtensionFilter(".xml files","*.xml"));
		Stage ownerWindow=new Stage();
		Result result=null;
		 result = new StreamResult(filechooser.showSaveDialog(ownerWindow));		
		try {		
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
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