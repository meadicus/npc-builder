package uk.co.meadicus.npcbuilder.client.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptGroupElement;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Various useful bits of code.
 * 
 * @author mead
 */
public abstract class NPCUtils {

	/**
	 * Adds the give options to the list box.
	 * 
	 * @param listBox
	 * @param clear remove all current options first
	 * @param options
	 * @param blank include a blank on the list
	 */
	static public void addListOptions(ListBox listBox, boolean clear, Collection<?> options, boolean blank) {
		if (clear) {
			listBox.clear();
		}
		if (blank) {
			listBox.addItem("");
		}
		for (Object o : options) {
			listBox.addItem(o.toString());
		}
	}
	/**
	 * Adds the give options to the list box.
	 * 
	 * @param listBox
	 * @param clear remove all current options first
	 * @param options
	 * @param blank include a blank on the list
	 */
	static public void addListOptions(ListBox listBox, boolean clear, Object[] options, boolean blank) {
		if (clear) {
			listBox.clear();
		}
		if (blank) {
			listBox.addItem("");
		}
		for (Object o : options) {
			listBox.addItem(o.toString());
		}
	}
	
	/**
	 * Adds the given items where the map is from string to value.
	 * 
	 * @param listBox
	 * @param clear
	 * @param options
	 * @param blank
	 */
	static public void addListOptions(ListBox listBox, boolean clear, Map<?, ?> options, boolean blank) {
		if (clear) {
			listBox.clear();
		}
		if (blank) {
			listBox.addItem("");
		}
		for (Object k : options.keySet()) {
			Object v = options.get(k);
			listBox.addItem(k.toString(), v.toString());
		}			
	}
	
	/**
	 * Adds the given items where the map is from string to value.
	 * 
	 * @param listBox
	 * @param clear
	 * @param options
	 * @param blank
	 */
	static public void addListOptionGroups(ListBox listBox, boolean clear, Map<String, List<String>> groups) {
		// Cast box to dom element
		SelectElement select = listBox.getElement().cast();	
		if (clear) {
			while(select.hasChildNodes()) {
				select.removeChild(select.getFirstChild());
			}
		}	
		for (String k : groups.keySet()) {
			// Add the opt group element
			OptGroupElement groupElement = Document.get().createOptGroupElement();
			groupElement.setLabel(k);			
			List<String> options = groups.get(k);
			for (String option : options) {
				OptionElement optionElement = Document.get().createOptionElement();
				optionElement.setInnerText(option);
				optionElement.setValue(option);
				groupElement.appendChild(optionElement);
			}
			select.appendChild(groupElement);
		}			
	}
	
	/**
	 * Adds the given items where the map is from string to value.
	 * 
	 * @param listBox
	 * @param clear
	 * @param options
	 * @param blank
	 */
	static public void addListOptionValuedGroups(ListBox listBox, boolean clear, Map<String, Map<String, String>> groups) {
		// Cast box to dom element
		SelectElement select = listBox.getElement().cast();	
		if (clear) {
			while(select.hasChildNodes()) {
				select.removeChild(select.getFirstChild());
			}
		}	
		for (String k : groups.keySet()) {
			// Add the opt group element
			OptGroupElement groupElement = Document.get().createOptGroupElement();
			groupElement.setLabel(k);			
			Map<String, String> options = groups.get(k);
			for (Map.Entry<String, String> option : options.entrySet()) {
				OptionElement optionElement = Document.get().createOptionElement();
				optionElement.setInnerText(option.getKey());
				optionElement.setValue(option.getValue());
				groupElement.appendChild(optionElement);
			}
			select.appendChild(groupElement);
		}			
	}
	/**
	 * Adds the given items where the map is from string to value.
	 * 
	 * @param listBox
	 * @param clear
	 * @param options
	 * @param blank
	 */
	static public void addListOptionGroup(ListBox listBox, boolean clear, String groupName, Map<String, String> options) {
		// Cast box to dom element
		SelectElement select = listBox.getElement().cast();	
		if (clear) {
			while(select.hasChildNodes()) {
				select.removeChild(select.getFirstChild());
			}
		}
		// Add the opt group element
		OptGroupElement groupElement = Document.get().createOptGroupElement();
		groupElement.setLabel(groupName);
		for (Map.Entry<String, String> option : options.entrySet()) {
			OptionElement optionElement = Document.get().createOptionElement();
			optionElement.setInnerText(option.getKey());
			optionElement.setValue(option.getValue());
			groupElement.appendChild(optionElement);
		}
		select.appendChild(groupElement);
	}
	
