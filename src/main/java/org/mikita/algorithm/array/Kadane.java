package org.mikita.algorithm.array;

public class Kadane {

    public int findMaxSubArraySum(int[] array) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int num : array) {
            currentSum += num;
            maxSum = Math.max(currentSum, maxSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        return maxSum;
    }
}
