import java.util.*;
import java.io.*;
import com.google.gson.Gson;

public class SongParsing {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("top100.txt"));
		ArrayList<Song> songs = new ArrayList<Song>();
		Map<String, Integer> uniqueArtists = new TreeMap<String, Integer>();
		PrintStream output = new PrintStream(new File("SongDetails.json"));
		parseData(input, songs, uniqueArtists, output);
	}

	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(Map<K, V> map) {
    	Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K k1, K k2) {
				int compareKeys = map.get(k2).compareTo(map.get(k1));
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

    public static void parseData(Scanner input, ArrayList<Song> songs, Map<String, Integer> uniqueArtists, PrintStream output) {
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
			songs.add(anotherSong);
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