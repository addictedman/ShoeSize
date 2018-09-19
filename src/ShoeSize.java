
/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShoeSize {
	private static final String SHOESIZEENAME = "SHOESIZE";
	public static final int SHOESIZEMAX = 15;
	public static final int SHOESIZEMIN = 3;

	final static String FILENAME = "Shoesize.txt";

	private Integer shoesize;
	public static Map<String, ShoeSize> data;
	public static ShoeSize shoeSize;

	public ShoeSize() {
		shoesize = null;
	}

	public String show() {
		return (shoesize == null ? "" : shoesize.toString());
	}

	public boolean set(Integer v) {
		if (v == null || v >= ShoeSize.SHOESIZEMIN && v <= ShoeSize.SHOESIZEMAX) {
			shoesize = v;
			save(data,FILENAME);
			return true;
		} else {
			shoesize = null;
			return false;
		}
	}

	public static ShoeSize load(String filePath) {
		// add code here that will load shoe size from a file called "FILENAME"
		try {BufferedReader b_reader = new BufferedReader(new FileReader(filePath));
			// read in line and test if null
			String line;

			// a dictionary like structure for holding our student data
			data = new HashMap<String, ShoeSize>();


			while((line = b_reader.readLine()) != null){
				System.out.println(line);
				// break string into array
				// put data into new Student object
				ShoeSize shoeSize = new ShoeSize();

				// generate unique identifier
				String uniqueID =  UUID.randomUUID().toString();

				// ensure uniqueID not in hashmap
				while(data.containsKey(uniqueID)){
					uniqueID = UUID.randomUUID().toString();
				}

				// place data into hashmap
				data.put(uniqueID, shoeSize);

			}
			// release system resources
			b_reader.close();

		}
		catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return shoeSize;
	}


	public void save(Map<String, ShoeSize> size,String filePath) {
		// add code here that will save shoe size into a file called "FILENAME"
		// try-with-resource-statement since Java 7

		try{
			BufferedWriter b_writer = new BufferedWriter(new FileWriter(filePath));

			for (Map.Entry<String, ShoeSize> entry : size.entrySet()) {
				String key = entry.getKey();
				ShoeSize shoeSize = entry.getValue();

				String line = shoeSize.shoesize.toString();

				// debug code
				System.out.println(line);

				// write single student data to file
				b_writer.write(line);
				// insert linebreak
				b_writer.newLine();


			}

			// release system resources
			b_writer.close();
		}
		catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

	}
	public Map<String, ShoeSize> getShoeSize(){
		return data;
	}

}
