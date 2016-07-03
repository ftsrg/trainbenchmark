import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BenchmarkReporter {
    static Slack slack;

    static {
        String url = "https://hooks.slack.com/services/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";
        slack = new Slack(url)
                .icon(":doomhappy:")
                .sendToChannel("benchmark-notifs")
                .displayName("Benchmark Reporter");
    }

    public static void reportReady() {
        try {
            slack.push(new SlackMessage("Benchmark Ready: " + getURL()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getURL() {
        try {
            String hostname = InetAddress.getLocalHost().getHostAddress();
            return "http://" + hostname + ":8080/diagrams";
        } catch (UnknownHostException e) {
            return "";
        }
    }
}
