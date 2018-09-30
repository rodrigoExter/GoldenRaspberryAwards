package com.base.test.features.units;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.base.test.features.units.filme.FilmeServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ FilmeServiceTest.class })
public class SuiteTest {
	
	@BeforeClass
	public static void setUp() {
		
	}
}
