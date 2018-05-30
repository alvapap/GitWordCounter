package counter.demo;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import counter.CountExecuter;

public class WordCounterDemo {

	public static void main(String[] args) {
		// pass the repository name as parameter
		String repository = args[0];
		GitHub github;
		GHRepository ghRepository = null;
		try {
			github = GitHub.connect("yourUserName", "yourAuthToken");
			ghRepository = github.getRepository(repository);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CountExecuter wordCounter = new CountExecuter(ghRepository, ".md");
		System.out.println(wordCounter.countPullRequests());
	}

}
