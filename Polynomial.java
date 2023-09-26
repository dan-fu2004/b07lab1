public class Polynomial{

  double[] coeff;

  public Polynomial(){
    this.coeff = new double[]{0};
  }

  public Polynomial(double[] coeff){
    this.coeff = coeff;
  }

  public Polynomial add(Polynomial calling){

    int a = calling.coeff.length;
    int b = this.coeff.length;
    double[] new_coeff;
    if(a>b){
      new_coeff = new double[a];
      for(int i = 0; i<a; i++){
        if(i<b){
          new_coeff[i] = this.coeff[i]+calling.coeff[i];
        } else{
          new_coeff[i] = calling.coeff[i];
        }

      }

    } else{

      new_coeff = new double[b];
      for(int i = 0; i<a; i++){
        if(i<a){
          new_coeff[i] = this.coeff[i]+calling.coeff[i];
        } else{
          new_coeff[i] = this.coeff[i];
        }

      }
    }

    Polynomial added = new Polynomial(new_coeff);
    return added;
  }

  public double evaluate(double x){
    double acc = 0;
    int length = this.coeff.length;

    for(int i =0; i<length; i++){
      double curr = this.coeff[i];

      for(int j = 0; j<i; j++){
        curr = curr*x;
      }

      acc = acc+curr;
    }
    return acc;
  }


  public boolean hasRoot(double x){
    if(evaluate(x) == 0){
      return true;
    }
    return false;
  }
}
