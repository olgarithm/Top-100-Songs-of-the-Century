import java.util.*;
import java.io.*;

// Simple class to reverse the contents of a text file
// Used to reverse a top 100 from "100. --- 1." to "1. --- 100."
public class ReverseText {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("top100reverse.txt"));
		ArrayList<String> songs = new ArrayList<String>();
		while (input.hasNextLine()) {
			songs.add(input.nextLine());
		}
		PrintStream output = new PrintStream(new File("top100.txt"));
		for (int i = songs.size() - 1; i >= 0; i--) {
			output.println(songs.get(i));
		}
	}
}