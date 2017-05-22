package com.raymondbl.essaychecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter
{

    private int count;
    private ArrayList<String> checkedInput = new ArrayList<>();
    private ArrayList<String> uncheckedInput = new ArrayList<>();

    private static final HashMap<String, String> CONTRACTIONS = new HashMap<>();
    /*Uses three unicode variants of apostrophes */

    public void initContractions() {
        CONTRACTIONS.put("aren['‘’]t", "are not");
        CONTRACTIONS.put("can['‘’]t", "cannot");
        CONTRACTIONS.put("couldn['‘’]t", "could not");
        CONTRACTIONS.put("didn['‘’]t", "did not");
        CONTRACTIONS.put("doesn['‘’]t", "does not");
        CONTRACTIONS.put("don['‘’]t", "did not");
        CONTRACTIONS.put("hadn['‘’]t", "had not");
        CONTRACTIONS.put("hasn['‘’]t", "has not");
        CONTRACTIONS.put("haven['‘’]t", "have not");
        CONTRACTIONS.put("he['‘’]d", "he would");
        CONTRACTIONS.put("he['‘’]ll", "he will");
        CONTRACTIONS.put("he['‘’]s", "he is");
        CONTRACTIONS.put("I['‘’]d", "I would");
        CONTRACTIONS.put("I['‘’]ll", "I will");
        CONTRACTIONS.put("I['‘’]m", "I am");
        CONTRACTIONS.put("I['‘’]ve", "I have");
        CONTRACTIONS.put("isn['‘’]t", "is not");
        CONTRACTIONS.put("it['‘’]s", "it is");
        CONTRACTIONS.put("let['‘’]s", "let us");
        CONTRACTIONS.put("mightn['‘’]t", "might not");
        CONTRACTIONS.put("mustn['‘’]t", "must not");
        CONTRACTIONS.put("shan['‘’]t", "should not");
        CONTRACTIONS.put("she['‘’]d", "she would");
        CONTRACTIONS.put("she['‘’]ll", "she will");
        CONTRACTIONS.put("she['‘’]s", "she is");
        CONTRACTIONS.put("shouldn['‘’]t", "should not");
        CONTRACTIONS.put("that['‘’]s", "that is");
        CONTRACTIONS.put("there['‘’]s", "there is");
        CONTRACTIONS.put("they['‘’]d", "they would");
        CONTRACTIONS.put("they['‘’]ll", "they will");
        CONTRACTIONS.put("they['‘’]re", "they are");
        CONTRACTIONS.put("they['‘’]ve", "they have");
        CONTRACTIONS.put("wasn['‘’]t", "was not");
        CONTRACTIONS.put("we['‘’]d", "we would");
        CONTRACTIONS.put("we['‘’]re", "we are");
        CONTRACTIONS.put("we['‘’]ve", "we have");
        CONTRACTIONS.put("were['‘’]nt", "were not");
        CONTRACTIONS.put("what['‘’]ll", "what will");
        CONTRACTIONS.put("what['‘’]re", "what are");
        CONTRACTIONS.put("what['‘’]s", "what is");
        CONTRACTIONS.put("what['‘’]ve", "what have");
        CONTRACTIONS.put("where['‘’]s", "where is");
        CONTRACTIONS.put("who['‘’]d", "who would");
        CONTRACTIONS.put("who['‘’]ll", "who will");
        CONTRACTIONS.put("who['‘’]re", "who are");
        CONTRACTIONS.put("who['‘’]s", "who is");
        CONTRACTIONS.put("who['‘’]ve", "who have");
        CONTRACTIONS.put("won['‘’]t", "will not");
        CONTRACTIONS.put("wouldn['‘’]t", "would not");
        CONTRACTIONS.put("you['‘’]d", "you would");
        CONTRACTIONS.put("you['‘’]re", "you are");
        CONTRACTIONS.put("you['‘’]ve", "you have");
        CONTRACTIONS.put("Aren['‘’]t", "Are not");
        CONTRACTIONS.put("Can['‘’]t", "Cannot");
        CONTRACTIONS.put("Wouldn['‘’]t", "Could not");
        CONTRACTIONS.put("Didn['‘’]t", "Did not");
        CONTRACTIONS.put("Doesn['‘’]t", "Does not");
        CONTRACTIONS.put("Don['‘’]t", "Did not");
        CONTRACTIONS.put("Hadn['‘’]t", "Had not");
        CONTRACTIONS.put("Hasn['‘’]t", "Has not");
        CONTRACTIONS.put("Haven['‘’]t", "Have not");
        CONTRACTIONS.put("He['‘’]d", "He would");
        CONTRACTIONS.put("He['‘’]ll", "He will");
        CONTRACTIONS.put("He['‘’]s", "He is");
        CONTRACTIONS.put("Isn['‘’]t", "Is not");
        CONTRACTIONS.put("It['‘’]s", "It is");
        CONTRACTIONS.put("Let['‘’]s", "Let us");
        CONTRACTIONS.put("Mightn['‘’]t", "Might not");
        CONTRACTIONS.put("Mustn['‘’]t", "Must not");
        CONTRACTIONS.put("Shan['‘’]t", "Should not");
        CONTRACTIONS.put("She['‘’]d", "She would");
        CONTRACTIONS.put("She['‘’]ll", "She will");
        CONTRACTIONS.put("She['‘’]s", "She is");
        CONTRACTIONS.put("Shouldn['‘’]t", "Should not");
        CONTRACTIONS.put("That['‘’]s", "That is");
        CONTRACTIONS.put("There['‘’]s", "There is");
        CONTRACTIONS.put("They['‘’]d", "They would");
        CONTRACTIONS.put("They['‘’]ll", "They will");
        CONTRACTIONS.put("They['‘’]re", "They are");
        CONTRACTIONS.put("They['‘’]ve", "They have");
        CONTRACTIONS.put("Wasn['‘’]t", "Was not");
        CONTRACTIONS.put("We['‘’]d", "We would");
        CONTRACTIONS.put("We['‘’]re", "We are");
        CONTRACTIONS.put("We['‘’]ve", "We have");
        CONTRACTIONS.put("Were['‘’]nt", "Were not");
        CONTRACTIONS.put("What['‘’]ll", "What will");
        CONTRACTIONS.put("What['‘’]re", "What are");
        CONTRACTIONS.put("What['‘’]s", "What is");
        CONTRACTIONS.put("What['‘’]ve", "What have");
        CONTRACTIONS.put("Where['‘’]s", "Where is");
        CONTRACTIONS.put("Who['‘’]d", "Who would");
        CONTRACTIONS.put("Who['‘’]ll", "Who will");
        CONTRACTIONS.put("Who['‘’]re", "Who are");
        CONTRACTIONS.put("Who['‘’]s", "Who is");
        CONTRACTIONS.put("Who['‘’]ve", "Who have");
        CONTRACTIONS.put("Won['‘’]t", "Will not");
        CONTRACTIONS.put("Wouldn['‘’]t", "Would not");
        CONTRACTIONS.put("You['‘’]d", "You would");
        CONTRACTIONS.put("You['‘’]re", "You are");
        CONTRACTIONS.put("You['‘’]ve", "You have");
    }

    private static final String[] FIRST = {"I", "me", "my", "mine", "myself", "we", "us", "our",
            "ours", "ourselves", "Me", "My", "Mine", "Myself", "We", "Us", "Our",
            "Ours", "Ourselves"};
    private static final String[] SECOND = {"you", "your", "yours", "yourself", "yourselves",
            "You", "Your", "Yours", "Yourself", "Yourselves"};
    private static Pattern QUOTES_PATTERN = Pattern.compile("[\"“”]");

    public Highlighter() {
        initContractions();
    }

    /**
     * Highlights items in the HTMLEditor string and returns the new string
     * @return string with HTML color formatting
     */
    public String check(String input, boolean checkContractions, boolean checkQuotes,
                        boolean replaceContr, boolean includeFirsts, boolean includeSeconds) {
        input = removeRed(input);
        if(!checkQuotes) {
            split(input);
        } else checkedInput.add(input);
        System.out.println("After split: " + checkedInput.toString());
        if(includeFirsts) {
            highlight(FIRST);
        }
        if(includeSeconds) {
            highlight(SECOND);
        }
        if(checkContractions) {
            highlight(CONTRACTIONS, replaceContr);
        }
        System.out.println("After highlight: " + checkedInput.toString());
        String output = putTogether();
        setCount(output);
        System.out.println("After puttogether: " + output);
        return output;
    }

    /**
     * Erases any HTML formatting that highlights text with a red color.
     */
    private String removeRed(String input) {
        Matcher redMatcher = Pattern.compile("(<font color=(\\W)red(\\W)>)|(</font>)").matcher(input);
        String output = redMatcher.replaceAll("");
        System.out.println("removeRed: " + output);
        return output;
    }

    /**
     * Splits the input into two ArrayLists: checkedInput, which will be processed,
     * and uncheckedInput, which are ignored until the two are assembled together.
     */
    private void split(String input) {
        checkedInput = new ArrayList<>();
        uncheckedInput = new ArrayList<>();
        String[] tempArray = QUOTES_PATTERN.split(input);
        for(int i = 0; i <= tempArray.length - 1; i++) {
            if(i % 2 == 0) {
                checkedInput.add(tempArray[i]);
            } else uncheckedInput.add("\"" + tempArray[i] + "\"");
        }
    }

    /**
     * Items defined in the parameter are matched against checkedInput.
     * @param array for contractions or pronouns
     */
    private void highlight(String[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int k = 0; k < checkedInput.size(); k++) {
                replace(array[i], k, false);
            }
        }
    }

    private void highlight(Map<String, String> map, boolean replaceContr) {
        map.forEach((String key, String value) -> {
            for(int k = 0; k < checkedInput.size(); k++) {
                replace(key, k, replaceContr);
            }
        });
    }
    /**
     * @param bool determines whether or not to replace the contraction
     * with its extended version.
     */
    private void replace(String toMatch, int index, boolean bool) {
        Pattern inputPattern = Pattern.compile("([^\\w\'’]|\\s)" + "(" + toMatch + ")" + "([^\\w\'’]|\\s)");
        String tempInput = checkedInput.get(index);
        Matcher inputMatcher = inputPattern.matcher(tempInput);
        if(bool) {
            String replacement = CONTRACTIONS.get(toMatch);
            checkedInput.set(index, inputMatcher.replaceAll("$1<font color=\"red\">" + replacement + "</font>$3"));
        } else checkedInput.set(index, inputMatcher.replaceAll("$1<font color=\"red\">$2</font>$3"));
    }

    /**
     * Assembles checkedInput and uncheckedInput into output.
     */
    private String putTogether() {
        StringBuilder builder = new StringBuilder();
        if(uncheckedInput.size() == 0) {
            for(String s : checkedInput)
                builder.append(s);
        }
        else {
            assert checkedInput.size() == (uncheckedInput.size() + 1);
            for(int i = 0; i < uncheckedInput.size(); i++) {
                builder.append(checkedInput.get(i));
                builder.append(uncheckedInput.get(i));
            }
            builder.append(checkedInput.get(checkedInput.size() - 1));
        }
        return builder.toString();
    }

    /**
     * Makes the count, once the output is put together and ready to be printed.
     */
    private int setCount(String string) {
        count = 0;
        Matcher redMatcher = Pattern.compile("<font color=\"red\">").matcher(string);
        while(redMatcher.find())
            count++;
        return count;
    }

    public int getCount() {
        return count;
    }

    /**
     * Removes highlighting in response to a click on the "Un-highlight" button on the GUI.
     * @return the input without any highlighting.
     */
    public String unHighlight(String input) {
        return removeRed(input);
    }
}