	/**
	 * 
	 * @param listBox
	 * @param clear
	 * @param start
	 * @param limit
	 * @param roman
	 * @param blank
	 */
	static public void addListOptions(ListBox listBox, boolean clear, int start, int limit, boolean roman, boolean blank) {
		addListOptions(listBox, clear, start, limit, 1, roman, blank);
	}
	/**
	 * 
	 * @param listBox
	 * @param clear
	 * @param start
	 * @param limit
	 * @param step 
	 * @param roman
	 * @param blank
	 */
	static public void addListOptions(ListBox listBox, boolean clear, int start, int limit, int step, boolean roman, boolean blank) {
		if (clear) {
			listBox.clear();
		}
		if (blank) {
			listBox.addItem("");
		}
		if (start < limit) {
			for (int i = start; i < limit; i += step) {
				String val = Integer.toString(i);
				String item = val;
				if (roman) {
					item = Roman.toRoman(i);
				} else if (start < 0 && i >= 0) {
					item = "+" + item;
				}
				listBox.addItem(item, val);
			}
		} else {
			for (int i = start; i > limit; i -= step) {
				String val = Integer.toString(i);
				String item = val;
				if (roman) {
					item = Roman.toRoman(i);
				} else if (start < 0 && i >= 0) {
					item = "+" + item;
				}
				listBox.addItem(item, val);
			}
			
		}
	}
	
	static public void selectByValue(ListBox listBox, Object value) {
		SelectElement select = listBox.getElement().cast();
		NodeList<Element> options = select.getElementsByTagName("option");		
		for (int i = 0; i < options.getLength(); ++i) {
			OptionElement option = (OptionElement) options.getItem(i);
			if (option.getValue().equals(value.toString())) {
				listBox.setSelectedIndex(i);
				break;
			}
		}			
	}
	
	static public void selectByValueIgnoreCase(ListBox listBox, Object value) {
		SelectElement select = listBox.getElement().cast();
		NodeList<Element> options = select.getElementsByTagName("option");		
		for (int i = 0; i < options.getLength(); ++i) {
			OptionElement option = (OptionElement) options.getItem(i);
			if (option.getValue().equalsIgnoreCase(value.toString())) {
				listBox.setSelectedIndex(i);
				break;
			}
		}			
	}
	
	static public String getSelectedItemValue(final ListBox listBox) {
		if (listBox.getSelectedIndex() < 0) {
			return null;
		} else {
			return getValue(listBox, listBox.getSelectedIndex());
		}
	}
	
	static public String getValue(final ListBox listBox, final int index) {
		String val = null;
		try {
			val = listBox.getValue(index);
		} catch (IndexOutOfBoundsException ex) {
			// nothing to do
		}
		if (val == null || val.equals("undefined")) {
			SelectElement select = listBox.getElement().cast();
			NodeList<Element> options = select.getElementsByTagName("option");
			OptionElement option = (OptionElement) options.getItem(index);
			val = option.getValue();
		}
		return val;
	}
	
	static public String renderModifier(int mod) {
		if (mod < 0) {
			return Integer.toString(mod);
		} else {
			return "+" + Integer.toString(mod);
		}
	}
	
	static public int lookupStat(int[][] statTable, int rating, int threatLevel) {
		int theRating = Math.max(1, Math.min(10, rating));
		int theLevel = Math.max(1, Math.min(20, threatLevel));
		return statTable[theRating-1][theLevel-1];
	}

	public static int attributeToBonus(int score) {
		return attributeToBonus((double)score);
	}
	public static int attributeToBonus(double score) {
		return (int)Math.floor((score - 10.0) / 2.0);
	}
	public static String attributeToModifier(int score) {
		return renderModifier(attributeToBonus(score));
	}
	
	public static String toCapCase(String text) {
		return text.toUpperCase().charAt(0) + text.substring(1).toLowerCase();
	}
	
	/**
	 * Splits the given text by the delimeter, while being aware of brackets,
	 * therefore if there is a non-closed bracket, it is likely to go completely
	 * wrong. It respects (, [, and {
	 * 
	 * @param text
	 * @param delimiter
	 * @return
	 */
	public static List<String> bracketAwareSplit(String text, char delimiter) {
		List<String> items = new ArrayList<String>();
				
		while (!text.isEmpty()) {
			String item = getFirstBracketedItem(text, delimiter);
			items.add(item.trim());
			if (item.length() < text.length()) {
				text = text.substring(item.length()+1);
			} else {
				text = "";
			}
		}
		
		return items;
	}
	
