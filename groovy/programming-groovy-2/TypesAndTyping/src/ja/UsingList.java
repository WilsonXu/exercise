package ja;

import java.util.ArrayList;

public class UsingList {
    public static void main(String[] args) {
        {
            ArrayList list1 = new ArrayList();

            list1.add("hello");

            int size = ((String) (list1.get(0))).length();
            System.out.println(size);
        }

        {
            ArrayList<String> list2 = new ArrayList<>();

            list2.add("better?");

            int size = list2.get(0).length();
            System.out.println(size);
        }

        {
            ArrayList<String> list3 = new ArrayList<>();

            //list3.add(1); // Will result in a compilation error if uncommented

            ArrayList list = list3;
            // May happen during passing parameters in method calls
            list.add(1);
            int size = list3.get(0).length();
            System.out.println(size);
        }
    }
}
