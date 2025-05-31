package api.endpoints;

import api.model.Owner;
import common.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static api.base.BaseApiTest.BASE_URL;
import static io.restassured.RestAssured.config;

@Slf4j
public class OwnerService {

    private RequestSpecification getJsonRequestSpec(Object body) {
        return new RequestSpecBuilder().setBaseUri(BASE_URL).addHeader("Accept", "application/json").addHeader("Content-type", "application/json").setConfig(config).build().body(body);
    }

    /**
     * Retrieves all pet owners.
     *
     * @return Response containing a list of owners.
     */
    public Response getOwners() {
        log.info("API Call: GET all owners at endpoint: {}/list", Constants.OWNER_ENDPOINT);
        return RestAssured.get(BASE_URL + Constants.OWNER_ENDPOINT + "/list");
    }

    /**
     * Retrieves a specific owner by ID.
     *
     * @param id the unique ID of the owner
     * @return Response containing owner details or error
     */
    public Response getOwnerByID(int id) {
        log.info("API Call: GET owner by ID - Endpoint: {}/{}", Constants.OWNER_ENDPOINT, id);
        return RestAssured.get(BASE_URL + Constants.OWNER_ENDPOINT + "/" + id);
    }

    /**
     * Creates a new owner.
     *
     * @param owner the owner object to be created
     * @return Response from the POST request
     */
    public Response createOwner(Owner owner) {
        log.info("API Call: POST new owner to endpoint: {}", Constants.OWNER_ENDPOINT);

        Response response = RestAssured.given().spec(getJsonRequestSpec(owner)).post(Constants.OWNER_ENDPOINT);

        if (response.getStatusCode() != 201) {
            log.error("API Error: Failed to create owner. Status: {}, Response: {}", response.getStatusCode(), response.getBody().asString());
        }

        return response;
    }

    /**
     * Deletes an owner by ID.
     *
     * @param id the ID of the owner to delete
     * @return Response from the DELETE request
     */
    public Response deleteOwnerById(Integer id) {
        log.info("API Call: DELETE owner by ID - Endpoint: {}/{}", Constants.OWNER_ENDPOINT, id);
        return RestAssured.delete(BASE_URL + Constants.OWNER_ENDPOINT + "/" + id);
    }

    /**
     * Updates an existing owner.
     *
     * @param owner the updated owner object
     * @return Response from the PUT request
     */
    public Response updateOwner(Owner owner) {
        log.info("API Call: PUT update owner at endpoint: {}/{}", Constants.OWNER_ENDPOINT, owner.getId());
        return RestAssured.given().spec(getJsonRequestSpec(owner)).put(Constants.OWNER_ENDPOINT + "/" + owner.getId());
    }
}
