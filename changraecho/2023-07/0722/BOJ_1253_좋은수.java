import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ_1253_좋은수 {
    static int left;
    static int right;
    static int N;
    static int[] nums;

    static int ans = 0;
    static Set<Integer> ansSet = new HashSet<Integer>();

    static boolean isGood(int index) {
        if (ansSet.contains(nums[index])) {
            return true;
        }

        int n = nums[index];

        left = 0;
        right = N - 1;

        while (left < right) {
            if (left == index) {
                left++;
                continue;
            } else if (right == index) {
                right--;
                continue;
            }

            int sum = nums[left] + nums[right];

            if (sum == n) {
                ansSet.add(n);
                return true;

            } else if (sum < n) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        for (int i = 0; i < N; i++) {
            if (isGood(i)) {

                ans++;
            }
        }

        System.out.println(ans);
    }
}