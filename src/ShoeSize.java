import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/* ShoeSize - Eric McCreath 2015 - GPL
 * This class stores a persons shoe size.
 */

public class ShoeSize {


	private static final String SHOESIZEENAME = "SHOESIZE";
	public static final int SHOESIZEMAX = 15;
	public static final int SHOESIZEMIN = 3;

	static final String FILENAME = "shoesize.txt";

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

	static public ShoeSize load(String filename) {
		ShoeSize res = new ShoeSize();
		try {
			BufferedReader lr = Files.newBufferedReader(
					new File(filename).toPath(), Charset.forName("US-ASCII"));

			int line = lr.read();

			res.shoesize = line;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;

	}

	void save(String filename) {

		try {
			BufferedWriter lw = Files.newBufferedWriter(
					new File(filename).toPath(), Charset.forName("US-ASCII"));

			lw.write(shoesize);

			lw.newLine();
			lw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}
}