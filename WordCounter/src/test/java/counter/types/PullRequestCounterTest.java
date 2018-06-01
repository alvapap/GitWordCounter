package counter.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import counter.executer.CountExecuter;
import counter.types.PullRequestCounter;

import static org.junit.Assert.*;

public class PullRequestCounterTest {

	@Test
	public void testWordsCounter() {
		CountExecuter executer = new CountExecuter(null, ".txt", new LocalFileCounter());

		long countWords = 0;
		countWords = executer.countLocalFileRequest("/countWords.txt");
		assertEquals(6, countWords);
	}

	@Test
	public void testWordsWithDifferentFileTypeCounter() {
		CountExecuter executer = new CountExecuter(null, ".md", new LocalFileCounter());

		long countWords = 0;
		countWords = executer.countLocalFileRequest("/countWords.txt");
		assertEquals(0, countWords);
	}

	@Test
	public void testMoreWordsCounter() {
		CountExecuter executer = new CountExecuter(null, ".txt", new LocalFileCounter());

		long countWords = 0;
		countWords = executer.countLocalFileRequest("/countMoreWords.txt");
		assertEquals(12, countWords);
	}

}
