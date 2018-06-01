package counter.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHCommit.File;

import counter.requesttype.CommitRequest;
import counter.requesttype.PullRequestType;

public abstract class WordCounter {

	/**
	 * Counts the words of a pull request for the given file type. The
	 * implementation should be done in {@link PullRequestCounter}
	 * 
	 * @param requestType
	 *            The type of request in this case it is a {@link PullRequestType}
	 * @param fileType
	 *            The extension of the type of the words to be counted.
	 * @return The number of words
	 */
	public long pullRequestCounter(PullRequestType requestType, String fileType) {
		return 0;
	};

	/**
	 * To count the words of local files
	 * @param file	The file
	 * @param fileType	The fileExtension
	 * @return	The number of words.
	 */
	public long fileRequestCounter(String file, String fileType) {
		return 0;
	}

	/**
	 * Counts the words of commits for the given file extension. The implementation
	 * should be done in {@link CommitCounter}
	 * 
	 * @param commitRequest	The type of request in this case it is a {@link CommitRequest}
	 * @param fileExtension	The extention of the type of the words to be counted.
	 * @return	The number of words.
	 */
	public long commitCounter(CommitRequest commitRequest, String fileExtension) {
		return 0;
	}

	protected long coutner(BufferedReader br) throws IOException {
		String line;
		long countWords = 0;
		while ((line = br.readLine()) != null) {
			if (!line.isEmpty()) {
				countWords += line.split(" ").length;
			}
		}
		return countWords;
	}

}
