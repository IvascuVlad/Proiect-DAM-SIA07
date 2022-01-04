package DAM.rest;

import DAM.models.Contract;
import DAM.models.PersonalData;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_contractRepository {
    private static Logger logger = Logger.getLogger(Test_contractRepository.class.getName());

    private static String serviceURL = "http://localhost:8081/contract";
    //
    private RestTemplate restTemplate = new RestTemplate();

    private static Integer resourceID = 14;

    @Test
    @Order(1)
    public void test1_CreateResource() throws Exception {
        logger.info("DEBUG: Junit Spring REST Template TESTING: test_CreateResource ...");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userID", "7");
        map.add("positionsIDs", "8");

        ResponseEntity<String> contractResponse = restTemplate.postForEntity(serviceURL, map, String.class);
        String tmpString = contractResponse.getBody();
        tmpString = tmpString.substring(tmpString.indexOf("ID=") + 3);
        tmpString = tmpString.substring(0, tmpString.indexOf(","));
        resourceID = Integer.valueOf(tmpString);

        logger.info("DEBUG: Junit TESTING Spring REST Template: GetMessage ... " + contractResponse.getBody() + resourceID);

    }

    @Test
    @Order(2)
    public void test2_GetResource() throws Exception {

        logger.info("DEBUG: Junit Spring REST Template TESTING: test_GetResource ...");

        ResponseEntity<Contract> contractResponse = restTemplate.getForEntity(serviceURL + "/" + resourceID, Contract.class);
        Contract contract = contractResponse.getBody();


        logger.info("DEBUG: Junit TESTING Spring REST Template: GetMessage ... " + contract.toString());
    }

    @Test
    @Order(3)
    public void test3_UpdateResource() throws Exception {

        logger.info("DEBUG: Junit Spring REST Template TESTING: test_UpdateResource ...");

        ResponseEntity<Contract> contractResponse = restTemplate.getForEntity(serviceURL + "/" + resourceID, Contract.class);
        Contract contract = contractResponse.getBody();

        logger.info("DEBUG: Junit TESTING Spring REST Template: before update Message ... " + contract.toString());

        //ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Game>>(){});
        restTemplate.put(serviceURL + "/" + resourceID + "?positionID=31",contract, Contract.class);

        contractResponse = restTemplate.getForEntity(serviceURL + "/" + resourceID, Contract.class);
        contract = contractResponse.getBody();


        logger.info("DEBUG: Junit TESTING Spring REST Template: after update Message ... " + contract.toString());
    }

    @Test
    @Order(4)
    public void test4_DeleteResource() throws Exception {

        logger.info("DEBUG: Junit Spring REST Template TESTING: test_DeleteResource ...");

        restTemplate.delete(serviceURL + "/" + resourceID, String.class);


        logger.info("DEBUG: Junit TESTING Spring REST Template: DeleteMessage ... ");
    }
}
