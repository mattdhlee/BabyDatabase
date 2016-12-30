
 
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DataSet
{	
	
	DataStore data; 
	ArrayList<Integer> indexList = new ArrayList<Integer>();
	HashSet<Integer> indexSet = new HashSet<Integer>();
	HashSet<String> fields;
	//ArrayList<String> n = new HashSet<String>();
    /* initializes the DataSet to the given DataStore contents */
    public DataSet(DataStore dstore)
    {
    	data = dstore;
    	//ArrayList<Integer> indexList = new ArrayList<Integer>();
    	//HashSet<Integer> indexSet = new HashSet<Integer>();
    	fields = new HashSet<String>(data.columnToIndex.keySet());
    	for (int i=0;i<data.allDataLines.size();i++) {//contains all indexes initially
    		System.out.println("added!");
    		indexList.add((Integer) i);
    		//indexSet.add((Integer) i);
    	}
    	
    }
    

    /* returns a new object, containing a copy of this data set */
    public DataSet copy()
    {
    	DataSet copy = new DataSet(data);
    	return copy;
    }

    /* returns the names of all included fields */
    public String[] getIncludedFields()
    {	
    	String[] nameList = new String[fields.size()];
    	//int columnIndex;
    	int i = 0;
    	for(String name: fields) {
    		nameList[i] = name;
    		i++;
    	}
    	return nameList;
    }
    
    /* returns the max number of characters used for field f */
    public int getFieldLength(String f) throws DataSetException
    {	
    	int columnIndex = data.columnToIndex.get(f);
    	int maxNum = 0;
    	for (Integer i: indexList) {
    		int count = data.allDataLines.get(i)[columnIndex].length();
    		if (count>maxNum) {
    			maxNum = count;
    		}
    	}
    	return maxNum;
        // to be implemented; remove the line below
        //throw new UnsupportedOperationException();
    }
    
    /* returns the number of records this data set has */
    public int size()
    {
    	return indexList.size();
    }
    
    /* returns the data contained in field f in record r,
       where 0 <= r < getNumRecords() */
    public String getRecordField(int r, String f) throws DataSetException
    {	if (!data.columnToIndex.containsKey(f.toLowerCase())) {
    		throw new DataSetException("Field does not exist.");
    	}
    
		if (!(r<indexList.size() && r>=0)) {
			throw new DataSetException("Index not valid.");
		}
		
		return data.allDataLines.get(indexList.get(r))[data.columnToIndex.get(f.toLowerCase())];
    }
    	
//    	if(indexSet.get(r)) {
//    		String[] currentLine = data.allDataLines.get(r);
//    		int wantedColumn = data.columnToIndex.get(f.toLowerCase());
//    		return currentLine[wantedColumn];
//    	}
//    	else{  //IS THIS OKAY? 
//    		throw new DataSetException("Record does not exist");
//    	}
    
    
    /* removes any records that do not also appear in other */
    public void intersect(DataSet other)
    {	
    	Iterator<Integer> iter = indexSet.iterator();
    	Integer index;
    	while(iter.hasNext()) { //for every index in indexList.
    		index = iter.next();
    		if (!other.indexSet.contains(index)) {
    			iter.remove();
    			indexSet.remove(index);
    			indexList.remove(index);
    		}
    	}
    }	
    	
    
    /* adds any records that appear in other */
    public void union(DataSet other)
    {	
    	for (int i=0; i<other.indexList.size(); i++) {
    		Integer otherI = other.indexList.get(i);
    		if (!indexList.contains(otherI)) {
    			indexList.add(otherI);
    			indexSet.add(otherI);    		
    		}	
    	}
    }
    
    /* removes any records where the given field does not contain the search string */
    public void contains(String field, String search) throws DataSetException
    {
    	field = field.toLowerCase();
    	if (!data.columnToIndex.containsKey(field)) {
    		throw new DataSetException("The field does not exist.");
    	}
    	int columnIndex = data.columnToIndex.get(field);
    	
    	Iterator<Integer> iter = indexSet.iterator();
    	Integer index;
    	while (iter.hasNext()) {
    		index = iter.next();
    		if (!(data.allDataLines.get(index)[columnIndex].toLowerCase().contains(search.toLowerCase()))) {//compare the string in field.
    			iter.remove();
    			indexList.remove(index);
    			indexSet.remove(index);
    		}
    	}
    	
    }
    
    
    /* removes any records where the given field does not start with the search string */
    public void starts(String field, String search) throws DataSetException
    {
    	field = field.toLowerCase();
    	search = search.toLowerCase();
    	if (!data.columnToIndex.containsKey(field)) {
    		throw new DataSetException("The field does not exist.");
    	}
    	int columnIndex = data.columnToIndex.get(field);
    	//Iterator<Integer> iter = indexList.iterator();
    	//Integer index = indexList.get(0);
    	for (int i=0; i<indexList.size();i++) {
    		//index = indexList.get(i);
    		//System.out.println(index);
    		if (!data.allDataLines.get(indexList.get(i))[columnIndex].toLowerCase().startsWith(search)) {
    			//iter.remove();
    			//System.out.println(data.allDataLines.get(index)[columnIndex]);
    			indexList.remove(i);
    			i--;
    			
    			//indexSet.remove(index);
    		}
    	}
    }
//    		
    	
//    	while (iter.hasNext()) {
//    	
//    	
//    		if (!data.allDataLines.get(index)[columnIndex].toLowerCase().startsWith(search)) {
//    			iter.remove();
//    			indexList.remove(index);
//    			//indexSet.remove(index);
//    		}
//    		index = iter.next();
//    		
//    	}
    	//System.out.println(indexList.size());
    	//System.out.println("its good");
    	
    	
    	
    
    /* sets the number of active fields to be as many there are in the original data set */
    public void includeAll()
    {	
    	fields = new HashSet<String>(data.columnToIndex.keySet());
    	
        // to be implemented; remove the line below
        //throw new UnsupportedOperationException();
    }
    
    /* sets the number of active fields to be zero */
    public void includeNone()
    {	
    	fields.removeAll(fields);
        // to be implemented; remove the line below
       // throw new UnsupportedOperationException();
    }
    
    /* sets the given field to be active */
    public void include(String field) throws DataSetException
    {
    	fields.add(field);
        // to be implemented; remove the line below
        //throw new UnsupportedOperationException();
    }
    static class AscendingComp implements Comparator<Integer>
    {
      private int columnIndex;
      private DataStore d;
      //private boolean ascend;
      public AscendingComp(String field,DataStore d){
        this.columnIndex = d.columnToIndex.get(field.toLowerCase());
        //System.out.println(columnIndex + "what is hop");
        this.d = d;
        //this.ascend = ascend;
      }
      //@Override
      public int compare(Integer a, Integer b)
      {		//System.out.println("compare enter");
    		return d.allDataLines.get(a)[columnIndex].compareTo(d.allDataLines.get(b)[columnIndex]);
    }
      
      
    }
    /* does an ascending sort on the data, relative to the given field */
    public void asort(String field) throws DataSetException
    {	
    	
    	//int columnIndex = data.columnToIndex.get(field);
    	Collections.sort(indexList, new AscendingComp(field,data));
    	System.out.println(data.allDataLines.get(indexList.get(0))[0]); //should return name of the first line in order
        // to be implemented; remove the line below
       // throw new UnsupportedOperationException();
    }
    
    /* does a descending sort on the data, relative to the given field */
    public void dsort(String field) throws DataSetException
    {
    	Collections.sort(indexList, new AscendingComp(field,data));
    	Collections.sort(indexList, Collections.reverseOrder());
        // to be implemented; remove the line below
        //throw new UnsupportedOperationException();
    }
}