package cs445.lab6;

public class Lab6{


  // public static <T> void reverse(T[] a){
  //   return reverse(a, 0, a.length-1);
  //
  // }
  //
  // private static <T> void reverse(T[] a, int start, int end){
  //   if(start<end){
  //     T temp = a[start];
  //     a[start] = a[end];
  //     a[end] = temp;
  //     return reverse(x, start++, end--);
  //   }
  // }

  public static String replace(String str, char before, char after){
    if(str.length()<1){
      return "";
    }else{
      for(int i=0; i<str.length(); i++){
        if(str.charAt(i)==before){
          str = str.substring(0,i) + after + str.substring(++i);
          return replace(str,before,after);
        }
      }
    }
    return str;
  }
}
