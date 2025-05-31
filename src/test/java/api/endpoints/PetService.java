package api.endpoints;

import api.model.Pet;
import common.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static api.base.BaseApiTest.BASE_URL;
import static common.utils.Constants.OWNER_ENDPOINT;
import static common.utils.Constants.PET_ENDPOINT;
import static io.restassured.RestAssured.config;

@Slf4j
public class PetService {

    private RequestSpecification getJsonRequestSpec(Object body) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Accept", "application/json")
                .addHeader("Content-type", "application/json")
                .setConfig(config)
                .build()
                .body(body);
    }

    public Response getPetByOwnerIdAndPetId(int ownerId, int petId) {
        log.info("API Call: GET pet by ownerId and petId - Endpoint: {}/{}{}/{}", OWNER_ENDPOINT, ownerId, PET_ENDPOINT, petId);
        return RestAssured.get(BASE_URL + OWNER_ENDPOINT + "/" + ownerId + PET_ENDPOINT + "/" + petId);
    }

    public Response addPet(Pet pet) {
        log.info("API Call: Add new pet for the owner to endpoint: {}/{}{}", OWNER_ENDPOINT, pet.getOwnerId(), PET_ENDPOINT);

        Response response = RestAssured.given()
                .spec(getJsonRequestSpec(pet))
                .post(OWNER_ENDPOINT + "/" + pet.getOwnerId() + PET_ENDPOINT);

        if (response.getStatusCode() != 201) {
            log.error("API Error: Failed to create pet. Status: {}, Response: {}",
                    response.getStatusCode(), response.getBody().asString());
        }

        return response;
    }

    public Response updatePet(Pet pet) {
        log.info("API Call: Update pet for the owner to endpoint: {}/{}{}", OWNER_ENDPOINT, pet.getOwnerId(), PET_ENDPOINT);

        return RestAssured.given()
                .spec(getJsonRequestSpec(pet.getOwnerId()))
                .put(Constants.OWNER_ENDPOINT + "/" + pet.getOwnerId() + PET_ENDPOINT + "/" + pet.getId());

    }

}
