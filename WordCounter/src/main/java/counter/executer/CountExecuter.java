package counter.executer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestFileDetail;
import org.kohsuke.github.GHPullRequestQueryBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedIterable;

import counter.requesttype.CommitRequest;
import counter.requesttype.PullRequestType;
import counter.types.PullRequestCounter;
import counter.types.WordCounter;

/**
 * Executes the {@link PullRequestCounter} to count the words of different types of files and different types of requests
 * 
 * @author Alex
 *
 */
public class CountExecuter {

	private GHRepository repository;
	private String fileExtension;
	private WordCounter wordCounter;

	public CountExecuter(GHRepository repository, String fileExtension, WordCounter wordCounterType) {
		this.repository = repository;
		this.fileExtension = fileExtension;
		this.wordCounter = wordCounterType;
	}

	public GHRepository getRepository() {
		return repository;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Count the words of the requested file extension with request type {@link PullRequestType}
	 * @param lastPulls To count only the last nth pull/s. 0 if all should be counted.
	 * @return the number of words found.
	 */
	public long countPullRequests(int lastPulls) {

		GHPullRequestQueryBuilder pullRequest = repository.queryPullRequests();
		PagedIterable<GHPullRequest> pullList = pullRequest.list();
		long pullListSize = pullList.asList().stream().count();
		long skipElements = lastPulls == 0 ? 0 : pullListSize - lastPulls;
		List<GHPullRequestFileDetail> pullRequestDetails = pullList.asList().stream().skip(skipElements)
				.flatMap(pull -> pull.listFiles().asList().stream()).collect(Collectors.toList());
		return wordCounter.pullRequestCounter(new PullRequestType(pullRequestDetails, repository), fileExtension);
	}

	public long countLocalFileRequest(String file) {
		return wordCounter.fileRequestCounter(file, fileExtension);
	}
	
	/**
	 * Count the words of the requested file extension with request type {@link CommitRequest}
	 * @param lastCommits To count only the last nth commit/s. 0 if all should be counted.
	 * @return the number of words found.
	 */
	public long countCommitWords(int lastCommits) {
		List<GHCommit> commits = repository.listCommits().asList();
		long pullListSize = commits.stream().count();
		long skipElements = lastCommits == 0 ? 0 : pullListSize - lastCommits;
		List<File> listCommitFiles = commits.stream().skip(skipElements).flatMap(commit -> {
			try {
				return commit.getFiles().stream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		return wordCounter.commitCounter(new CommitRequest(listCommitFiles, repository), fileExtension);
	}
}
