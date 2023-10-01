import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {

      // Testing the constructor that reads from a file
      try {
          Polynomial p1 = new Polynomial(new File("polynomial.txt"));
          printPolynomial(p1);

          // Testing the evaluate method
          double x = 2;
          System.out.println("p1 evaluated at x = " + x + ": " + p1.evaluate(x));

          // Creating another Polynomial object
          double[] coeff2 = {3, -5};
          int[] exp2 = {2, 0};
          Polynomial p2 = new Polynomial(coeff2, exp2);

          // Testing the add method
          Polynomial sum = p1.add(p2);
          printPolynomial(sum);

          // Testing the multiply method
          Polynomial product = p1.multiply(p2);
          printPolynomial(product);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
  }

  static void printPolynomial(Polynomial p) {
      System.out.print("Polynomial: ");
      for (int i = 0; i < p.coeff.length; i++) {
          System.out.print(p.coeff[i] + "x^" + p.exponent[i] + " ");
      }
      System.out.println();
  }


}
