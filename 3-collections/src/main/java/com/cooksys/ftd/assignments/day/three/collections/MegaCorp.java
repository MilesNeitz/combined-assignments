package com.cooksys.ftd.assignments.day.three.collections;

import com.cooksys.ftd.assignments.day.three.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.day.three.collections.model.Capitalist;
import com.cooksys.ftd.assignments.day.three.collections.model.FatCat;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	private Map<FatCat, Set<Capitalist>> hierarchy;

	public MegaCorp() {
		this.hierarchy = new HashMap<>();
	}

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it
	 * and return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add
	 * it and return false
	 *
	 * @param capitalist
	 *            the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {
		if (capitalist != null && !this.has(capitalist)) {
			if (capitalist instanceof FatCat) {
				FatCat cat = (FatCat) capitalist;
				this.hierarchy.put(cat, new HashSet<>());
			}

			if (capitalist.hasParent()) {
				FatCat parent = capitalist.getParent();
				this.add(parent);
				this.hierarchy.get(parent).add(capitalist);
			}

			return this.has(capitalist);
		}
		return false;
	}

	/**
	 * @param capitalist
	 *            the element to search for
	 * @return true if the element has been added to the hierarchy, false
	 *         otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		if (capitalist != null) {
			if (capitalist instanceof FatCat) {
				FatCat cat = (FatCat) capitalist;
				return hierarchy.containsKey(cat);
			} else if (capitalist.hasParent()) {
				FatCat parent = capitalist.getParent();
				return this.has(parent) && this.hierarchy.get(parent).contains(capitalist);
			}
		}
		return false;
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		Set<Capitalist> result = new HashSet<>();
		for (FatCat cat : this.hierarchy.keySet()) {
			result.add(cat);
			result.addAll(this.hierarchy.get(cat));
		}
		return result;
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no
	 *         parents have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		return new HashSet<>(this.hierarchy.keySet());
	}

	/**
	 * @param fatCat
	 *            the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a
	 *         direct parent, or an empty set if the parent is not present in
	 *         the hierarchy or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {
		Set<Capitalist> result = new HashSet<>();
		if (this.has(fatCat)) {
			result.addAll(this.hierarchy.get(fatCat));
		}
		return result;
	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of
	 *         the associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {
		return new HashMap<>(this.hierarchy);
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the
	 *         given element has no parent or if its parent is not in the
	 *         hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {
		FatCat parent = capitalist.getParent();
		List<FatCat> result = new LinkedList<>();
		while (parent != null) {
			result.add(parent);
			parent = parent.getParent();
		}
		return result;
	}
}
