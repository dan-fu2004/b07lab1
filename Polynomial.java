
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Polynomial{

  double[] coeff;
  int[] exponent;

  public Polynomial(){
    this.coeff = new double[]{0};
    this.exponent = new int[]{0};
  }

  public Polynomial(double[] coeff, int[] exponent){
    this.coeff = coeff;
    this.exponent = exponent;

  }

  public Polynomial(File file){
    Scanner sc = new Scanner(file);
    String data = sc.nextLine();
    sc.close();

    data = data.replace("-", "+-");
    String[] result = data.split("\\+");

    int length = result.length;

    double[] coeff = new double[length];
    int[] exponent = new int[length];

    for(int i = 0; i<length; i++){

      int len = result[i].length();
      int end = 0;

      if(result[i].charAt(0) == '-') {

        for(int j=1; j<len; j++){
          if (!Character.isDigit(result[i].charAt(j))){
              end = i;

              coeff[i] = -1* (Double.parseDouble(result[i].substring(1,end)));
              exponent[i] = Integer.parseInt(result[i].substring(end+1));
              break;
          }



        }

    } else{

        for(int j =0; j<len; j++){
          if (!Character.isDigit(result[i].charAt(j))){
              end = i;

              coeff[i] = Double.parseDouble(result[i].substring(0,end));
              exponent[i] = Integer.parseInt(result[i].substring(end+1));
              break;
          }



        }

    }

    }

    this.coeff = coeff;
    this.exponent = exponent;


  }

  public Polynomial clean(){

    int length = this.coeff.length;
    int count = 0;
    for(int i = 0; i<length; i++){
      if(this.coeff[i] == 0){
        count++;
      }
    }

    double[] new_coeff = new double[length-count];
    int[] new_exponent = new int[length-count];
    count = 0;
    for(int i = 0; i<length; i++){
      if(this.coeff[i]!=0){
        new_coeff[count] = this.coeff[i];
        new_exponent[count] = this.exponent[i];
        count++;
      }
    }

    Polynomial p = new Polynomial(new_coeff, new_exponent);
    return p;
  }

  public Polynomial add(Polynomial calling){

    int a = calling.coeff.length;
    int b = this.coeff.length;

    double[] new_coeff = new double[a+b];
    int[] new_exponent = new int[a+b];
    int count = 1;

    for(int i = 0; i<a; i++){
      new_coeff[i] = calling.coeff[i];
      new_exponent[i] = calling.exponent[i];

      for(int j =0; j<b; j++){
        if(this.exponent[j] == new_exponent[i]){
          new_coeff[i]= new_coeff[i] + this.coeff[j];
          break;
        }
      }


    }

    for(int i =0; i<b; i++){
      boolean flag = true;
      for(int j = 0; j<a; j++){
        if(this.exponent[i] == new_exponent[j]){
          flag = false;
          break;
        }
      }

      if(flag){
        new_coeff[a+count] = this.coeff[i];
        new_exponent[a+count] = this.exponent[i];
        count++;
      }

    }




    Polynomial added = new Polynomial(new_coeff, new_exponent);
    added = added.clean();
    return added;
  }

  // public double evaluate(double x){
  //   double acc = 0;
  //   int length = this.coeff.length;
  //
  //   for(int i =0; i<length; i++){
  //     double curr = this.coeff[i];
  //
  //     for(int j = 0; j<this.exponent[i]; j++){
  //       curr = curr*x;
  //     }
  //
  //     acc = acc+curr;
  //   }
  //   return acc;
  // }


  public double evaluate(double x) {
      double acc = 0;

      for (int i = 0; i < this.coeff.length; i++) {
          acc += this.coeff[i] * Math.pow(x, this.exponent[i]);
      }

      return acc;
  }



  public boolean hasRoot(double x){
    if(evaluate(x) == 0){
      return true;
    }
    return false;
  }

  public Polynomial multiply(Polynomial other){

    int leng1 = this.coeff.length;
    int leng2 = other.coeff.length;

    Polynomial[] all =  new Polynomial[leng1*leng2];
    int count = 0;

    for(int i =0; i<leng1; i++){

      for(int j =0; j<leng2; j++){
        double[] temp = new double[1];
        int[] exp = new int[1];

        temp[0] = this.coeff[i] * other.coeff[j];
        exp[0] = this.exponent[i] + other.exponent[j];

        all[count] = new Polynomial(temp,exp);
        count++;

      }
    }

    Polynomial result = all[0];

    for(int i =1; i <count; i++){
      result = result.add(all[i]);

    }

    result =  result.clean();
    return result;

  }





}
