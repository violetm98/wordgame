# Test Plan


**Author**: Team 59

##  Version Control

- This is Version 2 of the test plan
- Compared to Version 1, we added test results for the remaining test cases. We also added more test cases to test end to end flow between activities and to cover additional edge cases.

## 1 Testing Strategy

### 1.1 Overall strategy
Testing will be carried out during construction and transition phases.
Team will perform continuous unit testing and integration testing. Developer should write unit test for each module developed. The QA will be responsible for generating and running the integration test. The integration test cases and unit test are run every time the project is built
UAT testing will be performed once all individual features are integrated or when features of subsystem are integrated. UAT should be performed
early and frequently as well. 

### 1.2 Test Selection

For unit test where independently testable features are tested, we plan to use control flow based white box testing technique.
For integration test, we plan to use partition category method and we will use the tsl generator to generate test frames and test cases.

### 1.3 Adequacy Criterion

For unit test, We are going to use statement or branch coverage criterion to attain 80-90% code coverage. 
For integration test, we are going to compare generated test cases with use case model to attain 80-90% use case coverage

### 1.4 Bug Tracking

Since we don't have access to JIRA, we plan to track bugs and enhancement requests in a shared Google sheet. We will label the bugs or enhancements with tags such as Assigned, Open, In Progress, Under Review, Resolved.
Each desired feature or bug fixes will have a short description, acceptance criteria and story points assigned to it. Developers will
estimate story points for each task based on development effort.
Developers can pick open issues in the backlog and assign tasks to themselves
Once bug is fixed or new feature is implemented, developer will send a pull request and all team members will perform code review based on 
acceptance criteria. Once approved by all team members, features or bug fixes will be merged into master branch

### 1.5 Technology

AndroidJUnit for unit testing. 
Espresso for UI and integration testing

## 2 Test Cases

| No. | Purpose | Steps necessary | Expected Results | Actual Results | Pass/Fail | Additional Information |
|-----|---------|-----------------|------------------|----------------|-----------|-----------------------|
| 1 | Verify ValidateWord function with empty input | Input Indexes: Array() | Exception  | JUNIT testEmptyWords() |  Pass |   |
| 2 | Verify ValidateWord function with input word length < 2 | Input Indexes: Array((1,2)) | Exception  | JUNIT testShortWords()  | Pass  |   |
| 3 | Verify ValidateWord function with non adjacent input | Input Indexes Array((0,0)(2,1)(1,0))  | Exception  | JUNIT testWordNotConnect()   | Pass  |   |
| 4 | Verify ValidateWord function with duplicate words | Input Indexes Array((0,2),(0,3),(0,4)) and Array((0,2),(0,3),(1,3)) with the same letters| Exception  |JUNIT testWordDuplicateWord()  | Pass  |   |
| 5 | Verify ValidateWord function with valid input  |  Input Indexes: Array((1,1), (2,2), (3,3)) | True, wordFrequency, wordScore and final score attributes will be updated | JUNIT testEnterWord()  | Pass  |   |
| 6 | Verify generateBoard function with default setting  | Input Default setting | 4x4 board generated |  4x4 board generated |  Pass |   |
| 7 | Verify generateBoard function with custom size | Input size = 5  | 5x5 board generated  | 5x5 board generated  |  Pass |   |
| 8 | Verify generateBoard function with custom total time | total time = 4  | timer is set to 4 minutes  | timer is set to 4 minutes  |  Pass |   |
| 9 | Verify generateBoard function with custom weights | Input with custom weights  | Verify Distribution of letters  | Letters generated according to weight  |  Pass |   |
| 10 | Verify generateBoard function to display 'Q' as 'Qu'| Input letter 'Q' is selected  | 'Q' Displayed as 'Qu'  | 'Q' Displayed as 'Qu  |  Pass |   |
| 11 | Verify reRoll | Run reRoll function with current settings | New board is generated. Timer is not reset. 5 points are deducted from current score and numRerolls is incremented by 1 |  As expected | Pass  |   |
| 12 | Verify generateSessionStats by checking updates in wordStats | Start game, enter 3 to 4 valid input, exit current game & check wordStats for updates  | wordStats is updated according to wordFrequency map from the current session  | As expected  | Pass |   |
| 13 | Verify generateSessionStats by checking updates in gameStats | Start game, enter 3 to 4 valid input, exit current game & check gameStats for updates | gameStats is updated using variables from the current game |  As expected | Pass  |   |
| 14 | Verify start  |  Start game | New game session is created with current setting. Timer is started and board is generated  | As expected  | Pass  |   |
| 15 | Verify exit |  Exit current game | Game session is terminated and final score is displayed. gameStats and wordStats are updated accordingly | As expected   | Pass  |   |
| 16 | Verify viewStats (wordStats)| Play couple of rounds of game then viewStats. |  word frequency needs to be displayed in descending order | As expected  | Pass  |   |
| 17 | Verify viewStats (gameStats) |Play couple of rounds of game then viewStats. | List of scores are displayed in descending orders. All required fields are displayed  | As expected  | Pass  |   |
| 18 | Verify select game setting from viewStats  | select a specific game score in the displayed list  | Setting of the selected game should be displayed  | As expected  | Pass  |   |
| 19 | Verify the final score count 'Qu' as two points  | Input valid words with Qu | 'Qu' should count as 2 points | As expected  | Pass  |   |
| 20 | Verify enter two validate letters and the score is 2 | Input valid words with 2 letters| final score = 2| As expected  | Pass  |   |
| 21 | Verify score ranking  | Input: words with 10 letters| score = 10, should be the top game session| As expected  | Pass  |   |
| 22 | Verify default setting when app is launched for the first time | Open setting page, open new game| setting page should display boardsize spinner value at 4, timer spinner at 3, all 26 letter weight spinners at 1, the game session should display a board with size of 4*4, and timer with 3 minutes| As expected  | Pass  |   |
| 23 | Verify score ranking  | Input: words with 10 letters| score = 10, should be the top game session| As expected  | Pass  |   |
| 24 | Verify the game session takes new setting after the setting is changed  | In the setting page, change board size to 8, and timer to 4, confirm and open new game| Game session should display a board with size of 8*8, and timer with 4 mins |As expected  | Pass  |   |
| 25 | Verify the game session preserves the previous setting if the setting did not change  | Open new game| Game session should display a board with size of 8*8, and timer with 4 mins| As expected  | Pass  |   |
| 26 | Verify in the wordStats page, the back arrow button should navigate to stats home page | Click back arrow button | Should switch to stats home page | As expected  | Pass  |   |
| 27 | Verify in the wordStats page, the refresh button should navigate to app home page | Click refresh button | Should switch to app home page | As expected  | Pass  |   |
| 28 | Verify in the gameStats page, the back arrow button should navigate back to stats home page | Click back arrow button | Should switch to stats home page | As expected  | Pass  |   |
| 29 | Verify in the gameStats page, the refresh button should navigate to app home page | Click refresh button | Should switch to app home page | As expected  | Pass  |   |
| 30 | Verify in the stats home page, the back arrow button should navigate back to stats home page and refresh button could navigate to app home page | Click refresh button | Should switch to app home page | As expected  | Pass  |   |
| 31 | Verify duplicate letter input | Input: Array((0,0),(0,0)) | Exception | JUNIT testWordDuplicateLetter() | Pass  |   |
| 32 | Verify the GameStats is persistent between two runs | Play rounds of games, close app and reopen app| GameStats should persist| As expected | Pass  |   |
| 33 | Verify the WordStats is persistent between two runs | Play rounds of games, close app and reopen app | WordStats should persist| As expected | Pass  |   |
