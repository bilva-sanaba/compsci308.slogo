package xml;
	import java.util.Arrays;
	import java.util.List;
	import java.util.Map;
import java.util.HashMap;
	/**
	 * Simple immutable value object representing music data.
	 *
	 * @author Robert C. Duvall
	 */
	public class Default {
		

		
		
	    // name in data file that will indicate it represents data for this type of object
	    public static final String DATA_TYPE = "Default";
	    // field names expected to appear in data file holding values for this object
	    // simple way to create an immutable list
	    
	    
	    public static final List<String> DATA_FIELDS =  Arrays.asList(new String[] {
	        "imageList",
	        "backgroundColor",
	        
	        "language"
	       
	    });
	   

	

	    // specific data values for this instance
	    private Map<String, String> myDataValues;
	    public Default (Map<String, String> dataValues) {
	        myDataValues = dataValues;
	    }
	    
	    public Default(){
	    	myDataValues=new HashMap<String,String>();
	    }
	    public void setDataValues(Map<String, String> dataValues){
	    	myDataValues=dataValues;
	    }
	   public Map<String,String> getAttributes(){
		   return myDataValues;
	   }
	  
	    public String getType(){
	    	return DATA_TYPE;
	    }
	    public List<String>getFields(){
	    	return DATA_FIELDS;
	    }
	    public String getLanguage(){
	    	return myDataValues.get("language");
	    }
	    public String getBackgroundColor(){
	    	return myDataValues.get("backgroundColor");
	    }
	    
	    public List<String> getImageString(){
	    	return Arrays.asList(myDataValues.get("imageList").split(" "));
	    }
	    @Override
	    public String toString () {
	        StringBuilder result = new StringBuilder();
	        result.append("Default {\n");
	        for (Map.Entry<String, String> e : myDataValues.entrySet()) {
	            result.append("  "+e.getKey()+"='"+e.getValue()+"',\n");
	        }
	        result.append("}\n");
	        return result.toString();
	    }
	    
	}