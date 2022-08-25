package com.newtech.Unapec_Test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        features = "src/test/resources/features",
        glue="com.newtech.Unapec_Test.definitions")
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}
