package counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import counter.WordCounter;

import static org.junit.Assert.*;

public class WordCounterTest {

	@Test
	public void testWordsCounter() {
		InputStream inputStream = this.getClass().getResourceAsStream("/countWords.txt");
		
		WordCounter wordCounter = new WordCounter();
		long countWords = 0;
		try {
			countWords = wordCounter.coutner(countWords, new BufferedReader(new InputStreamReader(inputStream)));
			assertEquals(6, countWords);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMoreWordsCounter() {
		InputStream inputStream = this.getClass().getResourceAsStream("/countMoreWords.txt");
		
		WordCounter wordCounter = new WordCounter();
		long countWords = 0;
		try {
			countWords = wordCounter.coutner(countWords, new BufferedReader(new InputStreamReader(inputStream)));
			assertEquals(12, countWords);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
