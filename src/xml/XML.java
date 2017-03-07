//based on code written by Robert Duvall
//written by Alex Blumenstock
package xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.List;
import javax.xml.bind.annotation.XmlElement.DEFAULT;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class XML {
    // name of root attribute that notes the type of file expecting to parse
    private File dataFile;
    private Default defaults;

	//Elements expected to be in every xml file
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String IMAGE_ELEMENT = "image";
    public static final String BACKGROUNDCOLOR_ELEMENT="backgroundColor";
    public static final String PENCOLOR_ELEMENT="penColor";
    public static final String LANGUAGE_ELEMENT="language";
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
    /**
     * Get the data contained in this XML file as an object
     */
    public XML(File data){
    	dataFile=data;
    	defaults=getDefaults();
    }
    public XML(String fileName){
    	dataFile=new File(fileName);
    	defaults=getDefaults();
    }
    public Default getDefaults () {
        
    	Element root = getRootElement(dataFile);
    	Default myDefault=new Default();
        if (! isValidFile(root, myDefault.getType())) {
        	
        	
        	throw new XMLException("XML file does not represent %s", myDefault.getType());
        }
        // read data associated with the fields given by the object
        
        Map<String, String> results = new HashMap<>();
        for (String field : myDefault.getFields()) {
            
        	results.put(field, getTextValue(root, field));
        	
        }
       
        myDefault.setDataValues(results);
        //determine whether the initial grid should be read from the file or generated randomly
      
        
        return myDefault;
    }
    
  
    
    public List<String> getAttributes(){
    	return defaults.getFields();
    }
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }
    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root, String type) {
        return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
    }
    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }
    // Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }
    }
    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private int squared(int i){
    	return i*i;
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