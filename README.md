# Instructions to execute the tests:

1. Install java 1.8 
2. Install maven 
3. Cop PokerStarsAPI project from Github
3. Start the mock API server
4. Open the command windows in the project folder

To test the following run the command line
#-------------------------------------------------------------------------
# Test #1
1. Retrieve all fixtures.
    1. Assert that there are 3 fixtures within the returned object.
    1. Assert that each of the 3 fixtures has a fixtureId value.

Command line: mvn -Dtest=RetrieveFixturesTest test
#-------------------------------------------------------------------------
# Test #2
2. Using the model guide in `apiDocs.html`, store a new fixture in the database.
    1. Get the new fixture.
    1. Assert, within the `teams` array, that the first object has a `teamId` of 'HOME'.
new fixture is read from the file .\src\test\resources\jsonFileInput\SampleFixture.json

Command line: mvn -Dtest=AddNewFixtureTest test
#-------------------------------------------------------------------------
# Test #3
3. To simulate latency within systems, there is an intentional, random delay to store a new fixture on the server. 
    1. Bearing the delay in mind, create a new fixture and then retrieve it as soon as it's available

Command line: mvn -Dtest=LatencyTest test
#-------------------------------------------------------------------------
# Test #4
4. Create and delete a new fixture.
    1. Assert that the fixture no longer exists.

Command line: mvn -Dtest=CreateAndDeleteFixtureTest test










