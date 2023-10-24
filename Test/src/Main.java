import java.util.*;

public class Main {
    //two sorted lists
    //list1 = [1,2,4]
    //list2 = [1,3,4]
    public static void main(String[] args) {
        //System.out.println(mergeTwoSortedLists(
               // new ArrayList<>(Arrays.asList(1,2,4)), new ArrayList<>(Arrays.asList(1,3,4))));
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(4);

        Node n2 = new Node(1);
        n2.next = new Node(3);
        n2.next.next = new Node(5);

        Node mergedNode = mergeTwoLinkedLists(n1, n1);
        printLinkedList(mergedNode);
        //System.out.println("MergeNode is" + mergedNode);
        //System.out.println("Hello world!");
    }

    public static void printLinkedList(Node head) {
        Node temp = head;
        while(null != temp) {
            System.out.println(temp.data + "->");
            temp = temp.next;
        }
    }

    public static List<Integer> mergeTwoSortedLists(List<Integer> listOne, List<Integer> listTwo) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(listOne);
        priorityQueue.addAll(listTwo);

        List<Integer> result = new ArrayList<>();
        while(!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }

        return result;

    }

    public static Node mergeTwoLinkedLists(Node n1, Node n2) {
        Node temp = new Node(0);
        Node currentNode = temp;


        Node first = n1;
        Node second = n2;

        while(null != first && null != second) {
            if(first.data < second.data) {
                currentNode.next = first;
                first = first.next;
            } else {
                currentNode.next = second;
                second = second.next;
            }
            currentNode = currentNode.next;
        }

        if(null != first) {
            currentNode.next = first;
        }

        if(null != second) {
            currentNode.next = second;
        }

        return temp.next;
    }
}