package service.jdbc;

import base.BaseTest;
import com.bmw.boss.infos.jdbc.jdbc.IJDBCUtil;
import com.bmw.boss.infos.entity.CarRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JDBCUtilTest extends BaseTest {
	private static Logger logger = LoggerFactory.getLogger(JDBCUtilTest.class);
	@Autowired
	IJDBCUtil jdbcUtil;
    @Test
    public void testJDBC() {
		//UUID uuid = UUID.randomUUID();
		logger.info(getUUID());
		CarRequest car = new CarRequest();
		car.setVin("1");
		car.setAppName("1");
		car.setPu("1");
		car.setSystem("1");
		car.setPage("1");
    	try {
			jdbcUtil.insert(car);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void testJDBC2() {
		List<CarRequest> list = new ArrayList<>();
    	for(int i=0;i<100;i++){
			CarRequest car = new CarRequest();
			UUID uuid = UUID.randomUUID();
			//car.setId(uuid.);
			car.setVin("1");
			car.setAppName("1");
			car.setPu("1");
			car.setSystem("1");
			car.setPage("1");
			list.add(car);
		}

		try {
			jdbcUtil.insertList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJDBCDelete() throws SQLException {
		jdbcUtil.delete();
	}

	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		//去掉“-”符号
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}
}
