package counter.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;

import counter.requesttype.CommitRequest;

public class CommitCounter extends WordCounter {

	@Override
	public long commitCounter(CommitRequest commitRequest, String fileExtension) {
		long resultCounter = 0;
		List<File> filteredFiles = commitRequest.getFileList().stream().filter(file -> file.getFileName().endsWith(fileExtension)).collect(Collectors.toList());
		for (File file : filteredFiles) {
			GHContent content;
			try {
				content = commitRequest.getRepository().getFileContent(file.getFileName());
				InputStream is = content.read();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				resultCounter += this.coutner(br);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultCounter;
	}
}
