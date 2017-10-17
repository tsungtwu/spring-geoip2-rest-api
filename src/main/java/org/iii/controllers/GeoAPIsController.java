package org.iii.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;
import org.iii.service.GeoipCityService;
import org.json.JSONException;

@Controller
@Api(value = "/geoip", description = "geoip api")
@RequestMapping(value = "/geoip", produces = "application/json; charset=utf-8")
public class GeoAPIsController {

	private static Logger logger = Logger.getLogger(GeoAPIsController.class);

	@Autowired
	GeoipCityService geoip2City;

	
	/**
	 * Get Geo information from GeoLite2-City.mmdb
	 * 
	 * @param response
	 * @param ip
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	@ApiOperation(value = "get Geo info from city", notes = "Get geo location information from city database. ")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = CityResponse.class),
			@ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(value = { "/city/{ip:.+}" }, method = { RequestMethod.GET })
	public @ResponseBody String getCity(HttpServletResponse response,
			@ApiParam(value = "ip address", required = true) @PathVariable String ip)
			throws JSONException, IOException {

		JSONObject json = new JSONObject();

		try {
			json = new JSONObject(geoip2City.getLocation(ip));

		} catch (AddressNotFoundException e) {

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			json.put("msg", e.getMessage());

			logger.debug(e.toString());

		} catch (UnknownHostException e) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			json.put("error", e.getMessage());
			logger.debug(e.toString());

		} catch (GeoIp2Exception e) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			json.put("msg", e.getMessage());
			logger.error(e.toString());

		} catch (Exception e) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			json.put("msg", e.getMessage());
			logger.error(e.toString());
		}

		return json.toString();

	}

	

}
