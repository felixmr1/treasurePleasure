package treasure.pleasure.data;

/**
 *  simple tuple class for sending more than one value
 */
public class Tuple<X,Y> {

     X x;
     Y y;

     public Tuple (X datax, Y datay){
         this.x = datax;
         this.y = datay;

     }

  public X getField1(){
         return  x;
  }

  public Y getField2(){
         return y;
  }


}
