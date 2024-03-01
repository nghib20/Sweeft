import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Sweeft {
    public static void main(String[] args) {
        Sweeft sweeft = new Sweeft();
        testEvaluateExpression(sweeft);
        testNumberOfHappyStrings(sweeft);
        testReverseList(sweeft);
        testFindIntersection(sweeft);
        testLenOfLongSubarr(sweeft);
        testIsValidSequence(sweeft);
    }
    public boolean isValidSequence(int[] array, int[] sequence) {
        int currIndex = 0;
        for(int i=0;i<sequence.length;i++) {
            int currElem = sequence[i];
            boolean found = false;
            for(int j=currIndex;j<array.length;j++) {
                if(array[j]==currElem) {
                    currIndex = j;
                    found = true;
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }

    public int lenOfLongSubarr(int[] array, int k) {
        int[] result = new int[]{0};
        recursiveLongestSum(array, k, 0, 0, result);
        return result[0];
    }

    private void recursiveLongestSum(int[] array, int k, int startIndex, int numElems, int[] result) {
        if (k == 0) {
            if (numElems > result[0]) {
                result[0] = numElems;
            }
        }
        if (startIndex == array.length) {
            return;
        }
        recursiveLongestSum(array, k, startIndex + 1, numElems, result);
        recursiveLongestSum(array, k - array[startIndex], startIndex + 1, numElems + 1, result);
    }

    public int evaluateExpression(String expression) {
        int result = 0;
        int startIndex = 0;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '+' || ch == '-' || i == expression.length() - 1) {
                if(i==0) continue;
                String numberStr = expression.substring(startIndex, i == expression.length() - 1 ? i + 1 : i);

                int number = Integer.parseInt(numberStr);

                if (startIndex == 0) {
                    result = number;
                } else if (expression.charAt(startIndex - 1) == '+') {
                    result += number;
                } else if (expression.charAt(startIndex - 1) == '-') {
                    result -= number;
                }
                startIndex = i + 1;
            }
        }

        return result;
    }
    

    public int numberOfHappyStrings(List<String> strings) {
        int result = 0;
        for(int i=0;i<strings.size();i++) {
            String currWord = strings.get(i);
            if(isHappyString(currWord)) result++;
        }
        return result;
    }

    private boolean isHappyString(String s) {
        for(int i=0;i<s.length()-1;i++) {
            if (s.charAt(i)==s.charAt(i+1))
                return false;
        }
        return true;
    }
    
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
    
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    
        return prev;
    }

    public int[] findIntersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int num : nums1) {
            set1.add(num);
        }
        for(int num : nums2) {
            if(set1.contains(num)) {
                set2.add(num);
            }
        }
        int[] result = new int[set2.size()];   
        int index = 0;
        for (int num : set2) {
            result[index] = num;
            index++;
        }
        return result;
    }

    public static void testEvaluateExpression(Sweeft sweeft) {
        System.out.println("Running evaluateExpression tests\n\n");

        System.out.println("Test1 starting:\n");
        System.out.println("Expected: 22");
        System.out.println("Actual: " + sweeft.evaluateExpression("5+20-8+5"));
        System.out.println("\n\n");

        System.out.println("Test2 starting:\n");
        System.out.println("Expected: 12");
        System.out.println("Actual: " + sweeft.evaluateExpression("-5+20-8+5"));
        System.out.println("\n\n");

        System.out.println("Test3 starting:\n");
        System.out.println("Expected: 0");
        System.out.println("Actual: " + sweeft.evaluateExpression("13+5-19+1"));
        System.out.println("\n\n");

        System.out.println("Test4 starting:\n");
        System.out.println("Expected: -1");
        System.out.println("Actual: " + sweeft.evaluateExpression("7+7-15"));
        System.out.println("\n\n");

        System.out.println("Test5 starting:\n");
        System.out.println("Expected: -40");
        System.out.println("Actual: " + sweeft.evaluateExpression("-20-20"));
        System.out.println("\n\n");
    }

    public static void testNumberOfHappyStrings(Sweeft sweeft) {
        System.out.println("Running numberOfStrings tests\n\n");

        System.out.println("Test1 starting:\n");
        List<String> strings1 =  List.of("abbcc", "abc", "abcabc", "cabcbb");
        System.out.println("Expected: 2");
        System.out.println("Actual: "+sweeft.numberOfHappyStrings(strings1));
        System.out.println("\n\n");

        System.out.println("Test2 starting:\n");
        List<String> strings2 =  List.of("abc", "abc", "abcabc", "cabcbb");
        System.out.println("Expected: 3");
        System.out.println("Actual: "+sweeft.numberOfHappyStrings(strings2));
        System.out.println("\n\n");

        System.out.println("Test3 starting:\n");
        List<String> strings3 =  List.of("abbcc", "abc", "abc", "abc", "abcabcabc");
        System.out.println("Expected: 4");
        System.out.println("Actual: "+sweeft.numberOfHappyStrings(strings3));
        System.out.println("\n\n");

        System.out.println("Test4 starting:\n");
        List<String> strings4 =  List.of("abab", "abc", "abcabc", "a");
        System.out.println("Expected: 4");
        System.out.println("Actual: "+sweeft.numberOfHappyStrings(strings4));
        System.out.println("\n\n");
    }

    public static void testReverseList(Sweeft sweeft) {
        System.out.println("Running reverseList tests\n");

        ListNode node1 = new ListNode();
        node1.value = 1;

        ListNode node2 = new ListNode();
        node2.value = 2;

        ListNode node3 = new ListNode();
        node3.value = 3;

        ListNode node4 = new ListNode();
        node4.value = 4;

        ListNode node5 = new ListNode();
        node5.value = 5;

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        System.out.println("Original:");
        node1.print();
        System.out.println();
        System.out.println("Now reversing:");
        sweeft.reverseList(node1).print();
        System.out.println("\n\n");
    }

    public static void testFindIntersection(Sweeft sweeft) {
        System.out.println("Running findIntersection tests\n");
        
        System.out.println("Test1 starting:\n");
        int[] array1 = {1,2,3,3,4,5};
        int[] array2 = {3,4,4,5,6,7};
        System.out.println("Expected: [3, 4, 5]");
        System.out.println("Actual: " + Arrays.toString(sweeft.findIntersection(array1, array2)));
        System.out.println();
        System.out.println();

        System.out.println("Test2 starting:\n");
        int[] array3 = {6,10,10,12,12,12};
        int[] array4 = {3,4,4,5,6,7};
        System.out.println("Expected: [6]");
        System.out.println("Actual: " + Arrays.toString(sweeft.findIntersection(array3, array4)));
        System.out.println();
        System.out.println();

        System.out.println("Test3 starting:\n");
        int[] array5 = {6,10,10,12,12,12};
        int[] array6 = {7,8,9,9,11,13};
        System.out.println("Expected: []");
        System.out.println("Actual: " + Arrays.toString(sweeft.findIntersection(array5, array6)));
        System.out.println();
        System.out.println();
    }

    public static void testLenOfLongSubarr(Sweeft sweeft) {
        System.out.println("Running lenOfLongSubarr tests\n");

        System.out.println("Test1 starting:\n");
        int[] array = {6,2,2,3,4,1};
        System.out.println();
        System.out.println("Expected: 4");
        System.out.println("Actual: " + sweeft.lenOfLongSubarr(array, 8));
        System.out.println();
        System.out.println();

        System.out.println("Test2 starting:\n");
        int[] array2 = {6,2,2,3,4,1};
        System.out.println();
        System.out.println("Expected: 3");
        System.out.println("Actual: " + sweeft.lenOfLongSubarr(array2, 6));
        System.out.println();
        System.out.println();

        System.out.println("Test3 starting:\n");
        int[] array3 = {6,2,2,3,4,1};
        System.out.println();
        System.out.println("Expected: 3");
        System.out.println("Actual: " + sweeft.lenOfLongSubarr(array3, 5));
        System.out.println();
        System.out.println();
    }

    public static void testIsValidSequence(Sweeft sweeft) {
        System.out.println("Running isValidSequence tests\n");

        System.out.println("Test1 starting:\n");
        int[] array1 = {5,1,22,25,6,-1,8,10};
        int[] array2 = {1,6,-1,10};
        System.out.println();
        System.out.println("Expected: true");
        System.out.println("Actual: " + sweeft.isValidSequence(array1, array2));
        System.out.println();
        System.out.println();

        System.out.println("Test2 starting:\n");
        int[] array3 = {2,-1,1,6,10};
        int[] array4 = {1,6-1,10};
        System.out.println();
        System.out.println("Expected: false");
        System.out.println("Actual: " + sweeft.isValidSequence(array3, array4));
        System.out.println();
        System.out.println();

        System.out.println("Test3 starting:\n");
        int[] array5 = {1,2,3};
        int[] array6 = {1,2,3};
        System.out.println();
        System.out.println("Expected: true");
        System.out.println("Actual: " + sweeft.isValidSequence(array5, array6));
        System.out.println();
        System.out.println();

        System.out.println("Test3 starting:\n");
        int[] array7 = {2,3};
        int[] array8 = {1,2,3};
        System.out.println();
        System.out.println("Expected: false");
        System.out.println("Actual: " + sweeft.isValidSequence(array7, array8));
        System.out.println();
        System.out.println();
    }
}