import com.google.common.base.Strings;

/**
 * Created by Administrator on 2014/11/12.
 */
public class GuavaTest {

  @org.junit.Test
  public void testStrings(){
    int minLength = 9;
    String padEndResult = Strings.padEnd("123", minLength, '0');
    System.out.println("padEndResult is " + padEndResult);

    String padStartResult = Strings.padStart("1", 2, '0');
    System.out.println("padStartResult is " + padStartResult);
  }
}
