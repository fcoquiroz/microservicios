package com.cacti.wsms.dia8;

import rx.Observable;

public class KataFizzBuzz {
  public static void main(String... args) {

    Observable.range(1, 20).map(value -> {
      if (value % 3 == 0 && value % 5 == 0) {
        return "FizzBuzz";
      } else if (value % 5 == 0) {
        return "Buzz";
      } else if (value % 3 == 0) {
        return "Fizz";
      }

      return value.toString();
    }).subscribe(System.out::println);
  }
}
