package other;

import java.util.ArrayList;
import java.util.List;

public class P68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int charCount = 0;
        int wordCount = -1;
        int start = 0;
        for (int i = 0; i < words.length; i++) {
            charCount += words[i].length();
            ++wordCount;
            if ((charCount + wordCount) > maxWidth) {
                result.add(helper(words, start, --i, maxWidth, charCount - words[i + 1].length()));
                charCount = 0;
                wordCount = -1;
                start = i + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < words.length; i++) {
            sb.append(words[i]).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        for (int i = sb.length(); i < maxWidth; i++) {
            sb.append(" ");
        }
        result.add(sb.toString());
        return result;
    }

    private static String helper(String[] words, int start, int end, int maxWidth, int charCount) {
        StringBuilder sb = new StringBuilder();
        if (start == end) {
            sb.append(words[start]);
            for (int i = sb.length(); i < maxWidth; i++)
                sb.append(" ");
            return sb.toString();
        }
        int blankCount = maxWidth - charCount;
        int avg = blankCount / (end - start);
        int rest = blankCount % (end - start);
        for (int i = start; i < end; i++) {
            sb.append(words[i]);
            for (int j = 0; j < avg; j++)
                sb.append(" ");
            if (rest != 0) {
                sb.append(" ");
                --rest;
            }
        }
        sb.append(words[end]);
        return sb.toString();
    }


    public static void main(String[] args) {
        P68 p68 = new P68();
//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        List<String> result = p68.fullJustify(words, 16);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }


}
