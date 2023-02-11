
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.List;
public class Scrabble {
    private final String scrabbleWord;
    private List<Character> sDoubleLetters = new ArrayList<>();
    private List<Character> sTripleLetters = new ArrayList<>();
    private int wordMultiplier = 1;


    private final Map<String, Integer> letterValues = new HashMap<String, Integer>(){{
        put("A", 1);
        put("E", 1);
        put("I", 1);
        put("O", 1);
        put("U", 1);
        put("L", 1);
        put("N", 1);
        put("R", 1);
        put("S", 1);
        put("T", 1);
        put("D", 2);
        put("G", 2);
        put("B", 3);
        put("C", 3);
        put("M", 3);
        put("P", 3);
        put("F", 4);
        put("H", 4);
        put("V", 4);
        put("W", 4);
        put("Y", 4);
        put("K", 5);
        put("J", 8);
        put("X", 8);
        put("Q", 10);
        put("Z", 10);
    }};


    public Scrabble(String word){
        //setting scrabble word
        scrabbleWord = word;
    }
    public Scrabble(String word, Character[] doubleLetters, Character[] tripleLetters, boolean doubleWord, boolean tripleWord){
        //setting scrabble word
        scrabbleWord = word;
        //setting double/triple letters/words
        sDoubleLetters = new ArrayList<>(Arrays.asList(doubleLetters));
        sTripleLetters = new ArrayList<>(Arrays.asList(tripleLetters));
        if (doubleWord) wordMultiplier = 2;
        if (tripleWord) wordMultiplier = 3;
    }

    public int letterScore(char letter){
        if (letterValues.containsKey(String.valueOf(letter))){
            if (sDoubleLetters.contains(letter)){
                sDoubleLetters.remove(sDoubleLetters.indexOf(letter));
                return letterValues.get(String.valueOf(letter)) * 2;
            }
            else if(sTripleLetters.contains(letter)){
                sTripleLetters.remove(sDoubleLetters.indexOf(letter));
                return letterValues.get(String.valueOf(letter)) * 3;
            }
            else{
                return letterValues.get(String.valueOf(letter));
            }
        }
        else return 0;
    }

    public int score(){
        int score = 0;

        if (scrabbleWord == null){
            return 0;
        }

        char[] letters = scrabbleWord.toUpperCase().toCharArray();
        for (char letter : letters){
            score+=letterScore(letter);
        }

        return score * wordMultiplier;
    }

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble("");
        System.out.println(scrabble.score());   // => 0

        scrabble = new Scrabble(null);
        System.out.println(scrabble.score());   // => 0

        scrabble = new Scrabble("a");
        System.out.println(scrabble.score()); // => 1

        scrabble = new Scrabble("f");
        System.out.println(scrabble.score()); // => 4

        scrabble = new Scrabble("street");
        System.out.println(scrabble.score()); // =>, 6

        scrabble = new Scrabble("quirky");
        System.out.println(scrabble.score()); // => 22

        scrabble = new Scrabble("OXYPHENBUTAZONE");
        System.out.println(scrabble.score()); // => 41
    }


}
