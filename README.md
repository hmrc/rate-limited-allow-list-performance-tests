**This is a template README.md.  Be sure to update this with project specific content that describes your performance test project.**

# rate-limited-allow-list-performance-tests

Performance test suite for the `rate-limited-allow-list`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Services

Start Mongo Docker container following instructions from the [MDTP Handbook](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/set-up-mongodb.html).

Start `RATE_LIMITED_ALLOW_LIST_ALL` services as follows:

```bash
sm2 --start RATE_LIMITED_ALLOW_LIST_ALL
```

## Tests

Run smoke test (locally) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

Run full performance test (locally) as follows:

```bash
sbt -DrunLocal=true gatling:test
```

Run smoke test (staging) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=false gatling:test
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
