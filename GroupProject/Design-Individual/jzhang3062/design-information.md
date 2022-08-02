
**1. When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.**  
_This is not presented in the design and will implemented in the GUI._

**2. When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1.**

_This is implemented in the Setting class. The Setting class have 3 attributes: numberOfMinutes(int), size(int) and weights(HashMap). The attributes can be updated by corresponding operations: updateTime, updateSize and updateWeight._

**3. When choosing to play a word game, a player will:**

  **a. Be shown a ‘board’ of randomly generated letters.**
  
  _This is implemented through the board class. The board class will be explained in 4.e_
  
  **b. Be shown a timer counting down the number of minutes available for the game, as set in the settings.**
  
  _This is implemented through the timer class, the timer class will users the Setting class. The timer class has one attributes: TimetoEnd(int), which stores the number of seconds to the game ending time. It has CountDown function, which accept TimetoEnd as an input arguement and modified TimetoEnd to count down time._
  
  **c. Start with 0 points, which is not required to be displayed during the game.**
  
  _This is implemented  through the score attributes through the GameSession class and the GetScore Operation of the word class_
  
  **d. Until the game timer counts to zero and the game ends**
  
   **1. Enter a unique word made up of two or more letters on the board.  The word must contain only letters from the board that are each adjacent to the next (horizontally, vertically, or diagonally) and a single letter on the board may not be used twice.  The word does not need to be checked against a dictionary (for simplicity, we will assume the player enters only real words that are spelled correctly).**
  
   **2. Choose to re-roll the board at a cost of 5 points.  The board will be re-created in the same way it is generated at the start of each game, replacing each letter.  The timer will not be reset or paused.  The player’s score may go into negative values.**
  
   **3. Choose to exit the game early.**
  
  _The GameSession class implemented the step from a-d, the requirement of di, dii and diii can be fullfiled by the following attributes and operations.
  
- _The SessionID will be unique int that can be used as unique identifier of each game session. It will later used to identified the game statistics of each session._
- _The CurrentBoard attribute stores the board of current game session. The board class will be introduced in more details in the following explanation._
- _The CurrentSetting attribute stores the current Setting of current game session._
- _The CurrentTimer attribute stores the current Timer class_
- _The CurrentWord attribute stores the word that gamer tries to enter. The default will be blank. The Word class is created seperately, it has two attributes: Letters and score. Letters stores the letters of the word and score attribute is the score of the word that hold. It also has 3 operations: AddLetter(add letters to the letters attributes), UpdateScore (calculate score of the current word), IsValid(check whether current word is longer than 2)_
- _The CurrentScoreMap attributes stores the current score of each words the current game session._
- _The CurrentScore attributes stores current total score_
- _The SelectIndexes attributes stores the indexes of the select letters. When confirmWord is clicked, all SelectIndex must be at the same row, column or diagonal_
- _The NumberOfReroll attributes stores the number of times the GameSession Rerolls_

_The currentWord is initialized with a word class with letters as blank, There are 6 operations associated with the GameSession class: selectLetter, confirmWord, Reroll, Exit, AccumulateScore and GenerateStats._

_The selectLetter operation will add select letter to current word. The index of the selected letter checked as the same index cannot be added twice. It will add to the SelectIndexes as well._

_The confirmWord operation will calculate the words score and reset the CurrentWord as blank. It will also validate whether the SelectIndexes are at the same row, column and diagonal. If not, an error message must be popped out._

_The Reroll operation will reinitial the board attribute and will use accumulateScore operation to update the currentScore.

_The exit operation will call the GenerateStats operation and exit the current GameSession.

_The accumulateScore will accumalate the score when the word is confirmed. It will also update the currentScoreMap HashMap, CurrentScore, and the score for each entered word._

