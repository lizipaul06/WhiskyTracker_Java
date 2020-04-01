package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFilterByYear(){
		List<Whisky> foundWhiskies = whiskyRepository.findByYear(1991);
		assertEquals("The Rosebank 12 - Flora and Fona", foundWhiskies.get(0).getName());
	}

	@Test
	public void canFilterByRegion(){
		List<Distillery> foundDistilleries = distilleryRepository.findByRegion("Speyside");
		assertEquals("Macallan", foundDistilleries.get(0).getName());
		assertEquals(2, foundDistilleries.size());
	}

	@Test
	public void canFilterByDistilleyNameAndAge(){
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryNameAndYear("Rosebank",  1991);
		assertEquals("The Rosebank 12 - Flora and Fona", foundWhiskies.get(0).getName());
	}

	@Test
	public void canFilterWhiskiesByRegion(){
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegion("Speyside");
		assertEquals(1, foundWhiskies.size());
		assertEquals("The Macallan Anniversary Malt", foundWhiskies.get(0).getName());
	}

	@Test
	public void canFilterDistilleriesByWhiskeyAge(){
		List<Distillery> foundDistilleries = distilleryRepository.findByWhiskiesAgeGreaterThan(16);
		assertEquals(1, foundDistilleries.size());
		assertEquals("Macallan", foundDistilleries.get(0).getName());
	}

}
