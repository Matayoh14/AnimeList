Coverage: 62%
# Hobby Project - AnimeList

This project is a simple full-stack web application, utilizing the Spring API to connect the front-end and back-end together.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The following are the prerequisite software required in order to run this project:

Java JRE (created using 'OpenJDK Runtime Environment AdoptOpenJDK-16.0.1+9 (build 16.0.1+9)')
Download at 'https://www.oracle.com/uk/java/technologies/javase-jre8-downloads.html'
or the JDK equivalent at 'https://adoptopenjdk.net/'

MySQL Server (created using v8.0)
Download the installer at 'https://dev.mysql.com/downloads/installer/'

### Installing

Run the installers for the aforementioned software in the prerequisites section.

## Running the Tests

### Unit Tests
Unit testing is the act of running tests on individual components of code to ensure each part works as intended. These tests were done using JUnit & Mockito, which can be found in 'src/test/java/com/qa/animelist/unit/'. Here my unit tests can be found which test the large majority of the functions in my classes. While I only reached 62% line coverage, the 38% was largely made up by the auto-generated 'hashCode' & 'equals' classes.

## Example
```
@MockBean
	private AnimeListService service;

	@Autowired
	AnimeListController controller = new AnimeListController(service);

	@Autowired
	private AnimeMapper mapper;

	@Test
	public void CreateAnimeTest() {
		// Given
		Anime newAnime = new Anime(1, "Erased", "Thriller", 12L, 1L);
		AnimeDTO expected = mapper.mapToDTO(newAnime);

		// When
		Mockito.when(this.service.createAnime(newAnime)).thenReturn(expected);

		// Then
		assertThat(this.controller.createAnime(newAnime)).isEqualTo(expected);

		Mockito.verify(this.service, Mockito.times(1)).createAnime(newAnime);
	}
```

### Integration Tests
Integration testing is the act of running tests against the various services your project should provide to see if your code works well in tandem to deliver the desired effects. These tests were done using JUnit & Spring's MockMvc in order to send mock requests to my codebase and observe the result.

##Example
```
@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void CreateTest() throws Exception {
		Anime testAnime = new Anime("Erased", "Thriller", 12L, 1L);
		String testAnimeAsJSON = this.mapper.writeValueAsString(testAnime);
		
		Anime testSavedAnime = new Anime("Erased", "Thriller", 12L, 1L);
		testSavedAnime.setId(2);
		String testSavedAnimeAsJSON = this.mapper.writeValueAsString(testSavedAnime);
		
		RequestBuilder mockRequest = post("/animelist/create").content(testAnimeAsJSON)
						.contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = status().isOk();
		
		ResultMatcher checkBody = content().json(testSavedAnimeAsJSON);
		
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);		
	}
```

### Acceptance Tests
Acceptance testing is the act of testing the front-end of your code by scripting the actions users would need to take in order to achieve various user stories. These test were JUnit & Selenium which handled converting java code into actionable scripts that could followed during the tests.

## Example
```
private WebDriver driver;
	
	@Test
	public void createAnimeTest() throws InterruptedException {
		driver.get("http://localhost:8080/");
		
		driver.findElement(By.id("title")).sendKeys("Erased");
		driver.findElement(By.id("genre")).sendKeys("Thriller");
		driver.findElement(By.id("episodes")).sendKeys("12");
		driver.findElement(By.id("season")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id=\"createForm\"]/button[2]")).click();
		
		Thread.sleep(500);
		
		assertThat(driver.findElements(By.className("card-text")).get(4).getText()).isEqualTo("Title: Erased");
	}
```

## Deployment

In order to setup the AnimeList project to work with your specific database, it is necessary to change the properties used by the project (located in 'scr/main/resources/application-prod.properties').

spring.datasource.url should hold the location that you want the Animelist's database to exist, in the form 'spring.datasource.url=jdbc:mysql://<hostname>:<port>/animelist?useSSL=false' (Set to 'spring.datasource.url=jdbc:mysql://localhost:3306/animelist?useSSL=false' by default)
	
spring.datasource.username should hold the name of the user that you want the AnimeList to access the database as, in the form 'spring.datasource.username=<name>' (Set to 'spring.datasource.username=root' by default)
	
spring.datasource.password should hold the password associated with the user, in the form 'spring.datasource.password=<password>' (Set to 'spring.datasource.password=root' by default)
	
To build the project, run 'mvn clean package' from the project's root directory.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.
  
 * **Jira** - https://matayoh14.atlassian.net/jira/software/projects/AWA/boards/2
This is a link to the Jira board used to track the project's progress. Send an e-mail to  MHolmes1@academytrainee.com to request an invite to view the board.

## Author
* **Matthew Holmes** - *AnimeList implementation* - [matayoh14](https://github.com/matayoh14)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*
