package pl.sages.training.restassured.junitExamples;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import pl.sages.training.restassured.TestTags;

@Suite
//@SelectPackages("pl.sages")
@SelectClasses(TagsExampleTest.class)
@IncludeTags(TestTags.SMOKE_TESTS)
public class ExampleSuiteTest {
}
