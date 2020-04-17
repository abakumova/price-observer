package ui

import geb.spock.GebReportingSpec

class UiTests extends GebReportingSpec {

    def "Simple first UI test"() {
        given: "Open site"
        to testPage

        expect: "Site is opened and label is displayed"
        testPage.label.text() != "ТОВАРЫ И УСЛУГИ"
    }

    def "Simple second UI test"() {
        given: "Open site"
        to testPage

        expect: "Site is opened and label is displayed"
        testPage.label.text() == "ТОВАРЫ И УСЛУГИ"
    }

    def testPage = new TestPage()
}