package runners;

import apitests.AddNewFixtureTest;
import apitests.CreateAndDeleteFixtureTest;
import apitests.LatencyTest;
import apitests.RetrieveFixturesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

   @RunWith(Suite.class)
    @Suite.SuiteClasses(
            {
                    RetrieveFixturesTest.class,
                    AddNewFixtureTest.class,
                    CreateAndDeleteFixtureTest.class,
                    LatencyTest.class
            }
    )
    public class SuiteRunner {
    }

