package com.api.model;

public class SearchInfo {
	private Integer totalhits;

	public Integer getTotalhits() { return totalhits; }

	public void setTotalhits(Integer totalHits) { this.totalhits = totalHits; }

	@Override
    public boolean equals(Object obj) {
		
		if (obj != null 
				&& this.getClass() == obj.getClass()
				&& this.totalhits == ((SearchInfo)obj).totalhits) {
            return true;
        }
        
        return false;
	}
	
	public String toString()
	{
		return "TotalHits: " + this.totalhits;
	}
	
}
