package Link.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-12-12 17:34
 */
public class Leader {
    private Map<String, Employee> refister = new HashMap<String, Employee>();

    //知道每个员工的特长去分发任务
    public Leader() {
        refister.put("jiami", new EmployeeA());
        refister.put("jiagou", new EmployeeB());
    }

    public void dosoming(String command) {
        refister.get(command).dosoming(command);
    }
}
