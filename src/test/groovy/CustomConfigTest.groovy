@Typed
package com.chocolatey.pmsencoder

class CustomConfigTest extends PMSEncoderTestCase {
    void testOverrideDefaultArgs() {
        URL customConfig = this.getClass().getResource('/args.groovy')
        matcher.load(customConfig)

        def uri = 'http://www.example.com'
        def stash = new Stash(uri: uri)
        def wantStash = new Stash(uri: uri)

        assertMatch(
            uri,          // URI
            stash,        // stash
            [],           // args
            [],           // expected matches
            wantStash,    // expected stash
            [             // expected args
                '-foo',
                '-bar',
                '-baz',
                '-quux'
            ],
            true          // use the default mencoder args defined in the config file
        )
    }

    void testReplace() {
        URL customConfig = this.getClass().getResource('/replace.groovy')
        matcher.load(customConfig)

        def uri = 'http://feedproxy.google.com/~r/TEDTalks_video'
        def stash = new Stash(uri: uri)
        def wantStash = new Stash(uri: uri + '/foo/bar.baz')

        assertMatch(
            uri,          // URI
            stash,        // stash
            [],           // args
            [ 'TED' ],    // expected matches
            wantStash,    // expected stash
            [             // expected args
                '-foo',
                'bar',
            ]
        )
    }

    void testAppend() {
        URL customConfig = this.getClass().getResource('/append.groovy')
        matcher.load(customConfig)

        def uri = 'http://www.example.com'
        def stash = new Stash(uri: uri)
        def wantStash = new Stash(uri: uri)

        assertMatch(
            uri,           // URI
            stash,         // stash
            [],            // args
            [ 'Example' ], // expected matches
            wantStash,     // expected stash
            [              // expected args
                '-an',
                'example'
            ]
        )
    }

    // basic test of the eq operator
    void testEqPattern() {
        URL customConfig = this.getClass().getResource('/pattern_eq_1.groovy')
        matcher.load(customConfig)

        def uri = 'http://www.example.com'
        def stash = new Stash(uri: uri)
        def wantStash = new Stash(uri: uri)

        assertMatch(
            uri,           // URI
            stash,         // stash
            [],            // args
            [ 'Eq 1' ], // expected matches
            wantStash,     // expected stash
            [              // expected args
                'result',
                'OK',
            ]
        )
    }

    // ditto, but make sure we can use a GString on RHS
    void testEqPatternWithGString() {
        URL customConfig = this.getClass().getResource('/pattern_eq_2.groovy')
        matcher.load(customConfig)

        def uri = 'http://www.example.com'
        def stash = new Stash(uri: uri)
        def wantStash = new Stash(uri: uri)

        assertMatch(
            uri,           // URI
            stash,         // stash
            [],            // args
            [ 'Eq 2' ],    // expected matches
            wantStash,     // expected stash
            [              // expected args
                'result',
                'OK'
            ]
        )
    }

    // test eq again: don't succeed if the strings aren't equal
    void testEqPatternFailure() {
        URL customConfig = this.getClass().getResource('/pattern_eq_3.groovy')
        matcher.load(customConfig)

        def uri = 'http://www.example.com/extra_stuff'
        def stash = new Stash(uri: uri)
        def wantStash = new Stash(uri: uri)

        assertMatch(
            uri,           // URI
            stash,         // stash
            [],            // args
            [],            // expected matches
            wantStash,     // expected stash
            []             // expected args
        )
    }
}
