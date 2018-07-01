public class Song {
	private String title;
	private String artist;
	private boolean hasFeature;
	private String featuredArtist;
	private int ranking;

	public Song(String title, String artist, int ranking) {
		this.title = title;
		this.artist = artist;
		this.ranking = ranking;
	}

	public Song(String title, String artist, boolean hasFeature, String featuredArtist, int ranking) {
		this.title = title;
		this.artist = artist;
		this.hasFeature = hasFeature;
		this.featuredArtist = featuredArtist;
		this.ranking = ranking;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public boolean hasFeature() {
		return hasFeature;
	}

	public String getFeature() {
		if (hasFeature) {
			return featuredArtist;
		} else {
			return "NA";
		}
	}

	public int getRanking() {
		return ranking;
	}

	public String toString() {
		return "\"" + title + "\"" + " by " + artist + ", " + "ranked " + ranking;
	}
}