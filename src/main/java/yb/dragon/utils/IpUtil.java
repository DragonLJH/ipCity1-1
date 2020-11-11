package yb.dragon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.apache.commons.io.IOUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

public class IpUtil {

	public String getCityInfo(String ip) throws IOException {

		// db
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("static/ip2region.db");
		byte[] dbsj = IOUtils.toByteArray(inputStream);

		// 查询算法
		int algorithm = DbSearcher.MEMORY_ALGORITYM; // Memory
		// DbSearcher.BINARY_ALGORITHM //Binary
		// DbSearcher.MEMORY_ALGORITYM //Memory
		try {
			DbConfig config = new DbConfig();
			DbSearcher searcher = new DbSearcher(config, dbsj);

			// define the method
			Method method = null;
			switch (algorithm) {
			case DbSearcher.BTREE_ALGORITHM:
				method = searcher.getClass().getMethod("btreeSearch", String.class);
				break;
			case DbSearcher.BINARY_ALGORITHM:
				method = searcher.getClass().getMethod("binarySearch", String.class);
				break;
			case DbSearcher.MEMORY_ALGORITYM:
				method = searcher.getClass().getMethod("memorySearch", String.class);
				break;
			}

			DataBlock dataBlock = null;
			if (Util.isIpAddress(ip) == false) {
				System.out.println("Error: Invalid ip address");
			}

			dataBlock = (DataBlock) method.invoke(searcher, ip);

 			return dataBlock.getRegion();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
