package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.TestSQLCommandManager;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    TestSQLCommandManager.class
})

public class JunitTestSuite {
}