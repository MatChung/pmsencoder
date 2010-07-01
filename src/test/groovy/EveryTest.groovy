@Typed
package com.example

import groovy.util.GroovyTestCase

class ExampleClosure {
    boolean call() { true }
}

class EveryTest extends GroovyTestCase {
    ArrayList<ExampleClosure> closures = new ArrayList<ExampleClosure>()
    int numClosures = 10

    void setUp() {
        numClosures.times { closures << new ExampleClosure() }
    }

    void testEach() {
        Reference count = [ 0 ]
        closures.each { if (it()) count += 1 } // OK: element type is inferred
        assert count == numClosures 
    }

    void testEvery() {
        // XXX this fails to compile with error: "Cannot find method Object.call()"
        assert closures.every { it() }
    }
}
