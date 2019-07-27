package uk.co.meadicus.npcbuilder.client.npc;

import java.util.HashMap;
import java.util.Map;

public abstract class FantasyCraftStatTables {
	
	protected final static int[][] INIT_DEF_TABLE = 
		{	{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},
			{0, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9},
			{1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 7, 7, 8, 8, 9, 10, 10, 11, 11, 12},
			{1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15, 15},
			{2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 15, 16, 17, 18},
			{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
			{3, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 22, 23, 24},
			{3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27},
			{4, 5, 6, 8, 9, 10, 12, 13, 15, 16, 18, 19, 20, 22, 23, 25, 26, 28, 29, 30},
			{4, 6, 7, 9, 10, 12, 13, 15, 16, 18, 19, 21, 22, 24, 25, 27, 28, 30, 31, 33} };
				
	protected final static int[][] ATK_TABLE = 
		{	{0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10},
			{0, 1, 1, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 12},
			{0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15},
			{1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 15, 16, 17},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
			{1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21, 22},
			{2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 25},
			{2, 3, 5, 6, 7, 9, 10, 11, 13, 14, 15, 17, 18, 19, 21, 22, 23, 25, 27, 27},
			{2, 3, 5, 6, 8, 9, 11, 12, 14, 16, 17, 18, 20, 21, 23, 24, 26, 27, 29, 30},
			{3, 5, 6, 8, 9, 11, 12, 14, 15, 17, 18, 20, 21, 23, 24, 26, 27, 29, 30, 32} };
	
	protected final static int[][] RES_COMP_HLTH_TABLE = 
		{	{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6},
			{0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 8},
			{1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9},
			{1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11},
			{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},
			{2, 3, 3, 4, 5, 5, 6, 6, 7, 7, 8, 9, 9, 10, 10, 11, 11, 12, 13, 14},
			{3, 4, 4, 5, 6, 7, 7, 8, 8, 9, 10, 10, 11, 11, 12, 13, 13, 14, 14, 15},
			{3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 11, 12, 13, 13, 14, 15, 16, 16, 17},
			{4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15, 15, 16, 17, 18, 18},
			{4, 5, 6, 7, 8, 8, 9, 10, 11, 12, 12, 13, 14, 15, 16, 16, 17, 18, 19, 20} };
	
	protected final static int[][] SKILL_TABLE = 
		{	{2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12},
			{3, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 13, 13, 14, 15, 15},
			{4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15, 15, 16, 17, 18, 18},
			{5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 15, 16, 17, 18, 19, 20, 20, 21},
			{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24},
			{7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27},
			{8, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 28, 29, 30},
			{9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27, 28, 29, 31, 32, 33},
			{10, 12, 13, 14, 16, 17, 18, 20, 21, 22, 24, 25, 26, 28, 29, 30, 32, 33, 34, 36},
			{11, 12, 14, 15, 17, 18, 20, 21, 23, 24, 26, 27, 29, 30, 32, 33, 35, 36, 38, 39} };
	
	protected static class SizeMods {
		public final double woundMult;
		public final int defMod;
		public final int dmgSaveMod;
		protected SizeMods(double woundMult, int defMod, int dmgSaveMod) {
			super();
			this.woundMult = woundMult;
			this.defMod = defMod;
			this.dmgSaveMod = dmgSaveMod;
		}
	}
	
	protected final static Map<Size, SizeMods> SIZE_MODS;
	static {
		SIZE_MODS = new HashMap<Size, SizeMods>();
		SIZE_MODS.put(Size.NUISANCE, new SizeMods(1.0/8.0, 16, -10));
		SIZE_MODS.put(Size.FINE, new SizeMods(1.0/4.0, 8, -8));
		SIZE_MODS.put(Size.DIMINUATIVE, new SizeMods(1.0/3.0, 4, -6));
		SIZE_MODS.put(Size.TINY, new SizeMods(1.0/2.0, 2, -4));
		SIZE_MODS.put(Size.SMALL, new SizeMods(2.0/3.0, 1, -2));
		SIZE_MODS.put(Size.MEDIUM, new SizeMods(1, 0, 0));
		SIZE_MODS.put(Size.LARGE, new SizeMods(1.5, -1, 2));
		SIZE_MODS.put(Size.HUGE, new SizeMods(2, -2, 4));
		SIZE_MODS.put(Size.GARGANTUAN, new SizeMods(3, -4, 6));
		SIZE_MODS.put(Size.COLOSSAL, new SizeMods(4, -8, 8));
		SIZE_MODS.put(Size.ENORMOUS, new SizeMods(5, -16, 10));
		SIZE_MODS.put(Size.VAST, new SizeMods(6, -32, 12));
	}
	
	protected static class NatAtkStats {
		public final String NtoTdmgDice;
		public final String SdmgDice;
		public final String MdmgDice;
		public final String LdmgDice;
		public final String HtoGdmgDice;
		public final String CtoVdmgDice;
		public final int baseThreat;
		protected NatAtkStats(String ntoTdmgDice, String sdmgDice,
				String mdmgDice, String ldmgDice, String htoGdmgDice,
				String ctoVdmgDice, int baseThreat) {
			super();
			NtoTdmgDice = ntoTdmgDice;
			SdmgDice = sdmgDice;
			MdmgDice = mdmgDice;
			LdmgDice = ldmgDice;
			HtoGdmgDice = htoGdmgDice;
			CtoVdmgDice = ctoVdmgDice;
			this.baseThreat = baseThreat;
		}
		protected String getDamageDice(Size size) {
			String damageDice = CtoVdmgDice;
			switch(size) {
			case NUISANCE:
			case FINE:
			case DIMINUATIVE:
			case TINY:
				damageDice = NtoTdmgDice;
				break;
			case SMALL:
				damageDice = SdmgDice;
				break;
			case MEDIUM:
				damageDice = MdmgDice;
				break;
			case LARGE:
				damageDice = LdmgDice;
				break;
			case HUGE:
			case GARGANTUAN:
				damageDice = HtoGdmgDice;
			default:
				break;
			}
			return damageDice;
		}
		protected int getBaseThreat() {
			return this.baseThreat;
		}
	}
	
	protected final static Map<String, NatAtkStats> NAT_ATKS;
	static {
		NAT_ATKS = new HashMap<String, NatAtkStats>();
		NAT_ATKS.put("bite", new NatAtkStats("1d4", "1d6", "1d8", "1d10", "1d12", "2d8", 18));
		NAT_ATKS.put("claw", new NatAtkStats("1d3", "1d4", "1d6", "1d8", "1d10", "1d12", 20));
		NAT_ATKS.put("kick", new NatAtkStats("1d3", "1d4", "1d6", "1d8", "1d10", "1d12", 20));
		NAT_ATKS.put("slam", new NatAtkStats("1d3", "1d4", "1d6", "1d8", "1d10", "1d12", 20));
		NAT_ATKS.put("talon", new NatAtkStats("1d3", "1d4", "1d6", "1d8", "1d10", "1d12", 20));
		NAT_ATKS.put("gore", new NatAtkStats("1d3", "1d4", "1d6", "1d8", "1d10", "1d12", 19));
		NAT_ATKS.put("squeeze", new NatAtkStats("1d6", "1d8", "1d10", "1d12", "2d8", "2d10", 0));
		NAT_ATKS.put("swallow", new NatAtkStats("1d6", "1d8", "1d10", "1d12", "2d8", "2d10", 0));
		NAT_ATKS.put("tail slap", new NatAtkStats("1d4", "1d6", "1d8", "1d10", "1d12", "2d8", 20));
		NAT_ATKS.put("tentacle slap", new NatAtkStats("1d4", "1d6", "1d8", "1d10", "1d12", "2d8", 20));
		NAT_ATKS.put("trample", new NatAtkStats("1d4", "1d6", "1d8", "1d10", "1d12", "2d8", 20));
	}
	
	protected final static String[] DAMAGE_EO_DICE = {"", "d4", "d6", "d8", "d10", "d12"};
}
