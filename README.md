# Twitter API Test Suite

Automated integration tests for the Twitter API v1.1, built with [REST Assured](https://rest-assured.io/) and [TestNG](https://testng.org/).

## Prerequisites

- Java 8+
- Maven 3+
- A Twitter Developer account with an app that has **Read, Write, and Direct Messages** permissions

## Configuration

Credentials are **never stored in source code**. Supply them via environment variables or JVM system properties.

### Environment variables (recommended)

```bash
export TWITTER_CONSUMER_KEY=your_consumer_key
export TWITTER_CONSUMER_SECRET=your_consumer_secret
export TWITTER_ACCESS_TOKEN=your_access_token
export TWITTER_SECRET_TOKEN=your_access_token_secret
```

### JVM system properties

```bash
mvn test \
  -Dtwitter.consumerKey=your_consumer_key \
  -Dtwitter.consumerSecret=your_consumer_secret \
  -Dtwitter.accessToken=your_access_token \
  -Dtwitter.secretToken=your_access_token_secret
```

### Retweet target

The retweet test uses a default tweet ID. Override it when needed:

```bash
export TWITTER_RETWEET_ID=<tweet_id>
# or
-Dtwitter.retweetId=<tweet_id>
```

## Running the tests

### Full suite

```bash
mvn test
```

Runs all tests defined in `testng.xml`: create tweet, latest tweets, retweet, trends by location, send direct message, follower list, and negative tests.

### Smoke suite

Fast subset covering core write/read operations.

```bash
mvn test -Dsurefire.suiteXmlFiles=testng-smoke.xml
```

Includes: `CreateTweetTest`, `LatestTweetsTest`, `ReTweetTest`, `SendDirectMessageTest`.

### Regression suite

Full suite including edge cases and negative tests.

```bash
mvn test -Dsurefire.suiteXmlFiles=testng-regression.xml
```

### Delete flows

Run separately from the main suite. Each flow searches for existing records, then deletes them.

```bash
# Search for automated tweets, then delete them
mvn test -Dsurefire.suiteXmlFiles=testngDeleteTweets.xml

# Fetch direct messages, then delete them
mvn test -Dsurefire.suiteXmlFiles=testngDeleteDirectMessages.xml
```

These suites enforce ordering: the search/fetch step runs first and populates the ID list; the delete step depends on it via `dependsOnGroups`.

## Project structure

```
src/test/java/com/twitterapi/
├── base/
│   └── BaseLoader.java          # REST Assured setup, credential loading, shared state
├── functionality/
│   ├── CreateTweetTest.java      # POST /statuses/update
│   ├── LatestTweetsTest.java     # GET /statuses/home_timeline
│   ├── ReTweetTest.java          # POST /statuses/retweet/:id
│   ├── TrendsByLocationTest.java # GET /trends/place
│   ├── SendDirectMessageTest.java# POST /direct_messages/events/new
│   ├── FriendListTest.java       # GET /friends/list
│   ├── A_SearchTweetsTest.java   # GET /statuses/user_timeline (feeds delete flow)
│   ├── DeleteTweetsTest.java     # POST /statuses/destroy/:id
│   ├── A_GetDirectMessagesTest.java # GET /direct_messages/events/list (feeds delete flow)
│   ├── DeleteMessagesTest.java   # DELETE /direct_messages/events/destroy
│   └── NegativeTests.java        # 401 / 403 / 404 error path tests
├── resources/
│   ├── IUserOperations.java      # API endpoint constants and URL builders
│   └── IPayLoad.java             # Request body builders
└── utility/
    └── IUtilities.java           # Shared logging helpers
```

## Test groups

| Group | What it covers |
|---|---|
| `smoke` | Create tweet, latest tweets, retweet, send DM |
| `regression` | Everything above plus trends, friend list, delete flows, negative tests |

## Logging

Log output is written via Log4j 2. Configuration is in `src/test/java/log4j2.xml`. File logs are written under `src/logs/`.
