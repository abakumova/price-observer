package api

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class ApiTests extends Specification {

    @Shared
    def client = new RESTClient("https://reqres.in")

    def "Simple first API testing"() {
        given: "Get response from client"
        def response = client.get(path: "/api/users?page=2")

        expect: "Verify the response"
        response.status == 200
        response.data.page == 1
        response.data.per_page == 6
        response.data.total == 12
    }
}