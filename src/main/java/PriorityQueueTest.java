import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 4, 3, 2}, new int[]{1, 2, 3, 4}),
            Arguments.of(new int[]{8, 8, 7, 7}, new int[]{7, 7, 8, 8}),
            Arguments.of(new int[]{-4, -3, -2, -1}, new int[]{-4, -3, -2, -1}),
            Arguments.of(new int[]{-1, -7, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, -7, -1}),
            Arguments.of(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Arguments={0}, {1}")
    @MethodSource("stringIntAndListProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test_PQ = new PriorityQueue<>();

        int[] result = new int[random_array.length];

        for (int i : random_array) {
            test_PQ.add(i);
        }

        for (int i = 0; i < random_array.length; ++i) {
            result[i] = test_PQ.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void PriorityQueue_InitialCapacity_IllegalArgumentExceptionTest() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test_PQ = new PriorityQueue<>(Integer.MIN_VALUE);
        });
    }

    @Test
    public void PriorityQueue_Add_NullPointerException_Test() {
        PriorityQueue<Integer> test_PQ = new PriorityQueue<>();

        Exception e = assertThrows(NullPointerException.class, () -> {
            test_PQ.add(null);
        });
    }

    @Test
    public void PriorityQueue_Offer_ClassCastException_Test() {
        PriorityQueue<Object> test_PQ = new PriorityQueue<>();
        test_PQ.offer(1);

        Exception e = assertThrows(ClassCastException.class, () -> {
            test_PQ.offer("test_string");
        });
    }
}