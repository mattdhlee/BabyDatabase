import java.io.FileNotFoundException;

public class test {

	
	public static void main(String[] args) throws FileNotFoundException, DataSetException {
		DataStore mainD = new DataStore("comets.ssd");
		DataStore otherD = new DataStore("cometsCopy.ssd");
		//System.out.println(mainD.columnToIndex.size());
		DataSet d = new DataSet(mainD);
		DataSet other = new DataSet(otherD);
		System.out.println(other.size());
		System.out.println(d.data.allDataLines.get(0)[0] + "fsda");
		//d.asort("period");
		//System.out.println(d.indexList.get(1)+"ok");
		//d.contains("Name", "P/Brooks 2"); // only brooks in d 
		//other.starts("Name", "p/w"); //other should now lines that start with 'B'
		//System.out.println(other.indexSet.size()+"damn");
		//d.union(other);
		
		
		//other.intersect(d);
		//System.out.println(d.indexSet.size()+"duck");
		//d.starts("name", "P/W");
		//String p = d.getRecordField(37,"Name");
		//System.out.println(p + "fsd");
		//d.starts("name", "P/W");
		//System.out.println(d.data.allDataLines.size() + "sf"); //does not modify the allDataLines size 
		
		System.out.println("////");
		//d.intersect(other);
		//String[] ex = {"ad","df"};
		//String[] ex2 = {"ad","df"};
		//System.out.println(ex.equals(ex2));
		//d.union(other);
		//System.out.println(d.size());
		System.out.println("ay");

		//DataSet d = new DataSet(mainD);
		//System.out.println(d.data.countryMap.get(0)[0]);
	}
}
