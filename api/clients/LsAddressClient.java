package com.leroy.magmobile.api.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.leroy.core.api.BaseMashupClient;
import com.leroy.magmobile.api.data.address.*;
import com.leroy.magmobile.api.data.address.cellproducts.*;
import com.leroy.magmobile.api.helpers.LsAddressHelper;
import com.leroy.magmobile.api.requests.address.*;
import io.qameta.allure.Step;
import org.testng.util.Strings;
import ru.leroymerlin.qa.core.clients.base.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LsAddressClient extends BaseMashupClient {

    @Inject
    private LsAddressHelper lsAddressHelper;

    // REQUESTS //

    // Alleys
    @Step("Create Alley")
    public Response<AlleyData> createAlley(AlleyData alleyData) {
        LsAddressAlleysPostRequest req = new LsAddressAlleysPostRequest();
        req.jsonBody(alleyData);
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        req.setShopId(getUserSessionData().getUserShopId());
        return execute(req, AlleyData.class);
    }

    @Step("Search for alleys")
    public Response<AlleyDataItems> searchForAlleys() {
        LsAddressAlleysRequest req = new LsAddressAlleysRequest();
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        req.setShopId(getUserSessionData().getUserShopId());
        return execute(req, AlleyDataItems.class);
    }


    @Step("Rename Alley")
    public Response<AlleyData> renameAlley(AlleyData alleyData) {
        LsAddressAlleysPutRequest req = new LsAddressAlleysPutRequest();
        req.setAlleyId(alleyData.getId());
        req.setShopId(alleyData.getStoreId());
        req.setDepartmentId(alleyData.getDepartmentId());
        req.setCode(alleyData.getCode());
        return execute(req, AlleyData.class);
    }

    @Step("Delete Alley")
    public Response<AlleyData> deleteAlley(AlleyData alleyData) {
        LsAddressAlleysDeleteRequest req = new LsAddressAlleysDeleteRequest();
        req.setAlleyId(alleyData.getId());
        req.setCode(alleyData.getCode());
        return execute(req, AlleyData.class);

    }

    // Stands

    @Step("Create stand with alleyId={alleyId}")
    public Response<StandDataList> createStand(Integer alleyId, StandDataList postData) {
        LsAddressStandsPostRequest req = new LsAddressStandsPostRequest();
        req.setAlleyId(alleyId);
        req.setShopId(getUserSessionData().getUserShopId());
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        req.jsonBody(postData);
        return execute(req, StandDataList.class);
    }

    @Step("Search for stands with alleyId={alleyId}")
    public Response<StandDataList> searchForStand(Integer alleyId) {
        LsAddressStandsRequest req = new LsAddressStandsRequest();
        req.setAlleyId(alleyId);
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        req.setShopId(getUserSessionData().getUserShopId());
        return execute(req, StandDataList.class);
    }

    // Scheme

    @Step("Get scheme")
    public Response<SchemeData> getScheme() {
        LsAddressSchemeRequest req = new LsAddressSchemeRequest();
        req.setShopId(getUserSessionData().getUserShopId());
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        return execute(req, SchemeData.class);
    }

    @Step("Update scheme with schemeType={schemeType}")
    public Response<JsonNode> putScheme(int schemeType) {
        LsAddressSchemePutRequest req = new LsAddressSchemePutRequest();
        req.setShopId(getUserSessionData().getUserShopId());
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        SchemeData schemeData = new SchemeData();
        schemeData.setSchemeType(schemeType);
        req.jsonBody(schemeData);
        return execute(req, JsonNode.class);
    }

    // Cells

    @Step("Create Cell for standId={standId}")
    public Response<CellDataList> createCell(int standId, CellDataList postData) {
        LsAddressCellsPostRequest req = new LsAddressCellsPostRequest();
        req.setStandId(standId);
        req.jsonBody(postData);
        return execute(req, CellDataList.class);
    }

    @Step("Get cells with standId={standId}")
    public Response<CellDataList> getCells(int standId) {
        LsAddressCellsRequest req = new LsAddressCellsRequest();
        req.setStandId(standId);
        return execute(req, CellDataList.class);
    }

    @Step("Update cells with standId={standId}")
    public Response<CellDataList> updateCells(int standId, CellDataList putData) {
        LsAddressCellsPutRequest req = new LsAddressCellsPutRequest();
        req.setStandId(standId);
        req.jsonBody(putData);
        return execute(req, CellDataList.class);
    }

    @Step("Delete cell with cellId={cellId}")
    public Response<JsonNode> deleteCell(String cellId) {
        LsAddressCellsDeleteRequest req = new LsAddressCellsDeleteRequest();
        req.setCellId(cellId);
        return execute(req, JsonNode.class);
    }

    @Step("Search product cells with lmCode={lmCode}")
    public Response<ProductCellDataList> searchProductCells(String lmCode) {
        LsAddressCellSearchRequest req = new LsAddressCellSearchRequest();
        req.setShopId(getUserSessionData().getUserShopId());
        req.setLmCode(lmCode);
        return execute(req, ProductCellDataList.class);
    }

    // Cell Products

    @Step("Create cell products for cellId={cellId}")
    public Response<CellProductDataList> createCellProducts(String cellId, ReqCellProductDataList postData) {
        LsAddressCellProductsPostRequest req = new LsAddressCellProductsPostRequest();
        req.setCellId(cellId);
        req.setLdapHeader(getUserSessionData().getUserLdap());
        req.setShopId(getUserSessionData().getUserShopId());
        req.jsonBody(postData);
        return execute(req, CellProductDataList.class);
    }

    @Step("Get cell products for cellId={cellId}")
    public Response<CellProductDataList> getCellProducts(String cellId) {
        LsAddressCellProductsRequest req = new LsAddressCellProductsRequest();
        req.setShopId(getUserSessionData().getUserShopId());
        req.setCellId(cellId);
        return execute(req, CellProductDataList.class);
    }

    @Step("Update cell products for cellId={cellId}, lmCode={lmCode}")
    public Response<CellProductData> updateCellProducts(String cellId, String lmCode, ReqCellProductData putData) {
        LsAddressCellProductsPut req = new LsAddressCellProductsPut();
        req.setLdapHeader(getUserSessionData().getUserLdap());
        req.setShopId(getUserSessionData().getUserShopId());
        req.setCellId(cellId);
        req.setLmCode(lmCode);
        req.jsonBody(putData);
        return execute(req, CellProductData.class);
    }

    @Step("Move cell products for cellId={cellId}")
    public Response<JsonNode> moveCellProducts(String cellId, ReqCellProductData putData) {
        LsAddressCellProductsMove req = new LsAddressCellProductsMove();
        req.setLdapHeader(getUserSessionData().getUserLdap());
        req.setCellId(cellId);
        req.jsonBody(putData);
        return execute(req, JsonNode.class);
    }

    @Step("Delete cell products for cellId={cellId}")
    public Response<JsonNode> deleteCellProducts(String cellId, String lmCode) {
        LsAddressCellProductsDelete req = new LsAddressCellProductsDelete();
        req.setCellId(cellId);
        req.setLmCode(lmCode);
        req.setLdapHeader(getUserSessionData().getUserLdap());
        req.setShopId(getUserSessionData().getUserShopId());
        return execute(req, JsonNode.class);
    }

    @Step("Delete cell products for cellId={cellId}")
    public Response<JsonNode> batchDeleteCellProduct(ProductBatchData postData) {
        LsAddressCellProductsBatchDelete req = new LsAddressCellProductsBatchDelete();
        req.jsonBody(postData);
        req.setLdapHeader(getUserSessionData().getUserLdap());
        req.setShopId(getUserSessionData().getUserShopId());
        return execute(req, JsonNode.class);
    }

    // Pdf

    @Step("Report pdf file to email")
    public Response<JsonNode> pdfReport(StandDataList postData) {
        LsAddressReportPostRequest req = new LsAddressReportPostRequest();
        req.setShopId(getUserSessionData().getUserShopId());
        req.setDepartmentId(getUserSessionData().getUserDepartmentId());
        req.jsonBody(postData);
        return execute(req, JsonNode.class);
    }


    // VERIFICATIONS ///

    // Alley

    @Step("Check that alley is created and response matches postData")
    public void assertThatAlleyIsCreated(Response<AlleyData> resp, AlleyData expectedData) {
        assertThatResponseIsOk(resp);
        AlleyData actualData = resp.asJson();
        softAssert().isTrue(actualData.getId() > 0, "Alley id doesn't match expected value");
        softAssert().isTrue(actualData.getCount() == 0, "Count doesn't match expected value");
        softAssert().isEquals(actualData.getType(), expectedData.getType(), "Alley type doesn't match expected value");
        softAssert().isEquals(actualData.getStoreId(),
                Integer.parseInt(getUserSessionData().getUserShopId()), "StoreId doesn't match expected value");
        softAssert().isEquals(actualData.getDepartmentId(),
                Integer.parseInt(getUserSessionData().getUserDepartmentId()), "DepartmentId doesn't match expected value");
        softAssert().isTrue(Strings.isNotNullAndNotEmpty(actualData.getCode()), "Alley name doesn't match expected value");
        softAssert().verifyAll();
    }

    public void assertThatGetAlleyList(Response<AlleyDataItems> resp) {
        assertThatResponseIsOk(resp);
        List<AlleyData> items = resp.asJson().getItems();
        assertThat("items count", items, hasSize(greaterThan(0)));
        for (AlleyData alleyData : items) {
            String desc = String.format("AlleyId(%s): ", alleyData.getId());
            softAssert().isTrue(alleyData.getId() >= 0, desc + ": id doesn't match");
            softAssert().isTrue(alleyData.getCount() >= 0, desc + ": count doesn't match");
            softAssert().isTrue(alleyData.getType() >= 0, desc + ": type doesn't match");
            softAssert().isEquals(alleyData.getStoreId(),
                    Integer.parseInt(getUserSessionData().getUserShopId()), desc + ": storeId doesn't match");
            softAssert().isEquals(alleyData.getDepartmentId(),
                    Integer.parseInt(getUserSessionData().getUserDepartmentId()), desc + ": departmentId doesn't match");
            softAssert().isTrue(Strings.isNotNullAndNotEmpty(alleyData.getCode()), desc + ": alley name is null or empty");
        }
        softAssert().verifyAll();
    }

    @Step("Check that alley is renamed")
    public void assertThatAlleyIsRenamed(Response<AlleyData> resp, AlleyData expectedData) {
        assertThatResponseIsOk(resp);
        AlleyData actualData = lsAddressHelper.searchAlleyById(expectedData.getId());
        assertThat("id", actualData.getId(), is(expectedData.getId()));
        assertThat("count", actualData.getCount(), is(0));
        assertThat("storeId", actualData.getStoreId(), is(Integer.valueOf(getUserSessionData().getUserShopId())));
        assertThat("departmentId", actualData.getDepartmentId(),
                is(Integer.valueOf(getUserSessionData().getUserDepartmentId())));
        assertThat("code", actualData.getCode(), is(expectedData.getCode()));
    }

    @Step("Check that alley is deleted")
    public void assertThatAlleyIsDeleted(Response<AlleyData> resp, int alleyId) {
        assertThatResponseIsOk(resp);
        AlleyData actualData = lsAddressHelper.searchAlleyById(alleyId, true);
        assertThat("id", actualData.getId(), nullValue());
        assertThat("count", actualData.getCount(), nullValue());
        assertThat("storeId", actualData.getStoreId(), nullValue());
        assertThat("departmentId", actualData.getDepartmentId(), nullValue());
        assertThat("code", actualData.getCode(), nullValue());
    }

    // Stand

    @Step("Check that stand is created and response matches postData")
    public StandDataList assertThatStandIsCreatedAndGetData(Response<StandDataList> resp, StandDataList postData) {
        assertThatResponseIsOk(resp);
        StandDataList actualData = resp.asJson();
        assertThat("items count", actualData.getItems(), hasSize(postData.getItems().size()));
        for (int i = 0; i < actualData.getItems().size(); i++) {
            StandData actualItem = actualData.getItems().get(i);
            StandData expectedItem = postData.getItems().get(i);
            assertThat("code", actualItem.getCode(),
                    containsString(String.format("-%s-", postData.getAlleyCode())));
            assertThat("id", actualItem.getId(), greaterThan(0));
            assertThat("side", actualItem.getSide(), is(expectedItem.getSide()));
            assertThat("size", actualItem.getSize(), is(expectedItem.getSize()));
            assertThat("position", actualItem.getPosition(), is(expectedItem.getPosition()));
            assertThat("cellsCount", actualItem.getCellsCount(), is(0));
            assertThat("productsCount", actualItem.getProductsCount(), is(0));
            assertThat("equipmentId", actualItem.getEquipmentId(), is(0));
        }
        return actualData;
    }

    @Step("Check that response matches expected Data")
    public void assertThatDataMatches(Response<StandDataList> resp, StandDataList expectedData) {
        assertThatResponseIsOk(resp);
        StandDataList actualData = resp.asJson();

        assertThat("items count", actualData.getItems(), hasSize(expectedData.getItems().size()));
        for (int i = 0; i < actualData.getItems().size(); i++) {
            StandData actualItem = actualData.getItems().get(i);
            StandData expectedItem = expectedData.getItems().get(i);
            String desc = String.format("StandId(%s): ", actualItem.getId());
            softAssert().isEquals(actualItem.getId(), expectedItem.getId(),
                    desc + "id doesn't match the expected");
            softAssert().isEquals(actualItem.getCode(), expectedItem.getCode(),
                    desc + "code doesn't match the expected");
            softAssert().isEquals(actualItem.getSide(), expectedItem.getSide(),
                    desc + "side doesn't match the expected");
            softAssert().isEquals(actualItem.getSize(), expectedItem.getSize(),
                    desc + "size doesn't match the expected");
            softAssert().isEquals(actualItem.getPosition(), expectedItem.getPosition(),
                    desc + "position doesn't match the expected");
            softAssert().isEquals(actualItem.getCellsCount(), expectedItem.getCellsCount(),
                    desc + "Cells count doesn't match the expected");
            softAssert().isEquals(actualItem.getProductsCount(), expectedItem.getProductsCount(),
                    desc + "Products count doesn't match the expected");
            softAssert().isEquals(actualItem.getEquipmentId(), expectedItem.getEquipmentId(),
                    desc + "Equipment id doesn't match the expected");
        }
        softAssert().verifyAll();
    }

    // Scheme
    @Step("Check that we can get scheme and check data")
    public void assertThatGetScheme(Response<SchemeData> resp) {
        assertThatResponseIsOk(resp);
        SchemeData schemeData = resp.asJson();
        softAssert().isTrue(schemeData.getSchemeType() >= 0 && schemeData.getSchemeType() < 3,
                "Scheme type doesn't match the expected");
        softAssert().isTrue(schemeData.getNavigationType() > 0,
                "Navigation type doesn't match the expected");
        softAssert().isEquals(schemeData.getDepartmentId(),
                Integer.parseInt(getUserSessionData().getUserDepartmentId()),
                "Department id doesn't match the expected");
        softAssert().isEquals(schemeData.getShopId(),
                Integer.parseInt(getUserSessionData().getUserShopId()),
                "Shop id doesn't match the expected");
        softAssert().verifyAll();
    }

    @Step("Check that response is success")
    public void assertThatSchemeIsUpdated(Response<JsonNode> resp) {
        assertThatResponseIsOk(resp);
        assertThat("success", resp.asJson().get("success").asText(), is("true"));
    }

    // Cell

    @Step("Check that cell is created and response has valid data")
    public void assertThatCellIsCreated(Response<CellDataList> resp, int standId, CellDataList postData) {
        assertThatResponseIsOk(resp);
        CellDataList actualData = resp.asJson();
        assertThat("items count", actualData.getItems(), hasSize(postData.getItems().size()));
        for (int i = 0; i < actualData.getItems().size(); i++) {
            CellData actualItem = actualData.getItems().get(i);
            CellData expectedItem = postData.getItems().get(i);
            String desc = String.format("StandId(%s), CellId(%s): ", standId, actualItem.getId());
            softAssert().isTrue(Strings.isNotNullAndNotEmpty(actualItem.getId()), desc + "id is null or empty");
            softAssert().isTrue(Strings.isNotNullAndNotEmpty(actualItem.getCode()), desc + "code is null or empty");
            softAssert().isEquals(actualItem.getShelf(), expectedItem.getShelf(),
                    desc + "shelf doesn't match the expected");
            softAssert().isEquals(actualItem.getPosition(), expectedItem.getPosition(),
                    desc + "side doesn't match the expected");
            softAssert().isEquals(actualItem.getType(), expectedItem.getType(),
                    desc + "type doesn't match the expected");
            softAssert().isEquals(actualItem.getStandId(), standId,
                    desc + "standId doesn't match the expected");
            softAssert().isTrue(actualItem.getProductsCount() >= 0,
                    desc + "productsCount doesn't match the expected");
        }
        softAssert().verifyAll();
    }

    public void assertThatDataMatches(Response<CellDataList> resp, CellDataList expectedData) {
        assertThatDataMatches(resp, expectedData, ResponseType.GET);
    }

    @Step("Check that response matches expected Data")
    public void assertThatDataMatches(Response<CellDataList> resp, CellDataList expectedData, ResponseType respType) {
        assertThatResponseIsOk(resp);
        CellDataList actualData = resp.asJson();
        assertThat("items count", actualData.getItems(), hasSize(expectedData.getItems().size()));
        // Check main Cell id
        if (respType.equals(ResponseType.GET)) {
            for (CellData cellData : expectedData.getItems()) {
                assertThat("Cell id", actualData.getStandId(), is(cellData.getStandId()));
            }
        }
        // Check items
        for (int i = 0; i < actualData.getItems().size(); i++) {
            CellData actualItem = actualData.getItems().get(i);
            CellData expectedItem = expectedData.getItems().get(i);
            String desc = String.format("StandId(%s), CellId(%s): ", actualData.getStandId(), actualItem.getId());
            if (ResponseType.PUT.equals(respType) && expectedItem.getId() == null) {
                softAssert().isTrue(Strings.isNotNullAndNotEmpty(actualItem.getId()), "id is null or empty");
                softAssert().isTrue(Strings.isNotNullAndNotEmpty(actualItem.getCode()), "code is null or empty");
                softAssert().isTrue(actualItem.getProductsCount() >= 0,
                        "productsCount doesn't match the expected");
                softAssert().isNotNull(actualItem.getStandId(), "StandId is null");
            } else {
                softAssert().isEquals(actualItem.getId(), expectedItem.getId(),
                        desc + "id doesn't match the expected");
                softAssert().isEquals(actualItem.getCode(), expectedItem.getCode(),
                        desc + "code doesn't match the expected");
                softAssert().isEquals(actualItem.getProductsCount(), expectedItem.getProductsCount(),
                        desc + "product count doesn't match the expected");
                softAssert().isEquals(actualItem.getStandId(), expectedItem.getStandId(),
                        desc + "stand id doesn't match the expected");
            }
            softAssert().isEquals(actualItem.getShelf(), expectedItem.getShelf(),
                    desc + "shelf doesn't match the expected");
            softAssert().isEquals(actualItem.getPosition(), expectedItem.getPosition(),
                    desc + "position doesn't match the expected");
            softAssert().isEquals(actualItem.getType(), expectedItem.getType(),
                    desc + "type doesn't match the expected");
        }
        softAssert().verifyAll();
    }

    @Step("Check that cell is deleted. CellId={deletedCellId}")
    public void assertThatCellIsDeleted(Response<JsonNode> resp, String deletedCellId) {
        assertThatResponseIsOk(resp);
        JsonNode respData = resp.asJson();
        assertThat("item id", respData.get("items").get("items").get(0).asText(),
                is(deletedCellId));
    }

    // Cell products
    @Step("Check that cell products are created")
    public void assertThatIsCellProductsIsCreated(
            Response<CellProductDataList> resp, ReqCellProductDataList postData, CellData cellData) {
        assertThatResponseIsOk(resp);
        CellProductDataList actualRespData = resp.asJson();
        assertThat("items", actualRespData.getItems(), hasSize(postData.getItems().size()));
        for (int i = 0; i < postData.getItems().size(); i++) {
            CellProductData actualCellProductData = actualRespData.getItems().get(i);
            ReqCellProductData expectedCellProductData = postData.getItems().get(i);
            ProductCellData actualCellData = actualCellProductData.getLsAddressCells().stream()
                    .filter((s) -> s.getCode().equals(cellData.getCode())).findFirst()
                    .orElseThrow(() -> new AssertionError("Required cell code doesn't found"));


            String desc = String.format("StandId(%s), CellCode(%s): ", actualCellData.getStandId(), actualCellData.getCode());
            softAssert().isEquals(actualCellProductData.getQuantity(), expectedCellProductData.getQuantity(),
                    desc + "Product quantity doesn't match the expected");
            softAssert().isEquals(actualCellProductData.getLmCode(), expectedCellProductData.getLmCode(),
                    desc + "Product lmCode doesn't match the expected");
            softAssert().isNotNull(actualCellProductData.getLsAddressCells(),
                    desc + "Cells list is empty or null");
            softAssert().isEquals(actualCellProductData.getLsAddressCells().size(), 2,
                    desc + "lsAddressCells count doesn't match the expected");
            softAssert().isEquals(actualCellData.getId(), cellData.getId(),
                    desc + "lsAddress Cell - Id doesn't match the expected");
            softAssert().isEquals(actualCellData.getCode(), cellData.getCode(),
                    desc + "lsAddress Cell - Code doesn't match the expected");
            softAssert().isEquals(actualCellData.getPosition(), cellData.getPosition(),
                    desc + "lsAddress Cell - Position doesn't match the expected");
            softAssert().isEquals(actualCellData.getQuantity(), expectedCellProductData.getQuantity(),
                    desc + "lsAddress Cell - Quantity");
            softAssert().isEquals(actualCellData.getShelf(), cellData.getShelf(),
                    desc + "lsAddress Cell - Shelf doesn't match the expected");
            softAssert().isEquals(actualCellData.getStandId(), cellData.getStandId(),
                    desc + "lsAddress Cell - Stand Id doesn't match the expected");
            softAssert().isEquals(actualCellData.getType(), cellData.getType(),
                    desc + "lsAddress Cell - Type doesn't match the expected");
        }
        softAssert().verifyAll();
    }

    @Step("Check that product moved to new cell ")
    public void assertThatProductMovedToNewCell(Response<CellProductDataList> resp, CellProductData expectedData) {
        assertThatResponseIsOk(resp);
        CellProductData actualRespData = resp.asJson().getItems().get(0);
        ProductCellData expectedProductCellData = expectedData.getLsAddressCells().get(0);
        ProductCellData actualProductCellData = actualRespData.getLsAddressCells().stream()
                .filter((s) -> s.getId().equals(expectedProductCellData.getId())).findFirst()
                .orElseThrow(() -> new AssertionError("Required cellId doesn't found"));


        softAssert().isEquals(actualProductCellData.getId(), expectedProductCellData.getId(),
                "lsAddress Cell - Cell's id doesn't match the expected");
        softAssert().isEquals(actualProductCellData.getCode(), expectedProductCellData.getCode(),
                "lsAddress Cell - Cell's code id doesn't match the expected");
        softAssert().isEquals(actualRespData.getLmCode(), expectedData.getLmCode(),
                "lsAddress Cell - LmCode doesn't match the expected");
        softAssert().isEquals(actualRespData.getQuantity(), expectedData.getQuantity(),
                "lsAddress Cell - Product quantity doesn't match the expected");
        softAssert().verifyAll();
    }

    @Step("Check that response matches expected Data")
    public void assertThatDataMatches(Response<CellProductDataList> resp, CellProductDataList expectedData) {
        assertThatResponseIsOk(resp);
        CellProductDataList actualRespData = resp.asJson();
        assertThat("Cell Products items", actualRespData, equalTo(expectedData));
    }

    @Step("Check that response matches expected Data")
    public void assertThatDataMatches(Response<CellProductData> resp, CellProductData expectedData, String cellCode) {
        assertThatResponseIsOk(resp);
        CellProductData actualRespData = resp.asJson();
        String desc = String.format("CellCode(%s), LmCode(%s): ", cellCode, actualRespData.getLmCode());
        softAssert().isEquals(actualRespData.getQuantity(), expectedData.getQuantity(),
                desc + "\n Products quantity doesn't match the expected.");
        softAssert().isEquals(actualRespData.getLmCode(), expectedData.getLmCode(),
                desc + "\n Products lmcode doesn't match the expected.");
        softAssert().verifyAll();
        // to be continued if necessary
    }

    @Step("Check that response matches expected Data")
    public void assertThatDataMatches(Response<ProductCellDataList> resp, List<ProductCellData> expectedData) {
        assertThatResponseIsOk(resp);
        ProductCellDataList actualRespData = resp.asJson();
        assertThat("Ls Address Cells", actualRespData.getItems(), equalTo(expectedData));
    }

    @Step("Check that cell products are deleted for cellId={deletedCellId}")
    public void assertThatCellProductsIsDeleted(Response<JsonNode> resp, String deletedCellId) {
        assertThatResponseIsOk(resp);
        JsonNode respData = resp.asJson();
        assertThat("item id", respData.get("items").get(0).asText(),
                is(deletedCellId));
    }

    @Step("Check that report pdf to email")
    public void assertThatReportPdfIsSuccessful(Response<JsonNode> resp) {
        assertThatResponseIsOk(resp);
        JsonNode respData = resp.asJson();
        assertThat("pdfCreated value", respData.get("pdfCreated").asBoolean(), is(true));
    }

}
