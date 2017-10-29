Feature: UK Gov vehicle information comparison

  @test
  Scenario: Validate the vehicle information in UK gov website
    Given I have got xls or csv file from the directory
    When I launch the "https://vehicleenquiry.service.gov.uk/" URL
    Then the make and colour should match by the vehicle registration number
