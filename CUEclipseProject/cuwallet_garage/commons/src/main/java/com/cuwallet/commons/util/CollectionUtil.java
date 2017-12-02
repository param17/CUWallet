package com.cuwallet.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtil {

	public static <T> List<T> subList(List<T> list, int offset, int limit) {
		if (offset >= list.size()) {
			return Collections.emptyList();
		}
		int adjustedSize = Math.min(limit, list.size() - offset);
		return new ArrayList<T>(list.subList(offset, offset + adjustedSize));
	}

	public int getSize(Collection<?> collection) {
		return null != collection ? collection.size() : 0;
	}

	public static <T> Set<T> toSet(T[] arr) {
		Set<T> set = new HashSet<>();
		if (arr != null) {
			for (T elem : arr) {
				set.add(elem);
			}
		}
		return set;
	}

	public static Integer addElements(Collection<Integer> addableCollection) {
		Integer result = null;
		if (addableCollection != null) {
			result = 0;
			for (Integer i : addableCollection) {
				result = i != null ? result + i : result;
			}
		}
		return result;
	}
}
