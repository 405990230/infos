package service.mybatis;

import base.BaseTest;
import com.bmw.boss.infos.entity.Websites;
import com.bmw.boss.infos.mybatis.service.IWebsitesService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class WebsitesTest extends BaseTest{
	private static Logger logger = LoggerFactory.getLogger(WebsitesTest.class);
	@Autowired
	IWebsitesService websitesService;
    @Test
    public void testQueryById() throws SQLException{
		Websites web = websitesService.selectByPrimaryKey(1);
		logger.info(web.getName()+":"+web.getUrl());

    }

	@Test
	public void tesQueryList() throws SQLException{
		List<Websites> list = websitesService.querList();
		for(Websites web : list){
			logger.info(web.getName()+":"+web.getUrl());
		}


	}
}
