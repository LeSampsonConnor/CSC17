/**
 * Created by connorsampson on 2/26/18.
 */

// reverse("java") := "avaj" = 'a'+'v'+'a'+'j'

public class ReverseString {
    public String reverse(String s) {
        if (s.length() <= 1) {
            return s;
        } else {
            return reverse(s.substring(1)) + s.substring(0, 1);
        }
    }
}
