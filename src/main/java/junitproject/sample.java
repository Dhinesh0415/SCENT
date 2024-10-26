package junitproject;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class sample extends baseclass {
@Before
	public void browser() {
	System.out.println("launchbrowser");
	LaunchBrowser();
	
	}
@Test
public void ts1() {
	System.out.println("launchurl");
launchurl("https://www.youtube.com/");

}}
