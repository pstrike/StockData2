package model;

public class Stock3Id {
	private int dbId;
	private String id;
	private String name;
	private String market;
	private String category;
	private String type;
	
	public Stock3Id()
	{
		dbId=0;
		id="";
		name="";
		market="";
		category="";
		type="";
	}
	
	public int getDbId() {
		return dbId;
	}
	public void setDbId(int dbId) {
		this.dbId = dbId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public boolean equals(Object obj) {
		 return this.id.equals(((Stock3Id)obj).id); 
	}
	@Override
	public String toString() {
		return id+"-"+name;
	}
}
