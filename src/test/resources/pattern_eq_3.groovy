// test the eq operator: fail if the strings aren't equal
config {
    profile ('Eq 3') {
        pattern {
            eq uri: 'http://www.example.com'
        }

        action {
            set result: 'OK'
        }
    }
}
