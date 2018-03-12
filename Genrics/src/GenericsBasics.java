import javax.swing.plaf.synth.SynthTextAreaUI;

/**
 * Created by connorsampson on 2/23/18.
 */
public class GenericsBasics {
    public static void main(String[] args) {
        Integer number = new Integer(14);
        String name = "Connor Sampson";
        Box<String> mybox = new Box<>();
        mybox.put(name);
        Box<Integer> secondBox = new Box<>();
        secondBox.put(number);

        String stringItem = mybox.getItem();

        System.out.println("My box contains: " + mybox.getItem());
        System.out.println("The size of contents is: " + stringItem.length());
        System.out.println("secondBox contains: " + secondBox.getItem());


    }
}

class Box<Type> {
    private Object the_item;
    public void put( Type item ) {
        the_item = item;
    }

    public Type getItem() {
        return (Type)the_item;
    }
}
