package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.HashMap;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;
import uk.co.meadicus.npcbuilder.client.treasure.generator.RandomTable.RollType;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.D20MultValueAlgorithm;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.FixedSpecApplicator;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.TLSpecApplicator;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.XPMultValueAlgorithm;

public class FantasyCraftTreasureTables {

	private static final Map<TreasureType, TreasureTable> tableMap = new HashMap<TreasureType, TreasureTable>();
	
	static {
		// Cash table
		TreasureTable coinTable = new TreasureTable("7.12 Coin", TreasureType.COIN);
		coinTable.addItem(2, 5, new RandomTableRow("Coins 1/4 × XP", new XPMultValueAlgorithm(0.25)));
		coinTable.addItem(6, 10, new RandomTableRow("Coins 1/2 × XP", new XPMultValueAlgorithm(0.5)));		
		coinTable.addItem(11, 13, new RandomTableRow("Coins 1 × XP", new XPMultValueAlgorithm(1)));
		coinTable.addItem(14, 16, new RandomTableRow("Coins 2 × XP", new XPMultValueAlgorithm(2)));
		coinTable.addItem(17, 19, new RandomTableRow("Coins 3 × XP", new XPMultValueAlgorithm(3)));
		coinTable.addItem(20, 22, new RandomTableRow("Coins 4 × XP", new XPMultValueAlgorithm(4)));	
		coinTable.addItem(23, 25, new RandomTableRow("Coins 5 × XP", new XPMultValueAlgorithm(5)));	
		coinTable.addItem(26, 28, new RandomTableRow("Coins 6 × XP", new XPMultValueAlgorithm(6)));	
		coinTable.addItem(29, 31, new RandomTableRow("Coins 7 × XP", new XPMultValueAlgorithm(7)));	
		coinTable.addItem(32, 34, new RandomTableRow("Coins 8 × XP", new XPMultValueAlgorithm(8)));	
		coinTable.addItem(35, 37, new RandomTableRow("Coins 9 × XP", new XPMultValueAlgorithm(9)));	
		coinTable.addItem(38, 39, new RandomTableRow("Coins 10 × XP", new XPMultValueAlgorithm(10)));	
		coinTable.addItem(40, 41, new RandomTableRow("Coins 20 × XP", new XPMultValueAlgorithm(20)));	
		coinTable.addItem(42, 43, new RandomTableRow("Coins 30 × XP", new XPMultValueAlgorithm(30)));	
		coinTable.addItem(44, 45, new RandomTableRow("Coins 40 × XP", new XPMultValueAlgorithm(40)));	
		coinTable.addItem(46, 47, new RandomTableRow("Coins 50 × XP", new XPMultValueAlgorithm(50)));	
		coinTable.addItem(48, new RandomTableRow("Coins 75 × XP", new XPMultValueAlgorithm(75)));
		coinTable.addItem(49, new RandomTableRow("Coins 100 × XP", new XPMultValueAlgorithm(100)));
		coinTable.addItem(50, new RandomTableRow("Coins 100 × XP plus second roll", new XPMultValueAlgorithm(100), coinTable, new TLSpecApplicator()));
		tableMap.put(TreasureType.COIN, coinTable);
		
		// Species table
		RandomTable speciesTable = new RandomTable("7.17 All Things Great and Small");
		RandomTable toolUsingSpeciesITable = RandomTableBuilder.buildSimpleTable("tool-using species I", "1: Angel, 2: Brain fiend, 3–4: Bugbear, 5: Demon, 6: Doppelganger, 10: Faerie, 16: Gorgon, 7: Dragon, 11–12: Ghoul, 17: Hag, 8: Drider, 13–14: Gnoll, 18: Harpy, 9: Elemental, 15: Golem, 19–20: Hobgoblin");
		speciesTable.addItem(1, 2, new RandomTableRow("Tool-using species I", toolUsingSpeciesITable));
		RandomTable toolUsingSpeciesIITable = RandomTableBuilder.buildSimpleTable("tool-using species II", "1: Homunculus, 2: Imp, 3–4: Kobold, 5: Lamia, 6: Manticore, 11–12: Myrmidon, 17–18: Troglodyte, 7–8: Merfolk, 13: Naga, 19: Troll, 9: Minotaur, 14: Rakshasa, 20: Wight, 10: Mummy, 15–16: Sea Devil");
		speciesTable.addItem(3, 4, new RandomTableRow("Tool-using species II", toolUsingSpeciesIITable));
		RandomTable heroicSpeciesTable = RandomTableBuilder.buildSimpleTable("heroic species", "1: Drake, 2–3: Dwarf, 4–5: Elf, 6: Giant, 12–13: Ogre, 18: Rootwalker, 7–8: Goblin, 14–15: Orc, 19: Saurian, 9–11: Human, 16–17: Pech, 20: Unborn");
		speciesTable.addItem(5, 10, new RandomTableRow("Heroic species", heroicSpeciesTable));
		RandomTable commonMountTable = RandomTableBuilder.buildSimpleTable("common mount", "1–3: Camel, 4–6: Dog, 7–10: Donkey/mule, 13–17: Horse/pony, 11–12: Elephant, 18–20: Wolf");
		speciesTable.addItem(11, 12, new RandomTableRow("Common mount", commonMountTable));
		RandomTable animalTable = RandomTableBuilder.buildSimpleTable("animal", "1: Ape, 2: Basilisk, 3: Bear, 4: Bulette, 5: Darkmantle, 6: Dinosaur, 11: Livestock/game animal, 16: Giant spider, 7: Eagle, 12: Monstrous insect, 17: Stirge, 8: Griffon, 13: Pegasus, 18: Tiger, 9: Hippocampus, 14: Raptor, 19: Giant turtle, 10: Kraken, 15: Rhino, 20: Water serpent");
		speciesTable.addItem(13, 14, new RandomTableRow("Animal", animalTable));
		RandomTable otherCreatureITable = RandomTableBuilder.buildSimpleTable("other creature I", "1–3: Barghest, 4–5: Burrowing behemoth, 6–7: Chaos beast, 8–10: Chimera, 14: Jelly, 18–19: Nightmare, 11: Gelatinous cube, 15–16: Mimic, 20: Pudding, 12–13: Hell hound, 17: Monstrous plant");
		speciesTable.addItem(15, 17, new RandomTableRow("Other creature I", otherCreatureITable));
		RandomTable otherCreatureIITable = RandomTableBuilder.buildSimpleTable("other creature II", "1–2: Rust monster, 3–4: Salamander, 5–7: Scavenger worm, 8–9: Shadow beast, 13–14: Unicorn, 18–20: Wyvern, 10–11: Sporling, 15: Watcher in the dark, 12: Tarasque, 16–17: Will o’ wisp ");
		speciesTable.addItem(18, 20, new RandomTableRow("Other creature II", otherCreatureIITable));
		
		// Plot table
		RandomTable plotTable = RandomTableBuilder.buildSimpleTable("7.18 plot table", "1: The item was created with a purpose, 2: The item hails from a previous age, 3: The item... isn’t an item, 4: The item features sockets or other crevasses, 5: The item is... odd, 6: The item is part of a set, 7: The item is made from an unusual material, 8: The item bears a unique crest or other identifying mark, 9: The item is central to a prophecy, 10: The item is haunted by one or more ghosts or other spirits, 11: The item is hollow, 12: The item is stolen, 13: The item was involved in recent events, 14: The item contains or bears a map or clues leading to a location, 15: The item is famous, 16: The item was forged by the divine, 17: The item changes shape and function once a day at a random time, 18: The item is cursed, 19: The item is intelligent");
		plotTable.addItem(20, new RandomTableRow("GM choice, random here", plotTable));
		
		// Treasure type table, declared here so it can be used, populated lower down after all treasure tables have been declared
		TreasureTable typeTable = new TreasureTable("random type", RollType.EVEN, false, TreasureType.ANY);
				
		// Gear table
		TreasureTable gearTable = new TreasureTable("7.13 Gear", TreasureType.GEAR);
		RandomTable cheapGoodsTable = RandomTableBuilder.buildSimpleTable("cheap goods", "1–2: Garrote, 4: Whistle, 3: Lanyard, 5–6: Hand axe, 7–8: Blanket or bedroll, 9: Brand, 10–11: Canteen or waterskin, 12: Fishing pole or net, 13–14: Tool hammer, 15: Average map, 16: Pick or shovel, 17: Pipe, 18: Saw, 19: Umbrella, 20: Grappling hook");
		gearTable.addItem(2, 4, new RandomTableRow("Cheap goods", cheapGoodsTable, 158));
		RandomTable foodTable = RandomTableBuilder.buildSimpleTable("food & drink", "1–2: Common meals,9–10: Comfort food,3–4: Animal feed,11–12: Filling food,19: Fresh food,5–6: Rations,13–14: Hearty food,20: Spices,7–8: Coffee or tea,15–16: Booze,17–18: Spirits");
		gearTable.addItem(5, 6, new RandomTableRow("Spoiled food & drink, 2d6 servings", foodTable, 165));
		gearTable.addItem(7, 9, new RandomTableRow("Food & drink, 2d6 servings", foodTable, 165));
		RandomTable consumablesTable = RandomTableBuilder.buildSimpleTable("consumables", "1–2: Candles, 11: Bandages, 16: Tonics, 3–4: Pints of oil, 12: Jars of body paint, 17: Jars of leeches, 5–6: Pieces of chalk, 13: Sheaves of paper, 18: Salves, 7–8: Jars of common ink, 14: Smelling salts, 19: Balms, 9–10: Torches, 15: Jars of invisible ink, 20: Ointments");
		gearTable.addItem(10, 11, new RandomTableRow("Spoiled or destroyed consumables, 1d4 items", consumablesTable, 162));
		gearTable.addItem(12, 14, new RandomTableRow("Consumables, 1d4 items", consumablesTable, 162));
		RandomTable valuedGoodsTable = RandomTableBuilder.buildSimpleTable("valued goods", "1: Hemp rope (50 ft.), 9: Chain (10 ft.), 15: Glasses or goggles, 2–3: Firesteel or tinderbox, 10: Game (GM’s choice), 16: Bandolier, 4: Grooming case, 11: Magnet, 17: Manacles, 5–6: Candle lantern, 12: Snowshoes, 18: Silk rope (50 ft.), 7: Skis, 13: Tent, 19–20: Hooded lantern, 8: Block and tackle, 14: Hourglass or sundial");
		gearTable.addItem(15, 17, new RandomTableRow("Valued goods", valuedGoodsTable, 158));
		RandomTable emptyContainersTable = RandomTableBuilder.buildSimpleTable("containers", "1–2: Jar/jug, 9–10: Saddlebags, 15–16: Large sack, 3–5: Pouch, 11–12: Backpack, 17–18: Small chest, 6–8: Purse, 13–14: Small sack, 19–20: Large chest");
		RandomTable containersTable = new RandomTable("containers");
		containersTable.addItem(1, 2, new RandomTableRow("Jar/jug containing:", typeTable, new TLSpecApplicator()));
		containersTable.addItem(3, 5, new RandomTableRow("Pouch containing:", coinTable, new TLSpecApplicator()));
		containersTable.addItem(6, 8, new RandomTableRow("Purse containing:", coinTable, new TLSpecApplicator()));
		containersTable.addItem(9, 10, new RandomTableRow("Saddlebags containing:", typeTable, new TLSpecApplicator()));
		containersTable.addItem(11, 12, new RandomTableRow("Backpack containing:", typeTable, new TLSpecApplicator()));
		containersTable.addItem(13, 14, new RandomTableRow("Small sack containing:", typeTable, new TLSpecApplicator()));
		containersTable.addItem(15, 16, new RandomTableRow("Large sack containing:", typeTable, new TLSpecApplicator()));
		containersTable.addItem(17, 18, new RandomTableRow("Small chest containing:", typeTable, new TLSpecApplicator()));
		containersTable.addItem(19, 20, new RandomTableRow("Large chest containing:", typeTable, new TLSpecApplicator()));
		gearTable.addItem(18, 19, new RandomTableRow("Destroyed container", emptyContainersTable, 158));
		gearTable.addItem(20, 22, new RandomTableRow("Container", containersTable, 158));
		RandomTable weaponsTable = RandomTableBuilder.buildSimpleTable("weapons", "1–5: Blunt, 11–15: Hurled, 6–10: Edged, 16–18: Bows, 19–20: Black powder");
		gearTable.addItem(23, 24, new RandomTableRow("Destroyed weapon", weaponsTable, 176));
		gearTable.addItem(25, 26, new RandomTableRow("Broken weapon", weaponsTable, 176));
		gearTable.addItem(27, 30, new RandomTableRow("Weapon", weaponsTable, 176));
		RandomTable weaponUpgradesTable = RandomTableBuilder.buildSimpleTable("weapons upgrades", "3: Crude materials, 9: Bayonet, 15: Bleed, 4: Lure, 10: Massive, 16: Guard, 5: Cord, 11: Hurl, 17: Injector †, 6: Grip, 12: Trip, 18: Armor-piercing, 7: Hook, 13: Bludgeon, 19: Superior materials, 8: Cavalry, 14: Keen, 20: Finesse");
		weaponUpgradesTable.addItem(1, 2, new RandomTableRow("Craftsmanship", speciesTable));
		gearTable.addItem(31, new RandomTableRow("Upgraded weapon", weaponsTable, weaponUpgradesTable, 185));
		RandomTable preciousGoodTable = RandomTableBuilder.buildSimpleTable("precious goods", "1–2: Mirror, 8–9: Flag or standard, 16: Music box, 3–4: Bullseye lantern, 10–11: Seal or signet ring, 17: Spyglass, 5: Poison ring †, 12: Magnifying glass, 18: Pocket watch, 6: Detailed map, 13–14: Musical instrument, 19: Holy symbol (GM’s choice), 7: Hand scale, 15: Astrolabe, 20: Holy book (GM’s choice)");
		gearTable.addItem(32, 34, new RandomTableRow("Precious good", preciousGoodTable, 158));
		RandomTable preciousUpgradesTable = RandomTableBuilder.buildSimpleTable("precious goods upgrades", "1–12: Durable, 13–16: Hollow, 17–20: Masterwork");
		gearTable.addItem(35, 36, new RandomTableRow("Upgraded precious good", preciousGoodTable, preciousUpgradesTable, 159));
		RandomTable poisonsTable = RandomTableBuilder.buildSimpleTable("poisons", "1: Putrid, 8: Intoxicating, 15: Paranoia, 2: Deafening, 9: Maddening, 16: Lethal, 3: Sickening, 10: Stupefying, 17: Knockout, 4: Numbing, 11: Weakening, 18: Baffling, 5: Agonizing, 12: Debilitating, 19: Blinding, 6: Necrotic, 13: Disorienting, 20: Paralyzing, 7: Slowing, 14: Enraging");
		gearTable.addItem(37, 38, new RandomTableRow("Spoiled poison 1d4 uses", poisonsTable, 166));
		gearTable.addItem(39, 41, new RandomTableRow("Poison 1d4 uses", poisonsTable, 166));
		RandomTable poisonUpgradesTable = new RandomTable("posion upgrades");
		poisonUpgradesTable.addItem(1, 3, new RandomTableRow("Concentrated", poisonsTable));
		poisonUpgradesTable.addItem(4, 6, new RandomTableRow("Persistent", poisonsTable));
		poisonUpgradesTable.addItem(7, 9, new RandomTableRow("Exotic", poisonsTable));
		poisonUpgradesTable.addItem(10, 12, new RandomTableRow("Potent", poisonsTable));
		poisonUpgradesTable.addItem(13, 14, new RandomTableRow("Fast-acting", poisonsTable));
		poisonUpgradesTable.addItem(15, 16, new RandomTableRow("Gas", poisonsTable));
		poisonUpgradesTable.addItem(17, 18, new RandomTableRow("Virulent", poisonsTable));
		RandomTableRow cocktailRow = new RandomTableRow("Cocktail", poisonsTable);
		cocktailRow.addChildTable(poisonsTable);
		poisonUpgradesTable.addItem(19, 20, cocktailRow);
		gearTable.addItem(42, new RandomTableRow("Upgraded poison", poisonsTable, poisonUpgradesTable, 166));
		RandomTable armourTable = RandomTableBuilder.buildSimpleTable("armour", "1–2: Padded (partial), 10: Heavy fittings, 16: Hardened leather (moderate), 3–4: Leather (partial), 11: Chainmail (partial), 17: Platemail (partial), 5–6: Padded (moderate), 12: Hardened leather (partial), 18: Scalemail (moderate), 7: Light fittings, 13: Studded leather (full), 19: Platemail (moderate), 8: Studded leather (partial), 14: Scalemail (partial), 20: Articulated plate (moderate), 9: Leather (moderate), 15: Chainmail (moderate)");
		gearTable.addItem(43, new RandomTableRow("Destroyed armour", armourTable, 174));
		gearTable.addItem(44, new RandomTableRow("Broken armour", armourTable, 174));
		gearTable.addItem(45, 47, new RandomTableRow("Armour", armourTable, 174));
		RandomTable armourUpgradesTable = RandomTableBuilder.buildSimpleTable("armour Upgrades", "4–5: Fitted, 11: Fireproofed, 18: Discreet, 6–7: Crude materials, 12: Ceremonial, 19: Cushioned, 8: Vented, 13: Blessed, 20: Reinforced, 9: Warm, 14–15: Superior materials, 10: Insulated, 16–17: Lightweight");
		armourUpgradesTable.addItem(1, 3, new RandomTableRow("Craftsmanship", speciesTable));
		gearTable.addItem(48, new RandomTableRow("UpgradedArmour", armourTable, armourUpgradesTable, 175));
		gearTable.addItem(49, new RandomTableRow("Plot item (~~~)", plotTable, gearTable));
		gearTable.addItem(50, new RandomTableRow("2 items, first is GM choice, random here", gearTable, gearTable, new TLSpecApplicator(), true));
		tableMap.put(TreasureType.GEAR, gearTable);
		
		TreasureTable lootTable = new TreasureTable("7.14 Loot", TreasureType.LOOT);
		RandomTable rawMaterialsTable = RandomTableBuilder.buildSimpleTable("raw materials", "1: Carving, 9–10: Metalworking, 18: Scrolls, 2–3: Carpentry, 11: Pharmacy, 19: Elixirs, 4: Chemistry, 12–13: Pottery, 20: Magic items, 5–6: Cooking, 14–15: Stonecutting, 7–8: Inscription, 16–17: Tailoring");
		lootTable.addItem(2, 3, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(1)));
		RandomTable tradeGoodsTable = RandomTableBuilder.buildSimpleTable("trade goods", "1: Bone/ivory (10 lbs.), 8: Furniture/décor (50 lbs.), 15: Oil (10 lbs.), 2: Chemicals (2 lbs.), 9: Grains (25 lbs.), 16: Paint/dye (5 lbs.), 3: Cosmetics/perfume (2 lbs.), 10: Herbs/spices (10 lbs.), 17: Paper/parchment (10 lbs.), 4: Drugs (5 lbs.), 11: Incense (2 lbs.), 18: Stone (100 lbs.), 5: Fabric (50 lbs.), 12: Livestock (750 lbs.), 19: Tobacco (10 lbs.), 6: Fruit/vegetables (25 lbs.), 13: Metal (100 lbs.), 20: Wood (25 lbs.), 7: Fur/hide (25 lbs.), 14: Nuts (25 lbs.)");
		lootTable.addItem(4, 5, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(2)));
		RandomTable clothingTable = RandomTableBuilder.buildSimpleTable("piece of clothing", "1: Belt, 8: Gloves, 15: Shirt, 2: Boots, 9: Handkerchief, 16: Shoes/sandals, 3: Coat, 10: Hat/headband, 17: Tunic, 4: Cloak/cape, 11: Loincloth/stockings, 18: Veil, 5: Dress, 12: Pants, 19: Vest, 6: Eyepatch, 13: Robe, 20: Vestments, 7: Glasses/monocle, 14: Sash");
		RandomTable jewelryTable = RandomTableBuilder.buildSimpleTable("jewelry or personal effect", "1: Amulet, 8: Collar/choker, 15: Pendant, 2: Anklet/bracelet, 9: Crown/tiara, 16: Ring, 3: Armband/bracer, 10: Earring/piercing, 17: Scepter, 4: Brooch/medal, 11: Fastener, 18: Talisman, 5: Cane/walking stick, 12: Locket, 19: Tattoo (with skin), 6: Charm/trinket (per GM), 13: Mask, 20: Torc, 7: Circlet, 14: Necklace");
		RandomTable pieceOfArtTable = RandomTableBuilder.buildSimpleTable("piece of art", "15: Sculpture (10 lbs.), 16: Sheet music (1/10 lb.), 2: Book/scroll/text (5 lbs.), 3: Crystalware (5 lbs.), 17: Statuary (500 lbs.), 4: Embroidery (5 lbs.), 11: Magic art (1d20 lbs.), 5: Glassware (5 lbs.), 12: Mosaic/relief/carving (1000 lbs.), 19: Tableware (5 lbs.), 6: Illusion (0 lbs no value), 13: Painting/drawing/etching (2 lbs.), 20: Woodblock print (2 lbs.), 1: Architecture (1000 lbs.), 7: Invention (1d20 lbs.), 14: Pottery (5 lbs.)");
		pieceOfArtTable.addItem(8, new RandomTableRow("Ornate jewlery", jewelryTable));
		pieceOfArtTable.addItem(9, new RandomTableRow("Ornate clothing", clothingTable));
		pieceOfArtTable.addItem(10, new RandomTableRow("Ornate gear", gearTable));
		pieceOfArtTable.addItem(18, new RandomTableRow("Stuffed critter", speciesTable));
		lootTable.addItem(6, 7, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(5)));
		RandomTable gemTable = RandomTableBuilder.buildSimpleTable("gems", "5: Amethyst, 18: Iolite, 31: Quartz, 6: Aquamarine, 19: Jacinth, 32: Rhodochrosite, 7: Azurite, 20: Jade, 33: Ruby, 8: Beryl, 21: Jasper, 34: Sapphire, 9: Bloodstone, 22: Jet, 35: Sard, 10: Carnelian, 23: Lapis lazuli, 36: Spinel, 11: Citrine, 24: Malachite, 37: Topaz, 12: Coral, 25: Moonstone, 38: Tourmaline, 13: Corundum, 26: Obsidian, 39: Turquoise, 14: Diamond, 27: Onyx, 40: Zircon, 2: Agate, 3: Alexandrite, 16: Garnet, 29: Pearl, 4: Amber, 17: Hematite, 30: Peridot, 15: Emerald, 28: Opal/fire opal", RollType.D40);
		lootTable.addItem(8, 9, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(10)));
		lootTable.addItem(10, 11, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(2)));
		lootTable.addItem(12, 13, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(5)));
		lootTable.addItem(14, 15, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(10)));
		lootTable.addItem(16, 17, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(25)));
		lootTable.addItem(18, 19, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(5)));
		lootTable.addItem(20, 21, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(10)));
		lootTable.addItem(22, 23, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(25)));
		lootTable.addItem(24, 25, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(50)));
		lootTable.addItem(26, 27, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(10)));
		lootTable.addItem(28, 29, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(25)));
		lootTable.addItem(30, 31, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(50)));
		lootTable.addItem(32, 33, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(100)));
		lootTable.addItem(34, 35, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(25)));
		lootTable.addItem(36, 37, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(50)));
		lootTable.addItem(38, 39, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(100)));
		lootTable.addItem(40, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(250)));
		lootTable.addItem(41, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(50)));
		lootTable.addItem(42, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(100)));
		lootTable.addItem(43, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(250)));
		lootTable.addItem(44, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(500)));
		lootTable.addItem(45, new RandomTableRow("Raw materials", rawMaterialsTable, new D20MultValueAlgorithm(100)));
		lootTable.addItem(46, new RandomTableRow("Trade goods", tradeGoodsTable, new D20MultValueAlgorithm(250)));
		lootTable.addItem(47, new RandomTableRow("Piece of art", pieceOfArtTable, new D20MultValueAlgorithm(500)));
		lootTable.addItem(48, new RandomTableRow("Gem", gemTable, new D20MultValueAlgorithm(1000)));
		lootTable.addItem(49, new RandomTableRow("Plot item (~~~)", plotTable, lootTable));
		lootTable.addItem(50, new RandomTableRow("2 items, first is GM choice, random here", lootTable, lootTable, new TLSpecApplicator(), true));
		tableMap.put(TreasureType.LOOT, lootTable);
		
		RandomTable levelZeroSpellsTable = RandomTableBuilder.buildEvenTable("level 0 spells", "Create Water~Endure Elements~Glow I~Read Magic~Dancing Lights~Expeditious Retreat~Magic Vestment I~Touch of Light~Detect Alignment~Feather Fall~Orient Self~Water Walk~Detect Secret Doors~Flare~Polar Ray I~Whispers", "~", false);
		RandomTable levelOneSpellsTable = RandomTableBuilder.buildEvenTable("level 1 spells", "Alarm~Control Weather I~Insight~Ray of Enfeeblement~Animate Dead I~Cure Wounds I~Jump~Scare I~Bless~Deathwatch~Mage Scribe I~Scrye I~Call from Beyond I~Detect Magic~Magic Aura~Shatter~Cause Wounds I~Disguise Self~Magic Missile~Shield~Charm Person I~Divine Favor~Magic Stone~Sleep I~Color Spray~Entangle~Magic Weapon I~Tinker I~Command I~Entropic Shield~Nature’s Ally I~True Strike I~Concealing Countryside I~Identify I~Pass without Trace~Unseen Servant~Conjure Elemental I~Illusionary Image I~Protection from Alignment~Winter’s Domain I", "~", false);
		RandomTable levelTwoSpellsTable = RandomTableBuilder.buildEvenTable("level 2 spells", "Align Weapon~Darkness I~Insanity I~Righteous Aura~Arcane Lock~Deadly Draft I~Knock~Scare II~Blindness/Deafness~Death Knell~Levitate~Scorching Ray~Blur~Detect Emotion~Living Library I~Silence~Brawn I~Dominate Undead I~Locate Object~Shield Other~Calm Emotions~Goodberry~Mage Armor~Status I~Cause Wounds II~Gust of Wind~Mirror Images~Tinker II~Chill Storm I~Hold Animal~Obscure Object~Water Walk, Mass~Consecrate~Hold Person~Resist Energy~Wild Side I~Cure Wounds II~Illusionary Image II~Restoration I~Wit I", "~", false);
		RandomTable levelThreeSpellsTable = RandomTableBuilder.buildEvenTable("level 3 spells", "Animate Dead II~Cure Wounds III~Keen Edge~Slow~Bestow Curse~Darkness II~Lift Curse I~Shape Stone~Call from Beyond II~Fireball I~Magic Vestment II~Speak with the Dead~Call Lightning I~Fly I~Nature’s Ally II~Tiny Shelter~Cause Wounds III~Glow II~Neutralize Poison~Tongues I~Charm Person II~Glyph of Protection I~Prayer~Verdure~Confounding Images~Haste~Scrye II~Wall of Wind~Control Weather II~Heroism I~Searing Ray~Water Breathing~Conjure Elemental II~Illusionary Image III~See Invisible~Wish I~Counter Magic I~Invisibility~Sleep II~Zone of Truth", "~", false);
		RandomTable levelFourSpellsTable = RandomTableBuilder.buildEvenTable("level 4 spells", "Air Walk~Detect Traps~Illusionary Image IV~Resilient Sphere I~Brawn II~Devotion Hammer~Lightning Bolt I~Restoration II~Castigate I~Dimension Door~Mage Scribe II~Rusting Grasp~Cause Wounds IV~Divine Power~Magic Weapon II~Spell Immunity I~Chill Storm II~Elemental Shield~Mantle of the Mundane~True Strike II~Concealing Countryside II~Flawless Fib~Move Water~Wall of Fire~Cure Wounds IV~Freedom of Movement~Phantasmal Killer~Wall of Ice~Detect Lies~Geas~Polar Ray II~Wit II", "~", false);
		RandomTable levelFiveSpellsTable = RandomTableBuilder.buildEvenTable("level 5 spells", "Animate Dead III~Cone of Cold~Light’s Grace~Scrye III~Brawn I, Mass~Conjure Elemental III~Living Library II~Teleport I~Call from Beyond III~Control Weather III~Mark of Justice~True Seeing~Call Lightning II~Cure Wounds I, Mass~Move Earth~Wall of Counter Magic~Cause Wounds I, Mass~Fly II~Natural Attunement~Wall of Stone~Charm Person III~Heal~Nature’s Ally III~Wild Side II~Cloudkill~Illusionary Image V~Power Word: Harm~Winter’s Domain II~Command II~Insanity II~Resurrection I~Wit I, Mass", "~", false);
		RandomTable levelSixSpellsTable = RandomTableBuilder.buildEvenTable("level 6 spells", "Anti-Magic Field I~Blindness/Deafness, Mass~Brawn III~Cause Wounds II, Mass~Cure Wounds II, Mass~Disintegrate~Find the Path~Glyph of Protection II~Harm~Heroes’ Feast~Heroism II~Identify II~Illusionary Image VI~Lift Curse II~Lightning Bolt II~Permanency~Power Word: Recall~Quake Touch~Repelling Wave I~Status, Mass~Tongues II~Tree Walk~Wish II~Wit III", "~", false);
		RandomTable levelSevenSpellsTable = RandomTableBuilder.buildEvenTable("level 7 spells", "Animate Dead IV~Call from Beyond IV~Cause Wounds III, Mass~Charm Person IV~Control Weather IV~Conjure Elemental IV~Counter Magic II~Cure Wounds III, Mass~Deadly Draft II~Dominate Undead II~Finger of Death~Fireball II~Fire Storm~Hindsight~Nature’s Ally IV~Phase Door~Project Pres~Regenerate~Resurrection II~Scrye IV~Shadow Walk~Sunlight I~Teleport II", "~", false);


		TreasureTable magicTable = new TreasureTable("7.15 Magic", TreasureType.MAGIC); 
		RandomTableBuilder.populateRandomTable(magicTable, "4: 1d4 purifying oils or sustaining potions (party’s choice), 5: 1d4 weak acid vials, 6: 1d4 vitality potions, 8: 1d4 repairing oils, 9: 1d4 magic weapon oils, 10: 1d4 blessing oils, 11: 1d4 weather vials, 12: 1d4 striking potions, 13: 1d4 shattering vials, 14: 1d4 healing potions, 15: 1d4 mana potions, 17: 1d4 obscuring oils, 19: 1d4 consecrating oils, 20: 1d4 darkvision potions, 21: 1d4 blurring oils, 22: 1d4 mage armor oils, 23: 1d4 wilding oils, 24: 1d4 confidence or refreshing potions (party’s choice), 25: 1d4 strong acid vials, 26: 1d4 hold person vials, 27: 1d4 restoration oils, 29: 1d4 tongues potions, 30: 1d4 love potions, 31: 1d4 sanctifying oils, 32: 1d4 animating vials, 33: 1d4 invisibility potions, 35: 1d4 anointed vials, 37: 1d4 boost attribute potions");
		magicTable.addItem(2, new RandomTableRow("1 Level 0 scroll", levelZeroSpellsTable));
		magicTable.addItem(3, new RandomTableRow("1 Level 1 scroll", levelOneSpellsTable));
		magicTable.addItem(7, new RandomTableRow("1 Level 2 scroll", levelTwoSpellsTable));
		magicTable.addItem(16, new RandomTableRow("1 Level 3 scroll", levelThreeSpellsTable));
		magicTable.addItem(28, new RandomTableRow("1 Level 4 scroll", levelFourSpellsTable));
		magicTable.addItem(34, new RandomTableRow("1 Level 5 scroll", levelFiveSpellsTable));
		magicTable.addItem(36, new RandomTableRow("1 Level 6 scroll", levelSixSpellsTable));
		magicTable.addItem(38, new RandomTableRow("1 Level 7 scroll", levelSevenSpellsTable));
		RandomTable resistingOilsTable = RandomTableBuilder.buildSimpleTable("resistng oils", "1: Acid, 3: Electrical, 2: Cold, 4: Fire, 5: Heat, 6: Sonic", RollType.EVEN);
		magicTable.addItem(18, new RandomTableRow("1d4 resisting oils", resistingOilsTable));
		RandomTable oilCocktailTable = RandomTableBuilder.buildSimpleTable("oil cocktail", "1–2: Blessing, 5–6: Mage armor, 3: Blurring, 7–8: Magic weapon, 4: Consecrating, 9–10: Obscuring, 5: Heat, 6: Sonic, 11–12: Purifying, 13–14: Repairing, 15–16: Resistance, 17: Restoration, 18: Sanctifying, 19–20: Wilding");
		magicTable.addItem(39, new RandomTableRow("Oil cocktail", oilCocktailTable, oilCocktailTable));
		RandomTable potionCocktailTable = RandomTableBuilder.buildSimpleTable("potion cocktail", "1: Boost attribute, 5–6: Healing, 10–11: Mana, 15–16: Sustaining, 2: Confidence, 7: Invisibility, 12: Refreshing, 17–18: Tongues, 3–4: Darkvision, 8–9: Love, 13–14: Striking, 19–20: Vitality");
		magicTable.addItem(40, new RandomTableRow("Potion cocktail", potionCocktailTable, potionCocktailTable));
		RandomTable vialCocktailTable = RandomTableBuilder.buildSimpleTable("vail cocktail", "1–4: Animating, 9–12: Hold person, 17–20: Weather, 5–8: Anointed, 13–16: Shattering");
		magicTable.addItem(41, new RandomTableRow("Vial cocktail", vialCocktailTable, vialCocktailTable));
		RandomTable artifactEssencesTable = RandomTableBuilder.buildSimpleTable("artifact essences", "1–4: 2 Lesser Essences, 9–11: 3 Lesser Essences, 15–16: 4 Lesser Essences, 19: 5 Lesser Essences, 5–8: 2 Greater Essences, 12–14: 3 Greater Essences, 17–18: 4 Greater Essences, 20: 5 Greater Essences");
		RandomTable artifactCharmsTable = RandomTableBuilder.buildSimpleTable("artifact charms", "1–4: 2 Lesser Charms, 9–11: 3 Lesser Charms, 15–16: 4 Lesser Charms, 19: 5 Lesser Charms, 5–8: 2 Greater Charms, 12–14: 3 Greater Charms, 17–18: 4 Greater Charms, 20: 5 Greater Charms");
		// TODO random choosing of essences and/or charms
		magicTable.addItem(42, new RandomTableRow("1 magic item (party chooses object, 1 Lesser Essence or 1 Lesser Charm — party’s choice)"));
		magicTable.addItem(43, new RandomTableRow("1 magic item (party chooses object, 1 Greater Essence or 1 Greater Charm — party’s choice)"));
		magicTable.addItem(44, new RandomTableRow("1 magic item (party chooses object, 1 Lesser Essence & 1 Lesser Charm)"));
		magicTable.addItem(45, new RandomTableRow("1 magic item (party chooses object, 1 Greater Essence & 1 Lesser Charm)"));
		magicTable.addItem(46, new RandomTableRow("1 magic item (party chooses object, 1 Lesser Essence & 1 Greater Charm)"));
		magicTable.addItem(47, new RandomTableRow("1 magic item (party chooses object, 1 Greater Essence & 1 Greater Charm)"));
		magicTable.addItem(48, new RandomTableRow("Atrifact", artifactEssencesTable, artifactCharmsTable));
		magicTable.addItem(49, new RandomTableRow("Plot item (~~~)", plotTable, magicTable));
		magicTable.addItem(50, new RandomTableRow("2 items, first is GM choice, random here", magicTable, magicTable, new TLSpecApplicator(), true));
		tableMap.put(TreasureType.MAGIC, magicTable);
	
		TreasureTable trophieTable = new TreasureTable("7.16 Trophies", TreasureType.TROPHIES);
		RandomTable bodyPartTable = RandomTableBuilder.buildSimpleTable("body part", "1: Brain/control mechanism, 8: Heart/engine/rib cage, 15: Skull/jaw/horn, 2: Ear/lobe/sensor, 9: Knuckle bone/joint gear, 16: Spine/fin/barb, 3: Entrails/blood/slime/ooze, 10: Limb/tail/wing, 17: Teeth/mandible/pincer/stinger, 4: Eye/antenna/sensor, 11: Loins/breast, 18: Tongue/tentacle/vine, 5: Finger/toe/claw/talon, 12: Nose/snout/beak/gills, 19: Venom sack/gas gland/fire lung/silk spinner, 6: Hair/fur/feather/leaf, 13: Scalp/mane/face, 20: Pristine body (perfect for stuffing!), 7: Hand/foot/hoof/paw/root, 14: Skin/hide/bark/scale/shell");
		trophieTable.addItem(2, 5, new RandomTableRow("Body part", bodyPartTable));
		trophieTable.addItem(6, 7, new RandomTableRow("Flawless body part", bodyPartTable, new D20MultValueAlgorithm(2)));
		trophieTable.addItem(8, new RandomTableRow("Ruined piece of clothing", clothingTable));
		trophieTable.addItem(9, 11, new RandomTableRow("1 piece of clothing", clothingTable, new D20MultValueAlgorithm(1)));
		trophieTable.addItem(12, 13, new RandomTableRow("Flawless piece of clothing", clothingTable, new D20MultValueAlgorithm(2)));
		trophieTable.addItem(14, new RandomTableRow("Ruined piece of jewelry", jewelryTable));
		trophieTable.addItem(15, 17, new RandomTableRow("1 piece of jewelry", jewelryTable, new D20MultValueAlgorithm(25)));
		trophieTable.addItem(18, 19, new RandomTableRow("Flawless piece of jewelry", jewelryTable, new D20MultValueAlgorithm(50)));
		trophieTable.addItem(20, 22, new RandomTableRow("Body part", bodyPartTable));
		trophieTable.addItem(23, 24, new RandomTableRow("Flawless body part", bodyPartTable, new D20MultValueAlgorithm(5)));
		trophieTable.addItem(25, new RandomTableRow("Ruined piece of clothing", clothingTable));
		trophieTable.addItem(26, 28, new RandomTableRow("1 piece of clothing", clothingTable, new D20MultValueAlgorithm(5)));
		trophieTable.addItem(29, 30, new RandomTableRow("Flawless piece of clothing", clothingTable, new D20MultValueAlgorithm(10)));
		trophieTable.addItem(31, new RandomTableRow("Ruined piece of jewelry", jewelryTable));
		trophieTable.addItem(32, 34, new RandomTableRow("1 piece of jewelry", jewelryTable, new D20MultValueAlgorithm(100)));
		trophieTable.addItem(35, 36, new RandomTableRow("Flawless piece of jewelry", jewelryTable, new D20MultValueAlgorithm(250)));
		trophieTable.addItem(37, 39, new RandomTableRow("Body part", bodyPartTable));
		trophieTable.addItem(40, new RandomTableRow("Flawless body part", bodyPartTable, new D20MultValueAlgorithm(10)));
		trophieTable.addItem(41, new RandomTableRow("Ruined piece of clothing", clothingTable));
		trophieTable.addItem(42, 43, new RandomTableRow("1 piece of clothing", clothingTable, new D20MultValueAlgorithm(25)));
		trophieTable.addItem(44, new RandomTableRow("Flawless piece of clothing", clothingTable, new D20MultValueAlgorithm(50)));
		trophieTable.addItem(45, new RandomTableRow("Ruined piece of jewelry", jewelryTable));
		trophieTable.addItem(46, 47, new RandomTableRow("1 piece of jewelry", jewelryTable, new D20MultValueAlgorithm(500)));
		trophieTable.addItem(48, new RandomTableRow("Flawless piece of jewelry", jewelryTable, new D20MultValueAlgorithm(1000)));
		trophieTable.addItem(49, new RandomTableRow("Plot item (~~~)", plotTable, trophieTable));
		trophieTable.addItem(50, new RandomTableRow("2 items, first is GM choice, random here", trophieTable, trophieTable, new TLSpecApplicator(), true));
		tableMap.put(TreasureType.TROPHIES, trophieTable);
		
		typeTable.addItem(1, new RandomTableRow("1T", trophieTable, false));
		typeTable.addItem(2, new RandomTableRow("1G", gearTable, false));
		typeTable.addItem(3, new RandomTableRow("1C", coinTable, false));
		typeTable.addItem(4, new RandomTableRow("1L", lootTable, false));
		typeTable.addItem(5, new RandomTableRow("1M", magicTable, false));
		
		TreasureTable anyTable = new TreasureTable("7.11 Any", TreasureType.ANY);
		anyTable.addItem(2, 5, new RandomTableRow("1T", trophieTable));
		anyTable.addItem(6, 10, new RandomTableRow("1G", gearTable));
		anyTable.addItem(11, 15, new RandomTableRow("1C", coinTable));
		anyTable.addItem(16, 20, new RandomTableRow("1L", lootTable));
		anyTable.addItem(21, 25, new RandomTableRow("1M", magicTable));
		anyTable.addItem(26, 29, new RandomTableRow("2 categies (GM's choice, here random) with -5 modifier", typeTable, typeTable, new FixedSpecApplicator(-5), true));
		anyTable.addItem(30, 33, new RandomTableRow("2 categies (party's choice, here random) with -5 modifier", typeTable, typeTable, new FixedSpecApplicator(-5), true));
		anyTable.addItem(34, 36, new RandomTableRow("1T +5 modifier", trophieTable, new FixedSpecApplicator(5)));
		anyTable.addItem(37, 39, new RandomTableRow("1G +5 modifier", gearTable, new FixedSpecApplicator(5)));
		anyTable.addItem(40, 42, new RandomTableRow("1C +5 modifier", coinTable, new FixedSpecApplicator(5)));
		anyTable.addItem(43, 45, new RandomTableRow("1L +5 modifier", lootTable, new FixedSpecApplicator(5)));
		anyTable.addItem(46, 48, new RandomTableRow("1M +5 modifier", magicTable, new FixedSpecApplicator(5)));
		anyTable.addItem(49, new RandomTableRow("2 categies (GM's choice, here random)", typeTable, typeTable, true));
		anyTable.addItem(50, new RandomTableRow("2 categies (party's choice, here random)", typeTable, typeTable, true));
		tableMap.put(TreasureType.ANY, anyTable);
	}
	
	private static FantasyCraftTreasureTables instance = null; 
	private FantasyCraftTreasureTables() { }
	public static FantasyCraftTreasureTables getInstance() {
		if (instance == null) {
			instance = new FantasyCraftTreasureTables();
		}
		return instance;
	}
	
	public TreasureTable getTreasureTable(TreasureType type) {
		return tableMap.get(type);
	}
}
