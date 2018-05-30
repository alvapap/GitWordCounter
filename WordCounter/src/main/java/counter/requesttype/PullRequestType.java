package counter.requesttype;

import java.util.List;

import org.kohsuke.github.GHPullRequestFileDetail;
import org.kohsuke.github.GHRepository;

public class PullRequestType {

	private List<GHPullRequestFileDetail> pullRequestDetails;
	private GHRepository repository;

	public PullRequestType(List<GHPullRequestFileDetail> pullRequestDetails, GHRepository repository) {
		this.pullRequestDetails = pullRequestDetails;
		this.repository = repository;
	}

	public List<GHPullRequestFileDetail> getPullRequestDetails() {
		return pullRequestDetails;
	}

	public GHRepository getRepository() {
		return repository;
	}

}
