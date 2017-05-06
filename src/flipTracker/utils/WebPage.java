package flipTracker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Used for loading data from the Internet eg. Buy limits
 */
public class WebPage {

    private URL url;
    private ArrayList<String> lines;

    /**
     * Create a new webpage object
     * @param url url of the site to read
     * @throws MalformedURLException if url is insufficient
     */
    public WebPage(String url) throws MalformedURLException {
        if (!url.startsWith("http://")) {
            url = "http://" + url;
            this.url = new URL(url);
        }
        this.url = new URL(url);
    }

    /**
     * Read the lines on the page
     * @throws IOException if url is bad or no connection
     */
    public void load() throws IOException {
        String line;
        this.lines = new ArrayList<>();
        URLConnection c = this.url.openConnection();
        c.setReadTimeout(3000);
        BufferedReader stream = new BufferedReader(new InputStreamReader(c.getInputStream()));
        while ((line = stream.readLine()) != null) {
            this.lines.add(line);
        }
        stream.close();
    }

    /**
     *
     * @return Lines in the webpage (has to be loaded before calling this)
     */
    public ArrayList<String> getLines() {
        return this.lines;
    }
}

