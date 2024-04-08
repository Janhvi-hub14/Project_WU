Feature: API Testing with Karate

  Background:
    * url 'https://restful-booker.herokuapp.com'

  Scenario: Perform GET Request
    Given path '/booking'
    When method GET
    Then status 200
    * print response
#
  Scenario: Perform POST Request
    Given path '/auth'
    And request {"username": "admin","password": "password123"}
    And header Content-type = 'application/json'
    When method POST
    Then status 200

  Scenario: Perform PUT Request
    Given path '/booking/1'
    And request {"firstname" : "Jim","lastname" : "Brown","totalprice" : 111,"depositpaid" : true,"bookingdates" : {"checkin" : "2018-01-01","checkout" : "2019-01-01"},"additionalneeds" : "Breakfast"}'
    And header Content-type = 'application/json'
    And header Accept  = 'application/json'
    And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
    When method PUT
    Then status 200

  Scenario: Perform PATCH Request
    Given path '/booking/1'
    And request {"firstname" : "Janu","lastname" : "Athanikar"}'
    And header Content-type = 'application/json'
    And header Accept  = 'application/json'
    And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
    And header Cookie = token="c874a9da4adb298"
    When method PATCH
    Then status 200

  Scenario: Perform DELETE Request
    Given path '/booking/1621'
    And header Content-type = 'application/json'
    And header Accept  = 'application/json'
    And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
    And header Cookie = token="c874a9da4adb298"
    When method DELETE
    Then status 201