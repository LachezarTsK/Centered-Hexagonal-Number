import java.util.Scanner;

public class Challenge {

  public static int total_hexagonLattices;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int integerToCheck = scanner.nextInt();
    scanner.close();

    String result = check_andIfPossibleCreate_centeredHexagonLattice(integerToCheck);
    System.out.println(result);
  }

  public static String check_andIfPossibleCreate_centeredHexagonLattice(int integerToCheck) {
    if (isCenteredHexagonLattice(integerToCheck)) {
      return create_centeredHexagonLattice();
    }
    return "Invalid";
  }

  public static boolean isCenteredHexagonLattice(int integerToCheck) {
    total_hexagonLattices = 0;
    int current = 0;
    while (current < integerToCheck) {
      current = 3 * total_hexagonLattices * (total_hexagonLattices + 1) + 1;
      total_hexagonLattices++;
    }
    return current == integerToCheck;
  }

  public static String create_centeredHexagonLattice() {

    int totalRows = 2 * total_hexagonLattices - 1;

    /*
     Includes columns with a space between the columns with points, as well as one column with a space
     to the left and to right of the hexagon lattice, as required by the problem statement.
    */
    int totalColumns = 4 * total_hexagonLattices - 1;

    // Capacity includes one char for new line below the hexagon lattice that is subsequently deleted.
    StringBuilder hexagonLattice = new StringBuilder(totalColumns * totalRows + 1);

    // UPPER HALF, MIDDLE ROW INCLUSIVE.
    for (int rows = 0; rows <= (2 * total_hexagonLattices - 1) / 2; rows++) {

      // Upper Left (spaces).
      append_hexagonLatticeSpaces(total_hexagonLattices - rows, hexagonLattice);

      // Upper Center (points).
      append_hexagonLatticePoints_separatedBySpace(total_hexagonLattices + rows, hexagonLattice);

      // Upper Right (spaces).
      append_hexagonLatticeSpaces(total_hexagonLattices - rows, hexagonLattice);

      hexagonLattice.append("\n");
    }

    // LOWER HALF, MIDDLE ROW EXCLUSIVE.
    for (int rows = (2 * total_hexagonLattices) / 2 - 1; rows > 0; rows--) {

      // Lower Left (spaces).
      append_hexagonLatticeSpaces(total_hexagonLattices - rows + 1, hexagonLattice);

      // Lower Center (points).
      append_hexagonLatticePoints_separatedBySpace(
          total_hexagonLattices + rows - 1, hexagonLattice);

      // Lower Right (spaces).
      append_hexagonLatticeSpaces(total_hexagonLattices - rows + 1, hexagonLattice);

      hexagonLattice.append("\n");
    }

    // This char for new line has to be deleted in order to comply with the problem statement.
    hexagonLattice.deleteCharAt(hexagonLattice.length() - 1);

    return hexagonLattice.toString();
  }

  private static void append_hexagonLatticeSpaces(int end, StringBuilder hexagonLattice) {
    for (int columns = 0; columns < end; columns++) {
      hexagonLattice.append(" ");
    }
  }

  private static void append_hexagonLatticePoints_separatedBySpace(
      int end, StringBuilder hexagonLattice) {
    for (int columns = 0; columns < end - 1; columns++) {
      hexagonLattice.append("o ");
    }
    hexagonLattice.append("o");
  }
}
