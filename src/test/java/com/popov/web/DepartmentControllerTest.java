package com.popov.web;

import com.popov.service.dto.DepartmentInDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DepartmentControllerTest {

    @Test
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveDepartmentNoParent() {
        given()
                .auth().basic("admin", "admin")
                .with()
                .body(new DepartmentInDto(null, null, "HR department", 10, false))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/departments")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("parentId", is(nullValue()))
                .body("name", is("HR department"))
                .body("members", is(10))
                .body("archived", is(false));

    }

    @Test
    @Sql(scripts = {"/scripts/departments.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveDepartmentWithParent() {
        given()
                .auth().basic("admin", "admin")
                .with()
                .body(new DepartmentInDto(null, 22L, "QA department", 5, false))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/departments")
                .then()
                .statusCode(200)
                .body("id", is(23))
                .body("parentId", is(22))
                .body("name", is("QA department"))
                .body("members", is(5))
                .body("archived", is(false));

    }

    @Test
    @Sql(scripts = {"/scripts/departments.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findSubTreeFromTop() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("GET", "/api/departments/sub-tree")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .statusCode(200);
    }

    @Test
    @Sql(scripts = {"/scripts/departments.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findSubTreeById() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("GET", "/api/departments/1/sub-tree")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .statusCode(200).and()
                .body("id", is(1)).and()
                .body("parentId", is(nullValue())).and()
                .body("children[0].id", is(4)).and()
                .body("children[0].parentId", is(1)).and()
                .body("children[0].children[0].id", is(10)).and()
                .body("children[0].children[0].parentId", is(4)).and()
                .body("children[0].children[0].children", is(nullValue())).and()
                .body("children[0].children[1].id", is(11)).and()
                .body("children[0].children[1].parentId", is(4)).and()
                .body("children[0].children[1].children", is(nullValue())).and()
                .body("children[0].children[2].id", is(12)).and()
                .body("children[0].children[2].parentId", is(4)).and()
                .body("children[0].children[2].children", is(nullValue())).and()
                .body("children[1].id", is(5)).and()
                .body("children[1].parentId", is(1)).and()
                .body("children[1].children.size()", is(0));
    }
}