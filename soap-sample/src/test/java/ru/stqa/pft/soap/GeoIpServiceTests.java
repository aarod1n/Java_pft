package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {

  @Test
  public void myIPTest(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("79.164.19.134");
    // assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
