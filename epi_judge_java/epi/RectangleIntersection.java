package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rect {
    int x, y, width, height;

    public Rect(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rect rectangle = (Rect)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }
  @EpiTest(testDataFile = "rectangle_intersection.tsv")
  public static Rect intersectRectangle(Rect r1, Rect r2) {
    int r1x2=r1.x+r1.width;
    int r1y2=r1.y+r1.height;
    int r2x2=r2.x+r2.width;
    int r2y2=r2.y+r2.height;
    if(r1x2<r2.x||r1y2<r2.y||r1.x>r2x2||r1.y>r2y2)return new Rect(0,0,-1,-1);
    int interX=Math.max(r1.x,r2.x);
    int interY=Math.max(r1.y,r2.y);
    int interHeight=Math.min(r1y2-interY,r2y2-interY);
    int interWidth=Math.min(r1x2,r2x2)-interX;

    return new Rect(interX, interY, interWidth, interHeight);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RectangleIntersection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
