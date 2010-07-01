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
}
