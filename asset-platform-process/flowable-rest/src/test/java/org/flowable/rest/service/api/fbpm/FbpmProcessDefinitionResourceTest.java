package org.flowable.rest.service.api.fbpm;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.flowable.rest.service.BaseSpringRestTestCase;
import org.junit.Test;

public class FbpmProcessDefinitionResourceTest extends BaseSpringRestTestCase {

    @Test
    public void testGetProcessDefinitions() throws Exception {
        String baseUrl = "http://127.0.0.1:8080/repository/process-definitions";
        CloseableHttpResponse response = executeRequest(new HttpGet(baseUrl), HttpStatus.SC_OK);
        JsonNode dataNode = objectMapper.readTree(response.getEntity().getContent()).get("data");
        System.out.println(dataNode);
    }
}
