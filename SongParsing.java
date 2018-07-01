import java.util.*;
import java.io.*;
import com.google.gson.Gson;

// Class that takes in a list of Rolling Stones' Top 100 Songs of the Century
// and makes objects out of each song 
public class SongParsing {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("top100.txt"));
		ArrayList<Song> songList = new ArrayList<Song>();
		Map<String, Integer> uniqueArtists = new TreeMap<String, Integer>();
		PrintStream output = new PrintStream(new File("SongDetails.json"));
		parseData(input, songList, uniqueArtists, output);
	}

	// Given a map, sorts the map in order of key values and
	// returns a new sorted map
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(Map<K, V> map) {
    	Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K key, K key2) {
				int compareKeys = map.get(key2).compareTo(map.get(key));
				if (compareKeys == 0) {
					return 1;
				} else {
					return compareKeys;
				}
			}
		};
		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
    }

    // Given a Scanner with lines of songs in the format "{ranking}. {artist} - {song title}"
    // or "{ranking}. {artist} feat. {featuring artist} - {song title}, an ArrayList of Song objects,
    // and a map of how many songs a particular artist has in the top 100, creates Song objects for
    // each line of input, puts that Song into songList, and updates the artist's entry in uniqueArtists
    public static void parseData(Scanner input, ArrayList<Song> songList, Map<String, Integer> uniqueArtists, PrintStream output) {
    	while (input.hasNextLine()) {
			String line = input.nextLine();
			int ranking = Integer.parseInt(line.substring(0, line.indexOf(".")));
			String artist = line.substring(line.indexOf(".") + 2, line.indexOf(" - "));
			String title = line.substring(line.indexOf("'") + 1, line.length() - 1);
			Song anotherSong;
			if (artist.contains("feat.")) {
				String featuredArtist = artist.substring(artist.indexOf("feat.")  + 6);
				artist = artist.substring(0, artist.indexOf("feat.") - 1);
				anotherSong = new Song(title, artist, true, featuredArtist, ranking);
			} else {
				anotherSong = new Song(title, artist, ranking);
			}
			songList.add(anotherSong);
			Gson g = new Gson();
			output.println(g.toJson(anotherSong));
			if (uniqueArtists.containsKey(artist)) {
				uniqueArtists.put(artist, uniqueArtists.get(artist) + 1);
			} else {
				uniqueArtists.put(artist, 1);
			}
		}
    }
}