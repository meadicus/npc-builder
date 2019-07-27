package uk.co.meadicus.npcbuilder.client.xp;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplexXP extends XP {
	
	private final Map<String, List<XP>> components = new LinkedHashMap<String, List<XP>>();

	public ComplexXP() {
		super();
	}

	public ComplexXP(boolean autofix) {
		super(autofix);
	}

	public double getValue() {
		return this.components.values().stream()
				.flatMap(List::stream)
				.collect(Collectors.summarizingDouble(XP::getValue)).getSum();
	}
	
	public Map<String, XP> getFlatComponents() {
		
		return this.components.entrySet().stream().flatMap(this::expandComponents).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}
	
	public void put(String name, int val) {
		ensureEntry(name);
		components.get(name).add(new SimpleXP(val));
	}
	
	public void put(String name, double val) {
		ensureEntry(name);
		components.get(name).add(new SimpleXP(val));
	}
	
	public void put(String name, XP xp) {
		ensureEntry(name);
		components.get(name).add(xp);
	}

	private void ensureEntry(String name) {
		if (!components.containsKey(name)) {
			components.put(name, new ArrayList<>());
		}
	}
	
	private Stream<Map.Entry<String, XP>> expandComponents(Map.Entry<String, List<XP>> entry) {
		if (entry.getValue().size() == 1) {
			return Stream.of(entry(entry.getKey(), entry.getValue().get(0)));
		} else {
			AtomicInteger counter = new AtomicInteger(0);
			return entry.getValue().stream().map(item -> entry(entry.getKey() + " (" + counter.incrementAndGet() + ")", item));
		}
	}
	
	private <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new SimpleEntry<K, V>(key, value);
	}
	
}
