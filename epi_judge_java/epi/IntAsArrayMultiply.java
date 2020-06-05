package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));
        List<Integer> res = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
        for (int i = num1.size() - 1; i >= 0; i--)
            for (int j = num2.size() - 1; j >= 0; j--) {
                res.set(i + j + 1, res.get(i + j + 1) + num1.get(i) * num2.get(j));
                res.set(i + j, res.get(i + j) + res.get(i + j + 1) / 10);
                res.set(i + j + 1, res.get(i + j + 1) % 10);
            }
        int firstIndex = 0;
        while (firstIndex < res.size() && res.get(firstIndex) == 0) firstIndex++;
        res = res.subList(firstIndex, res.size());
        if (res.size() == 0) return List.of(0);
        res.set(0, res.get(0) * sign);
        return res;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
