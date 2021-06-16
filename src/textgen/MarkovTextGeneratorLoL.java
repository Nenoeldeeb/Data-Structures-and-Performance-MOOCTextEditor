package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL (Random generator) {
		wordList = new LinkedList<ListNode> ();
		starter = "";
		rnGenerator = generator;
	}

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 *
	 * @param args
	 */
	public static void main (String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL (new Random (42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println (textString);
		gen.train (textString);
		System.out.println (gen);
		System.out.println (gen.generateText (20));
		String textString2 = "You say yes, I say no, " +
				"You say stop, and I say go, go, go, " +
				"Oh no. You say goodbye and I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello. " +
				"I say high, you say low, " +
				"You say why, and I say I don't know. " +
				"Oh no. " +
				"You say goodbye and I say hello, hello, hello. " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello. " +
				"Why, why, why, why, why, why, " +
				"Do you say goodbye. " +
				"Oh no. " +
				"You say goodbye and I say hello, hello, hello. " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello. " +
				"You say yes, I say no, " +
				"You say stop and I say go, go, go. " +
				"Oh, oh no. " +
				"You say goodbye and I say hello, hello, hello. " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println (textString2);
		gen.retrain (textString2);
		System.out.println (gen);
		System.out.println (gen.generateText (20));
	}

	/**
	 * Train the generator by adding the sourceText
	 */
	@Override
	public void train (String sourceText) {
		String[] words = sourceText.split (" +|\n+");
		if (words.length <= 1) return;
		starter = words[0];
		String prevWord = starter;
		ListNode word = null;
		for (int i = 1; i < words.length; i++) {
			for (ListNode l : wordList) {
				if (l.getWord ().equals (prevWord)) {
					word = l;
					break;
				}
			}
			if (word != null) {
				word.addNextWord (words[i]);
			} else {
				word = new ListNode (prevWord);
				word.addNextWord (words[i]);
				wordList.add (word);
			}
			prevWord = words[i];
			word = null;
		}
		for (ListNode l : wordList) {
			if (l.getWord ().equals (prevWord)) {
				word = l;
				break;
			}
		}
		if (word != null) {
			word.addNextWord (starter);
		} else {
			word = new ListNode (prevWord);
			word.addNextWord (starter);
			wordList.add (word);
		}
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText (int numWords) {
		if (wordList.size () > 0 && numWords > 0) {
			String currWord = starter;
			StringBuilder output = new StringBuilder (currWord);
			int numWordsCounter = 1;
			while (numWordsCounter < numWords) {
				ListNode word = null;
				for (ListNode l : wordList) {
					if (l.getWord ().equals (currWord)) {
						word = l;
						break;
					}
				}
				if (word != null) {
					String randomWord = word.getRandomNextWord (rnGenerator);
					output.append (" ").append (randomWord).append (" ");
					currWord = randomWord;
					numWordsCounter++;
				}
			}
			return output.toString ().trim ();
		}
		return "";
	}

	// Can be helpful for debugging
	@Override
	public String toString () {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString ();
		}
		return toReturn;
	}

	/**
	 * Retrain the generator from scratch on the source text
	 */
	@Override
	public void retrain (String sourceText) {
		wordList.clear ();
		this.train (sourceText);
	}

}

/**
 * Links a word to the next words in the list
 * You should use this class in your implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode (String word) {
		this.word = word;
		nextWords = new LinkedList<String> ();
	}

	public String getWord () {
		return word;
	}

	public void addNextWord (String nextWord) {
		nextWords.add (nextWord);
	}

	public String getRandomNextWord (Random generator) {
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		int index = generator.nextInt (nextWords.size ());
		return nextWords.get (index);
	}

	public String toString () {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}


