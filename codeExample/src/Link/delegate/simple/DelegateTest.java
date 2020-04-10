package Link.delegate.simple;

/**
 * 描述:
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-12-12 17:46
 */
public class DelegateTest {
    public static void main(String[] args) {
        new Boss().command("jiami", new Leader());
    }
}
