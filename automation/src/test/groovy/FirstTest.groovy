import geb.spock.GebSpec

class FirstTest extends GebSpec {

    def "Simple first test"() {
        given: "Open site"
        to testPage

        expect: "Site is opened and label is displayed"
        testPage.label.text() == "ТОВАРЫ И УСЛУГИ"
    }

    def testPage = new TestPage()
}