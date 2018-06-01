package counter.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocalFileCounter extends WordCounter {

	@Override
	public long fileRequestCounter(String file, String fileType) {
		long countWordsResult = 0;
		if (file.endsWith(fileType)) {
			InputStream inputStream = this.getClass().getResourceAsStream(file);
			try {
				countWordsResult += this.coutner(new BufferedReader(new InputStreamReader(inputStream)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return countWordsResult;
	}
}
