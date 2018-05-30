package counter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.GHPullRequestFileDetail;
import org.kohsuke.github.GHPullRequestQueryBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import counter.requesttype.PullRequestType;

/**
 * Executes the {@link WordCounter} to count the words of different types of files and different types of requests
 * 
 * @author Alex
 *
 */
public class CountExecuter {

	private GHRepository repository;
	private String fileExtension;
	private WordCounter wordCounter = new WordCounter();

	public CountExecuter(GHRepository repository, String fileExtension) {
		this.repository = repository;
		this.fileExtension = fileExtension;
	}

	public GHRepository getRepository() {
		return repository;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Count the words of the requested file extension with request type {@link PullRequestType}
	 * 
	 * @return the number of words found.
	 */
	public long countPullRequests() {

		GHPullRequestQueryBuilder pullRequest = repository.queryPullRequests();
		PagedIterable<GHPullRequest> pullList = pullRequest.list();
		List<GHPullRequestFileDetail> commitDetails = pullList.asList().stream()
				.flatMap(pull -> pull.listFiles().asList().stream()).collect(Collectors.toList());
		return wordCounter.pullRequestCounter(new PullRequestType(commitDetails, repository), fileExtension);
	}

}