_The GenerateStats will generate the Statistics of current game session. The Statistics class will be introduced later in this document._
    
 **e. At the end of the game, when the timer has run out or the player chooses to exit, the final score for the game will be displayed.**
  
  _The end act will be implemented in in end operation. The calculate score action is implemented through the GenerateStats operation of the GameSession Class. The GeneratedStats will update both the GameScoreStats and WordStats class. These classes will be introduced in 6 and 7_
  
  **f. Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and the cost for each board reset will be subtracted.**
  
  _The AccumulateScore operation in the GameSession class willl implement it. This method accepts two arguments(Word, int cost). If a confirmWord is fired, the words will be checked, the score for the corresponding word will be calcualted and the cost will set to 0. If a Reroll operation is performed, the word will be blank the cost will be -5. The currentScore attribute will be updated with either the score of current word or the reroll cost. The GUI will force will 'Q' will shown as 'Qu'_
  
  **g. After the player views the score, they will continue back to the main menu.**
 
 _This is not presented in the design and will implemented in the GUI._

**4. Whenever the board is generated, or re-generated it will meet the following criteria:**
  
  **a. The board will consist of a square full of letters.  The square should have the number of letters, both horizontally and vertically, indicated by the size of the square board from the game settings (4x4, 5x5, 6x6, 7x7, or 8x8).**  
  
  **b. ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be consonants.**
  
  **c. The letter Q will be displayed as ‘Qu’ (so that Q never appears alone).**  
  
  **d. The location and particular letters should be randomly selected from a distribution of letters reflecting the weights of letters from the settings.  A letter with a weight of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming both are consonants or both are vowels).  In this way, more common letters can be set to appear more often.**
  
  **e. A letter may appear on the board more than once.**
  
  _This is implemented through the board class. The board class will use the Setting class. The board class has 3 attributes: size, boardLetters and letterWeight. Among them size and letterWeight will be initialized with the Setting attributes. The CreateBoardLetters in the board will be utilized to create board, it accepts the size and letterWeight as arguement and return a string ArrayList object. This result should assign value to the boardLetters attributes. The size attributes determine the size of this ArrayList and the length of each String in the ArrayList. The letter in the boardLeters will be chosen randomly with assigned weights that is determined by he letterWeight. The vowels and consonants will be 4 times more likely to be chosen_

**5. When choosing to view statistics, the player may view (1) game score statistics, or (2) word statistics.**

_Choosing is not presented in the design and will implemented in the GUI. The statistics class will be created for this requirement. The statistic class will have two subclasses - GameScoreStatistics and WordStatistics_

**6. For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:
The final game score**
  
  **a. The number of times the board was reset**
  
  **b. The number of words entered in the game**
  
  **c. The player may select any of the game scores from this list to view the settings for that game’s board size, number of minutes, and the highest scoring word played in the game (if multiple words score an equal number of points, the first played will be displayed).**

_This is implemented through the GameScoreStatistics class. This class includes the following attributes: GameScoreMap: HashMap<int, int>, GameResetTimesMap: HashMap<int, int>, WordNumberMap: HashMap<int, int>, BoardSizeList: HashMap<int, int>, NumberOfMinutesMap: HashMap<int, int>, HighScoreMap: HashMap<int,  String>. The Keys of these HashMap attributes will be the GameSesssion ID and the Value stores corresponding value for the attributes, for example, the GameResetTimeMap will use the SessionID as the key and the number of reset times as the value. This class will have two operations: 1 GetScoreRank, this operation accept GameScoreMap, GameResetTimeMap, WordNumberMap as arguements and will rank the game sessions by the the game session score. After each game session is finished, the game session stats will update the 3 attributes in the GameScoreStatistics. 2. The GetSessionDetail will show the value of the BoardSize, NumberOfMinutes, HighScoreMap by the given SessionID _

**7. For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:**
  
  **a. The word**
 
  **b. The number of times the word has been played, across all games**
  
 _The WordStatistic Class implements the above requirement. It will have one attribute - WordMap. The WordMap will be a HashMap, with Words the player has entered before as key and number of times the words have been played as value. Both Attributes will be update after each session. The WordStatistic class has an operation - GetWordRank, it will rank the words by the number times it has been played. As the game session ends, the generateStats operation will update the WordStatistic class WordList attribute_

**8. The user interface must be intuitive and responsive.**

**9. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.**

**10. For simplicity, you may assume there is a single system running the application.**

8-10 should be implemented through all GUI and is not reflected in the UML design.

