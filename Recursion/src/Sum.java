/**
 * Created by connorsampson on 2/26/18.
 */

public void static main() {
       Sum sum = new Sum();
       sum.add(5)
}

public class Sum {
    public int add(int value) {
        if (n == 1) {
            return 1;
        } else {
            return add(value-1)+value;
        }
    }
}
