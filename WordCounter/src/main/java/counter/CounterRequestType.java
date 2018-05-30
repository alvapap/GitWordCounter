package counter;

import counter.requesttype.PullRequestType;

public interface CounterRequestType {

	/**
	 * Counts the words of a pull request for the given file type
	 * @param requestType	The type of request in this case it is a {@link PullRequestType}
	 * @param fileType	The extension of the type of the words to be counted. 
	 * @return	The number of words
	 */
	public long pullRequestCounter(PullRequestType requestType, String fileType);
}
