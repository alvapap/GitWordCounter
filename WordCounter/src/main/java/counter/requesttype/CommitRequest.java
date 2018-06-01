package counter.requesttype;

import java.util.List;

import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHRepository;

/**
 * Used for commit requests.
 * @author Alex
 *
 */
public class CommitRequest {
	List<File> fileList;
	private GHRepository repository;

	public CommitRequest(List<File> fileList, GHRepository repository) {
		this.fileList = fileList;
		this.repository = repository;
	}

	public List<File> getFileList() {
		return fileList;
	}

	public GHRepository getRepository() {
		return repository;
	}
	
	
}
