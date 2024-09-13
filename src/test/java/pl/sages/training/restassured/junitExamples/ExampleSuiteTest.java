package pl.sages.training.restassured.junitExamples;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import pl.sages.training.restassured.framework.commons.TestTags;

@Suite
@SelectClasses(TagsExampleTest.class)
@IncludeTags(TestTags.SMOKE_TESTS)
public class ExampleSuiteTest {
}
