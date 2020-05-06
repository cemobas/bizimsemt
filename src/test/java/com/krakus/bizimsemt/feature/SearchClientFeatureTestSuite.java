package com.krakus.bizimsemt.feature;

import com.krakus.bizimsemt.controller.ClientWebControllerUnitTest;
import com.krakus.bizimsemt.repository.BuyerRepositoryIntegrationTest;
import com.krakus.bizimsemt.service.BuyerServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({ BuyerServiceIntegrationTest.class,
	BuyerRepositoryIntegrationTest.class, ClientWebControllerUnitTest.class })
public class SearchClientFeatureTestSuite {
	// Test Suite Setup (annotations) is sufficient
}
