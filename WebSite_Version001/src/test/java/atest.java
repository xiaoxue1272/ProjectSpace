import com.utils.MysqlBean;
import com.utils.MysqlConnection;
import org.junit.Test;

/**
 * author:xiaoxue1272
 * date:2019/11/9-11:21
 */
public class atest {
    @Test
    public void tset(){
        MysqlBean bean = new MysqlBean("jdbc:mysql://localhost:3306/week1?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai", "root", "127201", "movie");
        MysqlConnection.CreatEntity(bean);

    }
}