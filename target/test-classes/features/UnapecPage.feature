Feature: UNAPEC Test
  Scenario: Open UNAPEC page and Go To Academic Offers
    Given Launch UNAPEC Home Page
    When User clicks on 'Ofertas de Grado' button
    When User should be able to go to next page
    Then User clicks on other 'Ofertas de Grado' button
    Then User sees all academic offers
