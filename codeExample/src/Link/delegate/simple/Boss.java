package Link.delegate.simple;

/**
 * 描述:
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-12-12 17:32
 */
public class Boss {
    //发命令
    public void command(String command, Leader leader) {
        leader.dosoming(command);
    }
}
