package base;


import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述: 测试基类
 *
 * @outhor hants
 * @create 2018-04-25 下午6:24
 */
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest extends TestCase {

    @Ignore
    @Test
    public void test(){
    }


}