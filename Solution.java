package JavaJava.CenteredHexagonalNumber.gitHub.two;

import java.util.Scanner;

public class Challenge {

  public static int total_centeredHexagonLattices;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int integerToCheck = scanner.nextInt();
    scanner.close();

    String result = check_andIfPossibleCreate_centeredHexagonLattice(integerToCheck);
    System.out.println(result);
  }

  public static String check_andIfPossibleCreate_centeredHexagonLattice(int integerToCheck) {
    if (isCenteredHexagonLattice(integerToCheck)) {
      // returns a string image of the centered haxagon number.
      return create_centeredHexagonLattice();
    }
    return "Invalid";
  }

  public static boolean isCenteredHexagonLattice(int integerToCheck) {
    total_centeredHexagonLattices = 0;
    int current = 0;
    while (current < integerToCheck) {
      current = 3 * total_centeredHexagonLattices * (total_centeredHexagonLattices + 1) + 1;
      total_centeredHexagonLattices++;
    }
    return current == integerToCheck;
  }

  public static String create_centeredHexagonLattice() {

    int totalRows = 2 * total_centeredHexagonLattices - 1;

    /*
     Includes columns with a space between the columns with points, as well as one column with a space
     to the left and to right of the hexagon lattice, as required by the problem statement.
    */
    int totalColumns = 4 * total_centeredHexagonLattices - 1;

    // Capacity includes one char for new line below the hexagon lattice that is subsequently
    // deleted.
    StringBuilder centeredHexagonLattice = new StringBuilder(totalColumns * totalRows + 1);

    // UPPER HALF, MIDDLE ROW INCLUSIVE.
    for (int rows = 0; rows <= (2 * total_centeredHexagonLattices - 1) / 2; rows++) {

      // Upper Left (spaces).
      append_centeredHexagonLatticeSpaces(
          total_centeredHexagonLattices - rows, centeredHexagonLattice);

      // Upper Center (points).
      append_centeredHexagonLatticePoints_separatedBySpace(
          total_centeredHexagonLattices + rows, centeredHexagonLattice);

      // Upper Right (spaces).
      append_centeredHexagonLatticeSpaces(
          total_centeredHexagonLattices - rows, centeredHexagonLattice);

      centeredHexagonLattice.append("\n");
    }

    // LOWER HALF, MIDDLE ROW EXCLUSIVE.
    for (int rows = (2 * total_centeredHexagonLattices) / 2 - 1; rows > 0; rows--) {

      // Lower Left (spaces).
      append_centeredHexagonLatticeSpaces(
          total_centeredHexagonLattices - rows + 1, centeredHexagonLattice);

      // Lower Center (points).
      append_centeredHexagonLatticePoints_separatedBySpace(
          total_centeredHexagonLattices + rows - 1, centeredHexagonLattice);

      // Lower Right (spaces).
      append_centeredHexagonLatticeSpaces(
          total_centeredHexagonLattices - rows + 1, centeredHexagonLattice);

      centeredHexagonLattice.append("\n");
    }

    // This char for new line has to be deleted in order to comply with the problem statement.
    centeredHexagonLattice.deleteCharAt(centeredHexagonLattice.length() - 1);

    return centeredHexagonLattice.toString();
  }

  private static void append_centeredHexagonLatticeSpaces(
      int end, StringBuilder centeredHexagonLattice) {
    for (int columns = 0; columns < end; columns++) {
      centeredHexagonLattice.append(" ");
    }
  }

  private static void append_centeredHexagonLatticePoints_separatedBySpace(
      int end, StringBuilder centeredHexagonLattice) {
    for (int columns = 0; columns < end - 1; columns++) {
      centeredHexagonLattice.append("o ");
    }
    centeredHexagonLattice.append("o");
  }
}
