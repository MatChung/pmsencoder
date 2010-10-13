@Typed
package com.chocolatey.pmsencoder

class ActionTest extends PMSEncoderTestCase {
    void testScrapeURI() {
        def customConfig = this.getClass().getResource('/action.groovy')
        def uri = 'http://action.com'
        def command = new Command([ $URI: uri ])
        def wantCommand = new Command(
            [ $URI: uri, $rfc: '2606' ], []
        )

        matcher.load(customConfig)

        assertMatch(
            command,       // supplied command
            wantCommand,   // expected command
            [ 'Scrape' ],  // expected matches
        )
    }

    void testStringifyValues() {
        def customConfig = this.getClass().getResource('/action.groovy')
        def uri = 'http://stringify.values.com'
        def command = new Command([ $URI: uri ])
        def wantCommand = new Command(
            [ $URI: uri ],
            [
                '-foo',  '42',
                '-bar',  '3.1415927',
                '-baz',  'true',
                '-quux', 'null'
            ]
        )

        matcher.load(customConfig)

        assertMatch(
            command,                 // supplied command
            wantCommand,             // expected command
            [ 'Stringify Values' ],  // expected matches
        )
    }
}