	private static String getFirstBracketedItem(String text, char delimiter) {
		String item = null;
		
		int firstDelimiter = text.indexOf(delimiter);
		if (firstDelimiter < 0) {
			item = text;
		} else {
			// check for open brackets
			int firstBracket = findFirstBracket(text);
			while (firstBracket > 0 && firstBracket < firstDelimiter) {
				int closeBracket = findCloseBracket(text, firstBracket);
				firstBracket = findFirstBracket(text, closeBracket+1);
				firstDelimiter = text.indexOf(delimiter, closeBracket+1);
			}
			
			if (firstDelimiter > 0) {			
				item = text.substring(0, firstDelimiter);
			} else {
				item = text;
			}
		}
		return item;
	}

	private static int findFirstBracket(String text) {
		return findFirstBracket(text, 0);
	}
	
	private static int findFirstBracket(String text, int after) {
		
		int firstBracket = text.indexOf('(', after);
		int firstSBracket = text.indexOf('[', after);
		int firstBrace = text.indexOf('{', after);
				
		if (firstBracket < 0 || (firstSBracket > 0 && firstSBracket < firstBracket)) {
			firstBracket = firstSBracket;
		}
		if (firstBracket < 0 || (firstBrace > 0 && firstBrace < firstBracket)) {
			firstBracket = firstBrace;
		}
		
		return firstBracket;
	}
	
	private static int findCloseBracket(String text, int openBracket) {
		
		char openChar = text.charAt(openBracket);
		char closeChar = ')';
		if (openChar == '[') {
			closeChar = ']';
		} else if (openChar == '{') {
			closeChar = '}';
		}
		
		int firstClose = text.indexOf(closeChar, openBracket+1);
		int nextOpen = text.indexOf(openChar, openBracket+1);
		
		while (nextOpen > 0 && nextOpen < firstClose) {
			int childClose = findCloseBracket(text, nextOpen);
			firstClose = text.indexOf(closeChar, childClose+1);
			nextOpen = text.indexOf(openChar, childClose+1);
		}
		
		int closeBracket = firstClose;
		
		if (closeBracket < 0) {
			closeBracket = text.length()-1;
		}
		
		return closeBracket;
	}
	
	public static final Random random = new Random();
	
	public static int rollDice(int sides) {
		return random.nextInt(sides)+1;
	}
	
	public static int rollDice(int sides, int mod) {
		return rollDice(sides) + mod;
	}
	
	public static int rollNDice(int n, int sides) {
		int total = 0;
		for (int i = 0; i < n; ++i) {
			total += rollDice(sides);
		}
		return total;
	}
	
	public static int rollNDice(int n, int sides, int mod) {
		return rollNDice(n, sides) + mod;
	}
	
	public static int rollD20() {
		return rollDice(20);
	}
	
	public static int rollD20(int mod) {
		return rollD20() + mod; 
	}
	
	/**
	 * Returns a random number between the lower and upper limits inclusive.
	 * @param lower
	 * @param upper
	 * @return
	 */
	public static int randomInRange(int lower, int upper) {
		int floor = lower-1;
		return rollDice(upper-floor,floor);
	}

	/**
	 * Parses text for any instance of a dice roll \dd\d and replaces this with the result
	 * of rolling such dice.
	 */
	public static String evalDiceText(String text) {
		String output = text;
		RegExp regexp = RegExp.compile("(\\d+)[dD](\\d+)([-+]\\d+)?");
		MatchResult result;
		while (null != (result = regexp.exec(output))) {

			String strNumDice = result.getGroup(1);
			String strSides = result.getGroup(2);
			String strMod = result.getGroup(3);
			
			int ndice = Integer.parseInt(strNumDice);
			int nsides = Integer.parseInt(strSides);
			int mod = 0;
			if (strMod != null && !strMod.isEmpty()) {
				mod = Integer.parseInt(strMod);
			}
			int roll = NPCUtils.rollNDice(ndice, nsides, mod);
			
			output = regexp.replace(output, Integer.toString(roll));
		}
		
		return output;
	}

} 
