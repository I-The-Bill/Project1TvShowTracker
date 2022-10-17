package TvTracker;

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
		this.setId(inID);
		this.setName(inName);
		this.setEc(eC);
	}

	public int getId() {
		return id;
	}

	private void setId(int inID) 
    {
        if (inID >= 0)
        {  
            this.id = inID; 
        }
        else
        {   
            this.id = -1; 
            System.out.println("There has been an error making setting the show ID");
        }
	}

	public String getName() {
		return this.name;
	}

	private void setName(String inName) {
		this.name = inName;
	}

	public int getEc() {
		return this.episodeCount;
	}

	private void setEc(int eC) {
		if (eC >= 0)
        {  
            this.episodeCount = eC; 
        }
        else
        {   
            this.episodeCount = -1; 
            System.out.println("There has been an error making setting the epside Count");
        }
	}

	@Override
	public String toString() 
    {
		return ("Show: " + this.name +"\t\tShow Id: "+ this.id + "\t\tEpisode Count: "+ episodeCount);
    }

}