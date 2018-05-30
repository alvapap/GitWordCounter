package counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

import org.kohsuke.github.GHPullRequestFileDetail;

import counter.requesttype.PullRequestType;

/**
 * Counts the words of different types of requests.
 * @author Alex
 *
 */
public class WordCounter implements CounterRequestType{
	
	@Override
	public long pullRequestCounter(PullRequestType requestType, String fileType) {
		long countWords = 0;
		Map<String, String> fileShaMap = requestType.getPullRequestDetails().stream()
				.filter(fileDetail -> fileDetail.getFilename().endsWith(fileType))
				.collect(Collectors.toMap(GHPullRequestFileDetail::getFilename, GHPullRequestFileDetail::getSha,
						(oldVal, newVal) -> newVal));

		for (Map.Entry<String, String> entry : fileShaMap.entrySet()) {
			try (InputStream inputStream = requestType.getRepository().getBlob(entry.getValue()).read();
					BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));) {

				countWords = coutner(countWords, br);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return countWords;
	}

	protected long coutner(long countWords, BufferedReader br) throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			if (!line.isEmpty()) {
				countWords += line.split(" ").length;
			}
		}
		return countWords;
	}
}
