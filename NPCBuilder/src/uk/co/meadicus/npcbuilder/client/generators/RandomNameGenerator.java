package uk.co.meadicus.npcbuilder.client.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class RandomNameGenerator {

	private static class GeneratorFormat extends LinkedHashMap<Integer, String> {

		private static final long serialVersionUID = 1L;

		@Override
		public String get(Object arg0) {
			if (containsKey(arg0)) {
				return super.get(arg0);
			} else {
				String result = null;
				for (Map.Entry<Integer, String> entry : entrySet()) {
					if (entry.getKey() <= (Integer) arg0) {
						result = entry.getValue();
					} else {
						break;
					}
				}
				return result;
			}
		}
		
	}
	
	private static final Map<String, GeneratorFormat> patternTable;
	static {
		patternTable = new LinkedHashMap<String, GeneratorFormat>();
		
		GeneratorFormat drakeFormat = new GeneratorFormat();
		drakeFormat.put(1, "D[-]B");
		drakeFormat.put(7, "GB");
		drakeFormat.put(13, "H B");
		drakeFormat.put(19, "OO [the] A");
		patternTable.put("Drake", drakeFormat);
		
		GeneratorFormat dwarfFormat = new GeneratorFormat();
		dwarfFormat.put(1, "L EA");
		dwarfFormat.put(9, "L EB");
		dwarfFormat.put(17, "L DJ");
		dwarfFormat.put(19, "M G[-]A");
		patternTable.put("Dwarf", dwarfFormat);
		
		GeneratorFormat elfFormat = new GeneratorFormat();
		elfFormat.put(1, "KK E[-]C");
		elfFormat.put(7, "KK DB");
		elfFormat.put(13, "KK F A");
		elfFormat.put(19, "KK [the] HJ");
		patternTable.put("Elf", elfFormat);
		
		GeneratorFormat giantFormat = new GeneratorFormat();
		giantFormat.put(1, "M DA");
		giantFormat.put(9, "L DB");
		giantFormat.put(13, "L ED");
		giantFormat.put(17, "NO [the] I");
		patternTable.put("Giant", giantFormat);
		
		GeneratorFormat goblinFormat = new GeneratorFormat();
		goblinFormat.put(1, "NN");
		goblinFormat.put(9, "N O");
		goblinFormat.put(13, "NN [the] C");
		goblinFormat.put(17, "NN [the] G");
		patternTable.put("Goblin", goblinFormat);
		
		GeneratorFormat humanFormat = new GeneratorFormat();
		humanFormat.put(1, "|LM H C");
		humanFormat.put(9, "|LM CB");
		humanFormat.put(13, "|LM KK");
		humanFormat.put(17, "IM [the] J");
		patternTable.put("Human", humanFormat);
		
		GeneratorFormat ogreFormat = new GeneratorFormat();
		ogreFormat.put(1, "BA");
		ogreFormat.put(8, "GA");
		ogreFormat.put(15, "IJ");
		ogreFormat.put(18, "KN IB");
		patternTable.put("Ogre", ogreFormat);
		
		GeneratorFormat orcFormat = new GeneratorFormat();
		orcFormat.put(1, "NN G[-]J");
		orcFormat.put(9, "NN CA");
		orcFormat.put(13, "NN HA");
		orcFormat.put(17, "NN [the] I J");
		patternTable.put("Orc", orcFormat);
		
		GeneratorFormat pechFormat = new GeneratorFormat();
		pechFormat.put(1, "M H F");
		pechFormat.put(9, "L E[-]C");
		pechFormat.put(13, "L DF");
		pechFormat.put(17, "M EF");
		patternTable.put("Pech", pechFormat);
		
		GeneratorFormat rootwalkerFormat = new GeneratorFormat();
		rootwalkerFormat.put(1, "FB");
		rootwalkerFormat.put(6, "G F");
		rootwalkerFormat.put(11, "H [of the] J");
		rootwalkerFormat.put(16, "DF");
		patternTable.put("Rootwalker", rootwalkerFormat);
		
		GeneratorFormat saurianFormat = new GeneratorFormat();
		saurianFormat.put(1, "OO");
		saurianFormat.put(9, "OO EB");
		saurianFormat.put(13, "OO FA");
		saurianFormat.put(17, "OO D[-]C");
		patternTable.put("Saurian", saurianFormat);
		
		GeneratorFormat unbornFormat = new GeneratorFormat();
		unbornFormat.put(1, "KO");
		unbornFormat.put(5, "HJ");
		unbornFormat.put(11, "M['s] G C");
		unbornFormat.put(16, "E C [Mk.] Z");
		patternTable.put("Unborn", unbornFormat);
		
	}
	
	private static final Map<Character, Map<String, String[]>> nameTable;
	static {
		nameTable = new HashMap<Character, Map<String, String[]>>();

		Map<String, String[]> mapA = new HashMap<String, String[]>();
		nameTable.put('A', mapA);
		String[] arrayA = {"Breaker", "Builder", "Caller", "Chaser", "Cleaver", "Crusher", "Eater", "Holder", "Ruler", "Scraper", "Seeker", "Seer", "Singer", "Slayer", "Speaker", "Strider", "Sworn", "Taker", "Walker", "Watcher"};
		mapA.put("*", arrayA);

		Map<String, String[]> mapB = new HashMap<String, String[]>();
		nameTable.put('B', mapB);
		String[] arrayB = {"Arm", "Back", "Beard", "Breath", "Claw", "Crest", "Eye", "Fang", "Fist", "Flesh", "Foot", "Hand", "Head", "Heart", "Hide", "Maw", "Skull", "Spine", "Tooth", "Wing"};
		mapB.put("*", arrayB);

		Map<String, String[]> mapC = new HashMap<String, String[]>();
		nameTable.put('C', mapC);
		String[] arrayC = {"Angel", "Boar", "Crow", "Devil", "Dragon", "Eagle", "Hawk", "Horse", "Hound", "Lion", "Man", "Monster", "Rat", "Raven", "Serpent", "Shark", "Stag", "Tiger", "Weasel", "Wolf"};
		mapC.put("*", arrayC);

		Map<String, String[]> mapD = new HashMap<String, String[]>();
		nameTable.put('D', mapD);
		String[] arrayD = {"Cloud", "Dawn", "Dusk", "Earth", "Fire", "Flame", "Frost", "Hill", "Lightning", "Moon", "Mountain", "Rain", "Sea", "Sky", "Star", "Stone", "Storm", "Sun", "Thunder", "Wind"};
		mapD.put("*", arrayD);

		Map<String, String[]> mapE = new HashMap<String, String[]>();
		nameTable.put('E', mapE);
		String[] arrayE = {"Adamant", "Bronze", "Copper", "Diamond", "Emerald", "Gem", "Gold", "Granite", "Hearth", "Iron", "Jade", "Mithril", "Onyx", "Opal", "Ore", "Ruby", "Sapphire", "Silver", "Steel", "Tin"};
		mapE.put("*", arrayE);

		Map<String, String[]> mapF = new HashMap<String, String[]>();
		nameTable.put('F', mapF);
		String[] arrayF = {"Branch", "Elm", "Flower", "Forest", "Grass", "Grove", "Hollow", "Leaf", "Moss", "Needle", "Oak", "Pine", "River", "Root", "Spring", "Tree", "Twig", "Valley", "Vine", "Willow"};
		mapF.put("*", arrayF);

		Map<String, String[]> mapG = new HashMap<String, String[]>();
		nameTable.put('G', mapG);
		{ String[] arrayG = {"Ash", "Bane", "Black", "Blood", "Bone", "Broken", "Dark", "Death", "Doom", "Foul", "Fume", "Grey", "Jagged", "Night", "Red", "Shade", "Shadow", "Venom", "Vile", "Wither"};
		mapG.put("*", arrayG); }

		Map<String, String[]> mapH = new HashMap<String, String[]>();
		nameTable.put('H', mapH);
		{ String[] arrayH = {"Blue", "Boon", "Brave", "Dawn", "Day", "Glory", "Green", "Hero", "Just", "King", "Law", "Light", "Oath", "Pure", "Soul", "Spring", "Sun", "Summer", "Truth", "White"};
		mapH.put("*", arrayH); }

		Map<String, String[]> mapI = new HashMap<String, String[]>();
		nameTable.put('I', mapI);
		String[] arrayI = {"Bastard", "Bonny", "Bloody", "Conquerer", "Clever", "Cruel", "Fat", "Flatulent", "Handsome", "Jolly", "Killer", "Lame", "Mad", "Magnificent", "Mighty", "One-Eyed", "Randy", "Swift", "Terrible", "Ugly"};
		mapI.put("*", arrayI);

		Map<String, String[]> mapJ = new HashMap<String, String[]>();
		nameTable.put('J', mapJ);
		String[] arrayJ = {"Anvil", "Arrow", "Axe", "Bow", "Blade", "Dagger", "Edge", "Forge", "Guard", "Hammer", "Knife", "Lance", "Maul", "Saw", "Shield", "Spear", "Spike", "Staff", "Sword", "Wheel"};
		mapJ.put("*", arrayJ);

		Map<String, String[]> mapK = new HashMap<String, String[]>();
		nameTable.put('K', mapK);
		String[] arrayKpre = {"Aen", "Ala", "And", "Ar", "Cas", "Cyl", "El", "Eln", "Fir", "Gael", "Hu", "Koeh", "Laer", "Lue", "Nai", "Rhy", "Sere", "Tia", "Tele", "Zau"};
		mapK.put("pre", arrayKpre);

		String[] arrayPsuff = {"ael", "ari", "eth", "dil", "eil", "evar", "ir", "mus", "oth", "rad", "re", "riel", "rond", "sar", "sil", "tahl", "thus", "uil", "vain", "wyn"};
		mapK.put("suff", arrayPsuff);


		Map<String, String[]> mapL = new HashMap<String, String[]>();
		nameTable.put('L', mapL);
		String[] arrayLmale = {"Bjorn", "Dalgar", "Einar", "Fulnir", "Garth", "Galinn", "Geir", "Hakon", "Hamar", "Hrothgar", "Ivar", "Kell", "Magne", "Oddvar", "Ranulf", "Sigurd", "Snorri", "Tarn", "Tor", "Wulf"};
		mapL.put("male", arrayLmale);

		String[] arrayLfemale = {"Asta", "Bodil", "Dagmar", "Emla", "Flos", "Freya", "Ginna", "Gunnhild", "Haldis", "Helga", "Idona", "Inge", "Liva", "Norna", "Ragnhild", "Sigrun", "Solveig", "Thoris", "Valda", "Unni"};
		mapL.put("female", arrayLfemale);


		Map<String, String[]> mapM = new HashMap<String, String[]>();
		nameTable.put('M', mapM);
		String[] arrayMmale = {"Aiden", "Bruce", "Dirk", "Gareth", "Gregor", "Gustav", "Halsten", "Harold", "Jack", "James", "Kirk", "Lief", "Liam", "Patrick", "Robert", "Ronan", "Seth", "Steven", "Tom", "William"};
		mapM.put("male", arrayMmale);

		String[] arrayMfemale = {"Abby", "Bridget", "Cate", "Daisy", "Helen", "Hilda", "Ingrid", "Jessica", "Linnea", "Maggie", "Natalia", "Olga", "Rebecca", "Raelia", "Rose", "Sarah", "Scarlett", "Sophia", "Tamara", "Violet"};
		mapM.put("female", arrayMfemale);


		Map<String, String[]> mapN = new HashMap<String, String[]>();
		nameTable.put('N', mapN);
		String[] arrayNpre = {"Blud", "Bruh", "Dirg", "Dur", "Gaz", "Gor", "Goth", "Gut", "Lor", "Luth", "Mag", "Nar", "Nug", "Od", "Og", "Skum", "Teg", "Was", "Wort", "Yag"};
		mapN.put("pre", arrayNpre);

		String[] arrayNsuff = {"bag", "brak", "dar", "dreg", "gar", "gog", "ghul", "git", "grub", "ok", "rak", "rot", "ruk", "sarg", "shak", "sot", "tek", "thag", "tor", "zod"};
		mapN.put("suff", arrayNsuff);


		Map<String, String[]> mapO = new HashMap<String, String[]>();
		nameTable.put('O', mapO);
		String[] arrayOpre = {"Geth", "Grath", "Gyss", "Hyss", "Kla", "Lath", "Lex", "Lyth", "Mor", "Nar", "Nyl", "Pesh", "Ssath", "Sser", "Ssla", "Ssyss", "Tla", "Xer", "Xyl", "Xyss"};
		mapO.put("pre", arrayOpre);

		String[] arrayOsuff = {"chal", "chyss", "geth", "hesh", "hyll", "kesh", "klatch", "lyss", "mash", "moth", "myss", "resh", "ron", "ryn", "tetch", "tek", "thyss", "toss", "xec", "yss"};
		mapO.put("suff", arrayOsuff);


		/* sits in for the d20 entry in the unborn */
		Map<String, String[]> mapZ = new HashMap<String, String[]>();
		nameTable.put('Z', mapZ);
		String[] arrayZ = {"I","II","III","IV","V","VI","VII","VIII","IX","X", "XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX"};
		mapZ.put("*", arrayZ);

	}
	
	public static Collection<String> getSpeciesList() {
		return patternTable.keySet();
	}
	
	public static List<String> getGenderList() {
		List<String> genders = new ArrayList<String>();
		genders.add("male");
		genders.add("female");
		return genders;
	}
	
	public static String getName(String type, String gender) {
		
		GeneratorFormat format = patternTable.get(type);
		
		int patternNum = NPCUtils.rollD20();
		String pattern = format.get(patternNum);
		
		String name = makeName(pattern, gender);
		
		return name;
	}
	
	private static String makeName(String pattern, String gender) {
		String name = "";
		
		String patternPart = "";
		for (int c = 0; c < pattern.length(); ++c) {
			char ch = pattern.charAt(c);
			
			if (ch == '|') {
				// get the two next characters
				char c1 = pattern.charAt(c+1);
				char c2 = pattern.charAt(c+2);
				
				ch = (NPCUtils.rollD20() < 11) ? c1 : c2;
				
				c += 2;
				patternPart += ch;
			} else if (ch == '[') {
				name += makeNamePart(patternPart, gender);
				patternPart = "";
				++c;
				while (pattern.charAt(c) != ']') {
					name += pattern.charAt(c);
					++c;
				}
			} else if (ch == ' ') {
				name += makeNamePart(patternPart, gender);
				patternPart = "";
				name += ch;
			} else {
				patternPart += ch;
			}
		}
		
		if (!patternPart.isEmpty()) {
			name += makeNamePart(patternPart, gender);
		}
		
		return name;
	}
	
	private static String makeNamePart(String patternPart, String gender) {
		String namepart = "";
		
		for (int c = 0; c < patternPart.length(); ++c) {
			char ch = patternPart.charAt(c);
			String part = getRandomPart(ch, gender, c == 0);
			
			if (c != 0) {
				// second half of a name
				part = part.toLowerCase();
				
				// blend if possible;
				if (namepart.substring(namepart.length() - 1).equals(part.substring(0, 1))) {
					part = part.substring(1);
				}
			}
			namepart += part;
		}
		
		return namepart;
	}
	
	private static String getRandomPart(char code, String gender, boolean isPrefix) {
		
		Map<String, String[]> dataMap = nameTable.get(code);
		
		String[] nameList = null;
		
		if (dataMap.containsKey("*")) {
			nameList = dataMap.get("*");
		} else {
			if (dataMap.containsKey(gender)) {
				nameList = dataMap.get(gender);
			} else if (dataMap.containsKey("pre")) {
				if (isPrefix) {
					nameList = dataMap.get("pre");
				} else {
					nameList = dataMap.get("suff");
				}
			}
		}
		
		int num = NPCUtils.rollD20() - 1;
		
		return nameList[num];
	}
}
