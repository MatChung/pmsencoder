@Typed
package com.chocolatey.pmsencoder

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.lang.NoClassDefFoundError;

/*
    for some reason, HTTPBuilder (via HTTPClient) is acting flaky under
    MIXED typing. try to pin it down
*/

class HTTPClientTest extends PMSEncoderTestCase {
    private HTTPClient http = new HTTPClient()

    void testHead() {
        assertFalse(http.head('http://www.example.com/nosuchfile.com'))
        assertTrue(http.head('http://www.example.com'))
    }

    void testGet() {
        assertNull(http.get('http://www.example.com/nosuchfile.com'))
        def example = http.get('http://www.example.com')
        assertNotNull(example)
        assertThat(example, instanceOf(String.class))
        assert example =~ 'RFC\\s+2606'
    }

    // wtf? this generates a "java.lang.NoClassDefFoundError: com/chocolatey/pmsencoder/HTTPClient$mixed_get$1"
    // error
    void testMixedGet() {
        shouldFail(NoClassDefFoundError.class) { http.mixed_get('http://www.example.com/nosuchfile.com') }
        // def example = http.mixed_get('http://www.example.com')
        // assertNotNull(example)
        // assertThat(example, instanceOf(String.class))
        // assert example =~ 'RFC\\s+2606'
    }
}
