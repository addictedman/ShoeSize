import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

public class ShoeSize {
	private static final String SHOESIZEENAME = "SHOESIZE";
	public static final int SHOESIZEMAX = 15;
	public static final int SHOESIZEMIN = 3;
	private static String SHOESIZE = "sss";
	static final String FILENAME = "shoesize.json";


	private static Long shoesize;

	public ShoeSize() {
		shoesize = null;

	}

	public String show() {
		return (shoesize == null ? "" : shoesize.toString());
	}

	public boolean set(Long v) {
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
		File f = new File(filename);
		ShoeSize res = new ShoeSize();
		try {
			JSONObject obj = (JSONObject) JSONValue.parse(new FileReader(f));
			res.shoesize = (Long) obj.get(SHOESIZE);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;

	}

	void save(String filename) {
		File f = new File(filename);
		JSONObject obj = new JSONObject();
		obj.put(SHOESIZE, shoesize);
		System.out.println(shoesize);
		FileWriter out;
		try {
			out = new FileWriter(f);
			obj.writeJSONString(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}