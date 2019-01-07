import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Pair {
  public String key;
  public String value;
  
  public Pair(String k, String v) {
    key = k;
    value = v;
  }
}


class Cruise_Transaction {
  Stack<Pair> stack = new Stack<>();    
  HashMap<String, String> map = new HashMap<>();
  String Reserved = "";

  int limit = 100;
  
  public static void main(String[] args) {
    Cruise_Transaction sol = new Cruise_Transaction();

    sol.end();
    
    sol.set("abc", "123");
    System.out.println(sol.get("abc"));

    sol.begin();
    sol.set("abc", "456");
    System.out.println(sol.get("abc"));
    sol.end();
    

    sol.begin();
    sol.set("abc", "789");
    System.out.println(sol.get("abc"));
      sol.begin();
      sol.set("abc", "eee");
      System.out.println(sol.get("abc"));
      sol.rollback();
    System.out.println(sol.get("abc"));
    sol.end();
    System.out.println(sol.get("abc"));
  }
  
  public void set(String key, String value) {
    if (map.containsKey(key)) {
      String oldValue = map.get(key);
      stack.push((new Pair(key, oldValue)));
      map.put(key, value); 
    } else {
      map.put(key, value);
      stack.push(new Pair(key, value));
    }
  }
  
  public String get(String key) {
    if (map.containsKey(key))
      return map.get(key);
    return null;
  }
  
  public void begin() {
    stack.push(new Pair(Reserved, ""));
  }
                 
  public void end() {
    Pair p = stack.pop();
    if (p == null) {
      throw new Exception("invalid operation for end(): no transaction is open."); 
    }
    
    while (p.key != Reserved) {
      p = stack.pop(); 
    }
  }
                 
  public void rollback() {
    Pair p = stack.pop();
    if (p == null) {
      throw new Exception("invalid operation for rollback(): no transaction is open."); 
    }
    
    while (p.key != Reserved) {
      map.put(p.key, p.value);
      p = stack.pop(); 
    }
  }
}


/* 
Your previous Python 2 content is preserved below:

# set(key, value)
# get(key) -> value
# begin() 
# end() 
# rollback()
#
#Assumptions: 
#
# Values are positive integers.
# Keys are strings
# The caller is single threaded.
 */