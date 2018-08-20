package service.mongodb;

import base.BaseTest;
import com.bmw.boss.infos.entity.data.CarRequestData;
import com.bmw.boss.infos.mongodb.service.ICarRequestMongoService;
import com.bmw.boss.common.util.TimeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRequrestMongoServiceTest extends BaseTest {
	private static Logger logger = LoggerFactory.getLogger(CarRequrestMongoServiceTest.class);
	@Autowired
	ICarRequestMongoService carRequestMongoService;

	@Autowired
	MongoTemplate mongoTemplate;
	@Test
	public void testInsertList() throws Exception{
    	List<CarRequestData> list = new ArrayList();
    	for(int i = 0;i<5;i++){
			CarRequestData record = new CarRequestData();
			record.setRequestDate(TimeUtils.getDateByFormatString("2018-03-02 00:11:11"));
			record.setAppName("weather");
			record.setPage("main");
			record.setPu("03-18");
			record.setSystem("NBTEvo_ASN");
			record.setVin("123456"+i);
			//record.setUpdateDate(new Date());
			record.setCreatDate(new Date());
			list.add(record);
		}

		carRequestMongoService.insertList(list);

	}

	@Test
	public void testQueryByCondition() throws Exception{
		List<CarRequestData> list = carRequestMongoService.queryByCondition("vin","1234561");
		System.out.println("list data size:"+list.size());

//		Query query = new Query();
//		query.addCriteria(Criteria.where("appName").is("1111"));
//		List<CarRequestData> list = mongoTemplate.find(query,CarRequestData.class);
//		System.out.println("appName: "+list.get(0).getAppName());
	}


	@Test
	public void testDelete(){
		CarRequestData record = new CarRequestData();
		//record.setId("5ab8b81d3d55e8a85fec11de");
		record.setAppName("weather");
		Integer i = carRequestMongoService.delete(record);
		System.out.println("共删除"+i+"条数据");
	}


	@Test
	public void testDeleteByCondition(){
		Integer i = carRequestMongoService.deleteByCondition("appName","weather");
		System.out.println("共删除"+i+"条数据");
	}
}
