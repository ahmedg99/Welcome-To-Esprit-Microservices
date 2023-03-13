package tn.esprit.springfever.util;

import java.util.regex.Pattern;

/**
 * This class defines constants that are used during the computation of the sentiment scores.
 *
 * @author Animesh Pandey
 */
public final class Constants {
    /**
     * Max allowed question marks in a string.
     * Beyond this value the affect of the Question marks will be considered the same.
     *
     * @see SentimentModifyingTokens#QUESTION_MARK
     */
    public static final int MAX_QUESTION_MARKS = 3;

    /**
     * Window size for preceding trigram.
     */
    public static final int PRECEDING_TRIGRAM_WINDOW = 3;

    /**
     * Window size for preceding bigram.
     */
    public static final int PRECEDING_BIGRAM_WINDOW = 2;

    /**
     * Window size for preceding unigram.
     */
    public static final int PRECEDING_UNIGRAM_WINDOW = 1;

    /**
     * Maximum number for exclamation marks that could be processed.
     */
    public static final int MAX_EXCLAMATION_MARKS = 4;

    /**
     * This is the window size within which processing will be done.
     * This means that we would be dealing only with unigrams, bigrams and
     * trigrams.
     */
    public static final int MAX_GRAM_WINDOW_SIZE = 3;

    /**
     * This alpha approximates the max expected value for a sentiment score.
     */
    public static final float DEFAULT_ALPHA = 15.0F;

    /**
     * This regex checks if a string has only alphabets and no special characters or numbers.
     */
    public static final Pattern NON_NUMERIC_STRING_REGEX = Pattern.compile(".*[a-zA-Z]+.*");

    /**
     * This string defines the prefix for a string that has a URL.
     */
    public static final String HTTP_URL_PREFIX = "http://";

    /**
     * This string defines the prefix for a string that has a URL.
     */
    public static final String HTTPS_URL_PREFIX = "https://";

    /**
     * The separator for a word N-gram.
     */
    public static final String SPACE_SEPARATOR = " ";

    /**
     * Private constructor for utility class.
     */
    private Constants() {

    }
}
