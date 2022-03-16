public class Word {

    private String[] word;

    public Word(String string) {
        word = string.split("");
    }

    public String[] getLetterList() {
        return word;
    }

    public String toString() {
        String answer = "";
        for(String letter : word) {
            answer += letter;
        }
        return answer;
    }

}
