package service.mybatis;

import base.BaseTest;
import com.bmw.boss.infos.entity.CarRequest;
import com.bmw.boss.infos.mybatis.service.ICarRequestService;
import com.bmw.boss.common.util.TimeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRequrestServiceTest extends BaseTest {
	private static Logger logger = LoggerFactory.getLogger(CarRequrestServiceTest.class);
	@Autowired
	ICarRequestService carRequestService;
    @Test
    public void testInsertSelective() throws Exception{
		CarRequest record = new CarRequest();
		record.setRequestDate(TimeUtils.getDateByFormatString("2018-01-02 00:11:11"));
		record.setAppName("weather");
		record.setPage("main");
		record.setPu("03-18");
		record.setSystem("NBTEvo_ASN");
		record.setVin("LBVKY5108JSJ95555");
		record.setCreatDate(new Date());
		int i = carRequestService.insertSelective(record);
		if(i==1){
			logger.info("插入成功！");
		}else{
			logger.info("插入异常！");
		}

    }

	@Test
	public void testBatchInsertCarRequestList() throws Exception{
    	List<CarRequest> list = new ArrayList();
    	for(int i = 0;i<5;i++){
			CarRequest record = new CarRequest();
			record.setRequestDate(TimeUtils.getDateByFormatString("2018-01-02 00:11:11"));
			record.setAppName("weather");
			record.setPage("main");
			record.setPu("03-18");
			record.setSystem("NBTEvo_ASN");
			record.setVin("LBVKY5108JSJ95555-----"+i);
			//record.setUpdateDate(new Date());
			record.setCreatDate(new Date());
			list.add(record);
		}

		int i = carRequestService.batchInsertCarRequestList(list);
		if(i!=0){
			logger.info("插入成功！");
		}else{
			logger.info("插入异常！");
		}

	}

	@Test
	public void testDeleteAll() throws Exception{
		logger.info("deleted num :  "+carRequestService.deleteAll());
	}

}
