
 

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.LinkedHashMap;
import java.util.List;

//TODO: make everything case insensitive..

public class DataStore
{
	BufferedReader data;
	ArrayList<String[]> allDataLines = new ArrayList<String[]>();
	//ArrayList<String[]> rowList = new ArrayList<String[]>();
	//ArrayList<HashMap<String,String>> columnMap = new ArrayList<HashMap<String,String>>(); // each element is one country. hashmap<column category, value in that category> 
    String[] firstLine;
    HashMap<String,Integer> columnToIndex = new HashMap<String,Integer>();//Integer generalization...
	
	/* loads the data from the specified filename */
    
	public DataStore(String filename) throws FileNotFoundException, DataSetException
    {	
    	String[] oneLineInfo;
    	try{
    	data = new BufferedReader(new FileReader(new File(filename)));
    	String line;
    	String fLine = data.readLine();//read the first Line with the column names
    	firstLine = fLine.split(";");// creates array of the column names, will be used as keys for column map
    	for(int i=0; i<firstLine.length;i++) {
    		//System.out.println(firstLine[i]);
    		columnToIndex.put(firstLine[i].toLowerCase(),i);
    	}

    	
    	while((line=data.readLine()) != null) {
    		oneLineInfo = line.split(";"); //a string array of one line
    		//System.out.println(oneLineInfo[0] +"ok");
    		allDataLines.add(oneLineInfo);
    	}
    	
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
}