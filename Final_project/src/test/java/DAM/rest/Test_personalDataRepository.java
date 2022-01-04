package DAM.rest;

import DAM.models.PersonalData;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_personalDataRepository {
    private static Logger logger = Logger.getLogger(Test_personalDataRepository.class.getName());

    private static String serviceURL = "http://localhost:8081/personal";
    //
    private RestTemplate restTemplate = new RestTemplate();

    private static Integer resourceID;

    @Test
    @Order(1)
    public void test1_CreateResource() throws Exception {
        logger.info("DEBUG: Junit Spring REST Template TESTING: test_CreateResource ...");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("firstName", "test_firstName");
        map.add("lastName", "test_lastName");
        map.add("phoneNo", "072");
        map.add("address", "acasa");

        ResponseEntity<String> personalResponse = restTemplate.postForEntity(serviceURL, map, String.class);
        //PersonalData personalData = personalResponse.getBody();
        String tmpString = personalResponse.getBody();
        tmpString = tmpString.substring(tmpString.indexOf("ID=") + 3);
        tmpString = tmpString.substring(0, tmpString.indexOf(","));
        resourceID = Integer.valueOf(tmpString);

        logger.info("DEBUG: Junit TESTING Spring REST Template: GetMessage ... " + personalResponse.getBody() + resourceID);

    }

    @Test
    @Order(2)
    public void test2_GetResource() throws Exception {

        logger.info("DEBUG: Junit Spring REST Template TESTING: test_GetResource ...");

        ResponseEntity<PersonalData> personalResponse = restTemplate.getForEntity(serviceURL + "/" + resourceID, PersonalData.class);
        PersonalData personalData = personalResponse.getBody();


        logger.info("DEBUG: Junit TESTING Spring REST Template: GetMessage ... " + personalData.toString());
    }

    @Test
    @Order(3)
    public void test3_UpdateResource() throws Exception {

        logger.info("DEBUG: Junit Spring REST Template TESTING: test_UpdateResource ...");

        ResponseEntity<PersonalData> personalResponse = restTemplate.getForEntity(serviceURL + "/" + resourceID, PersonalData.class);
        PersonalData personalData = personalResponse.getBody();

        logger.info("DEBUG: Junit TESTING Spring REST Template: before update Message ... " + personalData.toString());

        personalData.setLastName("Emil");
        personalData.setAddress("Departe");
        personalData.setPhoneNo("0733");

        //ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Game>>(){});
        restTemplate.put(serviceURL + "/" + resourceID + "?lastName=Ivascu&phoneNo=0722222222&address=Roman",personalData, PersonalData.class);

        personalResponse = restTemplate.getForEntity(serviceURL + "/" + resourceID, PersonalData.class);
        personalData = personalResponse.getBody();


        logger.info("DEBUG: Junit TESTING Spring REST Template: after update Message ... " + personalData.toString());
    }

    @Test
    @Order(4)
    public void test4_DeleteResource() throws Exception {

        logger.info("DEBUG: Junit Spring REST Template TESTING: test_DeleteResource ...");

        restTemplate.delete(serviceURL + "/" + resourceID, String.class);


        logger.info("DEBUG: Junit TESTING Spring REST Template: DeleteMessage ... ");
    }
}
