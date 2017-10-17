package org.iii.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class GeoipCityService {

	private DatabaseReader dbReader;

	public GeoipCityService() throws IOException {
		
		/** Read mmdb file from src/main/resources */
		Resource resource = new ClassPathResource("GeoLite2-City.mmdb");

		File database = resource.getFile();
		dbReader = new DatabaseReader.Builder(database).build();
	}
	/**
	 * get geo location by maxmind geoip2 api
	 * @param ip
	 * @return
	 * @throws IOException
	 * @throws GeoIp2Exception
	 */
	public String getLocation(String ip) throws IOException, GeoIp2Exception {

		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = dbReader.city(ipAddress);

		return response.toJson();
	}

}
