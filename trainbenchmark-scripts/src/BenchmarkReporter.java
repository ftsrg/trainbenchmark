import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;

import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackMessage;

public class BenchmarkReporter {
	static Slack slack;

	static {
		String url = "https://hooks.slack.com/services/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";
		slack = new Slack(url).icon(":doomhappy:").sendToChannel("benchmark-notifs").displayName("Benchmark Reporter");
	}

	public static void reportReady() throws SocketException, IOException {
		slack.push(new SlackMessage("Benchmark Ready: " + getURL()));
	}

	static String getURL() throws SocketException {
		return "http://" + getIpAddress() + ":8080/diagrams";
	}

	static String getIpAddress(){
		String url = "http://bot.whatismyipaddress.com";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))){
			return reader.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
	