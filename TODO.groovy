/* Add mencoder.feature.level (bool) to stash based on MEncoder version */

/* More patterns? */

    greaterThan java.version: 1.5.0
    gt pms.revision: 400
    eq uri: 'http://www.example.com'

/* Different pattern syntax? */

    uri ~~ 'http://(?:\\w+\\.)?youtube\\.com/watch\\?v=(?<video_id>[^&]+)'
    pms.revision > 400 

/* XPath scraping? */

    scrape '//foo/bar/@baz', 'foo:(?<bar>bar):baz'
    scrape '//foo/bar/text()', 'foo:(?<bar>bar):baz'

/* Or: */

    scrape uri:   stash[uri], // default
        xpath: '//foo/bar/@baz',
        regex: 'foo:(?<bar>bar):baz'
        format: 'html' // default if xpath is defined
	cookies: COOKIES_FILE

/* restore missing actions e.g. add and remove */

/* Add tests for YOUTUBE_ACCEPT */
