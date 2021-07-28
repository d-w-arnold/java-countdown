import java.util.*;
import java.util.stream.Collectors;

/**
 * @author David W. Arnold
 * @version 13/04/2021
 */
public class PossibleWords
{
    final private String[] ENGLISH_DIGRAPHS = {
            "TH", "ER", "ON", "AN", "RE",
            "HE", "IN", "ED", "ND", "HA",
            "AT", "EN", "ES", "OF", "OR",
            "NT", "EA", "TI", "TO", "IT",
            "ST", "IO", "LE", "IS", "OU",
            "AR", "AS", "DE", "RT", "VE"
    };
    final private String[] ENGLISH_TRIGRAPHS = {
            "THE", "AND", "THA", "ENT", "ION",
            "TIO", "FOR", "NDE", "HAS", "NCE",
            "EDT", "TIS", "OFT", "STH", "MEN"
    };
    private final String LETTERS;

    // Key: Word, Value: Num of Digraphs/Trigraph occurrences.
    private Map<String, List<String>> wordsScoreboard;
    // Confirmed final words.
    private List<String> words;

    public PossibleWords(String letters)
    {
        this.LETTERS = letters;
        wordsScoreboard = new HashMap<>();
        words = new ArrayList<>();
    }

    public List<String> getWords()
    {
        return words;
    }

    public String getLETTERS()
    {
        return LETTERS;
    }

    public void getPermutations()
    {
        getPermutations("", LETTERS);
    }

    public void getTopScorers()
    {
        Comparator<List<String>> compByLength = (aList, bList) -> bList.size() - aList.size();
        Map<String, List<String>> result = wordsScoreboard.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(compByLength))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private void getPermutations(String prefix, String str)
    {
        int n = str.length();
        if (n == 0) {
            wordsScoreboard.put(prefix, getScore(prefix));
        } else {
            for (int i = 0; i < n; i++) {
                getPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    private List<String> getScore(String str)
    {
        List<String> value = new ArrayList<>();
        for (String s : ENGLISH_DIGRAPHS) {
            if (str.contains(s)) {
                value.add(s);
            }
        }
        for (String s : ENGLISH_TRIGRAPHS) {
            if (str.contains(s)) {
                value.add(s);
            }
        }
        return value;
    }
}
