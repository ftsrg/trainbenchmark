import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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

	static String getIpAddress() throws SocketException {
		System.setProperty("java.net.preferIPv4Stack", "true");
		Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
		for (; n.hasMoreElements();) {
			NetworkInterface e = n.nextElement();
			if ("eth0".equals(e.getName())) {
				Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements();) {
					InetAddress addr = a.nextElement();
					return addr.getHostAddress();
				}
			}
		}
		throw new RuntimeException("Could not retrieve IP address");
	}

}
