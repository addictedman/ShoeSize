
/* ShoeSize - Eric McCreath 2015 - GPL 
 * This class stores a persons shoe size.
 */

import java.io.*;

public class ShoeSize implements Serializable{
	private static final String SHOESIZEENAME = "SHOESIZE";
	public static final int SHOESIZEMAX = 15;
	public static final int SHOESIZEMIN = 3;

	static final String FILENAME = "shoesize.ser";

	private Integer shoesize;

	public ShoeSize() {
		shoesize = null;
	}

	public String show() {
		return (shoesize == null ? "" : shoesize.toString());
	}

	public boolean set(Integer v) {
		if (v == null || v >= ShoeSize.SHOESIZEMIN && v <= ShoeSize.SHOESIZEMAX) {
			shoesize = v;
			save(FILENAME);
			return true;
		} else {
			shoesize = null;
			return false;
		}
	}




	static ShoeSize load(String filename) {
                // add code here that will load shoe size from a file called "FILENAME"
		ShoeSize res = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					filename));
			res = (ShoeSize) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return res;
	}

	void save(String filename) {
		// add code here that will save shoe size into a file called "FILENAME
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(filename));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
