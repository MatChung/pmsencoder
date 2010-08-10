// test the eq operator: fail if the strings aren't equal
config {
    profile ('Eq 2') {
        pattern {
            eq uri: 'http://www.example.com/extra.stuff'
        }

        action {
            set result: 'OK'
        }
    }
}
