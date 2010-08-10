// test the eq operator
config {
    profile ('Eq 1') {
        pattern {
	    def var = 'example'
            eq uri: "http://www.${var}.com"
        }

        action {
            set result: 'OK'
        }
    }
}
