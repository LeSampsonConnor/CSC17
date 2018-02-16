/**
 * Created by connorsampson on 2/9/18.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DataFiles {

    public static void main(String[] args) throws IOException {
        // Create the variables to hold all the data
        ArrayList<HashMap<String, HashMap<String, String>>> allTweets = loadDataArray("pol_tweets.tsv");
        HashMap<String, String> knownPols = loadDataHash("known_pols.tsv");
        HashMap<String, String> officials = loadDataHash("officials.tsv");
        ArrayList<ArrayList<String>> filteredTweets = new ArrayList<>();


        // Compare all tweets to knownPols
        for (int i = 1; i < allTweets.size(); i++) {
            HashMap<String, HashMap<String, String>> tweet = allTweets.get(i);
            String tweetKey = tweet.keySet().toArray()[0].toString();
            HashMap<String, String> tweetData = tweet.get(tweetKey);
            // Determine if matched tweeter is also in the knownPols list.
            if (knownPols.containsKey(tweetKey)) {
                ArrayList<String> newTweetForFilteredList = new ArrayList<>();

                // Add the values for a matched tweet to a newTweetForFilteredList
                newTweetForFilteredList.add(tweetKey);
                // Determine if matched tweeter is also in the officials list.
                if (officials.containsKey(tweetKey)) {
                    newTweetForFilteredList.add("incumbent");
                } else {
                    newTweetForFilteredList.add("challenger");
                }
                newTweetForFilteredList.add(tweetData.get("date"));
                newTweetForFilteredList.add(tweetData.get("tweet"));

                filteredTweets.add(newTweetForFilteredList);

            }
        }


        // Send the arrayList created from previous forloop as a parameter to the save data function
        savedata(filteredTweets, "pol_tweets_filtered.tsv");

    }

    private static HashMap<String, String> loadDataHash(String filename) throws IOException {
        HashMap<String, String> filedatahash = new HashMap<>();
        BufferedReader inp_file;
        inp_file = new BufferedReader(new FileReader(filename));

        String input_line;
        while ( (input_line = inp_file.readLine()) != null ) {
            String[] fields = input_line.split("\t");
            String values = "";
            // Depending on which file is being sent through the keys and values will be determined differently
            if (filename == "known_pols.tsv") {
                for (int i = 1; i<fields.length; i++) {
                    values+=fields[i];
                }
                filedatahash.put(fields[0], values);
            } else {
                for (int i = 0; i<fields.length; i++) {
                    // No need to add the key into the value
                    if (i!=2) {
                        values+=fields[i];
                    }
                }
                filedatahash.put(fields[2], values);
            }
        }
        inp_file.close();

        return filedatahash;
    }

    private static ArrayList<HashMap<String, HashMap<String, String>>> loadDataArray(String filename) throws IOException {
        // An arrayList of a hashMap of a hashMap was chosen, so the tweet data could be stored with keys
        // This was done to try and replicate the twitter data like it originally comes in the .json format
        ArrayList<HashMap<String, HashMap<String, String>>> filedata = new ArrayList<>();
        BufferedReader inp_file;
        inp_file = new BufferedReader(new FileReader(filename));

        String input_line;

        while ( (input_line = inp_file.readLine()) != null ) {
            HashMap<String, HashMap<String, String>> hashedMap = new HashMap<>();
            String[] fields = input_line.split("\t");
            HashMap<String, String> value = new HashMap<>();

            value.put("date", fields[1]);
            value.put("tweet", fields[2]);

            hashedMap.put(fields[0], value);
            filedata.add(hashedMap);
        }
        inp_file.close();

        return filedata;
    }

    private static void savedata(ArrayList<ArrayList<String>> all_lines, String outputfile) throws IOException {

        PrintWriter out;
        out = new PrintWriter(outputfile);
        out.println("Screenname\tStatus\tDate\tTweet");
        // Loop to save lines to output file
        for(ArrayList<String> line: all_lines) {
            String tweetData = "";
            // loop through each line
            for(int i=0; i<line.size(); i++) {
                if(!(i+1 == line.size())) {
                    tweetData += line.get(i)+"\t";
                } else {
                    // if the last line no need to add a "\t" to the end
                    tweetData += line.get(i);
                }
            }
            out.println(tweetData);
        }
        out.close();
    }
}
