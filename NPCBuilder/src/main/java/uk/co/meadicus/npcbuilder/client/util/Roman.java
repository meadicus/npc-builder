package uk.co.meadicus.npcbuilder.client.util;

/**
 * From:
 * http://www.hawaga.org.uk/java/benno/applets/RomanNumeralConverter.html
 * 
 * <p>
 * This NumberFormat converts long integers to and from Roman Numeral notation.
 * Once an instance has been created, the format and parse methods may be used
 * as defined in java.text.NumberFormat.
 * </p>
 * 
 * <p>
 * The <a
 * href="../benno/applets/RomanNumeralConverter.html">RomanNumeralConverter
 * applet</a> demonstrates the use of this class.
 * </p>
 * 
 * @see benno.applets.RomanNumeralConverter
 * @author Ben Clifford
 * @version $Revision: 1.11 $
 */
public abstract class Roman {

	/**
	 * This nested class is used to map Roman symbols onto their numerical
	 * values. Used in the Roman class.
	 * 
	 */
	private static class SymTab {
		/** Roman symbol */
		char symbol;

		/** Numerical value */
		long value;

		/**
		 * Constructor to build a SymTab from supplied symbol and value
		 * 
		 * @param s Roman symbol
		 * @param v Numerical value
		 */
		public SymTab(char s, long v) {
			this.symbol = s;
			this.value = v;
		}
	};

	/**
	 * This table maps individual Roman symbols onto their numerical values.<br>
	 * Unfortunately, JavaDoc JDK 1.1 does not create documentation for the
	 * inner class Roman.SymTab, so the reader cannot see the definition.
	 */
	private static Roman.SymTab syms[] = {
		new Roman.SymTab('M', 1000),
		new Roman.SymTab('D', 500),
		new Roman.SymTab('C', 100),
		new Roman.SymTab('L', 50),
		new Roman.SymTab('X', 10),
		new Roman.SymTab('V', 5),
		new Roman.SymTab('I', 1)
	};

	/**
	 * This method converts a Roman Numeral string to a long integer. It does
	 * not check that the string is in the correct format - for some incorrectly
	 * formatted numbers, i.e. iix, it will produce a number. For others, it
	 * will throw an exception.
	 * 
	 * @param s string of Roman Numerals
	 * @return The integer representation of the Numerals
	 */

	public static long toLong(String s) {
		long tot = 0, max = 0;
		char ch[] = s.toUpperCase().toCharArray();
		int i, p;
		for (p = ch.length - 1; p >= 0; p--) {
			for (i = 0; i < syms.length; i++) {
				if (syms[i].symbol == ch[p]) {
					if (syms[i].value >= max)
						tot += (max = syms[i].value);
					else
						tot -= syms[i].value;
				}
			}
		}
		return tot;
	};

	public static int toInt(String s) {
		return (int) toLong(s);
	}
	
	/**
	 * This method converts a long integer to capitalised Roman notation.
	 * 
	 * @param n The integer to convert to Roman Numerals.
	 * @return A String object containing the Roman Numerals.
	 */
	public static String toRoman(long n) {
		int i;
		String s;
		if (n == 0) {
			s = "None";
		} else {
			s = "";
			while (n > 0) {
				for (i = 0; i < syms.length; i++) {
					if (syms[i].value <= n) {
						int shift = i + (i % 2);
						if (i > 0 && shift < syms.length
								&& (syms[i - 1].value - syms[shift].value) <= n) {
							s = s + syms[shift].symbol + syms[i - 1].symbol;
							n = n - syms[i - 1].value + syms[shift].value;
	
							i = -1;
	
						} else {
							s += syms[i].symbol;
							n -= syms[i].value;
							i = -1;
						}
					}
				}
			}
		}
		return s;
	}

}
