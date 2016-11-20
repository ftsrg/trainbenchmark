package hu.bme.mit.trainbenchmark.benchmark.runcomponents;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;

import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackMessage;

public class BenchmarkReporter {

	public static void reportReady(final String url) throws SocketException, IOException {
		final Slack slack = new Slack(url).icon(":doomhappy:").sendToChannel("benchmark-notifs").displayName("Benchmark Reporter");
		slack.push(new SlackMessage("Benchmark Ready: " + getURL()));
	}

	static String getURL() throws SocketException {
		return "http://" + getIpAddress() + ":8080/diagrams";
	}

	static String getIpAddress(){
		final String url = "http://bot.whatismyipaddress.com";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))){
			return reader.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
