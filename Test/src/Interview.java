import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interview {
    public static void main(String[] args) {
        System.out.println("The sum is " + findSum(new ArrayList<>(Arrays.asList(1,Integer.MAX_VALUE))));
    }

    public static long findSum(List<Integer> input) {
        if(null == input || input.isEmpty()) {
            return -1L;
        }
        return input.stream().mapToLong(Integer::longValue).sum();
    }
}

//list is empty or null
//elements inside the list is null
//return value must be between long threshold
