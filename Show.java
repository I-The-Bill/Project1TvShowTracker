public class Show {

	// create attributes first then generate the constructor, getters/setters,
	// and toString() from Source
	private int id;
	private String name;
	private int episodeCount;

    public Show() {
		this.id = -1;
		this.name = "The Test Show";
		this.episodeCount = 9001;
	}

	public Show(int inID, String inName, int eC) {
		this.id = inID;
		this.name = inName;
		this.episodeCount = eC;
	}

	public int getId() {
		return id;
	}

	public void setId(int inID) {
		this.id = inID;
	}

	public String getName() {
		return name;
	}

	public void setName(String inName) {
		this.name = name;
	}

	public int getPhone() {
		return episodeCount;
	}

	public void setPhone(int eC) {
		this.episodeCount = eC;
	}

	@Override
	public String toString() {
		return ("Show: " + this.name +"\tShow Id: " + this.id + "\tEpisode Count: " + episodeCount);
    	}

}