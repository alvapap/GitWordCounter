package counter.demo;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import counter.executer.CountExecuter;
import counter.types.CommitCounter;
import counter.types.PullRequestCounter;

public class WordCounterDemo {

	public static void main(String[] args) {
		// pass the repository name as parameter
		String repository = args[0];
		GitHub github;
		GHRepository ghRepository = null;
		try {
			github = GitHub.connect("alvapap", "d26a17c867ac86e5f72f9c09a1c90e0b06a62230");
			ghRepository = github.getRepository(repository);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CountExecuter wordCounter = new CountExecuter(ghRepository, ".md", new PullRequestCounter());
		System.out.println(wordCounter.countPullRequests(0));
		CountExecuter wordCounter2 = new CountExecuter(ghRepository, ".md", new CommitCounter());
		System.out.println(wordCounter2.countCommitWords(2));
	}

}
