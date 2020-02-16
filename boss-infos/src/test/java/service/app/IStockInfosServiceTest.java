package service.app;

import base.BaseTest;
import com.bmw.boss.infos.app.pojo.json.SectorPojo;
import com.bmw.boss.infos.app.pojo.json.StocksPojo;
import com.bmw.boss.infos.app.service.IStockInfosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
public class IStockInfosServiceTest extends BaseTest{
    private static Logger logger = LoggerFactory.getLogger(IStockInfosServiceTest.class);
    @Autowired
    private IStockInfosService stockInfosService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetHsidx(){
        try {
            List<StocksPojo> list = stockInfosService.getHsidx();
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void testGetSectors(){
        try {
            List<SectorPojo> list = stockInfosService.getSectors();
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void testGetStockInfoBySearch(){
        try {
            List<StocksPojo> list = stockInfosService.getStockInfoBySearch("SZ000893");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void testGetSectorsDetail(){
        try {
            List<StocksPojo> list = stockInfosService.getSectorsDetail("4142");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void testGetMyFav(){
        try {
            List<StocksPojo> list = stockInfosService.getMyFav("SH000001,SH600023,SH600016");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }
}


