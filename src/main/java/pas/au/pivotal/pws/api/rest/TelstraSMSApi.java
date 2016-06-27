package pas.au.pivotal.pws.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping(path = "/telstra")
public class TelstraSMSApi
{
    private static final Logger log = LoggerFactory.getLogger(TelstraSMSApi.class);
    private static final JsonParser parser = JsonParserFactory.getJsonParser();


    @ApiOperation(value = "sendSms", nickname = "sendSms")
    @RequestMapping(value = "/sms", method = RequestMethod.GET, path = "/sms")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public String sendSms(@RequestParam(value="to", required=true) String to,
                          @RequestParam(value="appkey", required=true) String appkey,
                          @RequestParam(value="appsecret", required=true) String appsecret)
    {
        return sendAnSMS(to, null, appkey, appsecret);
    }

    @ApiOperation(value = "sendSms", nickname = "sendSms")
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public String sendSmsPost(@RequestParam(value="to", required=true) String to,
                              @RequestParam(value="body", required=true) String body,
                              @RequestParam(value="appkey", required=true) String appkey,
                              @RequestParam(value="appsecret", required=true) String appsecret)
    {

        return sendAnSMS(to, body, appkey, appsecret);
    }

    private String sendAnSMS
            (String to,
             String body,
             String appKey,
             String appSecret)
    {
        String requestJson = null;

        if (body == null)
        {
            log.info("HTTP GET request to send a Telstra SMS API");
            requestJson = String.format("{\"to\":\"%s\", \"body\":\"Hello, pas sent this message from telstra SMS api on Pivotal Web Services (PWS)!\"}", to);
        }
        else
        {
            log.info("HTTP POST request to send a Telstra SMS API");
            requestJson = String.format("{\"to\":\"%s\", \"body\":\"%s\"}", to, body);
        }

        RestTemplate restTemplate = new RestTemplate();

        String jsonResponse =
                restTemplate.getForObject
                        (String.format
                                ("https://api.telstra.com/v1/oauth/token?client_id=%s&client_secret=%s&grant_type=client_credentials&scope=SMS",
                                        appKey,
                                        appSecret), String.class);

        Map<String, Object> jsonMap = parser.parseMap(jsonResponse);

        String accessToken = (String) jsonMap.get("access_token");

        log.info("Access Token for SMS API is - " + accessToken);

        String url = "https://api.telstra.com/v1/sms/messages";

        log.info(requestJson);

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", String.format("Bearer %s", accessToken));

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        String answer = restTemplate.postForObject(url, entity, String.class);

        log.info("Sent SMS to phone number - " + to);

        return answer;
    }

}
