package com.woc.bdd.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/com/woc/bdd/featurefiles",
 glue = {"com.woc.bdd.cucumber"})
public class Runner {
}
