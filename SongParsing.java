import java.util.*;
import java.io.*;

public class SongParsing {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("top100.txt"));
		ArrayList<Song> songs = new ArrayList<Song>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			int ranking = Integer.parseInt(line.substring(0, line.indexOf(".")));
			String artist = line.substring(line.indexOf(".") + 2, line.indexOf(" - "));
			boolean hasFeature = false;
			String featuredArtist = "NA";
			if (artist.contains("feat.")) {
				featuredArtist = artist.substring(artist.indexOf("feat.")  + 6);
				System.out.println(artist);
				artist = artist.substring(0, artist.indexOf("feat.") - 1);
				hasFeature = true;
			}
			String title = line.substring(line.indexOf("'") + 1, line.length() - 1);
			Song anotherSong = new Song(title, artist, hasFeature, featuredArtist, ranking);
			songs.add(anotherSong);
		}
		System.out.println(songs);
	}
}