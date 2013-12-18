package com.ndc888.resp;

import java.util.Map;

public class MapResponse<K, V> extends ActionResponse {
	private Map<K, V> records;
	private int totalCount;
	public Map<K, V> getRecords() {
		return records;
	}
	public void setRecords(Map<K, V> records) {
		this.records = records;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


}
