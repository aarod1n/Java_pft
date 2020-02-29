package ru.stqa.pft.sandbox;

public class Prime {

  public static boolean isPrime(int namber){
    for(int i = 2; i < namber; i++){
      if(namber % i == 0){
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast(int namber){
    for(int i = 2; i < (int)Math.sqrt(namber); i++){
      if(namber % i == 0){
        return false;
      }
    }
    return true;
  }

  public static boolean isPrime(long namber){
    for(long i = 2; i < namber; i++){
      if(namber % i == 0){
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int namber){
    int i = 2;
    while (i < namber && namber % i != 0){
      i++;
    }
    //Если цикл завершился раньше, значит число не простое. делитель найден
    return i == namber;
  }
}
