package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    int l=A.size()-1;
    A.set(l,A.get(l)+1);
    int i=l;
    while (A.get(i)==10&&i>0){
      A.set(i,0);
      i--;
      A.set(i,A.get(i)+1);
    }
    if(A.get(0)==10){
      A.set(0,0);
      A.add(0,1);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
