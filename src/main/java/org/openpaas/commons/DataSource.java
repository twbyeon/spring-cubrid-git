package org.openpaas.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSource extends BasicDataSource {

	private static final Logger logger = LoggerFactory.getLogger(DataSource.class);
	
	public DataSource(){
		super();
		setUserProvidedVcapServices();
	}
	
	private void setUserProvidedVcapServices (){
		/* test */
        String vcap_services = System.getenv("VCAP_SERVICES");
        logger.info("VCAP_SERVICES = " + vcap_services);

        JSONObject jsonObj = JSONObject.fromObject(vcap_services);
        logger.info("vcap_service = " + jsonObj);

        JSONArray userPro = jsonObj.getJSONArray("CubridDB");

        jsonObj = JSONObject.fromObject(userPro.get(0));
        jsonObj = jsonObj.getJSONObject("credentials");
        String username = jsonObj.getString("username");
        String password = jsonObj.getString("password");
        String uri = jsonObj.getString("uri");
        String jdbcURL = jsonObj.getString("jdbcurl");
        logger.info("username = " + username);
        logger.info("password = " + password);
        logger.info("uri = " + uri);
        logger.info("jdbcurl = " + jdbcURL);
        setUsername(username);
        setPassword(password);
        setUrl(jdbcURL);
	}

}
